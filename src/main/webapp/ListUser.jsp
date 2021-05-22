<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Auction Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="newUser">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="loginProcess">Login</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID User</th>
            <th>Nickname</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Street</th>
            <th>Postal Code</th>
            <th>City</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>

                <td><c:out value="${user.id_user}" /></td>
                <td><c:out value="${user.nickname}" /></td>
                <td><c:out value="${user.last_name}" /></td>
                <td><c:out value="${user.first_name}" /></td>
                <td><c:out value="${user.email}" /></td>
                <td><c:out value="${user.phone_number}" /></td>
                <td><c:out value="${user.street}" /></td>
                <td><c:out value="${user.postal_code}" /></td>
                <td><c:out value="${user.city}" /></td>
                <td>
                    <a href="editUser?id_user=<c:out value='${user.id_user}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteUser?id_user=<c:out value='${user.id_user}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
