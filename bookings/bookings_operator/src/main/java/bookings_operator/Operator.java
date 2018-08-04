package bookings_operator;

import org.openqa.selenium.WebDriver;

public interface Operator {
	public void searchForHotel(WebDriver browser, String city, String myDate, int rooms, int adults, int children);
	public void filterBy(WebDriver browser, String filter);
}
