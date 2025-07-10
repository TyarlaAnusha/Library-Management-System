package com.codegnan.model;

public class Book {  
    private String title; 
    private String author; 
    private int ISBN;
    private double rating; 
  
  public Book(String title, String author,int ISBN,double rating) { 
        this.title = title; 
        this.author = author; 
        this.ISBN=ISBN;
        this.rating = rating; 
    } 
 
  public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
   
    @Override 
//    objects to string conversion
    public String toString(){ 
        return "Book{ title= " + title +
        		", author=" + author +
        		", ISBN=" + ISBN +
        		", rating=" + rating + "}"; 
    }
  
 
}