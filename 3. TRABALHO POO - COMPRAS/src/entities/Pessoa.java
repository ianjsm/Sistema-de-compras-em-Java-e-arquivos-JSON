package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pessoa {
	protected String tipo;

	public Pessoa(String tipo) {
		this.tipo = tipo;
	}

	public static void visualizarEstoque() {
	    File file = new File("C:\\Users\\ianjo\\OneDrive\\Área de Trabalho\\POO\\json\\produtos\\produtos.json");

	    try {
	        String estoqueJSON = Administrador.lerArquivoProdutos(file);
	        JSONArray jsonArray = new JSONArray(estoqueJSON);

	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject produto = jsonArray.getJSONObject(i);
	            int idProduto = produto.getInt("id");
	            String nomeProduto = produto.getString("nome");
	            String descricaoProduto = produto.getString("descricao");
	            double precoProduto = produto.getDouble("preco");
	            int quantidadeProduto = produto.getInt("quantidade");

	            System.out.println();
	            System.out.println("ID do Produto: " + idProduto);
	            System.out.println("Nome do Produto: " + nomeProduto);
	            System.out.println("Descrição do Produto: " + descricaoProduto);
	            System.out.println("Preço do Produto: " + precoProduto);
	            System.out.println("Quantidade em Estoque: " + quantidadeProduto);
	            System.out.println();
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	}
}