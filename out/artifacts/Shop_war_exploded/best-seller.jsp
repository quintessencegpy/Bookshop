<%--
  Created by IntelliJ IDEA.
  User: Pengyu
  Date: 2016/6/19
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Best Seller</title>
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
                <li><a href="${checkCartLink}">Cart<span>Check Your Shopping Cart</span></a>
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
                    <input type="hidden" name="COMMAND" value="BEST_SELLER_LOGIN" />
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" name = "EMAIL" class="form-control" placeholder="Email" value="${WRONG_EMAIL}">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name= "PASSWORD" class="form-control" placeholder="Password" value="${WRONG_PASSWORD}">
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
                    <input type="hidden" name="COMMAND" value="BEST_SELLER_SIGN_UP"/>

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

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>

            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse center" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-title">
                <c:set var="allBookNum" value="1"/>
                <c:url var="allBookLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="PAGE_NOW" value="${allBookNum}"/>
                </c:url>
                <li><a href="${allBookLink}">All Books</a></li>
                <c:set var="bestSellerNum" value="1"/>
                <c:url var="bestSellerLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="BOOK_ACTION" value="LIST_BEST_SELLER"/>
                    <c:param name="PAGE_NOW" value="${bestSellerNum}"/>
                </c:url>
                <li class="active"><a href="${bestSellerLink}">Best Seller</a></li>
                <c:set var="fictionNum" value="1"/>
                <c:url var="fictionLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="BOOK_ACTION" value="LIST_FICTION"/>
                    <c:param name="PAGE_NOW" value="${fictionNum}"/>
                </c:url>
                <li><a href="${fictionLink}">Fiction</a></li>
                <c:set var="animationNum" value="1"/>
                <c:url var="animationLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="BOOK_ACTION" value="LIST_ANIMATION"/>
                    <c:param name="PAGE_NOW" value="${animationNum}"/>
                </c:url>
                <li><a href="${animationLink}">Animation</a></li>
                <c:set var="scienceNum" value="1"/>
                <c:url var="scienceLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="BOOK_ACTION" value="LIST_SCIENCE"/>
                    <c:param name="PAGE_NOW" value="${scienceNum}"/>
                </c:url>
                <li><a href="${scienceLink}">Science</a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<section>
    <div class="container">
        <div class="row animated fadeIn">
            <c:forEach var="tempBook" items="${BEST_SELLER_LIST}">
                <c:url var="tempLink" value="ShowBookDetailServlet">
                    <c:param name="FLAG" value="BOOK"/>
                    <c:param name="BOOK_ACTION" value="LOAD"/>
                    <c:param name="BOOK_ID" value="${tempBook.bookId}"/>
                </c:url>
                <div class="col-md-4 book">
                    <img class="img-responsive img-thumbnail img-circle" src="img/${tempBook.bookPhoto}">

                    <h3>${tempBook.bookName}</h3>

                    <p class="oblique">${tempBook.bookAuthor}</p>

                    <p>${tempBook.bookIntro} </p>

                    <div class="price">Buy it for <span>$${tempBook.bookPrice}</span></div>
                    <br>
                    <a href="${tempLink}" class="btn buy">Book Details</a>
                </div>
            </c:forEach>
        </div>


        <div class="row">
            <div class="col-md-12 link-center">
                <ul class="pagination">
                    <fmt:parseNumber var="PAGE_NOW_NUM" type="number" value="${PAGE_NOW}"/>
                    <fmt:parseNumber var="PAGE_COUNT_NUM" type="number" value="${PAGE_COUNT}"/>
                    <c:if test="${PAGE_NOW_NUM != 1}">
                        <c:url var="previousLink" value="ShowBookDetailServlet">
                            <c:param name="FLAG" value="BEST_SELLER_PAGINATION"/>
                            <c:param name="PAGE_NOW" value="${PAGE_NOW_NUM - 1}"/>
                        </c:url>
                        <li><a href="${previousLink}">Previous</a></li>
                    </c:if>
                    <c:set var="index" value="0"/>
                    <c:forEach begin="1" end="${PAGE_COUNT_NUM}">
                        <c:set var="index" value="${index+1}"/>
                        <c:url var="numberLink" value="ShowBookDetailServlet">
                            <c:param name="FLAG" value="BEST_SELLER_PAGINATION"/>
                            <c:param name="PAGE_NOW" value="${index}"/>
                        </c:url>
                        <c:choose>
                            <c:when test="${index == PAGE_NOW_NUM}">
                                <li class="active"><a href="${numberLink}">${index}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${numberLink}">${index}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${PAGE_NOW_NUM != PAGE_COUNT_NUM}">
                        <c:url var="nextLink" value="ShowBookDetailServlet">
                            <c:param name="FLAG" value="BEST_SELLER_PAGINATION"/>
                            <c:param name="PAGE_NOW" value="${PAGE_NOW_NUM + 1}"/>
                        </c:url>
                        <li><a href="${nextLink}">Next</a></li>
                    </c:if>
                </ul>
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

