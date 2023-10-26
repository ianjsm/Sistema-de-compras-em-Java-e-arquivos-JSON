package entities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class Produto {
	protected int id;
	protected String nome;
	protected String descricao;
	protected double preco;
	protected int quantidade;
	
	public Produto(int id, String nome, String descricao, double preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public static void adicionarProduto() {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Digite o id do produto: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.print("Digite o nome do produto: ");
        String nome = scan.nextLine();
        System.out.print("Digite a descrição do produto: ");
        String descricao = scan.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scan.nextDouble();
        System.out.print("Digite a quantidade do produto: ");
        int quantidade = scan.nextInt();
        
        Produto produto = new Produto(id, nome, descricao, preco, quantidade);
        JSONObject jsonObject = new JSONObject(produto);

        try (FileWriter file = new FileWriter("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json", true)) {
            file.write(jsonObject.toString()); 
            System.out.println("Produto adicionado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}