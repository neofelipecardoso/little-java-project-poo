package menu;

import java.math.BigDecimal;

import pedido.Pedido;

public class Bauru implements Pedido {
	private String descricao = "Bauru";
	private BigDecimal custo;

	public Bauru(BigDecimal custo) {
		this.custo = custo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getCusto() {
		return custo;
	}
}