import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
import com.spire.xls.VerticalAlignType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

public class Jelenleti_osszesito extends JPanel {
    
    private JComboBox<String> honap_box;
    private String[] honapok = {"Január","Február","Március","Április","Május","Június","Július","Augusztus","Szeptember","Október","November","December"};
    private String hely = "\\\\\\10.1.0.11\\minosegbiztositas\\Érk.Táv\\";
    private String excelhely = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Jelenléti összesítő2.xlsx";
    private File mappa;
    private File[] fajlok;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Jelenléti összesítő.xlsx";

    /**
     * Create the panel.
     */
    public Jelenleti_osszesito() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Jelenléti összesítő készítő");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(477, 35, 253, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel2 = new JLabel("Hónap kiválasztása");
        lblNewLabel2.setBounds(417, 156, 125, 14);
        add(lblNewLabel2);
        
        honap_box = new JComboBox<String>(honapok);                 //honapok
        honap_box.setBounds(569, 152, 161, 22);
        add(honap_box);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.setBounds(513, 208, 89, 23);
        start_gomb.addActionListener(new Excelcsinal());
        add(start_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Mappa kiválasztása");
        lblNewLabel_1.setBounds(417, 87, 123, 14);
        add(lblNewLabel_1);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Mappa());
        megnyit_gomb.setBounds(569, 83, 125, 23);
        add(megnyit_gomb);

    }
    
    class Excelcsinal implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int honap = 0;
                if(honap_box.getSelectedItem().equals("Január")){              
                    honap = 0;
                }
                else if(honap_box.getSelectedItem().equals("Február")){              
                    honap = 1;
                }
                else if(honap_box.getSelectedItem().equals("Március")){              
                    honap = 2;
                }
                else if(honap_box.getSelectedItem().equals("Április")){              
                    honap = 3;
                }
                else if(honap_box.getSelectedItem().equals("Május")){              
                    honap = 4;
                }
                else if(honap_box.getSelectedItem().equals("Június")){              
                    honap = 5;
                }
                else if(honap_box.getSelectedItem().equals("Július")){              
                    honap = 6;
                }
                else if(honap_box.getSelectedItem().equals("Augusztus")){              
                    honap = 7;
                }
                else if(honap_box.getSelectedItem().equals("Szeptember")){              
                    honap = 8;
                }
                else if(honap_box.getSelectedItem().equals("Október")){              
                    honap = 9;
                }
                else if(honap_box.getSelectedItem().equals("November")){              
                    honap = 10;
                }
                else if(honap_box.getSelectedItem().equals("December")){              
                    honap = 11;
                }                
                beolvasas(honap, hely);
                setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
    
    class Mappa implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\10.1.0.11\\minosegbiztositas\\Érk.Táv\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                hely = fajl.getAbsolutePath()+"\\";
                
                 
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    public void beolvasas(int oldalszam, String hely)
    {
        try
        {
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
            Workbook osszesites = new Workbook();
            osszesites.loadFromFile(excelhely);
            Worksheet sheet2 = osszesites.getWorksheets().get(0);
            sheet2.getRange().get(1, 1).setText("Név");
            fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja         
            int ujsor = 2;
            for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
            {
                String fajlneve = fajlok[szamlalo].getName();
                if(fajlneve.startsWith("~$")){}
                else
                {               
                    File excel = new File(hely +fajlok[szamlalo].getName());
                    Workbook workbook = new Workbook();             
                    workbook.loadFromFile(excel.getAbsolutePath());
                    Worksheet sheet = workbook.getWorksheets().get(oldalszam);
                    String[] nev = excel.getName().split("\\."); 
                    sheet2.getRange().get(ujsor, 1).setText(nev[0]);
                    int sor = 6;
                    int ujcella = 2;
                    int datumcella = 2;
                    for(int szamlalo2 = 6; szamlalo2 < 37; szamlalo2++)
                    {
                        if(sheet.getRange().get(sor, 1).getText().contains("Sat"))
                        {
                            ujcella--;datumcella--;
                        }
                        else if(sheet.getRange().get(sor, 1).getText().contains("Sun"))
                        {
                            ujcella--;datumcella--;
                        }
                        else if(sheet.getRange().get(sor, 1).getText().equals(""))
                        {
                            ujcella--;datumcella--;
                        }
                        else
                        {
                            if(sheet.getRange().get(sor, 2).getText().equals(""))
                            {
                                String[] idopont = sheet.getRange().get(sor, 1).getText().split("/");
                                if(idopont.length > 1)
                                {
                                    sheet2.getRange().get(1, datumcella).setText(idopont[1]+"."+idopont[2]);
                                }
                                else
                                {
                                    sheet2.getRange().get(1, datumcella).setText(sheet.getRange().get(sor, 1).getText());
                                }
                                String[] muszak = sheet.getRange().get(sor, 3).getText().split(":");
                                if(muszak.length > 1)
                                {
                                    if(Integer.valueOf(muszak[0]) < 6)
                                    {
                                        sheet2.getRange().get(ujsor, ujcella).setText("6:00-\n14:00\nA");
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                    }
                                    else if(Integer.valueOf(muszak[0]) < 12)
                                    {
                                        sheet2.getRange().get(ujsor, ujcella).setText("8:00-\n16:25\nA");
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                    }
                                    else
                                    {
                                        sheet2.getRange().get(ujsor, ujcella).setText("14:00-\n22:00\nA");
                                        sheet2.getRange().get(ujsor, ujcella).getStyle().setColor(Color.green);
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                        sheet2.getCellRange(ujsor, ujcella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                    }
                                }
                                //sheet2.getRange().get(ujsor, ujcella).setNumberFormat("H:mm:ss");
                                /*String[] ido = sheet2.getRange().get(ujsor, ujcella).getText().split(":");
                                if(ido.length > 0)
                                {
                                    if(ido[0].equals(""))
                                    {
                                        
                                    }
                                    else
                                    {
                                        if(Integer.parseInt(ido[0]) < 8)        // || Integer.parseInt(ido[1]) < 25
                                        {
                                            sheet2.getRange().get(ujsor, ujcella).getStyle().setColor(Color.yellow);
                                        }
                                        ora += Integer.parseInt(ido[0]);
                                        perc += Integer.parseInt(ido[1]);
                                    }                               
                                }*/
                            }
                            else
                            {
                                String[] idopont = sheet.getRange().get(sor, 1).getText().split("/");                           
                                if(idopont.length > 1)
                                {
                                    sheet2.getRange().get(1, datumcella).setText(idopont[1]+"."+idopont[2]);
                                }
                                else
                                {
                                    sheet2.getRange().get(1, datumcella).setText(sheet.getRange().get(sor, 1).getText());
                                }
                                
                                if(sheet.getRange().get(sor, 2).getText().toLowerCase().contains("szabad"))
                                {
                                    sheet2.getRange().get(ujsor, ujcella).setText("F");
                                    sheet2.getRange().get(ujsor, ujcella).getStyle().setColor(Color.red);
                                    sheet2.getCellRange(ujsor, ujcella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                    sheet2.getCellRange(ujsor, ujcella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                }
                                if(sheet.getRange().get(sor, 2).getText().toLowerCase().contains("beteg"))
                                {
                                    sheet2.getRange().get(ujsor, ujcella).setText("B");
                                    sheet2.getRange().get(ujsor, ujcella).getStyle().setColor(Color.red);
                                    sheet2.getCellRange(ujsor, ujcella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                    sheet2.getCellRange(ujsor, ujcella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                }
                                
                                
                                /*sheet2.getRange().get(ujsor, ujcella).getStyle().setColor(Color.blue);
                                ora += 8;
                                perc += 0;*/
                            }
                        }
                        //String currentFormula = "=Y"+ujsor+"/"+oszto;
                        //sheet2.getRange().get(ujsor, 26).setFormula(currentFormula);
                        sor++;datumcella++;ujcella++;
                    }
                    //int pluszora = perc/60;
                    //int maradekperc = perc % 60;
                    //ora += pluszora;
                    //sheet2.getRange().get("Y" + ujsor).setText(ora +":"+maradekperc);
                    //sheet2.getRange().get("Y" + ujsor).setNumberFormat("HHH:mm");
                    ujsor++;
                }
            }
            System.out.println(sheet2.getLastColumn());
            System.out.println(sheet2.getLastRow());
            sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
            sheet2.getAllocatedRange().autoFitColumns();
            sheet2.getAllocatedRange().autoFitRows();                
            sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
            osszesites.saveToFile(menteshelye, ExcelVersion.Version2016);            
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
            JOptionPane.showMessageDialog(null, "Mentve az asztalra Jelenléti összesítő.xlsx", "Info", 1);
        }
         catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
    }
}
