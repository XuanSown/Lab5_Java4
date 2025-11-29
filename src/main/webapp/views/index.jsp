<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${!empty sessionScope.user}">
            Xin chào: <b>${sessionScope.user.fullName}</b>

		</c:if>
		<br>
		<c:if test="${empty sessionScope.user}">
            Bạn chưa đăng nhập. <a
				href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a>
		</c:if>
	</div>
</body>
</html>