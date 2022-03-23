package apichallenge;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.supportlibraries.ReusableLibraries;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GumtreeApiChallenge extends ReusableLibraries {

	Properties prop = new Properties();

	// Load configuration properties file
	@BeforeMethod
	public void setup() throws IOException {

		prop = loadConfigFile();
	}

	// Fetch the gumtree api data and validate the response
	@Test
	@Parameters({ "expectedAdTitle" })
	public void verifySydneyWeatherOnThursdays(String expectedAdTitle)

	{

		RestAssured.baseURI = prop.getProperty("baseURI");

		Response response = RestAssured.given().when().get(prop.getProperty("adsApiResource")).then().assertThat()
				.contentType(ContentType.JSON).extract().response();

		Assert.assertEquals(response.getContentType(), "application/json;charset=UTF-8", "Verify API response type"); // To
																														// validate
																														// response
																														// type

		Assert.assertEquals(response.statusCode(), 200, "Verify the status code"); // To validate response code

		Assert.assertEquals(response.getHeader("Server"), "rhino-core-shield",
				"Verify the responser header of server key"); // To validate response header for server value

		Assert.assertEquals(response.getHeader("Content-Encoding"), "gzip",
				"Verify the responser header of Content-Encoding key"); // To validate response header for
																		// Content-Encoding value

		JsonPath js = new JsonPath(response.asString());

		List<Object> sizeOfPicturesObjects = js.getList("ads.pictures");

		Assert.assertEquals(sizeOfPicturesObjects.size(), 22, "Verify the size of pictures object"); // To validate the
																										// size of
																										// pictures
																										// objects
																										// returned

		System.out.println("allTitles" + js.get("ads.title"));

		List<String> titleList = js.get("ads.title");

		boolean titleFound = false;

		for (int i = 0; i < titleList.size(); i++) {
			if (expectedAdTitle.equalsIgnoreCase(titleList.get(i))) {
				titleFound = true;
			}
		}

		Assert.assertTrue(titleFound, "Verify the title " + expectedAdTitle); // To validate if the expected title is
																				// available
																				// in response payload
	}
}
