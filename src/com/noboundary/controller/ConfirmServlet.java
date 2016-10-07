package com.noboundary.controller;

import com.noboundary.model.CartUntil;
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
import java.util.ArrayList;

/**
 * Created by Pengyu on 2016/6/22.
 */
@WebServlet(value = "/ConfirmServlet", name = "ConfirmServlet")
public class ConfirmServlet extends HttpServlet {
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
                case "GUEST_LOAD":
                    loadGuest(request, response);
                    break;
                case "GUEST_UPDATE":
                    guestUpdate(request, response);
                    break;
                case "GUEST_VIEW_ORDER":
                    guestViewOrder(request,response);
                    break;
                case "GUEST_VIEW_ORDER_UPDATE":
                    guestViewOrderUpdate(request, response);
                    break;
                case "USER_VIEW_ORDER":
                    userViewOrder(request,response);
                    break;

            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void loadGuest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserBean guest = (UserBean)request.getSession().getAttribute("TEMP_USER");
        if(guest == null) {
            guest = new UserBean("","","","","","","","");
            request.getSession().setAttribute("TEMP_USER",guest);
        }

        request.getRequestDispatcher("customer-info.jsp").forward(request, response);
    }

    private void guestUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean guest = (UserBean)request.getSession().getAttribute("TEMP_USER");
        guest.setUserName(userName);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setEmail(email);
        guest.setPassword(password);
        guest.setPhoneNumber(phoneNumber);
        guest.setZipCode(zipCode);
        guest.setAddress(address);

        request.getSession().setAttribute("TEMP_USER", guest);
        request.getRequestDispatcher("customer-info.jsp").forward(request, response);

    }

    private void guestViewOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CartUntil cartUntil = (CartUntil)request.getSession().getAttribute("CART_OBJ");

        ArrayList myCart = cartUntil.showCart();

        request.setAttribute("MY_CART", myCart);

        request.getRequestDispatcher("guest-view-order.jsp").forward(request, response);
    }

    private void guestViewOrderUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean guest = (UserBean)request.getSession().getAttribute("TEMP_USER");
        guest.setUserName(userName);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setEmail(email);
        guest.setPassword(password);
        guest.setPhoneNumber(phoneNumber);
        guest.setZipCode(zipCode);
        guest.setAddress(address);

        request.getSession().setAttribute("TEMP_USER", guest);
        request.getRequestDispatcher("guest-view-order.jsp").forward(request, response);
    }

    public void userViewOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CartUntil cartUntil = (CartUntil)request.getSession().getAttribute("CART_OBJ");

        ArrayList myCart = cartUntil.showCart();

        request.setAttribute("MY_CART", myCart);

        request.getRequestDispatcher("user-view-order.jsp").forward(request, response);
    }
}
