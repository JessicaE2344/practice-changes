package com.nashss.se.gutenberg.app.model;

/**
 * Represents a book from the Project Gutenberg Library
 */
public class Book {
    private final int id;
    private final String title;
    private final String author;
    private final String link;

    /**
     * Create a enw book with the given values
     * @param id The id of the book
     * @param title The title of the book
     * @param author The author of the book
     * @param link A URL that points to the book's info on the Project Gutenberg website
     */
    public Book(int id, String title, String author, String link) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.link = link;
    }

    public int getId() {
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

    @Override
    public String toString() {
        // This is really only useful for debugging.
        // It's not very friendly for the user.
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
