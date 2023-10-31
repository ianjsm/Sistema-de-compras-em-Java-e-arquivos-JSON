package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
		List<Produto> listaProdutos = new ArrayList<>();

		while (continuarComprando) {
			System.out.print("Digite o ID do produto que deseja comprar: ");
			int idDesejado = scan.nextInt();
			File estoque = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json");
	        String dados = Administrador.lerArquivoProdutos(estoque);
	        JSONArray produtos = new JSONArray(dados);

	        boolean idExiste = false;
	        for (int i = 0; i < produtos.length(); i++) {
	            JSONObject produto = produtos.getJSONObject(i);
	            int idProduto = produto.getInt("id");

	            if (idProduto == idDesejado) {
	                idExiste = true;
	                break;
	            }
	        }
			if (idExiste == false) {
			    System.out.println("Não existe um produto com esse ID.");
			    return;
			}
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
						Produto produto = new Produto(idProduto, produtoJSON.getString("nome"), produtoJSON.getString("descricao"), produtoJSON.getDouble("preco"), quantidadeDesejada);
						listaProdutos.add(produto);
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
		
		double valorFinal = 0;
		
		if (formaPagamento == 1) {
			valorFinal = valorTotalCompra;
			System.out.println("Valor total: " + valorFinal);
		} else if (formaPagamento == 2) {
			valorFinal = valorTotalCompra * 0.9;
			System.out.println("Valor total: " + valorFinal);
		} else {
			System.out.println("Escolha uma forma de pagamento válida!");
		}
		System.out.println();
		System.out.print("Digite seu nome: ");
		scan.nextLine();
		String nomeCliente = scan.nextLine();
		
		int id = 0;
		id++;
		Compra compra = new Compra(id, LocalDateTime.now() , nomeCliente, listaProdutos, valorFinal, valorTotalCompra);
		List<Compra> compras = new ArrayList<>();
		compras.add(compra);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		
		JSONArray comprasRealizadas;
		File arquivo = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\compras\\compras.json");

		if (arquivo.exists()) {
		    String comprasJSON = Administrador.lerArquivoProdutos(arquivo);
		    comprasRealizadas = new JSONArray(comprasJSON);
		} else {
		    comprasRealizadas = new JSONArray();
		}

		for (Compra c : compras) {
		    JSONObject compraJSON = new JSONObject();
		    compraJSON.put("idCompra", c.getIdCompra());
		    compraJSON.put("data:", dtf.format(c.getDataHora()));
		    compraJSON.put("nome", c.getNome());

		    JSONArray produtosDaCompra = new JSONArray();
		    for (Produto produto : c.getListaProdutos()) {
		        JSONObject produtoJSON = new JSONObject();
		        produtoJSON.put("nome", produto.getNome());
		        produtoJSON.put("preco", produto.getPreco());
		        produtoJSON.put("quantidade", produto.getQuantidade());
		        produtosDaCompra.put(produtoJSON);
		    }
		    compraJSON.put("listaProdutos", produtosDaCompra);
		    compraJSON.put("precoComDesconto", decimalFormat.format(c.getValorFinal()));
		    compraJSON.put("precoSemDesconto", c.getPrecoSemDesconto());

		    comprasRealizadas.put(compraJSON);
		}

		try (FileWriter writer = new FileWriter(arquivo)) {
		    writer.write(comprasRealizadas.toString(4));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}