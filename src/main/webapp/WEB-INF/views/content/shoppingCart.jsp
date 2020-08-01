<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="shopping_cart">
    Cosul meu are
    <span id="nr_products_in_cart">
        <c:choose>
            <c:when test="${shoppingCart != null}">
                <c:out value="${shoppingCart.productIds.size()}"/>
            </c:when>
            <c:otherwise>
                <c:out value="11000"/>
            </c:otherwise>
        </c:choose>

    </span> produse.</div>