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
import java.util.ArrayList;

import javax.swing.JTextField;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelVersion;
import com.spire.xls.OrderBy;
import com.spire.xls.SortComparsionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;

public class AVM_csomagoloanyag extends JPanel {
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    static int cellaszam = 1;
    private String hova = System.getProperty("user.home") + "\\Desktop\\AVM csomagolóanyag PPM.xlsx";;

    /**
     * Create the panel.
     */
    public AVM_csomagoloanyag() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("AVM csomagolóanyagok PPM értékei");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(386, 44, 268, 29);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(399, 114, 70, 14);
        add(lblNewLabel_1);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(492, 111, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(399, 163, 70, 14);
        add(lblNewLabel_2);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(492, 160, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Excel());
        keres_gomb.setBounds(451, 224, 89, 23);
        add(keres_gomb);

    }
    
    class Excel implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Workbook workbook = new Workbook();
            Worksheet sheet = workbook.getWorksheets().get(0);
            Worksheet sheet2 = workbook.getWorksheets().get(1); 
            sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
            sheet.getRange().get("B" + cellaszam).setText("Cikk megnevezés");
            sheet.getRange().get("C" + cellaszam).setText("Felhasználva");
            sheet.getRange().get("D" + cellaszam).setText("Zárolt");
            sheet.getRange().get("E" + cellaszam).setText("Hiba százalék");
            sheet.getRange().get("F" + cellaszam).setText("Készlet");            
            sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
            sheet2.getRange().get("B" + cellaszam).setText("Beérkezett db");
            sheet2.getRange().get("C" + cellaszam).setText("Rendlési szám");
            sheet2.getRange().get("D" + cellaszam).setText("Felhasználva");
            sheet2.getRange().get("E" + cellaszam).setText("Készlet");
            cellaszam++;                      
            try
            {                           
                String[] datumtol = datumtol_mezo.getText().split("\\.");
                String[] datumig = datumig_mezo.getText().split("\\.");
                String cikkszam = "";
                int felhasznalva = 0;
                int zarolt = 0;
                int keszlet = 0;
                float ppm;
                int cellaszam2 = 2;
                ArrayList<String> cikkszamok = new ArrayList<String>();
                ArrayList<String> megnevezes = new ArrayList<String>();
                ArrayList<String> avm_cikkszamok = new ArrayList<String>();
                ArrayList<Integer> beszerzett_db = new ArrayList<Integer>();
                ArrayList<Integer> rendelesi_azonosito = new ArrayList<Integer>();
                //ArrayList<Object[]> ppmek = new ArrayList<Object[]>();
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery("select PART_NO\r\n"
                        + "from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                        + "where 3 = 3\r\n"
                        + "and VENDOR_NO = '36976'");
                while(rs.next())
                {
                    avm_cikkszamok.add(rs.getString(1));
                }
                
                for(int szamlalo = 0; szamlalo < avm_cikkszamok.size(); szamlalo++)
                {
                    rs = stmt.executeQuery("select PART_NO as Cikkszam,\r\n"
                            + " ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO) as Megnevezes\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "     and part_no = '"+ avm_cikkszamok.get(szamlalo) +"' "    
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"                      
                            + "    and LOCATION_NO = '91' group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)");
                    if(rs.next())
                    {
                        cikkszamok.add(rs.getString(1));
                        megnevezes.add(rs.getString(2));
                    }
                }
                //cikkszamok.size()
                for(int szamlalo = 0; szamlalo < cikkszamok.size(); szamlalo++)
                {
                    
                    cikkszam = cikkszamok.get(szamlalo);
                    
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and TRANSACTION_CODE = 'BACFLUSH'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        felhasznalva = rs.getInt(1);
                    }
            
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and LOCATION_NO = '91'\r\n"
                            + "    and TRANSACTION_CODE = 'INVM-IN'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        zarolt = rs.getInt(1);
                    }
                    
                    rs = stmt.executeQuery("select sum(QTY_ONHAND)\r\n"
                            + "from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                            + "where 3 = 3\r\n"
                            + "and PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        keszlet = rs.getInt(1);
                    }
                    
                    rs = stmt.executeQuery("select sum(QTY_ARRIVED),\r\n"
                            + "RECEIPT_REFERENCE\r\n"
                            + "ARRIVAL_DATE\r\n"
                            + "from ifsapp.PURCHASE_RECEIPT_NEW\r\n"
                            + "where 3 = 3\r\n"
                            + "and PART_NO = '"+ cikkszamok.get(szamlalo) +"'\r\n"
                            + "group by RECEIPT_REFERENCE\r\n"
                            + "order by ARRIVAL_DATE DESC\r\n"
                            + "FETCH FIRST 8 ROWS ONLY");
                    while(rs.next())
                    {            
                        beszerzett_db.add(rs.getInt(1));
                        rendelesi_azonosito.add(rs.getInt(2));
                    }                 
                    
                    for(int szamlalo2 = 0; szamlalo2 < beszerzett_db.size() -1; szamlalo2++)
                    {
                        sheet2.getRange().get("A" + cellaszam2).setText(cikkszamok.get(szamlalo));
                        sheet2.getCellRange("B" + cellaszam2).setNumberValue(beszerzett_db.get(szamlalo2));
                        sheet2.getCellRange("C" + cellaszam2).setNumberValue(rendelesi_azonosito.get(szamlalo2));
                        sheet2.getCellRange("D" + cellaszam2).setNumberValue(felhasznalva + zarolt);
                        sheet2.getCellRange("E" + cellaszam2).setNumberValue(keszlet);
                        cellaszam2++;
                    }                        
                    
                    cellaszam2++;
                    ppm = (((float)zarolt/((float)felhasznalva+(float)zarolt)));
                    sheet.getRange().get("A" + cellaszam).setText(cikkszam);
                    sheet.getRange().get("B" + cellaszam).setText(megnevezes.get(szamlalo));
                    sheet.getCellRange("C" + cellaszam).setNumberValue(felhasznalva + zarolt);                        
                    sheet.getCellRange("D" + cellaszam).setNumberValue(zarolt);
                    sheet.getCellRange("E" + cellaszam).setNumberValue(ppm);
                    sheet.getCellRange("F" + cellaszam).setNumberValue(keszlet);
                                       
                    beszerzett_db.clear();
                    rendelesi_azonosito.clear();
                    //cellaszam = sheet.getLastRow()+5;
                    cellaszam++;
                }                
               
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                sheet.getCellRange("E2:E"+sheet.getLastRow()).setNumberFormat("0.00%");
                workbook.getDataSorter().getSortColumns().add(4, SortComparsionType.Values, OrderBy.Descending);
                workbook.getDataSorter().sort(sheet.getCellRange("A1:F"+ sheet.getLastRow()));
                
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
                
                workbook.saveToFile(hova, ExcelVersion.Version2016);
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra AVM csomagolóanyag PPM.xlsx néven!", "Info", 1); 
                con.close();  
                Foablak.frame.setCursor(null);          
              
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }  
         }    
    }
}
