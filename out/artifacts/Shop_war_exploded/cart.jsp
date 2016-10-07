<%@ page import="com.noboundary.model.CartUntil" %>
<%@ page import="com.noboundary.model.BookBean" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Pengyu
  Date: 2016/6/19
  Time: 1:51
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
                <li><a href="#" data-toggle="modal" data-target="#loginModal">Login
                    <span>Login to Your Account</span></a></li>
                <li><a href="#" data-toggle="modal" data-target="#signUpModal">Sign Up<span>Sign Up A New Account</span></a>
                </li>
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

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="loginModalLabel">Login</h4>
            </div>
            <div class="modal-body">
                <form action="LoginServlet" method="post">
                    <input type="hidden" name="COMMAND" value="CART_LOGIN"/>

                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" name="EMAIL" class="form-control" placeholder="Email"
                               value="${WRONG_EMAIL}">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="PASSWORD" class="form-control" placeholder="Password"
                               value="${WRONG_PASSWORD}">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Login"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="signUpModalLabel">Sign Up</h4>
            </div>
            <div class="modal-body">
                <form action="SignUpServlet" method="post">
                    <input type="hidden" name="COMMAND" value="CART_SIGN_UP"/>

                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" placeholder="Email" name="EMAIL">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Password" name="PASSWORD">
                    </div>
                    <div class="form-group">
                        <label>User Name</label>
                        <input type="text" class="form-control" placeholder="User Name" name="USER_NAME">
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" placeholder="First Name" name="FIRST_NAME">
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" placeholder="Last Name" name="LAST_NAME">
                    </div>
                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" class="form-control" placeholder="Phone Number" name="PHNOE_NUMBER">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" class="form-control" placeholder="Address" name="ADDRESS">
                    </div>
                    <div class="form-group">
                        <label>Zip Code</label>
                        <input type="text" class="form-control" placeholder="Zip Code" name="ZIP_CODE">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Sign Up"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

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
                                <input type="hidden" name="CART_BOOK_ID" value="<%= theBook%>"/>
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

                <div class="col-md-6 button-right">
                    <c:url var="guestCheckOutLink" value="ConfirmServlet">
                        <c:param name="COMMAND" value="GUEST_LOAD"/>
                    </c:url>
                    <a href="${guestCheckOutLink}" class="btn btn-success">Check out as guest</a> or <a href="#"
                                                                                     class="btn btn-info"
                                                                                     data-toggle="modal"
                                                                                     data-target="#loginModal">Login</a>
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
