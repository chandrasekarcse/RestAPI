package apiResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

  public class GetExcelData {
	
	XSSFWorkbook wb;
	XSSFSheet sh;
	public GetExcelData(String path)
	{
		File fs = new File(path);
		try {
			FileInputStream fis = new FileInputStream(fs);
			wb=new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object getData(int rownum, int colnum)
	{
		sh= wb.getSheetAt(0);
		DataFormatter df = new DataFormatter();
		Object data = df.formatCellValue(sh.getRow(rownum).getCell(colnum));
		return data;
	}

}
