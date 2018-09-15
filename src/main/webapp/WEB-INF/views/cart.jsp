<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Корзина" />
</jsp:include>

<table class="table table-bordered table-hover">
    <thead style="text-align: center" class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Название</th>
        <th scope="col">Цена</th>
        <th scope="col">Действие</th>
    </tr>
    </thead>
    <c:set var="total" value="${0}" />
    <c:forEach var="product" items="${cart}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td style="text-align: center; width: 20%;">
                <a class="btn btn-danger" href="delete-from-cart?id=${product.id}">Удалить</a>
            </td>
        </tr>
        <c:set var="total" value="${total + product.price}" />
    </c:forEach>
    <tr>
        <td colspan="2" style="text-align: left">Итого:</td>
        <td colspan="2">${total}</td>
    </tr>
</table>

<a href="<c:url value="/order"/>" class="btn btn-success">Оформить заказ</a>