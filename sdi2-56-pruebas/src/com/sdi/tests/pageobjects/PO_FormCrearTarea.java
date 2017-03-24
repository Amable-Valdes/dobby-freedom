package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormCrearTarea {

	public void rellenaFormulario(WebDriver driver, String nombre_tarea,
			String comment, String fecha, String idCategoriaSelected) {
		
		SeleniumUtils.esperaCargaPagina(driver, "id", "j_idt7:form-registro:Title", 10);
		
		// Ponemos un comentario a la tarea
		SeleniumUtils.rellenarTextField(driver, "j_idt7:form-registro:Comment", comment);
		// Seleccionamos del comboBox la categoria que deseemos
		SeleniumUtils.selectComboBox(driver, "j_idt7:form-registro:Category_label", idCategoriaSelected);
		// Ponemos la fecha planificada
		SeleniumUtils.rellenarTextField(driver, "j_idt7:form-registro:Planned_input", fecha);
		// Ponemos el título a la tarea
		SeleniumUtils.rellenarTextField(driver, "j_idt7:form-registro:Title", nombre_tarea);
		// Pulsar el boton de guardar tarea.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SeleniumUtils.esperaCargaPagina(driver, "id", "j_idt7:form-registro:botonSalvarTarea", 10);
		SeleniumUtils.clickButton(driver, "j_idt7:form-registro:botonSalvarTarea");
	}
}
