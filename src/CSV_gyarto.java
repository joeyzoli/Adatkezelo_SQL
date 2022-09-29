import java.io.File;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class CSV_gyarto 
{
	/*
	 * Ezt az osztályt az UTF8 kódolás miatt hoztam létre, mert a windows alapból nem jól mentett az ékezetes karaktereket és ezt manuálisan kellett megcsinálni
	 */
	JFileChooser csv = new JFileChooser();;
	JFileChooser mentes_helye = new JFileChooser();;
	
	public void csvalakito()
	{
		 //Create a workbook
	     Workbook workbook = new Workbook();																					//példányosítás excel osztályból
		 //Load a sample excel file
	     csv.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);															//filechooser beállítása
	     csv.showOpenDialog(csv);																								//fc ablak megniytása
	     File csv_fajl = csv.getSelectedFile();																					//kiválasztott fájl odaadása egy File osztálynak
		 workbook.loadFromFile(csv_fajl.getName());																				//fájl odaadása az excel osztálynak
		
		//Get the first sheet
		Worksheet sheet = workbook.getWorksheets().get(0);																		//excel tábla létrehozása
			 
		//Save the document to CSV
		mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mentes_helye.showOpenDialog(mentes_helye);
        File fajl = mentes_helye.getSelectedFile();
		sheet.saveToFile(fajl.getAbsolutePath(), ",", Charset.forName("UTF-8"));												//mentés a kiválasztott helyre
		JOptionPane.showMessageDialog(null, "CSV kész", "Hiba üzenet", 2);
	}

}
