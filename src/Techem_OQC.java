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

public class Techem_OQC extends JPanel {
    
    private String excelfile = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\OQC_control_datasheet.xlsx";   
    
    /**
     * Create the panel.
     */
    public Techem_OQC() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Techem OQC adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(538, 40, 314, 14);
        add(lblNewLabel);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes());
        start_gomb.setBounds(590, 211, 89, 23);
        add(start_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile);              
                Worksheet sheet = workbook.getWorksheets().get(0);
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();  
                ResultSet rs2 = null;
                ResultSet rs3 = null;
                Connection conn = null;
                Statement stmt2 = null;        
                try 
                {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                } 
                catch (Exception e1) 
                {
                   System.out.println(e1);
                   String hibauzenet2 = e1.toString();
                   JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                  
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt2 = (Statement) conn.createStatement();
                
                ResultSet rs = stmt.executeQuery("Select waiv_dev_rej_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '1742' and QTY_ONHAND > 0\r\n"
                        + "and location_no like 'TE%'");
                
                while(rs.next())
                {
                    rs2 = stmt.executeQuery("select tracy_serial_no \r\n"
                            + "from ifsapp.C_TRACY\r\n"
                            + "where PACKAGE_ID = '"+ rs.getString(1) +"'");
                    int cellaszam = 6;
                    while(rs2.next())
                    {
                        rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ rs2.getString(1) +"%'");
                        //System.out.println(rs2.getString(1));
                        
                        if(rs3.next())
                        {
                            sheet.getRange().get("D" + 2).setText(rs3.getString(2));
                            sheet.getRange().get("J" + 2).setText(rs3.getString(3));
                            sheet.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                            sheet.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                            sheet.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                            sheet.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                            sheet.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                            sheet.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                            sheet.getRange().get("H" + cellaszam).setText(rs3.getString(10));
                            sheet.getRange().get("I" + cellaszam).setText(rs3.getString(11));
                            sheet.getRange().get("J" + cellaszam).setText(rs3.getString(12));
                            sheet.getRange().get("K" + cellaszam).setText(rs3.getString(13));
                            sheet.getRange().get("L" + cellaszam).setText(rs3.getString(14));
                            cellaszam++;
                            System.out.println(rs3.getString(5));
                        }
                        else
                        {
                            //System.out.print("Nincs találat!");
                        }
                    }
                    
                }
                
                String hova = System.getProperty("user.home") + "\\Desktop\\TEchem OQC.xlsx";
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

                con.close(); 
                conn.close(); 
                
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
