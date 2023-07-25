package loja.savlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import loja.model.Carrinho;


@WebServlet("/Add-Carrinho")
public class AddCarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			ArrayList<Carrinho> carrinhoList = new ArrayList<Carrinho>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Carrinho cm = new Carrinho();
			cm.setId(id);
			cm.setQuantidade(1);
			
			HttpSession session =  request.getSession();
			ArrayList<Carrinho> carroList =(ArrayList<Carrinho>) session.getAttribute("carroList");
			
			if(carroList == null) {
				carrinhoList.add(cm);
				session.setAttribute("carroList", carrinhoList);
				response.sendRedirect("index.jsp");
			}else {
				carrinhoList=carroList;
				boolean existe = false;
				
				
				for(Carrinho c:carrinhoList) {
					if(c.getId()==id) {
						existe = true;
						out.println("<h3 style='color:crimson; text-align: center'>Item ja foi adcionado no carinho<a href='carrinho.jsp'>Ir para a pagina</a></h3><br><a href='index.jsp'>Ou Ir para a pagina inicial</a>");
					}
					
				}
				if(!existe) {
					carrinhoList.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
		}
	} 

}
