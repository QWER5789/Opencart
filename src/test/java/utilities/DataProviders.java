package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders 
{
	  // Data Provider 1
	
	  @DataProvider(name="LoginData")
	  public String [][] getData () throws IOException
	  {
		     String path = ".\\testData\\Opencart_logindata.xlsx";  // taking excel file from test data
		     
		     ExcelUtility xlutil = new ExcelUtility(path);   // creating an object for excelutility
		     
		     int totalRows = xlutil.getRowCount("Sheet1");
		     int totalCols = xlutil.getCellCount("Sheet1", 1);
		     
		      // creating 2 dimensional array to store values
		     String loginData [][] = new String [totalRows][totalCols];  
		     
		     for(int i =1; i<totalRows; i++)   //1 - read the data from excel storing in 2 dimensional array
		     {
		    	   for(int j = 0; j<totalCols; j++)  //0 - i is rows and j is columns
		    	   {
		    		     // i-1 is because array index starts from zero
		    		     loginData [i-1][j] = xlutil.getCellData("Sheet1", i, j); 
		    	   }
		     }
		     
		     return loginData;   // returning 2 dimensional array
	  }
	  
}
