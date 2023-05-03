package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeMethod
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());		
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging .....");
		
	}


	@Test(priority=1)
	public void testPostUser()
	{		
		logger.info("********* Creating User **************");
		Response response = UserEndPoints.createUser(userPayload);			
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		logger.info("********* User is created **************");
	}
	
	
	//@Test(priority=2)
	public void testGetUserByName()
	{	
		logger.info("********* Get User **************");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());		
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(),200);	
		logger.info("********* User info is displayed **************");
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{		
		logger.info("********* Updating User **************");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		System.out.println("== Update Response==");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		logger.info("********* User details is updated **************");
		
		//Check data after update	
		logger.info("********* Get Updated User **************");
		Response respAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());		
		respAfterUpdate.then().log().all();
		Assert.assertEquals(respAfterUpdate.getStatusCode(),200);
		
		
		//Delete after update		
		logger.info("********* Deleting User **************");
		Response respAfterDelete = UserEndPoints.deleteUser(this.userPayload.getUsername());		
		respAfterDelete.then().log().all();
		Assert.assertEquals(respAfterDelete.getStatusCode(),200);
		logger.info("********* User is deleted **************");
	}
	
	
//	@Test(priority=4)
	public void testDeleteUserByName()
	{	
		logger.info("********* Deleting User **************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
		logger.info("********* User is deleted **************");
	}



}
