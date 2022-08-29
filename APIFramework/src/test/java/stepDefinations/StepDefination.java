package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;



public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	JsonPath js;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String strName, String strLanguage, String strAddress) throws IOException {

	//@Given("Add place payload")
	//public void add_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
		//System.out.println("Inside Add place payload");

		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		res=given().spec(requestSpecification())
		.body(data.addPlacePayload(strName,strLanguage,strAddress));
	}
	
	@When("User calls {string} using {string} http request")
	public void user_calls_using_post_http_request(String strResource,String strHttpMethod) {
	    // Write code here that turns the phrase above into concrete actions
	    //constructor will be called with value of resource passed
		APIResources resourceAPI = APIResources.valueOf(strResource);
		System.out.println(resourceAPI.getResource());
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(strHttpMethod.equalsIgnoreCase("POST"))
			response =res.when().post(resourceAPI.getResource());
		//then().spec(resspec).extract().response();
		else if(strHttpMethod.equalsIgnoreCase("GET"))
			response =res.when().get(resourceAPI.getResource());
	}
	@Then("the API call is successfull with status code {int}")
	public void the_api_call_is_successfull_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   //System.out.println("api call is successfull");
		assertEquals(response.getStatusCode(),200);
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	    //String resp = response.asString();
	    //JsonPath js= new JsonPath(resp);
		//assertEquals(js.get(keyValue).toString(),expectedValue);
		assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	
	@Then("verify place_Id cretaed matches to {string} using {string}")
	public void verify_place_id_cretaed_matches_to_using(String strExpectedName,String strResource) throws IOException {
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_using_post_http_request(strResource,"GET");
		String strActualName = getJsonPath(response,"name");
		assertEquals(strActualName,strExpectedName);
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	}


}
