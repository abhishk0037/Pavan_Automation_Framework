package apiEndpoints;
import static io.restassured.RestAssured.given;
import apiPayload.UserPOJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
    //this class is created to perform CRUD (create, read, update, delete) operations
public class UserEndPoints {
	
	public static Response createUser(UserPOJO payload) {
		Response res=given().contentType(ContentType.JSON).accept("application/json").body(payload)
		.when().post(Routes.post_url);
		return res;
		
	}
	
	public static Response getUser(String name) {
		Response res=given().pathParam("username", name)
		.when().get(Routes.get_url);
		return res;
		
	}
	
	public static Response updateUser(String name, UserPOJO payload) {
		Response res=given().contentType(ContentType.JSON).accept("application/json").pathParam("username", name).
				body(payload)
		.when().put(Routes.put_url);
		return res;
		
	}
	
	public static Response deleteUser(String name) {
		Response res=given().contentType(ContentType.JSON).accept("application/json").pathParam("username", name)
		.when().delete(Routes.delete_url);
		return res; 
		
	}

}
