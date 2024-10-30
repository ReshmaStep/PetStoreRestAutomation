package api.endpoints;

import  static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*2nd file and 1st file are linked
//UserEndPoints.java
//Created for peform , cerate, read, update , delete request the user API
 * payload= request body it is but nothing but pojo
 * here no validations done, its done in testcase
*/
public class UserEndPoints  {
	
	public static Response createUser(User payload){
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		// check above in swagger doc accept, body , content type//
		
		.when()
		.post(Routes.post_url);//Routes is class name.methodname as it is static
		
		return response;
	}
	public static Response readUser (String userName){
		
		Response response=given()
		.pathParam("username", userName)

		.when()
		.get(Routes.get_url);//Routes is class name.methodname as it is static
		
		return response;
	}

public static Response updateUser(String userName, User payload){
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		        .pathParam("username", userName)
	
		.when()
		.put(Routes.put_url);//Routes is class name.methodname as it is static
		
		return response;
	}



public static Response deleteUser(String userName){
	
	Response response=given()
	.pathParam("username", userName)

	.when()
	.delete(Routes.delete_url);//Routes is class name.methodname as it is static
	
	return response;
}
	
}
