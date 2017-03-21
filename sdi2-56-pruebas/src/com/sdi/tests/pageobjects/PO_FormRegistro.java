package com.sdi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_FormRegistro {

	public void rellenaFormulario(WebDriver driver, String nombre_user,
			String email, String password_user_1, String password_user_2) {
		
		SeleniumUtils.esperaCargaPagina(driver, "id", "j_idt7:user", 10);
		
		// Ponemos el usuario en el textField usuario
		SeleniumUtils.rellenarTextField(driver, "j_idt7:user", nombre_user);
		// Ponemos el email en el textField email
		SeleniumUtils.rellenarTextField(driver, "j_idt7:email", email);
		// Ponemos la contraseña en el textField contraseña
		SeleniumUtils.rellenarTextField(driver, "j_idt7:pass", password_user_1);
		// Ponemos la contraseña repetida en el textField repetir contraseña
		SeleniumUtils.rellenarTextField(driver, "j_idt7:passRew",
				password_user_2);
		// Pulsar el boton de Registrarse.
		SeleniumUtils.clickButton(driver, "j_idt7:enviarUser");
	}
}
