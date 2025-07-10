package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String name;
    private String author;
    private String isbn;
    private double rating;

    // Constructor
    Book(String name, String author, String isbn, double rating) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.rating = rating;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    
    public String toString() {
        return "Book Name: " + name + ", Author: " + author + ", ISBN: " + isbn + ", Rating: " + rating;
    }

    // Method to convert Book object to CSV format
    public String toCsv() {
        return name + "," + author + "," + isbn + "," + rating;
    }
}


public class LibraryManagementSystems {

	private static final String BOOKS_FILE = "LibraryManagement.csv";
	static Scanner scanner=new Scanner(System.in);
    public static List<Book> books = new ArrayList<>(); // Store books in memory

    public static void main(String[] args) {
        // Load books from the file at the start
        loadBooksFromFile();

        
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book Rating");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Top-Rated Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addBook();
                case 2 -> updateBookRating();
                case 3 -> displayAllBooks();
                case 4 -> displayTopRatedBook();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

   
    // Save books from memory back to the file
    public static void saveBooksToFile() {
        try (FileWriter fw = new FileWriter(BOOKS_FILE)) {
            for (Book book : books) {
                fw.write(book.toCsv() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to books file: " + e.getMessage());
        }
    }

    // Add a new book
    public static void addBook() {
        System.out.print("Enter book name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter rating (out of 5): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); 

        Book newBook = new Book(name, author, isbn, rating);
        books.add(newBook);
        saveBooksToFile();
        System.out.println("Book added successfully!");
    }

    // Update book rating
    public static void updateBookRating() {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter new rating (out of 5): ");
        double newRating = scanner.nextDouble();
        scanner.nextLine(); 

        boolean bookFound = false;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setRating(newRating);
                bookFound = true;
                break;
            }
        }

        if (bookFound) {
            saveBooksToFile();
            System.out.println("Book rating updated successfully!");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    // Display all books
    public static void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Display top-rated book
    public static void displayTopRatedBook() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        Book topRatedBook = books.get(0);
        for (Book book : books) {
            if (book.getRating() > topRatedBook.getRating()) {
                topRatedBook = book;
            }
        }

        System.out.println("Top-Rated Book:");
        System.out.println(topRatedBook);
    }
 // Load books from the CSV file into memory
    public static void loadBooksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] bookData = line.split(",");
                if (bookData.length == 4) {
                    String name = bookData[0];
                    String author = bookData[1];
                    String isbn = bookData[2];
                    double rating = Double.parseDouble(bookData[3]);
                    books.add(new Book(name, author, isbn, rating));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }
    
}