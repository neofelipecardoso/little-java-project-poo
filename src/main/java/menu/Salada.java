package menu;

import pedido.Pedido;

public class Salada implements Pedido {
	private String descricao;
	private double custo;

	public Salada(String descricao, double custo) {
		this.descricao = descricao;
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getCusto() {
		return custo;
	}
}