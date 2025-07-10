package com.codegnan.view;

import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import com.codegnan.model.Book;
import com.codegnan.service.LibraryService; 

public class LibraryApp { 
	
	private final LibraryService libraryService;
    private final Scanner scanner;

    public LibraryApp(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;

        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBookRating();
                    break;
                case 3:
                    displayAllBooks();
                    break;
                case 4:
                    displayTopRatedBook();
                    break;
                case 5:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void printMenu() {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. Update Book Rating");
        System.out.println("3. View All Books");
        System.out.println("4. View Top Rated Book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addBook() {
        System.out.print("Enter Title: ");
        String title= scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine(); 
        System.out.print("Enter ISBN: ");
        int ISBN= scanner.nextInt();
        System.out.print("Enter Rating: ");
        double rating = scanner.nextDouble();
        Book book = new Book(title, author,ISBN, rating);
        libraryService.addBook(book);
        System.out.println("Book added successfully!");
        
    }

    private void updateBookRating() {
        System.out.println("Enter ISBN of the book to update: ");
        int ISBN = scanner.nextInt();
        System.out.println("Enter new rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); 
        libraryService.updateBookRating(ISBN, rating);
        System.out.println("Book rating updated successfully.");
    }

    private void displayAllBooks() {
        TreeSet<Book> allBooks = libraryService.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("No books found in the library.");
        } else {
            System.out.println("\nAll Books:");
            for (Book book : allBooks) {
                System.out.println(book); 
            }
        }
    }

    private void displayTopRatedBook() {
        Book topRatedBook = libraryService.getTopRatedBook();
        if (topRatedBook == null) {
            System.out.println("No books found in the library.");
        } else {
            System.out.println("\nTop Rated Book:");
            System.out.println(topRatedBook);
        }
    }
        public static void main(String[] args) {
            LibraryService libraryService = new LibraryService();
            LibraryApp app = new LibraryApp(libraryService);
            app.run();
    }

}