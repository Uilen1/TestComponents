package model.scenarios;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import model.pages.MainPage;

@RunWith(Parameterized.class)
public class Scenarios{

	private MainPage mainPage;
	private WebDriver driver;
	
	@Parameter(value=0)
	public String name;
	@Parameter(value=1)
	public String surname;
	@Parameter(value=2)
	public String sex;
	@Parameter(value=3)
	public String food;
	@Parameter(value=4)
	public String scholarLevel;
	@Parameter(value=5)
	public String[] sports;
	@Parameter(value=6)
	public String text;
	
	@Parameters
	public static Collection<Object[]> parametersToTest() {
		return Arrays.asList(new Object[][] {
			{"Uilen Helei","Lelles Moreira","Masculino","Pizza","Superior",new String[]{"Futebol","Karate","Natacao"},"Testando a Bagaça!"},
			{"Carlos Anthony","Lelles Moreira","Masculino","Carne","Mestrado",new String[]{"Futebol","Natacao"},"Testando a Bagaça!"},
			{"Jordana","Lelles Moreira","Feminino","Vegetariano","Doutorado",new String[]{"Natacao"},"Testando a Bagaça!"},
		});
	}
	
	
	@Before
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		mainPage = new MainPage(driver);
	}
	
	@After
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void registerUser() {
		try {
			mainPage.writeName(name);
			mainPage.writeSurname(surname);
			mainPage.selectUserSex(sex);
			mainPage.selectUserFood(food);
			mainPage.choiceScholarLevel(scholarLevel);
			mainPage.selectUserSports(sports);
			mainPage.writeSugestions(text);
			mainPage.register();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}
}
