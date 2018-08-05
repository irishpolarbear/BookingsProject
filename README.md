# BookingsProject

Tools used:

Java 1.8,
Eclipse Photon with Maven,
Selenium 3.13.0,
ChromeDriver 2.41

How to run tests:

To run my tests, navigate to the bookings\bookings_testware\src\test\java\com\tom\selenium\bookings\ folder and ruin the BookingsFilterTest.java as JUnit Test

Some documentation / review:

I decided to write this project using JUnit with Selenium driver.

I separated the tests from the operators that handle the Searching / Filtering features. This allows the tests to import the same steps without having to have a dependency on the test cases as well. It also allows future extension of any other operators without unnecessary overhead.

I did see there was an opportunity to use DataDriven testing here. This would save space on using the current 4 tests for the same process and instead only have one block of code. If I had implemented such a solution, it would have only required one block of code for the test and the data would be fed (via a CSV) into the test similarly to a loop. I decided not to implement this due to time constraints; I was only familiar with the DataDriven methods from my current employer's project, and would not be able to use the same framework here. As learning another framework would take time away from developing the tests in time, I chose not to prioritize this.

One issue: Filter xPaths

If you delve into the code you will find that the xPaths for the filters are essentially hard-coded in. I spent much time frustrated in trying to find a generic / catchall solution that would handle any filter, but was unable. 
