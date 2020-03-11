package Tests;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestAlert {

	@Test
	public void TestComponentsInteractWithAlert() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='alert']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Alert Simples", text);
		alert.accept();
		
		driver.findElement(By.xpath("//input[@id='elementosForm:nome']"))
			.sendKeys(text);
		
		
		//driver.quit();
	}
	
	@Test
	public void TestComponentsAlertConfirm() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='confirm']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Confirm Simples", text);
		alert.accept();
		String confirm = alert.getText();
		Assert.assertEquals("Confirmado", confirm);
		alert.accept();
		
		//driver.quit();
	}
	
	@Test
	public void TestComponentsAlertConfirm2() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='confirm']")).click();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Confirm Simples", text);
		alert.dismiss();

		Assert.assertEquals("Negado", alert.getText());
		alert.accept();
		
		//driver.quit();
	}
	
	@Test
	public void TestComponentsAlertPrompt() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='prompt']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();

		Assert.assertEquals("Era 12?", alert.getText());
		alert.accept();
		
		Assert.assertEquals(":D", alert.getText());
		alert.accept();
		
		//driver.quit();
	}
	
	@Test
	public void TestComponentsAlertPrompt2() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 760));
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='prompt']")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.dismiss();

		Assert.assertEquals("Era null?", alert.getText());
		alert.dismiss();
		
		Assert.assertEquals(":(", alert.getText());
		alert.accept();
		
		//driver.quit();
	}
}
