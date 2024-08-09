import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;

public class AVM_UTMH extends JPanel {

    /**
     * Create the panel.
     */
    public AVM_UTMH() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("AVM UTMH adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(561, 58, 298, 14);
        add(lblNewLabel);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new UTMH_kereses());
        start_gomb.setBounds(595, 177, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class UTMH_kereses implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            Connection conn2 = null;
            Statement stmt2 = null;
            Connection conn3 = null;
            Statement stmt3 = null;
            ResultSet rs3 = null;
          
            try 
            {              
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.5.145/", "quality", "Qua25!");
               stmt2 = (Statement) conn2.createStatement(); 
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               stmt.execute("select Panelszam from qualitydb.Beolvasott_panelek where 3 = 3 and Projekt = 'AVM'");
               ResultSet rs = stmt.getResultSet();
               DataTable datatable = new DataTable();
               JdbcAdapter jdbcAdapter = new JdbcAdapter();
               jdbcAdapter.fillDataTable(datatable, rs);
               ArrayList<String> utmh = new ArrayList<String>();
               ArrayList<String> selejt = new ArrayList<String>();
               for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   if(datatable.getRows().get(szamlalo).getString(0).startsWith("111"))
                   {
                       utmh.add(datatable.getRows().get(szamlalo).getString(0));
                   }
                   if(datatable.getRows().get(szamlalo).getString(0).endsWith(","))
                   {
                       ResultSet rs2 = stmt2.executeQuery("select panel from videoton.fkovavm WHERE ain = '"+ datatable.getRows().get(szamlalo).getString(0).replace(",", "") +"'");
                       if(rs2.next())
                       {
                           utmh.add(rs2.getString(1));
                       }
                   }
               }
               ResultSet rs2 = stmt2.executeQuery("select panel from videoton.selejt where 3=3 and panel like '111%'");
               while(rs2.next())
               {
                   selejt.add(rs2.getString(1));
               }
               for(int szamlalo = 0; szamlalo < utmh.size(); szamlalo++)
               {
                   for(int szamlalo2 = 0; szamlalo2 < selejt.size(); szamlalo2++)
                   {
                       if(utmh.get(szamlalo).toUpperCase().equals(selejt.get(szamlalo2).toUpperCase()))
                       {
                           utmh.remove(szamlalo);
                           break;
                       }
                   }
               }
               Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
               String msAccDB = "\\\\10.1.0.11\\minosegbiztositas\\Babud_Imre\\AVM_SELEJTEZETT_Panelek.accdb";
               String dbURL = "jdbc:ucanaccess://"
                       + msAccDB;
               conn3 = DriverManager.getConnection(dbURL);
               stmt3 = conn3.createStatement();
               rs3 = stmt3.executeQuery("SELECT Panel, dátum FROM AVM_SELEJT where 3 = 3 order by Dátum desc");
               DataTable datatable2 = new DataTable();
               JdbcAdapter jdbcAdapter2 = new JdbcAdapter();
               jdbcAdapter2.fillDataTable(datatable2, rs3);
               System.out.println("UTMH mérete "+ utmh.size());
               /*while(rs3.next())
               {
                   selejtek.add(rs3.getString(1));
               }
               utmh.removeAll(selejtek);*/
               
               for(int szamlalo = 0; szamlalo < utmh.size(); szamlalo++)
               {
                   for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                   {
                       if(utmh.get(szamlalo).toUpperCase().equals(datatable.getRows().get(szamlalo2).getString(0).toUpperCase()))
                       {
                           utmh.remove(szamlalo);
                           break;
                       }
                   }
               }
               System.out.println("UTMH mérete selejt után"+ utmh.size());
               Workbook workbook = new Workbook();
               workbook.setVersion(ExcelVersion.Version2016);
               Worksheet sheet = workbook.getWorksheets().get(0);
               int cellaszam = 1;
               sheet.getRange().get("A" + cellaszam).setText("Azonosító");
               sheet.getRange().get("B" + cellaszam).setText("Munkahely száma");
               sheet.getRange().get("C" + cellaszam).setText("Munkahely neve");
               sheet.getRange().get("D" + cellaszam).setText("Dátum");
               sheet.getRange().get("E" + cellaszam).setText("Panelszám");
               sheet.getRange().get("F" + cellaszam).setText("Teszter szám");
               sheet.getRange().get("G" + cellaszam).setText("Eredmény");
               sheet.getRange().get("H" + cellaszam).setText("Hibakód");
               sheet.getRange().get("I" + cellaszam).setText("kód2");
               sheet.getRange().get("J" + cellaszam).setText("torolt");
               sheet.getRange().get("K" + cellaszam).setText("Szériaszám");
               sheet.getRange().get("L" + cellaszam).setText("Teszt szám");
               sheet.getRange().get("M" + cellaszam).setText("Pozició");
               sheet.getRange().get("N" + cellaszam).setText("Teljes szám");
               sheet.getRange().get("O" + cellaszam).setText("Hibakód");
               sheet.getRange().get("P" + cellaszam).setText("error");
               sheet.getRange().get("Q" + cellaszam).setText("Dolgozó");
               cellaszam++;
               int szam = 0;
               String osszefuzott = "";
               for(int szamlalo = 0; szamlalo < utmh.size(); szamlalo++)
               {
                   osszefuzott += ("'" + utmh.get(szamlalo) +"',");
                   szam++;
                   if(szam == 9900 || szamlalo == utmh.size() -1)
                   {
                       System.out.println("Indul a lekérdezés");
                       osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                       rs2 = stmt2.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                               + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                               + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                               + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                               + "videoton.fkov.dolgozo \n"
                               + "from (select videoton.fkov.panel, max(ido) as 'Datum' from videoton.fkov "
                               + "     where videoton.fkov.panel in ("+ osszefuzott +") group by videoton.fkov.panel) belso, videoton.fkov \n"
                               + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                               + " where videoton.fkov.panel = belso.panel and videoton.fkov.ido = belso.datum and videoton.fkovsor.nev not like '%Csomagolás%'");
                       System.out.println("Lefutott");
                       while(rs2.next())
                       {                        
                           sheet.getRange().get("A" + cellaszam).setText(rs2.getString(1));
                           sheet.getRange().get("B" + cellaszam).setText(rs2.getString(2));
                           sheet.getRange().get("C" + cellaszam).setText(rs2.getString(3));  
                           sheet.getRange().get("D" + cellaszam).setText(rs2.getString(4));
                           sheet.getRange().get("E" + cellaszam).setText(rs2.getString(5));
                           sheet.getRange().get("F" + cellaszam).setText(rs2.getString(6));
                           sheet.getRange().get("G" + cellaszam).setText(rs2.getString(7));
                           sheet.getRange().get("H" + cellaszam).setText(rs2.getString(8));
                           sheet.getRange().get("I" + cellaszam).setText(rs2.getString(9));
                           sheet.getRange().get("J" + cellaszam).setText(rs2.getString(10));
                           sheet.getRange().get("K" + cellaszam).setText(rs2.getString(11));
                           sheet.getRange().get("L" + cellaszam).setText(rs2.getString(12));
                           sheet.getRange().get("M" + cellaszam).setText(rs2.getString(13));
                           sheet.getRange().get("N" + cellaszam).setText(rs2.getString(14));
                           sheet.getRange().get("O" + cellaszam).setText(rs2.getString(15));
                           sheet.getRange().get("P" + cellaszam).setText(rs2.getString(16));
                           sheet.getRange().get("Q" + cellaszam).setText(rs2.getString(17));
                           cellaszam++;
                       }
                       System.out.println("Beírva az excelbe");
                       szam = 0;
                       osszefuzott = "";
                   }                  
               }
               
               sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
               sheet.getAllocatedRange().autoFitColumns();
               sheet.getAllocatedRange().autoFitRows();
               sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
               
               String hova = System.getProperty("user.home") + "\\Desktop\\UTMH alapadat.xlsx";
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
                         
               stmt.close();
               conn.close(); 
               Foablak.frame.setCursor(null);     
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
