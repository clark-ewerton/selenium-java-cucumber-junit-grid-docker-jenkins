package com.clark.stepDefinitions;

import com.clark.page.ecommerce.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Home_Steps {

	HomePage home = new HomePage();

	@Given("que seleciono os produtos {string}")
	public void que_seleciono_os_produtos(String opcao) {
		home.deveVerificarProdutos(opcao);
	}

	@Given("^que retorno para pagina inicial$")
	public void que_retorno_para_pagina_inicial() throws Throwable {
		home.deveClicarPaginaInicial();
	}

	@When("^adicionar no carrinho de compras todos os produtos$")
	public void adicionar_no_carrinho_de_compras() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

	@When("^adicionar no carrinho de compras os produtos com desconto$")
	public void adicionar_no_carrinho_de_compras_com_desconto() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

}
