package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility
{
	    public FileInputStream fis;
	    public FileOutputStream fos;
	    public XSSFWorkbook workbook;
	    public XSSFSheet sheet;
	    public XSSFRow row;
	    public XSSFCell cell;
	    public CellStyle style;
	    String path;
	    
	    public ExcelUtility(String path)
	    {
	    	   this.path = path;
	    }
	    
	    public int getRowCount(String sheetName) throws IOException
	    {
	    	  fis = new FileInputStream(path);
	    	  workbook = new XSSFWorkbook(fis);
	    	  sheet = workbook.getSheet(sheetName);
	    	  int rowCount = sheet.getLastRowNum();
	    	  workbook.close();
	    	  fis.close();
	    	  return rowCount;
	    }
	    
	    public int getCellCount(String sheetName, int rowNum) throws IOException
	    {
	    	   fis = new FileInputStream(path);
	    	   workbook = new XSSFWorkbook(fis);
	    	   sheet = workbook.getSheet(sheetName);
	    	   row = sheet.getRow(rowNum);
	    	   int cellCount = row.getLastCellNum();
	    	   workbook.close();
	    	   fis.close();
	    	   return cellCount;
	    }
	    
	    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException
	    {
	    	   fis = new FileInputStream(path);
	    	   workbook = new XSSFWorkbook(fis);
	    	   sheet = workbook.getSheet(sheetName);
	    	   row = sheet.getRow(rowNum);
	    	   cell = row.getCell(colNum);
	    	   
	    	   DataFormatter formatter = new DataFormatter();
	    	   String data;
	    	   
	    	   try
	    	   {
	    		       // return the formatted value of cell as a string regardlees of data type
	    		     data = formatter.formatCellValue(cell);
	    	   }
	    	   catch(Exception e)
	    	   {
	    		     data = "";
	    	   }
	    	   
	    	   workbook.close();
	    	   fis.close();
	    	   return data;
	    }
	    
	    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException
	    {
	    	  File xlfile = new File(path);
	    	  
	    	  if(!xlfile.exists())   // if file not exists then create new file
	    	  {
	    		    workbook = new XSSFWorkbook();
	    		    fos = new FileOutputStream(path);
	    		    workbook.write(fos);
	    	  }
	    	  
	    	  fis = new FileInputStream(path);
	    	  workbook = new XSSFWorkbook(fis);
	    	  
	    	  if(workbook.getSheetIndex(sheetName)==-1)   // if sheet not exists then create new sheet
	    	  {
	    		     workbook.createSheet(sheetName);
	    		     sheet = workbook.getSheet(sheetName);
	    	  }
	    	  
	    	  if(sheet.getRow(rowNum)==null)    // if row not exists then create new row
	    	  {
	    		     sheet.createRow(rowNum);
	    		     row = sheet.getRow(rowNum);
	    		     
	    		     cell = row.createCell(colNum);
	    		     cell.setCellValue(data);
	    		     
	    		     fos = new FileOutputStream(path);
	    		     workbook.write(fos);
	    		     workbook.close();
	    		     fis.close();
	    		     fos.close();
	    	  }
	    	  
	    }
	    
	    public void fileGreenColor(String sheetName, int rowNum, int colNum) throws IOException
	    {
	    	  fis = new FileInputStream(path);
	    	  workbook = new XSSFWorkbook(fis);
	    	  sheet = workbook.getSheet(sheetName);
	    	  row = sheet.getRow(rowNum);
	    	  cell = row.getCell(colNum);
	    	  
	    	  style = workbook.createCellStyle();
	    	  
	    	  style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
	    	  style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    	  
	    	  cell.setCellStyle(style);
	    	  workbook.write(fos);
	    	  workbook.close();
	    	  fis.close();
	    	  fos.close();
	    }
	    
	    public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException
	    {
	    	  fis = new FileInputStream(path);
	    	  workbook = new XSSFWorkbook(fis);
	    	  sheet = workbook.getSheet(sheetName);
	    	  row = sheet.getRow(rowNum);
	    	  cell = row.getCell(colNum);
	    	  
	    	  style = workbook.createCellStyle();
	    	  
	    	  style.setFillBackgroundColor(IndexedColors.RED.getIndex());
	    	  style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    	  
	    	  cell.setCellStyle(style);
	    	  workbook.write(fos);
	    	  workbook.close();
	    	  fis.close();
	    	  fos.close();
	    }
	    
}
