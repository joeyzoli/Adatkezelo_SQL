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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Elokeszitett_anyagkereso extends JPanel {
    
    private String osszefuzott;
    private File fajl;

    /**
     * Create the panel.
     */
    public Elokeszitett_anyagkereso() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ME számok megniytása");
        lblNewLabel.setBounds(441, 166, 183, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Előkészített anyagok ME számból való gyártói adatok visszakeresése");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(347, 62, 502, 14);
        add(lblNewLabel_1);
        
        JButton keres_gomb = new JButton("Megnyitás");
        keres_gomb.addActionListener(new Kereses());
        keres_gomb.setBounds(634, 162, 89, 23);
        add(keres_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    private class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
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
                
                while (szam < datatable.getRows().size())                 
                { 
                    osszefuzott += ("'" + datatable.getRows().get(szam).getString(0) +"',");
                    szam++;
                    
                }
                osszefuzott = osszefuzott.substring(0, osszefuzott.length() - 1);
                
                
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();              
                
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      
                    Workbook workbook2 = new Workbook();
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
            
                    //System.out.println(osszefuzott);
                      stmt.execute("select elokeszitett.*, rakcikvon.LOT_BATCH_NO as Sarzs, ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID)as Gyarto, rakcikvon.C_MANU_PART_NO as Gyarto_cikk, rakcikvon.C_MANU_PART_NO2 as Gyarto_cikk2,  rakcikvon.C_LOT1 as gyartoi_lot  \r\n"
                              + "from ifsapp.INVENTORY_PART_BARCODE rakcikvon,\r\n"
                              + "    (select beepules.PART_NO as elokeszitett_cikkszam, beepules.TRACY_SERIAL_NO as elokeszitett_ME_szam, beepules.COMPONENT_PART as mibol_lett, beepules.WAIV_DEV_REJ_NO as mibol_ME_szam   \r\n"
                              + "    from ifsapp.C_MTRL_TRACY_OVW beepules\r\n"
                              + "    where 3 = 3\r\n"
                              + "            and TRACY_SERIAL_NO in ("+ osszefuzott +")) elokeszitett\r\n"
                              + "        where elokeszitett.mibol_ME_szam = rakcikvon.WAIV_DEV_REJ_NO");
                      
                      ResultSet rs = stmt.getResultSet();
                      
                      DataTable datatable2 = new DataTable();
                      JdbcAdapter jdbcAdapter = new JdbcAdapter();
                      jdbcAdapter.fillDataTable(datatable2, rs);
                      sheet2.insertDataTable(datatable2, true, 1, 1);
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\Előkészített anyag adatai.xlsx";
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
}
