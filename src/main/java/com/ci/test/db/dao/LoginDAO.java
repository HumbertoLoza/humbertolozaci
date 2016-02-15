package com.ci.test.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import com.ci.test.db.model.Login;

public class LoginDAO extends AbstractDAO<Login> {

	private static final String INSERT_LOGIN = "INSERT INTO registro(usuario,"
			+ "password,usuariofk) VALUES(?,?,?)";
	private static final String AUTHENTICATION = "SELECT usuario FROM registro"
			+ " WHERE usuario = ? AND password = ?";
	
	public LoginDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public void insert(Login obj) {
		try {
			PreparedStatement st = this.conn.prepareStatement(INSERT_LOGIN);
			st.setString(1, obj.getUsername());
			st.setString(2, obj.getPassword());
			st.setLong(3, obj.getUsuariofk());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void get(Object id) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean authenticate(String username, String password) {
		try {
			LOG.info(username + " and  " + password);
			PreparedStatement ps = this.conn.prepareStatement(AUTHENTICATION);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				LOG.log(Level.INFO, rs.getString(1));
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
		
	}

}
