package Tests;
import static model.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import model.core.BaseTest;

public class TestAlert extends BaseTest{
	
	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
	}

	@Test
	public void TestComponentsInteractWithAlert() {
		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		getDriver().findElement(By.xpath("//input[@id='alert']")).click();
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Alert Simples", text);
		alert.accept();
		
		getDriver().findElement(By.xpath("//input[@id='elementosForm:nome']"))
			.sendKeys(text);

	}
	
	@Test
	public void TestComponentsAlertConfirm() {

		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		getDriver().findElement(By.xpath("//input[@id='confirm']")).click();
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Confirm Simples", text);
		alert.accept();
		String confirm = alert.getText();
		Assert.assertEquals("Confirmado", confirm);
		alert.accept();
		
	}
	
	@Test
	public void TestComponentsAlertConfirm2() {

		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		getDriver().findElement(By.xpath("//input[@id='confirm']")).click();
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Confirm Simples", text);
		alert.dismiss();

		Assert.assertEquals("Negado", alert.getText());
		alert.accept();

	}
	
	@Test
	public void TestComponentsAlertPrompt() {

		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		getDriver().findElement(By.xpath("//input[@id='prompt']")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();

		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void TestComponentsAlertPrompt2() {

		getDriver().manage().window().setSize(new Dimension(1200, 760));
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		getDriver().findElement(By.xpath("//input[@id='prompt']")).click();
		Alert alert = getDriver().switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.dismiss();

		Assert.assertEquals("Era null?", alert.getText());
		alert.dismiss();
		
		Assert.assertEquals(":(", alert.getText());
		alert.accept();
	}
}
