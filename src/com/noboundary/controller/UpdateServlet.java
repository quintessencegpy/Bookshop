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
@WebServlet(value = "/UpdateServlet", name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
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
                case "LOAD":
                    loadUser(request,response);
                    break;
                case "LOG_OUT":
                    logOut(request,response);
                    break;
                case "UPDATE":
                    update(request,response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("my-account.jsp").forward(request,response);
    }
    private void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        response.sendRedirect("ShowBookDetailServlet");
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("ID"));
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String userName = request.getParameter("USER_NAME");
        String firstName = request.getParameter("FIRST_NAME");
        String lastName = request.getParameter("LAST_NAME");
        String phoneNumber = request.getParameter("PHNOE_NUMBER");
        String zipCode = request.getParameter("ZIP_CODE");
        String address = request.getParameter("ADDRESS");

        UserBean updatedUser = new UserBean(id,userName,firstName,lastName,password,email,phoneNumber,address,zipCode);

        userBeanUtil.updateUser(updatedUser);

        request.getSession().setAttribute("LOGIN_USER",updatedUser);
        request.getRequestDispatcher("my-account.jsp").forward(request,response);
    }
}
