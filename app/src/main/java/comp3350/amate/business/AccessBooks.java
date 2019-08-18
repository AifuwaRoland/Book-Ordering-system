package comp3350.amate.business;

import java.util.Collections;
import java.util.List;

import comp3350.amate.application.Services;
import comp3350.amate.objects.Book;
import comp3350.amate.persistence.BookPersistence;
import comp3350.amate.persistence.HSQL.PersistenceException;

public class AccessBooks {
    private BookPersistence library;
    private List<Book> books;
    private Book currentBook;

    public AccessBooks() {
        library = Services.getBookPersistence();
        books = null;
        currentBook = null;
    }//end of constructor

    public AccessBooks(BookPersistence bp) {
        this();
        library = bp;
    }

    public List<Book> getBookSequential() {
        books = library.getBookSequential();
        return Collections.unmodifiableList(books);
    }

    public Book getBook(String title) {
        return library.getBook(title);
    }

    public Book getBestSeller() {
        return library.getBestSeller();
    }

    //Call when an order is successfully placed
    public void updateSales(String title, int sales) throws InvalidSalesNumber {
        currentBook = library.getBook(title);
        if ((currentBook != null) && (sales > 0)) {
            try {
                library.updateSales(currentBook, sales);
            } catch (PersistenceException e) {
                throw new InvalidSalesNumber("Sales were not updated");
            }
        } else if (sales < 0) {
            throw new InvalidSalesNumber("Sales were not updated");
        }
    }

    public void rateBook(String title, int rating) {
        library.rateBook(title, rating);
    }

    public List<Book> searchTitles(String text) {
        return library.searchTitles(text);
    }

    public List<Book> searchAuthor(String text) {
        return library.searchAuthor(text);
    }

}