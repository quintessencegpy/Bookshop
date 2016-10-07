<%@ page import="com.noboundary.model.CartUntil" %>
<%@ page import="com.noboundary.model.BookBean" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Pengyu
  Date: 2016/6/21
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    CartUntil cartUntil = (CartUntil) request.getSession().getAttribute("CART_OBJ");
    ArrayList cartBooks = (ArrayList) request.getAttribute("MY_CART");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shopping Cart</title>
    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:set var="allBookNum" value="1"/>
            <c:url var="allBookLink" value="ShowBookDetailServlet">
                <c:param name="FLAG" value="BOOK"/>
                <c:param name="PAGE_NOW" value="${allBookNum}"/>
            </c:url>
            <a class="navbar-brand logo-nav" href="${allBookLink}"><img src="img/logo.png"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav  navbar-main navbar-right">
                <c:url var="updateLoadLink" value="UpdateServlet">
                    <c:param name="COMMAND" value="LOAD"/>
                </c:url>
                <li><a href="${updateLoadLink}">${LOGIN_USER.userName}
                    <span>Welcome</span></a></li>
                <c:url var="checkCartLink" value="ShoppingServlet">
                    <c:param name="CART_ACTION" value="SHOW_CART"/>
                </c:url>
                <li class="active"><a href="${checkCartLink}">Cart<span>Check Your Shopping Cart</span></a>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>


<section class="section-title">
    <div class="container">
        <h1>Shopping Cart</h1>
    </div>
</section>
<section class="book-detail">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <ol class="breadcrumb">
                    <c:set var="allBookNum" value="1"/>
                    <c:url var="allBookLink" value="ShowBookDetailServlet">
                        <c:param name="FLAG" value="BOOK"/>
                        <c:param name="PAGE_NOW" value="${allBookNum}"/>
                    </c:url>
                    <li><a href="${allBookLink}">Home</a></li>
                    <li class="active">Shopping cart</li>
                </ol>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="animated fadeIn">
                <form action="ShoppingServlet">
                    <div class="col-md-12">
                        <input type="hidden" name="CART_ACTION" value="UPDATE"/>
                        <table class="table table-striped">
                            <th>Item</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Action</th>

                            </tr>
                            <%
                                for (int i = 0; i < cartBooks.size(); i++) {
                                    BookBean theBook = (BookBean) cartBooks.get(i);
                            %>
                            <tr>
                                <td><%= theBook.getBookName()%>
                                </td>
                                <td><%= theBook.getBookPrice()%>
                                </td>
                                <input type="hidden" name="CART_BOOK_ID" value="<%= theBook.getBookId()%>"/>
                                <td><input type="text" class="form-control quantity" name="NEW_BOOK_QUANTITY"
                                           value="<%= cartUntil.getBookNumById(theBook.getBookId())%>"/></td>
                                <td>
                                    <a href="ShoppingServlet?CART_ACTION=DELETE&CART_BOOK_ID=<%=theBook.getBookId()%>"
                                       class="btn btn-default">Delete</a>
                                    <a href="ShowBookDetailServlet?FLAG=BOOK&BOOK_ACTION=LOAD&BOOK_ID=<%=theBook.getBookId()%>"
                                       class="btn btn-default">View</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table>

                    </div>
                    <div class="col-md-12 book">
                        <div class="price">Your total
                            is<span>$<%= (float) (Math.round(cartUntil.getBalance() * 100)) / 100%></span></div>
                        <br>
                        <button type="submit" class="btn btn-primary">Change Quantity</button>
                        <c:url var="clearLink" value="ShoppingServlet">
                            <c:param name="CART_ACTION" value="CLEAR"/>
                        </c:url>
                        <a href="${clearLink}" class="btn btn-primary">Empty Cart</a>
                    </div>
                </form>
                <div class="col-md-6 button-left">
                    <a href="${allBookLink}" class="btn btn-info">Continue Shopping</a>
                </div>
                <c:url var="viewOrderLink" value="ConfirmServlet">
                    <c:param name="COMMAND" value="USER_VIEW_ORDER"/>
                </c:url>
                <div class="col-md-6 button-right">
                    <a href="${viewOrderLink}" class="btn btn-success">View Order </a>
                </div>
            </div>
        </div>
    </div>
</section>

<footer>
    <p>No Boundary Copyright &copy; 2015 - <a href="#">Terms</a> &middot; <a href="#">Privacy</a>
</footer>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/wow.js"></script>
<script>
    new WOW().init();
</script>
</body>
</html>