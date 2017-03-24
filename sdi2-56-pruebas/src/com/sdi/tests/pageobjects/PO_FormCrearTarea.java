package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormCrearTarea {

	public void rellenaFormulario(WebDriver driver, String nombre_tarea,
			String comment, String fecha, String idCategoriaSelected) {
		
		SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/form[2]/table/tbody/tr[1]/td[2]/input", 10);
		
		// Ponemos un comentario a la tarea
		SeleniumUtils.rellenarTextField(driver, "Comment", comment);
		// Seleccionamos del comboBox la categoria que deseemos
		List<WebElement> elementos = SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/form[2]/table/tbody/tr[1]/td[2]/input", 4);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(nombre_tarea);
		WebElement hoverElement = driver.findElement(By.xpath("/html/body/form[2]/table/tbody/tr[4]/td[2]/div/div[3]"));
		hoverElement.click();
		By locator = By.xpath("/html/body/div[3]/div/ul/li[2]");
		driver.findElement(locator).click();
		// Ponemos la fecha planificada
		if(fecha!=null)
			SeleniumUtils.rellenarTextField(driver, "Planned_input", fecha);

		// Pulsar el boton de guardar tarea.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/form[2]/div/button", 10).get(0).click();;
		//SeleniumUtils.clickButton(driver, "j_idt7:form-registro:botonSalvarTarea");
	}
}
