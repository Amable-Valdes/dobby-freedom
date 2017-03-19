package uo.sdi.presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean(name = "settings")
@ApplicationScoped
public class SettingsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Locale> locales;
	private Map<String, String> idiomas;
	private List<String> nButtons;
	private Locale locale;
	private String option;

	public SettingsBean() {
		locale = new Locale("es");
		setIdiomas(new HashMap<String, String>());
		setnButtons(new ArrayList<String>());
		setLocales(new ArrayList<Locale>());
//		search("C:/Users/admin/Desktop/dobby-freedom/LopezCarlos-ValdesAmable/src");
		search("S:/work/LopezCarlos-ValdesAmable/src");
	}

	public Map<String, String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Map<String, String> idiomas) {
		this.idiomas = idiomas;
	}

	public void search(String folderpath) {
		File directory = new File(folderpath);
		for (File element : directory.listFiles()) {
			if (element.getName().contains("messages")) {
				chargeButton(element);
			}
		}
	}

	private void chargeButton(File file) {
		String[] partir = file.getName().split("_");
		String partir2 = partir[1];
		String[] resultados = partir2.split(Pattern.quote("."));
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {

		}
		String idioma = properties.getProperty("enlaceIdioma");
		nButtons.add(idioma);
		idiomas.put(idioma, resultados[0]);

	}

	public Locale getLocale() {
		return locale;
	}

	public void setIdioma() {
		if (!option.isEmpty()) {
			locale = new Locale(idiomas.get(option));
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		}
	}

	public List<Locale> getLocales() {
		return locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public List<String> getnButtons() {
		return nButtons;
	}

	public void setnButtons(List<String> nButtons) {
		this.nButtons = nButtons;
	}

}