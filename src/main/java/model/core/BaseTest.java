package model.core;

import static model.core.DriverFactory.getDriver;
import static model.core.DriverFactory.killDriver;
import static model.utilities.Properties.CLOSE_BROWNSER;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void afterTest() throws IOException {
		TakesScreenshot takeSs = (TakesScreenshot) getDriver();
		File file = takeSs.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir") + ""+File.separator+"outPut"+File.separator+""+testName.getMethodName()+".jpg"));
		
		
		if(CLOSE_BROWNSER) {
			killDriver();
		}
		
	}
	
}
