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

public class IQC_ellenorzes extends JPanel {
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;

    /**
     * Create the panel.
     */
    public IQC_ellenorzes() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("IQC ellenőrzés lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(454, 35, 288, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátim -tól");
        lblNewLabel_1.setBounds(464, 96, 68, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(464, 141, 68, 14);
        add(lblNewLabel_2);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(542, 93, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(542, 138, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Lekerdezes());
        keres_gomb.setBounds(499, 200, 89, 23);
        add(keres_gomb);
        
        setBackground(Foablak.hatter_szine);

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
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                                        
                
                ResultSet rs = stmt.executeQuery("select \r\n"
                        + "prn.part_no Cikkszam,\r\n"
                        + "ifsapp.inventory_part_api.Get_Description(prn.contract, prn.part_no) Megnevezes,\r\n"
                        + "prn.qty_arrived Erkezettmenny,\r\n"
                        + "prn.qty_inspected VizsgaltMenny,\r\n"
                        + "prh.user_code Vegrehajtotta,\r\n"
                        + "min(case when prh.change_message like ('%j_v_h%') or prh.change_message like ('%appro%')\r\n"
                        + "         then prh.change_date else NULL end) over (partition by prh.order_no, prh.line_no, prh.release_no, prh.receipt_no) Jovahagyas_datuma  \r\n"
                        + "from ifsapp.PURCHASE_RECEIPT_HIST prh, ifsapp.PURCHASE_RECEIPT_NEW prn\r\n"
                        + "where 3 = 3\r\n"
                        + "and prh.order_no = prn.order_no\r\n"
                        + "and prh.line_no = prn.line_no\r\n"
                        + "and prh.release_no = prn.release_no\r\n"
                        + "and prh.receipt_no = prn.receipt_no\r\n"
                        + "and trunc(prh.change_date) between nvl(to_date('"+ datumtol_mezo.getText() +"' , 'YYYY.MM.DD.'),trunc(prh.change_date)) and nvl(to_date('"+ datumig_mezo.getText() +"' , 'YYYY.MM.DD.'), trunc(sysdate))+ ( 1 - 1/ ( 60*60*24 ))\r\n"
                        + "and prn.no_of_inspections > 0\r\n"
                        + "and ifsapp.purchase_part_supplier_api.Get_Receive_Case_Db(prn.contract,prn.part_no,prn.vendor_no) = 'ARRQA'\r\n"
                        + "and prh.hist_state = 'Vizsgálandó'\r\n"
                        + "and (prh.user_code = 'AJUHASZ' or prh.user_code = 'GKAMAN' or prh.user_code = 'PHOLDVILAG' or prh.user_code = 'ESZUCS' or prh.user_code = 'BSZEGFU' or prh.user_code = 'CMAGYAR')\r\n");
                                 
                sheet.getRange().get("A" + 1).setText("Cikkszám");
                sheet.getRange().get("B" + 1).setText("Menevezés");
                sheet.getRange().get("C" + 1).setText("Érkezett mennyiség");
                sheet.getRange().get("D" + 1).setText("Vizsgált mennyiség");
                sheet.getRange().get("E" + 1).setText("Ellenőr");
                sheet.getRange().get("F" + 1).setText("Jóváhagyás dátuma");
                int cellaszam = 2;
                while(rs.next())
                {
                    sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                    sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                    sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                    sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                    sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));
                    sheet.getRange().get("F" + cellaszam).setText(rs.getString(6));
                    cellaszam++;
                }
                
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:G1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\IQC ellenmőrzések.xlsx";
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IQC ellenmőrzések.xlsx néven!", "Info", 1); 
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
