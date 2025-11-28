<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>ĐĂNG NHẬP</h2>
    <h3 style="color:red">${message}</h3>
    
    <form action="login" method="post">
        Username: <input type="text" name="id"> <br>
        Password: <input type="password" name="password"> <br>
        <button type="submit">Login</button>
    </form>
</body>
</html>