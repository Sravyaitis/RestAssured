import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicViaExternalFile {
	
	@Test
	public void AddBook() throws IOException {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("/Users/sc250374/Downloads/Library+API.postman_collection.json"))))
		.when().post("Library/Addbook.php")
		.then()
		.log().all().extract().asString();
		
		
		System.out.println(response);
	}

}
