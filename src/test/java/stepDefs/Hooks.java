package stepDefs;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@delete")
	public void beforeScenario() throws Throwable {
		
		AddPlaceStepsDefs ob = new AddPlaceStepsDefs();
		
		if (AddPlaceStepsDefs.js == null) {
			
		ob.add_place_body_payload("ala", "ma", "kota");
		ob.user_calls_api_with_Post_request("post", "RESOURCE_DELETE");
		ob.verify_placeId_created_maps_to("a1", "a2");
		}
	}

}
