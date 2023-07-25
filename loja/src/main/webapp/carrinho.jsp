<%@page import="java.text.DecimalFormat"%>
<%@page import="loja.dao.ProdutoDao"%>
<%@page import="java.util.List"%>
<%@page import="loja.model.Carrinho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="loja.model.Usuario"%>
<%@page import="loja.conexao.DbConn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
Usuario autor = (Usuario) request.getSession().getAttribute("autor");
if (autor != null) {
	request.setAttribute("autor", autor);
}

ArrayList<Carrinho> carro_list = (ArrayList<Carrinho>) session.getAttribute("carroList");
List<Carrinho> carroProduto = null;
if (carro_list != null) {
	ProdutoDao pDao = new ProdutoDao(DbConn.getConnection());
	carroProduto = pDao.getCarrinhoProdutos(carro_list);
	double total= pDao.getTotalCarroValor(carro_list);
	request.setAttribute("carro_list", carro_list);
	request.setAttribute("total", total);

}
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container my-3">
		<div class="d-flex py-3">
			<h3>
				Preço Total: $ ${(total>0)?total:0}
				<!-- ${(total>0)?dcf.format(total):0}-->
			</h3>
			<a class="mx-3 btn btn-primary" href="check-Out-Servlet">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Preço</th>
					<th scope="col">Comprar Agora</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
<tbody>
				<%
				if (carro_list != null) {
					for (Carrinho c : carroProduto) {
				%>
				<tr>
					<td><%=c.getNome()%></td>
					<td><%=c.getCategoria()%></td>
					<td><%= dcf.format(c.getPreco())%></td>
					<td>
						<form action="pedir-Novo-Savelt" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre" href="quantidade-No-Carro?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantidade" class="form-control w-50"  value="<%=c.getQuantidade()%>" readonly> 
								<a class="btn btn-sm btn-decre" href="quantidade-No-Carro?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Comprar</button>
						</form>
					</td>
					<td><a href="remover-Do-Carrinho-Servlet?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remover</a></td>
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