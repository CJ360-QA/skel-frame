package testcases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.HomePage;


public class HomeTest extends BaseClass{

	
	
	@Test
	public void t1() {
		HomePage hp=new HomePage();
		hp.setsignup(prop.getProperty("username"), prop.getProperty("password"));
		
		hp.verifyTitle();
	}
	
}
