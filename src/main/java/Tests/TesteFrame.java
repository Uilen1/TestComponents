package Tests;
import static model.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import model.core.BaseTest;

public class TesteFrame extends BaseTest{
	
	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
	}

	@Test
	public void TestFrame() {

		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.xpath("//input[@id='frameButton']")).click();

		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals("Frame OK!", text);
		alert.accept();

		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.xpath("//input[@id='elementosForm:nome']"));
	}

	@Test
	public void TestPopUp() {

		getDriver().findElement(By.xpath("//input[@id='buttonPopUpEasy']")).click();

		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		getDriver().close();

		getDriver().switchTo().window("");
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");

	}

	@Test
	public void TestPopUpHard() {

		getDriver().findElement(By.xpath("//input[@id='buttonPopUpHard']")).click();

		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");

		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");

	}

}
