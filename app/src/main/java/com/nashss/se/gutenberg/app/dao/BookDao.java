package com.nashss.se.gutenberg.app.dao;

import com.nashss.se.gutenberg.app.model.Book;
import com.nashss.se.gutenberg.data.BookData;
import com.nashss.se.gutenberg.data.BookDataCsv;

import java.util.ArrayList;
import java.util.List;

/**
 * A data access object for interacting with book data
 */
public class BookDao {
    private final BookDataCsv bookDataCsv;

    /**
     * Construct a new book data access object
     * @param bookDataCsv Object that reads from the underlying book data stre
     */
    public BookDao(BookDataCsv bookDataCsv) {
        this.bookDataCsv = bookDataCsv;
    }

    /**
     * Get all the books in the system
     * @return A list of books
     */
    public List<Book> getAll() {
        List<BookData> bookData = bookDataCsv.getAll();
        List<Book> books = convertBookDataList(bookData);
        return books;
    }

    /**
     * Get a single book by its id
     * @param id The idea of the book to retrieve
     * @return The book with the given id or null if no book is found
     */
    public Book getById(int id) {
        List<BookData> allBooks = bookDataCsv.getAll();

        for (BookData book : allBooks) {
            if (book.getId().equals(Integer.toString(id))) {
                return convertBookData(book);
            }
        }

        return null;
    }

    /**
     * Search books by author name.
     * Does case-sensitive 'contains' search.
     * @param searchName The name to look for
     * @return A list of books that match the author name or an empty list if nothing matched
     */
    public List<Book> searchByAuthor(String searchName) {
        List<Book> results = new ArrayList<>();

        List<BookData> allBooks = bookDataCsv.getAll();
        for (BookData book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(searchName.toLowerCase())) {
                results.add(convertBookData(book));
            }
        }

        return results;
    }

    /**
     * Search books by book title.
     * Does case-sensitive 'contains' search.
     * @param searchTitle The name to look for
     * @return A list of books that match the title or an empty list if nothing matched
     */
    public List<Book> searchByTitle(String searchTitle){
        List<Book> results = new ArrayList<>();

        List <BookData> allBooks = bookDataCsv.getAll();
        for (BookData book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                results.add(convertBookData(book));
            }
        }

        return results;
    }

    /**
     * Search books by book author or title.
     * Calls the searchByAuthor and searchByTitle methods, adding results to the list.
     * @param searchAuthorTitle The name to look for
     * @return A combined list of books that match the author and title or an empty list if nothing matched
     */
    public List<Book> searchByAuthorOrTitle(String searchAuthorTitle){
        List<Book> results = new ArrayList<>();

        results.addAll(searchByAuthor(searchAuthorTitle));
        results.addAll(searchByTitle(searchAuthorTitle));
        
        return results;
    }

    private List<Book> convertBookDataList(List<BookData> bookDataList) {
        List<Book> books = new ArrayList<>();
        for (BookData bd : bookDataList) {
            Book book = convertBookData(bd);
            books.add(book);
        }
        return books;
    }

    private Book convertBookData(BookData bookData) {
        return new Book(
                Integer.parseInt(bookData.getId()),
                bookData.getTitle(),
                bookData.getAuthor(),
                bookData.getLink()
        );
    }
}
