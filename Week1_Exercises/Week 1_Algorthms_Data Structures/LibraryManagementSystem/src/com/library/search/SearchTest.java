package com.library.search;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchTest {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("1", "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("2", "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("3", "1984", "George Orwell"));
        books.add(new Book("4", "Pride and Prejudice", "Jane Austen"));
        books.add(new Book("5", "The Catcher in the Rye", "J.D. Salinger"));

        System.out.println("Linear Search:");
        Book foundBook = Library.linearSearchByTitle(books, "1984");
        if (foundBook != null) {
            System.out.println("Found: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
        } else {
            System.out.println("Book not found.");
        }

        System.out.println("\nBinary Search:");
        // Sort the books by title before performing binary search
        Collections.sort(books, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        foundBook = Library.binarySearchByTitle(books, "1984");
        if (foundBook != null) {
            System.out.println("Found: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }
}
