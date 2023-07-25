<%@page import="java.text.DecimalFormat"%>
<%@page import="loja.model.PedirNovo"%>
<%@page import="loja.dao.PedirDao"%>
<%@page import="loja.dao.ProdutoDao"%>
<%@page import="java.util.List"%>
<%@page import="loja.model.Carrinho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="loja.model.Usuario"%>
<%@page import="loja.conexao.DbConn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf=new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
List<PedirNovo> pedir=null;
Usuario autor = (Usuario) request.getSession().getAttribute("autor");
if (autor != null) {
	request.setAttribute("autor", autor);
	pedir=new PedirDao(DbConn.getConnection()).userPedido(autor.getId());
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Carrinho> carro_list = (ArrayList<Carrinho>) session.getAttribute("carroList");
List<Carrinho> carroProduto = null;
if (carro_list != null) {
	ProdutoDao pDao = new ProdutoDao(DbConn.getConnection());
	carroProduto = pDao.getCarrinhoProdutos(carro_list);
	double total = pDao.getTotalCarroValor(carro_list);
	request.setAttribute("carro_list", carro_list);
	
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Pagina de Pedidos</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container">
		<div class="card-header my-3">Todos os pedidos</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Preço</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>

				<%
				if (pedir != null) {
					for (PedirNovo o : pedir) {
				%>
				<tr>
					<td><%=o.getDate()%></td>
					<td><%=o.getNome()%></td>
					<td><%=o.getCategoria()%></td>
					<td><%=o.getQuantidade()%></td>
					<td><%=dcf.format(o.getPreco())%></td>
					<td><a class="btn btn-sm btn-danger"
						href="cancelar-Servlet?id=<%=o.getOrderId()%>">Cancelar Pedido</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>

	<%@include file="includes/rodape.jsp"%>
</body>
</html>