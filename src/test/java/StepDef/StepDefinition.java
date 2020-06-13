package StepDef;

import static org.junit.Assert.*;


import java.io.IOException;

import Resources.APIResource;
import Resources.TestDataBuild;
import Resources.Utils;

import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class StepDefinition extends Utils{

	ResponseSpecification resSpec;
	RequestSpecification add_res;
	//RequestSpecification get_res;
	Response response;
	
	static String placeId;
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload {int} {string} {string} {string} {string} {string}")
	public void add_Place_Payload(int Accuracy, String name, String language, String address, String Phone_number, String Website) throws IOException {
	    // using SpecBuilder and passing input args dynamically to payload
	    
	   add_res=given().spec(requestSpecification())
				.body(data.addPlacePayload(Accuracy,name, language,address, Phone_number,Website));
	


	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resourceType,String httpMethod) {
		
		//Using ENUM class for Specification of Resourse Type and Name
		
		APIResource resourceAPI=APIResource.valueOf(resourceType);
		resourceAPI.getResource();
		System.out.println(resourceAPI.getResource());
		
		//resSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		//Checking for http  method type
		if(httpMethod.equalsIgnoreCase("POST")){
			
			response =add_res.when().post(resourceAPI.getResource());
		}
		else if(httpMethod.equalsIgnoreCase("GET")){
			response =add_res.when().get(resourceAPI.getResource());
		
		}
		else if(httpMethod.equalsIgnoreCase("DELETE")){
			response =add_res.when().delete(resourceAPI.getResource());
		}
		

	}

	@Then("the API call success with status code {int}")
	public void the_API_call_success_with_status_code(Integer int1) {
		//Assertion for status code is 200
		assertEquals(response.getStatusCode(),200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String status, String value) {
		// Write code here that turns the phrase above into concrete actions
		
		//Assertion for scope is APP
		assertEquals(getJsonPath(response, status),value);

	}
	@Then("verify placeId created maps to {string} using {string}")
	public void verify_placeId_created_maps_to_using(String expectedName, String resourceName) throws IOException {
	    // retriving placeid
		 placeId =getJsonPath(response,"place_id");
		 
		 add_res=given().spec(requestSpecification()).queryParam("place_id",placeId);
		 //calling for making a request 
		 user_calls_with_Post_http_request(resourceName,"GET");
		 //Assertion of name parameter
		 String actualName =getJsonPath(response,"name");
		 assertEquals(actualName,expectedName);
	}
	@Given("Delete Place Payload")
	public void delete_Place_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		add_res=given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}




}
