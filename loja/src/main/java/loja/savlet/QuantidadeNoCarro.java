package loja.savlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loja.model.Carrinho;

/**
 * Servlet implementation class QuantidadeNoCarro
 */
@WebServlet("/quantidade-No-Carro")
public class QuantidadeNoCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset-UTF-8");
		try(PrintWriter out =response.getWriter();) {
			String action = request.getParameter("action");
			int id =Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Carrinho> carroList = (ArrayList<Carrinho>) request.getSession().getAttribute("carroList");
			
			if(action != null && id>-1) {
				if(action.equals("inc")) {
					for(Carrinho c:carroList) {
						if(c.getId()==id) {
							int quantidade= c.getQuantidade();
							quantidade++;
							c.setQuantidade(quantidade);
							response.sendRedirect("carrinho.jsp");
							
						}
					}
				}
				if(action.equals("dec")) {
					for(Carrinho c:carroList) {
						if(c.getId()==id && c.getQuantidade()>1) {
							int quantidade= c.getQuantidade();
							quantidade--;
							c.setQuantidade(quantidade);
							break;
							
						}
					}
					response.sendRedirect("carrinho.jsp");
				}else {
					response.sendRedirect("carrinho.jsp");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


}
