package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Comprador extends Pessoa {

	public Comprador(String tipo) {
		super(tipo);
	}

	public static void comprarProduto() {
		Scanner scan = new Scanner(System.in);
		JSONArray carrinho = new JSONArray();
		double valorTotalCompra = 0;
		boolean continuarComprando = true;

		while (continuarComprando) {
			System.out.print("Digite o ID do produto que deseja comprar: ");
			int idDesejado = scan.nextInt();
			System.out.print("Digite a quantidade desejada: ");
			int quantidadeDesejada = scan.nextInt();

			File estoqueFile = new File(
					"C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json");
			String dadosJSON = Administrador.lerArquivoProdutos(estoqueFile);

			JSONArray listaProdutosJSON = new JSONArray(dadosJSON);

			for (int i = 0; i < listaProdutosJSON.length(); i++) {
				JSONObject produtoJSON = listaProdutosJSON.getJSONObject(i);
				int idProduto = produtoJSON.getInt("id");
				int quantidadeDisponivel = produtoJSON.getInt("quantidade");

				if (idProduto == idDesejado) {
					if (quantidadeDesejada <= quantidadeDisponivel) {
						JSONObject produtoCarrinho = new JSONObject();
						produtoCarrinho.put("id", idProduto);
						produtoCarrinho.put("nome", produtoJSON.getString("nome"));
						produtoCarrinho.put("quantidade", quantidadeDesejada);
						produtoCarrinho.put("preco", produtoJSON.getDouble("preco"));
						carrinho.put(produtoCarrinho);
						System.out.println("Produto adicionado ao carrinho de compras!");
					} else {
						System.out.println("Quantidade desejada maior do que a disponível.");
						return;
					}
				}
			}
			System.out.print("Deseja adicionar outro produto? (s/n): ");
			char resposta = scan.next().charAt(0);
			if (resposta == 'n') {
				continuarComprando = false;
			}
		}

		try (FileWriter writer = new FileWriter(
				new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\carrinho\\carrinho.json"))) {
			writer.write(carrinho.toString(4));
			System.out.println("Carrinho salvo com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		File estoqueFile = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json");
		String dadosJSON = Administrador.lerArquivoProdutos(estoqueFile);
		JSONArray estoqueJSON = new JSONArray(dadosJSON);

		for (int i = 0; i < carrinho.length(); i++) {
			JSONObject produtoCarrinho = carrinho.getJSONObject(i);
			int idProdutoCarrinho = produtoCarrinho.getInt("id");
			int quantidadeCarrinho = produtoCarrinho.getInt("quantidade");

			for (int j = 0; j < estoqueJSON.length(); j++) {
				JSONObject produtoEstoque = estoqueJSON.getJSONObject(j);
				int idProdutoEstoque = produtoEstoque.getInt("id");

				if (idProdutoCarrinho == idProdutoEstoque) {
					int quantidadeEstoque = produtoEstoque.getInt("quantidade");
					produtoEstoque.put("quantidade", quantidadeEstoque - quantidadeCarrinho);
					break;
				}
			}
		}

		try (FileWriter estoqueWriter = new FileWriter(estoqueFile)) {
			estoqueWriter.write(estoqueJSON.toString(4));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < carrinho.length(); i++) {
			JSONObject produtoCarrinho = carrinho.getJSONObject(i);
			System.out.print("nome: ");
			System.out.println(produtoCarrinho.getString("nome"));
			System.out.print("quantidade no carrinho: ");
			System.out.println(produtoCarrinho.getInt("quantidade"));
			System.out.print("preço: ");
			System.out.println(produtoCarrinho.getDouble("preco"));
			int qntCarrinho = produtoCarrinho.getInt("quantidade");
			valorTotalCompra += qntCarrinho * produtoCarrinho.getDouble("preco");
		}

		System.out.println();
		System.out.print("Preço total da compra: " + valorTotalCompra);
		System.out.println();
		System.out.println("FORMAS DE PAGAMENTO DISPONÍVEIS:");
		System.out.println("1- CRÉDITO");
		System.out.println("2- DÉBITO (10% DE DESCONTO)");
		System.out.println();
		System.out.println("Qual a forma de pagamento desejada?");
		System.out.print("Pagamento escolhido: ");
		int formaPagamento = scan.nextInt();

		if (formaPagamento == 1) {
			double valorFinal = valorTotalCompra;
			System.out.println("Valor total: " + valorFinal);
		} else if (formaPagamento == 2) {
			double valorFinal = valorTotalCompra * 0.9;
			System.out.println("Valor total: " + valorFinal);
		} else {
			System.out.println("Escolha uma forma de pagamento válida!");
		}
	}
}

/*
 * public static void comprarProduto() { Scanner scan = new Scanner(System.in);
 * JSONArray carrinho = new JSONArray();
 * 
 * System.out.print("Digite o ID do produto que deseja comprar: "); int
 * idDesejado = scan.nextInt();
 * System.out.print("Digite a quantidade desejada: "); int quantidadeDesejada =
 * scan.nextInt();
 * 
 * File estoqueFile = new
 * File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json"
 * ); String dadosJSON = Administrador.lerArquivoProdutos(estoqueFile);
 * 
 * JSONArray listaProdutosJSON = new JSONArray(dadosJSON);
 * 
 * for (int i = 0; i < listaProdutosJSON.length(); i++) { JSONObject produtoJSON
 * = listaProdutosJSON.getJSONObject(i); int idProduto =
 * produtoJSON.getInt("id"); int quantidadeDisponivel =
 * produtoJSON.getInt("quantidade");
 * 
 * if (idProduto == idDesejado) { if (quantidadeDesejada <=
 * quantidadeDisponivel) { JSONObject produtoCarrinho = new JSONObject();
 * produtoCarrinho.put("id", idProduto); produtoCarrinho.put("nome",
 * produtoJSON.getString("nome")); produtoCarrinho.put("quantidade",
 * quantidadeDesejada); produtoCarrinho.put("preco",
 * produtoJSON.getDouble("preco")); carrinho.put(produtoJSON);
 * System.out.println("Produto adicionado ao carrinho de compras!"); } else {
 * System.out.println("Quantidade desejada maior do que a disponível."); return;
 * } } }
 * 
 * try (FileWriter writer = new FileWriter( new
 * File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\carrinho\\carrinho.json"
 * ))) { writer.write(carrinho.toString(4));
 * System.out.println("Carrinho salvo com sucesso!"); } catch (IOException e) {
 * e.printStackTrace(); } }
 * 
 * public static void finalizarCompra() { Scanner scan = new Scanner(System.in);
 * double valorTotalCompra = 0;
 * 
 * File fileCarrinho = new
 * File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\carrinho\\carrinho.json"
 * ); File fileEstoque = new
 * File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json"
 * ); String carrinhoJSON = Administrador.lerArquivoProdutos(fileCarrinho);
 * String estoqueJSON = Administrador.lerArquivoProdutos(fileEstoque); JSONArray
 * listaProdutosCarrinho = new JSONArray(carrinhoJSON); JSONArray
 * listaProdutosEstoque = new JSONArray(estoqueJSON); for (int i = 0; i <
 * listaProdutosCarrinho.length(); i++) { JSONObject produtoCarrinho =
 * listaProdutosCarrinho.getJSONObject(i); System.out.print("nome: ");
 * System.out.println(produtoCarrinho.getString("nome"));
 * System.out.print("quantidade: ");
 * System.out.println(produtoCarrinho.getInt("quantidade"));
 * System.out.print("preço: ");
 * System.out.println(produtoCarrinho.getDouble("preco")); int qntCarrinho =
 * produtoCarrinho.getInt("quantidade"); for (int j = 0; j <
 * listaProdutosEstoque.length(); j++) { JSONObject produtoEstoque =
 * listaProdutosEstoque.getJSONObject(j); if
 * (produtoCarrinho.getString("nome").equalsIgnoreCase(produtoEstoque.getString(
 * "nome"))) { int novaQntEstoque = produtoEstoque.getInt("quantidade") -
 * qntCarrinho; produtoEstoque.put("quantidade", novaQntEstoque); double valor =
 * produtoEstoque.getDouble("preco"); double precoTotalDoProduto = qntCarrinho *
 * valor; valorTotalCompra += precoTotalDoProduto; break; } } }
 * 
 * try (FileWriter writer = new FileWriter(fileEstoque)) {
 * writer.write(listaProdutosEstoque.toString(4)); } catch (IOException e) {
 * e.printStackTrace(); }
 * 
 * System.out.println(); System.out.print("Preço total da compra: " +
 * valorTotalCompra); System.out.println();
 * System.out.println("FORMAS DE PAGAMENTO DISPONÍVEIS:");
 * System.out.println("1- CRÉDITO");
 * System.out.println("2- DÉBITO (10% DE DESCONTO)"); System.out.println();
 * System.out.println("Qual a forma de pagamento desejada?");
 * System.out.print("Pagamento escolhido: "); int formaPagamento =
 * scan.nextInt();
 * 
 * if(formaPagamento == 1) { double valorFinal = valorTotalCompra;
 * System.out.println("Valor total: " + valorFinal); } else if(formaPagamento ==
 * 2) { double valorFinal = valorTotalCompra*0.9;
 * System.out.println("Valor total: " + valorFinal); } else {
 * System.out.println("Escolha uma forma de pagamento válida!"); }
 * 
 * //FALTA DEBITAR OS PRODUTOS DO ESTOQUE!!!!!!!!!!!! }
 */