package pedido;

public class DetalheExtra extends Detalhe {
	private String descricaoExtra;
	private double custoExtra;

	public DetalheExtra(Pedido pedido, String descricaoExtra, double custoExtra) {
		super(pedido);
		this.descricaoExtra = descricaoExtra;
		this.custoExtra = custoExtra;
	}

	public String getDescricao() {
		return pedido.getDescricao() + ", " + descricaoExtra;
	}

	public double getCusto() {
		return pedido.getCusto() + custoExtra;
	}
}