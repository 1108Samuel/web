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
 * Servlet implementation class RemoverDoCarrinhoServlet
 */
@WebServlet("/remover-Do-Carrinho-Servlet")
public class RemoverDoCarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			String id=request.getParameter("id");
			if(id!=null) {
				ArrayList<Carrinho> carroList = (ArrayList<Carrinho>) request.getSession().getAttribute("carroList");
				if(carroList !=null) {
					for(Carrinho c:carroList) {
						if(c.getId()== Integer.parseInt(id)) {
							carroList.remove(carroList.indexOf(c));
							break;
						}
					}
					response.sendRedirect("carrinho.jsp");
				}
				}else {
					response.sendRedirect("carrinho.jsp");
				}
			}
		} 
	}



