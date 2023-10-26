package util;

public class Tela {
	public static void telaInicialAdmin() {
		System.out.println("===== MENU ADMIN =====");
		System.out.println();
		System.out.println("1- CADASTRAR PRODUTO");
		System.out.println("2- EDITAR PRODUTO");
		System.out.println("3- REMOVER PRODUTO");
		System.out.println("4- LISTAR COMPRAS REALIZADAS");
		System.out.println("5- VOLTAR AO MENU ANTERIOR");
		System.out.print("Digite a opção desejada: ");
	}
	
	public static void telaInicialComprador() {
		System.out.println("===== MENU COMPRADOR =====");
		System.out.println();
		System.out.println("1- REALIZAR COMPRA");
		System.out.println("2- VOLTAR AO MENU ANTERIOR");
		System.out.print("Digite a opção desejada: ");
	}
}