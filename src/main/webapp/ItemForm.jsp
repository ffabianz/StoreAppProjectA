<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Item Store Application</title>
</head>
<body>
<center>
    <h1>Item Management</h1>
    <h2>
        <a href="/new">Add New Item</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Items</a>
        &nbsp;&nbsp;&nbsp;
        <a href="logout">Logout</a>

    </h2>
</center>
<div align="center">
    <c:if test="${item != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${item == null}">
        <form action="insertItem?id_user=<c:out value='${user.id_user}' />" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${item != null}">
                            Edit Item
                        </c:if>
                        <c:if test="${item == null}">
                            Add New Item
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${item != null}">
                    <input type="hidden" name="id" value="<c:out value='${item.id}' />" />
                </c:if>
                <tr>
                    <th>Item Name: </th>
                    <td>
                        <input type="text" name="item_name" size="45"
                               value="<c:out value='${item.item_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Item Description: </th>
                    <td>
                        <input type="text" name="item_description" size="45"
                               value="<c:out value='${item.item_description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Initial Date: </th>
                    <td>
                        <input type="text" name="bid_start_date" size="45"
                               value="<c:out value='${item.bid_start_date}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Final Date: </th>
                    <td>
                        <input type="text" name="bid_end_date" size="45"
                               value="<c:out value='${item.bid_end_date}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Initial Price: </th>
                    <td>
                        <input type="text" name="starting_price" size="5"
                               value="<c:out value='${item.starting_price}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Category: </th>
                    <td>
                        <input type="text" name="id_category" size="45"
                               value="<c:out value='${item.id_category}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>