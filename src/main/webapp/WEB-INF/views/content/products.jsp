<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${products}" var="product">
    <div class="product_container" id="product-${product.id}">
        <label>Title:</label>
        <span class="product_title">${product.title}</span>
        <label>Cost:</label>
        <span class="product_title">${product.cost}</span>
        <button>Add to Cart</button>
    </div>
</c:forEach>