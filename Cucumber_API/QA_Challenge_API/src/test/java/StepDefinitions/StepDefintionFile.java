package StepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefintionFile {

	private static final String USERNAME = "gianna@hightable.test";
	private static final String PASSWORD = "thedantonio1";
	private static final String BASE_URL = "https://qa-challenge-api.scratchpay.com/api/auth";
	private static final String EMAIL_URL = "https://qa-challenge-api.scratchpay.com/api/clinics/2/emails";
	private static final String Clinic_URL = "https://qa-challenge-api.scratchpay.com/api/clinics/search?term=";

	private static String token;
	private static Response response;
	private static String jsonString;

	@Given("Navigate to given URL Link")
	public void navigate_URL_Link() {

		RestAssured.baseURI = BASE_URL;

	}

	@When("User logged in using user email and password")
	public void User_Login() {

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
				.post("/Account/v1/GenerateToken");

		String jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");
	}

	@Then("User Successfully Logged in")
	public void Logged_in() {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Given("Navigate to email URL Link")
	public void navigate_email_URL_Link() {

		RestAssured.basePath = EMAIL_URL;

	}

	@When("Header is {string} and Authorization is {string}")
	public void header_Authorization(String User_Agent, String token) {

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

	}

	@Then("User should be prevented from getting the list")
	public void user_should_be_prevented_from_getting_the_list() {
		Assert.assertEquals(400, response.getStatusCode());
	}

	@Given("Navigate to clinic URL Link")
	public void navigate_clinic_URL_Link() {
		RestAssured.baseURI = Clinic_URL;

	}

	@When("params key is {string}")
	public void params_key_is(String veterinary) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

	}

	@Then("Getting the Veterinary clinic lists")
	public void getting_the_Veterinary_clinic_lists() {

		Assert.assertEquals(400, response.getStatusCode());
	}

}
