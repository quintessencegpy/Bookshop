<%@ page import="java.util.ArrayList" %>
<%@ page import="com.noboundary.model.CartUntil" %>
<%@ page import="com.noboundary.model.BookBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Pengyu
  Date: 2016/6/22
  Time: 16:50
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
  <title>View Order</title>
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

<section class="section-title">
  <div class="container">
    <h1>Order Complete</h1>
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
          <li class="active">Order Complete</li>
        </ol>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="animated fadeIn">
        <div class="col-md-12">
          <h3>Order Detail</h3>
          <br>
          <table class="table table-striped">
            <th>Order Number</th>
            <th>Ship To</th>
            <th>Address</th>
            <th>Zip Code</th>
            <th>Phone Number</th>
            <th>Total</th>
            <th>User Name</th>
            <th>Email</th>
            <th>Action</th>

            </tr>

            <tr>
              <td>${THIS_ORDER.orderId}</td>
              <td>${THIS_ORDER.trueName}</td>
              <td>${THIS_ORDER.address}</td>
              <td>${THIS_ORDER.zipCode}</td>
              <td>${THIS_ORDER.phoneNumber}</td>
              <td>${THIS_ORDER.totalPrice}</td>
              <td>${THIS_ORDER.userName}</td>
              <td>${THIS_ORDER.email}</td>
              <td><a href="#" class="btn btn-default">View Detail</a></td>
            </tr>


          </table>
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
