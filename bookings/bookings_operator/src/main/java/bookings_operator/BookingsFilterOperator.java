package bookings_operator;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BookingsFilterOperator implements Operator {
	
	
	public void filterBy(WebDriver browser, String filter) {
		WebElement searchBox;
		if(filter.contains("5")) {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_class\"]/div/a/div/span[contains(text(), " + filter + ") and contains(text(), 'stars')]"));
		} else {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_popular_activities\"]/div/a/div/span[contains(text(), " + filter + ")]"));
		}
		
		searchBox.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void searchForHotel(WebDriver browser, String city, String myDate, int rooms, int adults, int children) {
		WebElement accom = browser.findElement(By.xpath("//*[@id=\"ss\"]"));

		WebElement mySelectElement = browser.findElement(By.id("no_rooms"));
		WebElement mySelectElement2 = browser.findElement(By.id("group_adults"));
		WebElement mySelectElement3 = browser.findElement(By.id("group_children"));
		Select dropdown = new Select(mySelectElement);
		Calendar currentDate = Calendar.getInstance();
		
		int month = Integer.parseInt(myDate.split("-")[1]);
		int date = Integer.parseInt(myDate.split("-")[2])-1;
		int temp;
		
		if(myDate.contains("" + currentDate.get(Calendar.YEAR))) {	
			month = month - (currentDate.get(Calendar.MONTH));
		} else {
			temp = 12 - (currentDate.get(Calendar.MONTH));
			month = temp + month;
		}
		
		browser.findElement(By.cssSelector(".b-datepicker")).click();
		for(int i=0; i < month-1; i++) {
			browser.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[2]/span")).click();
		}
		browser.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[" + month + "]/table/tbody/tr/td/span[contains(text(), " + date + ")]")).click();
		accom.clear();
		accom.sendKeys(city);
		
		new Actions(browser).moveToElement(browser.findElement(By.id("xp__guests__toggle"))).click().perform();
		dropdown.selectByIndex((rooms-1));
		dropdown = new Select(mySelectElement2);
		dropdown.selectByIndex((adults-1));
		dropdown = new Select(mySelectElement3);
		dropdown.selectByIndex(children);
		
		new Actions(browser).moveToElement(browser.findElement(By.className("sb-searchbox__button"))).click().perform();
		
		
	}
	
}
