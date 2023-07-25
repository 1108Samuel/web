<%@page import="loja.model.Carrinho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="loja.model.Produto"%>
<%@page import="java.util.List"%>
<%@page import="loja.dao.ProdutoDao"%>
<%@page import="loja.model.Usuario"%>
<%@page import="loja.conexao.DbConn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Usuario autor = (Usuario) request.getSession().getAttribute("autor");
if (autor != null) {
	request.setAttribute("autor", autor);
}

ProdutoDao pd = new ProdutoDao(DbConn.getConnection());
List<Produto> produto = pd.getAllProdutos();

ArrayList<Carrinho> carro_list = (ArrayList<Carrinho>) session.getAttribute("carroList");
List<Carrinho> carroProduto = null;
if (carro_list != null) {
	ProdutoDao pDao = new ProdutoDao(DbConn.getConnection());
	carroProduto = pDao.getCarrinhoProdutos(carro_list);
	double total= pDao.getTotalCarroValor(carro_list);
	request.setAttribute("carro_list", carro_list);

}
%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Cadastro Usuario</div>
			<div class="card-body">
				<form action="usuario-Cadastro" method="post">
				<div class="form-group">
					<label>Nome</label> <input type="nome" class="form-control"
							name="nome" placeholder="Nome" required="required">
					</div>
					<div class="form-group">
						<label>Email</label> <input type="email" class="form-control"
							name="email" placeholder="Email" required="required">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="password" placeholder="****"
							required="required">
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Cadastrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
			<%@include file="includes/rodape.jsp"%>
</body>
</html>

