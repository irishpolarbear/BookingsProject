package bookings_operator;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BookingsFilterOperator implements Operator {
	
	/**
	 * 
	 * Searches for the given filter within the given browser
	 * 
	 *  @param browser the  current / active browser
	 *  @param filter the filter to search for in the DOM.
	 */
	public void filterBy(WebDriver browser, String filter) {
		WebElement searchBox;
		if(filter.contains("Sauna")) {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_popular_activities\"]/div/a/div/span[contains(text(), " + filter + ")]"));
		} else {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_class\"]/div/a/div/span[contains(text(), " + filter + ") and contains(text(), 'stars')]"));
		}
		
		searchBox.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Searches for hotels using the below parameters
	 * 
	 * @param browser the current / active browser
	 * @param checkDate the check-in date (must of format YYYY-MM-DD)
	 * @param rooms the number of rooms required
	 * @param aduults the number of adults staying
	 * @param children the number of children staying
	 */
	public void searchForHotel(WebDriver browser, String city, String checkDate, int rooms, int adults, int children) {
		WebElement accom = browser.findElement(By.xpath("//*[@id=\"ss\"]"));

		WebElement roomElement = browser.findElement(By.id("no_rooms"));
		WebElement adultElement = browser.findElement(By.id("group_adults"));
		WebElement childElement = browser.findElement(By.id("group_children"));
		Select dropdown = new Select(roomElement);
		Calendar currentDate = Calendar.getInstance();
		
		int month = Integer.parseInt(checkDate.split("-")[1]);
		int date = Integer.parseInt(checkDate.split("-")[2])-1;
		int temp = 0;
		
	    //Check if the date is within the current year, otherwise we add on to the counter
		if(checkDate.contains("" + currentDate.get(Calendar.YEAR))) {
			month = month - (currentDate.get(Calendar.MONTH));
		} else {
			temp = 12 - (currentDate.get(Calendar.MONTH));
			month = temp + month;
		}
		

		//Select the datepicker element
		browser.findElement(By.cssSelector(".b-datepicker")).click();
		
		//Click the next button depending on the difference between specified month and the current month as calculated above.
		for(int i=0; i < month-1; i++) {
			new Actions(browser).moveToElement(browser.findElement(By.xpath("//*[@id=\"frm\"]//div/div/div/span[contains(text(), '→')]"))).click().perform();
		}
		
		//Select the specified date.
		new Actions(browser).moveToElement(browser.findElement(By.xpath("//*[@id=\"frm\"]//div[2]/div[3]/div/div/div[" + month + "]/table/tbody/tr/td/span[contains(text(), " + date + ")]"))).click().perform();
		
		
		//Input the city
		accom.clear();
		accom.sendKeys(city);
		
		//Activate the room / people dropdown menu
		new Actions(browser).moveToElement(browser.findElement(By.id("xp__guests__toggle"))).click().perform();
		dropdown.selectByIndex((rooms-1));
		dropdown = new Select(adultElement);
		dropdown.selectByIndex((adults-1));
		dropdown = new Select(childElement);
		dropdown.selectByIndex(children);
		
		
		//Click the search button
		new Actions(browser).moveToElement(browser.findElement(By.className("sb-searchbox__button"))).click().perform();
		
		
	}
	
}
