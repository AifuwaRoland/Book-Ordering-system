package comp3350.amate.persistence.HSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3350.amate.objects.Card;
import comp3350.amate.persistence.CardPersistence;

public class CardPersistenceHSQLDB implements CardPersistence {
    private final String dbPath;

    public CardPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Card fromResultSet(final ResultSet rs) throws SQLException {
        final long cardNumber = rs.getLong("cardNum");
        final String cardHolder = rs.getString("cardName");
        final String expiryDate = rs.getString("expiryDate");
        final int cvv = rs.getInt("cvv");
        final String userID = rs.getString("cardUserID");

        return new Card(cardNumber, cardHolder, expiryDate, cvv, userID);
    }

    @Override
    public Card addCreditCard(Card creditCard) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO creditCard VALUES(?, ?, ?, ?, ?)");

            st.setLong(1, creditCard.getCardNumber());
            st.setString(2, creditCard.getCardHolder());
            st.setString(3, creditCard.getExpiryDate());
            st.setInt(4, creditCard.getCVV());
            st.setString(5, creditCard.getCardUserID());

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return creditCard;
    }

    public Card getCard(String userID) {
        Card result = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM creditCard WHERE cardUserID = ?");
            st.setString(1, userID);
            final ResultSet rs = st.executeQuery();

            if (rs.next())
                result = fromResultSet(rs);

            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return result;
    }

    @Override
    public Card updateCreditCard(Card creditCard) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE creditCard SET cardName = ?, expiryDate = ?, CVV = ?, cardNum = ? WHERE cardUserID = ?");
            st.setString(1, creditCard.getCardHolder());
            st.setString(2, creditCard.getExpiryDate());
            st.setInt(3, creditCard.getCVV());
            st.setLong(4, creditCard.getCardNumber());
            st.setString(5, creditCard.getCardUserID());

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return creditCard;
    }
}

