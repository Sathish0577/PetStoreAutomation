package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.ResourceBundle;

//User endpoints.java  - create, read, update and delete requests
public class UserEndPoints2 {	
	
  //method created for getting url's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("route"); //Load the route.properties file
		return routes;		
	}
		
	
	 public static Response createUser(User payload)
	 {		 
		 String post_url=getURL().getString("post_url");
		 
		 Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)	
		    .log().all()
		 .when()
		    .post(post_url);
		 
		 return response;
	 }
	 
	 
	 public static Response readUser(String userName)
	 {
		 String get_url=getURL().getString("get_url");
		 
		 Response response=given()
		     .pathParam("username",userName)
		     .log().all()
		 .when()
		    .get(get_url);
		 
		 return response;
	 }
	 
	 
	 public static Response updateUser(String userName,User payload)
	 {
		 String update_url=getURL().getString("update_url");
		 Response response=given()
				    .contentType(ContentType.JSON)
				    .accept(ContentType.JSON)
				    .pathParam("username",userName)
				    .body(payload)		
				    .log().all()
		 .when()
		    .put(update_url);
		 
		 return response;
	 }
	 
	 
	 public static Response deleteUser(String userName)
	 {
		 String delete_url=getURL().getString("delete_url");
		 Response response=given()
		     .pathParam("username",userName)	
		     .log().all()
		 .when()
		    .delete(delete_url);
		 
		 return response;
	 }
	
	
	
	
	
	
	
	
	

}
