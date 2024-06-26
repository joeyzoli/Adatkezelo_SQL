import javax.swing.JPanel;
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
import javax.swing.JTextField;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;


public class Beepules_kereso extends JPanel {
    private JTextField komponens_mezo;
    private File fajl;
    private String osszefuzott;
    private String osszefuzott2;
    private String osszefuzott3;
    private String osszefuzott4;
    private String osszefuzott5;

    /**
     * Create the panel.
     */
    public Beepules_kereso() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Komponens beépülés adatainak lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(384, 34, 333, 14);
        add(lblNewLabel);
        
        JButton megnyit_gomb = new JButton("Tallózás");
        megnyit_gomb.addActionListener(new Megnyitas());
        megnyit_gomb.setBounds(534, 118, 89, 23);
        add(megnyit_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Excel megnyitása");
        lblNewLabel_1.setBounds(384, 122, 121, 14);
        add(lblNewLabel_1);
        
        komponens_mezo = new JTextField();
        komponens_mezo.setBounds(534, 163, 169, 20);
        add(komponens_mezo);
        komponens_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Komponens cikkszáma");
        lblNewLabel_2.setBounds(384, 166, 140, 14);
        add(lblNewLabel_2);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Kereses());
        start_gomb.setBounds(495, 229, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_3 = new JLabel("Maximum 5000 szériaszám");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 10));
        lblNewLabel_3.setBounds(654, 122, 171, 14);
        add(lblNewLabel_3);

    }
    
    private class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();              
                ResultSet rs = null;
                
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      
                    Workbook workbook2 = new Workbook();
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
            
                      int cellaszam = 1;
                      sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                      sheet2.getRange().get("B" + cellaszam).setText("Komponens cikkszám");
                      sheet2.getRange().get("C" + cellaszam).setText("Beépült ME szám");
                      sheet2.getRange().get("D" + cellaszam).setText("Sarzs");
                      sheet2.getRange().get("E" + cellaszam).setText("Felhasznált mennyiség");
                      sheet2.getRange().get("F" + cellaszam).setText("Gyártás ideje");
                      sheet2.getRange().get("G" + cellaszam).setText("Gyártó azonosító");
                      sheet2.getRange().get("H" + cellaszam).setText("Gyártó neve");
                      sheet2.getRange().get("I" + cellaszam).setText("Gyártói cikkszám 1");
                      sheet2.getRange().get("J" + cellaszam).setText("Gyártói cikkszám 2");
                      sheet2.getRange().get("K" + cellaszam).setText("Gyártói lot 1");
                      sheet2.getRange().get("L" + cellaszam).setText("Beérkezés dátuma");
                      cellaszam++;
                      rs = stmt.executeQuery("select beepulesek.*, gyartoiadatok.C_MANUFACTURER_ID as gyarto_azonosito,ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                              + "gyartoiadatok.C_MANU_PART_NO as gyartoicikk1, gyartoiadatok.C_MANU_PART_NO2 as gyartoicikk2, gyartoiadatok.C_LOT1  as gyartoi_lot  , gyartoiadatok.C_DATE1 as beerkezes  \r\n"
                              + "from ifsapp.C_INV_PART_BARCODE_HIST gyartoiadatok,\r\n"
                              + "    (select TRACY_SERIAL_NO as szeriaszam, COMPONENT_PART as komponens, WAIV_DEV_REJ_NO as me_szam, LOT_BATCH_NO as Sarzs, QTY_CONSUMED as felhasznalt_menny, CONSUMPTION_DATE as gyartas_ideje \r\n"
                              + "    from ifsapp.C_MTRL_TRACY_OVW\r\n"
                              + "    where TRACY_SERIAL_NO  in ("+ osszefuzott +")\r\n"
                              + "     and COMPONENT_PART = '"+ komponens_mezo.getText() +"') beepulesek\r\n"
                              + " where 3 = 3\r\n"
                              + " and beepulesek.me_szam = gyartoiadatok.WAIV_DEV_REJ_NO  and gyartoiadatok.C_DATE1 is not null\r\n"
                              + " group by beepulesek.szeriaszam, beepulesek.komponens, beepulesek.me_szam,beepulesek.sarzs, beepulesek.felhasznalt_menny, beepulesek.gyartas_ideje, gyartoiadatok.C_MANUFACTURER_ID,\r\n"
                              + "gyartoiadatok.C_MANU_PART_NO, gyartoiadatok.C_MANU_PART_NO2 , gyartoiadatok.C_LOT1, gyartoiadatok.C_DATE1\r\n"
                              + "order by beepulesek.szeriaszam asc");
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
                          cellaszam++;
                      }
                      if(osszefuzott2.equals("")){ }
                      else
                      { 
                          rs = stmt.executeQuery("select beepulesek.*, gyartoiadatok.C_MANUFACTURER_ID as gyarto_azonosito,ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO as gyartoicikk1, gyartoiadatok.C_MANU_PART_NO2 as gyartoicikk2, gyartoiadatok.C_LOT1  as gyartoi_lot  , gyartoiadatok.C_DATE1 as beerkezes  \r\n"
                                  + "from ifsapp.C_INV_PART_BARCODE_HIST gyartoiadatok,\r\n"
                                  + "    (select TRACY_SERIAL_NO as szeriaszam, COMPONENT_PART as komponens, WAIV_DEV_REJ_NO as me_szam, LOT_BATCH_NO as Sarzs, QTY_CONSUMED as felhasznalt_menny, CONSUMPTION_DATE as gyartas_ideje \r\n"
                                  + "    from ifsapp.C_MTRL_TRACY_OVW\r\n"
                                  + "    where TRACY_SERIAL_NO  in ("+ osszefuzott2 +")\r\n"
                                  + "     and COMPONENT_PART = '"+ komponens_mezo.getText() +"') beepulesek\r\n"
                                  + " where 3 = 3\r\n"
                                  + " and beepulesek.me_szam = gyartoiadatok.WAIV_DEV_REJ_NO  and gyartoiadatok.C_DATE1 is not null\r\n"
                                  + " group by beepulesek.szeriaszam, beepulesek.komponens, beepulesek.me_szam,beepulesek.sarzs, beepulesek.felhasznalt_menny, beepulesek.gyartas_ideje, gyartoiadatok.C_MANUFACTURER_ID,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO, gyartoiadatok.C_MANU_PART_NO2 , gyartoiadatok.C_LOT1, gyartoiadatok.C_DATE1\r\n"
                                  + "order by beepulesek.szeriaszam asc");
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
                              cellaszam++;
                          }
                      }
                      if(osszefuzott3.equals("")){ }
                      else
                      { 
                          rs = stmt.executeQuery("select beepulesek.*, gyartoiadatok.C_MANUFACTURER_ID as gyarto_azonosito,ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO as gyartoicikk1, gyartoiadatok.C_MANU_PART_NO2 as gyartoicikk2, gyartoiadatok.C_LOT1  as gyartoi_lot  , gyartoiadatok.C_DATE1 as beerkezes  \r\n"
                                  + "from ifsapp.C_INV_PART_BARCODE_HIST gyartoiadatok,\r\n"
                                  + "    (select TRACY_SERIAL_NO as szeriaszam, COMPONENT_PART as komponens, WAIV_DEV_REJ_NO as me_szam, LOT_BATCH_NO as Sarzs, QTY_CONSUMED as felhasznalt_menny, CONSUMPTION_DATE as gyartas_ideje \r\n"
                                  + "    from ifsapp.C_MTRL_TRACY_OVW\r\n"
                                  + "    where TRACY_SERIAL_NO  in ("+ osszefuzott3 +")\r\n"
                                  + "     and COMPONENT_PART = '"+ komponens_mezo.getText() +"') beepulesek\r\n"
                                  + " where 3 = 3\r\n"
                                  + " and beepulesek.me_szam = gyartoiadatok.WAIV_DEV_REJ_NO  and gyartoiadatok.C_DATE1 is not null\r\n"
                                  + " group by beepulesek.szeriaszam, beepulesek.komponens, beepulesek.me_szam,beepulesek.sarzs, beepulesek.felhasznalt_menny, beepulesek.gyartas_ideje, gyartoiadatok.C_MANUFACTURER_ID,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO, gyartoiadatok.C_MANU_PART_NO2 , gyartoiadatok.C_LOT1, gyartoiadatok.C_DATE1\r\n"
                                  + "order by beepulesek.szeriaszam asc");
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
                              cellaszam++;
                          }
                      }
                      if(osszefuzott4.equals("")){ }
                      else
                      { 
                          rs = stmt.executeQuery("select beepulesek.*, gyartoiadatok.C_MANUFACTURER_ID as gyarto_azonosito,ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO as gyartoicikk1, gyartoiadatok.C_MANU_PART_NO2 as gyartoicikk2, gyartoiadatok.C_LOT1  as gyartoi_lot  , gyartoiadatok.C_DATE1 as beerkezes  \r\n"
                                  + "from ifsapp.C_INV_PART_BARCODE_HIST gyartoiadatok,\r\n"
                                  + "    (select TRACY_SERIAL_NO as szeriaszam, COMPONENT_PART as komponens, WAIV_DEV_REJ_NO as me_szam, LOT_BATCH_NO as Sarzs, QTY_CONSUMED as felhasznalt_menny, CONSUMPTION_DATE as gyartas_ideje \r\n"
                                  + "    from ifsapp.C_MTRL_TRACY_OVW\r\n"
                                  + "    where TRACY_SERIAL_NO  in ("+ osszefuzott4 +")\r\n"
                                  + "     and COMPONENT_PART = '"+ komponens_mezo.getText() +"') beepulesek\r\n"
                                  + " where 3 = 3\r\n"
                                  + " and beepulesek.me_szam = gyartoiadatok.WAIV_DEV_REJ_NO  and gyartoiadatok.C_DATE1 is not null\r\n"
                                  + " group by beepulesek.szeriaszam, beepulesek.komponens, beepulesek.me_szam,beepulesek.sarzs, beepulesek.felhasznalt_menny, beepulesek.gyartas_ideje, gyartoiadatok.C_MANUFACTURER_ID,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO, gyartoiadatok.C_MANU_PART_NO2 , gyartoiadatok.C_LOT1, gyartoiadatok.C_DATE1\r\n"
                                  + "order by beepulesek.szeriaszam asc");
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
                              cellaszam++;
                          }
                      }
                      if(osszefuzott5.equals("")){ }
                      else
                      { 
                          rs = stmt.executeQuery("select beepulesek.*, gyartoiadatok.C_MANUFACTURER_ID as gyarto_azonosito,ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as gyarto_neve,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO as gyartoicikk1, gyartoiadatok.C_MANU_PART_NO2 as gyartoicikk2, gyartoiadatok.C_LOT1  as gyartoi_lot  , gyartoiadatok.C_DATE1 as beerkezes  \r\n"
                                  + "from ifsapp.C_INV_PART_BARCODE_HIST gyartoiadatok,\r\n"
                                  + "    (select TRACY_SERIAL_NO as szeriaszam, COMPONENT_PART as komponens, WAIV_DEV_REJ_NO as me_szam, LOT_BATCH_NO as Sarzs, QTY_CONSUMED as felhasznalt_menny, CONSUMPTION_DATE as gyartas_ideje \r\n"
                                  + "    from ifsapp.C_MTRL_TRACY_OVW\r\n"
                                  + "    where TRACY_SERIAL_NO  in ("+ osszefuzott5 +")\r\n"
                                  + "     and COMPONENT_PART = '"+ komponens_mezo.getText() +"') beepulesek\r\n"
                                  + " where 3 = 3\r\n"
                                  + " and beepulesek.me_szam = gyartoiadatok.WAIV_DEV_REJ_NO  and gyartoiadatok.C_DATE1 is not null\r\n"
                                  + " group by beepulesek.szeriaszam, beepulesek.komponens, beepulesek.me_szam,beepulesek.sarzs, beepulesek.felhasznalt_menny, beepulesek.gyartas_ideje, gyartoiadatok.C_MANUFACTURER_ID,\r\n"
                                  + "gyartoiadatok.C_MANU_PART_NO, gyartoiadatok.C_MANU_PART_NO2 , gyartoiadatok.C_LOT1, gyartoiadatok.C_DATE1\r\n"
                                  + "order by beepulesek.szeriaszam asc");
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
                              cellaszam++;
                          }
                      }
                      
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\IFS beépülés adatok.xlsx";
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
                      JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS beépülés adatok.xlsx néven!", "Info", 1); 
                      con.close(); 
                }  
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
    
    private class Megnyitas implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                fajl = mentes_helye.getSelectedFile();
                Workbook workbook = new Workbook();
                workbook.loadFromFile(fajl.getAbsolutePath());
                Worksheet sheet = workbook.getWorksheets().get(0);
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                int szam = 0;
                osszefuzott = "";
                osszefuzott2 = "";
                osszefuzott3 = "";
                osszefuzott4 = "";
                osszefuzott5 = "";
                while (szam < datatable.getRows().size())                 
                { 
                    if(szam < 1000)
                    {
                        
                        osszefuzott += ("'" + datatable.getRows().get(szam).getString(0) +"',");                          
                    }
                    else if(szam >= 1000 && szam < 2000)
                    {
                        
                        osszefuzott2 += ("'" + datatable.getRows().get(szam).getString(0) +"',");                                     
                    }
                    else if(szam >= 2000  && szam < 3000)
                    {
                        
                        osszefuzott3 += ("'" + datatable.getRows().get(szam).getString(0) +"',");                                    
                    }
                    else if(szam >= 3000  && szam < 4000)
                    {
                        
                        osszefuzott4 += ("'" + datatable.getRows().get(szam).getString(0) +"',");                                     
                    }
                    else
                    {
                        
                        osszefuzott5 += ("'" + datatable.getRows().get(szam).getString(0) +"',");                                     
                    }
                    szam++;
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
                System.out.println(osszefuzott);
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
