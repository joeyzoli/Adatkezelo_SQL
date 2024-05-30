import javax.swing.JPanel;
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

import javax.swing.JTextField;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;

public class Raktarban_anyag extends JPanel {
    private JTextField cikkszam_mezo;

    /**
     * Create the panel.
     */
    public Raktarban_anyag() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Raktárban levő cikkszám gyártói adatokkal");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(446, 69, 331, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cikkszám");
        lblNewLabel_1.setBounds(473, 143, 86, 14);
        add(lblNewLabel_1);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(548, 140, 157, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes_minden());
        start_gomb.setBounds(528, 202, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Lekerdezes_minden implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));               
                Workbook workbook2 = new Workbook();
                workbook2.setVersion(ExcelVersion.Version2016);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());                
                Class.forName("oracle.jdbc.OracleDriver");  //.driver                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
                sheet2.getRange().get("B" + cellaszam).setText("ME szám");
                sheet2.getRange().get("C" + cellaszam).setText("Sarzs szám");
                sheet2.getRange().get("D" + cellaszam).setText("Eredeti csomagméret");
                sheet2.getRange().get("E" + cellaszam).setText("Elérhető mennyiség");
                sheet2.getRange().get("F" + cellaszam).setText("Raktárhely száma");
                sheet2.getRange().get("G" + cellaszam).setText("Használatvezérlés megnevezés");
                sheet2.getRange().get("H" + cellaszam).setText("Gyártói kód");
                sheet2.getRange().get("I" + cellaszam).setText("Gyártó neve");
                sheet2.getRange().get("J" + cellaszam).setText("Gyártói cikkszám");
                sheet2.getRange().get("K" + cellaszam).setText("Gyártói cikkszám2");
                sheet2.getRange().get("L" + cellaszam).setText("Gyártás ideje");
                sheet2.getRange().get("M" + cellaszam).setText("LOT1");
                sheet2.getRange().get("N" + cellaszam).setText("LOT2");
                sheet2.getRange().get("O" + cellaszam).setText("Varchar9");
                sheet2.getRange().get("P" + cellaszam).setText("Varchar10");
                sheet2.getRange().get("Q" + cellaszam).setText("Beérkezés ideje");
                
                cellaszam++;
                ResultSet rs = null;
                
                rs = stmt.executeQuery("select  von.PART_NO as Cikkszam,\r\n"
                        + "        von.WAIV_DEV_REJ_NO as ME_szam,\r\n"
                        + "        von.LOT_BATCH_NO as Sarzs,\r\n"
                        + "        von.ORIGIN_PACK_SIZE as Eredeti_csomagmeret,        \r\n"
                        + "        belso.elerheto_menny as Elerheto_menny,\r\n"
                        + "        belso.hol_van as Raktarhely_szama,\r\n"
                        + "        belso.mivanvele,\r\n"
                        + "        von.C_MANUFACTURER_ID as Gyartoi_kod,\r\n"
                        + "        ifsapp.MANUFACTURER_INFO_API.Get_Name(C_MANUFACTURER_ID) as Gyarto_neve,\r\n"
                        + "        von.C_MANU_PART_NO as Gyartoi_cikk,\r\n"
                        + "        von.C_MANU_PART_NO2 as Gyartoi_cikk2,\r\n"
                        + "        von.C_MANUF_DATE as Gyartas_ideje,\r\n"
                        + "        von.C_LOT1 as Lot1,\r\n"
                        + "        von.C_LOT2 as Lot2,\r\n"
                        + "        von.C_VARCHAR9 as Varchar9,\r\n"
                        + "        von.C_VARCHAR10 as Varchar10,\r\n"
                        + "        von.C_DATE1 as Beerkezes_ideje\r\n"
                        + "from ifsapp.INVENTORY_PART_BARCODE von,\r\n"
                        + "    (select rak.WAIV_DEV_REJ_NO me,\r\n"
                        + "            rak.part_no ci,\r\n"
                        + "            rak.QTY_ONHAND elerheto_menny,\r\n"
                        + "            rak.LOCATION_NO hol_van,\r\n"
                        + "            ifsapp.PART_AVAILABILITY_CONTROL_API.Get_Description(AVAILABILITY_CONTROL_ID) mivanvele\r\n"
                        + "        from ifsapp.INVENTORY_PART_IN_STOCK_UIV rak\r\n"
                        + "        where\r\n"
                        + "            3 = 3\r\n"
                        + "            and rak.QTY_ONHAND > 0\r\n"
                        + "            and rak.part_no = '"+ cikkszam_mezo.getText() +"') belso  \r\n"
                        + "    where belso.me = von.WAIV_DEV_REJ_NO\r\n"
                        + "          and belso.ci = von.part_no");
                
                
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
                    
                
                //sheet2.insertDataTable(datatable2, true, 1, 1);
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:AD1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:AD1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Raktárban levő anyag gyártói adatokkal.xlsx";
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
               
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Raktárban levő anyag gyártói adatokkal.xlsx néven!", "Info", 1); 
                con.close();    
              
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
