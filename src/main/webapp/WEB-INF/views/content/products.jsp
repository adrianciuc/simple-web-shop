<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${products}" var="product">
    <div class="product_container" id="product-${product.id}">
        <div class="product_image">
            <img src="${pageContext.request.contextPath}/static/img/img-placeholder.jpg">
        </div>
        <div class="product_details_container">
            <label>Denumire: <span class="product_title">${product.title}</span></label>
            <label>Pret: <span class="product_cost">${product.cost}</span> lei</label>
        </div>
        <button class="add_to_cart">Adauga in cos</button>
    </div>
</c:forEach>
