package comp3350.amate.persistence;

import java.util.List;

import comp3350.amate.objects.Book;

public interface BookPersistence {
    List<Book> getBookSequential();

    List<Book> searchTitles(String s);

    List<Book> searchAuthor(String s);

    Book getBook(String title);

    Book getBestSeller();

    Book updateSales(Book book, int sales);

    void rateBook(String title, int rating);
}