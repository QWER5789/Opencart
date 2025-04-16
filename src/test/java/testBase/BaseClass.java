package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//Reusable methods
public class BaseClass 
{
	  public static WebDriver driver;
	  public Logger logger;
	  public Properties p;
	
      @BeforeClass(groups= {"Sanity","Regression","Master"})
      @Parameters({"os", "browser"})
      public void setup(String os, String br) throws IOException
      {
    	    // loading config.properties file
    	    FileReader file = new FileReader("./src//test//resources//config.properties");
    	    p = new Properties();
    	    p.load(file);
    	    
    	    logger = LogManager.getLogger(this.getClass());
    	    
    	    if(p.getProperty("execution_env").equalsIgnoreCase("Remote"))
    	    {
    	    	   DesiredCapabilities capabilities = new DesiredCapabilities();
    	    	   
    	    	   //os
    	    	   if(os.equalsIgnoreCase("Windows"))
    	    	   {
    	    		    capabilities.setPlatform(Platform.WIN11);
    	    	   }
    	    	   else if(os.equalsIgnoreCase("Linux"))
    	    	   {
    	    		    capabilities.setPlatform(Platform.LINUX);
    	    	   }
    	    	   else if(os.equalsIgnoreCase("Mac"))
    	    	   {
    	    		    capabilities.setPlatform(Platform.MAC);
    	    	   }
    	    	   else
    	    	   {
    	    		     System.out.println("No matching os");
    	    		     return;
    	    	   }
    	    	   
    	    	   // browser
    	    	   switch(br)
    	    	   {
    	    	          // under setBrowserName - chrome should be in small letter only
    	    	         case "Chrome" : capabilities.setBrowserName("chrome"); break;
     	                 case "Edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
     	                case "Firefox" : capabilities.setBrowserName("firefox"); break;
     	                 default : System.out.println("No matching browser"); return;
    	    	   }   
    	    	   
    	    	   driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
    	//    	   driver = new RemoteWebDriver(new URL("http://192.168.1.10:4444"), capabilities);
    	    }
    	    
    	    if(p.getProperty("execution_env").equalsIgnoreCase("Local"))
    	    {
    	    	  switch(br)
        	      {
        	            case "Chrome" : driver = new ChromeDriver(); break;
        	            case "Edge" : driver = new EdgeDriver(); break;
        	            case "Firefox" : driver = new FirefoxDriver(); break;
        	            default : System.out.println("Invalid browser name"); return;
        	      }
    	    }
    	    
    	    driver.manage().deleteAllCookies();
    	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	  
    	    // reading url from properties file
    	    driver.get(p.getProperty("appURL"));
    	    driver.manage().window().maximize();
      }
    
      @AfterClass(groups= {"Sanity","Regression","Master"})
      public void teardown()
      {
    	    driver.close();
      }
      
      public String captureScreen(String tname) throws IOException
      {
    	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    	    
    	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    	     File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
    	     
    	     String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp;
    	     File targetFile = new File(targetFilePath);
    	     
    	      sourceFile.renameTo(targetFile);
    	      
    	      return targetFilePath;
      }
	
	  public String randomString()
	  {
		     String generatedString = RandomStringUtils.randomAlphabetic(6);
		     return generatedString;
	  }
	  
	  public String randomNumber()
	  {
		     String generatedNumber = RandomStringUtils.randomNumeric(10);
		     return generatedNumber;
	  }
	  
	  public String randomAlphaNumeric()
	  {
		     String generatedString = RandomStringUtils.randomAlphabetic(3);
		     String generatedNumber = RandomStringUtils.randomNumeric(3);
		     return (generatedString+"@"+generatedNumber);
	  }
}
