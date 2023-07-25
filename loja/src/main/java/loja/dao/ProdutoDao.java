package loja.dao;

import java.sql.Connection;
import java.util.*;

import loja.model.Carrinho;
import loja.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProdutoDao(Connection con) {
		this.con = con;
	}

	public List<Produto> getAllProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			query = "select * from produtos";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Produto row = new Produto();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("nome"));
				row.setPreco(rs.getDouble("valor"));
				row.setCategoria(rs.getString("categoria"));
				row.setImagem(rs.getString("imagem"));

				produtos.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public List<Carrinho> getCarrinhoProdutos(ArrayList<Carrinho> carrinhoList) {
		List<Carrinho> produtos = new ArrayList<Carrinho>();

		try {
			if (carrinhoList.size() > 0) {
				for (Carrinho item : carrinhoList) {
					query = "select * from produtos where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Carrinho row = new Carrinho();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("nome"));
						row.setCategoria(rs.getString("categoria"));
						row.setPreco(rs.getDouble("valor") * item.getQuantidade());
						row.setQuantidade(item.getQuantidade());
						produtos.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return produtos;
	}

	public Produto getUmUnicoProdut0(int id) {
		Produto row = null;
		try {
			query = "select * from produtos where id=? ";

			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				row = new Produto();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("nome"));
				row.setCategoria(rs.getString("categoria"));
				row.setPreco(rs.getDouble("valor"));
				row.setImagem(rs.getString("imagem"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return row;
	}

	public double getTotalCarroValor(ArrayList<Carrinho> carroList) {
		double soma = 0;
		try {
			if (carroList.size() > 0) {
				for (Carrinho item : carroList) {
					query = "select valor from produtos where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						soma += rs.getDouble("valor") * item.getQuantidade();
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return soma;
	}

	public List<Carrinho> getCarroProdutos(ArrayList<Carrinho> carroList) {
		List<Carrinho> iten = new ArrayList<>();
		try {
			if (carroList.size() > 0) {
				for (Carrinho item : carroList) {
					query = "select * from produtos where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Carrinho row = new Carrinho();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("nome"));
						row.setCategoria(rs.getString("categoria"));
						row.setPreco(rs.getDouble("valor") * item.getQuantidade());
						row.setQuantidade(item.getQuantidade());
						iten.add(row);
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return iten;

	}
	
}
