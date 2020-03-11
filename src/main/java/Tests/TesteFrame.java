package Tests;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteFrame {

	@Test
	public void TestFrame() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.xpath("//input[@id='frameButton']")).click();
		
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Frame OK!",text);
		alert.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='elementosForm:nome']"));
	}
	
	@Test
	public void TestPopUp() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='buttonPopUpEasy']")).click();
		
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
	}
	
	@Test
	public void TestPopUpHard() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		
		driver.findElement(By.xpath("//input[@id='buttonPopUpHard']")).click();
		
		driver.switchTo().window( (String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");

		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		driver.quit();
	}
	
}
