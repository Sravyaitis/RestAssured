package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String reponse = given()
		.header("Content-Type","application/json")
		.body(payload.AddBook1(isbn,aisle))
		.when()
		.post("Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js=ReUseableMethods.rawToJson(reponse);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//array= collection of elements
		//multi-dimentional array= collection of arrays
		
		//new object[][] {"array1", "array2", "array3"};
		return new Object[][] {{"abcd","1111"}, {"efgh","2222"}, {"ijkl","3333"}};
	}

}
