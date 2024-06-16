package pedido;

import java.math.BigDecimal;

public class DetalheExtra extends Detalhe {
	private String descricaoExtra;
	private BigDecimal custoExtra;

	public DetalheExtra(Pedido pedido, String descricaoExtra, BigDecimal custoExtra) {
		super(pedido);
		this.descricaoExtra = descricaoExtra;
		this.custoExtra = custoExtra;
	}

	public String getDescricao() {
		return pedido.getDescricao() + ", " + descricaoExtra;
	}

	public BigDecimal getCusto() {
		return pedido.getCusto().add(custoExtra);
	}
}