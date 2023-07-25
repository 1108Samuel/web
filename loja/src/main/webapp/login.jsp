<%@page import="loja.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    Usuario autor= (Usuario) request.getSession().getAttribute("autor");
    if(autor!=null){
    	response.sendRedirect("index.jsp");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navBar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Usuario_ Login</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Email</label>
						<input type="email" class="form-control" name="login-email" placeholder="Email" required="required">
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password" class="form-control" name="login-password" placeholder="****" required="required">
					</div>
					
					<div class="text-center">
					<button type="submit" class="btn btn-primary">Login</button>
					</div>
					<br>
					<a href="cadastroUser.jsp" class="btn btn-dark"> Cadastrar</a>
				</form>
			</div>
		</div>
	</div>
	
<%@include file="includes/rodape.jsp" %>
</body>
</html>