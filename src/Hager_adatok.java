import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.CellRange;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Hager_adatok extends JPanel {
    
    private String osszefuzott = "";
    private String osszefuzott2 = "";;
    private String osszefuzott3 = "";;
    private String osszefuzott4 = "";;
    private String osszefuzott5 = "";;
    private String osszefuzott6 = "";;
    private String osszefuzott7 = "";;
    private String osszefuzott8 = "";;
    private String osszefuzott9 = "";
    private JTextField iranyszam_mezo;
    private JTextField datum_mezo;;

    /**
     * Create the panel.
     */
    public Hager_adatok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Kiszállított panelek adatainak keresése - ME számot vár");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel.setBounds(290, 163, 316, 14);
        add(lblNewLabel);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(630, 159, 89, 23);
        add(megnyit_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_1 = new JLabel("Hager adatok keresése");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(534, 59, 199, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Raktárban levő szériaszámok - Cikkszámot vár");
        lblNewLabel_2.setBounds(290, 211, 316, 14);
        add(lblNewLabel_2);
        
        JButton megnyit2_gomb = new JButton("Megnyitás");
        megnyit2_gomb.addActionListener(new Lekerdezes2());
        megnyit2_gomb.setBounds(630, 207, 89, 23);
        add(megnyit2_gomb);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(66, 250, 1021, 23);
        add(separator);
        
        JLabel lblNewLabel_3 = new JLabel("Irányszám");
        lblNewLabel_3.setBounds(473, 298, 109, 14);
        add(lblNewLabel_3);
        
        iranyszam_mezo = new JTextField();
        iranyszam_mezo.setBounds(630, 295, 155, 20);
        add(iranyszam_mezo);
        iranyszam_mezo.setColumns(10);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(630, 326, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Dátum");
        lblNewLabel_4.setBounds(473, 329, 70, 14);
        add(lblNewLabel_4);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Lekerdezes3());
        keres_gomb.setBounds(555, 370, 89, 23);
        add(keres_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();                
                Workbook workbook = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016);
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    workbook.setVersion(ExcelVersion.Version2016);
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    DataTable datatable2 = new DataTable();
                    DataTable datatable3 = new DataTable();
                    DataTable datatable4 = new DataTable();
                    DataTable datatable5 = new DataTable();
                    DataTable datatable6 = new DataTable();
                    DataTable datatable7 = new DataTable();
                    DataTable datatable8 = new DataTable();
                    DataTable datatable9 = new DataTable();
                    DataTable datatable10 = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    String osszefuzott_me = "";
                    
                    Workbook workbook2 = new Workbook();
                    workbook2.setVersion(ExcelVersion.Version2016);
                    Workbook workbook4 = new Workbook();
                    workbook4.setVersion(ExcelVersion.Version2016);
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                    Worksheet sheet3 = workbook2.getWorksheets().get(1);
                    Worksheet sheet4 = workbook2.getWorksheets().get(2);
                    Worksheet sheet5 = workbook4.getWorksheets().get(0);
                     
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                     //jdbc mysql driver meghÃ­vÃ¡sa
                    
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                    Connection con;                   
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                    Statement stmt = con.createStatement();
                    
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        osszefuzott_me += ("'" + datatable.getRows().get(szamlalo).getString(0) +"',");
                    }
                    osszefuzott_me = osszefuzott_me.substring(0, osszefuzott_me.length() - 1);
                    
                    
                    ResultSet rs = stmt.executeQuery("SELECT panel FROM videoton.csomagol where csomag in ("+ osszefuzott_me +")");
                    
                    int cellaszam = 1;
                    sheet2.getRange().get("A" + cellaszam).setText("Azonosító");
                    sheet2.getRange().get("B" + cellaszam).setText("Munkahely száma");
                    sheet2.getRange().get("C" + cellaszam).setText("Munkahely neve");
                    sheet2.getRange().get("D" + cellaszam).setText("Dátum");
                    sheet2.getRange().get("E" + cellaszam).setText("Panelszám");
                    sheet2.getRange().get("F" + cellaszam).setText("Teszter szám");
                    sheet2.getRange().get("G" + cellaszam).setText("Eredmény");
                    sheet2.getRange().get("H" + cellaszam).setText("Hibakód");
                    sheet2.getRange().get("I" + cellaszam).setText("kód2");
                    sheet2.getRange().get("J" + cellaszam).setText("torolt");
                    sheet2.getRange().get("K" + cellaszam).setText("Szériaszám");
                    sheet2.getRange().get("L" + cellaszam).setText("Teszt szám");
                    sheet2.getRange().get("M" + cellaszam).setText("Pozició");
                    sheet2.getRange().get("N" + cellaszam).setText("Teljes szám");
                    sheet2.getRange().get("O" + cellaszam).setText("Hibakód");
                    sheet2.getRange().get("P" + cellaszam).setText("error");
                    sheet2.getRange().get("Q" + cellaszam).setText("Dolgozó");
                    sheet2.getRange().get("R" + cellaszam).setText("Érvénytelenítve");
                    cellaszam++;
                    
                    int szam = 0;
                    while(rs.next())
                    {                        
                        if(szam < 1000)
                        {                            
                            osszefuzott += ("'" + rs.getString(1) +"',");                          
                        }
                        else if(szam >= 1000 && szam < 2000)
                        {                            
                            osszefuzott2 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else if(szam >= 2000  && szam < 3000)
                        {                           
                            osszefuzott3 += ("'" + rs.getString(1) +"',");                                    
                        }
                        else if(szam >= 3000  && szam < 4000)
                        {                          
                            osszefuzott4 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else if(szam >= 4000  && szam < 5000)
                        {                            
                            osszefuzott5 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else if(szam >= 5000  && szam < 6000)
                        {                            
                            osszefuzott6 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else if(szam >= 6000  && szam < 7000)
                        {                            
                            osszefuzott7 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else if(szam >= 7000  && szam < 8000)
                        {                           
                            osszefuzott8 += ("'" + rs.getString(1) +"',");                                     
                        }
                        else
                        {                            
                            osszefuzott9 += ("'" + rs.getString(1) +"',");                                     
                        }
                        szam++;
                        System.out.println(szam);
                    }
                    
                    osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                    if(osszefuzott2 != "")
                    {   
                        osszefuzott2 = osszefuzott2.substring(0, osszefuzott2.length() - 1);
                    }
                    
                    if(osszefuzott3 != "")
                    {   
                        osszefuzott3 = osszefuzott3.substring(0, osszefuzott3.length() - 1);
                    }
                    
                    if(osszefuzott4 != "")
                    {   
                        osszefuzott4 = osszefuzott4.substring(0, osszefuzott4.length() - 1);
                    }
                    
                    if(osszefuzott5 != "")
                    {   
                        osszefuzott5 = osszefuzott5.substring(0, osszefuzott5.length() - 1);
                    }
                    
                    if(osszefuzott6 != "")
                    {   
                        osszefuzott6 = osszefuzott6.substring(0, osszefuzott6.length() - 1);
                    }
                    
                    if(osszefuzott7 != "")
                    {   
                        osszefuzott7 = osszefuzott7.substring(0, osszefuzott7.length() - 1);
                    }
                    
                    if(osszefuzott8 != "")
                    {   
                        osszefuzott8 = osszefuzott8.substring(0, osszefuzott8.length() - 1);
                    }
                    
                    if(osszefuzott9 != "")
                    {   
                        osszefuzott9 = osszefuzott9.substring(0, osszefuzott9.length() - 1);
                    }
                    
                    rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                            + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                            + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                            + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                            + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                            + "from videoton.fkov \n"
                            + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                            + " where videoton.fkov.panel in ("+ osszefuzott +")");
                                            
                      while(rs.next())
                      {                        
                          sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                          sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                          sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                          sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                          sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                          sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                          sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                          sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                          sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                          sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                          sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                          sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                          sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                          sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                          sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                          sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                          cellaszam++;
                      }
                      if(osszefuzott2.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott2 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott3.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott3 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott4.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott4 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott5.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott5 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott6.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott6 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott7.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott7 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott8.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott8 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      if(osszefuzott9.equals("")) {}
                      else
                      {
                          rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo,  if(videoton.fkov.torolt in ('-1', '1'), \"Igen\", \"Nem\") as Torolt \n"
                                  + "from videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel in ("+ osszefuzott9 +")");
                                                  
                            while(rs.next())
                            {                        
                                sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                sheet2.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                sheet2.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                sheet2.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                sheet2.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                sheet2.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                sheet2.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                sheet2.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                sheet2.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                sheet2.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                sheet2.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                sheet2.getRange().get("R" + cellaszam).setText(rs.getString(18));
                                cellaszam++;
                            }
                      }
                      
                      /////////////////////////////////////////////////////////////////////////////////////////////////////
                      rs = stmt.executeQuery("select  hibakod "
                              + "from videoton.fkov \n"
                              + " \n"
                              + " where videoton.fkov.panel in ("+ osszefuzott +") and hely = '58' group by hibakod");
                      
                      String osszefuzott10 = osszefuzott;
                      osszefuzott = "";
                      while(rs.next())
                      {  
                          osszefuzott += ("'" + rs.getString(1) +"',");
                      }
                      if(osszefuzott.equals(""))
                      {
                          osszefuzott = osszefuzott10;
                      }
                      else
                      {
                          osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                      }
                      
                      
                      rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                              + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                              + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                              + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                              + "videoton.fkov.dolgozo \n"
                              + "from videoton.fkov \n"
                              + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                              + " where videoton.fkov.panel in ("+ osszefuzott +")");
                        cellaszam = 1;
                        sheet3.getRange().get("A" + cellaszam).setText("Azonosító");
                        sheet3.getRange().get("B" + cellaszam).setText("Munkahely száma");
                        sheet3.getRange().get("C" + cellaszam).setText("Munkahely neve");
                        sheet3.getRange().get("D" + cellaszam).setText("Dátum");
                        sheet3.getRange().get("E" + cellaszam).setText("Panelszám");
                        sheet3.getRange().get("F" + cellaszam).setText("Teszter szám");
                        sheet3.getRange().get("G" + cellaszam).setText("Eredmény");
                        sheet3.getRange().get("H" + cellaszam).setText("Hibakód");
                        sheet3.getRange().get("I" + cellaszam).setText("kód2");
                        sheet3.getRange().get("J" + cellaszam).setText("torolt");
                        sheet3.getRange().get("K" + cellaszam).setText("Szériaszám");
                        sheet3.getRange().get("L" + cellaszam).setText("Teszt szám");
                        sheet3.getRange().get("M" + cellaszam).setText("Pozició");
                        sheet3.getRange().get("N" + cellaszam).setText("Teljes szám");
                        sheet3.getRange().get("O" + cellaszam).setText("Hibakód");
                        sheet3.getRange().get("P" + cellaszam).setText("error");
                        sheet3.getRange().get("Q" + cellaszam).setText("Dolgozó");
                        cellaszam++;
                        while(rs.next())
                        {                        
                            sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                            sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                            sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                            sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                            sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                            sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                            sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                            sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                            sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                            sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                            sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                            sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                            sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                            sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                            sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                            sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                            sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                            cellaszam++;
                        }
                        
                        if(osszefuzott2.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott2 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott2;
                            osszefuzott2 = "";
                            while(rs.next())
                            {  
                                osszefuzott2 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott2.equals(""))
                            {
                                osszefuzott2 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott2 = osszefuzott2.substring(0, osszefuzott2.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott2 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott3.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott3 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott3;
                            osszefuzott3 = "";
                            while(rs.next())
                            {  
                                osszefuzott3 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott3.equals(""))
                            {
                                osszefuzott3 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott3 = osszefuzott3.substring(0, osszefuzott3.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott3 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott4.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott4 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott4;
                            osszefuzott4 = "";
                            while(rs.next())
                            {  
                                osszefuzott4 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott4.equals(""))
                            {
                                osszefuzott4 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott4 = osszefuzott4.substring(0, osszefuzott4.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott4 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott5.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott5 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott5;
                            osszefuzott5 = "";
                            while(rs.next())
                            {  
                                osszefuzott5 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott5.equals(""))
                            {
                                osszefuzott5 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott5 = osszefuzott5.substring(0, osszefuzott5.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott5 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott6.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott6 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott6;
                            osszefuzott6 = "";
                            while(rs.next())
                            {  
                                osszefuzott6 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott6.equals(""))
                            {
                                osszefuzott6 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott6 = osszefuzott6.substring(0, osszefuzott6.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott6 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott7.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott7 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott7;
                            osszefuzott7 = "";
                            while(rs.next())
                            {  
                                osszefuzott7 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott7.equals(""))
                            {
                                osszefuzott7 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott7 = osszefuzott7.substring(0, osszefuzott7.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott7 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott8.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott8 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott8;
                            osszefuzott8 = "";
                            while(rs.next())
                            {  
                                osszefuzott8 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott8.equals(""))
                            {
                                osszefuzott8 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott8 = osszefuzott.substring(0, osszefuzott8.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott8 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                        
                        if(osszefuzott9.equals("")) {}
                        else
                        {
                            rs = stmt.executeQuery("select  hibakod "
                                    + "from videoton.fkov \n"
                                    + " \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott9 +") and hely = '58' group by hibakod");
                            
                            osszefuzott10 = osszefuzott9;
                            osszefuzott9 = "";
                            while(rs.next())
                            {  
                                osszefuzott9 += ("'" + rs.getString(1) +"',");
                            }
                            if(osszefuzott9.equals(""))
                            {
                                osszefuzott9 = osszefuzott10;
                            }
                            else
                            {
                                osszefuzott9 = osszefuzott9.substring(0, osszefuzott9.length() - 1);
                            }
                            
                            
                            rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                    + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                    + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                    + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                    + "videoton.fkov.dolgozo \n"
                                    + "from videoton.fkov \n"
                                    + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                    + " where videoton.fkov.panel in ("+ osszefuzott9 +")");
                              ;
                              while(rs.next())
                              {                        
                                  sheet3.getRange().get("A" + cellaszam).setText(rs.getString(1));
                                  sheet3.getRange().get("B" + cellaszam).setText(rs.getString(2));
                                  sheet3.getRange().get("C" + cellaszam).setText(rs.getString(3));  
                                  sheet3.getRange().get("D" + cellaszam).setText(rs.getString(4));
                                  sheet3.getRange().get("E" + cellaszam).setText(rs.getString(5));
                                  sheet3.getRange().get("F" + cellaszam).setText(rs.getString(6));
                                  sheet3.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                  sheet3.getRange().get("H" + cellaszam).setText(rs.getString(8));
                                  sheet3.getRange().get("I" + cellaszam).setText(rs.getString(9));
                                  sheet3.getRange().get("J" + cellaszam).setText(rs.getString(10));
                                  sheet3.getRange().get("K" + cellaszam).setText(rs.getString(11));
                                  sheet3.getRange().get("L" + cellaszam).setText(rs.getString(12));
                                  sheet3.getRange().get("M" + cellaszam).setText(rs.getString(13));
                                  sheet3.getRange().get("N" + cellaszam).setText(rs.getString(14));
                                  sheet3.getRange().get("O" + cellaszam).setText(rs.getString(15));
                                  sheet3.getRange().get("P" + cellaszam).setText(rs.getString(16));
                                  sheet3.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                                  cellaszam++;
                              }
                        }
                      
                      /////////////////////////////////////////IFS kezdés
                      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                      Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                          
                      Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                      Statement stmt2 = con2.createStatement();
                      
                      cellaszam = 1;
                      sheet4.getRange().get("A" + cellaszam).setText("Contract");
                      sheet4.getRange().get("B" + cellaszam).setText("Part no");
                      sheet4.getRange().get("C" + cellaszam).setText("Tracy ID");
                      sheet4.getRange().get("D" + cellaszam).setText("Szériaszám");
                      sheet4.getRange().get("E" + cellaszam).setText("Alternatív Szériaszám");
                      sheet4.getRange().get("F" + cellaszam).setText("Gyártás Dátuma");
                      sheet4.getRange().get("G" + cellaszam).setText("Beolvasási pont");
                      sheet4.getRange().get("H" + cellaszam).setText("REsource ID");
                      sheet4.getRange().get("I" + cellaszam).setText("Scan Loc");
                      sheet4.getRange().get("J" + cellaszam).setText("Művelet száma");
                      sheet4.getRange().get("K" + cellaszam).setText("Művelet megnevezése");
                      sheet4.getRange().get("L" + cellaszam).setText("Pass");
                      sheet4.getRange().get("M" + cellaszam).setText("Ismétlések száma");
                      sheet4.getRange().get("N" + cellaszam).setText("Javítás óta ismétlések száma");
                      sheet4.getRange().get("O" + cellaszam).setText("Komment");
                      sheet4.getRange().get("P" + cellaszam).setText("Tracy Space code");
                      sheet4.getRange().get("Q" + cellaszam).setText("Alfanumerikus érték");
                      sheet4.getRange().get("R" + cellaszam).setText("Package ID");
                      
                      cellaszam++;
                      rs = stmt2.executeQuery("select ot.contract,\r\n"
                              + "       ct.part_no,\r\n"
                              + "       ot.tracy_id,\r\n"
                              + "       ot.TRACY_SERIAL_NO,\r\n"
                              + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                              + "       ot.manuf_date,\r\n"
                              + "       ot.work_center_no,\r\n"
                              + "       ot.resource_id,\r\n"
                              + "       ot.scan_loc,\r\n"
                              + "       ot.operation_no,\r\n"
                              + "       ot.operation_description,\r\n"
                              + "       ot.pass,\r\n"
                              + "       ot.repetitions,\r\n"
                              + "       ot.REPET_LAST_REPAIR,\r\n"
                              + "       so.comments,\r\n"
                              + "       so.tracy_spec_code,\r\n"
                              + "       so.alfa_num_value,\r\n"
                              + "       ct.package_id\r\n"
                              + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                              + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                              + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                              + " where 3 = 3\r\n"
                              + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott +")\r\n"
                              + "   and ot.contract = so.contract(+)\r\n"
                              + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                              + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                              + "      --and ot.tracy_id = '35903860'\r\n"
                              + "   and so.COMMENTS(+) is not null \r\n"
                              + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                              + "   and ot.history_id = ct.history_id\r\n"
                              + "   and ot.tracy_id = ct.tracy_id\r\n"
                              + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                              + " order by ot.manuf_date asc ");
                      
                      datatable2 = new DataTable();
                      JdbcAdapter jdbcAdapter = new JdbcAdapter();
                      jdbcAdapter.fillDataTable(datatable2, rs);
                      //sheet2.insertDataTable(datatable2, true, 1, 1);
                      
                      for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                      {                           
                          sheet4.getRange().get("A" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(0));
                          sheet4.getRange().get("B" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(1));
                          sheet4.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(2));
                          sheet4.getRange().get("D" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(3));
                          sheet4.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(4));
                          sheet4.getRange().get("F" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(5));
                          sheet4.getRange().get("G" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(6));
                          sheet4.getRange().get("H" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(7));
                          sheet4.getRange().get("I" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(8));
                          sheet4.getRange().get("J" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(9));
                          sheet4.getRange().get("K" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(10));
                          sheet4.getRange().get("L" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(11));
                          sheet4.getRange().get("M" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(12));
                          sheet4.getRange().get("N" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(13));
                          sheet4.getRange().get("O" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(14));
                          sheet4.getRange().get("P" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(15));
                          sheet4.getRange().get("Q" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(16));
                          sheet4.getRange().get("R" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(17));
                          cellaszam++;
                          
                      }
                      
                      if(osszefuzott2.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott2 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable3 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable3, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable3.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable3.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott3.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott3 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable4 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable4, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable4.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable4.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott4.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott4 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable5 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable5, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable5.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable5.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott5.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott5 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable6 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable6, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable6.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable6.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott6.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott6 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable7 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable7, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable7.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable7.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott7.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott7 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable8 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable8, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable8.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable8.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott8.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott8 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable9 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable9, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable9.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable9.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      if(osszefuzott9.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select ot.contract,\r\n"
                                  + "       ct.part_no,\r\n"
                                  + "       ot.tracy_id,\r\n"
                                  + "       ot.TRACY_SERIAL_NO,\r\n"
                                  + "       ot.ALT_TRACY_SERIAL_NO1,\r\n"
                                  + "       ot.manuf_date,\r\n"
                                  + "       ot.work_center_no,\r\n"
                                  + "       ot.resource_id,\r\n"
                                  + "       ot.scan_loc,\r\n"
                                  + "       ot.operation_no,\r\n"
                                  + "       ot.operation_description,\r\n"
                                  + "       ot.pass,\r\n"
                                  + "       ot.repetitions,\r\n"
                                  + "       ot.REPET_LAST_REPAIR,\r\n"
                                  + "       so.comments,\r\n"
                                  + "       so.tracy_spec_code,\r\n"
                                  + "       so.alfa_num_value,\r\n"
                                  + "       ct.package_id\r\n"
                                  + "  from ifsapp.C_OPER_TRACY_OVW      ot,\r\n"
                                  + "       ifsapp.C_SPEC_TRACY_UNIT_OVW so,\r\n"
                                  + "       ifsapp.C_TRACY_HIST_OVW      ct\r\n"
                                  + " where 3 = 3\r\n"
                                  + "   and ot.TRACY_SERIAL_NO in ("+ osszefuzott9 +")\r\n"
                                  + "   and ot.contract = so.contract(+)\r\n"
                                  + "   and ot.tracy_id = so.tracy_id(+)\r\n"
                                  + "   and ot.oper_tracy_id = so.oper_tracy_id(+)\r\n"
                                  + "      --and ot.tracy_id = '35903860'\r\n"
                                  + "   and so.COMMENTS(+) is not null \r\n"
                                  + "   -- and (so.tracy_spec_code = 'MANUF_START_DATE'  or  so.tracy_spec_code = 'MANUF_END_DATE')\r\n"
                                  + "   and ot.history_id = ct.history_id\r\n"
                                  + "   and ot.tracy_id = ct.tracy_id\r\n"
                                  + "      --and nvl(ot.manuf_date, ot.process_date) >= trunc(SYSDATE)\r\n"                       
                                  + " order by ot.manuf_date asc ");
                          
                          datatable10 = new DataTable();
                          jdbcAdapter = new JdbcAdapter();
                          jdbcAdapter.fillDataTable(datatable10, rs);
                          //sheet2.insertDataTable(datatable2, true, 1, 1);
                          
                          for(int szamlalo2 = 0; szamlalo2 < datatable10.getRows().size(); szamlalo2++)
                          {                           
                              sheet4.getRange().get("A" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(0));
                              sheet4.getRange().get("B" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(1));
                              sheet4.getRange().get("C" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(2));
                              sheet4.getRange().get("D" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(3));
                              sheet4.getRange().get("E" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(4));
                              sheet4.getRange().get("F" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(5));
                              sheet4.getRange().get("G" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(6));
                              sheet4.getRange().get("H" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(7));
                              sheet4.getRange().get("I" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(8));
                              sheet4.getRange().get("J" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(9));
                              sheet4.getRange().get("K" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(10));
                              sheet4.getRange().get("L" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(11));
                              sheet4.getRange().get("M" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(12));
                              sheet4.getRange().get("N" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(13));
                              sheet4.getRange().get("O" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(14));
                              sheet4.getRange().get("P" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(15));
                              sheet4.getRange().get("Q" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(16));
                              sheet4.getRange().get("R" + cellaszam).setText(datatable10.getRows().get(szamlalo2).getString(17));
                              cellaszam++;
                              
                          }
                      }
                      
                      
                      
                      cellaszam = 1;
                      sheet5.getRange().get("A" + cellaszam).setText("Tracy ID");
                      sheet5.getRange().get("B" + cellaszam).setText("Anyagtracy ID");
                      sheet5.getRange().get("C" + cellaszam).setText("Cikkszam");
                      sheet5.getRange().get("D" + cellaszam).setText("Folyamat tracy ID");
                      sheet5.getRange().get("E" + cellaszam).setText("Barcode ID");
                      sheet5.getRange().get("F" + cellaszam).setText("Tracy gyári szám");
                      sheet5.getRange().get("G" + cellaszam).setText("Panel azonosító");
                      sheet5.getRange().get("H" + cellaszam).setText("Komponens cikk");
                      sheet5.getRange().get("I" + cellaszam).setText("Cikk megnevezés");
                      sheet5.getRange().get("J" + cellaszam).setText("Sarzs");
                      sheet5.getRange().get("K" + cellaszam).setText("ME szám");
                      sheet5.getRange().get("L" + cellaszam).setText("Felhasználás ideje");
                      cellaszam++;
                      
                      rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                              + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                              + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott +")");
                      
                      while(rs.next())
                      {
                          System.out.println(rs.getString(1));
                          sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                          sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                          sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                          sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                          sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                          sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                          sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                          sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                          sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                          sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                          
                          cellaszam++;
                      }
                      
                      if(osszefuzott2.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott2 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott3.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott3 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott4.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott4 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott5.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott5 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott6.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott6 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott7.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott7 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott8.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott8 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }
                      
                      if(osszefuzott9.equals("")) {}
                      else
                      {
                          rs = stmt2.executeQuery("select anyag.*, ifsapp.inventory_part_api.get_description (anyag.Contract,anyag.Component_Part) cikk_nev \r\n"
                                  + "from ifsapp.C_MTRL_TRACY_OVW anyag\r\n"
                                  + "where 3 = 3 and TRACY_SERIAL_NO in ("+ osszefuzott9 +")");
                          
                          while(rs.next())
                          {
                              System.out.println(rs.getString(1));
                              sheet5.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet5.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet5.getRange().get("C" + cellaszam).setText(rs.getString(4));  
                              sheet5.getRange().get("D" + cellaszam).setText(rs.getString(5));
                              sheet5.getRange().get("E" + cellaszam).setText(rs.getString(6));
                              sheet5.getRange().get("F" + cellaszam).setText(rs.getString(7));
                              sheet5.getRange().get("G" + cellaszam).setText(rs.getString(8));
                              sheet5.getRange().get("H" + cellaszam).setText(rs.getString(9));
                              sheet5.getRange().get("I" + cellaszam).setText(rs.getString(26));
                              sheet5.getRange().get("J" + cellaszam).setText(rs.getString(10));
                              sheet5.getRange().get("K" + cellaszam).setText(rs.getString(13));
                              sheet5.getRange().get("L" + cellaszam).setText(rs.getString(20));
                              
                              cellaszam++;
                          }
                      }


                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:R1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      
                      sheet3.getAutoFilters().setRange(sheet3.getCellRange("A1:R1"));
                      sheet3.getAllocatedRange().autoFitColumns();
                      sheet3.getAllocatedRange().autoFitRows();
                      sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      
                      sheet4.getAutoFilters().setRange(sheet4.getCellRange("A1:R1"));
                      sheet4.getAllocatedRange().autoFitColumns();
                      sheet4.getAllocatedRange().autoFitRows();
                      sheet4.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      
                      sheet5.getAutoFilters().setRange(sheet5.getCellRange("A1:R1"));
                      sheet5.getAllocatedRange().autoFitColumns();
                      sheet5.getAllocatedRange().autoFitRows();
                      sheet5.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      
                      String hova = System.getProperty("user.home") + "\\Desktop\\Hager adatok.xlsx";
                      try 
                      {
                          workbook2.saveToFile(hova, ExcelVersion.Version2016);
                          FileInputStream fileStream = new FileInputStream(hova);
                          try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                          {
                              for(int i = workbook3.getNumberOfSheets()-1; i > 2 ;i--)
                              {    
                                  workbook3.removeSheetAt(i); 
                              }      
                              FileOutputStream output = new FileOutputStream(hova);
                              workbook3.write(output);
                              output.close();
                          }
                          JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Hager adatok.xlsx néven!", "Info", 1); 
                          con.close();
                      } 
                      catch (Exception e1) 
                      {
                          
                      }
                      
                      hova = System.getProperty("user.home") + "\\Desktop\\Hager adatok2.xlsx";
                      try 
                      {
                          workbook4.saveToFile(hova, ExcelVersion.Version2016);
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
                          JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Hager adatok2.xlsx néven!", "Info", 1); 
                          con.close();
                      } 
                      catch (Exception e1) 
                      {
                          
                      }
                }
                Foablak.frame.setCursor(null);  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Lekerdezes2 implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();                
                Workbook workbook = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016);
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    workbook.setVersion(ExcelVersion.Version2016);
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    DataTable datatable2 = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    String osszefuzott_cikk = "";
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        osszefuzott_cikk += ("'" + datatable.getRows().get(szamlalo).getString(0) +"',");
                    }
                    osszefuzott_cikk = osszefuzott_cikk.substring(0, osszefuzott_cikk.length() - 1);
                    Workbook workbook2 = new Workbook();
                    workbook2.setVersion(ExcelVersion.Version2016);
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                    Worksheet sheet3 = workbook2.getWorksheets().get(1);
                    
                     
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                     //jdbc mysql driver meghÃ­vÃ¡sa
                    
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                    Connection con;                   
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                    Statement stmt = con.createStatement();
                    
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                        
                    Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                    Statement stmt2 = con2.createStatement();
                    ResultSet rs2 = null;
                    
                    ResultSet rs = stmt2.executeQuery("Select WAIV_DEV_REJ_NO  \r\n"
                            + "from ifsapp.INVENTORY_PART_IN_STOCK_UIV \r\n"
                            + "where PART_NO in ("+ osszefuzott_cikk +") and QTY_ONHAND > 0 and WAIV_DEV_REJ_NO not like '*'");
                    String osszefuzott = "";
                    while(rs.next())
                    {  
                        osszefuzott += ("'" + rs.getString(1) +"',");
                    }
                    try
                    {  
                        osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                        
                        
                     
                      
                      Foablak.frame.setCursor(null);  
                    }
                    catch (Exception e1) 
                    {                        
                        JOptionPane.showMessageDialog(null, "Nincs még ME számmal rendelkező készlet!", "Hiba üzenet", 2);
                        Foablak.frame.setCursor(null);//kiírja a hibaüzenetet
                        return;
                    }
                    
                    rs = stmt.executeQuery("SELECT panel FROM videoton.csomagol where csomag in ("+ osszefuzott +")");
                    
                    osszefuzott = "";
                    int cellaszam = 1;
                    sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
                    sheet2.getRange().get("B" + cellaszam).setText("ME szám");
                    sheet2.getRange().get("C" + cellaszam).setText("Szériaszám");
                                     
                    cellaszam++;
                    //int szam = 0;
                    while(rs.next())
                    {
                        /*if(szam < 999)
                        {
                            osszefuzott += ("'" + rs.getString(1) +"',");
                        }
                        else
                        {
                            osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                            
                            
                            osszefuzott = "";
                            szam = 0;
                        }
                        szam++;*/
                        rs2 = stmt2.executeQuery("Select PART_NO , PACKAGE_ID, TRACY_SERIAL_NO\r\n"
                                + "from ifsapp.C_TRACY\r\n"
                                + "where TRACY_SERIAL_NO = '"+ rs.getString(1) +"'");
                        
                        if(rs2.next())
                        {   
                            sheet2.getRange().get("A" + cellaszam).setText(rs2.getString(1));
                            sheet2.getRange().get("B" + cellaszam).setNumberValue(rs2.getInt(2));
                            sheet2.getRange().get("C" + cellaszam).setText(rs2.getString(3));
                            System.out.println("Szériaszám beírva: " + cellaszam);
                            cellaszam++;                       
                        }
                        else
                        { 
                            sheet2.getRange().get("C" + cellaszam).setText(rs.getString(1));
                            System.out.println("Szériaszám beírva: " + cellaszam);
                            cellaszam++;
                        }
                        
                    }
                    
                    rs = stmt2.executeQuery("select part_no as Cikkszam,ifsapp.INVENTORY_PART_API.Get_Description(contract,part_no) as Megnevezes, waiv_dev_rej_no as ME_szam, warehouse as Raktar, location_no as Raktarhely_szama, QTY_ONHAND as Keszleten\r\n"
                            + "from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                            + "where PART_NO in ("+ osszefuzott_cikk +") and QTY_ONHAND > 0");
                    
                    JdbcAdapter jdbcAdapter = new JdbcAdapter();
                    jdbcAdapter.fillDataTable(datatable2, rs);
                    
                    sheet3.insertDataTable(datatable2, true, 1, 1);
                    
                    CellRange range = sheet3.getCellRange(1, 1, sheet3.getLastRow(), sheet3.getLastColumn());
                    //Convert numbers stored as text back to number
                    range.convertToNumber();
                    
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:R1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    
                    sheet3.getAutoFilters().setRange(sheet3.getCellRange("A1:R1"));
                    sheet3.getAllocatedRange().autoFitColumns();
                    sheet3.getAllocatedRange().autoFitRows();
                    sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    
                    String hova = System.getProperty("user.home") + "\\Desktop\\Hager raktárban adatok.xlsx";
                    try 
                    {
                        workbook2.saveToFile(hova, ExcelVersion.Version2016);
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook3.getNumberOfSheets()-1; i > 1 ;i--)
                            {    
                                workbook3.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook3.write(output);
                            output.close();
                        }
                        JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Hager raktárban adatok.xlsx néven!", "Info", 1); 
                        con.close();
                    } 
                    catch (Exception e1) 
                    {
                        System.out.println(e1);
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
                    }
                }
                
            }
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Lekerdezes3 implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook2 = new Workbook();
                workbook2.setVersion(ExcelVersion.Version2016);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
              
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                     //jdbc mysql driver meghÃ­vÃ¡sa
                
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                Connection con;                   
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                Statement stmt = con.createStatement();
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt2 = con2.createStatement();
                ResultSet rs2 = null;
                
                ResultSet rs = stmt.executeQuery("SELECT * \r\n"
                        + "FROM videoton.kov \r\n"
                        + "where 3 = 3 -- and panel = '111A24058AA2440321694'\r\n"
                        + " and ido > '"+ datum_mezo.getText() +" 00:00:00' and ido < '"+ datum_mezo.getText() +" 23:59:59'\r\n"
                        + " and gyszam = '"+ iranyszam_mezo.getText() +"'");
                
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Dátum");
                sheet2.getRange().get("B" + cellaszam).setText("Sor");
                sheet2.getRange().get("C" + cellaszam).setText("Eredeti cimke");
                sheet2.getRange().get("D" + cellaszam).setText("Cikkszám");
                sheet2.getRange().get("E" + cellaszam).setText("Megnevezés");
                sheet2.getRange().get("F" + cellaszam).setText("Charge");
                sheet2.getRange().get("G" + cellaszam).setText("Lot");
                sheet2.getRange().get("H" + cellaszam).setText("Lot2");
                sheet2.getRange().get("I" + cellaszam).setText("Datecod");
                sheet2.getRange().get("J" + cellaszam).setText("Gyártó");
                sheet2.getRange().get("K" + cellaszam).setText("Gyártói cikk");
                sheet2.getRange().get("L" + cellaszam).setText("Eredeti menny");
                sheet2.getRange().get("M" + cellaszam).setText("ME");
                sheet2.getRange().get("N" + cellaszam).setText("Panel");
                sheet2.getRange().get("O" + cellaszam).setText("Iranyszam");
                
                cellaszam++;
                
                while(rs.next())
                {  
                    sheet2.getRange().get("A" + cellaszam).setText(rs.getString(6));
                    sheet2.getRange().get("B" + cellaszam).setText("Fuji NXT"+rs.getString(11));
                    sheet2.getRange().get("C" + cellaszam).setText(rs.getString(4));
                    
                    String meszam = rs.getString(4).substring(rs.getString(4).length() -8, rs.getString(4).length());
                    
                   
                    System.out.println("ME: "+meszam );
                    
                    rs2 = stmt2.executeQuery("select PART_NO, ifsapp.inventory_part_api.Get_Description(contract, part_no) Megnevezes,  LOT_BATCH_NO,\r\n"
                            + "C_LOT1  as gyartoi_lot, C_LOT2, C_MANU_PART_NO2 as gyartoicikk2, ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                            + "C_MANU_PART_NO as gyartoicikk1, ORIGIN_PACK_SIZE    \r\n"
                            + "from ifsapp.INVENTORY_PART_BARCODE   \r\n"
                            + "  where 3 = 3\r\n"
                            + "  and WAIV_DEV_REJ_NO = '"+ meszam +"'");
                    if(rs2.next())
                    {                  
                        sheet2.getRange().get("D" + cellaszam).setText(rs2.getString(1));
                        sheet2.getRange().get("E" + cellaszam).setText(rs2.getString(2));
                        sheet2.getRange().get("F" + cellaszam).setText(rs2.getString(3));
                        sheet2.getRange().get("G" + cellaszam).setText(rs2.getString(4));
                        sheet2.getRange().get("H" + cellaszam).setText(rs2.getString(5));
                        sheet2.getRange().get("I" + cellaszam).setText(rs2.getString(6));
                        sheet2.getRange().get("J" + cellaszam).setText(rs2.getString(7));
                        sheet2.getRange().get("K" + cellaszam).setText(rs2.getString(8));
                        sheet2.getRange().get("L" + cellaszam).setText(rs2.getString(9));
                        sheet2.getRange().get("M" + cellaszam).setText(meszam);
                    }
                    sheet2.getRange().get("N" + cellaszam).setText(rs.getString(3));
                    sheet2.getRange().get("O" + cellaszam).setText(rs.getString(10));
                    
                    cellaszam++;
                }

                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:R1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
    
                String hova = System.getProperty("user.home") + "\\Desktop\\Hager beépülés adatok.xlsx";
                try 
                {
                    workbook2.saveToFile(hova, ExcelVersion.Version2016);
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
                    JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Hager raktárban adatok.xlsx néven!", "Info", 1); 
                    con.close();
                } 
                catch (Exception e1) 
                {
                    System.out.println(e1);
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
                }
                Foablak.frame.setCursor(null); 
            }
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }  
                               
         }
    }
}
