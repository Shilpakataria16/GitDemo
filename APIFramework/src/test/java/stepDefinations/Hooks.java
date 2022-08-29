package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write code to get place_id
		//execute in case place_id is null
		StepDefination objStepDef = new StepDefination();
		if(StepDefination.place_id==null)
		{
			objStepDef.add_place_payload_with("abcd", "English", "Asia");
			objStepDef.user_calls_using_post_http_request("AddPlaceAPI", "POST");
			objStepDef.verify_place_id_cretaed_matches_to_using("abcd", "GetPlaceAPI");
		}
	}
	
}
