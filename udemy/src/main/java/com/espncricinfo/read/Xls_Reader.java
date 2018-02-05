package com.espncricinfo.read;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {
	
	public String path;
	public FileInputStream fis =null;
	public FileOutputStream fout=null;
	private XSSFWorkbook workBook=null;
	private XSSFSheet sheet=null;
	private XSSFRow row=null;
	private XSSFCell cell=null;
	
	
	public Xls_Reader(String path){
		this.path=path;
		try{
			fis=new FileInputStream(path);
			workBook=new XSSFWorkbook(fis);
			sheet=workBook.getSheetAt(0);
			
		}catch(Exception e){
			System.out.println("Error");
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
	public int getRowCount(String sheetName){
		int index=workBook.getSheetIndex(sheetName);
		if(index==-1){
			return 0;
		}else{
			sheet=workBook.getSheetAt(index);
			int rowNum=sheet.getLastRowNum()+1;
			return rowNum;
		}
	}
	
	//get cell data from sheet
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum){
		try{
				if(rowNum<0){
			return "";
				}
				int index= workBook.getSheetIndex(sheetName);
				int colNum=-1;
				if (index==-1){
			return "";
				}
		
				sheet= workBook.getSheetAt(index);
				row= sheet.getRow(0);
				for(int i=0;i<row.getLastCellNum();i++){
					if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())){
						colNum=i;
					}
			
			
				}
				if(colNum==-1){
					return "";
				}
				sheet= workBook.getSheetAt(index);
				row=sheet.getRow(rowNum-1);
				if(row==null){
					return "";
				}
				cell=row.getCell(colNum);
				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
					return cell.getStringCellValue();
			
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC||cell.getCellType()==Cell.CELL_TYPE_FORMULA){
					String cellText  = String.valueOf(cell.getNumericCellValue());
					if(HSSFDateUtil.isCellDateFormatted(cell)){
						double d = cell.getNumericCellValue();
						Calendar cal= Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText=(String.valueOf(cal.get(Calendar.YEAR)).substring(2));
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                        cal.get(Calendar.MONTH)+1 + "/" +
                        cellText;
				 
				
					}
				
			return cellText;
				}
			else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        }
		
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colName +" does not exist in xls";
        }
		

}

}
