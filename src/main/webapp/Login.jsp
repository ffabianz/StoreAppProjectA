<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Encheres Application</title>
</head>
<body>
<center>
    <h1>users Management</h1>
    <h2>
        <a href="/new">Add New user</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All users</a>

    </h2>
</center>
<div align="center">
    <c:if test="${user != null}">
    <form action="login" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="submit" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit user
                        </c:if>
                        <c:if test="${user == null}">
                            Add New user
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>
                <tr>
                    <th>email: </th>
                    <td>
                        <input type="email" name="email" size="45"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>password: </th>
                    <td>
                        <input type="password" name="user_password" size="45"
                               value="<c:out value='${user.user_password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Login" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>