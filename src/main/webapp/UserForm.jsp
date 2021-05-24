<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>users Store Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/new">Add New user</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All users</a>

    </h2>
</center>
<div align="center">
    <c:if test="${user != null}">
    <form action="updateUser" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="insertUser" method="post">
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
                    <input type="hidden" name="id_user" value="<c:out value='${user.id_user}' />" />
                </c:if>
                <tr>
                    <th>Nickname: </th>
                    <td>
                        <input type="text" name="nickname" size="45"
                               value="<c:out value='${user.nickname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Last name: </th>
                    <td>
                        <input type="text" name="last_name" size="45"
                               value="<c:out value='${user.last_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>First name: </th>
                    <td>
                        <input type="text" name="first_name" size="5"
                               value="<c:out value='${user.first_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="5"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="user_password" size="5"
                               value="<c:out value='${user.user_password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone number: </th>
                    <td>
                        <input type="text" name="phone_number" size="5"
                               value="<c:out value='${user.phone_number}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Street: </th>
                    <td>
                        <input type="text" name="street" size="5"
                               value="<c:out value='${user.street}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Postal Code: </th>
                    <td>
                        <input type="text" name="postal_code" size="5"
                               value="<c:out value='${user.postal_code}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" size="5"
                               value="<c:out value='${user.city}' />"
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