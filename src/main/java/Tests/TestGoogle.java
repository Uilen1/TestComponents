package Tests;
import static model.core.DriverFactory.getDriver;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import model.core.BaseTest;

public class TestGoogle extends BaseTest {


	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().manage().window().setSize(new Dimension(1200, 765));
	}
	
	
	@Test
	public void test() {
		
		getDriver().get("http://www.google.com");
		System.out.println(getDriver().getTitle());
	}

}
