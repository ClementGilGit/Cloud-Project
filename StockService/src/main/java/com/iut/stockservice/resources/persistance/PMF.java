package com.iut.stockservice.resources.persistance;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.Path;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * 
 * On utilise DatastoreService car plus simple et plus rapide à utiliser que le PMF
 *
 */
public final class PMF {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	public PMF(){}
	
	public static PersistenceManagerFactory get(){
		return pmfInstance;
	}
}