package com.example.demo.entity;

public class Entity {
private long id;
private String name;
public Entity(long id, String name) {
	super();
	this.id = id;
	this.name = name;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getContent() {
	return name;
}
public void setContent(String content) {
	this.name = content;
}

}
