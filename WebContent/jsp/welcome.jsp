<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenida | Tienda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		String user = "";
		if (session.getAttribute("user") != null) {
			user = (String) session.getAttribute("user");
		}
	%>

	<br>
	<br>
	<div>
		<h1>
			Hola
			<%=user%>.
		</h1>

		&nbsp;&nbsp; <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/logout">Cerrar sesión</a>
	</div>
	<br>
	<br>
	<div>
		<a style="font-size: 20px" href="<%=request.getContextPath()%>/home">Inicio</a>
	</div>


</body>
</html>