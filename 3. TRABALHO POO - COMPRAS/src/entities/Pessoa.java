package entities;

public class Pessoa {
	protected String tipo;

	public Pessoa(String tipo) {
		this.tipo = tipo;
	}
	
	public void visualizarProdutos() {
		//visualizar todos os produtos no arquivo json
	}
	
	public void comprarProduto() {
		//nao pode comprar mais do que tiver no estoque
	}
}