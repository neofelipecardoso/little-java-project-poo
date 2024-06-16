package menu;

import java.math.BigDecimal;

import pedido.Pedido;

public class Salada implements Pedido {
	private String descricao = "Salada";
	private BigDecimal custo;

	public Salada(BigDecimal custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCusto() {
		return custo;
	}
}