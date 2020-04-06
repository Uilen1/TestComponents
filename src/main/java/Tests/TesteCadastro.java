package Tests;

import static model.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.core.BaseTest;
import model.utilities.Utils;

public class TesteCadastro extends BaseTest{

	private Utils utils;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		utils = new Utils();
	}

	@Test
	public void register() {

		utils.click("elementosForm:nome","name");
		utils.write("elementosForm:nome", "Uilen Helei","writeName");
		Assert.assertEquals("Uilen Helei", utils.obtainedText("elementosForm:nome"));

		utils.click("elementosForm:sobrenome","surname");
		utils.write("elementosForm:sobrenome", "Lelles Moreira","writeSurname");
		Assert.assertEquals("Lelles Moreira", utils.obtainedText("elementosForm:sobrenome"));

		utils.click("elementosForm:sexo:0","sexo");
		Assert.assertTrue(utils.elementRadioIsSelected("elementosForm:sexo:0"));

		utils.click("elementosForm:comidaFavorita:2","Comida");
		Assert.assertTrue(utils.elementRadioIsSelected("elementosForm:comidaFavorita:2"));

		utils.selectItemCombo("Superior", "elementosForm:escolaridade","clickItem");
		Assert.assertTrue(utils.obtainedItemCombo("Superior", "elementosForm:escolaridade"));

		utils.selectItemCombo("Futebol", "elementosForm:esportes","clickItem");

		utils.write("textarea", "elementosForm:sugestoes", "Teste","writeSugestion");
		Assert.assertEquals("Teste", utils.obtainedText("textarea", "elementosForm:sugestoes"));

		utils.click("elementosForm:cadastrar","clickCadastrar");

	}

}
