package stepDefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;


import dane.ApiResourcesEnum;
import dane.BodyDoWyslania;
import dane.SpecBuilderCommons;

import static org.junit.Assert.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import pojo.KlasaGlowna;


public class AddPlaceStepsDefs extends SpecBuilderCommons{
	
	KlasaGlowna klasaGlowna = new KlasaGlowna();
	
	RequestSpecification urlPlusParam;
	RequestSpecification urlPlusParamPlusBody;
	
	ResponseSpecification code200;
	Response postPlusResource;
	
	static JsonPath js;
	
	BodyDoWyslania bodyDoWyslania = new BodyDoWyslania();
	
	
	@Given("Add place body payload {string} {string} {string}")
	public void add_place_body_payload(String imie, String jezyk, String adres) throws Throwable {
				
		klasaGlowna.returnCode200();
		//code200 = new ResponseSpecBuilder()
				//.expectStatusCode(200)
				//.expectContentType(ContentType.JSON)
				//.build();
		
		urlPlusParamPlusBody = given().spec(urlPlusParamCommons())
				.body(bodyDoWyslania.addPlaceBody(imie, jezyk, adres));
	}
		
	
	

	@When("^user calls \"([^\"]*)\" api with \"([^\\\"]*)\" request$")
	public void user_calls_api_with_Post_request(String method, String api) throws Throwable {
		
		ApiResourcesEnum apiresourcesEnum = ApiResourcesEnum.valueOf(api);
		
		if (method.equals("post")) {
		postPlusResource = urlPlusParamPlusBody.when()
				.post(apiresourcesEnum.getPostUrl())
				.then().spec(klasaGlowna.returnCode200()).extract().response();
		}
		
		if (method.equals("get")) {
			postPlusResource = urlPlusParamPlusBody.when()
					.get(apiresourcesEnum.getPostUrl())
					.then().spec(klasaGlowna.returnCode200()).extract().response();
			}
		
		
	}

	@Then("^the response code is (\\d+)$")
	public void the_response_code_is(int expectedCode) throws Throwable {
	   
		System.out.println("testBart");
		assertEquals(postPlusResource.getStatusCode(), expectedCode);
		System.out.println("testBart");
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String expectedKey, String expectedValue) throws Throwable {
	    
		String responseString = postPlusResource.asString();
		js = new JsonPath(responseString);
		System.out.println("to jest odpowiedz api String");
		assertEquals(js.getString(expectedKey), expectedValue);
		System.out.println("to jest odpowiedz api String");
	}
	
	@Then("^verify placeId created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_placeId_created_maps_to(String a1, String a2) throws Throwable {
		
		
		
		System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		System.out.println(js.getString("place_id"));
		
		String ala =given().baseUri("https://rahulshettyacademy.com")
		.queryParam("place_id", js.getString("place_id"))
		.queryParam("key", "qaclick123")
		.when()
		.get("/maps/api/place/get/json")
		.then()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js2 = new JsonPath(ala);
		System.out.println(js2.getString("language"));
	}

	
	@Given("^DeletePlace payload$")
	public void deleteplace_payload() throws Throwable {
		System.out.println("Bartek delete place api");
		String place = js.getString("place_id");
		
		urlPlusParamPlusBody = given().spec(urlPlusParamCommons())
		.body(bodyDoWyslania.deletePlaceBody(place));
		
	System.out.println("to jest delete");
	System.out.println("jenkins framework super");
		
	}
	
}
