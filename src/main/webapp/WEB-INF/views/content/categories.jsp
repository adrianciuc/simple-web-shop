<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu-container">
    <div class="categories-container">
        <c:forEach items="${categories}" var="category">
            <div id="category-${category.id}" class="category">
                ${category.title}
            </div>
        </c:forEach>
    </div>
</div>