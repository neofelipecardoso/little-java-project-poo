package menu;

import java.math.BigDecimal;

import pedido.Pedido;

public class Hamburguer implements Pedido {
	private String descricao = "Hamburguer";
	private BigDecimal custo;

	public Hamburguer(BigDecimal custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCusto() {
		return custo;
	}
}
