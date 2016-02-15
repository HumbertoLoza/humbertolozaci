package com.ci.test.db.model;

public class Login {
	
	private String username;
	private String password;
	private Long usuariofk;
	
	public Login(String username, String password, Long usuariofk) {
		this.username = username;
		this.password = password;
		this.usuariofk = usuariofk;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUsuariofk() {
		return usuariofk;
	}

	public void setUsuariofk(Long usuariofk) {
		this.usuariofk = usuariofk;
	}
	
}
