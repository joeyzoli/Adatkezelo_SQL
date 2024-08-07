import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.CellRange;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Excel_kereso extends JPanel {
    
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Összefésült.xlsx";
    private List<File> files = new ArrayList<File>();
    private Workbook összesites;
    private Worksheet munkalap;
    private Workbook workbook = new Workbook();
    private int fajlszam = 1;
    private JTextField mukalap_mezo;
    private File mappa;

    /**
     * Create the panel.
     */
    public Excel_kereso() {
        
        setBounds(100, 100, 1120, 620);
        setLayout(null);
 
        JButton open_gomb = new JButton("Open folder");
        open_gomb.addActionListener(new Megnyit());
        open_gomb.setBounds(530, 120, 163, 23);
        add(open_gomb);
        
        összesites = new Workbook();
        összesites.setVersion(ExcelVersion.Version2016);
        munkalap = összesites.getWorksheets().get(0);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Mappa megynitása");
        lblNewLabel.setBounds(361, 120, 132, 18);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Munkalap amin keres");
        lblNewLabel_1.setBounds(361, 168, 132, 14);
        add(lblNewLabel_1);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Kereses());
        start_gomb.setBounds(465, 226, 89, 23);
        add(start_gomb);
        
        mukalap_mezo = new JTextField();
        mukalap_mezo.setBounds(530, 165, 163, 20);
        add(mukalap_mezo);
        mukalap_mezo.setColumns(10);

    }
    
    class Kereses implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {           
            try 
            {
                
                if(mappa != null)
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    
                    listf(mappa.getAbsolutePath(), files);
                                  
                    //munkalap.getAutoFilters().setRange(munkalap.getCellRange("A1:K1"));
                    munkalap.getAllocatedRange().autoFitColumns();
                    munkalap.getAllocatedRange().autoFitRows();                
                    munkalap.getCellRange("A1:K1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás     
                    
                    try 
                    {
                        összesites.saveToFile(menteshelye, ExcelVersion.Version2016);            
                        FileInputStream fileStream = new FileInputStream(menteshelye);
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(menteshelye);               //menteshelye
                            workbook2.write(output);
                            output.close();
                        }
                        JOptionPane.showMessageDialog(null, "File saved to desktop with name Összefésült.xlsx", "Info", 1);
                    }
                    catch (Exception e1) 
                    {
                        System.out.println("file is locked");
                        String hova = System.getProperty("user.home") + "\\Desktop\\Összefésült_"+ fajlszam +".xlsx";
                        workbook.saveToFile(hova, ExcelVersion.Version2016);
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook3.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook3.write(output);
                            output.close();
                        }
                        JOptionPane.showMessageDialog(null, "File saved to desktop with name Összefésült_"+ fajlszam +".xlsx", "Info", 1);
                        fajlszam++;
                    }
                }
                setCursor(null); 
                
            } 
            catch (Exception e1) 
            {                
                e1.printStackTrace();
                String hibauzenet = e1.toString();               
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                setCursor(null); 
            }
         }
    }
    
    public void listf(String directoryName, List<File> files) {     
        File directory = new File(directoryName);
        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null)
            for (File file : fList) {      
                if (file.isFile()) {
                    System.out.println("Fájl megtalálva itt "+ file.getAbsolutePath());
                    if(file.getName().startsWith("~$")){}
                    else
                    { 
                        files.add(file);
                        workbook.loadFromFile(file.getAbsolutePath());
                        Worksheet sheet = null;
                        int van = 0;
                        for (Object sheet2: workbook.getWorksheets()) {
                            String sheetName = ((Worksheet) sheet2).getName();                            
                            System.out.println(sheetName);
                            if(sheetName.contains(mukalap_mezo.getText()))
                            {
                                sheet = workbook.getWorksheets().get(sheetName);
                                van = 1;
                            }
                        }
                        /*for(int szamlalo = 2; szamlalo < sheet.getLastRow()+1; szamlalo++)
                        {                       
                            if(sheet.getRange().get("A" + szamlalo).getText().contains("2E1129") || sheet.getRange().get("A" + szamlalo).getText().contains("2E0964"))
                            {
                                munkalap.getRange().get("A" + cellaszam).setText(sheet.getRange().get("A" + szamlalo).getText());
                                munkalap.getRange().get("B" + cellaszam).setText(sheet.getRange().get("B" + szamlalo).getText());
                                munkalap.getRange().get("C" + cellaszam).setText(sheet.getRange().get("C" + szamlalo).getText());
                                munkalap.getRange().get("D" + cellaszam).setText(sheet.getRange().get("D" + szamlalo).getText());
                                munkalap.getRange().get("E" + cellaszam).setText(sheet.getRange().get("E" + szamlalo).getText());
                                munkalap.getRange().get("F" + cellaszam).setText(sheet.getRange().get("F" + szamlalo).getText());
                                munkalap.getRange().get("G" + cellaszam).setText(sheet.getRange().get("G" + szamlalo).getText());
                                munkalap.getRange().get("H" + cellaszam).setText(sheet.getRange().get("H" + szamlalo).getText());
                                munkalap.getRange().get("I" + cellaszam).setText(sheet.getRange().get("I" + szamlalo).getText());
                                munkalap.getRange().get("J" + cellaszam).setText(sheet.getRange().get("J" + szamlalo).getText());
                                munkalap.getRange().get("K" + cellaszam).setText(file.getAbsolutePath());
                                cellaszam++;
                            }                        
                        }*/
                        if(van == 1)
                        {
                            munkalap.getRange().get("A1").setText(sheet.getRange().get("A1").getText());
                            CellRange sourceRange = sheet.getCellRange("A2:AM"+sheet.getLastRow());
                            CellRange destRange = munkalap.getCellRange("A"+ (munkalap.getLastRow()+1) +":AM"+(munkalap.getLastRow() + sheet.getLastRow()));

                            //Copy a specific cell range from sheet1 to sheet2
                            sheet.copy (sourceRange,destRange,true);
                        }
                    }
                } else if (file.isDirectory()) {
                    listf(file.getAbsolutePath(), files);
                }
            }
    }
    
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    class Megnyit implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {           
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                mentes_helye.showOpenDialog(mentes_helye);
                mappa = mentes_helye.getSelectedFile();       
            } 
            catch (Exception e1) 
            {                
                e1.printStackTrace();
                String hibauzenet = e1.toString();               
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                setCursor(null); 
            }
         }
    }
}
