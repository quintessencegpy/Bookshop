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
 * Created by Pengyu on 2016/6/21.
 */
@WebServlet(value = "/LoginServlet", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
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
                case "INDEX_LOGIN" :
                    indexLogin(request,response);
                    break;
                case "ANIMATION_LOGIN" :
                    animationLogin(request,response);
                    break;
                case "BEST_SELLER_LOGIN":
                    bestSellerLogin(request,response);
                    break;
                case "FICTION_LOGIN":
                    fictionLogin(request,response);
                    break;
                case "SCIENCE_LOGIN":
                    scienceLogin(request,response);
                    break;
                case "BOOK_DETAIL_LOGIN":
                    bookDetailLogin(request,response);
                    break;
                case "CART_LOGIN" :
                    cartLogin(request,response);
                    break;
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void indexLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if(userBeanUtil.checkUser(email,password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER",theUser);
            request.getRequestDispatcher("ShowBookDetailServlet").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL",email);
            request.setAttribute("WRONG_PASSWORD",password);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    private void animationLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_ANIMATION").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("animation.jsp").forward(request, response);
        }
    }

    private void bestSellerLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_BEST_SELLER").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("best-seller.jsp").forward(request, response);
        }
    }

    private void fictionLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_FICTION").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("fiction.jsp").forward(request, response);
        }
    }

    private void scienceLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LIST_SCIENCE").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("science.jsp").forward(request, response);
        }
    }

    private void bookDetailLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShowBookDetailServlet?BOOK_ACTION=LOAD").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("book-detail.jsp").forward(request, response);
        }
    }

    private void cartLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (userBeanUtil.checkUser(email, password)) {
            UserBean theUser = userBeanUtil.getUserBeanByEmail(email);
            request.getSession().setAttribute("LOGIN_USER", theUser);
            request.getRequestDispatcher("ShoppingServlet").forward(request, response);
        } else {
            request.setAttribute("WRONG_EMAIL", email);
            request.setAttribute("WRONG_PASSWORD", password);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
