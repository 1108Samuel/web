 package loja.model;

public class PedirNovo extends Produto{
	private int orderId;
	private int userId;
	private int quantidade;
	private String date;
	
	public PedirNovo() {
		
	}
	public PedirNovo(int orderId, int userId, int quantidade, String date) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantidade = quantidade;
		this.date = date;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	

	
	
}
