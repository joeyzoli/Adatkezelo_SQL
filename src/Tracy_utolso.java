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

public class Tracy_utolso extends JPanel {

    /**
     * Create the panel.
     */
    public Tracy_utolso() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Régi Tracy panelszámok utolsó folyamata");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(386, 39, 319, 30);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Panaleszámok megniytása");
        lblNewLabel_1.setBounds(386, 156, 165, 14);
        add(lblNewLabel_1);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(548, 152, 89, 23);
        add(megnyit_gomb);
        
        setBackground(Foablak.hatter_szine);

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
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    
                    Workbook workbook2 = new Workbook();
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                     
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                     //jdbc mysql driver meghÃ­vÃ¡sa
                    
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                    Connection con;                   
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                    Statement stmt = con.createStatement();
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
                      
                      for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                      {
                          ResultSet rs = stmt.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                  + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                  + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                  + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                  + "videoton.fkov.dolgozo \n"
                                  + "from (select videoton.fkov.panel, max(ido) as 'Datum' from videoton.fkov "
                                  + "     where videoton.fkov.panel = '"+ datatable.getRows().get(szamlalo).getString(0) +"' group by videoton.fkov.panel) belso, videoton.fkov \n"
                                  + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                  + " where videoton.fkov.panel = belso.panel and videoton.fkov.ido = belso.datum");
                          if(rs.next())
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
                          }                      
                          else
                          {
                              sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                          }
                          cellaszam++;
                          rs.next();
                      }
                      
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\Tracy utolsó folyamat.xlsx";
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
                      JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Tracy utolsó folyamat.xlsx néven!", "Info", 1); 
                      con.close();
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
