package application;

import java.util.Scanner;

import entities.Administrador;
import entities.Comprador;
import entities.Pessoa;
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
		}
		else if(opcao == 2) {
			pessoa = new Comprador(null);
			Tela.telaInicialComprador();
			int opcao_submenu_comprador = scan.nextInt();
		}
		scan.close();
	}
}