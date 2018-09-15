<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Оформление заказ" />
</jsp:include>

<form action="order-success" method="post">
<label for="username">Ваше имя</label>
<input type="text" id="username" placeholder="Введите Ваше имя">
<label for="address">Адрес доставки</label>
<input type="text" id="address" placeholder="Укажите адрес доставки">
<label for="payMethod">Выберите способ оплаты:</label>
<select id="payMethod">
    <option value="0">Наличные</option>
    <option value="1">Онлайн оплата</option>
    <option value="2">Картой курьеру</option>
</select>
<input class="btn btn-success" type="submit" value="Подтвердить">
</form>

<table class="table table-bordered table-hover">
    <thead style="text-align: center" class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Название</th>
        <th scope="col">Цена</th>
    </tr>
    </thead>
    <c:set var="total" value="${0}" />
    <c:forEach var="product" items="${cart}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
        </tr>
        <c:set var="total" value="${total + product.price}" />
    </c:forEach>
    <tr>
        <td colspan="2" style="text-align: left">Итого:</td>
        <td>${total}</td>
    </tr>
</table>