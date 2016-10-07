package com.noboundary.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pengyu on 2016/6/22.
 */
public class OrderBeanUtil {
    private DataSource dataSource;

    public OrderBeanUtil(DataSource dataSource) {
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

    public OrderInfoBean addOrder(CartUntil mycart, int userId) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        Statement statement = null;
        ResultSet myRs = null;
        OrderInfoBean thisOrder = new OrderInfoBean();
        int orderId = 0;
        try {
            myConn = dataSource.getConnection();

            String sql = "insert into orders " + "(user_id, is_paid, total_price)"
                    + "values (?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, userId);
            myStmt.setByte(2, (byte) 0);
            myStmt.setFloat(3, mycart.getBalance());

            int temp = myStmt.executeUpdate();

            if (temp == 1) {
                myStmt = myConn.prepareStatement("select max(order_id) from orders");
                myRs = myStmt.executeQuery();
                if (myRs.next()) {
                    orderId = myRs.getInt(1);
                }

                ArrayList cartBook = mycart.showCart();
                for (int i = 0; i < cartBook.size(); i++) {
                    BookBean tempBook = (BookBean) cartBook.get(i);
                    statement = myConn.createStatement();
                    statement.addBatch("insert into order_detail values ('" + orderId + "','" + tempBook.getBookId() + "','" + mycart.getBookNumById(tempBook.getBookId()) + "')");
                }
                statement.executeBatch();

                String orderSql = "select order_id, order_date, pay_method, is_paid, total_price, first_name, last_name, user_name, email, phone_number, address, zipcode from users, orders where order_id = ? and users.user_id = (select orders.user_id from orders where order_id = ?)";

                myStmt = myConn.prepareStatement(orderSql);

                myStmt.setInt(1, orderId);
                myStmt.setInt(2, orderId);

                myRs = myStmt.executeQuery();

                if(myRs.next()){
                    thisOrder.setOrderId(myRs.getInt(1));
                    thisOrder.setOrderDate(myRs.getDate(2));
                    thisOrder.setPayMethod(myRs.getString(3));
                    thisOrder.setIsPayed(myRs.getByte(4));
                    thisOrder.setTotalPrice(myRs.getFloat(5));
                    String firstName = myRs.getString(6);
                    String lastName = myRs.getString(7);
                    String trueName = firstName + " " +lastName;
                    thisOrder.setTrueName(trueName);
                    thisOrder.setUserName(myRs.getString(8));
                    thisOrder.setEmail(myRs.getString(9));
                    thisOrder.setPhoneNumber(myRs.getString(10));
                    thisOrder.setAddress(myRs.getString(11));
                    thisOrder.setZipCode(myRs.getString(12));
                }
            }
        } finally {
            close(myConn, null, myStmt);
        }

        return thisOrder;
    }


}
