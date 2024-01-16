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

public class Retour_Mile extends JPanel {

    /**
     * Create the panel.
     */
    public Retour_Mile() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Retour adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(447, 27, 196, 27);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fájl megnyitása");
        lblNewLabel_1.setBounds(404, 247, 105, 14);
        add(lblNewLabel_1);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(557, 243, 89, 23);
        add(megnyit_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Lekerdezes implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                workbook.loadFromFile(fajl.getAbsolutePath());
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                 
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  int cellaszam = 1;
                  sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                  sheet2.getRange().get("B" + cellaszam).setText("Csomag szám/ME szám");
                  sheet2.getRange().get("C" + cellaszam).setText("Kiszállítás dátuma");
                  sheet2.getRange().get("D" + cellaszam).setText("Source_ref1");
                  sheet2.getRange().get("E" + cellaszam).setText("Source_ref2");
                  sheet2.getRange().get("F" + cellaszam).setText("Source_ref3");
                  sheet2.getRange().get("G" + cellaszam).setText("Source_ref4");
                  sheet2.getRange().get("H" + cellaszam).setText("Source_ref5");
                  cellaszam++;
                  
                  for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                  {
                      ResultSet rs = stmt.executeQuery("select belso.TRACY_SERIAL_NO,\r\n"
                              + "       belso.package_id,\r\n"
                              + "       sr.DATE_CREATED,\r\n"
                              + "       sr.SOURCE_REF1,\r\n"
                              + "       sr.SOURCE_REF2,\r\n"
                              + "       sr.SOURCE_REF3,\r\n"
                              + "       sr.SOURCE_REF4,\r\n"
                              + "       sr.SOURCE_REF5\r\n"
                              + "from ifsapp.INVENTORY_TRANSACTION_HIST2 sr,\r\n"
                              + "       (select TRACY_SERIAL_NO,\r\n"
                              + "           PACKAGE_ID\r\n"
                              + "       from   ifsapp.C_TRACY\r\n"
                              + "       where  3=3\r\n"
                              + "              \r\n"
                              + "              and TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"') belso\r\n"
                              + "where 3=3\r\n"
                              + "      and sr.WAIV_DEV_REJ_NO = to_char(belso.package_id)\r\n"
                              + "      and sr.TRANSACTION_CODE = 'OESHIP'");
                      if(rs.next())
                      {
                          String[] koztes =  rs.getString(3).split(" ");
                          sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C" + cellaszam).setText(koztes[0]);
                          sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                          sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                          sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                          sheet2.getRange().get("G" + cellaszam).setText(rs.getString(7));
                          sheet2.getRange().get("H" + cellaszam).setText(rs.getString(7));
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
                  String hova = System.getProperty("user.home") + "\\Desktop\\Retour adatok.xlsx";
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
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Retour adatok.xlsx néven!", "Info", 1); 
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
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }  
                               
         }
    }
}
