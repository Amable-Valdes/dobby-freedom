package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormLogin {

	public void rellenaFormulario(WebDriver driver, String nombre_user, String password_user) {
		
		SeleniumUtils.esperaCargaPagina(driver, "id", "loginForm:user", 10);
		
		//Ponemos el usuario en el textField usuario
		SeleniumUtils.rellenarTextField(driver, "loginForm:user", nombre_user);
		//Ponemos la contraseña en el textField contraseña
		SeleniumUtils.rellenarTextField(driver, "loginForm:pass", password_user);
		// Pulsar el boton de Iniciar Sesion.
		SeleniumUtils.clickButton(driver, "loginForm:enviarUser");
	}
}
