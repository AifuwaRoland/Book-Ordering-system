package comp3350.amate.tests.business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.amate.business.AccessBooks;
import comp3350.amate.objects.Book;
import comp3350.amate.persistence.BookPersistence;
import comp3350.amate.persistence.HSQL.BookPersistenceHSQLDB;
import comp3350.amate.tests.utils.TestUtils;

import static org.junit.Assert.*;

public class AccessBooksIT {
    private AccessBooks ab;
    private File tempdb;

    @Before
    public void setUP() throws IOException {
        tempdb = TestUtils.copyDB();
        BookPersistence bp = new BookPersistenceHSQLDB(tempdb.getAbsolutePath().replace(".script", ""));
        ab = new AccessBooks(bp);
    }

    @Test
    public void testAccessBooksList() {
        System.out.println("Starting testAccessBooksList\n");

        List<Book> list = ab.getBookSequential();
        assertNotNull("List should exist", list);
        assertNotNull("A book should exist", list.get(0));

        System.out.println("Finished testAccessBooksList\n");
    }

    @Test
    public void testAccessBooksGetBook() {
        System.out.println("Starting testAccessBooksGetBook\n");

        Book book = ab.getBook("The Inferno");
        assertNotNull(book);
        assertTrue(book.getBookTitle().equals("The Inferno"));

        System.out.println("Finished testAccessBooksGetBook\n");
    }

    @Test
    public void testAccessBooksGetBestSeller() {
        System.out.println("Starting testAccessBooksGetBestSeller\n");

        Book book = ab.getBestSeller();
        assertNotNull(book);
        assertTrue(book.getBookTitle().equals("Tales from Ovid"));

        System.out.println("Finished testAccessBooksGetBestSeller\n");
    }

    @Test
    public void testAccessBooksUpdateSales() {
        System.out.println("Starting testAccessBooksUpdateSales\n");

        boolean exceptionCaught = false;
        try {
            ab.updateSales("The Inferno", 7);
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertFalse(exceptionCaught);
        Book book = ab.getBook("The Inferno");
        assertEquals((long) 7, book.getBookSales());

        System.out.println("Finished testAccessBooksUpdateSales\n");
    }

    @Test
    public void testAccessBooksRateBook() {
        System.out.println("Starting testAccessBooksRateBook\n");

        //Get book with no ratings
        Book book = ab.getBook("The Inferno");
        assertEquals((long) 0, book.getBookRating());
        assertEquals((long) 0, book.getTotalRatings());

        //Test first rating
        ab.rateBook(book.getBookTitle(), 5);
        book = ab.getBook("The Inferno");
        assertEquals((long) 50, book.getBookRating());
        assertEquals((long) 1, book.getTotalRatings());

        //Test second rating
        ab.rateBook("The Inferno", 4);
        book = ab.getBook("The Inferno");
        assertEquals((long) 45, book.getBookRating());
        assertEquals((long) 2, book.getTotalRatings());

        System.out.println("Finished testAccessBooksRateBook\n");
    }

    @Test
    public void testAccessBooksSearchTitles() {
        System.out.println("Starting testAccessBooksSearchTitles\n");

        List<Book> list = ab.searchTitles("divine");
        assertEquals((long) 2, list.size());

        System.out.println("Finished testAccessBooksSearchTitles\n");
    }

    @Test
    public void testAccessBooksSearchAuthor() {
        System.out.println("Starting testAccessBooksSearchAuthor\n");

        List<Book> list = ab.searchAuthor("dante");
        assertEquals((long) 2, list.size());

        System.out.println("Finished testAccessBooksSearchAuthor\n");
    }

    @After
    public void tearDown() {
        tempdb.delete();
    }
}
