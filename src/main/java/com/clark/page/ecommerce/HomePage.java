package com.clark.page.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.clark.driver.DriverManager;
import com.clark.page.AbstractPageObject;

public class HomePage extends AbstractPageObject {

	static List<WebElement> webEleList;

	public List<WebElement> deveVerificarProdutos(String opcao) {
		//WebElement element = DriverManager.getDriver().findElement(By.tagName("div"));
		switch (opcao) {
		case "semdesconto":
			//webEleList = element.findElements(By.xpath(".//ul[@id='homefeatured']//div[@class='right-block']"));

			webEleList = DriverManager.getDriver().findElements(By.xpath("//div[@class='discount']/p[starts-with(text(),'-')]"));
			for (WebElement elemento : webEleList) {
				System.out.println("Produto sem desconto: " + elemento.findElement(By.xpath("./../../../..//a")).getText());
				System.out.println("Desconto: " + elemento.getText());
			}
			break;

		case "desconto":
			//webEleList = element.findElements(By.xpath(
			//		".//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price-percent-reduction']"));

			webEleList = DriverManager.getDriver().findElements(By.xpath("//div[@class='discount']/p[not(starts-with(text(),'-'))]\n"));
			for (WebElement elemento : webEleList) {
				System.out.println("Produto com desconto: " + elemento.findElement(By.xpath("./../../../..//a")).getText());
				System.out.println("Desconto: " + elemento.getText());
			}
			break;

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
