package com.noboundary.controller;

import com.noboundary.model.UserBean;
import com.noboundary.model.UserBeanUtil;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Pengyu on 2016/6/22.
 */
@WebServlet(value = "/SignUpServlet", name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private UserBeanUtil userBeanUtil;
    @Resource(name = "jdbc/book_shop")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userBeanUtil = new UserBeanUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String command = request.getParameter("COMMAND");
            switch (command) {
                case "INDEX_SIGN_UP":
                    indexSignUp(request,response);
                    break;
                case "ANIMATION_SIGN_UP":
                    animationSignUp(request,response);
                    break;
                case "BEST_SELLER_SIGN_UP":
                    bestSellerSignUp(request,response);
                    break;
                case "FICTION_SIGN_UP":
                    fictionSignUp(request,response);
                    break;
                case "SCIENCE_SIGN_UP":
                    scienceSignUp(request,response);
                    break;
                case "BOOK_DETAIL_SIGN_UP":
                    bookDetailSignUp(request,response);
                    break;
                case "CART_SIGN_UP":
                    cartSignUp(request,response);
                    break;
            }
        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void indexSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet").forward(request, response);
    }

    private void animationSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_ANIMATION").forward(request, response);
    }

    private void bestSellerSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_BEST_SELLER").forward(request, response);
    }

    private void fictionSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_FICTION").forward(request, response);
    }

    private void scienceSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_SCIENCE").forward(request, response);
    }

    private void bookDetailSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LOAD").forward(request, response);
    }

    private void cartSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean newUser = userBeanUtil.addUser(email, password, userName, firstName, lastName, phoneNumber, zipCode, address);

        request.getSession().setAttribute("LOGIN_USER",newUser);
        request.getRequestDispatcher("ShoppingServlet").forward(request, response);
    }
}
