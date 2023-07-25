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
		<div class="card-header my-3">Todos os Produtos</div>
		<div class="row">
			<%
			if (!produto.isEmpty()) {
				for (Produto p : produto) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="produto-img/<%=p.getImagem()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getNome()%></h5>
						<h6 class="price">
							Preço: $<%=p.getPreco()%></h6>
						<h6 class="category">
							Categoria:
							<%=p.getCategoria()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="Add-Carrinho?id=<%=p.getId()%>">Add
								no carro</a> <a class="btn btn-primary"
								href="pedir-Novo-Savelt?quantidade=1&id=<%=p.getId()%>">Comprar agora</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
			<%@include file="includes/rodape.jsp"%>
</body>
</html>