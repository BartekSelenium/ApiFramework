package dane;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class SpecBuilderCommons {
	
	public static RequestSpecification urlPlusParam;
	
	
	public RequestSpecification urlPlusParamCommons() throws IOException {
		
		if (urlPlusParam == null) {
	
		PrintStream logObiekt = new PrintStream(new FileOutputStream("logging.txt"));
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		urlPlusParam = new RequestSpecBuilder()
				.setBaseUri(getMainUrl("baseUrl"))
				.addQueryParam("\"key\", \"qaclick123\"")
				.addFilter(RequestLoggingFilter.logRequestTo(logObiekt))
				.addFilter(ResponseLoggingFilter.logResponseTo(logObiekt))
				.setContentType(ContentType.JSON)
				.build();
	
		return urlPlusParam;
		}
		
		return urlPlusParam;
		
	}
	
	public static String getMainUrl(String key) throws IOException {
		 Properties properties = new Properties();
		 FileInputStream plikProperties = new FileInputStream("C:\\Users\\Bartek\\eclipse-workspace\\APIframwork\\src\\test\\java\\dane\\glowneUrle.properties");
		 properties.load(plikProperties);
		 return properties.getProperty(key);
	}
	
}
