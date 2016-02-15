package com.ci.test.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ci.test.db.DbAccess;

public class StartupListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(StartupListener.class.getName());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOG.log(Level.INFO, "Initializing First time");
		//DbAccess db = new DbAccess();
		//db.createTables();
		DbAccess.getInstance().createModel();
		// TODO Auto-generated method stub
		
	}

}
