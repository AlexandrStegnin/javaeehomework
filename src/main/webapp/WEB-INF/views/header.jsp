<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${param.title}</title>
    <link href="<c:url value="/resources/styles/style.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <table class="menu" width="100%">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/main">Главная</a>
                <a href="${pageContext.request.contextPath}/catalog">Каталог</a>
                <a href="${pageContext.request.contextPath}/cart">Корзина</a>
                <a href="${pageContext.request.contextPath}/about">О компании</a>
            </td>
        </tr>
    </table>
</body>
</html>
