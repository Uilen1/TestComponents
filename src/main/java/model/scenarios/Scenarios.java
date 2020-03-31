package model.scenarios;

import static model.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.core.BaseTest;
import model.pages.MainPage;

@RunWith(Parameterized.class)
public class Scenarios extends BaseTest{

	private MainPage mainPage = new MainPage();
	
	public String name;
	public String surname;
	public String sex;
	public String food;
	public String scholarLevel;
	public String[] sports;
	public String text;
	
	public Scenarios(String name, String surname, String sex, String food, String scholarLevel, String[] sports, String text) {
		super(name);
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.food = food;
		this.scholarLevel = scholarLevel;
		this.sports = sports;
		this.text = text;
	}
	
	@Parameters(name = "Testing with name of_ {0}")
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
		getDriver().manage().window().maximize();
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
