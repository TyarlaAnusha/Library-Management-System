package com.codegnan.model;

import java.util.Comparator; 
//implements comparator interface
public class PopularityComparator implements Comparator<Book> { 
    @Override 
    public int compare(Book b1, Book b2) { 
        return Double.compare(b2.getRating(),b1.getRating()); 
    } 
}