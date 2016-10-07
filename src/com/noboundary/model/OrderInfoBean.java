package com.noboundary.model;

import java.util.Date;

/**
 * Created by Pengyu on 2016/6/22.
 */
public class OrderInfoBean {
    private int userId ;
    private String userName ;
    private String trueName ;
    private String	password ;
    private String email ;
    private String phoneNumber ;
    private String address ;
    private String zipCode ;
    private int grade;
    private int orderId ;
    private Date orderDate;
    private String payMethod;
    private byte isPayed;
    private float totalPrice;


    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTrueName() {

        return trueName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public void setIsPayed(byte isPayed) {
        this.isPayed = isPayed;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {

        return userId;
    }

    public String getUserName() {
        return userName;
    }



    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }



    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getGrade() {
        return grade;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public byte getIsPayed() {
        return isPayed;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
