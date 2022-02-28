package com.clark.stepDefinitions;

import com.clark.page.ecommerce.HomePage;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class Home_Steps {

	HomePage home = new HomePage();

	@Dado("^que seleciono os produtos \"([^\"]*)\"$")
	public void que_seleciono_os_produtos(String opcao) {
		home.deveVerificarProdutos(opcao);
	}

	@Quando("^que retorno para pagina inicial$")
	public void que_retorno_para_pagina_inicial() throws Throwable {
		home.deveClicarPaginaInicial();
	}

	@Quando("^adicionar no carrinho de compras todos os produtos$")
	public void adicionar_no_carrinho_de_compras() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

	@Entao("^adicionar no carrinho de compras os produtos com desconto$")
	public void adicionar_no_carrinho_de_compras_com_desconto() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

}
