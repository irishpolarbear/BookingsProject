package com.tom.selenium.bookings;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import bookings_operator.BookingsFilterOperator;
import bookings_operator.Operator;

public class BookingsFilterTest {
	
	Logger log = Logger.getLogger(BookingsFilterTest.class.getName());
	WebDriver browser;
	Operator myOp;
	
	@Before
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tommc\\Desktop\\driver\\chromedriver.exe");
		browser = new ChromeDriver();
		browser.get("https://bookings.com");
		myOp = new BookingsFilterOperator();
	}
	
	@Test
	public void testSaunaPass() {
		
		String expected = "Limerick Strand Hotel";
		
		myOp.searchForHotel(browser, "Limerick, Limerick County, Ireland", "2018-11-12", 1, 2, 0);
		myOp.filterBy(browser, "Sauna");
		
		String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'Limerick Strand Hotel')]")).getText();	
		    
		} catch(NoSuchElementException e) {
			actual = "";
			log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'Limerick Strand Hotel')]" , e);
		}	
		
		assertTrue((actual.equals(expected)));
		browser.close();
	}
	
	@Test
	public void testSaunaFail() {
		
		String expected = "George Limerick Hotel";
		
		myOp.searchForHotel(browser, "Limerick, Limerick County, Ireland", "2019-1-12", 1, 2, 0);
		myOp.filterBy(browser, "Sauna");
		
		String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'George Limerick Hotel')]")).getText();	
		    
		} catch(NoSuchElementException e) {
			actual = "";
			//log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]" , e);
            System.out.println("No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]");
		}	
		
		assertFalse((actual.equals(expected)));
		browser.close();
	}
	
	@Test
	public void test5starPass() {
		
		String expected = "The Savoy Hotel";
		
		myOp.searchForHotel(browser, "Limerick, Limerick County, Ireland", "2018-11-12", 1, 2, 0);
		myOp.filterBy(browser, "5");
			String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'The Savoy Hotel')]")).getText();	
		} catch(NoSuchElementException e) {
			actual = "";
			//log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'The Savoy Hotel')]" , e);
		}	
		
		assertTrue((actual.equals(expected)));
		
		browser.close();
	}
	
	@Test
	public void test5starFail() {
		
		String expected = "George Limerick Hotel";
		
		myOp.searchForHotel(browser, "Limerick, Limerick County, Ireland", "2018-11-12", 1, 2, 0);
		myOp.filterBy(browser, "5");
		
		String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'George Limerick Hotel')]")).getText();	
		    
		} catch(NoSuchElementException e) {
			actual = "";
			//log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]" , e);
            System.out.println("No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]");
		}	
		
		assertFalse((actual.equals(expected)));
		browser.close();
	}
	
	/*public void filterBy(String filter) {
		WebElement searchBox;
		if(filter.contains("5")) {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_class\"]/div/a/div/span[contains(text(), " + filter + ") and contains(text(), 'stars')]"));
			System.out.println(searchBox.getText());
		} else {
			searchBox = browser.findElement(By.xpath("//*[@id=\"filter_popular_activities\"]/div/a/div/span[contains(text(), " + filter + ")]"));
		}

		searchBox.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchForHotel(String City, String myDate, int rooms, int adults, int children) {
		WebElement accom = browser.findElement(By.xpath("//*[@id=\"ss\"]"));

		WebElement mySelectElement = browser.findElement(By.id("no_rooms"));
		WebElement mySelectElement2 = browser.findElement(By.id("group_adults"));
		WebElement mySelectElement3 = browser.findElement(By.id("group_children"));
		Select dropdown = new Select(mySelectElement);
		Calendar currentDate = Calendar.getInstance();

		System.out.println(myDate.contains("" + currentDate.get(Calendar.YEAR)));
		
		int month = Integer.parseInt(myDate.split("-")[1]);
		int date = Integer.parseInt(myDate.split("-")[2])-1;
		
		if(myDate.contains("" + currentDate.get(Calendar.YEAR))) {	
			month = month - (currentDate.get(Calendar.MONTH));
			System.out.print(month);
		} else {
			
		}
		
		browser.findElement(By.cssSelector(".b-datepicker")).click();
		for(int i=0; i < month-1; i++) {
			browser.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[2]/span")).click();
		}
		browser.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/div[2]/div[3]/div/div/div[" + month + "]/table/tbody/tr/td/span[contains(text(), " + date + ")]")).click();
		accom.clear();
		accom.sendKeys("Limerick, Limerick County, Ireland");
		
		new Actions(browser).moveToElement(browser.findElement(By.id("xp__guests__toggle"))).click().perform();
		dropdown.selectByIndex((rooms-1));
		dropdown = new Select(mySelectElement2);
		dropdown.selectByIndex((adults-1));
		dropdown = new Select(mySelectElement3);
		dropdown.selectByIndex(children);
		
		new Actions(browser).moveToElement(browser.findElement(By.className("sb-searchbox__button"))).click().perform();

		
	}*/
}
