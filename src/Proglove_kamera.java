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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Proglove_kamera extends JPanel {
    
    private String hely = "";
    private String excelhely = "";
    private File mappa;
    private File[] fajlok;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Proglove kamera.xlsx";

    /**
     * Create the panel.
     */
    public Proglove_kamera() {
        setLayout(null);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Kereses());
        start_gomb.setBounds(482, 257, 89, 23);
        add(start_gomb);
        
        JLabel lblNewLabel = new JLabel("Proglove kamera reklmációkban alkatrész szám keresése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(364, 91, 429, 14);
        add(lblNewLabel);
        
        JButton mappa_gomb = new JButton("Megnyitás");
        mappa_gomb.addActionListener(new Mappa());
        mappa_gomb.setBounds(543, 167, 106, 23);
        add(mappa_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Mappa kiválasztása");
        lblNewLabel_1.setBounds(417, 171, 116, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Amit keres excel");
        lblNewLabel_2.setBounds(417, 208, 98, 14);
        add(lblNewLabel_2);
        
        JButton fajl_gomb = new JButton("Megnyitás");
        fajl_gomb.addActionListener(new Excel());
        fajl_gomb.setBounds(543, 204, 106, 23);
        add(fajl_gomb);

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
                cellaszam++;
                fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja                      
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    if(datatable.getRows().get(szamlalo).getString(0).equals("")){}
                    else
                    {
                        for(int szamlalo2 = 0; szamlalo2 < fajlok.length; szamlalo2++)
                        {
                            String fajlneve = fajlok[szamlalo2].getName();
                            if(fajlneve.startsWith("~$")){}
                            else
                            {               
                                File excel = new File(hely+ "\\" +fajlok[szamlalo2].getName());
                                Workbook workbook = new Workbook();             
                                workbook.loadFromFile(excel.getAbsolutePath());
                                Worksheet sheet = workbook.getWorksheets().get(0);
                                String[] nev = excel.getName().split("\\.");
                                //DataTable datatable2 = new DataTable();
                                //datatable2 = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                                //System.out.println(sheet.getLastRow());
                                if(datatable.getRows().get(szamlalo).getString(0).equals("")){}
                                else
                                {
                                    for(int szamlalo3 = 1; szamlalo3 < sheet.getLastRow(); szamlalo3++)
                                    {
                                        
                                        if(sheet.getRange().get("A" + szamlalo3).getText().toString().equals("")){}
                                        else
                                        {
                                            //System.out.println(sheet.getRange().get("A" + szamlalo3).getText().toString());
                                            String osszefuzve = "S"+datatable.getRows().get(szamlalo).getString(0);
                                            if(osszefuzve.contains(sheet.getRange().get("A" + szamlalo3).getText().toString()))
                                            {                                                                        
                                                sheet3.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                                                sheet3.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                                sheet3.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(2));
                                                String[] koztes = nev[0].toString().split(" ");
                                                sheet3.getRange().get("D" + cellaszam).setText(koztes[1]);                                               
                                                sheet3.getRange().get("E" + cellaszam).setText(ifs(koztes[1]));
                                                cellaszam++;
                                                System.out.println("Találat");
                                            }
                                        }
                                    }
                                }
                                System.out.println("Fájl átnézve");
                            }
                               
                        }
                    }            
                }
                
                sheet3.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet3.getAllocatedRange().autoFitColumns();
                sheet3.getAllocatedRange().autoFitRows();                
                sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
                összesites.saveToFile(menteshelye, ExcelVersion.Version2016);            
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
    
    private String ifs(String keresett)                 //rendelési szám alapján kikeresé mikor érkezett be a termék                                                                           
    {
        String visszatero = "";
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();                      
            
            ResultSet rs = stmt.executeQuery("select arrival_date\r\n"
                    + "from ifsapp.PURCHASE_RECEIPT_NEW\r\n"
                    + "where 3=3\r\n"
                    + "and RECEIPT_REFERENCE = '"+ keresett +"'");
            if(rs.next())
            {  
                visszatero = rs.getString(1);
            }
              
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        return visszatero;  
    }
    
    class Mappa implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\Proglove kamera szállítólevelek\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajlok = mentes_helye.getSelectedFile();
                hely = fajlok.getAbsolutePath();
                Foablak.frame.setCursor(null);                 
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Excel implements ActionListener                                                                                        //Gomb megnoymásakor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\10.1.0.11\\minosegbiztositas\\SQA\\reklamációk\\Proglove kamera szállítólevelek\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajlok = mentes_helye.getSelectedFile();
                excelhely = fajlok.getAbsolutePath();
                Foablak.frame.setCursor(null);                 
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
