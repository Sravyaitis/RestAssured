import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicBook2 {

	@Test(dataProvider="data")
	public void AddBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		
		String reponse = given().header("Content-Type","application/json")
		.body(payload.AddBook1(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then()
		.log().all().extract().asString();
		
		JsonPath js = new JsonPath(reponse);
		String id =js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="data")
	public Object[][] getData() {
		return new Object[][] {
			{"abcd","1234"},
			{"uyrr","0975"},
			{"rgbj","6325"}
		};
	}
}
