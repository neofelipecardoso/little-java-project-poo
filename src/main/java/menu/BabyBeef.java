package menu;

import java.math.BigDecimal;

import pedido.Pedido;

public class BabyBeef implements Pedido {
	private String descricao = "Baby Beef";
	private BigDecimal custo;

	public BabyBeef(BigDecimal custo) {
		this.custo = custo;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCusto() {
		return custo;
	}
}