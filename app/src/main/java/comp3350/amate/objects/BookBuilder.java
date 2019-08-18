package comp3350.amate.objects;

public class BookBuilder {
    private String newTitle;
    private String newAuthor;
    private String newBookReleaseDate;
    private int newPrice;
    private String newFormat;
    private int newISBN;

    public BookBuilder setNewTitle(String newTitle) {
        this.newTitle = newTitle;
        return this;
    }

    public BookBuilder setNewAuthor(String newAuthor) {
        this.newAuthor = newAuthor;
        return this;
    }

    public BookBuilder setNewBookReleaseDate(String newBookReleaseDate) {
        this.newBookReleaseDate = newBookReleaseDate;
        return this;
    }

    public BookBuilder setNewPrice(int newPrice) {
        this.newPrice = newPrice;
        return this;
    }

    public BookBuilder setNewFormat(String newFormat) {
        this.newFormat = newFormat;
        return this;
    }

    public BookBuilder setNewISBN(int newISBN) {
        this.newISBN = newISBN;
        return this;
    }

    public Book createBook() {
        return new Book(newTitle, newAuthor, newBookReleaseDate, newPrice, newFormat, newISBN);
    }
}