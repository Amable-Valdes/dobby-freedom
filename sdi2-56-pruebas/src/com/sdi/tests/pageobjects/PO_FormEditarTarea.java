package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormEditarTarea {

	public void rellenaFormulario(WebDriver driver, String nombre_tarea,
			String comment, String fecha, String idCategoriaSelected) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(SeleniumUtils.existenTextosPagina(driver, "Editar tarea"));

		// Ponemos un comentario a la tarea
		SeleniumUtils.rellenarTextField(driver, "Comment", comment);
		// Seleccionamos del comboBox la categoria que deseemos
		// Ponemos el título a la tarea
		List<WebElement> elementos = SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/form[2]/table/tbody/tr[1]/td[2]/input", 4);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(nombre_tarea);
		
		SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/form[2]/table/tbody/tr[4]/td[2]/div/div[3]", 4);
		
		WebElement hoverElement = driver.findElement(By.xpath("/html/body/form[2]/table/tbody/tr[4]/td[2]/div/div[3]"));
		hoverElement.click();
		By locator = By.xpath("/html/body/div[3]/div/ul/li[3]");
		driver.findElement(locator).click();	
		
		// Pulsar el boton de guardar tarea.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SeleniumUtils.esperaCargaPagina(driver, "id", "form-editarTarea:botonSalvarTarea", 10);
		SeleniumUtils.clickButton(driver, "form-editarTarea:botonSalvarTarea");
	}
}
