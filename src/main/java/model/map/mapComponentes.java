package model.map;

import static model.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class mapComponentes {
	
	public static WebElement name = getDriver().findElement(By.xpath("//input[@id='elementosForm:nome']"));
	public static WebElement surname = getDriver().findElement(By.xpath("//input[@id='elementosForm:sobrenome']"));
	public static WebElement textarea = getDriver().findElement(By.xpath("//textarea[@id='elementosForm:sugestoes']"));

	public static String elementInput(String nameInput) {
		return "//input[@id='"+nameInput+"']";
	}
	
	public static String elementInput(String elementId,String tagName) {
		return "//"+tagName+"[@id='"+elementId+"']";
	}
	
	public static String elementSelected(String idSelect) {
		return "//select[@id='"+idSelect+"']";
	}
	
	public static String elementTable() {
		return "//table[@id='elementosForm:tableUsuarios']";
	}
	
	public static String inputTable(int idColumnButton, int idRow) {
		return "//table[@id='elementosForm:tableUsuarios']/tbody/tr["+idRow+"]/td["+idColumnButton+"]//input";
	}
	
	
	
	
}
