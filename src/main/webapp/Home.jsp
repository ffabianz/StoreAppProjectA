<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin CPanel - Bookshop</title>
</head>
<body>
<div style="text-align: center">
    <h1>Welcome to Bookshop Website Admin Panel</h1>
    <b>${user.nickname} (${user.email})</b>
    <br><br>
    <a href="logout">Logout</a>
    <a href="listUser">List USer</a>
    <a href="newItem?id_user=<c:out value='${user.id_user}' />">New Item</a>

</div>
</body>
</html>