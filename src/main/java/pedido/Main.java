package pedido;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;

import menu.*;
import cozinha.Cozinha;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static Cozinha cozinha = new Cozinha();
	private static Map<Integer, List<Pedido>> pedidosPorMesa = new HashMap<Integer, List<Pedido>>();

	public static void main(String[] args) {
		int opcao;
		while (true) {
			try {
				exibirMenuInicial();
				opcao = scanner.nextInt();
				scanner.nextLine(); // Limpar o buffer do scanner
	
				switch (opcao) {
					case 1:
						adicionarMesa();
						break;
					case 2:
						removerMesa();
						break;
					case 3:
						exibirMesas();
						break;
					case 0:
						System.out.println("Saindo do sistema...");
						return;
					default:
						System.out.println("Opção inválida! Tente novamente.");
				}
			} catch (Exception e) {
				System.out.println("\nOcorreu um erro: " + e.getClass().getSimpleName());
				scanner.nextLine(); // Limpar o buffer do scanner
			}
		}
	}

	private static void exibirMenuInicial() {
		System.out.print("\n=== ESCOLHA ===\n" +
						 "1. Adicionar ou escolher mesa\n" +
						 "2. Remover mesa\n" +
						 "3. Exibir mesas\n" +
						 "0. Sair\n" +
						 "Escolha uma opção: ");
		return;
	}

	private static void adicionarMesa() {
		System.out.print("\n=== ADICIONAR MESA ===\n" +
						 "Digite o número da mesa: ");
		int numeroMesa = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer do scanner

		if (pedidosPorMesa.containsKey(numeroMesa)) {
			System.out.println("Escolhido mesa já existente!");
			exibirMenuPrincipal(numeroMesa);
			return;
		}
	
		pedidosPorMesa.put(numeroMesa, new ArrayList<Pedido>());
		System.out.println("Mesa adicionada com sucesso!");
		exibirMenuPrincipal(numeroMesa);
	}

	private static void exibirMesas() {
		System.out.println("\n=== MESAS ===");

		if (pedidosPorMesa.isEmpty()) {
			System.out.println("Não há mesas para serem exibidas!");
			return;
		}
		
		for (Map.Entry<Integer, List<Pedido>> entry : pedidosPorMesa.entrySet()) {
			Integer mesa = entry.getKey();
			List<Pedido> valores = entry.getValue();

			System.out.println("Mesa " + mesa + ":");

			for (Pedido valor : valores) {
				System.out.println("  - " + valor.getDescricao());
			}
		}
	}

	private static void removerMesa() {
		System.out.print("\n=== REMOVER MESA ===\n");

		if (pedidosPorMesa.isEmpty()) {
			System.out.println("Não há mesas para serem removidas!");
			return;
		}
		
		System.out.print("Digite o número da mesa a ser removida: ");
		int numeroMesa = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer do scanner

		if (!pedidosPorMesa.containsKey(numeroMesa)) {
			System.out.println("Mesa não encontrada!");
			return;
		}

		pedidosPorMesa.remove(numeroMesa);
		System.out.println("Mesa removida com sucesso!");
	}

	private static void exibirMenuPrincipal(int numeroMesa) {
		int opcao;

		while (true) {
			System.out.print("\n=== MENU PRINCIPAL - MESA " + numeroMesa + " ===\n" +
							 "1. Adicionar pedido\n" +
							 "2. Remover pedido\n" +
							 "3. Exibir pedidos\n" +
							 "4. Entregar pedidos à cozinha\n" +
							 "0. Voltar ao menu inicial\n" +
							 "Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer do scanner

			switch (opcao) {
				case 1:
					adicionarPedido(numeroMesa);
					break;
				case 2:
					removerPedido(numeroMesa);
					break;
				case 3:
					exibirPedidos(numeroMesa);
					break;
				case 4:
					entregarPedidos(numeroMesa);
					return;
				case 0:
					return;
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}
		}
	}

	private static void adicionarPedido(int numeroMesa) {
		System.out.println("\n=== ADICIONAR PEDIDO ===");
		List<Pedido> pedidos = pedidosPorMesa.getOrDefault(numeroMesa, new ArrayList<Pedido>());
		
		System.out.print(
			"Produto         Preço\n" + 
			"1. Pizza        R$  14.00\n" +
			"2. Salada       R$  20.00\n" +
			"3. Hamburguer   R$  40.00\n" +
			"4. Baby Beef    R$ 120.00\n" +
			"5. Bauru        R$  37.00\n" +
			"Escolha uma opção: "
		);
		int escolha = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer do scanner

		Pedido pedido;
		switch (escolha) {
			case 1:
				pedido = new Pizza(BigDecimal.valueOf(14.00));
				break;
			case 2:
				pedido = new Salada(BigDecimal.valueOf(20.00));
				break;
			case 3:
				pedido = new Hamburguer(BigDecimal.valueOf(40.00));
				break;
			case 4:
				pedido = new BabyBeef(BigDecimal.valueOf(120.00));
				break;
			case 5:
				pedido = new Bauru(BigDecimal.valueOf(37.00));
				break;
			default:
				System.out.println("Opção inválida!");
				return;
		}

		System.out.print("Deseja personalizar o pedido? (s/N): ");
		String resposta = scanner.nextLine();
		
		if (resposta.equalsIgnoreCase("S")) {
			System.out.print("Digite a personalização do pedido: ");
			String personalizacao = scanner.nextLine();
			
			System.out.print("Digite o valor adicional da personalização: ");
			BigDecimal valorAdicional = scanner.nextBigDecimal();
			scanner.nextLine(); // Limpar o buffer do scanner
			
			pedido = new DetalheExtra(pedido, personalizacao, valorAdicional);
		}
		
		pedidos.add(pedido);
		pedidosPorMesa.put(numeroMesa, pedidos);

		System.out.println("Pedido adicionado com sucesso!");
	}

	private static void removerPedido(int numeroMesa) {

		while (true) {
			System.out.println("\n=== REMOVER PEDIDO ===");
			List<Pedido> pedidos = pedidosPorMesa.get(numeroMesa);

			if (pedidos.isEmpty()) {
				System.out.println("Não há pedidos nesta mesa para remover!");
				return;
			}

			System.out.println("Pedidos na mesa " + numeroMesa + ":");
			for (int i = 0; i < pedidos.size(); i++) {
				System.out.println((i + 1) + ". " + pedidos.get(i).getDescricao());
			}

			System.out.print("Escolha o número do pedido que deseja remover (0 para voltar): ");
			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer do scanner

			if (escolha == 0) {
				break;
			} else if (escolha < 1 || escolha > pedidos.size()) {
				System.out.println("Opção inválida!");
			} else {
				Pedido pedidoRemovido = pedidos.remove(escolha - 1);
				System.out.println("Pedido removido com sucesso: " + pedidoRemovido.getDescricao());
			}
		}

		// Voltar ao menu principal da mesa
		exibirMenuPrincipal(numeroMesa);
	}

	private static void exibirPedidos(int numeroMesa) {
		System.out.println("\n=== PEDIDOS DA MESA " + numeroMesa + " ===");
		List<Pedido> pedidos = pedidosPorMesa.get(numeroMesa);

		if (pedidos.isEmpty()) {
			System.out.println("Não há pedidos nesta mesa!");
			return;
		}

		for (Pedido pedido : pedidos) {
			System.out.println("- " + pedido.getDescricao());
			System.out.printf("   %s %.2f%n", "R$", pedido.getCusto());
		}
	}

	private static void entregarPedidos(int numeroMesa) {
		System.out.println("\n=== ENTREGAR PEDIDOS À COZINHA ===");
		List<Pedido> pedidos = pedidosPorMesa.get(numeroMesa);

		if (pedidos.isEmpty()) {
			System.out.println("Nenhum pedido para entregar!");
			return;
		}

		cozinha.receberPedidos(numeroMesa, pedidos);
		pedidos.clear();
	}
}