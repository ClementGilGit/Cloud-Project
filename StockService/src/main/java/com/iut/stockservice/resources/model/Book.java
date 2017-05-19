package com.iut.stockservice.resources.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * Classe Book permettant de définir l'entité d'un livre
 * Pas vraiment utile maintenant car on utilise DatastoreService pour les requetes en BDD
 */
@PersistenceCapable
public class Book {

	    @PrimaryKey
	    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	    private Key key;
	    @Persistent
	    private String isbn;
	    @Persistent
	    private String titre;
	    @Persistent
	    private String auteur;
	    @Persistent
	    private int stock;
	    @Persistent
	    private double prix;
	    
	    	    
	    public Book(String isbn, String titre, String auteur, Double prix) {
			this.isbn = isbn;
			this.titre = titre;
			this.auteur = auteur;
			this.prix = prix;
		}
	
	public Book(String isbn, String titre, String auteur, Double prix,int stock) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
		this.prix = prix;
		this.stock = stock;
	}
	
	public Book(String isbn,String auteur){
		this.isbn = isbn;
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public Double getPrix() {
		return prix;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
}