<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SWS</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/sws.css">

    <script>const CONTEXT_PATH = "<%=request.getContextPath()%>"</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/productsService.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/sws.js"></script>
</head>
<body>
<jsp:include page="/categories/"/>
<div class="products_container"></div>
</body>
</html>
