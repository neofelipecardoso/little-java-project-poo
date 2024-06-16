package cozinha;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import pedido.Pedido;

public class Cozinha {
	private Map<Integer, List<Pedido>> pedidosPorMesa;

	public Cozinha() {
		pedidosPorMesa = new HashMap<Integer, List<Pedido>>();
	}

	public void receberPedidos(int numeroMesa, List<Pedido> pedidos) {
		pedidosPorMesa.put(numeroMesa, pedidos);
		organizarPedidos();
		prepararPedidos();
	}

	private void organizarPedidos() {
		// Implementação da lógica de organização dos pedidos

		// Aqui você pode ordenar os pedidos por alguma lógica específica,
		// como por exemplo, prioridade do pedido, tipo de pedido, etc.
		// Por enquanto, vamos apenas imprimir os pedidos organizados por mesa

		System.out.println("\n=== PEDIDOS NA COZINHA ===");
		for (Map.Entry<Integer, List<Pedido>> entry : pedidosPorMesa.entrySet()) {
			int numeroMesa = entry.getKey();
			List<Pedido> pedidos = entry.getValue();
			System.out.println("Mesa " + numeroMesa + ":");
			for (Pedido pedido : pedidos) {
				System.out.println("- " + pedido.getDescricao());
			}
		}
	}

	public void prepararPedidos() {
		// Implementação da lógica de preparação dos pedidos

		// Aqui você pode simular a preparação dos pedidos, como por exemplo,
		// definindo um tempo de preparo para cada tipo de pedido

		System.out.println("\nPreparando pedidos...");

		// Vamos simular um tempo de preparo de 3 segundos para cada pedido
		for (Map.Entry<Integer, List<Pedido>> entry : pedidosPorMesa.entrySet()) {
			List<Pedido> pedidos = entry.getValue();
			for (Pedido pedido : pedidos) {
				System.out.println("Preparando: " + pedido.getDescricao());
				// Simulação do tempo de preparo
				try {
					Thread.sleep(3000); // 3 segundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Pedido pronto: " + pedido.getDescricao());
			}
		}

		// Após preparar os pedidos, limpar o mapa de pedidos na cozinha
		pedidosPorMesa.clear();
	}
}