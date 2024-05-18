package pedido;

public abstract class Detalhe implements Pedido {
	protected Pedido pedido;

	public Detalhe(Pedido pedido) {
		this.pedido = pedido;
	}
}