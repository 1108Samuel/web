package loja.savlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loja.conexao.DbConn;
import loja.dao.PedirDao;
import loja.model.Carrinho;
import loja.model.PedirNovo;
import loja.model.Usuario;


@WebServlet("/pedir-Novo-Savelt")
public class PedirNovoSavelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
			Date data = new Date();

			Usuario autor = (Usuario) request.getSession().getAttribute("autor");
			if (autor != null) {
				String produtoId = request.getParameter("id");
				int produtoQuantidade = Integer.parseInt(request.getParameter("quantidade"));
				if (produtoQuantidade <= 0) {
					produtoQuantidade = 1;
				}
				PedirNovo pedir = new PedirNovo();
				pedir.setId(Integer.parseInt(produtoId));
				pedir.setUserId(autor.getId());
				pedir.setQuantidade(produtoQuantidade);
				pedir.setDate(formato.format(data));

				PedirDao pedirDao = new PedirDao(DbConn.getConnection());
				Boolean result= pedirDao.inserirPedir(pedir);
				 if (result) {
					 ArrayList<Carrinho> carroList = (ArrayList<Carrinho>) request.getSession().getAttribute("carroList");
						if(carroList !=null) {
							for(Carrinho c:carroList) {
								if(c.getId()== Integer.parseInt(produtoId)) {
									carroList.remove(carroList.indexOf(c));
									break;
								}
							}
							response.sendRedirect("orders.jsp");
						}
	                } else {
	                    out.println("order failed");
	                }
	            } else {
	                response.sendRedirect("login.jsp");
	            }
		}catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
