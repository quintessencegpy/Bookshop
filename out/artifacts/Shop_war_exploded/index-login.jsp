<%--
  Created by IntelliJ IDEA.
  User: Pengyu
  Date: 2016/6/21
  Time: 16:51
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
  <title>No Boundary - Find Your New Book</title>
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
        <li><a href="${checkCartLink}">Cart<span>Check Your Shopping Cart</span></a>
        </li>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</nav>


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
        <li class="active"><a href="${allBookLink}">All Books</a></li>
        <c:set var="bestSellerNum" value="1"/>
        <c:url var="bestSellerLink" value="ShowBookDetailServlet">
          <c:param name="FLAG" value="BOOK"/>
          <c:param name="BOOK_ACTION" value="LIST_BEST_SELLER"/>
          <c:param name="PAGE_NOW" value="${bestSellerNum}"/>
        </c:url>
        <li><a href="${bestSellerLink}">Best Seller</a></li>
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
      <c:forEach var="tempBook" items="${BOOK_LIST}">
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
              <c:param name="FLAG" value="INDEX_PAGINATION"/>
              <c:param name="PAGE_NOW" value="${PAGE_NOW_NUM - 1}"/>
            </c:url>
            <li><a href="${previousLink}">Previous</a></li>
          </c:if>
          <c:set var="index" value="0"/>
          <c:forEach begin="1" end="${PAGE_COUNT_NUM}">
            <c:set var="index" value="${index+1}"/>
            <c:url var="numberLink" value="ShowBookDetailServlet">
              <c:param name="FLAG" value="INDEX_PAGINATION"/>
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
              <c:param name="FLAG" value="INDEX_PAGINATION"/>
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