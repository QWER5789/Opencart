package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	    public AccountRegistrationPage(WebDriver driver) 
	    {
		      super(driver);
	    }
	    
	    @FindBy(id="input-firstname")
	    WebElement txtFirstName;
	    
	    @FindBy(id="input-lastname")
	    WebElement txtLastName;
	    
	    @FindBy(id="input-email")
	    WebElement txtEmail;
	    
	    @FindBy(id="input-telephone")
	    WebElement txtTelephone;
	    
	    @FindBy(id="input-password")
	    WebElement txtPassword;
	    
	    @FindBy(id="input-confirm")
	    WebElement txtConfirmPassword;
	    
	    @FindBy(xpath="//input[@name='agree']")
	    WebElement chkdPolicy;
	    
	    @FindBy(xpath="//input[@value='Continue']")
	    WebElement btnContinue;
	    
	    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	    WebElement msgConfirmation;
	    
	    public void setFirstName(String fname)
	    {
	    	 txtFirstName.sendKeys(fname);
	    }
	    
	    public void setLastName(String lname)
	    {
	    	 txtLastName.sendKeys(lname);
	    }
	    
	    public void setEmail(String email)
	    {
	    	 txtEmail.sendKeys(email);
	    }
	    
	    public void setTelephone(String telephone)
	    {
	    	 txtTelephone.sendKeys(telephone);
	    }
	    
	    public void setPassword(String pwd)
	    {
	    	 txtPassword.sendKeys(pwd);
	    }
	    
	    public void setConfirmPassword(String pwd)
	    {
	    	 txtConfirmPassword.sendKeys(pwd);
	    }
	    
	    public void setPrivacyPolicy()
	    {
	    	 chkdPolicy.click();
	    }
	    
	    public void clickContinue()
	    {
	    	 btnContinue.click();
	    }
	    
        	 // validation will not do in page object class - do in test case
	    public String getConfirmationMsg()  
	    {
	    	  try
	    	  {
	    		    return (msgConfirmation.getText());    // just capture and return it
	    	  }
	    	  catch(Exception e)
	    	  {
	    		    return (e.getMessage());
	    	  }
	    }
	    
}
