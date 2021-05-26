<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- THIS IS THE MAIN PAGE-->
    <title>Auction Application</title>
</head>
<body>
<center>
    <h1>Items Management</h1>
    <h2>
        <a href="newUser">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="loginProcess">Login</a>
        &nbsp;&nbsp;&nbsp;
        <form action="listAuction" method="post">
            Select a Category:&nbsp;
        <select name="id_category">
            <option value="0">none</option>
            <c:forEach items="${listCategory}" var="category">
                <option value="${category.id_category}"
                        <c:if test="${category.id_category eq selectedCatId}">selected="selected"</c:if>
                >
                        ${category.category_name}
                </option>
            </c:forEach>
        </select>
            <input type="submit" value="Submit" />
        </form>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Items</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Start Price</th>
            <th>Sold Price</th>
            <th>ID User</th>
            <th>ID Category</th>
        </tr>
        <c:forEach var="item" items="${listItem}">
            <tr>
                <td><c:out value="${item.id_item}" /></td>
                <td><c:out value="${item.item_name}" /></td>
                <td><c:out value="${item.item_description}" /></td>
                <td><c:out value="${item.bid_start_date}" /></td>
                <td><c:out value="${item.bid_end_date}" /></td>
                <td><c:out value="${item.starting_price}" /></td>
                <td><c:out value="${item.selling_price}" /></td>
                <td><c:out value="${item.id_user}" /></td>
                <td><c:out value="${item.id_category}" /></td>
                <td>
                    <a href="editItem?id_item=<c:out value='${item.id_item}' />&id_user=<c:out value='${item.id_user}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="deleteItem?id_item=<c:out value='${item.id_item}' />&id_user=<c:out value='${item.id_user}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>