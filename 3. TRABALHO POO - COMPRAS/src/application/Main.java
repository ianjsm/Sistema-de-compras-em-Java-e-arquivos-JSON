package application;

import java.util.Scanner;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import entities.Administrador;
import entities.Comprador;
import entities.Pessoa;
import entities.Produto;
import util.Tela;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Pessoa pessoa;
		int opcao = 0;

		while (opcao > 2 || opcao < 1) {
			System.out.println("1- ADMIN");
			System.out.println("2- COMPRADOR");
			System.out.println("Admin ou comprador?");
			opcao = scan.nextInt();
			scan.nextLine();
		}

		if (opcao == 1) {

			pessoa = new Administrador(null);

			String senha;
			boolean senhaCorreta = false;

			while (!senhaCorreta) {
				System.out.println("Digite a senha: ");
				senha = scan.nextLine();

				if (senha.equals("admpoo")) {
					senhaCorreta = true;
				} else {
					System.out.println("Senha incorreta! Tente novamente.");
				}
			}

			Tela.telaInicialAdmin();
			int opcao_submenu_admin = scan.nextInt();

			if (opcao_submenu_admin == 1) {
				Pessoa.visualizarProdutos();
			} else if (opcao_submenu_admin == 2) {
				Administrador.adicionarProduto();
			} else if (opcao_submenu_admin == 3) {
				Administrador.editarProduto();
			} else if (opcao_submenu_admin == 4) {
				// remover produto
			} else if (opcao_submenu_admin == 5) {
				// listar compras realizadas
			} else if (opcao_submenu_admin == 6) {
				// retornar ao menu inicial
			} else {
				System.out.println("Opção inválida!");
			}
		} else if (opcao == 2) {
			pessoa = new Comprador(null);
			Tela.telaInicialComprador();
			int opcao_submenu_comprador = scan.nextInt();
		}
		scan.close();
	}
}