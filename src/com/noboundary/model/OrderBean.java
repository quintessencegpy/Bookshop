package com.noboundary.model;

import java.util.Date;

/**
 * Created by Pengyu on 2016/6/22.
 */
public class OrderBean {
    private int orderId;
    private int userId;
    private Date orderDate;
    private String payMethod;
    private byte isPaid;
    private float totalPrice;

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {

        return orderDate;
    }

    public String getPayMethod() {
        return payMethod;
    }



    public float getTotalPrice() {
        return totalPrice;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setIsPaid(byte isPaid) {
        this.isPaid = isPaid;
    }

    public byte getIsPaid() {

        return isPaid;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
