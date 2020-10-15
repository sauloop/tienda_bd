<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iniciar sesión | Tienda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<br>
	<br>
	<div>
		<h1>Iniciar sesión</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">
			<input type="text" name="name" placeholder="Nombre usuario" autofocus><br>
			<br> <input type="password" name="pass" placeholder="Contraseña"><br>
			<br> <input type="submit" value="Enviar">
		</form>
		<br> <br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/register">Registrarse</a>
	</div>
</body>
</html>