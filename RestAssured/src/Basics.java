import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUseableMethods;
import files.payload;

public class Basics {

	public static void main(String[] args) {
		
		
		//Given(query param, header, body) When(resource and http method) Then (assertThat.statuscode(200).body("key",equalto("value")).header("Server","Apache/ubuntu").extract().response().asString()
		
		//Add place ->update place with new address --> get place to validate updated address
		
				//retrieve placeId to update

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response); //json format
		
		
		
		
		
		//parse json and extract values
		JsonPath js = new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		String newAddress = "70 summer beach, USA";
		//update place in address
		
		given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"place_id\": \""+placeId+"\",\r\n"
				+ "    \"address\": \""+newAddress+"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		
		
		
		String getPlaceResponse=given().queryParam("key","qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js1=ReUseableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, "Pacific ocean");
		
		
	}

}
