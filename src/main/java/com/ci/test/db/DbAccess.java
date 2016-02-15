package com.ci.test.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hsqldb.persist.Log;

import com.ci.test.db.dao.AbstractDAO;
import com.ci.test.db.dao.LoginDAO;
import com.ci.test.db.dao.UserDAO;

public class DbAccess {

	private static DataSource source;
	private static Connection conn;

	private static final String DATA_SOURCE = "jdbc/test";
	private static final Logger LOG = Logger.getLogger(DbAccess.class.getName());

	private DbAccess() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		source = (DataSource) envCtx.lookup(DATA_SOURCE);
	}
	
	public static DbAccess getInstance() {
		return DbAccessSingleton.INSTANCE.get();
	}
	
	public static AbstractDAO getDAO(String dao) {
		switch (dao) {
		case "USER" :
			return new UserDAO(getConnection());
		case "LOGIN" :
			return new LoginDAO(getConnection());
		}
		return null;
	}
	
	public static void createModel() {
		openConnection();
		try {
			Statement creator = conn.createStatement();
			try {
				creator.executeUpdate("DROP TABLE usuario");
				creator.executeUpdate("DROP TABLE registro");
			}catch (SQLException e) {
				LOG.info("Tables not created yet");
			}
			LOG.info("Creating usuario table");
			creator.executeUpdate("CREATE TABLE usuario(id IDENTITY, nombre VARCHAR(50) NOT NULL ,"
					+ "apellido VARCHAR(50) NOT NULL, correo VARCHAR(50) NOT NULL)");
			LOG.info("Creating registro table");
			creator.executeUpdate("CREATE TABLE registro(usuario VARCHAR(50) NOT NULL ,"
					+ "password VARCHAR(50) NOT NULL, usuariofk INTEGER NOT NULL)");
			creator.executeUpdate("ALTER TABLE registro ADD FOREIGN KEY(usuariofk) "
					+ "REFERENCES usuario(id)");
		}catch (Exception e) {
			LOG.severe("Could not create tables " + e.getMessage());
		}
	}

	private static void openConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = source.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() {
		openConnection();
		return conn;
	}
	
	
	private static class DbAccessSingleton {

		public static final ThreadLocal<DbAccess> INSTANCE;

		static {
			ThreadLocal<DbAccess> db = new ThreadLocal<DbAccess>() {
				@Override
				protected DbAccess initialValue() {
					try {
						return new DbAccess();
					} catch (Exception e) {
						return null;
					}
				}
			};
			INSTANCE = db;
		}

	}

}
