package menu;

import java.math.BigDecimal;

import pedido.Pedido;

public class Pizza implements Pedido {
	private String descricao = "Pizza";
	private BigDecimal custo;

	public Pizza(BigDecimal custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCusto() {
		return custo;
	}
}