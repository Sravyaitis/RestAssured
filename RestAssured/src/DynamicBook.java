import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.ReUseableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicBook {

	
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String reponse = given()
		.header("Content-Type","application/json")
		.body(payload.AddBook())
		.when()
		.post("Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=ReUseableMethods.rawToJson(reponse);
		String id = js.get("ID");
		System.out.println(id);
	}
}
