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
	
	private static final String CITY = "Limerick, Limerick County, Ireland";
	
	/**
	 * 
	 * beforeTest sets up the testing environment as well as initializing the operator.
	 */
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
		
		myOp.searchForHotel(browser, CITY, "2018-11-12", 1, 2, 0);
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
		
		myOp.searchForHotel(browser, CITY, "2018-11-12", 1, 2, 0);
		myOp.filterBy(browser, "Sauna");
		
		String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'George Limerick Hotel')]")).getText();	
		    
		} catch(NoSuchElementException e) {
			actual = "";
			//log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]" , e);
		}	
		
		assertFalse((actual.equals(expected)));
		browser.close();
	}
	
	@Test
	public void test5starPass() {
		
		String expected = "The Savoy Hotel";
		
		myOp.searchForHotel(browser, CITY, "2018-11-12", 1, 2, 0);
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
		
		myOp.searchForHotel(browser, CITY, "2018-11-12", 1, 2, 0);
		myOp.filterBy(browser, "5");
		
		String actual;
		try {	
		    actual = browser.findElement(By.xpath("//*[contains(text(), 'George Limerick Hotel')]")).getText();	
		    
		} catch(NoSuchElementException e) {
			actual = "";
			//log.log(Level.SEVERE, "No such element found: " + "//*[contains(text(), 'George Limerick Hotel')]" , e);
   		}	
		
		assertFalse((actual.equals(expected)));
		browser.close();
	}
	
}
