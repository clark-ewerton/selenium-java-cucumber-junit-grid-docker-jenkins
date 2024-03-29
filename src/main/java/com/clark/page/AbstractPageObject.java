/*
 * MIT License
 *
 * Copyright (c) 2018 Elias Nogueira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.clark.page;

import java.time.Duration;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clark.driver.DriverManager;

public class AbstractPageObject {

    private static final Logger log = LogManager.getLogger(AbstractPageObject.class);
	protected static WebDriverWait wait;

	protected AbstractPageObject() {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public WebElement procurarElemento(By elemento) {
		log.info("Procurando elemento: " + elemento);
		return DriverManager.getDriver().findElement(elemento);
	}

	public void preencherCampo(By elemento, CharSequence... texto) {
        log.info("Preenchendo campo: " + elemento + " com texto: " + Arrays.toString(texto));
		procurarElemento(elemento).clear();
		procurarElemento(elemento).sendKeys(texto);
	}

	public Boolean esperaElementoParaInteragir(By locator) {
        log.info("Esperando por elemento: " + locator);
		Boolean elementExists = false;
		Duration tempo = Duration.ofSeconds(10, 10);
		wait = new WebDriverWait(DriverManager.getDriver(), tempo);
		// wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (wait.until(ExpectedConditions.elementToBeClickable(locator)).isDisplayed()) {
			elementExists = true;
		}

		return elementExists;

	}

	public void clicarElementoViaJavaScript(WebElement element){
        log.info("Clicando no elemento via JavaScript: " + element);
		JavascriptExecutor executor = (JavascriptExecutor)DriverManager.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
}
