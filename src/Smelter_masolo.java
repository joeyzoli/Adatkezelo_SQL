import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Smelter_masolo extends JPanel {
    
    private String hely = "";
    private File mappa;
    private File[] fajlok;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Összefésült Smelterek.xlsx";
    private JTextField smelter_mezo;
    private JTextField lap_mezo;

    /**
     * Create the panel.
     */
    public Smelter_masolo() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Smelter adatok összemásolása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(462, 40, 238, 14);
        add(lblNewLabel);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Kereses());
        megnyit_gomb.setBounds(595, 100, 89, 23);
        add(megnyit_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Mappa megnyitása");
        lblNewLabel_1.setBounds(435, 104, 118, 14);
        add(lblNewLabel_1);
        
        setBackground(Foablak.hatter_szine);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(48, 144, 1106, 23);
        add(separator);
        
        JLabel lblNewLabel_2 = new JLabel("Keresendő Smelter");
        lblNewLabel_2.setBounds(435, 232, 118, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Smelter kereső");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_3.setBounds(493, 178, 143, 14);
        add(lblNewLabel_3);
        
        smelter_mezo = new JTextField();
        smelter_mezo.setBounds(550, 229, 506, 20);
        add(smelter_mezo);
        smelter_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Melyik lapon");
        lblNewLabel_4.setBounds(435, 283, 89, 14);
        add(lblNewLabel_4);
        
        lap_mezo = new JTextField();
        lap_mezo.setText("Smelter List");
        lap_mezo.setBounds(550, 280, 86, 20);
        add(lap_mezo);
        lap_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Mappa megnyitása");
        lblNewLabel_5.setBounds(435, 331, 118, 14);
        add(lblNewLabel_5);
        
        JButton mappa_gomb = new JButton("Megnyitás");
        mappa_gomb.addActionListener(new Megnyitas());
        mappa_gomb.setBounds(550, 327, 109, 23);
        add(mappa_gomb);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Smelter_keres());
        keres_gomb.setBounds(498, 382, 89, 23);
        add(keres_gomb);

    }
    
    class Kereses implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            File excel = null;
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\10.1.0.11\\minosegbiztositas\\RoHS,Reach, CFSI\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                {
                    hely = fajl.getAbsolutePath();
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
                    Workbook összesites = new Workbook();
                    Worksheet sheet3 = összesites.getWorksheets().get(0);
                    int cellaszam = 1;
                    
                    sheet3.getRange().get("A" + cellaszam).setText("Smelter Identification Number");
                    sheet3.getRange().get("B" + cellaszam).setText("Metal");
                    sheet3.getRange().get("C" + cellaszam).setText("Smelter Look-up");
                    sheet3.getRange().get("D" + cellaszam).setText("Smelter Name");
                    sheet3.getRange().get("E" + cellaszam).setText("Smelter Country");
                    sheet3.getRange().get("F" + cellaszam).setText("Smelter Identification");
                    sheet3.getRange().get("G" + cellaszam).setText("Source of Smelter");
                    sheet3.getRange().get("H" + cellaszam).setText("Fájl neve");
                    cellaszam++;
                    fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                    Workbook workbook = new Workbook();
                    for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                    {
                        String fajlneve = fajlok[szamlalo].getName();
                        if(fajlneve.startsWith("~$")){}
                        else
                        {               
                            excel = new File(hely+ "\\" +fajlok[szamlalo].getName());
                                         
                            workbook.loadFromFile(excel.getAbsolutePath());
                            Worksheet sheet = null;
                            for (Object sheet2: workbook.getWorksheets()) {
                                String sheetName = ((Worksheet) sheet2).getName();                            
                                System.out.println(sheetName);
                                if(sheetName.contains("Smelter List"))
                                {
                                    sheet = workbook.getWorksheets().get(sheetName);
                                }
                            }
                                               
                            String[] nev = excel.getName().split("\\.");
                            DataTable datatable = new DataTable();
                            datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );         //sheet.getAllocatedRange()
                            //System.out.println(sheet.getLastRow());
                            int cellaszam2 = 5;
                            for(int szamlalo3 = 1; szamlalo3 < datatable.getRows().size(); szamlalo3++)
                            {
                                if(sheet.getRange().get("A" + cellaszam2).getValue().equals("")) {break;}
                                else
                                {
                                    sheet3.getRange().get("A" + cellaszam).setText(sheet.getRange().get("A" + cellaszam2).getText());           //sheet.getRange().get("A" + cellaszam2).getText()
                                    if(sheet.getRange().get("B" + cellaszam2).getText().equals(""))
                                    {
                                        sheet3.getRange().get("B" + cellaszam).setText(sheet.getRange().get("B" + cellaszam2).getFormulaStringValue());                   //datatable.getRows().get(cellaszam2).getString(1)
                                        sheet3.getRange().get("C" + cellaszam).setText(sheet.getRange().get("C" + cellaszam2).getFormulaStringValue());
                                        sheet3.getRange().get("D" + cellaszam).setText(sheet.getRange().get("D" + cellaszam2).getFormulaStringValue());                                               
                                        sheet3.getRange().get("E" + cellaszam).setText(sheet.getRange().get("E" + cellaszam2).getFormulaStringValue());
                                        sheet3.getRange().get("F" + cellaszam).setText(sheet.getRange().get("F" + cellaszam2).getFormulaStringValue());
                                        sheet3.getRange().get("G" + cellaszam).setText(sheet.getRange().get("G" + cellaszam2).getFormulaStringValue());
                                        sheet3.getRange().get("H" + cellaszam).setText(nev[0]);
                                        cellaszam++;
                                        cellaszam2++;
                                        System.out.println("Találat");
                                    }
                                    else
                                    {
                                        sheet3.getRange().get("B" + cellaszam).setText(sheet.getRange().get("B" + cellaszam2).getText());                   //datatable.getRows().get(cellaszam2).getString(1)
                                        sheet3.getRange().get("C" + cellaszam).setText(sheet.getRange().get("C" + cellaszam2).getText());
                                        sheet3.getRange().get("D" + cellaszam).setText(sheet.getRange().get("D" + cellaszam2).getText());                                               
                                        sheet3.getRange().get("E" + cellaszam).setText(sheet.getRange().get("E" + cellaszam2).getText());
                                        sheet3.getRange().get("F" + cellaszam).setText(sheet.getRange().get("F" + cellaszam2).getText());
                                        sheet3.getRange().get("G" + cellaszam).setText(sheet.getRange().get("G" + cellaszam2).getText());
                                        sheet3.getRange().get("H" + cellaszam).setText(nev[0]);
                                        cellaszam++;
                                        cellaszam2++;
                                        System.out.println("Találat");
                                    }
                                }
                            }
                            System.out.println("Fájl átnézve");
                        }
                    }
                    
                    sheet3.getAutoFilters().setRange(sheet3.getCellRange("A1:Z1"));
                    sheet3.getAllocatedRange().autoFitColumns();
                    sheet3.getAllocatedRange().autoFitRows();                
                    sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás  
                    menteshelye = System.getProperty("user.home") + "\\Desktop\\Összefésült Smelterek.xlsx";
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
                }
                Foablak.frame.setCursor(null); 
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Összefésült Smelterek.xlsx", "Info", 1);
            } 
            catch (Exception e1) 
            {
                
                System.out.println(excel.getName());
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, excel.getName()+"  " +hibauzenet, "Hiba üzenet", 2);
                Foablak.frame.setCursor(null); 
            }
         }
    }
    
    class Megnyitas implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {            
            try 
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\10.1.0.11\\minosegbiztositas\\RoHS,Reach, CFSI\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                mentes_helye.showOpenDialog(mentes_helye);
                mappa = mentes_helye.getSelectedFile();
                                
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                Foablak.frame.setCursor(null); 
            }
         }
    }
    
    class Smelter_keres implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            File excel = null;
            try 
            {                
                if(mappa != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    hely = mappa.getAbsolutePath();
                    FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                    {
                        
                        @Override
                        public boolean accept(File f, String name) 
                        {
                                                                                                            // csak az xlsx fájlokat listázza ki 
                            return name.endsWith(".xlsx");  
                        }
                    };               
                    Workbook összesites = new Workbook();
                    Worksheet sheet3 = összesites.getWorksheets().get(0);
                    int cellaszam = 1;
                    
                    sheet3.getRange().get("A" + cellaszam).setText("Smelter");
                    sheet3.getRange().get("B" + cellaszam).setText("Fájl neve");
                    sheet3.getRange().get("C" + cellaszam).setText("Cella");
                    
                    cellaszam++;
                    fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                    Workbook workbook = new Workbook();
                    for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                    {
                        String fajlneve = fajlok[szamlalo].getName();
                        if(fajlneve.startsWith("~$")){}
                        else
                        {               
                            excel = new File(hely+ "\\" +fajlok[szamlalo].getName());
                                         
                            workbook.loadFromFile(excel.getAbsolutePath());
                            Worksheet sheet = null;
                            for (Object sheet2: workbook.getWorksheets()) {
                                String sheetName = ((Worksheet) sheet2).getName();                            
                                System.out.println(sheetName);
                                if(sheetName.contains(lap_mezo.getText()))
                                {
                                    sheet = workbook.getWorksheets().get(sheetName);
                                }
                            }
                                               
                            String[] nev = excel.getName().split("\\.");
                            if(sheet != null)
                            {
                                DataTable datatable = new DataTable();
                                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );         //sheet.getAllocatedRange()
                                //System.out.println(sheet.getLastRow());
                                String[] smelterek = smelter_mezo.getText().split(";");
                                for(int szamlalo2 = 0; szamlalo2 < smelterek.length; szamlalo2++)
                                {
                                    for(int szamlalo3 = 4; szamlalo3 < datatable.getRows().size(); szamlalo3++)
                                    {
                                        if(datatable.getRows().get(szamlalo3).getString(0).equals(smelterek[szamlalo2]))
                                        {
                                            sheet3.getRange().get("A" + cellaszam).setText(smelterek[szamlalo2]);
                                            sheet3.getRange().get("B" + cellaszam).setText(nev[0]);
                                            sheet3.getRange().get("C" + cellaszam).setText("A"+ (szamlalo3+1));
                                            cellaszam++;
                                        }
                                    }
                                }
                                System.out.println("Fájl átnézve");
                            }
                            else
                            {
                                sheet3.getRange().get("A" + cellaszam).setText(nev[0]);
                                sheet3.getRange().get("B" + cellaszam).setText("Nincs a fájlba a megadott lap név");
                                cellaszam++;
                            }
                        }
                    }
                    
                    sheet3.getAutoFilters().setRange(sheet3.getCellRange("A1:Z1"));
                    sheet3.getAllocatedRange().autoFitColumns();
                    sheet3.getAllocatedRange().autoFitRows();                
                    sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    menteshelye = System.getProperty("user.home") + "\\Desktop\\Megkeresett Smelter-ek.xlsx";
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
                    JOptionPane.showMessageDialog(null, "Kész, mentve az asztalra Megkeresett Smelter-ek.xlsx néven", "Info", 1);
                }
                 
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, excel.getName()+"  " +hibauzenet, "Hiba üzenet", 2);
                Foablak.frame.setCursor(null); 
            }
         }
    }
}
