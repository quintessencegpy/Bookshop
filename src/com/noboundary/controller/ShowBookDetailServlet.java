package com.noboundary.controller;

import com.noboundary.model.BookBean;
import com.noboundary.model.BookBeanUntil;
import com.noboundary.model.UserBean;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Pengyu on 2016/6/19.
 */
@WebServlet(value = "/ShowBookDetailServlet", name = "ShowBookDetailServlet")
public class ShowBookDetailServlet extends HttpServlet {
    private BookBeanUntil bookBeanUntil;
    @Resource(name = "jdbc/book_shop")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            bookBeanUntil = new BookBeanUntil(dataSource);
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
            String flag = request.getParameter("FLAG");
            if (flag == null) {
                flag = "BOOK";
            }
            if (flag.equals("BOOK")) {
                String bookAction = request.getParameter("BOOK_ACTION");

                if (bookAction == null) {
                    bookAction = "LIST_ALL";
                }
                switch (bookAction) {
                    case "LIST_ALL":
                        listBook(request, response);
                        break;
                    case "LIST_ANIMATION":
                        listAnimation(request, response);
                        break;
                    case "LIST_BEST_SELLER":
                        listBestseller(request, response);
                        break;
                    case "LIST_FICTION":
                        listFiction(request, response);
                        break;
                    case "LIST_SCIENCE":
                        listScience(request, response);
                        break;
                    case "LOAD":
                        loadBook(request, response);
                        break;
                }
            } else if (flag.equals("INDEX_PAGINATION")) {
                allBookPagination(request, response);
            } else if (flag.equals("ANIMATION_PAGINATION")) {
                animationPagination(request, response);
            }else if (flag.equals("BEST_SELLER_PAGINATION")) {
                bestSellerPagination(request, response);
            }else if (flag.equals("FICTION_PAGINATION")) {
                fictionPagination(request, response);
            }else if (flag.equals("SCIENCE_PAGINATION")) {
                sciencePagination(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    private void loadBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sBookId = request.getParameter("BOOK_ID");
        BookBean theBook = bookBeanUntil.getBookBean(sBookId);
        request.setAttribute("THE_BOOK", theBook);
        request.setAttribute("BOOK_ID",sBookId);
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {

            request.getRequestDispatcher("book-detail.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("book-detail-login.jsp").forward(request, response);
        }

    }

    private void listBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageCount = bookBeanUntil.getPageCount();
        List<BookBean> books = bookBeanUntil.getBookByPage(1);
        request.getServletContext().setAttribute("BOOK_LIST", books);
        request.getServletContext().setAttribute("PAGE_NOW", "1");
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index-login.jsp").forward(request, response);
        }
    }

    private void listAnimation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageCount = bookBeanUntil.getClassifiedPageCount("Animation");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(1, "Animation");
        request.getServletContext().setAttribute("ANIMATION_LIST", books);
        request.getServletContext().setAttribute("PAGE_NOW", "1");
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("animation.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("animation-login.jsp").forward(request, response);
        }
    }

    private void listBestseller(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageCount = bookBeanUntil.getClassifiedPageCount("Best Seller");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(1, "Best Seller");
        request.getServletContext().setAttribute("BEST_SELLER_LIST", books);
        request.getServletContext().setAttribute("PAGE_NOW", "1");
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("best-seller.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("best-seller-login.jsp").forward(request, response);
        }
    }

    private void listScience(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageCount = bookBeanUntil.getClassifiedPageCount("Science");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(1,"Science");
        request.getServletContext().setAttribute("SCIENCE_LIST", books);
        request.getServletContext().setAttribute("PAGE_NOW", "1");
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("science.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("science-login.jsp").forward(request, response);
        }

     }
    private void listFiction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pageCount = bookBeanUntil.getClassifiedPageCount("Fiction");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(1, "Fiction");
        request.getServletContext().setAttribute("FICTION_LIST", books);
        request.getServletContext().setAttribute("PAGE_NOW", "1");
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("fiction.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("fiction-login.jsp").forward(request, response);
        }
    }

    private void allBookPagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sPageNow = request.getParameter("PAGE_NOW");
        int pageNow=Integer.parseInt(sPageNow);
        int pageCount = bookBeanUntil.getPageCount();
        List<BookBean> books = bookBeanUntil.getBookByPage(pageNow);
        request.getServletContext().setAttribute("BOOK_LIST", books);
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        request.getServletContext().setAttribute("PAGE_NOW", pageNow + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index-login.jsp").forward(request, response);
        }
    }

    private void animationPagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sPageNow = request.getParameter("PAGE_NOW");
        int pageNow=Integer.parseInt(sPageNow);
        int pageCount = bookBeanUntil.getClassifiedPageCount("Animation");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(pageNow, "Animation");
        request.getServletContext().setAttribute("ANIMATION_LIST", books);
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        request.getServletContext().setAttribute("PAGE_NOW", pageNow + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("animation.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("animation-login.jsp").forward(request, response);
        }
    }
    private void bestSellerPagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sPageNow = request.getParameter("PAGE_NOW");
        int pageNow=Integer.parseInt(sPageNow);
        int pageCount = bookBeanUntil.getClassifiedPageCount("Best Seller");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(pageNow, "Best Seller");
        request.getServletContext().setAttribute("BEST_SELLER_LIST", books);
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        request.getServletContext().setAttribute("PAGE_NOW", pageNow + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("best-seller.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("best-seller-login.jsp").forward(request, response);
        }
    }
    private void fictionPagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sPageNow = request.getParameter("PAGE_NOW");
        int pageNow=Integer.parseInt(sPageNow);
        int pageCount = bookBeanUntil.getClassifiedPageCount("Fiction");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(pageNow, "Fiction");
        request.getServletContext().setAttribute("FICTION_LIST", books);
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        request.getServletContext().setAttribute("PAGE_NOW", pageNow + "");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("fiction.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("fiction-login.jsp").forward(request, response);
        }
    }
    private void sciencePagination(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sPageNow = request.getParameter("PAGE_NOW");
        int pageNow=Integer.parseInt(sPageNow);
        int pageCount = bookBeanUntil.getClassifiedPageCount("Science");
        List<BookBean> books = bookBeanUntil.getClassfiedBookByPage(pageNow, "Science");
        request.getServletContext().setAttribute("SCIENCE_LIST", books);
        request.getServletContext().setAttribute("PAGE_COUNT", pageCount + "");
        request.getServletContext().setAttribute("PAGE_NOW",pageNow+"");
        UserBean userBean = (UserBean)request.getSession().getAttribute("LOGIN_USER");
        if(userBean == null) {
            request.getRequestDispatcher("science.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("science-login.jsp").forward(request, response);
        }
    }
}
