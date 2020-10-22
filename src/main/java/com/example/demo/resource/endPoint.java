package com.example.demo.resource;

import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.Header;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Entity;
import com.example.demo.entity.NevisUser;
import com.example.demo.service.NevisService;
import com.example.demo.service.OTPservice;

@RestController
@RequestMapping("api/testing")
public class endPoint {
	@Autowired
	OTPservice otPservice;
	@Autowired
	NevisService nevisService;
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@GetMapping("/greeting")
	public Entity greeting(@RequestParam(value = "name", defaultValue = "Bách") String name) {
		return new Entity(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/greetingNumber")
	public Entity greetingNumber(@RequestParam("name") int name) {
		return new Entity(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/path/{name}")
	public Entity greetingPath(@PathVariable("name") String name) {
		return new Entity(counter.incrementAndGet(), String.format(template, name));
	}
	
	@PostMapping("/body")
	public @ResponseBody Entity greetingBody(@RequestBody Entity entity) {
		return new Entity(counter.incrementAndGet(),String.format(template, entity.getContent()));
	}
	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "header")
	@ResponseBody
	public Entity greetingHeader(@RequestHeader("name") String name) {
		return new Entity(counter.incrementAndGet(), String.format(template, name));
	}
	@PostMapping("/formdata")
	public @ResponseBody Entity greetingFormdata(@RequestParam("file") String name) {
		return new Entity(counter.incrementAndGet(), String.format(template, name));
	}
	@PostMapping("/bodyFormdata")
	public @ResponseBody Entity greetingFile(@RequestParam("file") MultipartFile apiDocumentFile) {
		return new Entity(counter.incrementAndGet(),String.format(template, apiDocumentFile.getOriginalFilename()));
	}
	
	@GetMapping(value = "/otp", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String genOTP() {
		String otp= otPservice.generateOTP();
		return otp;
	}
	@PostMapping("/createNeviss")
	public ResponseEntity<String> createNevis(@RequestBody NevisUser entity) {			
			try {
				return nevisService.createNevis(entity);
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("không thành công",HttpStatus.BAD_REQUEST);
				// TODO Auto-generated catch block
				
			}
	}

	@GetMapping("/getTest")
	public String getTest() {
		return nevisService.getEmployees();
	}
	@GetMapping("/sendMail")
	public boolean sedMail() {
		return otPservice.sendOTP();
	}
	@GetMapping("/uid")
	public String uid() {
		return UUID.randomUUID().toString();
	}
}
