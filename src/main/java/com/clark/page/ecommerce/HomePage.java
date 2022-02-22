package com.clark.page.ecommerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.clark.driver.DriverManager;
import com.clark.page.AbstractPageObject;

public class HomePage extends AbstractPageObject {

	List<WebElement> webEleList;

	public List<WebElement> deveVerificarProdutos(String opcao) {
		WebElement element = DriverManager.getDriver().findElement(By.tagName("div"));
		switch (opcao) {
		case "todos":
			webEleList = element.findElements(By.xpath(".//ul[@id='homefeatured']//div[@class='right-block']"));
			break;

		case "desconto":
			webEleList = element.findElements(By.xpath(
					".//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price-percent-reduction']"));

		default:
			System.err.println("Opcao nao encontrada!");
			break;
		}

		return webEleList;

	}

	public void deveAdicionarProdutosComDescontoCarrinhoDeCompras() throws InterruptedException {
		for (WebElement item : webEleList) {
			item.findElement(By.xpath("../..//a[@title='Add to cart']")).click();
			procurarElemento(By.xpath("//span[@title='Close window']")).click();
		}
	}

	public void deveAdicionarTodosProdutosCarrinhoDeCompras() throws InterruptedException {
		for (WebElement item : webEleList) {
			item.findElement(By.xpath("../..//a[@title='Add to cart']")).click();
			procurarElemento(By.xpath("//span[@title='Close window']")).click();
			Thread.sleep(1000);

		}
	}

	public void deveClicarPaginaInicial() {
		procurarElemento(By.id("header_logo")).click();
	}

}
