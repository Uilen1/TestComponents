package Tests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import model.utilities.Utils;

public class TesteCadastro {

	private WebDriver driver;
	private Utils utils;
	
	@Before
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		utils = new Utils(driver);
	}

	@After
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void register() {		
		
		utils.click("elementosForm:nome");
		utils.write("elementosForm:nome", "Uilen Helei");
		Assert.assertEquals("Uilen Helei", utils.obtainedText("elementosForm:nome"));
		
		
		utils.click("elementosForm:sobrenome");
		utils.write("elementosForm:sobrenome", "Lelles Moreira");
		Assert.assertEquals("Lelles Moreira", utils.obtainedText("elementosForm:sobrenome"));
		
		
		utils.click("elementosForm:sexo:0");
		Assert.assertTrue(utils.elementRadioIsSelected("elementosForm:sexo:0"));
		
		utils.click("elementosForm:comidaFavorita:2");
		Assert.assertTrue(utils.elementRadioIsSelected("elementosForm:comidaFavorita:2"));
		
		utils.selectItemCombo("Superior","elementosForm:escolaridade");
		Assert.assertTrue(utils.obtainedItemCombo("Superior", "elementosForm:escolaridade"));
		
		utils.selectItemCombo("Futebol", "elementosForm:esportes");

		utils.write("textarea", "elementosForm:sugestoes", "Teste");
		Assert.assertEquals("Teste", utils.obtainedText("textarea", "elementosForm:sugestoes"));
		
		utils.click("elementosForm:cadastrar");
				
	}
	
}
