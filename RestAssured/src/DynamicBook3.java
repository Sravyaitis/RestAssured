import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicBook3 {
	
	@Test
	public void AddBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body("{\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\"abcd\",\n"
				+ "\"aisle\":\"1234\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}")
		.when().post("Library/Addbook.php")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id =js.get("ID");
		System.out.println(id);
		
	}

}
