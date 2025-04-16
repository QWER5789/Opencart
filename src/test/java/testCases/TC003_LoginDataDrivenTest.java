package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
1)	 Valid data – login success – passed - logout
2)	 Valid data – login failed – failed
3)	 Invalid data – login success – failed - logout
4)	 Invalid data – login failed – passed
*/

public class TC003_LoginDataDrivenTest extends BaseClass
{
	   // dataProviderClass - extra parameter need to add if data providers class exists 
	         // in another class or package
	   @Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	   public void verify_LoginDDT (String email, String pwd, String expResult) throws InterruptedException
	   {
		   logger.info(" ***** Starting TC003_LoginDataDrivenTest  ***** ");
		   
		   try
		   {
		           HomePage hp = new HomePage(driver);
		           Thread.sleep(3000);
		           logger.info(" Clicked on My Account link ");
		           hp.clickMyAccount();
		           Thread.sleep(3000);
		           logger.info(" Clicked on Login link ");
		           hp.clickLogin();
		     
		           LoginPage lp = new LoginPage(driver);
		           Thread.sleep(3000);
		           lp.setEmail(email);
		           lp.setPassword(pwd);
		           lp.clickLogin();
		     
		           MyAccountPage map = new MyAccountPage(driver);
		           Thread.sleep(3000);
		           boolean targetPage = map.isMyAccountPageExists();
		     
		           /*
		           1)	 Valid data – login success – passed - logout
		           2)	 Valid data – login failed – failed
		           3)	 Invalid data – login success – failed - logout
		           4)	 Invalid data – login failed – passed
		           */
		           
		           if(expResult.equalsIgnoreCase("valid"))
		           {
		        	     if(targetPage== true)
		        	     {
		        	    	    map.clickLogout();
		        	    	    Assert.assertTrue(true);
		        	     }
		        	     else
		        	     {
		        	    	    Assert.assertTrue(false);
		        	     }
		           }
		           else
		           // if(expResult.equalsIgnoreCase("invalid"))
		           {
		        	     if(targetPage== true)
		        	     {
		        	    	    map.clickLogout();
		        	    	    Assert.assertTrue(false);
		        	     }
		        	     else
		        	     {
		        	    	    Assert.assertTrue(true);
		        	     }
		           }
		    }
		    catch (Exception e)
		    {
		    	   Assert.fail();
		    }
		           
		     logger.info(" ***** Finsihed TC003_LoginDataDrivenTest  ***** ");
	   }
	   
}
