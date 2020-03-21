package model.pages;

import model.core.BasePage;

public class MainPage extends BasePage {
	
	public MainPage() {
		
	}

	public void register() {
		utils.click("elementosForm:cadastrar");
	}

	public void writeName(String name) {
		utils.write("elementosForm:nome", name);
	}

	public void writeSurname(String surname) {
		utils.write("elementosForm:sobrenome", surname);
	}

	public void selectUserSex(String sexo) {
		if (sexo == "Masculino") {
			utils.click("elementosForm:sexo:0");
		} else if (sexo == "Feminino") {
			utils.click("elementosForm:sexo:1");
		}
	}

	public void selectUserFood(String food) {
		if (food == "Carne") {
			utils.click("elementosForm:comidaFavorita:0");
		} else if (food == "Frango") {
			utils.click("elementosForm:comidaFavorita:1");
		} else if (food == "Pizza") {
			utils.click("elementosForm:comidaFavorita:2");
		} else if (food == "Vegetariano") {
			utils.click("elementosForm:comidaFavorita:3");
		}

	}

	public void choiceScholarLevel(String ScholarLevel) {
		utils.selectItemCombo(ScholarLevel, "elementosForm:escolaridade");
	}

	public void selectUserSports(String... sports) {
		for (String sport : sports) {
			utils.selectItemCombo(sport, "elementosForm:esportes");
		}

	}

	public void writeSugestions(String sugestion) {
		utils.write("textarea", "elementosForm:sugestoes", sugestion);
	}

}
