import javax.swing.JPanel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Proglove_kamera extends JPanel {
    
    private String hely = "\\\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\Proglove kamera szállítólevelek\\56A QUAD\\";
    private String excelhely = "\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\Proglove kamera szállítólevelek\\hibás kamera lista.xlsx";
    private File mappa;
    private File[] fajlok;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Proglove kamera.xlsx";

    /**
     * Create the panel.
     */
    public Proglove_kamera() {
        setLayout(null);
        
        JButton start_gomb = new JButton("New button");
        start_gomb.addActionListener(new Kereses());
        start_gomb.setBounds(481, 221, 89, 23);
        add(start_gomb);

    }
    
    class Kereses implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                mappa = new File(hely);                                         //mappa beolvasása
                
                FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                {
                    
                    @Override
                    public boolean accept(File f, String name) 
                    {
                                                                                                        // csak az xlsx fájlokat listázza ki 
                        return name.endsWith(".xlsx");  
                    }
                };
                Workbook keresett = new Workbook();
                keresett.loadFromFile(excelhely);
                Worksheet sheet2 = keresett.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false ); 
                Workbook összesites = new Workbook();
                Worksheet sheet3 = összesites.getWorksheets().get(0);
                int cellaszam = 1;
                sheet3.getRange().get("A" + cellaszam).setText("Barcode");
                sheet3.getRange().get("B" + cellaszam).setText("Típus");
                sheet3.getRange().get("C" + cellaszam).setText("Hibajelenség");
                sheet3.getRange().get("D" + cellaszam).setText("Delivery note");
                sheet3.getRange().get("E" + cellaszam).setText("Beérkezés ideje");
                fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja                      
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    for(int szamlalo2 = 0; szamlalo2 < fajlok.length; szamlalo2++)
                    {
                        String fajlneve = fajlok[szamlalo2].getName();
                        if(fajlneve.startsWith("~$")){}
                        else
                        {               
                            File excel = new File(hely +fajlok[szamlalo2].getName());
                            Workbook workbook = new Workbook();             
                            workbook.loadFromFile(excel.getAbsolutePath());
                            Worksheet sheet = workbook.getWorksheets().get(0);
                            //String[] nev = excel.getName().split("\\.");
                            DataTable datatable2 = new DataTable();
                            datatable2 = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                            for(int szamlalo3 = 0; szamlalo3 < datatable2.getRows().size(); szamlalo3++)
                            {
                                if(datatable.getRows().get(szamlalo).getString(0).contains(datatable2.getRows().get(szamlalo3).getString(0)))
                                {
                                    sheet3.getRange().get("A" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(0));
                                    sheet3.getRange().get("B" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(1));
                                    sheet3.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(szamlalo).getString(2));
                                    sheet3.getRange().get("D" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                                }
                            }
                        }
                           
                    }
                }
                
                sheet3.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet3.getAllocatedRange().autoFitColumns();
                sheet3.getAllocatedRange().autoFitRows();                
                sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
                keresett.saveToFile(menteshelye, ExcelVersion.Version2016);            
                FileInputStream fileStream = new FileInputStream(menteshelye);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(menteshelye);
                    workbook2.write(output);
                    output.close();
                }
                Foablak.frame.setCursor(null); 
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Jelenléti összesítő.xlsx", "Info", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }

}
