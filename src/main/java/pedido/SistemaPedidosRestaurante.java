package pedido;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;

import menu.*;
import cozinha.Cozinha;

public class SistemaPedidosRestaurante {

	private static Scanner scanner = new Scanner(System.in);
	private static Cozinha cozinha = new Cozinha();
	private static Map<Integer, List<Pedido>> pedidosPorMesa = new HashMap<Integer, List<Pedido>>();

	public static void main(String[] args) {
		int opcao;
		do {
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
				case 0:
					System.out.println("Saindo do sistema...");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);
	}
	private static boolean continuarExibindoMenu;

	private static void setContinuarExibindoMenu(boolean continuar) {
		continuarExibindoMenu = continuar;
	}

	private static boolean isContinuarExibindoMenu() {
		return continuarExibindoMenu;
	}

	private static void exibirMenuInicial() {
		System.out.println("\n=== ESCOLHA ===");
		System.out.println("1. Adicionar mesa");
		System.out.println("2. Remover mesa");
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
		setContinuarExibindoMenu(true); // Definir para continuar exibindo o menu
	}

	private static void adicionarMesa() {
		System.out.println("\n=== ADICIONAR MESA ===");
		System.out.print("Digite o número da mesa: ");
		int numeroMesa = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer do scanner

		if (pedidosPorMesa.containsKey(numeroMesa)) {
			System.out.println("Mesa já existente!");
			return;
		}

		pedidosPorMesa.put(numeroMesa, new ArrayList<Pedido>());
		System.out.println("Mesa adicionada com sucesso!");
		exibirMenuPrincipal(numeroMesa);
	}

	private static void removerMesa() {
		System.out.println("\n=== REMOVER MESA ===");
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
		setContinuarExibindoMenu(true); // Definir para continuar exibindo o menu

		while (isContinuarExibindoMenu()) { // Usar o método isContinuarExibindoMenu
			System.out.println("\n=== MENU PRINCIPAL - MESA " + numeroMesa + " ===");
			System.out.println("1. Adicionar pedido");
			System.out.println("2. Remover pedido");
			System.out.println("3. Entregar pedidos à cozinha");
			System.out.println("0. Voltar ao menu inicial");
			System.out.print("Escolha uma opção: ");
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
					setContinuarExibindoMenu(false); // Definir para não continuar exibindo o menu
					entregarPedidos(numeroMesa);
					break;
				case 0:
					setContinuarExibindoMenu(false); // Definir para não continuar exibindo o menu
					exibirMenuInicial();
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
			}

			if (opcao == 0 || opcao == 3) {
				setContinuarExibindoMenu(false); // Definir para não continuar exibindo o menu
			}
		}
	}

	private static void adicionarPedido(int numeroMesa) {
		System.out.println("\n=== ADICIONAR PEDIDO ===");
		List<Pedido> pedidos = pedidosPorMesa.getOrDefault(numeroMesa, new ArrayList<Pedido>());

		Scanner scanner = new Scanner(System.in);

		System.out.println("Escolha o item:");
		System.out.println("1. Pizza - R$14.00");
		System.out.println("2. Salada - R$20.00");
		System.out.println("3. Hamburguer - R$40.00");
		System.out.println("4. Baby Beef - R$120.00");
		System.out.println("5. Bauru - R$37.00");
		System.out.print("Escolha uma opção: ");
		int escolha = scanner.nextInt();
		scanner.nextLine(); // Limpar o buffer do scanner

		Pedido pedido;
		switch (escolha) {
			case 1:
				pedido = new Pizza("Pizza", 14.0);
				break;
			case 2:
				pedido = new Salada("Salada", 20.0);
				break;
			case 3:
				pedido = new Hamburguer("Hamburguer", 40.0);
				break;
			case 4:
				pedido = new BabyBeef("Baby Beef", 120.0);
				break;
			case 5:
				pedido = new Bauru("Bauru", 37.0);
				break;
			default:
				System.out.println("Opção inválida!");
				scanner.nextLine();
				return;
		}

		System.out.print("Deseja personalizar o pedido? (S/N): ");
		String resposta = scanner.nextLine();
		if (resposta.equalsIgnoreCase("S")) {
			System.out.print("Digite a personalização do pedido: ");
			String personalizacao = scanner.nextLine();
			System.out.print("Digite o valor adicional da personalização: ");
			double valorAdicional = scanner.nextDouble();
			scanner.nextLine(); // Limpar o buffer do scanner
			pedido = new DetalheExtra(pedido, personalizacao, valorAdicional);
		}

		pedidos.add(pedido);
		pedidosPorMesa.put(numeroMesa, pedidos);

		System.out.println("Pedido adicionado com sucesso!");

		// Voltar ao menu principal da mesa
		exibirMenuPrincipal(numeroMesa);
	}

	private static void removerPedido(int numeroMesa) {
		Scanner scanner = new Scanner(System.in);
		boolean continuarRemovendo = true;

		while (continuarRemovendo) {
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

			System.out.print("Escolha o número do pedido que deseja remover (0 para voltar ao menu principal): ");
			int escolha = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer do scanner

			if (escolha == 0) {
				continuarRemovendo = false;
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


	private static void entregarPedidos(int numeroMesa) {
		System.out.println("\n=== ENTREGAR PEDIDOS À COZINHA ===");
		List<Pedido> pedidos = pedidosPorMesa.get(numeroMesa);

		if (pedidos.isEmpty()) {
			System.out.println("Nenhum pedido para entregar!");
			return;
		}

		cozinha.receberPedidos(numeroMesa, pedidos);
		pedidos.clear();
		System.out.println("Pedidos entregues à cozinha!");
	}
}