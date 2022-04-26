package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	

	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		// Write your code that will give you place id
		// This code should execute only when place id is null
		
		StepDefinition step = new StepDefinition();
		if(StepDefinition.placeId == null) {
		step.addPlacePayload("Sachin", "French", "Paris, Europe");
		step.callAPI("ADDPlaceAPI", "Post");
		step.verify_place_id_created_maps_to_using("Sachin","GetPlaceAPI");
		}
		
	}
}
