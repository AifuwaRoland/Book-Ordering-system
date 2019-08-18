package comp3350.amate.objects;

public class Book {
    private final String bookTitle;
    private final String bookAuthor;
    private final String bookReleaseDate;
    private String bookDescription;
    private int bookRating;
    private int totalRatings;
    private int bookSales;
    private int bookPrice;
    private final String format;
    private final int ISBN;

    public Book(final String newTitle, final String newAuthor, final String newBookReleaseDate,
                final int newPrice, final String newFormat, final int newISBN) {
        this.bookTitle = newTitle;
        this.bookAuthor = newAuthor;
        this.bookReleaseDate = newBookReleaseDate;
        this.bookDescription = "No description available yet";
        this.bookRating = 0;
        this.totalRatings = 0;
        this.bookSales = 0;
        this.bookPrice = newPrice;
        this.format = newFormat;
        this.ISBN = newISBN;
    }//end of Book constructor

    //Accessors
    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookReleaseDate() {
        return bookReleaseDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public int getBookRating() {
        int result;
        if (totalRatings == 0)
            result = 0;
        else
            result = (bookRating * 10) / totalRatings;
        return result;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public int getBookSales() {
        return bookSales;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public String getFormat() {
        return format;
    }

    public int getISBN() {
        return ISBN;
    }

    //Mutators
    public void setBookDescription(String newDescription) {
        bookDescription = newDescription;
    }

    public void rateBook(final int newRating) {
        bookRating += newRating;
        totalRatings++;
    }

    public void addSale(final int sales) {
        bookSales += sales;
    }

    public void setBookPrice(final int newPrice) {
        bookPrice = newPrice;
    }

    public void updateRatings(int newRating, int newTotalRatings) {  //Restore values taken from database
        bookRating = newRating;
        totalRatings = newTotalRatings;
    }


    public String toString() {
        return String.format("%s by %s. Published on %s.", bookTitle, bookAuthor, bookReleaseDate);
    }
}
