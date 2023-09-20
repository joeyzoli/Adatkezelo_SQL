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

public class Cmrt_adatok extends JPanel {
    private JTextField termek_mezo;

    /**
     * Create the panel.
     */
    public Cmrt_adatok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Reach Rohs CMRT adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(519, 22, 348, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Késztermék");
        lblNewLabel_1.setBounds(455, 106, 81, 14);
        add(lblNewLabel_1);
        
        termek_mezo = new JTextField();
        termek_mezo.setBounds(546, 103, 184, 20);
        add(termek_mezo);
        termek_mezo.setColumns(10);
        
        JButton bom_gomb = new JButton("BOM keresés");
        bom_gomb.addActionListener(new Lekerdezes());
        bom_gomb.setBounds(530, 156, 139, 23);
        add(bom_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
               
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                //DataTable datatable = new DataTable();                  
 
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();
                  ResultSet rs = stmt.executeQuery("select bom.*,\r\n"
                          + "nyilatkozatok.CF$_cmrt as CMRT,\r\n"
                          + "nyilatkozatok.CF$_reach as Reach,\r\n"
                          + "nyilatkozatok.CF$_Rohs as Rohs\r\n"
                          + "from ifsapp.part_manu_part_no_cfv nyilatkozatok,\r\n"
                          + "(select b.* from\r\n"
                          + "(select \r\n"
                          + "lbom.part_no,\r\n"
                          + "ifsapp.inventory_part_api.Get_Description(lbom.contract, lbom.part_no) Part_Description,\r\n"
                          + "lbom.eng_chg_level Revision,\r\n"
                          + "ifsapp.part_revision_api.Get_Revision_Text(lbom.contract,lbom.part_no, lbom.eng_chg_level) Revision_text,\r\n"
                          + "ifsapp.inventory_part_api.Get_Part_Product_Code(lbom.contract, lbom.part_no) PartProdCode,\r\n"
                          + "ifsapp.inventory_part_api.Get_Accounting_Group(lbom.contract, lbom.part_no) PartAccGroup,\r\n"
                          + "ifsapp.inventory_part_api.Get_Second_Commodity(lbom.contract, lbom.part_no) PartSecComm,\r\n"
                          + "lbom.component_part_no,\r\n"
                          + "ifsapp.inventory_part_api.Get_Description(lbom.contract, lbom.component_part_no) Comp_Description,\r\n"
                          + "lbom.qty_per_assembly,\r\n"
                          + "ifsapp.inventory_part_api.Get_Unit_Meas(lbom.contract, lbom.component_part_no)Unit_Meas,\r\n"
                          + "ifsapp.inventory_part_api.Get_Part_Product_Code(lbom.contract, lbom.component_part_no) CompPartProdCode,\r\n"
                          + "ifsapp.inventory_part_api.Get_Accounting_Group(lbom.contract, lbom.component_part_no) CompPartAccGroup,\r\n"
                          + "ifsapp.inventory_part_api.Get_Second_Commodity(lbom.contract, lbom.component_part_no) ComPartSecComm,\r\n"
                          + "pm.manufacturer_no,\r\n"
                          + "pm.name,\r\n"
                          + "pm.preferred_manufacturer_db preferred_manufacturer,\r\n"
                          + "pmp.manu_part_no,\r\n"
                          + "pmp.preferred_manu_part_db preferred_manu_part,\r\n"
                          + "ifsapp.PURCHASE_PART_SUPPLIER_API.Get_Primary_Supplier_No(lbom.contract, lbom.component_part_no) Primary_Supplier,\r\n"
                          + "ifsapp.SUPPLIER_API.Get_Vendor_Name(ifsapp.PURCHASE_PART_SUPPLIER_API.Get_Primary_Supplier_No(lbom.contract, lbom.component_part_no)) SUPPLIER_NAME, --HT230206:Kovács Zoltán kérésére\r\n"
                          + "case when ifsapp.part_revision_api.Get_Revision_By_Date(lbom.contract, lbom.part_no, SYSDATE) <> lbom.eng_chg_level then 'FALSE'\r\n"
                          + "  else 'TRUE' end ACTUAL_ENG_CHG,\r\n"
                          + "lbom.contract\r\n"
                          + "from \r\n"
                          + "ifsapp.v_lmaa_bom lbom, ifsapp.PART_MANUFACTURER pm, ifsapp.PART_MANU_PART_NO pmp\r\n"
                          + "where 1 = 1\r\n"
                          + "and lbom.component_PART_NO = pm.part_no (+)\r\n"
                          + "and pm.part_no = pmp.part_no (+)\r\n"
                          + "and pm.manufacturer_no = pmp.manufacturer_no (+)\r\n"
                          + "and pmp.approved_db  (+) in ('1','2')\r\n"
                          + ")b\r\n"
                          + "where 1 = 1   \r\n"
                          + "and b.part_no = '"+ termek_mezo.getText() +"' \r\n"
                          + "and b.ACTUAL_ENG_CHG =  'TRUE') bom\r\n"
                          + "where 3 = 3 \r\n"
                          + "and bom.component_part_no = nyilatkozatok.part_no and bom.manu_part_no = nyilatkozatok.manu_part_no");
                  /*JdbcAdapter jdbcAdapter = new JdbcAdapter();
                  jdbcAdapter.fillDataTable(datatable, rs);
                  sheet.insertDataTable(datatable, true, 1, 1);*/
                  int cellaszam = 1;
                  sheet.getRange().get("A" + cellaszam).setText("Part_no");
                  sheet.getRange().get("B" + cellaszam).setText("Part Description");
                  sheet.getRange().get("C" + cellaszam).setText("Revision");
                  sheet.getRange().get("D" + cellaszam).setText("REvision text");
                  sheet.getRange().get("E" + cellaszam).setText("PartProdCode");
                  sheet.getRange().get("F" + cellaszam).setText("PARTACCGROUP");
                  sheet.getRange().get("G" + cellaszam).setText("PARTSECCOMM");
                  sheet.getRange().get("H" + cellaszam).setText("COMPONENT_PART_NO");
                  sheet.getRange().get("I" + cellaszam).setText("COMP_DESCRIPTION");
                  sheet.getRange().get("J" + cellaszam).setText("QTY_PER_ASSEMBLY");
                  sheet.getRange().get("K" + cellaszam).setText("UNIT_MEAS");
                  sheet.getRange().get("L" + cellaszam).setText("COMPPARTPRODCODE");
                  sheet.getRange().get("M" + cellaszam).setText("COMPPARTACCGROUP");
                  sheet.getRange().get("N" + cellaszam).setText("COMPARTSECCOMM");
                  sheet.getRange().get("O" + cellaszam).setText("MANUFACTURER_NO");
                  sheet.getRange().get("P" + cellaszam).setText("NAME");
                  sheet.getRange().get("Q" + cellaszam).setText("PREFERRED_MANUFACTURER");
                  sheet.getRange().get("R" + cellaszam).setText("MANU_PART_NO");
                  sheet.getRange().get("S" + cellaszam).setText("PREFERRED_MANU_PART");
                  sheet.getRange().get("T" + cellaszam).setText("PRIMARY_SUPPLIER");
                  sheet.getRange().get("U" + cellaszam).setText("SUPPLIER_NAME");
                  sheet.getRange().get("V" + cellaszam).setText("ACTUAL_ENG_CHG");
                  sheet.getRange().get("W" + cellaszam).setText("CONTRACT");
                  sheet.getRange().get("X" + cellaszam).setText("CMRT");
                  sheet.getRange().get("Y" + cellaszam).setText("REACH");
                  sheet.getRange().get("Z" + cellaszam).setText("ROHS");
                  
                  cellaszam++;
                  while(rs.next())
                  {
                      sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                      sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                      sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                      sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                      sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));
                      sheet.getRange().get("F" + cellaszam).setText(rs.getString(6));
                      sheet.getRange().get("G" + cellaszam).setText(rs.getString(7));
                      sheet.getRange().get("H" + cellaszam).setText(rs.getString(8));
                      sheet.getRange().get("I" + cellaszam).setText(rs.getString(9));
                      sheet.getRange().get("J" + cellaszam).setText(rs.getString(10));
                      sheet.getRange().get("K" + cellaszam).setText(rs.getString(11));
                      sheet.getRange().get("L" + cellaszam).setText(rs.getString(12));
                      sheet.getRange().get("M" + cellaszam).setText(rs.getString(13));
                      sheet.getRange().get("N" + cellaszam).setText(rs.getString(14));
                      sheet.getRange().get("O" + cellaszam).setText(rs.getString(15));
                      sheet.getRange().get("P" + cellaszam).setText(rs.getString(16));
                      sheet.getRange().get("Q" + cellaszam).setText(rs.getString(17));
                      sheet.getRange().get("R" + cellaszam).setText(rs.getString(18));
                      sheet.getRange().get("S" + cellaszam).setText(rs.getString(19));
                      sheet.getRange().get("T" + cellaszam).setText(rs.getString(20));
                      sheet.getRange().get("U" + cellaszam).setText(rs.getString(21));
                      sheet.getRange().get("V" + cellaszam).setText(rs.getString(22));
                      sheet.getRange().get("W" + cellaszam).setText(rs.getString(23));
                      sheet.getRange().get("X" + cellaszam).setText(rs.getString(24));
                      sheet.getRange().get("Y" + cellaszam).setText(rs.getString(25));
                      sheet.getRange().get("Z" + cellaszam).setText(rs.getString(26));
                      cellaszam++;
                  }
                  
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  String hova = System.getProperty("user.home") + "\\Desktop\\"+ termek_mezo.getText() +" BOM lista.xlsx";
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
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra "+ termek_mezo.getText() +" BOM lista.xlsx néven!", "Info", 1); 
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
}
