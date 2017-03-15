package uo.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class InternationalizationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Locale SPANISH = new Locale("es");
	private static final Locale ENGLISH = new Locale("en");

	private Locale locale = new Locale("es");

	public Locale getLocale() {
		//El lenguaje por defecto sera el castellano
		return locale;
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
