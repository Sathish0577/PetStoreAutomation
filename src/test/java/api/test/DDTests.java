package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {
	
	//User userPayload;
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataproviders.class)
	public void testPostUser(String userID, String userName, String firstName, String lastName, String useremail, String pwd, String ph) 
	{		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
				
		System.out.println("============ Create User =================");
		Response response = UserEndPoints.createUser(userPayload);
				
		System.out.println("== Create Response==");
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		
	}
	
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	public void testDeleteUserByName(String userName)
	{	
		System.out.println("============ Delete User =================");
		Response response = UserEndPoints.deleteUser(userName);
		System.out.println("== Delete Response==");
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
	}
	
	

}
