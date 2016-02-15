package com.ci.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.ci.test.db.DbAccess;
import com.ci.test.db.dao.LoginDAO;
import com.ci.test.db.dao.UserDAO;
import com.ci.test.db.model.Login;
import com.ci.test.db.model.User;

@ManagedBean(name="app")
@SessionScoped
public class Application implements java.io.Serializable {
	
	private String name;
	private String lastName;
	private String mail;
	private String user;
	private String password;
	private boolean authenticated;
	
	private static final Logger LOG = Logger.getLogger(Application.class.getName());
	private static final String USER_TABLE = "USER";
	private static final String LOGIN_TABLE = "LOGIN";
	
	@PostConstruct
	public void init() {
		name = "";
		lastName = "";
		mail = "";
		user = "";
		password = "";
	}
	
	public String createUser() {
		LOG.log(Level.ALL, "Entrando");
		try {
			User userObj = new User(name, lastName, mail);
			
			((UserDAO)DbAccess.getInstance().getDAO(USER_TABLE)).insert(userObj);
			userObj = ((UserDAO)DbAccess.getInstance().getDAO(USER_TABLE)).getUserByMail(mail);
			Login loginObj = new Login(user, password, userObj.getId());
			(DbAccess.getInstance().getDAO(LOGIN_TABLE)).insert(loginObj);
			return "index";
		}catch (Exception ex) {
			LOG.log(Level.INFO, "Could not log user", ex);
		}
		//Insercion a base de datos
		return "error";
	}
	
	public String authenticateUser() {
		
		if (((LoginDAO)DbAccess.getInstance().getDAO(LOGIN_TABLE)).authenticate(user, password)) {
			authenticated = true;
			return "main";
		}
		return "error";
	}
	
	public String signOut() {
		authenticated = false;
		return "index";
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
}