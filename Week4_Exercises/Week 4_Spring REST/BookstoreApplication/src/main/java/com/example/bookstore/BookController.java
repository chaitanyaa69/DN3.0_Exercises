package com.example.bookstore;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookstore.mapper.EntityMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	 private final EntityMapper entityMapper;

	    // Inject EntityMapper via constructor
	    public BookController(EntityMapper entityMapper) {
	        this.entityMapper = entityMapper;
	    }
    private List<Book> bookList = new ArrayList<>();

    // GET method to retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookList;
    }

    // POST method to add a new book
    @PostMapping("/dto")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO addBookWithDTO(@RequestBody BookDTO bookDTO) {
        Book book = entityMapper.bookDTOToBook(bookDTO);
        bookList.add(book);
        return entityMapper.bookToBookDTO(book);
    }

    // POST method to add a new book using entity
    @PostMapping("/entity")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBookWithEntity(@RequestBody Book book) {
        bookList.add(book);
        return book;
    }

    // GET method to filter books by title and author using query parameters
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {
        return bookList.stream()
                       .filter(book -> (title == null || book.getTitle().equalsIgnoreCase(title)) &&
                                       (author == null || book.getAuthor().equalsIgnoreCase(author)))
                       .toList();
    }

    // GET method to retrieve a book by its ID with custom headers
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookList.stream()
                       .filter(book -> book.getId().equals(id))
                       .findFirst()
                       .map(book -> {
                           // Create custom headers
                           HttpHeaders headers = new HttpHeaders();
                           headers.add("X-Bookstore-API-Version", "1.0");
                           return new ResponseEntity<>(book, headers, HttpStatus.OK);
                       })
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return 404 Not Found if book is not found
    }

    // PUT method to update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                
                // Return updated book with custom headers
                HttpHeaders headers = new HttpHeaders();
                headers.add("X-Bookstore-API-Version", "1.0");
                return new ResponseEntity<>(book, headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if book is not found
    }

    // DELETE method to remove a book
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 No Content on successful deletion
    public void deleteBook(@PathVariable Long id) {
        bookList.removeIf(book -> book.getId().equals(id));
    }
}
