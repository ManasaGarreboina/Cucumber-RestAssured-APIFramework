package StepDef;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeDeleteScenario() throws IOException{
		StepDefinition sd=new StepDefinition();
		//static variables should be called with class names
		if(StepDefinition.placeId==null)
			sd.add_Place_Payload(55, "fred", "spanish", "7thstreet", "(+91) 973 855 3737","https://rahulacademy.com");
			sd.user_calls_with_Post_http_request("AddPlaceAPI","POST");
			sd.verify_placeId_created_maps_to_using( "fred", "getPlaceAPI");
	}

}
