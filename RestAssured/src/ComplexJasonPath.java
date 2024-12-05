import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJasonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursesPrice());
		
		//1. Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		

		//2.Print Purchase Amount
		double amount=js.getDouble("dashboard.purchaseAmount");
		System.out.println(amount);

		
		//3. Print Title of the first course
		String firstTitle=js.getString("courses[0].title");
		System.out.println(firstTitle);
		String lastTitle=js.getString("courses[2].title");
		System.out.println(lastTitle);
		
		System.out.println("***********************");

		//4. Print All course titles and their respective Prices
		for(int i=0;i<count;i++) {
			String courseTitles = js.getString("courses["+i+"].title");
			System.out.println(courseTitles);
			
//			Long prices=js.getLong("courses["+i+"].price");
//			System.out.println(prices);
			
			System.out.println(js.get("courses["+i+"].price").toString()); //toString() converts any datatype to string
		}

		//5. Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++) {
			String courseTitles = js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA")) {
				int copiesCount=js.get("courses["+i+"].copies");
				System.out.println(copiesCount);
				break;
			}
		}
		

		//6. Verify if Sum of all Course prices matches with Purchase Amount
		
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		int sum=0;
		for(int i=0; i<count;i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int totalAmount=copies * price;
			System.out.println(totalAmount);
			sum+=totalAmount;
		}
		System.out.println(sum);
		
		int actualAmount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(actualAmount, sum);
	}

}
