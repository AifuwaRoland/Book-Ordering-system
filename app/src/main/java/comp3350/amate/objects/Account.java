package comp3350.amate.objects;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private String userID;
    private String userName;
    private String userAddr;
    private String userNum;
    private String userEmail;
    private String userPassword;
    private List<String> bookmarked;
    private List<Integer> orderList;
    private int userType;

    public static class AccountBuilder {
        private String userID;
        private String userName;
        private String userAddr;
        private String userNum;
        private String userEmail;
        private String userPassword;
        private List<String> bookmarked;
        private List<Integer> orderList;
        private int userType;

        public AccountBuilder(String newUserID) {
            this.userID = newUserID;
        }

        public AccountBuilder buildUserName(String newUserName) {
            this.userName = newUserName;
            return this;
        }

        public AccountBuilder buildUserAddr(String newUserAddr) {
            this.userAddr = newUserAddr;
            return this;
        }

        public AccountBuilder buildUserNum(String newUserNum) {
            this.userNum = newUserNum;
            return this;
        }

        public AccountBuilder buildUserEmail(String newUserEmail) {
            this.userEmail = newUserEmail;
            return this;
        }

        public AccountBuilder buildUserPassword(String newUserPassword) {
            this.userPassword = newUserPassword;
            return this;
        }

        public AccountBuilder buildUserBookmarks(List<String> bookmarks) {
            this.bookmarked = bookmarks;
            return this;
        }

        public AccountBuilder buildUserOrders(List<Integer> orders) {
            this.orderList = orders;
            return this;
        }

        public AccountBuilder buildUserAccountType(int type) {
            this.userType = type;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.userID = userID;
            account.userName = userName;
            account.userAddr = userAddr;
            account.userNum = userNum;
            account.userEmail = userEmail;
            account.userPassword = userPassword;
            if (this.bookmarked != null)
                account.bookmarked = bookmarked;
            else
                account.bookmarked = new ArrayList<>();
            if (this.orderList != null)
                account.orderList = orderList;
            else
                account.orderList = new ArrayList<>();
            account.userType = userType;
            return account;
        }

    }

    //Accessors
    public String getUserID() {
        return (userID);
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public String getUserName() {
        return (userName);
    }

    public String getUserAddr() {
        return (userAddr);
    }

    public String getUserNum() {
        return (userNum);
    }

    public String getUserEmail() {
        return (userEmail);
    }

    public int getType() {
        return this.userType;
    }

    public List<Integer> getOrderList() {
        return orderList;
    }

    public List<String> getBookmarkList() {
        return bookmarked;
    }

    public void addOrder(int orderID) {
        orderList.add(orderID);
    }

    public void addBookToBookmark(String bookTitle) {
        bookmarked.add(bookTitle);
    }

    //Mutator
    public void removeBookFromBookmark(String bookTitle) {
        int index = bookmarked.indexOf(bookTitle);
        bookmarked.remove(index);
    }
}
