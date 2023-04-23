package com.clark.page.ecommerce;

import com.clark.driver.DriverManager;
import com.clark.page.AbstractPageObject;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TodosProdutosPage extends AbstractPageObject {

	HomePage homePage;
    private static final Logger log = LogManager.getLogger(TodosProdutosPage.class);

	public void deveAcessarPaginaTodosOsProdutos(){
        log.info("Acessando a página de todos os produtos: https://www.carrinhomagico.com.br/collections/all");
		DriverManager.getDriver().get("https://www.carrinhomagico.com.br/collections/all");
	}

	public void deveFinalizarCompra() throws InterruptedException {
		Random rand = new Random();
		int index = rand.nextInt(homePage.webEleList.size());
		WebElement elementoAleatorio = homePage.webEleList.get(index);
        log.info("Clicando em um produto aleatório: " + elementoAleatorio.findElement(By.xpath("./../../../..//a")).getText());
		elementoAleatorio.findElement(By.xpath("./../../../..//a")).click();
		esperaElementoParaInteragir(By.cssSelector("span[data-buy-now-text]"));
		clicarElementoViaJavaScript(DriverManager.getDriver().findElement(By.cssSelector("span[data-buy-now-text]")));
		Thread.sleep(10000);
	}
}
