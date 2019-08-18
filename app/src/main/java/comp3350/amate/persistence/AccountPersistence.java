package comp3350.amate.persistence;

import java.util.List;

import comp3350.amate.objects.Account;

public interface AccountPersistence {
    List<Account> getAccountSequential();

    Account insertAccount(Account currentUser);

    void updateUserBookmarks(String userID, List<String> bookmarks);

    Account getAccount(String userID);

    void addOrderToUser(String userID, int orderID);
}
