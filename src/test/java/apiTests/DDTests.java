package apiTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiEndpoints.UserEndPoints;
import apiPayload.UserPOJO;
import apiUtilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {
	/*dataProviderClass annotation is used because Dataprovider method is in different class. Had it been in this class then no
	 * need to use this annotation */
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=Dataproviders.class )
	public void createUserTest(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		/*variables to catch data coming from the dataprovider needs to be declared in the same sequence in which column is created in 
		 * excel file*/
		UserPOJO p=new UserPOJO();
		p.setId(Integer.parseInt(userID));
		p.setUsername(userName);
		p.setFirstName(fname);
		p.setLastName(lname);
		p.setEmail(useremail);
		p.setPassword(pwd);
		p.setPhone(ph);
		
		Response response=UserEndPoints.createUser(p);
		Assert.assertEquals(response.getStatusCode(),200);		
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=Dataproviders.class)
	public void deleteUserTest(String uName)
	{
			Response response=UserEndPoints.deleteUser(uName);
			Assert.assertEquals(response.getStatusCode(),200);	
	
	}

}
