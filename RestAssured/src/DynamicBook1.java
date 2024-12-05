
import org.testng.annotations.Test;
import files.payload;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
	
public class DynamicBook1 {

		@Test
		public void addBook() {
			RestAssured.baseURI = "http://216.10.245.166";
			String reponse = given()
			.header("Content-Type","application/json")
			.body(payload.AddBook1("abcd","23456"))
			.when()
			.post("Library/Addbook.php")
			.then().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js=new JsonPath(reponse);
			String id = js.get("ID");
			System.out.println(id);
		}
}
