package com.nashss.se.gutenberg.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Reader that gets data from an underlying CSV file that contains a collection
 * of books from the Project Gutenberg library
 */
public class BookDataCsv {

    private static final String BOOK_DATA_RESOURCE = "gutenberg_books.csv";
    private static final String BOOK_DATA_FILENAME = System.getProperty("user.home") + "/.gutenberg/" + BOOK_DATA_RESOURCE;
    private List<BookData> books;

    /**
     * Construct a new BookDataCsv object. If the data file does not exist in
     * the user home directory, copy it from resources.
     */
    public BookDataCsv() {

        if (!Files.exists(Path.of(BOOK_DATA_FILENAME))) {
            try {
                InputStream input = this.getClass().getClassLoader().getResourceAsStream(BOOK_DATA_RESOURCE);

                Path destinationFile = Path.of(BOOK_DATA_FILENAME);
                Path destinationDir = destinationFile.getParent();
                if (!Files.exists(destinationDir)) {
                    Files.createDirectory(destinationDir);
                }
                Files.copy(input, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to write copy source data file", e);
            }
        }
        readBooksFromCsv();
    }

    /**
     * Get all the books
     * @return a list of BookData objects
     */
    public List<BookData> getAll() {
        return books;
    }

    void readBooksFromCsv() {

        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        try {
            FileInputStream input = new FileInputStream(BOOK_DATA_FILENAME);
            Reader csvReader = new InputStreamReader(input);


            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csvReader);
            for (CSVRecord record : records) {
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                this.books.add(new BookData(record.get(0), record.get(1), record.get(2), record.get(3)));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to get book data from csv file", e);
        }
    }

}