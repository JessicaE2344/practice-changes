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
import java.io.File;
import java.io.FileWriter;

/**
 * Reader that gets data from an underlying CSV file that contains a collection
 * of books from the Project Gutenberg library
 */
public class FaveListCsv {

    private static final String FAVORITE_LIST_RESOURCE = "Favorite_List.csv";
    private static final String FAVE_DATA_FILENAME = System.getProperty("user.home") + "/.gutenberg/" + FAVORITE_LIST_RESOURCE;
    private List<String> faves;

    /**
     * Constructs a new FaveListCsv object. 
     */
    public FaveListCsv() {

        if (!Files.exists(Path.of(FAVE_DATA_FILENAME))) {
            try {
                File csvFile = new File("Favorite_List.csv");
                System.out.println("Favorites List has been created");
                FileWriter csvWriter = new FileWriter(csvFile);
                csvWriter.append("ID");
                csvWriter.append("\n");
                csvWriter.flush();
                csvWriter.close();

            } catch (IOException e) {
                throw new RuntimeException("Unable to create favorites data file", e);
            }
        }
        readFavesFromCsv();
    }

    /**
     * Get all the favorite book IDs
     * @return a list of ID Strings
     */
    public List<String> getAll() {
        return faves;
    }

    void readFavesFromCsv() {

        if (this.faves == null) {
            this.faves = new ArrayList<>();
        }
        try {
            FileInputStream input = new FileInputStream(FAVE_DATA_FILENAME);
            Reader csvReader = new InputStreamReader(input);


            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csvReader);
            for (CSVRecord record : records) {
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                this.faves.add(record.get(0));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to get favorites from csv file", e);
        }
    }

}
