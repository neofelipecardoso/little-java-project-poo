package menu;

import pedido.Pedido;

public class BabyBeef implements Pedido {
	private String descricao;
	private double custo;

	public BabyBeef(String descricao, double custo) {
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