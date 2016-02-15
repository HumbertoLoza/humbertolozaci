package com.ci.test.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ci.test.db.model.User;

public class UserDAO extends AbstractDAO<User>{

	private static final String INSERT = "INSERT INTO usuario(nombre, "
			+ "apellido, correo) VALUES (?,?,?)";
	private static final String GET_BY_MAIL = "SELECT id, nombre,apellido,correo"
			+ " FROM usuario WHERE correo = ?";
	
	public UserDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public void insert(User obj) {
		try {
			LOG.info("Starting");
			PreparedStatement st = conn.prepareStatement(INSERT);
			st.setString(1, obj.getName());
			st.setString(2, obj.getLastName());
			st.setString(3, obj.getMail());
			st.executeUpdate();
			LOG.info("Inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUserByMail(String mail) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_BY_MAIL);
			ps.setString(1, mail);
			User user = new User();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getLong(1));
				user.setName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setMail(rs.getString(4));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void get(Object id) {
		// TODO Auto-generated method stub
		
	}

}
