package comp3350.amate.objects;

public class Review {
    private final String bookName;
    private final int numStars;
    private final String writtenReview;


    public Review(String bookName, String writtenReview, int numStars) {
        this.bookName = bookName;
        this.writtenReview = writtenReview;
        this.numStars = numStars;
    }

    //Accessors
    public String getReBookName() {
        return bookName;
    }

    public String getWrittenReview() {
        return writtenReview;
    }

    public int getNumStars() {
        return numStars;
    }
}
