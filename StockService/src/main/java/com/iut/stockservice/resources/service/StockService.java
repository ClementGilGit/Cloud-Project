package com.iut.stockservice.resources.service;


import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.jdo.Query;

import com.sun.istack.logging.Logger;
import com.google.appengine.api.datastore.*;
import com.google.appengine.repackaged.com.google.protobuf.Empty;
//import com.google.appengine.api.datastore.Query;

@Path("/stock")
public class StockService {
	
	/**
	 * 
	 * @param isbn
	 * Méthode qui retourne le stock d'un livre grâce à son isbn
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isbn}")
    public Response getStockReq(@PathParam("isbn") String isbn){
		try {
					
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("Book").setFilter(new Query.FilterPredicate("isbn", Query.FilterOperator.EQUAL, isbn));
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1));
			String output="rien";
			if(results.size() == 0){
				output = "{\"error\":\"isbn_inexistant\"}";	
				return Response.status(204).entity(output).header("Access-Control-Allow-Origin", "*").build();
			}else{
				for (Entity result : results) {	
					output = "{\"stock\":\"" + result.getProperty("stock") +"\"}";
				}
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();	
			}		
		} catch (Exception e) {
			String output = "{\"error\":\"erreur de conversion en JSON\"}";
			return Response.status(500).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
	
	}
	/**
	 * 
	 * @param isbn
	 * @param quantity
	 * Méthode qui rajoute au stock 50 livres + la quantité nécessaire à la commande du client si
	 * sa commande dépasse le stock disponible sur le Datastore
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("add/{isbn}/{quantity}")
	public Response addBook(@PathParam("isbn") String isbn,@PathParam("quantity") int quantity){
		try {
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("Book").setFilter(new Query.FilterPredicate("isbn", Query.FilterOperator.EQUAL, isbn));
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1));
			String output="rien";
			int additionalStock = 10;
			if(results.size() == 0){
				output = "{\"error\":\"isbn_inexistant\"}";	
				return Response.status(204).entity(output).header("Access-Control-Allow-Origin", "*").build();
			}else{
				for (Entity result : results) {	
					String stockAvantCommande =""+result.getProperty("stock");
					int stockk = Integer.parseInt(stockAvantCommande);
					int manque = quantity-stockk;
					int stockcommande= additionalStock+quantity-manque-stockk;
					result.setProperty("stock", stockcommande);
					datastore.put(result);
					output = "{\"stock\":\"" + result.getProperty("stock") +"\"}";
				}
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();	
			}		
		} catch (Exception e) {
			String output = "{\"error\":\"erreur de conversion en JSON\"}";
			return Response.status(500).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	/**
	 * 
	 * @param isbn
	 * @param quantity
	 * Méthode pour modifier le stock du livre grâce à son isbn
	 * Déduit le stock si quantité commande < stock datastore
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buy/{isbn}/{quantity}")
	public Response buyBook(@PathParam("isbn") String isbn,@PathParam("quantity") int quantity){
		try {
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("Book").setFilter(new Query.FilterPredicate("isbn", Query.FilterOperator.EQUAL, isbn));
			List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withLimit(1));
			String output="rien";
			if(results.size() == 0){
				output = "{\"error\":\"isbn_inexistant\"}";	
				return Response.status(204).entity(output).header("Access-Control-Allow-Origin", "*").build();
			}else{
				for (Entity result : results) {	
					//int oldStock = (int)result.getProperty("stock");
					//result.setProperty("stock", oldStock-quantity);
					String stock =""+result.getProperty("stock");
					int stockk = Integer.parseInt(stock)-quantity;
					result.setProperty("stock", stockk);
					datastore.put(result);
					output = "{\"stock\":\"" + result.getProperty("stock") +"\"}";
				}
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();	
			}		
		} catch (Exception e) {
			String output = "{\"error\":\"erreur de conversion en JSON\"}";
			return Response.status(500).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	/**
	 * Méthode pour retourner les noms des livres du Datastore
	 */
	@GET
	@Produces("text/plain")
	@Path("all")
	public String seeBooks(){
		String chaine ="";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Book");
		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		for (Entity result : results) {
			chaine+=result.getProperty("titre")+" ";
		}
		return chaine;
	}
	

	
	
	
}
