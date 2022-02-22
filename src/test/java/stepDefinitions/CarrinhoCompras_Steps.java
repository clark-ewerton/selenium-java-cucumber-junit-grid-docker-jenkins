package stepDefinitions;

import com.clark.page.ecommerce.CarrinhoComprasPage;

import cucumber.api.java.pt.Entao;

public class CarrinhoCompras_Steps {

	CarrinhoComprasPage carrinhoCompras = new CarrinhoComprasPage();

	@Entao("^realizo o checkout dos produtos$")
	public void realizo_o_checkout_dos_produtos() throws Throwable {
		carrinhoCompras.deveFinalizarCompra();
	}

}
