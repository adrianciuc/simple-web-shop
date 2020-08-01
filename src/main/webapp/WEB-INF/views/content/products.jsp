<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${products}" var="product">
    <div class="product_container" id="product-${product.id}">
        <label>Denumire:</label>
        <span class="product_title">${product.title}</span>
        <label>Pret:</label>
        <span class="product_cost">${product.cost}</span>
        <button>Add to Cart</button>
    </div>
</c:forEach>
