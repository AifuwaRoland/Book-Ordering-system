package comp3350.amate.business;

import java.util.List;

import comp3350.amate.application.Services;
import comp3350.amate.objects.Account;
import comp3350.amate.persistence.AccountPersistence;

public class AccessAccount {
    private AccountPersistence accountPersistence;
    private List<Account> accounts;
    private static Account signedInAccount;

    public AccessAccount() {
        accountPersistence = Services.getAccountPersistence();
        accounts = null;
    }

    public AccessAccount(final AccountPersistence accountPersistence) {
        this();
        this.accountPersistence = accountPersistence;
    }

    public Account getSignedInAccount() {
        return signedInAccount;
    }

    public List<Account> getAccounts() {
        accounts = accountPersistence.getAccountSequential();
        return accounts;
    }

    public Account signIn(Account inputAccount) throws InvalidSigninException {
        if (inputAccount.getUserID().trim().length() == 0)
            throw new InvalidSigninException(("Empty user ID."));

        if (inputAccount.getUserPassword().trim().length() == 0)
            throw new InvalidSigninException("Empty user Password.");

        Account currAccount;
        int exceptionType = -1;
        this.getAccounts();
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getUserID().equals(inputAccount.getUserID())) {
                currAccount = this.accounts.get(i);
                if (currAccount != null && currAccount.getUserPassword().equals(inputAccount.getUserPassword())) {
                    signedInAccount = this.accounts.get(i);
                    if (exceptionType == 1) {
                        exceptionType = -1;
                    }
                    AccessShoppingCart cart = new AccessShoppingCart();
                    cart.clearCart();
                } else {
                    exceptionType = 0;
                }
            } else {
                exceptionType = 1;
            }
        }

        if (signedInAccount == null) {
            if (exceptionType == 0)
                throw new InvalidSigninException("The password is incorrect.");
            else if (exceptionType == 1)
                throw new InvalidSigninException(("cannot find this username."));
        }
        return signedInAccount;
    }

    public Account register(Account inputAccount) throws InvalidRegisterException {
        if (inputAccount.getUserID().trim().length() == 0) {
            throw new InvalidRegisterException(("Empty user ID."));
        }
        if (inputAccount.getUserPassword().trim().length() == 0) {
            throw new InvalidRegisterException("Empty user Password.");
        }

        int exceptionType = -1;
        Account signUpAccount = null;
        this.getAccounts();
        for (int i = 0; i < this.accounts.size(); i++) {
            if (this.accounts.get(i).getUserID().equals(inputAccount.getUserID())) {
                exceptionType = 0;
            }
        }

        if (exceptionType != 0) {
            signUpAccount = inputAccount;
            this.insertAccount(signUpAccount);
        } else if (exceptionType == 0) {
            throw new InvalidRegisterException("Duplicated user name.");
        }

        return signUpAccount;
    }

    public Account insertAccount(Account currAccount) {
        return accountPersistence.insertAccount(currAccount);
    }

    public void addBookmark(String title) {
        signedInAccount.addBookToBookmark(title);
    }

    public Account updateUserBookmarks(List<String> list) {
        accountPersistence.updateUserBookmarks(signedInAccount.getUserID(), list);
        signedInAccount = accountPersistence.getAccount(signedInAccount.getUserID());
        return signedInAccount;
    }

    public void addOrderToUser(String userID, int orderID) {
        accountPersistence.addOrderToUser(userID, orderID);
    }
}

