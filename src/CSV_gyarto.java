import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class CSV_gyarto 
{
	public void csvalakito()
	{
		 //Create a workbook
	     Workbook workbook = new Workbook();
		 //Load a sample excel file
		 workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\Hibakód.xlsx");
		
		
		
		//Get the first sheet
		Worksheet sheet = workbook.getWorksheets().get(0);
		
		 
		//Save the document to CSV
		sheet.saveToFile("c:\\Users\\kovacs.zoltan\\Desktop\\Hibakód.csv", ",", Charset.forName("UTF-8"));
		JOptionPane.showMessageDialog(null, "CSV kész", "Hiba üzenet", 2);
	}

}
