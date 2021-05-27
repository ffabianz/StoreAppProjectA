<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>items Store Application</title>
</head>
<body>
<center>
    <h1>Item Management</h1>
    <h2>
        <a href="/new">Add New item</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All items</a>
        &nbsp;&nbsp;&nbsp;
        <a href="logout">Logout</a>

    </h2>
</center>
<div align="center">
    <c:if test="${item != null}">
    <form action="updateItem" method="post">
        </c:if>
        <c:if test="${item == null}">
        <form action="updateItem" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${item != null}">
                            Edit item
                        </c:if>
                        <c:if test="${item == null}">
                            Add New item
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${item != null}">
                    <input type="hidden" name="id_item" value="<c:out value='${item.id_item}' />" />
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
                    <th>End Date: </th>
                    <td>
                        <input type="text" name="bid_end_date" size="5"
                               value="<c:out value='${item.bid_end_date}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Category: </th>
                    <td>
                        <input type="text" name="id_category" size="5"
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