package stepDefinitions;

import com.clark.page.ecommerce.HomePage;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Quando;

public class Home_Steps {

	HomePage home = new HomePage();

	@Dado("^que seleciono os produtos \"([^\"]*)\"$")
	public void que_seleciono_os_produtos(String opcao) throws Throwable {
		home.deveVerificarProdutos(opcao);
	}

	@Dado("^que retorno para pagina inicial$")
	public void que_retorno_para_pagina_inicial() throws Throwable {
		home.deveClicarPaginaInicial();
	}

	@Quando("^adicionar no carrinho de compras todos os produtos$")
	public void adicionar_no_carrinho_de_compras() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

	@Quando("^adicionar no carrinho de compras os produtos com desconto$")
	public void adicionar_no_carrinho_de_compras_com_desconto() throws Throwable {
		home.deveAdicionarProdutosCarrinhoDeCompras();
	}

}
