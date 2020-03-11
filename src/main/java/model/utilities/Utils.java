package model.utilities;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import model.map.mapComponentes;

public class Utils {
	
	private WebDriver driver;
	
	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	public void click(String elementToBeClickable) {
		driver.findElement(By.xpath(mapComponentes.elementInput(elementToBeClickable))).click();
	}
	
	public void write(String element, String text) {
		driver.findElement(By.xpath(mapComponentes.elementInput(element))).sendKeys(text);
	}
	
	public void write(String tagName, String element, String text) {
		driver.findElement(By.xpath(mapComponentes.elementInput(element, tagName))).sendKeys(text);
	}

	public Object obtainedText(String element) {
		return driver.findElement(By.xpath(mapComponentes.elementInput(element))).getAttribute("value") ;
	}
	
	public Object obtainedText(String tagName,String element) {
		return driver.findElement(By.xpath(mapComponentes.elementInput(element, tagName))).getAttribute("value") ;
	}

	public boolean elementRadioIsSelected(String element) {
		return driver.findElement(By.xpath(mapComponentes.elementInput(element))).isSelected();
	}
	
	public void selectItemCombo(String item, String element) {
		WebElement elementWeb = driver.findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		combo.selectByVisibleText(item);
	}
	
	public boolean obtainedItemCombo(String item, String element) {
		WebElement elementWeb = driver.findElement(By.xpath(mapComponentes.elementSelected(element)));
		Select combo = new Select(elementWeb);
		List<WebElement> options = combo.getOptions();
		boolean finded = false;
		
		for(WebElement option : options) {
			if(option.getText().equals(item)) {
				finded = true;
				break;				
			}
		}
		
		return finded; 
	}
	
	public void executarJavascript(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(cmd, params);
	}
	
	public void goFrame(String frame) {
		driver.switchTo().frame(frame);
	}
	
	public String getAlertTextAndClick() {
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		alert.accept();
		return msg;
	}
	
	public int getColumn(String register) {
		int cont = -1;
		WebElement table = driver.findElement(By.xpath(mapComponentes.elementTable()));
		List<WebElement> column = table.findElements(By.xpath(".//th"));
		for(int i = 0; i < column.size(); i++ ) {
			if(column.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public int getRow(String register, int idColumn) {
		int cont = -1;
		WebElement table = driver.findElement(By.xpath(mapComponentes.elementTable()));
		List<WebElement> row = table.findElements(By.xpath("./tbody/tr/td["+idColumn+"]"));
		for(int i = 0; i < row.size(); i++ ) {
			if(row.get(i).getText().equals(register)) {
				cont = i + 1;
				break;
			}			
		}		
		return cont;
	}
	
	public void clickInputTable(int idColumnButton, int idRow) {
		WebElement tableButton = driver.findElement(By.xpath(mapComponentes.inputTable(idColumnButton, idRow)));
		tableButton.click();
	}
	
	
}
