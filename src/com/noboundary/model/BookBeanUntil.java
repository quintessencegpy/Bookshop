package com.noboundary.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pengyu on 2016/6/19.
 */
public class BookBeanUntil {
    private DataSource dataSource;
    private int pageCount = 0;
    private int rowCount = 0;
    private int pageSize = 3;

    public BookBeanUntil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    public BookBean getBookBean (String theBookId)  throws Exception{
        BookBean theBook = new BookBean();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int bookId;

        try {
           bookId = Integer.parseInt(theBookId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to get selected student
            String sql = "select * from books where book_id=?";

            // create prepared statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theBookId);

            // execute statement
            myRs = myStmt.executeQuery();

            if(myRs.next()) {
                theBook.setBookId(myRs.getInt("book_id"));
                theBook.setBookName(myRs.getString("book_name"));
                theBook.setBookIntro(myRs.getString("book_intro"));
                theBook.setBookPrice(myRs.getFloat("book_price"));
                theBook.setBookNum(myRs.getInt("book_num"));
                theBook.setBookAuthor(myRs.getString("book_author"));
                theBook.setBookPhoto(myRs.getString("book_photo"));
                theBook.setBookType(myRs.getString("book_type"));
            }
        } finally {
            // clean up JDBC objects
            close(myConn, myRs, myStmt);
        }

        return theBook;
    }

    public List<BookBean> getBookByPage(int pageNow) throws Exception {
        List<BookBean> books = new ArrayList<>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from books where book_id limit "+(pageNow-1)*this.pageSize+","+this.pageSize+"";
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int bookId = myRs.getInt("book_id");
                String bookName = myRs.getString("book_name");
                String bookIntro = myRs.getString("book_intro");
                float bookPrice = myRs.getFloat("book_price");
                int bookNum = myRs.getInt("book_num");
                String bookAuthor = myRs.getString("book_author");
                String bookPhoto = myRs.getString("book_photo");
                String bookType = myRs.getString("book_type");
                BookBean tempBook = new BookBean(bookId,bookName,bookIntro,bookPrice,bookNum,bookAuthor,bookPhoto,bookType);

                books.add(tempBook);
            }

            return books;
        } finally {
            close(myConn, myRs, myStmt);
        }
    }

    public List<BookBean> getClassfiedBookByPage(int pageNow, String type) throws Exception {
        List<BookBean> books = new ArrayList<>();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from books where book_type = ? and book_id limit "+(pageNow-1)*3+","+this.pageSize+"";
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, type);

            // execute statement
            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                int bookId = myRs.getInt("book_id");
                String bookName = myRs.getString("book_name");
                String bookIntro = myRs.getString("book_intro");
                float bookPrice = myRs.getFloat("book_price");
                int bookNum = myRs.getInt("book_num");
                String bookAuthor = myRs.getString("book_author");
                String bookPhoto = myRs.getString("book_photo");
                String bookType = myRs.getString("book_type");
                BookBean tempBook = new BookBean(bookId,bookName,bookIntro,bookPrice,bookNum,bookAuthor,bookPhoto,bookType);

                books.add(tempBook);
            }

            return books;
        } finally {
            close(myConn, myRs, myStmt);
        }
    }

    public int getPageCount() {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "select count(*) from books";
            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);
            if (myRs.next()) {
                rowCount = myRs.getInt(1);
            }
            if (rowCount % pageSize == 0) {
                pageCount = rowCount / pageSize;
            } else {
                pageCount = rowCount / pageSize + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myConn, myRs, myStmt);
        }
        return pageCount;
    }

    public int getClassifiedPageCount(String type) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "select count(*) from books where book_type = ?";
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, type);

            // execute statement
            myRs = myStmt.executeQuery();
            if (myRs.next()) {
                rowCount = myRs.getInt(1);
            }
            if (rowCount % pageSize == 0) {
                pageCount = rowCount / pageSize;
            } else {
                pageCount = rowCount / pageSize + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(myConn, myRs, myStmt);
        }
        return pageCount;
    }




}
