package loja.savlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

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


@WebServlet("/check-Out-Servlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()){
			SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
			Date data = new Date();
			ArrayList<Carrinho> carroList = (ArrayList<Carrinho>) request.getSession().getAttribute("carroList");
			Usuario autor=(Usuario) request.getSession().getAttribute("autor");
			if(carroList!=null && autor!=null) {
				for(Carrinho c:carroList) {
					PedirNovo pedir=new PedirNovo();
					pedir.setId(c.getId());
					pedir.setUserId(autor.getId());
					pedir.setQuantidade(c.getQuantidade());
					pedir.setDate(formato.format(data));
					
					PedirDao pDao=new PedirDao(DbConn.getConnection());
					Boolean result= pDao.inserirPedir(pedir);
					if(!result)break;
				}
				carroList.clear();
				response.sendRedirect("carrinho.jsp");
				
			}else {
				if(autor==null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("carrinho.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
