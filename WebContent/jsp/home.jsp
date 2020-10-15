<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="info.pablogiraldo.tienda_bd.model.Product"%>
<%@page import="java.util.Properties"%>
<%
	Properties proplan = (Properties) request.getAttribute("proplan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=proplan.getProperty("inicio.titulopag")%></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
		String displayLogin = (String) session.getAttribute("user") != null ? "display:none" : "";

		String displayLogout = (String) session.getAttribute("user") == null ? "display:none" : "";

		String usr = (String) session.getAttribute("user") != null ? (String) session.getAttribute("user") : "";

		String displayAdmin = usr.equals("admin") ? "" : "display:none";

		String displayUsr = !usr.equals("admin") ? "" : "display:none";

		String mensaje = (request.getAttribute("mensaje") != null) ? (String) request.getAttribute("mensaje") : "";

		String selEn = session.getAttribute("idioma").equals("en") ? "selected" : "";

		String selEs = session.getAttribute("idioma").equals("es") ? "selected" : "";
	%>
	<script type="text/javascript">
		function loadIdioma(selection) {
			var idioma = selection.value;
			window.location.assign("?idioma=" + idioma)
		}
	</script>

	<div class="custom-select">
		<br>
		<form action="<%=request.getContextPath()%>">
			<select name="idioma" onchange="loadIdioma(this);">
				<option value="en" <%=selEn%>>English</option>
				<option value="es" <%=selEs%>>Español</option>
			</select>
		</form>
	</div>
	<br>
	<br>
	<h1>
		<a style="font-size: 40px" href="<%=request.getContextPath()%>"><%=proplan.getProperty("inicio.titulo")%></a>
	</h1>

	<div Style="<%=displayLogout%>">
		<br> <br>
		<h3><%=mensaje%></h3>
		<br> <br>
	</div>

	<div Style="<%=displayLogout%>">
		<br> <br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/vercarro">Ver carro</a> <br>
		<br>
	</div>

	<div Style="<%=displayLogin%>">
		<br> <br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/login"><%=proplan.getProperty("inicio.iniciarses")%></a><br>
		<br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/register"><%=proplan.getProperty("inicio.registrarse")%></a>
	</div>

	<div Style="<%=displayLogout%>">
		<br> <br> <a style="font-size: 20px"
			href="<%=request.getContextPath()%>/logout"><%=proplan.getProperty("inicio.cerrarses")%></a>
		<br> <br>
	</div>

	<div Style="<%=displayAdmin%>;<%=displayLogout%>">
		<br> <br>
		<h3>Nuevo producto</h3>
		<form action="<%=request.getContextPath()%>/createprod" method="post">
			<input type="text" name="name" placeholder="Nombre"><br>
			<input type="text" name="info" placeholder="Info"><br> <input
				type="text" name="price" placeholder="Precio sin IVA"><br>
			<input type="text" name="iva" placeholder="IVA"><br> <input
				type="text" name="stock" placeholder="Stock"><br> <br>
			<input type="submit" value="Enviar">
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div>
		<table style="width: 100%">
			<tr>
				<th><%=proplan.getProperty("inicio.nombre")%></th>
				<th><%=proplan.getProperty("inicio.info")%></th>
				<th><%=proplan.getProperty("inicio.precio")%></th>
				<th><%=proplan.getProperty("inicio.iva")%></th>
				<th><%=proplan.getProperty("inicio.unidades")%></th>
				<th colspan="3"><%=proplan.getProperty("inicio.operaciones")%></th>
			</tr>

			<c:forEach var="product" items="${products}">
				<tr>
					<td><c:out value="${product.name}" /></td>
					<td><c:out value="${product.info}" /></td>
					<td><c:out value="${product.price}" /></td>
					<td><c:out value="${product.iva}" /></td>
					<td><c:out value="${product.stock}" /></td>
					<td>
						<div Style="<%=displayAdmin%>">
							<a style="font-size: 20px"
								href="<%=request.getContextPath()%>/updateprod?id=<c:out value="${product.id}" />">Editar</a>
						</div>
					</td>
					<td><div Style="<%=displayAdmin%>">
							<a style="font-size: 20px"
								href="<%=request.getContextPath()%>/deleteprod?id=
				<c:out value="${product.id}" />">Borrar</a>
						</div></td>
					<td><div Style="<%=displayUsr%>;<%=displayLogout%>">
							<a style="font-size: 20px"
								href="/carro?id=
				<c:out value="${product.id}" />">Comprar</a>
						</div></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>