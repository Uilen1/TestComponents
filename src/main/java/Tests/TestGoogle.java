package Tests;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestGoogle {

	@Test
	public void test(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200,765));
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}
	
	
}
