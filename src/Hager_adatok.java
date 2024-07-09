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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Hager_adatok extends JPanel {

    /**
     * Create the panel.
     */
    public Hager_adatok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Kiszállított panelek adatainak keresése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(570, 67, 269, 14);
        add(lblNewLabel);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(630, 159, 89, 23);
        add(megnyit_gomb);

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
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    String osszefuzott_me = "";
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        osszefuzott_me += ("'" + datatable.getRows().get(szamlalo).getString(0) +"',");
                    }
                    osszefuzott_me = osszefuzott_me.substring(0, osszefuzott_me.length() - 1);
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
                    
                    ResultSet rs = stmt.executeQuery("SELECT panel FROM videoton.csomagol where csomag in ("+ osszefuzott_me +")");
                    String osszefuzott = "";
                    while(rs.next())
                    {  
                        osszefuzott += ("'" + rs.getString(1) +"',");
                    }
                    osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                    
                    rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                            + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                            + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                            + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                            + "videoton.fkov.dolgozo \n"
                            + "from videoton.fkov \n"
                            + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                            + " where videoton.fkov.panel in ("+ osszefuzott +")");
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
                      cellaszam++;
                      
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
                          cellaszam++;
                      }
                      
                      rs = stmt.executeQuery("select  hibakod "
                              + "from videoton.fkov \n"
                              + " \n"
                              + " where videoton.fkov.panel in ("+ osszefuzott +") and hely = '58'");
                      
                      osszefuzott = "";
                      while(rs.next())
                      {  
                          osszefuzott += ("'" + rs.getString(1) +"',");
                      }
                      osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                      
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
                      
                      /////////////////////////////////////////IFS kezdés
                      /*DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                      Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                          
                      Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                      Statement stmt2 = con2.createStatement();
                      */
                      
                      
                      
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      
                      sheet3.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet3.getAllocatedRange().autoFitColumns();
                      sheet3.getAllocatedRange().autoFitRows();
                      sheet3.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\Hager adatok.xlsx";
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
                          JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Hager adatok.xlsx néven!", "Info", 1); 
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
}
