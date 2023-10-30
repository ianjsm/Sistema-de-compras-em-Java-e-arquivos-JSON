package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Compra {
	protected int idCompra;
	protected LocalDateTime dataHora;
	protected String nome;
	protected List<Produto> listaProdutos;
	protected double precoComDesconto;
	protected double precoSemDesconto;
	private static int proximoId = 1;
	
	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public double getPrecoComDesconto() {
		return precoComDesconto;
	}

	public void setPrecoComDesconto(double precoComDesconto) {
		this.precoComDesconto = precoComDesconto;
	}

	public double getPrecoSemDesconto() {
		return precoSemDesconto;
	}

	public void setPrecoSemDesconto(double precoSemDesconto) {
		this.precoSemDesconto = precoSemDesconto;
	}

	public static int getProximoId() {
		return proximoId;
	}

	public static void setProximoId(int proximoId) {
		Compra.proximoId = proximoId;
	}

	public Compra(int idCompra, LocalDateTime dataHora, String nome, List<Produto> listaProdutos, double precoComDesconto, double precoSemDesconto) {
		this.idCompra = idCompra;
		proximoId++;
		this.dataHora = dataHora;
		this.nome = nome;
		this.listaProdutos = listaProdutos;
		this.precoComDesconto = precoComDesconto;
		this.precoSemDesconto = precoSemDesconto;
	}
}