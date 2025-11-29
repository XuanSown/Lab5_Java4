<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập hệ thống</title>
</head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h2>ĐĂNG NHẬP</h2>
        
        <h3 style="color:red; min-height: 30px;">${message}</h3>
        
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>Email:</label><br>
            <input type="email" name="email" value="${email}" required style="padding: 5px; margin: 5px;"> <br>
            
            <label>Password:</label><br>
            <input type="password" name="password" required style="padding: 5px; margin: 5px;"> <br>
            
            <br>
            <button type="submit" style="padding: 5px 15px; cursor: pointer;">Login</button>
        </form>
    </div>
</body>
</html>