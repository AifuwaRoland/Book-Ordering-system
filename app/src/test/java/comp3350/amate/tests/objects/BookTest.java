package comp3350.amate.tests.objects;

import org.junit.Before;
import org.junit.Test;

import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;

import static org.junit.Assert.*;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        this.book = new BookBuilder()
                .setNewTitle("The Inferno")
                .setNewAuthor("Dante Alighieri")
                .setNewBookReleaseDate("October 6, 2009")
                .setNewPrice(536)
                .setNewFormat("Hard copy")
                .setNewISBN(101)
                .createBook();
    }

    @Test
    public void testBookConstruction() {
        System.out.println("Starting testBookConstruction\n");

        assertNotNull(book);
        assertTrue("The Inferno".equals(book.getBookTitle()));
        assertTrue("Dante Alighieri".equals(book.getBookAuthor()));
        assertTrue("October 6, 2009".equals(book.getBookReleaseDate()));
        assertTrue("No description available yet".equals(book.getBookDescription()));
        assertEquals((long) 0, (long) book.getBookRating());
        assertEquals((long) 0, (long) book.getTotalRatings());
        assertEquals((long) 0, (long) book.getBookSales());
        assertEquals((long) 536, (long) book.getBookPrice());
        assertTrue("Hard copy".equals(book.getFormat()));
        assertEquals((long) 101, (long) book.getISBN());

        System.out.println("Finished testBookConstruction\n");
    }

    @Test
    public void testBookSetDescription() {
        System.out.println("Starting testBookSetDescription\n");

        String s = "Inferno is the first part of Italian writer Dante Alighieri's 14th-century epic poem Divine Comedy.";
        book.setBookDescription(s);
        assertTrue(s.equals(book.getBookDescription()));

        System.out.println("Finished testBookSetDescription\n");
    }

    @Test
    public void testBookRating() {
        System.out.print("Starting testBookRating\n");

        book.rateBook(5);  //Test initial rating
        assertEquals((long) 50, (long) book.getBookRating());  //Rating averaged and multiplied by 10
        assertEquals((long) 1, (long) book.getTotalRatings());
        book.rateBook(3);  //Test additional ratings
        assertEquals((long) 40, (long) book.getBookRating());  //Test rating change
        assertEquals((long) 2, (long) book.getTotalRatings());  //Test total ratings update

        System.out.print("Finished testBookRating\n");
    }

    @Test
    public void testBookChangePrice() {
        System.out.print("Starting testBookChangePrice\n");

        book.setBookPrice(7777);
        assertEquals((long) 7777, book.getBookPrice());

        System.out.print("Finished testBookChangePrice\n");
    }

    @Test
    public void testBookSales() {
        System.out.println("Starting testBookSales\n");

        book.addSale(1);
        assertEquals((long) 1, (long) book.getBookSales());


        System.out.println("Finished testBookSales\n");
    }
}
