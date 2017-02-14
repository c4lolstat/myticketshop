<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sptg" tagdir="/WEB-INF/tags"%>
<html>
<head>
<title>Error</title>
<link href="${pageContext.request.contextPath}/resources/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
</head>
<body>
    <br>
    <div align="center">
        <h6>Some error occured! Please try again.</h6>
    </div>
    <div align="center">
        <img src="${pageContext.request.contextPath}/resources/images/error.png" alt="error" align="middle">
    </div>
</body>
</html>