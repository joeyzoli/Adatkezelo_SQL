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

public class Telecom_utolso extends JPanel {

    /**
     * Create the panel.
     */
    public Telecom_utolso() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Telecom Desig utolsó folyamat lekérdezés");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(414, 34, 316, 27);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fájl megnyitása");
        lblNewLabel_1.setBounds(430, 151, 92, 14);
        add(lblNewLabel_1);
        
        JButton megnyit_gomb = new JButton("Megnyit");
        megnyit_gomb.addActionListener(new Lekerdezes());
        megnyit_gomb.setBounds(548, 147, 89, 23);
        add(megnyit_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
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
                  sheet2.getRange().get("B" + cellaszam).setText("Feldolgozás dátuma");
                  sheet2.getRange().get("C" + cellaszam).setText("Munkahely száma");
                  sheet2.getRange().get("D" + cellaszam).setText("Eredmény");
                  cellaszam++;
                  
                  for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                  {
                      ResultSet rs = stmt.executeQuery("select TRACY_SERIAL_NO,\r\n"
                              + "PROCESS_DATE,\r\n"
                              + "WORK_CENTER_NO, \r\n"
                              + "PASS "
                              + "from ifsapp.C_OPER_TRACY_OVW, \r\n"
                              + "(select TRACY_SERIAL_NO as szeriaszam,\r\n"
                              + "MAX(PROCESS_DATE) as maxido\r\n"
                              + "from ifsapp.C_OPER_TRACY_OVW\r\n"
                              + "where 3=3\r\n"
                              + "and TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"'\r\n"
                              + "group by TRACY_SERIAL_NO) belso\r\n"
                              + "where belso.szeriaszam = TRACY_SERIAL_NO\r\n"
                              + "and belso.maxido = PROCESS_DATE");
                      if(rs.next())
                      {                        
                          sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                          sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4)); 
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
                  String hova = System.getProperty("user.home") + "\\Desktop\\IFS utolsó folyamat.xlsx";
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
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1); 
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
