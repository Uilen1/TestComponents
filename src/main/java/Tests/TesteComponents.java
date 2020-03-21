package Tests;

import static model.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.core.BaseTest;

public class TesteComponents extends BaseTest {

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
	}

	@Test
	public void TestComponentsInput() {

		getDriver().findElement(By.xpath("//input[@id='elementosForm:nome']")).sendKeys("TesteComponentsInput");
		Assert.assertEquals("TesteComponentsInput",
				getDriver().findElement(By.xpath("//input[@id='elementosForm:nome']")).getAttribute("value"));

	}

	@Test
	public void TestComponentsTextArea() {

		getDriver().findElement(By.xpath("//textarea[@id='elementosForm:sugestoes']"))
				.sendKeys("TesteComponentsTextArea");
		Assert.assertEquals("TesteComponentsTextArea",
				getDriver().findElement(By.xpath("//textarea[@id='elementosForm:sugestoes']")).getAttribute("value"));
	}

	@Test
	public void TestComponentsRadioButton() {

		getDriver().findElement(By.xpath("//input[@id='elementosForm:sexo:0']")).click();
		Assert.assertTrue(getDriver().findElement(By.xpath("//input[@id='elementosForm:sexo:0']")).isSelected());

	}

	@Test
	public void TestComponentsCheckedBox() {

		for (int i = 0; i < 4; i++) {
			getDriver().findElement(By.xpath("//input[@id='elementosForm:comidaFavorita:" + i + "']")).click();
			Assert.assertTrue(getDriver().findElement(By.xpath("//input[@id='elementosForm:comidaFavorita:" + i + "']"))
					.isSelected());
		}

	}

	@Test
	public void TestComponentsComboBox() {


		WebElement element = getDriver().findElement(By.xpath("//select[@id='elementosForm:escolaridade']"));
		Select combo = new Select(element);
		// combo.selectByIndex(3);
		// combo.selectByValue("1graucomp");
		combo.selectByVisibleText("2o grau incompleto");

	}

	@Test
	public void TestComponentsVerifiedComboBox() {

		WebElement element = getDriver().findElement(By.xpath("//select[@id='elementosForm:escolaridade']"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean finded = false;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals("Mestrado")) {
				finded = true;
				break;
			}

		}

		Assert.assertTrue(finded);
	}

	@Test
	public void TestComponentsMultipliesComboBox() {

		WebElement element = getDriver().findElement(By.xpath("//select[@id='elementosForm:esportes']"));
		Select combo = new Select(element);
//		List<WebElement> options = combo.getOptions();
//		boolean finded = false;
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
//		for(WebElement option: options) {
//			if(option.getText().equals("O que eh esporte?")) {
//				finded = true;
//				break;
//			}
//		}
//		Assert.assertTrue(finded);

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

	}

	@Test
	public void TestComponentsButtonClick() {

		WebElement btnClick = getDriver().findElement(By.xpath("//input[@id='buttonSimple']"));
		btnClick.click();
		Assert.assertEquals("Obrigado!", btnClick.getAttribute("value"));

	}

	@Test
	public void TestComponentsTextInPage() {


//		Assert.assertTrue(getDriver().findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));

		Assert.assertEquals("Campo de Treinamento",
				getDriver().findElement(By.xpath("//h3[text()='Campo de Treinamento']")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				getDriver().findElement(By.xpath("//span[@class='facilAchar']")).getText());

	}

}
