package model.core;

import static model.core.DriverFactory.driver;
import static model.core.DriverFactory.getDriver;
import static model.core.DriverFactory.killDriver;
import static model.utilities.Properties.CLOSE_BROWNSER;
import static model.utilities.Properties.DISABLE_ELEMENT_HIGHLIGHTS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef;

import model.map.mapComponentes;
import model.utilities.Properties;

public class BaseTest {

	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy--HH_mm_ss");
	protected static Date timeStamps;
	protected static String executionTestName;
	protected static String evidencePath;
	
	public BaseTest() {
		
	}
	
	public BaseTest(String executionTestName){
		BaseTest.executionTestName = executionTestName;
	}
	
	public static Date getTimeStamps() {
		timeStamps = new Date();
		return timeStamps;
	}
	
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void before() {
		getTimeStamps();
		evidencePath = System.getProperty("user.dir")+"/outPut/"+sdf.format(timeStamps);

	}
	
	@After
	public void afterTest() throws IOException, InterruptedException {
		executarJavascript("arguments[0].scrollIntoViewIfNeeded({behavior: 'instant', block: 'top',inline: 'top'})", mapComponentes.surname);
		TakesScreenshot takeSs = (TakesScreenshot) getDriver();
		File file = takeSs.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir") + ""+File.separator+"outPut"
							+File.separator+""+sdf.format(timeStamps)+""+File.separator+""+testName.getMethodName()+".png"));
		
		//higthlight(testName.getMethodName(), mapComponentes.surname);

		
		if(CLOSE_BROWNSER) {
			killDriver();
		}
		
	}
	
	protected static Object executarJavascript(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, params);
	}
	
	protected static String getLocatorFromWebElement(WebElement element) {
		String[] content = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "").trim().split(": ");
		return content[0];
	}
	
	protected static String getValueFromWebElement(WebElement element) {
		String[] content = element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "").trim().split(": ");
		return content[1];
	}
	
	protected static double getScreenScale() {
        WinDef.HDC hdc = GDI32.INSTANCE.CreateCompatibleDC(null);
        if (hdc != null) {
            float actual = GDI32.INSTANCE.GetDeviceCaps(hdc, 10 /* VERTRES */);
            float logical = GDI32.INSTANCE.GetDeviceCaps(hdc, 117 /* DESKTOPVERTRES */);
            GDI32.INSTANCE.DeleteDC(hdc);
            if (logical != 0 && logical/actual >= 1) {
                return logical/actual;
            }
        }
        return (Toolkit.getDefaultToolkit().getScreenResolution() / 96.0f);
	}
	
	protected static void higthlight(String name, WebElement...elements) {
		if (DISABLE_ELEMENT_HIGHLIGHTS) return;
		String imagePath = evidencePath +"/"+ name +".png";
		
		try {
			String[] rectArgs = Properties.EVIDENCE_ARGS.split(" ");
			double percentScale = getScreenScale();
			BufferedImage myPicture = ImageIO.read(new File(imagePath));
			Graphics2D g2D = (Graphics2D) myPicture.getGraphics();
			g2D.setStroke(new BasicStroke(Integer.parseInt(rectArgs[3])));
			g2D.setColor(new Color(Integer.parseInt(rectArgs[0]), Integer.parseInt(rectArgs[1]), Integer.parseInt(rectArgs[2])));
			
			for(WebElement element:elements) {
				WebElement webElement;
				switch (getLocatorFromWebElement(element)) {
		        case "id":
		            webElement = driver.findElement(By.id(getValueFromWebElement(element)));
		            break;
		        case "className":
		        	webElement = driver.findElement(By.className(getValueFromWebElement(element)));
		            break;
		        case "tagName":
		        	webElement = driver.findElement(By.tagName(getValueFromWebElement(element)));
		            break;
		        case "xpath":
		        	webElement = driver.findElement(By.xpath(getValueFromWebElement(element)));
		            break;
		        case "cssSelector":
		        	webElement = driver.findElement(By.cssSelector(getValueFromWebElement(element)));
		            break;
		        case "linkText":
		        	webElement = driver.findElement(By.linkText(getValueFromWebElement(element)));
		            break;
		        case "name":
		        	webElement = driver.findElement(By.name(getValueFromWebElement(element)));
		            break;
		        case "partialLinkText":
		        	webElement = driver.findElement(By.partialLinkText(getValueFromWebElement(element)));
		            break;
		        default:
		            throw new IllegalStateException("locator : " + getLocatorFromWebElement(element) + " not found!!!");
				}
				
				int width = (int)(webElement.getSize().getWidth()*percentScale);
				int height = (int)(webElement.getSize().getHeight()*percentScale);
				String yPosition = executarJavascript("return arguments[0].getBoundingClientRect().top", webElement).toString();
				String xPosition =  executarJavascript("return arguments[0].getBoundingClientRect().left", webElement).toString();
				int y = (int) (Integer.parseInt((yPosition.contains(".")? yPosition.substring(0, yPosition.indexOf(".")): yPosition))*percentScale);
				int x = (int) (Integer.parseInt((xPosition.contains(".")? xPosition.substring(0, xPosition.indexOf(".")): xPosition))*percentScale);
				
				g2D.drawRect(x, y, width, height);
			}
			g2D.dispose();
			ImageIO.write(myPicture, "PNG", new File(imagePath));
			
		} catch (IOException e) {
			System.out.println("Error print Highlight ("+e.getMessage()+") Evidence: " + name);
		}
		
		
		
		
	}
	
	
}
