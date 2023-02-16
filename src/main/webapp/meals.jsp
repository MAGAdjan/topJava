<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .normal {
            color: green;
        }
        .excess {
            color: red;
        }
    </style>
</head>
<body>
<%ArrayList<MealTo> meals = (ArrayList<MealTo>) request.getAttribute("meals");%>
<table style="width: 50%" border="1">
    <thead style="font-weight:bold">
    <tr>
        <td>Дата/время</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>
    </thead>

    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}" var="formattedDate"/>
            <td><${formattedDate}"</td>
            <td><${meal.description}"</td>
            <td>${meal.calories}"</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
