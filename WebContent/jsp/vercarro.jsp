<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Formatter"%>
<%@ page import="info.pablogiraldo.tienda_bd.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carro compra | Tienda</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		String displayLogin = (String) session.getAttribute("user") != null ? "display:none" : "";

		String displayLogout = (String) session.getAttribute("user") == null ? "display:none" : "";

		@SuppressWarnings("unchecked")
		ArrayList<Product> carro = (ArrayList<Product>) request.getAttribute("carro");

		float total = 0.0f;

		StringBuilder sb = new StringBuilder();

		Formatter formatter = new Formatter(sb);
	%>
	<br>
	<br>
	<h1>Carro compra</h1>
	<br>
	<br>
	<div Style="<%=displayLogout%>;text-align:left">
		<ul>
			<%
				for (Product prod : carro) {
					total += prod.getPrice();
			%>
			<li><%=prod.getName()%></li>
			<%
				}
			%>
		</ul>
		<br>
		<p Style="margin: 25px;">
			Total:
			<%=formatter.format("%.2f", total)%></p>
	</div>
	<%
		formatter.close();
	%>
	<br>
	<br>

	<div Style="<%=displayLogout%>">
		<br> <br> <a style="font-size: 20px" href="/">Inicio</a> <br>
		<br>
	</div>
</body>
</html>