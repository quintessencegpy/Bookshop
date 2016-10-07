package com.noboundary.model;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Pengyu on 2016/6/21.
 */
public class UserBeanUtil {
    private DataSource dataSource;

    public UserBeanUtil(DataSource dataSource) {
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

    public boolean checkUser(String email, String password) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        boolean isExist = false;
        try {
            myConn = dataSource.getConnection();


            String sql = "select * from users where email = ?";


            myStmt = myConn.prepareStatement(sql);


            myStmt.setString(1, email);


            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String dbPassword = myRs.getString("password");
                if (dbPassword.equals(password)) {
                    isExist = true;
                }
            }
        } finally {
            close(myConn, myRs, myStmt);
        }
        return isExist;
    }

    public UserBean getUserBeanByEmail(String email) throws Exception {
        UserBean theUser = new UserBean();
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();


            String sql = "select * from users where email = ?";


            myStmt = myConn.prepareStatement(sql);


            myStmt.setString(1, email);


            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                theUser.setEmail(myRs.getString("email"));
                theUser.setUserName(myRs.getString("user_name"));
                theUser.setUserId(myRs.getInt("user_id"));
                theUser.setFirstName(myRs.getString("first_name"));
                theUser.setLastName(myRs.getString("last_name"));
                theUser.setPassword(myRs.getString("password"));
                theUser.setPhoneNumber(myRs.getString("phone_number"));
                theUser.setAddress(myRs.getString("address"));
                theUser.setZipCode(myRs.getString("zipcode"));
                theUser.setGrade(myRs.getInt("grade"));

            }

        } finally {
            close(myConn, myRs, myStmt);
        }
        return theUser;
    }

    public UserBean addUser(String email, String password, String userName, String firstName, String lastName, String phoneNumber, String zipCode, String address) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        UserBean newUser = new UserBean(userName,firstName,lastName,password,email,phoneNumber,address,zipCode);
        try {
            myConn = dataSource.getConnection();

            String sql = "insert into users " + "(user_name, first_name, last_name, password, email, phone_number, address, zipcode, grade)"
                    + "values (?, ?, ?,?,?,?,?,?,?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, userName);
            myStmt.setString(2, firstName);
            myStmt.setString(3, lastName);
            myStmt.setString(4, password);
            myStmt.setString(5, email);
            myStmt.setString(6, phoneNumber);
            myStmt.setString(7, address);
            myStmt.setString(8, zipCode);
            myStmt.setInt(9, 1);

            myStmt.execute();

        } finally {
            close(myConn, null, myStmt);
        }
        return newUser;
    }

    public void updateUser(UserBean theUser) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "update users "
                    + "set user_name=?, first_name=?, last_name=?, password=?, email=?, phone_number=?, address=?, zipcode=?"
                    + "where user_id =?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theUser.getUserName());
            myStmt.setString(2, theUser.getFirstName());
            myStmt.setString(3, theUser.getLastName());
            myStmt.setString(4, theUser.getPassword());
            myStmt.setString(5, theUser.getEmail());
            myStmt.setString(6, theUser.getPhoneNumber());
            myStmt.setString(7, theUser.getAddress());
            myStmt.setString(8, theUser.getZipCode());
            myStmt.setInt(9,theUser.getUserId());


            myStmt.execute();

        } finally {
            close(myConn, null, myStmt);
        }
    }
}
