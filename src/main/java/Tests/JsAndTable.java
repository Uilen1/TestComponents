package Tests;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import model.map.mapComponentes;
import model.utilities.Utils;

public class JsAndTable {
	
	private WebDriver driver;
	private Utils utils;
	
	@Before
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "//ComponentesHTML//componentes.html");
		utils = new Utils(driver);
	}
	
	@After
	public void afterTest() {
		driver.quit();
	}
	
	@Test
	public void javascriptTest() {
		try {
			WebElement frame = driver.findElement(By.id("frame2"));
			utils.executarJavascript("window.scrollBy(0,arguments[0])", frame.getLocation().y);
			utils.goFrame("frame2");
			utils.click("frameButton");
			String msg = utils.getAlertTextAndClick();
			Assert.assertEquals("Frame OK!", msg);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void getTablesData() {
		
		int idColumn = utils.getColumn("Nome");
		
		int idRow = utils.getRow("Francisco", idColumn);
		
		int idColumnButton = utils.getColumn("Radio");
		
		utils.clickInputTable(idColumnButton, idRow);
			
		
	}
	
	
	
}
