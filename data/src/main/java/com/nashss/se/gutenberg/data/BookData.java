package com.nashss.se.gutenberg.data;

/**
 * Represents the 'raw' book data from the data store
 */
public class BookData {
    private final String id;
    private final String title;
    private final String author;
    private final String link;

    /**
     * Create a new BookData object with the given values
     * @param id
     * @param title
     * @param author
     * @param link
     */
    public BookData(String id, String title, String author, String link) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }
}
