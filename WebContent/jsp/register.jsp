<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrarse | Tienda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		String error = (request.getAttribute("usr") != null) ? (String) request.getAttribute("usr") + " ya existe."
				: "";
	%>
	<br>
	<br>
	<div>
		<h1>Registrarse</h1>
		<form action="<%=request.getContextPath()%>/register" method="post">
			<input type="text" name="name" placeholder="Nombre usuario" autofocus><br>
			<br> <input type="password" name="pass" placeholder="Contraseña"><br>
			<br> <input type="submit" value="Enviar">
		</form>
		<br> <br> <a style="font-size: 20px"
			href="/">Inicio</a>
	</div>
	<br>
	<br>
	<h1 style="color: red;"><%=error%></h1>
</body>
</html>