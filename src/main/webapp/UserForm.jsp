<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>users Store Application</title>
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
    <form action="update" method="post">
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
                            Banana
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
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
                    <th>Last Name: </th>
                    <td>
                        <input type="text" name="lastname" size="45"
                               value="<c:out value='${user.lastname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>First Name: </th>
                    <td>
                        <input type="text" name="firstname" size="45"
                               value="<c:out value='${user.firstname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="text" name="password" size="45"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone Number: </th>
                    <td>
                        <input type="text" name="phonenumber" size="45"
                               value="<c:out value='${user.phonenumber}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Street: </th>
                    <td>
                        <input type="text" name="street" size="45"
                               value="<c:out value='${user.street}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Postal Code: </th>
                    <td>
                        <input type="text" name="postalcode" size="45"
                               value="<c:out value='${user.postalcode}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" size="45"
                               value="<c:out value='${user.city}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Credit: </th>
                    <td>
                        <input type="text" name="credit" size="45"
                               value="<c:out value='${user.credit}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Admin?: </th>
                    <td>
                        <input type="text" name="isadmin" size="45"
                               value="<c:out value='${user.isadmin}' />"
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
