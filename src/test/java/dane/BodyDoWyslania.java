package dane;

import java.util.Arrays;
import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.AddPlace;
import pojo.Location;

public class BodyDoWyslania {
	
	public AddPlace addPlaceBody (String imie, String jezyk, String adres) {
		
		List<String> lista = Arrays.asList("ala", "ma");
		
		Location locationObiekt = new Location();
		locationObiekt.setLat(-38.383494);
		locationObiekt.setLng(33.427362);
		
		
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(adres);
		place.setLanguage(jezyk);
		place.setLocation(locationObiekt);
		place.setPhone_number("(+91) 983 893 3937");
		place.setName(imie);
		place.setTypes(lista);
		place.setWebsite("www.bart.pl");
		
		return place;
	}

	public String deletePlaceBody(String body) {
		
		return "{\r\n    \"place_id\":\""+body+"\"\r\n}";
	}
	
	
}
