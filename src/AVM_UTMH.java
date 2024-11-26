import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
            //Connection conn3 = null;
            Statement stmt3 = null;
            ResultSet rs3 = null;
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try 
            {              
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.5.145/", "quality", "Qua25!");
               stmt2 = (Statement) conn2.createStatement(); 
               stmt3 = (Statement) conn2.createStatement();
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               /*stmt.execute("select Panelszam\r\n"
                       + "from qualitydb.Beolvasott_panelek\r\n"
                       + "where Panelszam not in (select Selejtek.panel from qualitydb.Selejtek)\r\n"
                       + "and Projekt = 'AVM'");
               
               ResultSet rs = stmt.getResultSet();*/
               SQA_SQL betolt = new SQA_SQL();
               System.out.println("Kezdődik");
               betolt.mindenes("delete from qualitydb.Selejt_temp1");               
               betolt.mindenes("insert into qualitydb.Selejt_temp1 select Panelszam\r\n"
                       + "from qualitydb.Beolvasott_panelek\r\n"
                       + "where Panelszam not in (select Selejtek.panel from qualitydb.Selejtek)\r\n"
                       + "and Projekt = 'AVM' group by Panelszam");
               System.out.println("Első");
               
               
               ResultSet rs2 = stmt2.executeQuery("select panel\r\n"
                       + "from videoton.selejt \r\n"
                       + "WHERE  3 = 3\r\n"
                       + "and felvido > '2024.01.01'\r\n"
                       + "and panel like '111%'");
               betolt.mindenes("delete from qualitydb.Selejt_temp2");
               while(rs2.next())
               {
                   betolt.mindenes("insert into qualitydb.Selejt_temp2 (panel) Values('"+ rs2.getString(1) +"')");
                   System.out.println("Selejt feltölt");
               }
               
               betolt.mindenes("delete from qualitydb.Selejt_temp3");
               betolt.mindenes("insert into qualitydb.Selejt_temp3 select Selejt_temp1.Panel\r\n"
                                                                 + "from qualitydb.Selejt_temp1\r\n"
                                                                 + "where Selejt_temp1.Panel not in (select Selejt_temp2.panel from qualitydb.Selejt_temp2)");
               betolt.mindenes("delete from qualitydb.Selejt_temp4");
               betolt.mindenes("insert into qualitydb.Selejt_temp4 select Selejt_temp3.Panel\r\n"
                                                                 + "from qualitydb.Selejt_temp3\r\n"
                                                                 + "where Selejt_temp3.Panel not in (select Powerpanel.panel from qualitydb.Powerpanel)");
               System.out.println("Power kiszedve");
               stmt.execute("select Selejt_temp4.Panel\r\n"
                       + "from qualitydb.Selejt_temp4\r\n"
                       + "where Selejt_temp4.Panel not in (select Csomagoltak.panel from qualitydb.Csomagoltak) group by Selejt_temp4.Panel");
       
               ResultSet rs = stmt.getResultSet();
               System.out.println("Lekérdezés kész");
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
               
               while(rs.next())
               {
                   String szeriaszam = rs.getString(1);
                   //System.out.println(szeriaszam);
                   if(szeriaszam.contains(","))
                   {
                       String[] ipei = szeriaszam.split(",");
                       String eredeti = szeriaszam;
                       //System.out.println(ipei[0]);
                       rs3 = stmt3.executeQuery("select panel from videoton.fkovavm WHERE ain = '"+ ipei[0] +"'");
                       if(rs3.next())
                       {
                           szeriaszam = rs3.getString(1);
                           System.out.println("Megvan az ipei visszafejtés "+ szeriaszam);
                           rs2 = stmt2.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                   + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                   + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                   + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                   + "videoton.fkov.dolgozo \n"
                                   + "from (select videoton.fkov.panel, max(azon) as 'ID' from videoton.fkov "
                                   + "     where videoton.fkov.panel in ('"+ szeriaszam +"') group by videoton.fkov.panel) belso, videoton.fkov \n"
                                   + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                   + " where videoton.fkov.panel = belso.panel and videoton.fkov.azon = belso.ID");
                           if(rs2.next())
                           {
                               //System.out.println("Fut az ipei lekérdezés");
                               if(rs2.getString(3).contains("Csomagolás") || rs2.getString(3).contains("Quality") || rs2.getString(3).contains("OQC") || rs2.getString(3).contains("csomagolás"))                                
                               {
                                   betolt.mindenes("insert into qualitydb.Csomagoltak (panel) Values('"+ rs2.getString(5) +"')");
                                   betolt.mindenes("insert into qualitydb.Csomagoltak (panel) Values('"+ eredeti +"')");
                                   System.out.println("Csomagolt");
                               }
                               else
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
                                   //System.out.println("Ipei visszafejtve");
                               }
                           }
                       }                      
                   }
                   else
                   {
                       rs2 = stmt2.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                               + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                               + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                               + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                               + "videoton.fkov.dolgozo \n"
                               + "from (select videoton.fkov.panel, max(azon) as 'ID' from videoton.fkov "
                               + "     where videoton.fkov.panel in ('"+ szeriaszam +"') group by videoton.fkov.panel) belso, videoton.fkov \n"
                               + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                               + " where videoton.fkov.panel = belso.panel and videoton.fkov.azon = belso.ID");
                       if(rs2.next())
                       {
                           if(rs2.getString(3).contains("Csomagolás") || rs2.getString(3).contains("Quality") || rs2.getString(3).contains("OQC") || rs2.getString(3).contains("csomagolás"))                                
                           {
                               betolt.mindenes("insert into qualitydb.Csomagoltak (panel) Values('"+ rs2.getString(5) +"')");
                               System.out.println("Csomagolt");
                           }
                           else if(rs2.getString(3).contains("Párosító munkahely"))                                
                           {
                               if(rs.getString(1).startsWith("111A18090")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }               
                               else if(rs.getString(1).startsWith("111A18105")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A19022")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A19053")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }                               
                               else if(rs.getString(1).startsWith("111A19109")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A21133")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A22006")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A21105")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A22087")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else if(rs.getString(1).startsWith("111A23032")) {
                                   betolt.mindenes("insert into qualitydb.Powerpanel (panel) Values('"+ rs2.getString(5) +"')");
                                   System.out.println("Powerpanel");
                               }
                               else
                               {                                    
                                   System.out.println("Párosító megy");
                                   System.out.println(rs2.getString(8));
                                   System.out.println(rs2.getString(5));
                                   if(rs2.getString(8).equals(""))
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
                                   else
                                   {
                                       rs3 = stmt3.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                                               + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                                               + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                                               + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                                               + "videoton.fkov.dolgozo \n"
                                               + "from (select videoton.fkov.panel, max(azon) as 'ID' from videoton.fkov "
                                               + "     where videoton.fkov.panel in ('"+ rs2.getString(8) +"') group by videoton.fkov.panel) belso, videoton.fkov \n"
                                               + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                               + " where videoton.fkov.panel = belso.panel and videoton.fkov.azon = belso.ID");
                                       System.out.println("Lefutott");
                                       if(rs3.next())
                                       {
                                           if(rs3.getString(3).contains("Csomagolás") || rs3.getString(3).contains("Jelölő") || rs3.getString(3).contains("PrePacking") || rs3.getString(3).contains("Quality") || rs3.getString(3).contains("OQC") || rs3.getString(3).contains("csomagolás"))                                
                                           {
                                               betolt.mindenes("insert into qualitydb.Csomagoltak (panel) Values('"+ rs3.getString(5) +"')");
                                               betolt.mindenes("insert into qualitydb.Csomagoltak (panel) Values('"+ szeriaszam +"')");
                                               System.out.println("Csomagolt");
                                           }
                                           else
                                           {
                                               sheet.getRange().get("A" + cellaszam).setText(rs3.getString(1));
                                               sheet.getRange().get("B" + cellaszam).setText(rs3.getString(2));
                                               sheet.getRange().get("C" + cellaszam).setText(rs3.getString(3));  
                                               sheet.getRange().get("D" + cellaszam).setText(rs3.getString(4));
                                               sheet.getRange().get("E" + cellaszam).setText(rs3.getString(5));
                                               sheet.getRange().get("F" + cellaszam).setText(rs3.getString(6));
                                               sheet.getRange().get("G" + cellaszam).setText(rs3.getString(7));
                                               sheet.getRange().get("H" + cellaszam).setText(rs3.getString(8));
                                               sheet.getRange().get("I" + cellaszam).setText(rs3.getString(9));
                                               sheet.getRange().get("J" + cellaszam).setText(rs3.getString(10));
                                               sheet.getRange().get("K" + cellaszam).setText(rs3.getString(11));
                                               sheet.getRange().get("L" + cellaszam).setText(rs3.getString(12));
                                               sheet.getRange().get("M" + cellaszam).setText(rs3.getString(13));
                                               sheet.getRange().get("N" + cellaszam).setText(rs3.getString(14));
                                               sheet.getRange().get("O" + cellaszam).setText(rs3.getString(15));
                                               sheet.getRange().get("P" + cellaszam).setText(rs3.getString(16));
                                               sheet.getRange().get("Q" + cellaszam).setText(rs3.getString(17));
                                               cellaszam++;
                                               //System.out.println("Sima szériaszám");
                                           }   
                                       }
                                   }
                               }
                           }
                           else
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
                               //System.out.println("Sima szériaszám");
                           }   
                       }
                   }
                   System.out.println("Fut a while ebben a sorban: "+ cellaszam);
               }
               /*
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
               
               rs2 = stmt2.executeQuery("select  videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                       + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny, "
                       + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                       + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                       + "videoton.fkov.dolgozo \n"
                       + "from (select videoton.fkov.panel, max(ido) as 'Datum' from videoton.fkov "
                       + "     where videoton.fkov.panel in ("+ osszefuzott +") group by videoton.fkov.panel) belso, videoton.fkov \n"
                       + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                       + " where videoton.fkov.panel = belso.panel and videoton.fkov.ido = belso.datum and videoton.fkovsor.nev not like '%Csomagolás%'");
               

               
               int szam = 0;
               String osszefuzott = "";
               for(int szamlalo = 0; szamlalo < utmh.size(); szamlalo++)
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
               */
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
