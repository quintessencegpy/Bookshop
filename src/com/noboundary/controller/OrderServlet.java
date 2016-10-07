package com.noboundary.controller;

import com.noboundary.model.CartUntil;
import com.noboundary.model.OrderBeanUtil;
import com.noboundary.model.OrderInfoBean;
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

/**
 * Created by Pengyu on 2016/6/22.
 */
@WebServlet(value = "/OrderServlet", name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    private OrderBeanUtil orderBeanUtil;
    @Resource(name = "jdbc/book_shop")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            orderBeanUtil = new OrderBeanUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String order = request.getParameter("ORDER");

            switch (order) {
                case "USER_ORDER":
                    userOrder(request,response);
                    break;
            }


        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void userOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CartUntil myCart = (CartUntil)request.getSession().getAttribute("CART_OBJ");
        UserBean theUser = (UserBean)request.getSession().getAttribute("LOGIN_USER");

        int userId = theUser.getUserId();

        OrderInfoBean thisOrder = orderBeanUtil.addOrder(myCart,userId);

        request.setAttribute("THIS_ORDER",thisOrder);
        request.getRequestDispatcher("user-place-order.jsp").forward(request,response);

    }
}
