package com.clark.page.ecommerce;

import java.util.List;

import org.junit.Assert;
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

	public void deveAdicionarProdutosCarrinhoDeCompras() {
		for (WebElement item : webEleList) {
			try {
				if (esperaElementoParaInteragir(By.xpath("//div//ul[@id='homefeatured']//div[@class='right-block']"))) {
					item.findElement(By.xpath("../..//a[@title='Add to cart']")).click();
					procurarElemento(By.xpath("//span[@title='Close window']")).click();
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("It wasn't possible to locate element");
			}

		}

	}

	public void deveClicarPaginaInicial() {
		procurarElemento(By.id("header_logo")).click();
	}

}
