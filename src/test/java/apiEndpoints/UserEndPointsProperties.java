package apiEndpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import apiPayload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
    //this class is created to perform CRUD (create, read, update, delete) operations
public class UserEndPointsProperties {
	
	static ResourceBundle getUrl(){
		ResourceBundle routes= ResourceBundle.getBundle("Routes"); // load the properties file from the resources folder
		return routes;
	}
	
	public static Response createUser(UserPOJO payload) {
		String post=getUrl().getString("post_url");/*load the post url from the properties file- by calling the getUrl() method all the routes are returned then
		through key we loaded the post url*/
		
		Response res=given().contentType(ContentType.JSON).accept("application/json").body(payload)
		.when().post(post);
		return res;
		
	}
	
	public static Response getUser(String name) {
		String get=getUrl().getString("get_url");
		Response res=given().pathParam("username", name)
		.when().get(get);
		return res;
		
	}
	
	public static Response updateUser(String name, UserPOJO payload) {
		String put=getUrl().getString("update_url");
		Response res=given().contentType(ContentType.JSON).accept("application/json").pathParam("username", name).
				body(payload)
		.when().put(put);
		return res;
		
	}
	
	public static Response deleteUser(String name) {
		String delete=getUrl().getString("delete_url");
		Response res=given().contentType(ContentType.JSON).accept("application/json").pathParam("username", name)
		.when().delete(delete);
		return res; 
		
	}

}
