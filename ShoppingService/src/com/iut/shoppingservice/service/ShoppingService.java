package com.iut.shoppingservice.service;

import java.util.regex.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import javax.ws.rs.client.ClientBuilder;

@Path("/shopping")
public class ShoppingService {

	public static final String URL_STOCKMANAGER = "https://handy-math-164115.appspot.com/stock-service/stock";
	public static final String URL_WHOLESALERMANAGER = "https://arcane-badlands-16317.herokuapp.com/whole-saler-service";

	/**
	 * Récupère un isbn et retourne le stock du livre associé ou une erreur
	 * 
	 * @param isbn
	 * @return
	 */
	@GET
	@Path("book/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BookReq(@PathParam("isbn") String isbn) {
		String output;
		if (!isbnIsValid(isbn)) {
			output = "{\"response\":\"Erreur : ISBN invalide\"}";
			return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
		try {
			String urlTargetService = URL_STOCKMANAGER + "/" + isbn;
			Response response = ClientBuilder.newClient().target(urlTargetService).request().get();

			if (response.getStatus() == 200) {
				String str_response = response.readEntity(String.class);
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(str_response);
				output = "{\"response\":\" Livres restants : " + jsonObject.get("stock") + "\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			} else if (response.getStatus() == 204) {
				output = "{\"response\":\" Erreur : Livre inexistant\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			} else {
				output = "{\"response\":\" Erreur : Service indisponible\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (ParseException e) {
			output = "{\"response\":\" Erreur : Conversion JSON\"}";
			return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	/**
	 * Récupère un ISBN et une quantité en paramètre, achète la quantité passée
	 * et retourne le nouveau stock de livres
	 * 
	 * @param isbn
	 * @param quantity
	 * @return
	 */
	@GET
	@Path("/buy/{isbn}/{quantity}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BuyReq(@PathParam("isbn") String isbn, @PathParam("quantity") int quantity) {
		String output;
		if (!isbnIsValid(isbn)) {
			output = "{\"response\":\"Erreur : ISBN invalide\"}";
			return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
		if (quantity <= 0) {
			output = "{\"response\":\"Erreur : Quantité invalide\"}";
			return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
		try {
			String urlTargetService = URL_WHOLESALERMANAGER + "/" + isbn + "/" + quantity;
			Response response = ClientBuilder.newClient().target(urlTargetService).request().get();

			if (response.getStatus() == 200) {
				String str_response = response.readEntity(String.class);
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(str_response);
				output = "{\"response\":\" Livres achetés (restants : " + jsonObject.get("response") + ")\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			} else if (response.getStatus() == 204) {
				output = "{\"response\":\" Erreur : Livre inexistant\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			} else {
				output = "{\"response\":\" Erreur : Service indisponible\"}";
				return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (ParseException e) {
			output = "{\"response\":\" Erreur : Conversion JSON\"}";
			return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	/**
	 * Vérifie que l'ISBN soit valide
	 * 
	 * @param isbn
	 * @return
	 */
	private boolean isbnIsValid(String isbn) {
		return Pattern.matches(
				"^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$",
				isbn);
	}
}