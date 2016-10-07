package com.noboundary.model;

/**
 * Created by Pengyu on 2016/6/22.
 */
public class OrderDetailBean {
    private int orderId;
    private int bookId;
    private int num;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderId() {

        return orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getNum() {
        return num;
    }
}
