package model.scenarios;

import static model.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import model.core.BaseTest;
import model.pages.MainPage;

@RunWith(Parameterized.class)
public class Scenarios extends BaseTest{

	private MainPage mainPage = new MainPage();
	
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
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
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
