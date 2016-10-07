package com.noboundary.model;


import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Pengyu on 2016/6/20.
 */
public class CartUntil {

    private DataSource dataSource;
    private float balance = 0.0f;

    public CartUntil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    HashMap<String, String> cartHashMap = new HashMap<String, String>();

    private void close(Connection myConn, ResultSet myRs, Statement myStmt) {
        try {
            if (myRs != null) {
                myRs.close();
            }
            if (myConn != null) {
                myConn.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(String bookId, String bookNum) {
        cartHashMap.put(bookId, bookNum);

    }

    public void removeBook(String bookId) {
        cartHashMap.remove(bookId);
    }

    public void emptyCart() {
        cartHashMap.clear();
    }

    public void updateBookNum(String bookId, String newNum) {
        cartHashMap.put(bookId, newNum);
    }

    public float getBalance() {
        return balance;
    }

    public String getBookNumById(int bookId) {
        String sBookId = bookId + "";
        return (String)cartHashMap.get(sBookId);
    }

    public ArrayList showCart() throws Exception {
        ArrayList<BookBean> cartBooks = new ArrayList<BookBean>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            Iterator iterator = cartHashMap.keySet().iterator();

            String sub = "(";

            while (iterator.hasNext()) {
                String bookId = (String) iterator.next();
                if (iterator.hasNext()) {
                    sub += bookId + ",";
                } else {
                    sub += bookId + ")";
                }
            }
            myConn = dataSource.getConnection();

            if (sub != "(") {
                String sql = "select * from books where book_id in " + sub;


                myStmt = myConn.createStatement();

                myRs = myStmt.executeQuery(sql);

                this.balance = 0.0f;

                while (myRs.next()) {
                    int bookId = myRs.getInt("book_id");
                    String bookName = myRs.getString("book_name");
                    String bookIntro = myRs.getString("book_intro");
                    float bookPrice = myRs.getFloat("book_price");
                    int bookNum = myRs.getInt("book_num");
                    String bookAuthor = myRs.getString("book_author");
                    String bookPhoto = myRs.getString("book_photo");
                    String bookType = myRs.getString("book_type");
                    BookBean tempBook = new BookBean(bookId, bookName, bookIntro, bookPrice, bookNum, bookAuthor, bookPhoto, bookType);
                    this.balance = this.balance + Integer.parseInt(this.getBookNumById(bookId)) * bookPrice;
                    cartBooks.add(tempBook);
                }

            }
        } finally {
            close(myConn, myRs, myStmt);
        }
        return cartBooks;
    }
}
