package com.clark.page.ecommerce;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clark.driver.DriverManager;
import com.clark.page.AbstractPageObject;

public class CarrinhoComprasPage extends AbstractPageObject {
	
    private static final Logger log = LogManager.getLogger(CarrinhoComprasPage.class);

	public void deveFinalizarCompra() throws InterruptedException {
        log.info("Iniciando o processo de finalização de compra.");
		procurarElemento(By.xpath("//a[@title='View my shopping cart']")).click();
		procurarElemento(By.xpath("//p/a[@title='Proceed to checkout']")).click();
		try {
			if (new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(2, 1))
					.until(ExpectedConditions.visibilityOf(procurarElemento(By.id("email")))).isDisplayed()) {
				preencherCampo(By.id("email"), "clarkewerton@hotmail.com");
				preencherCampo(By.id("passwd"), "teste123");
				procurarElemento(By.id("SubmitLogin")).click();
				procurarElemento(By.name("processAddress")).click();
				procurarElemento(By.id("cgv")).click();
				procurarElemento(By.name("processCarrier")).click();
				procurarElemento(By.className("bankwire")).click();
				procurarElemento(By.xpath("//p/button")).click();
			}
		} catch (Exception e) {
            log.warn("Falha ao realizar o login. Continuando o processo de finalização de compra.", e);
			procurarElemento(By.name("processAddress")).click();
			procurarElemento(By.id("cgv")).click();
			procurarElemento(By.name("processCarrier")).click();
			procurarElemento(By.className("bankwire")).click();
			procurarElemento(By.xpath("//p/button")).click();
		}
        log.info("Finalizando o processo de finalização de compra.");
	}

}
