package apiTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import apiEndpoints.UserEndPoints;
import apiEndpoints.UserEndPointsProperties;
import apiPayload.UserPOJO;
import io.restassured.response.Response;

public class UserTestsproperties {
	Faker f=new Faker();
	UserPOJO p;
	public Logger logger; //created logs variable
	
	@BeforeTest
	public void setUp() {
		p=new UserPOJO();
		p.setId(f.idNumber().hashCode());
		p.setUsername(f.name().username());
		p.setFirstName(f.name().firstName());
		p.setLastName(f.name().lastName());
		p.setEmail(f.internet().safeEmailAddress());
		p.setPassword(f.internet().password(4, 8));
		p.setPhone(f.phoneNumber().cellPhone());
		p.setUserStatus(0);	
		
		logger=LogManager.getLogger(this.getClass()); //initiated the logger variable using the LogManager class
	}
	
	@Test(priority=1)
	public void createUser() {
		logger.info("-------------creating user----------");
		Response re=UserEndPointsProperties.createUser(p);
		re.then().log().all();
		
		Assert.assertEquals(re.getStatusCode(),200);
		logger.info("------------user is created---------");
	}
	
	@Test(priority=2)
	public void getUser() {
		logger.info("---------user info---------");
		Response re=UserEndPointsProperties.getUser(this.p.getUsername());
		re.then().log().all();
		
		Assert.assertEquals(re.getStatusCode(), 200);
		logger.info("---------user info retrieved-------");
	}

	@Test(priority=3)
	public void updateUser() {
		p.setFirstName(f.name().firstName());
		p.setLastName(f.name().lastName());
		
		Response re=UserEndPointsProperties.updateUser(this.p.getUsername(), p);
		re.then().log().all();
		Assert.assertEquals(re.getStatusCode(), 200);
		
		// reading updated response
		Response updatedRe=UserEndPointsProperties.getUser(this.p.getUsername());
		updatedRe.then().log().all();
	}
	
	@Test(priority=4)
	public void deleteuser() {
		Response re=UserEndPointsProperties.deleteUser(this.p.getUsername());
		Assert.assertEquals(re.getStatusCode(), 200);
	}
}
