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

@WebServlet("/user-login")
public class loginSavlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset-UTF8");
		try (PrintWriter out=response.getWriter()){
			String email = request.getParameter("login-email");
			String pass = request.getParameter("login-password");
			try {
				UserDao udao= new UserDao(DbConn.getConnection());
				Usuario user = udao.usuarioLogin(email, pass);
				
				if(user != null) {
					request.getSession().setAttribute("autor", user);
					response.sendRedirect("index.jsp");
				}else {
					out.print("Erro no login");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			
			//out.print(email + pass);
		} 
	}

}
