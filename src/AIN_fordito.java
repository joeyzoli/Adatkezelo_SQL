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

public class AIN_fordito extends JPanel {

    /**
     * Create the panel.
     */
    public AIN_fordito() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("AVM AIN szám átforgatás");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(465, 45, 269, 14);
        add(lblNewLabel);
        
        JButton megnyit_gomb = new JButton("Megnyitás");
        megnyit_gomb.addActionListener(new Fordito());
        megnyit_gomb.setBounds(485, 169, 89, 23);
        add(megnyit_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Fordito implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();                
                Workbook workbook = new Workbook();
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    
                    Workbook workbook2 = new Workbook();
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                     
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                     //jdbc mysql driver meghÃ­vÃ¡sa
                    
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                    Connection con;                   
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                    Statement stmt = con.createStatement();
                      int cellaszam = 1;
                      sheet2.getRange().get("A" + cellaszam).setText("AIN szám");
                      sheet2.getRange().get("B" + cellaszam).setText("Szériaszám");
                      
                      cellaszam++;
                      
                      for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                      {
                          String[] ain = datatable.getRows().get(szamlalo).getString(0).split(",");
                          ResultSet rs = stmt.executeQuery("select panel from videoton.fkovavm WHERE ain = '"+ ain[0] +"'");
                          if(rs.next())
                          {                        
                              sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                              sheet2.getRange().get("B" + cellaszam).setText(rs.getString(1));
                              
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
                      String hova = System.getProperty("user.home") + "\\Desktop\\AIN számok átforgatva.xlsx";
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
                      JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra AIN számok átforgatva.xlsx néven!", "Info", 1); 
                      con.close();
                }
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
