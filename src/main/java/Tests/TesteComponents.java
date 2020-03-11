package Tests;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TesteComponents {

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsInput() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		driver.findElement(By.xpath("//input[@id='elementosForm:nome']")).sendKeys("TesteComponentsInput");
		Assert.assertEquals("TesteComponentsInput",
				driver.findElement(By.xpath("//input[@id='elementosForm:nome']")).getAttribute("value"));

		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsTextArea() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		driver.findElement(By.xpath("//textarea[@id='elementosForm:sugestoes']")).sendKeys("TesteComponentsTextArea");
		Assert.assertEquals("TesteComponentsTextArea",
				driver.findElement(By.xpath("//textarea[@id='elementosForm:sugestoes']")).getAttribute("value"));

		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsRadioButton() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		driver.findElement(By.xpath("//input[@id='elementosForm:sexo:0']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='elementosForm:sexo:0']")).isSelected());

		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsCheckedBox() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		for (int i = 0; i < 4; i++) {
			driver.findElement(By.xpath("//input[@id='elementosForm:comidaFavorita:" + i + "']")).click();
			Assert.assertTrue(
					driver.findElement(By.xpath("//input[@id='elementosForm:comidaFavorita:" + i + "']")).isSelected());
		}

		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsComboBox() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		WebElement element = driver.findElement(By.xpath("//select[@id='elementosForm:escolaridade']"));
		Select combo = new Select(element);
		// combo.selectByIndex(3);
		// combo.selectByValue("1graucomp");
		combo.selectByVisibleText("2o grau incompleto");

		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsVerifiedComboBox() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		WebElement element = driver.findElement(By.xpath("//select[@id='elementosForm:escolaridade']"));
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
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsMultipliesComboBox() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		WebElement element = driver.findElement(By.xpath("//select[@id='elementosForm:esportes']"));
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
		
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsButtonClick() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		WebElement btnClick = driver.findElement(By.xpath("//input[@id='buttonSimple']"));
		btnClick.click();
		Assert.assertEquals("Obrigado!", btnClick.getAttribute("value"));

		
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestComponentsTextInPage() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento",driver.findElement(By.xpath("//h3[text()='Campo de Treinamento']")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.xpath("//span[@class='facilAchar']")).getText());
		
		driver.quit();
	}

}
