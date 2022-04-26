package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification req;
	ResponseSpecification resSpec;
	Response res;
	TestDataBuild data = new TestDataBuild();
	static String placeId;

	@Given("Add place payload with {string} {string} {string}")
	public void addPlacePayload(String name, String language, String address) throws IOException {
		req = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void callAPI(String resource, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);

		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			res = req.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			res = req.when().get(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("DELETE"))
			res = req.when().delete(resourceAPI.getResource());

	}

	@Then("the API call is success with status code {int}")
	public void validateAPIResponseCode(Integer int1) {
		assertEquals(res.statusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(value, getJsonPath(res, key));
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		 placeId = getJsonPath(res, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", placeId);
		callAPI(resource,"GET");
		assertEquals(name, getJsonPath(res, "name"));

	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecification()).body(data.deletePayload(placeId));
	    
	}
}
