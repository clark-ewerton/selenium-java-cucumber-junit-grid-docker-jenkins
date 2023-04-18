package com.clark.page.ecommerce;

import com.clark.driver.DriverManager;
import com.clark.page.AbstractPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TodosProdutosPage extends AbstractPageObject {

	HomePage homePage;

	public void deveAcessarPaginaTodosOsProdutos(){
		DriverManager.getDriver().get("https://www.carrinhomagico.com.br/collections/all");
	}

	public void deveFinalizarCompra() throws InterruptedException {
		Random rand = new Random();
		int index = rand.nextInt(homePage.webEleList.size());
		WebElement elementoAleatorio = homePage.webEleList.get(index);
		elementoAleatorio.findElement(By.xpath("./../../../..//a")).click();
		esperaElementoParaInteragir(By.cssSelector("span[data-buy-now-text]"));
		clicarElementoViaJavaScript(DriverManager.getDriver().findElement(By.cssSelector("span[data-buy-now-text]")));
		Thread.sleep(10000);
	}
}
