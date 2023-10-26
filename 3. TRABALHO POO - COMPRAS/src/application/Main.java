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
		
		while(opcao > 2 || opcao < 1) {
			System.out.println("1- ADMIN");
			System.out.println("2- COMPRADOR");
			System.out.println("Admin ou comprador?");
			opcao = scan.nextInt();
		}
		
		if(opcao == 1) {
			pessoa = new Administrador(null);
			Tela.telaInicialAdmin();
			int opcao_submenu_admin = scan.nextInt();
			
			if(opcao_submenu_admin == 1) {
				Produto.adicionarProduto();
			}
			else if(opcao_submenu_admin == 2) {
				//editar produto
			}
			else if(opcao_submenu_admin == 3) {
				//remov produto
			}
			else if(opcao_submenu_admin == 4) {
				//listar compras
			}
			else {
				System.out.println("Opção inválida!");
			}
		}
		else if(opcao == 2) {
			pessoa = new Comprador(null);
			Tela.telaInicialComprador();
			int opcao_submenu_comprador = scan.nextInt();
		}
		scan.close();
	}
}