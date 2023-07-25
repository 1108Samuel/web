package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import loja.model.Usuario;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public Usuario usuario_(String nome, String email, String pass) {
		Usuario user = null;
		try {
			query="insert into usuarios (nome, email, pass) values (?,?,?)";
			pst=this.con.prepareStatement(query);
			pst.setString(1, nome);
			pst.setString(2, email);
			pst.setString(3, pass);
			pst.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
	
	public Usuario usuarioLogin(String email, String pass) {
		Usuario user =null;
		try {
			query = "select * from usuarios where email=? and pass=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, pass);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				user=new Usuario();
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
}
