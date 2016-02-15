package com.ci.test.db.dao;

import java.sql.Connection;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> {
	
	public abstract void insert(T obj);
	public abstract void get(Object id);
	
	protected static final Logger LOG = Logger.getLogger(AbstractDAO.class.getName());
	
	protected Connection conn;
	
	public AbstractDAO(Connection conn) {
		this.conn = conn;
	}
	
}
