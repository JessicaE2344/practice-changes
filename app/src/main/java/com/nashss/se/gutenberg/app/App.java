package com.nashss.se.gutenberg.app;

import com.nashss.se.gutenberg.app.dao.BookDao;
import com.nashss.se.gutenberg.app.model.Book;
import com.nashss.se.gutenberg.data.BookDataCsv;
import com.nashss.se.gutenberg.app.dao.FavoritesDao;
import com.nashss.se.gutenberg.data.FaveListCsv;

import com.nashss.se.input.console.ATAUserHandler;
import com.nashss.se.string.TextTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an instance of the Gutenberg application
 */
public class App {
    public static void main(String[] args) {
        BookDataCsv bookDataCsv = new BookDataCsv();
        BookDao bookDao = new BookDao(bookDataCsv);
        FaveListCsv faveListCsv = new FaveListCsv();
        FavoritesDao favoritesDao = new FavoritesDao(faveListCsv);

        App app = new App(new ATAUserHandler(), bookDao, favoritesDao);
        app.run();
    }

    private final ATAUserHandler inputHandler;
    private final BookDao bookDao;
    private final FavoritesDao favoritesDao;

    /**
     * Creates a new instance of the Gutenberg application
     * 
     * @param inputHandler Used to get user input from the console
     * @param bookDao      Data access object for interacting with book data
     */
    public App(ATAUserHandler inputHandler, BookDao bookDao, FavoritesDao favoritesDao) {
        this.inputHandler = inputHandler;
        this.bookDao = bookDao;
        this.favoritesDao = favoritesDao;
    }

    /**
     * Run the Gutenberg application
     */
    public void run() {
        String userResponse = "";
        do {
            System.out.println(userResponse);
            System.out.println(MenuOption.renderMenu());
            userResponse = handleUserRequest();
        } while (userResponse != MenuOption.QUIT.toString());
    }

    private String handleUserRequest() {
        int menuOptionNum = inputHandler.getInteger(
                MenuOption.min(), MenuOption.max(), "Enter an option> ");

        MenuOption option = MenuOption.fromOptionNum(menuOptionNum);
        switch (option) {
            case QUIT:
                return option.toString();
            case ALL_BOOKS:
                return allBooks();
            case BOOK_BY_ID:
                return bookById();
            case SEARCH_BOOKS_BY_AUTHOR:
                return searchByAuthor();
            case SEARCH_BOOKS_BY_TITLE:
                return searchByTitle();
            case ADD_TO_FAVORITES:
                return addToFavorites();
            default:
                return "Unimplemented Operation!";
        }
    }

    private String allBooks() {
        List<Book> books = bookDao.getAll();
        return renderBookListTable(books);
    }

    private String bookById() {
        int id = inputHandler.getInteger("Enter a book id: ");
        Book book = bookDao.getById(id);
        if (book == null) {
            return String.format("\nNo Book with that ID number (%d), please try again.\n", id);
        }
        return renderBookTable(book);
    }

    private String searchByAuthor() {
        String searchName = inputHandler.getString("Enter author name: ");
        List<Book> results = bookDao.searchByAuthor(searchName);
        if (results.size() == 0) {
            return String.format("\nNo books matched author '%s'\n", searchName);
        }
        return renderBookListTable(results);
    }

    private String searchByTitle() {
        String searchTitle = inputHandler.getString("Enter book title: ");
        List<Book> results = bookDao.searchByTitle(searchTitle);
        if (results.size() == 0) {
            return String.format("\nNo books matched title '%s'\n", searchTitle);
        }
        return renderBookListTable(results);

    }

    private String addToFavorites() {
        int id = inputHandler.getInteger("Enter the chosen book's id: ");
        Book book = bookDao.getById(id);
        String results = "";
        if (book == null) {
            return String.format("\nNo Book with that ID number (%d), please try again.\n", id);
        }
        System.out.println(renderBookTable(book));
        String addingBook = inputHandler.getString("Is this the book you would like to add? Y or N: ");
        if (addingBook.toLowerCase().equals("y")){
            results = favoritesDao.addToFave(id);
        }
        else {
            results = "Please try again after finding the correct Book ID";
        }
        return results;

    }

    private String renderBookListTable(List<Book> books) {
        List<List<String>> contents = new ArrayList<>();

        for (Book book : books) {
            contents.add(renderBookTableRow(book));
        }

        return new TextTable(bookTableColumns(), contents).toString();
    }

    private String renderBookTable(Book book) {
        List<List<String>> contents = List.of(renderBookTableRow(book));
        return new TextTable(bookTableColumns(), contents).toString();
    }

    private List<String> bookTableColumns() {
        return List.of("ID", "Link", "Title", "Author");
    }

    private List<String> renderBookTableRow(Book book) {
        return List.of(
                Integer.toString(book.getId()),
                book.getLink(),
                book.getTitle(),
                book.getAuthor());
    }

}
