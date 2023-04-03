## Requirements

This document outlines the features that our book project needs to have. After fixing the bugs listed, work on the Core Functionality features (Searching, Favorites, and Documentation), and then if your team has time leftover you can start on some Stretch Goals.

## Bug ~~Fixxs~~ Fixes

Sadly, a lot of the code that you will write over your career as a software developer will have bugs in it. Fortunately, a lot of the code that other people write in their careers as software developers will have bugs too - so you're in good company. The code in this project definitely has some bugs in it, we're going to fix a few of the more noticeable ones. You may have noticed these during your code exploration earlier.

1. Choose menu option `2) book by id`, and search for the id `321`. You should see something like this:
    ```text
    Exception in thread "main" java.lang.NullPointerException
      at com.nashss.se.gutenberg.app.App.renderBookTableRow(App.java:110)
      at com.nashss.se.gutenberg.app.App.renderBookTable(App.java:100)
      at com.nashss.se.gutenberg.app.App.bookById(App.java:77)
      at com.nashss.se.gutenberg.app.App.handleUserRequest(App.java:61)
      at com.nashss.se.gutenberg.app.App.run(App.java:46)
      at com.nashss.se.gutenberg.app.App.main(App.java:22)

    FAILURE: Build failed with an exception.
    ```
    It seems that we don't have a book with the ID `321`, but that doesn't seem like a reason to fail the application?!

    **BUGFIX:** Fix this behavior so that if a user searches for a book ID that does not exist they get a friendly message saying something like "No book with ID 321 exists", and are able to continue using the application.

1. Choose menu option `3) search books by author` and search for the classic mystery author `Agatha Christie`. You should see 5 books listed with her name. Try this again and search for `Christie` - we should see that same list of books. _Shouldn't we? Ahh yes, see the note below in Milestone 1 about how the different searching features should work._

    **BUGFIX:** Fix this bug so that we still see those same books if we search for `Agatha Christie` or any portion of her name. Note that you might see more than 5 books when this is fixed.

## Core Functionality
The core features are divided between a few different different milestones: searching, favorites, and then some simple documentation. You should work on the milestones in the order listed below (but may want to work on tasks within a given milestone in parallel). Be sure to read the Supporting Details section before you begin development.

### Milestone 1: Searching
These features enable users to interact with the list of books that the system knows about via different search capabilities. Some of these have been implemented already, you only need to add the features that are not currently present.

1. List all books
    * As a user, I would like to list all the books in the system.
1. Get a book by id
    * As a user, I would like to search for book details by book ID.
1. Search books by author
    * As a user, I would like to search for books that match an author name.
1. Search books by title
    * As a user, I would like to search for books that match a title.
1. Search books by author or title
    * As a user, I would like to search for books that match an author name or title.
1. Get a random book
    * As a user, I would like to be shown a single book that is chosen randomly.

> **NOTE:** The book ID search must be an exact match, but searching by author or title should return partial matches. E.g. searching for an author named "Eliot" should return "George Eliot", and "Charles Eliot", and "horn" should return "Thornton W. Burgess" and "Nathaniel Hawthorne".

### Milestone 2: Documentation
1. Update the [README](./README.md)
    * As a developer, I would like to understand how this project works.
    * Using [Markdown](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax) syntax, update the project README to include an overview of what the project does, and some things that you think another developer would appreciate knowing if they going to add more features to this project.
    > **TIP:** We like using Markdown. In fact this file (the one you are reading) is also written in Markdown. Don't be alarmed at the prospect of learning something new just to take notes, there's no need to get fancy with how you use it (unless you want to).

_Documentation is not always a top priority when writing software, but when done correctly it can be invaluable for anyone who works on the code. As you continue working through additional features on this project, make sure to keep the README updated with any significant changes._

### Milestone 3: Favorites
These features enable users to start to customize the list by marking books as ones that are their favorites.

1. List all books in the favorites list
    * As a user, I would like to view all books on my favorites list.
1. Add a book to a favorites list
    * As a user, I would like to enter a book ID and add it to my favorites list.
    * If I attempt to add the same ID multiple times it should only appear once in my favorites list.
1. Remove a book from a favorites list
    * As a user, I would like to enter a book ID and remove it from my favorites list.


### Supporting Details

* When printing book details, the output should be formatted like this:
  ```
  -----------------------------------------------
  | ID | Link        | Title      | Author      |
  ===============================================
  | id | http://link | book title | book author |
  -----------------------------------------------
  ```

    > **TIP:** `com.nashss.se.string.TextTable` will handle the formatting of the table output for you.

* The favorites list should be stored as a separate CSV file that contains a single column of book ids. A sample favorites file with 2 book ids might look like this:

    ```csv
    id
    15
    123
    ```

  You should store this file in the same directory that `BookDataCsv.java` is using to copy the book data csv file into.

## Stretch goals
Stretch goals are features that would be nice to have in an application, but it's ok if you don't have time to add them. You should not start working on these unless/until everything from Core Functionality is completed.

* Add personal notes to the favorites list.
  * As a user, I would like to save notes about books in my favorites list.
  * These notes should be displayed as an additional column when viewing the list.
* Modify the main list of books.
  * As a user, I would like to modify the list of books.
  * To remove a book from the list, search for it by book ID.
  * To add a book to the list, prompt the user for the ID, link, title, and author.
  * **TIP:** There is a lot about this that will be similar to how you saved the Favorites data.
* Paginate results.
  * As a user, I would like to view search results with pagination.
  * Viewing some of the search results can be a ... bit much ... to read through in one go. A common way to make large sets of results manageable for a user is to "paginate" them. Instead of showing all 15k books for the "list all books" functionality, we can show 10 or 20 at a time. For this stretch goal, decide on a sensible pagination strategy and implement it. Questions to consider:
    * Should all results be paginated or just some? E.g. does it make sense to paginate through 15k results or should you limit it to some/all of the search features?
    * What is a reasonable number of results per page? E.g. 10? 20? 100? 1000?
    * Does a user they have to paginate through _all_ of the results or can they opt out somehow?
