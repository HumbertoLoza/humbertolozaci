package com.ci.test.db.model;

public class User {

	private String name;
	private String lastName;
	private String mail;
	private Long id;
	
	public User(String name, String lastName, String mail) {
		this.name = name;
		this.lastName = lastName;
		this.mail = mail;
	}
	
	public User() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
}
