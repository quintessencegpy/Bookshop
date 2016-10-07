package com.noboundary.controller;

import com.noboundary.model.CartUntil;
import com.noboundary.model.UserBean;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Pengyu on 2016/6/20.
 */
@WebServlet(value = "/ShoppingServlet", name = "ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
    private CartUntil cartUntil;
    @Resource(name = "jdbc/book_shop")
    private DataSource dataSource;

//    @Override
//    public void init() throws ServletException {
//        super.init();
//        try {
//            cartUntil = new CartUntil(dataSource);
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String cartAction = request.getParameter("CART_ACTION");
            if(cartAction == null) {
                cartAction = "SHOW_CART";
            }

            switch (cartAction) {
                case "SHOW_CART":
                    showCart(request,response);
                case "ADD_TO_CART" :
                    addToCart(request,response);
                    break;
                case "DELETE":
                    deleteFromCart(request,response);
                    break;
                case "CLEAR" :
                    clearCart(request,response);
                    break;
                case "UPDATE":
                    update(request,response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCart (HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(cartUntil == null) {
            cartUntil = new CartUntil(dataSource);
            request.getSession().setAttribute("CART_OBJ",cartUntil);
        }
        cartUntil = (CartUntil)request.getSession().getAttribute("CART_OBJ");
        ArrayList cartBooks = cartUntil.showCart();
        request.setAttribute("MY_CART", cartBooks);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("cart-login.jsp").forward(request, response);
        }
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookId = request.getParameter("BOOK_ID");
        if(cartUntil == null) {
            cartUntil = new CartUntil(dataSource);
            request.getSession().setAttribute("CART_OBJ",cartUntil);
        }
        cartUntil = (CartUntil)request.getSession().getAttribute("CART_OBJ");
        cartUntil.addBook(bookId,"1");
        ArrayList cartBooks = cartUntil.showCart();
        request.setAttribute("MY_CART", cartBooks);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("cart-login.jsp").forward(request, response);
        }
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookId = request.getParameter("CART_BOOK_ID");
        cartUntil = (CartUntil)request.getSession().getAttribute("CART_OBJ");
        cartUntil.removeBook(bookId);
        ArrayList cartBooks = cartUntil.showCart();
        request.setAttribute("MY_CART", cartBooks);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("cart-login.jsp").forward(request, response);
        }

    }

    private void clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        cartUntil = (CartUntil) request.getSession().getAttribute("CART_OBJ");
        cartUntil.emptyCart();
        ArrayList cartBooks = cartUntil.showCart();
        request.setAttribute("MY_CART", cartBooks);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("cart-login.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bookIds[] = request.getParameterValues("CART_BOOK_ID");
        String newBookQuantity[] = request.getParameterValues("NEW_BOOK_QUANTITY");
        cartUntil = (CartUntil) request.getSession().getAttribute("CART_OBJ");
        for(int i = 0; i < bookIds.length; i++) {
            cartUntil.updateBookNum(bookIds[i], newBookQuantity[i]);
        }
        ArrayList cartBooks = cartUntil.showCart();
        request.setAttribute("MY_CART", cartBooks);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("cart-login.jsp").forward(request, response);
        }


    }
}
