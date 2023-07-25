package loja.model;

public class Orders extends Produto{
	private int id;
	private int produto_id;
	private int usuario_id;
	private String date;
	public Orders() {
		
	}
	
	public Orders(int id, int produto_id, int usuario_id, String date) {
		super();
		this.id = id;
		this.produto_id = produto_id;
		this.usuario_id = usuario_id;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public int getProduto_id() {
		return produto_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public String getDate() {
		return date;
	}
	
	
}
