package com.nashss.se.gutenberg.app.dao;

import com.nashss.se.gutenberg.app.model.Book;
import com.nashss.se.gutenberg.data.BookData;
import com.nashss.se.gutenberg.data.BookDataCsv;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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
     * Add a book to the favorites list.
     * @param id The ID Number of the book being added to the list
     * @return A list of books that match the title or an empty list if nothing matched
     */
    public String addToFave(int id){
        Boolean existFave = false;
        try {
        File csvFile = new File("Favorite_List.csv");
        if (!csvFile.exists()) {
            System.out.println("Favorites List has been created");
            FileWriter csvWriter = new FileWriter(csvFile);
            csvWriter.append("ID");
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        }
        FileReader csvReader = new FileReader(csvFile);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(csvReader);
        for (CSVRecord record : records) {
            String enteredIds = record.get(0);
            if (!enteredIds.equals("ID")){
                if (Integer.parseInt(enteredIds) == id){
                    existFave = true;
                }
            }
        }
        if (existFave == true){
            return "This book is already on the favorite list";
        }
        else {
        FileWriter csvWriter = new FileWriter(csvFile, true);
        csvWriter.append(""+id);
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();
        return ("Book ID: " + id + " has been added to your favorites list");
        }
        } catch (IOException e) {
            e.printStackTrace();
            return "An error has occured";
        }
        
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
