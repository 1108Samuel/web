package loja.savlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loja.conexao.DbConn;
import loja.dao.UserDao;
import loja.model.Usuario;

@WebServlet("/usuario-Cadastro")
public class UsuarioCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()) {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			try {
				UserDao udao= new UserDao(DbConn.getConnection());
				Usuario user = udao.usuario_(nome, email, pass);
				
				if(user != null) {
					request.getSession().setAttribute("autor", user);
					response.sendRedirect("index.jsp");
				}else {
					response.sendRedirect("index.jsp");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
