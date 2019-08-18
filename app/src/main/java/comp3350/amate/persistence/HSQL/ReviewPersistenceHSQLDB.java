package comp3350.amate.persistence.HSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comp3350.amate.objects.Review;
import comp3350.amate.persistence.ReviewPersistence;

public class ReviewPersistenceHSQLDB implements ReviewPersistence {
    private final String dbPath;

    public ReviewPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Review fromResultSet(final ResultSet rs) throws SQLException {
        final String title = rs.getString("bookTitle");
        final int rating = rs.getInt("rating");
        final String review = rs.getString("review");
        return new Review(title, review, rating);
    }

    @Override
    public List<Review> getReviewList(String name) {
        List<Review> result = new ArrayList<>();

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM reviews WHERE bookTitle = ?");
            st.setString(1, name);
            final ResultSet rs = st.executeQuery();

            while (rs.next())
                result.add(fromResultSet(rs));

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }

    @Override
    public void addReview(Review review) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO reviews VALUES(?,?,?)");
            st.setString(1, review.getReBookName());
            st.setInt(2, review.getNumStars());
            st.setString(3, review.getWrittenReview());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
