package comp3350.amate.persistence.HSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.amate.objects.Book;
import comp3350.amate.objects.BookBuilder;
import comp3350.amate.persistence.BookPersistence;

public class BookPersistenceHSQLDB implements BookPersistence {
    private final String dbPath;

    public BookPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException {
        //Rebuild book
        final String bookTitle = rs.getString("bookTitle");
        final String bookAuthor = rs.getString("bookAuthor");
        final String bookReleaseDate = rs.getString("bookReleaseDate");
        final int bookPrice = rs.getInt("bookPrice");
        final String format = rs.getString("format");
        final int isbn = rs.getInt("isbn");

        //Restore sales and ratings
        Book result = new BookBuilder()
                .setNewTitle(bookTitle)
                .setNewAuthor(bookAuthor)
                .setNewBookReleaseDate(bookReleaseDate)
                .setNewPrice(bookPrice)
                .setNewFormat(format)
                .setNewISBN(isbn)
                .createBook();
        result.addSale(rs.getInt("sales"));
        result.updateRatings(rs.getInt("bookRating"),
                rs.getInt("totalRating"));  //new ratings, total ratings

        return result;
    }

    @Override
    public List<Book> getBookSequential() {
        final List<Book> books = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM booksDB");
            while (rs.next()) {
                final Book book = fromResultSet(rs);
                books.add(book);
            }
            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return books;
    }

    @Override
    public Book getBook(String title) {
        Book result = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM booksDB WHERE bookTitle = ?");
            st.setString(1, title);
            final ResultSet rs = st.executeQuery();

            while (rs.next())
                result = fromResultSet(rs);

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }

    @Override
    public Book getBestSeller() {
        Book book = null;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM booksDB ORDER BY sales DESC LIMIT 1");
            final ResultSet rs = st.executeQuery();

            while (rs.next())
                book = fromResultSet(rs);

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return book;
    }

    @Override
    public Book updateSales(Book book, int sales) throws PersistenceException {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE booksDB SET sales = ? WHERE bookTitle = ?");
            st.setInt(1, sales);
            st.setString(2, book.getBookTitle());

            st.executeUpdate();
            return book;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

    @Override
    public List<Book> searchTitles(String s) {
        List<Book> result = new ArrayList<>();
        if (s == "") {
            result = getBookSequential();
        } else {
            try (final Connection c = connection()) {
                final Statement st = c.createStatement();
                final ResultSet rs = st.executeQuery("SELECT * FROM booksDB");
                String title;
                while (rs.next()) {
                    title = rs.getString("bookTitle");
                    if ((title.toLowerCase()).contains(s.toLowerCase()))
                        result.add(fromResultSet(rs));
                }

                rs.close();
                st.close();
            } catch (final SQLException e) {
                throw new PersistenceException(e);
            }
        }
        return result;
    }

    @Override
    public List<Book> searchAuthor(String s) {
        List<Book> result = new ArrayList<>();
        if (s == "") {
            result = getBookSequential();
        } else {
            try (final Connection c = connection()) {
                final Statement st = c.createStatement();
                final ResultSet rs = st.executeQuery("SELECT * FROM booksDB");
                String author;
                while (rs.next()) {
                    author = rs.getString("bookAuthor");
                    if ((author.toLowerCase()).contains(s.toLowerCase()))
                        result.add(fromResultSet(rs));
                }

                rs.close();
                st.close();
            } catch (final SQLException e) {
                throw new PersistenceException(e);
            }
        }
        return result;
    }

    @Override
    public void rateBook(String title, int rating) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE booksDB SET bookRating = bookRating + ?, totalRating = totalRating + 1 WHERE bookTitle = ?");
            st.setInt(1, rating);
            st.setString(2, title);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
