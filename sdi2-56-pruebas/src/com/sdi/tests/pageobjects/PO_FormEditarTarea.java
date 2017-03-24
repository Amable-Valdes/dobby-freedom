package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormEditarTarea {

	public void rellenaFormulario(WebDriver driver, String nombre_tarea,
			String comment, String fecha, String idCategoriaSelected) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SeleniumUtils.textoPresentePagina(driver, "Editar tarea");
		
		// Ponemos un comentario a la tarea
		SeleniumUtils.rellenarTextField(driver, "Comment", comment);
		// Seleccionamos del comboBox la categoria que deseemos
		SeleniumUtils.selectComboBox(driver, "Category_label", idCategoriaSelected);
		// Ponemos la fecha planificada
		SeleniumUtils.rellenarTextField(driver, "Planned_input", fecha);
		// Ponemos el título a la tarea
		SeleniumUtils.rellenarTextField(driver, "Title", nombre_tarea);
		// Pulsar el boton de guardar tarea.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SeleniumUtils.esperaCargaPagina(driver, "id", "botonSalvarTarea", 10);
		SeleniumUtils.clickButton(driver, "botonSalvarTarea");
	}
}
