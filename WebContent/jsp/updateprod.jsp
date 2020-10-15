<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="info.pablogiraldo.tienda_bd.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar producto | Tienda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		// 		String displayLogin = (String) session.getAttribute("user") != null ? "display:none" : "";

	String displayLogout = (String) session.getAttribute("user") == null ? "display:none" : "";
	%>
	<br>
	<br>
	<h1>Editar producto</h1>
	<br>
	<br>
	<%
		Product prod = (Product) request.getAttribute("prod");
	%>
	<div Style="<%=displayLogout%>">
		<form action="<%=request.getContextPath()%>/updateprod" method="post">
			<input type="hidden" name="ident"
				value="<%=request.getParameter("id")%>"> <input type="text"
				name="name" value="<%=prod.getName()%>"><br> <input
				type="text" name="info" value="<%=prod.getInfo()%>"><br>
			<input type="text" name="price" value="<%=prod.getPrice()%>"><br>
			<input type="text" name="iva" value="<%=prod.getIva()%>"><br>
			<input type="text" name="stock" value="<%=prod.getStock()%>"><br>
			<br> <input type="submit" value="Enviar">
		</form>
		<br> <br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/home">Inicio test</a>
	</div>
	<br>
	<br>
</body>
</html>