import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class oAuthTest {

	public static void main(String[] args) {
		
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"}; //array
		
		String response =given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().extract().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String accessToken=js.getString("access_token");
		
		
		GetCourse gc=given().queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		
		List<Api> apiCourses = gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++) {
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
	//1. one-way
		List<WebAutomation> wa=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<wa.size();j++) {
			//a.add(wa.get(j).getCourseTitle());
			System.out.println(wa.get(j).getCourseTitle());
		}
		
		
	//2. another optimized way
		
		ArrayList<String> a= new ArrayList<String>(); //arraylist
		List<WebAutomation> wa1=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<wa1.size();j++) {
			a.add(wa1.get(j).getCourseTitle());
		}
		List<String> expectedList = Arrays.asList(courseTitles); //convert array to arraylist
		
		Assert.assertTrue(a.equals(expectedList));

	}

}
