package Tests;

import static model.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import model.core.BaseTest;
import model.utilities.Utils;

public class JsAndTable extends BaseTest {

	private Utils utils;

	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//driver//chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		utils = new Utils();
	}

	@Test
	public void javascriptTest() {
		try {
			WebElement frame = getDriver().findElement(By.id("frame2"));
			executarJavascript("window.scrollBy(0,arguments[0])", frame.getLocation().y);
			utils.goFrame("frame2");
			utils.click("frameButton");
			String msg = utils.getAlertTextAndClick();
			Assert.assertEquals("Frame OK!", msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getTablesData() {

		int idColumn = utils.getColumn("Nome");

		int idRow = utils.getRow("Francisco", idColumn);

		int idColumnButton = utils.getColumn("Radio");

		utils.clickInputTable(idColumnButton, idRow,"PrintClick");

	}

}
