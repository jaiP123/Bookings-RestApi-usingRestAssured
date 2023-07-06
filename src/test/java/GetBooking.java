import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojoClasses.BookingDetails;
import utility.AllureLogger;
import utility.BaseTest;
import utility.ExcelLib;

import static io.restassured.RestAssured.given;

public class GetBooking extends BaseTest {
	
	@DataProvider (name="DataFromExcel")
	public Object[][] data() throws Exception {
		ExcelLib xl = new ExcelLib("Booking", this.getClass().getSimpleName());
		return xl.getTestdata();
	}
    
	@Test(dataProvider="DataFromExcel", description="To retrieve the details of the booking IDs") 
	public void getBookingIDs(String ID, 
							  String firstname, 
							  String lastname,
							  String totalprice, 
							  String depositpaid, 
							  String checkin, 
							  String checkout,
							  String additionalneeds, String extra
							  ){
		
		AllureLogger.logToAllure("Starting the test to get booking details");
		
		//Sending the GET request for a specific booking id and receiving the response
		AllureLogger.logToAllure("Getting the response for the Booking IDs from test data excel");
		Response response = given().
				spec(requestSpec).
				pathParam("id", ID).
			when().
				get("/booking/{id}");
		
		//Verify the response code
		AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
		response.then().spec(responseSpec);		

		//To log the response to report
		logResponseAsString(response);

		// Can not assert response body as data keeps changing on website

		
	}
}
