---
title: Architecture
---

# See ArchitectureDiagram.jpg for diagram.
    
## Java Package comp3350.amate

### Domain

#### Application
    - Main.java
    - Services.java
    
#### objects
    - Account.java
    - Book.java
    - BookBuilder.java
    - Card.java
    - Order.java
    - Review.java
    - ShoppingCart.java
    
#### Logic layer
    
### business
    - AccessAccount.java
    - AccessBooks.java
    - AccessCard.java
    - AccessOrders.java
    - AccessReview.java
    - AccessShoppingCart.java
    - CardException.java
    - CardValidation.java
    - InvalidRegisterException.java
    - InvalidSalesNumber.java
    - InvalidSigninException.java
    
### Persistence Layer

#### persistence
    - AccountPersistence.java
    - BookPersistence.java
    - CardPersistence.java
    - OrderPersistence.java
    - ReviewPersistence.java

###### HSQL
    - AccountPersistenceHSQLDB.java
    - BookPersistenceHSQLDB.java
    - CardPersistenceHSQLDB.java
    - OrderPersistenceHSQLDB.java
    - PersistenceException.java
    - ReviewPersistenceHSQLDB.java
    
### Presentation Layer
    
#### presentation
    - AccountActivity.java
    - AccountInfoActivity.java
    - AdminListActivity.java
    - BookActivity.java
    - BookInfoActivity.java
    - BookListEntryAdapter.java
    - BookmarkActivity.java
    - BookmarkEntryAdapter.java
    - CaOneActivity.java
    - CaTwoActivity.java
    - CartActivity.java
    - Item.java
    - MainActivity.java
    - Messages.java
    - OrderInfoActivity.java
    - OrderListActivity.java
    - OrderListEntryAdapter.java
    - OrderSearchActivity.java
    - RegisterActivity
    - ReviewActivity.java
    
## XML

### layout
    - activity_accountinfo.xml
    - activity_admin_list.xml
    - activity_book.xml
    - activity_book_entry.xml
    - activity_book_list.xml
    - activity_bookmark_entry.xml
    - activity_bookmark_info.xml
    - activity_cart.xml
    - activity_check.xml
    - activity_item.xml
    - activity_main.xml
    - activity_newcard.xml
    - activity_order_info.xml
    - activity_order_list.xml
    - activity_order_list_entry.xml
    - activity_order_search.xml
    - activity_rating.xml
    - activity_register.xml
    - activity_signin.xml