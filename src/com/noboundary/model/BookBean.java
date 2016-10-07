package com.noboundary.model;

/**
 * Created by Pengyu on 2016/6/19.
 */
public class BookBean {
    private int bookId;
    private String bookName;
    private String bookIntro;
    private float bookPrice;
    private int bookNum;
    private String bookAuthor;
    private String bookPhoto;
    private String bookType;

    public BookBean(int bookId, String bookName, String bookIntro, float bookPrice, int bookNum, String bookAuthor, String bookPhoto, String bookType) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookIntro = bookIntro;
        this.bookPrice = bookPrice;
        this.bookNum = bookNum;
        this.bookAuthor = bookAuthor;
        this.bookPhoto = bookPhoto;
        this.bookType = bookType;
    }

    public BookBean() {

    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public int getBookNum() {
        return bookNum;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPhoto() {
        return bookPhoto;
    }

    public String getBookType() {
        return bookType;
    }
}
