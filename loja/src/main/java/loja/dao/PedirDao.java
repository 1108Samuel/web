package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import loja.model.*;

public class PedirDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public PedirDao(Connection con) {
		this.con=con;
	}
	
	public boolean inserirPedir(PedirNovo model) {
		boolean resultado = false;
		
		try {
			query = "insert into orders (produto_id, usuario_id, quantidade, data) values(?,?,?,?)";
			
			pst=this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUserId());
			pst.setInt(3, model.getQuantidade());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			resultado=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public List<PedirNovo> userPedido(int id){
		List<PedirNovo>lista=new ArrayList<>();
		try {
			query="select * from orders where usuario_id=? order by orders.id";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				PedirNovo pedir=new PedirNovo();
				ProdutoDao produtoDao=new ProdutoDao(this.con);
				int pId=rs.getInt("produto_id");
				Produto produto=produtoDao.getUmUnicoProdut0(pId);
				pedir.setOrderId(pId);
				pedir.setNome(produto.getNome());
				pedir.setCategoria(produto.getCategoria());
				pedir.setPreco(produto.getPreco()*rs.getInt("quantidade"));
				pedir.setQuantidade(rs.getInt("quantidade"));
				pedir.setDate(rs.getString("data"));
				lista.add(pedir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public void apagarPedido(int id) {
		try {
			query="delete from orders where id=?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
