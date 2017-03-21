package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Login {

	public void rellenaFormulario(WebDriver driver, String nombre_user, String password_user) {
		//Ponemos el usuario en el textField usuario
		WebElement nombre = driver.findElement(By.id("loginForm:user"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(nombre_user);
		//Ponemos la contraseña en el textField contraseña
		WebElement iduser = driver.findElement(By.id("loginForm:pass"));
		iduser.click();
		iduser.clear();
		iduser.sendKeys(password_user);
		// Pulsar el boton de Iniciar Sesion.
		By boton = By.id("loginForm:enviarUser");
		driver.findElement(boton).click();
	}
}
