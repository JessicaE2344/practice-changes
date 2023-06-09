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
    private List<BookData> allBooks;
    private List<Book> books;

    /**
     * Construct a new book data access object
     * @param bookDataCsv Object that reads from the underlying book data stre
     */
    public BookDao(BookDataCsv bookDataCsv) {
        this.bookDataCsv = bookDataCsv;
        this.allBooks = bookDataCsv.getAll();
        this.books = convertBookDataList(allBooks);
    }

    /**
     * Get all the books in the system
     * @return A list of books
     */
    public List<Book> getAll() {
        return books;
    }

    /**
     * Get a single book by its id
     * @param id The idea of the book to retrieve
     * @return The book with the given id or null if no book is found
     */
    public Book getById(int id) {
        for (BookData book : allBooks) {
            if (book.getId().equals(Integer.toString(id))) {
                return convertBookData(book);
            }
        }
        return null;
    }

    /**
     * Search books by author name.
     * Does case-insensitive 'contains' search.
     * @param searchName The name to look for
     * @return A list of books that match the author name or an empty list if nothing matched
     */
    public List<Book> searchByAuthor(String searchName) {
        List<Book> results = new ArrayList<>();

        for (BookData book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(searchName.toLowerCase())) {
                results.add(convertBookData(book));
            }
        }

        return results;
    }

    /**
     * Search books by book title.
     * Does case-insensitive 'contains' search.
     * @param searchTitle The name to look for
     * @return A list of books that match the title or an empty list if nothing matched
     */
    public List<Book> searchByTitle(String searchTitle){
        List<Book> results = new ArrayList<>();

        for (BookData book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                results.add(convertBookData(book));
            }
        }

        return results;
    }

    /**
     * Search books by book author or title.
     * Does a case-insensitive 'contains' search in the author and title fields.
     * @param searchAuthorTitle The name to look for
     * @return A list of books that match the author or title, or an empty list if nothing matched
     */
    public List<Book> searchByAuthorOrTitle(String searchAuthorTitle){
        List<Book> results = new ArrayList<>();

        for (BookData book : allBooks) {
            if (book.getTitle().toLowerCase().contains(searchAuthorTitle.toLowerCase()) || 
                    book.getAuthor().toLowerCase().contains(searchAuthorTitle.toLowerCase())){
                results.add(convertBookData(book));
            }
        }
        
        return results;
    }

    /**
     * Removes a particular book from all of the books based on id
     * 
     * @param id The id of the book to remove
     * @return The removed book from all books
     */
    public Book removeBookFromList(String id) {
        Book bookToRemove = null;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == Integer.parseInt(id)) {
                bookToRemove = books.get(i);
                books.remove(i);
                allBooks.remove(i);
                break;
            }
        }

        return bookToRemove;
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
