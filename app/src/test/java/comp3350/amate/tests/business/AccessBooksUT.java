package comp3350.amate.tests.business;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.amate.business.AccessBooks;
import comp3350.amate.business.InvalidSalesNumber;
import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;
import comp3350.amate.persistence.BookPersistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessBooksUT {
    private AccessBooks accessBooks;
    private BookPersistence bookPersistence;

    @Before
    public void setUp() {
        bookPersistence = mock(BookPersistence.class);
        accessBooks = new AccessBooks(bookPersistence);
    }

    @Test
    public void getBookSequential_TEST() {
        System.out.println("Starting getBookSequential_TEST\n");

        Book testBook = new BookBuilder().setNewTitle("The Inferno").createBook();
        List<Book> mockList = new ArrayList<>();
        mockList.add(testBook);
        when(bookPersistence.getBookSequential()).thenReturn(mockList);

        List<Book> list = accessBooks.getBookSequential();
        assertNotNull("List should exist", list);
        assertNotNull("A book should exist", list.get(0));
        assertTrue("Title should be 'The Inferno'", (0 == list.get(0).getBookTitle().compareTo("The Inferno")));

        System.out.println("Finished getBookSequential_TEST\n");
    }

    @Test
    public void getBook_TEST() {
        System.out.println("Starting getBook_TEST\n");

        Book testBook = new BookBuilder().setNewTitle("The Inferno").createBook();
        when(bookPersistence.getBook("The Inferno")).thenReturn(testBook);

        Book result = accessBooks.getBook("The Inferno");
        assertNotNull("A book should exist", result);
        assertTrue("Title should be 'The Inferno'", (0 == result.getBookTitle().compareTo("The Inferno")));

        System.out.println("Finished getBook_TEST\n");
    }

    @Test
    public void getBestSeller_TEST() {
        System.out.println("Starting getBestSeller_TEST(\n");

        Book testBook = new BookBuilder().setNewTitle("The Inferno").createBook();
        when(bookPersistence.getBestSeller()).thenReturn(testBook);

        Book result = accessBooks.getBestSeller();
        assertNotNull("A book should exist", result);
        assertTrue("Title should be 'The Inferno'", (0 == result.getBookTitle().compareTo("The Inferno")));

        System.out.println("Finished getBestSeller_TEST(\n");
    }


    @Test
    public void updateSales_TEST() throws InvalidSalesNumber {
        System.out.println("Starting updateSales_TEST");

        Book testBook = new BookBuilder().setNewTitle("The Inferno").createBook();
        testBook.addSale(3);
        when(bookPersistence.getBook("The Inferno")).thenReturn(testBook);
        boolean exceptionCaught = false;
        try {
            accessBooks.updateSales("The Inferno", -2);
        } catch (Exception e) {
            exceptionCaught = true;
        }
        assertTrue(exceptionCaught);
        Book book = accessBooks.getBook("The Inferno");
        assertEquals((long) 3, book.getBookSales());

        System.out.println("Finished updateSales_TEST\n");
    }

    @Test
    public void rateBook_TEST() {
        System.out.println("Starting rateBook_TEST(\n");

        accessBooks.rateBook("The Inferno", 2);
        verify(bookPersistence).rateBook("The Inferno", 2);

        System.out.println("Finished rateBook_TEST(\n");
    }

    @Test
    public void searchTitles_TEST() {
        System.out.println("Starting searchTitles_TEST(\n");

        accessBooks.searchTitles("The Inferno");
        verify(bookPersistence).searchTitles("The Inferno");

        System.out.println("Finished searchTitles_TEST(\n");
    }

    @Test
    public void searchAuthor_TEST() {
        System.out.println("Starting searchAuthor_TEST(\n");

        accessBooks.searchAuthor("Frank");
        verify(bookPersistence).searchAuthor("Frank");

        System.out.println("Finished searchAuthors_TEST(\n");
    }


}
