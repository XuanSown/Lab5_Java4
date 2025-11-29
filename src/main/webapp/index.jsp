<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h1>TRANG CHỦ</h1>
        <hr>
        
        <div style="padding: 10px; margin-bottom: 20px;">
        	<strong>Lượt truy cập: ${applicationScope.visitors}</strong>
        </div>
        
        <c:if test="${not empty sessionScope.user}">
            <h3>Xin chào: <span style="color: blue;">${sessionScope.user.fullName}</span></h3>
            <p>Email: ${sessionScope.user.email}</p>
            
            <br>
            <a href="${pageContext.request.contextPath}/logout" 
               style="color: red; text-decoration: none; border: 1px solid red; padding: 5px 10px;">
               Đăng xuất
            </a>
        </c:if>

        <c:if test="${empty sessionScope.user}">
            <h3>Bạn chưa đăng nhập!</h3>
            <p>Vui lòng đăng nhập để sử dụng hệ thống.</p>
            
            <a href="${pageContext.request.contextPath}/login"
               style="background-color: green; color: white; text-decoration: none; padding: 8px 15px; border-radius: 4px;">
               Đăng nhập ngay
            </a>
        </c:if>
    </div>
</body>
</html>