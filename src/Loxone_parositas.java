import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Loxone_parositas extends JPanel {
    private JTextField szeriaszam_mezo;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Loxone LS adatok.xlsx";

    /**
     * Create the panel.
     */
    public Loxone_parositas() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Excel megniytása");
        lblNewLabel.setBounds(435, 101, 116, 14);
        add(lblNewLabel);
        
        JButton excel_gomb = new JButton("New button");
        excel_gomb.setBounds(597, 97, 89, 23);
        add(excel_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Szériaszám");
        lblNewLabel_1.setBounds(435, 150, 96, 14);
        add(lblNewLabel_1);
        
        szeriaszam_mezo = new JTextField();
        szeriaszam_mezo.addKeyListener(new Enter());
        szeriaszam_mezo.setBounds(597, 147, 292, 20);
        add(szeriaszam_mezo);
        szeriaszam_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Tracy_kereses());
        keres_gomb.setBounds(534, 218, 89, 23);
        add(keres_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Loxone panelszám midnen adatának keresés");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setBounds(509, 38, 322, 14);
        add(lblNewLabel_2);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Tracy_kereses implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        @SuppressWarnings("resource")
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            Statement stmt = null;
            try
            {
                ResultSet result;
                JdbcAdapter jdbcAdapter;
                DataTable datatable;
                Workbook workbook;
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                    
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszÃ³ felhasznÃ¡lÃ³ nÃ©vvel
                System.out.println("Connection established......");
             
               
                String sql = "select nev, videoton.fkov.*\r\n"
                        + "from videoton.fkov\r\n"
                        + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                        + "where 3=3\r\n"
                        + " and panel = '"+ szeriaszam_mezo.getText() +"'";
                
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                           
                cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                if(result.next())
                {
                    int size =0;
                    if (result != null) 
                    {
                        result.last();    // moves cursor to the last row
                        size = result.getRow(); // get row id 
                    }
                    if(size > 1)
                    {
                        result.first();
                        datatable = new DataTable();
                        
                        workbook = new Workbook();
                        workbook.setVersion(ExcelVersion.Version2016); 
                        jdbcAdapter = new JdbcAdapter();         
                        jdbcAdapter.fillDataTable(datatable, result);
                     
                        //Get the first worksheet
                        Worksheet sheet = workbook.getWorksheets().get(0);
                        sheet.insertDataTable(datatable, true, 1, 1);
                        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                        sheet.getAllocatedRange().autoFitColumns();
                        sheet.getAllocatedRange().autoFitRows();
                            
                        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                         
                        result.close();
                        cstmt.close();
                        con.close();
                        workbook.setActiveSheetIndex(0);
                        JFileChooser mentes_helye = new JFileChooser();
                        mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                        mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        mentes_helye.showOpenDialog(mentes_helye);
                        File menteshelye = mentes_helye.getSelectedFile();
                        workbook.saveToFile(menteshelye.getAbsolutePath(), ExcelVersion.Version2016);
                        
                        FileInputStream fileStream = new FileInputStream(menteshelye.getAbsolutePath());
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(menteshelye.getAbsolutePath());
                            workbook2.write(output);
                            output.close();
                        }
                    }
                    else
                    {
                        sql = "select nev, videoton.fkov.*\r\n"
                                + "from videoton.fkov\r\n"
                                + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                + "where 3=3\r\n"
                                + " and szeriaszam = '"+ szeriaszam_mezo.getText() +"'";
                        System.out.println("Szériaszám "+result.getString(5));
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        result = cstmt.getResultSet();
                        
                        if(result.next())
                        {
                            sql = "select nev, videoton.fkov.*\r\n"
                                    + "from videoton.fkov\r\n"
                                    + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                    + "where 3=3\r\n"
                                    + " and panel = '"+ result.getString(5) +"'";
                            System.out.println("Panleszám "+ result.getString(5));
                            cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                            result = cstmt.getResultSet();
                        }
                        
                        
                        datatable = new DataTable();
                        
                        workbook = new Workbook();
                        workbook.setVersion(ExcelVersion.Version2016); 
                        jdbcAdapter = new JdbcAdapter();         
                        jdbcAdapter.fillDataTable(datatable, result);
                     
                        //Get the first worksheet
                        Worksheet sheet = workbook.getWorksheets().get(0);
                        sheet.insertDataTable(datatable, true, 1, 1);
                        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                        sheet.getAllocatedRange().autoFitColumns();
                        sheet.getAllocatedRange().autoFitRows();
                            
                        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                         
                        result.close();
                        cstmt.close();
                        con.close();
                        workbook.setActiveSheetIndex(0);
                        
                        workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                        
                        FileInputStream fileStream = new FileInputStream(menteshelye);
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(menteshelye);
                            workbook2.write(output);
                            output.close();
                        }
                    }
                }
                else
                {
                    sql = "select nev, videoton.fkov.*\r\n"
                            + "from videoton.fkov\r\n"
                            + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                            + "where 3=3\r\n"
                            + " and szeriaszam = '"+ szeriaszam_mezo.getText() +"'";
                    cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                    result = cstmt.getResultSet();
                    
                    if(result.next())
                    {
                        sql = "select nev, videoton.fkov.*\r\n"
                                + "from videoton.fkov\r\n"
                                + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                + "where 3=3\r\n"
                                + " and panel = '"+ result.getString(5) +"'";
                        System.out.println("Panleszám "+ result.getString(5));
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        result = cstmt.getResultSet();
                    }
                    
                    
                    datatable = new DataTable();
                    
                    workbook = new Workbook();
                    workbook.setVersion(ExcelVersion.Version2016); 
                    jdbcAdapter = new JdbcAdapter();         
                    jdbcAdapter.fillDataTable(datatable, result);
                 
                    //Get the first worksheet
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    sheet.insertDataTable(datatable, true, 1, 1);
                    sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                    sheet.getAllocatedRange().autoFitColumns();
                    sheet.getAllocatedRange().autoFitRows();
                        
                    sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                     
                    result.close();
                    cstmt.close();
                    con.close();
                    workbook.setActiveSheetIndex(0);
                    
                    workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                    
                    FileInputStream fileStream = new FileInputStream(menteshelye);
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(menteshelye);
                        workbook2.write(output);
                        output.close();
                    }
                }
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Infó", 1);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
            finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
            {
                try 
                {
                  if (stmt != null)
                     con.close();
                } 
                catch (SQLException se) {}
                try 
                {
                  if (con != null)
                     con.close();
                } 
                catch (SQLException se) 
                {
                  se.printStackTrace();
                }  
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        @SuppressWarnings("resource")
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                Connection con = null;
                Statement stmt = null;
                try
                {
                    ResultSet result;
                    JdbcAdapter jdbcAdapter;
                    DataTable datatable;
                    Workbook workbook;
                    //Registering the Driver
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                        
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszÃ³ felhasznÃ¡lÃ³ nÃ©vvel
                    System.out.println("Connection established......");
                 
                   
                    String sql = "select nev, videoton.fkov.*\r\n"
                            + "from videoton.fkov\r\n"
                            + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                            + "where 3=3\r\n"
                            + " and panel = '"+ szeriaszam_mezo.getText() +"'";
                    
                    Statement cstmt = con.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                               
                    cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                    result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                    if(result.next())
                    {
                        int size =0;
                        if (result != null) 
                        {
                            result.last();    // moves cursor to the last row
                            size = result.getRow(); // get row id 
                        }
                        if(size > 1)
                        {
                            result.first();
                            datatable = new DataTable();
                            
                            workbook = new Workbook();
                            workbook.setVersion(ExcelVersion.Version2016); 
                            jdbcAdapter = new JdbcAdapter();         
                            jdbcAdapter.fillDataTable(datatable, result);
                         
                            //Get the first worksheet
                            Worksheet sheet = workbook.getWorksheets().get(0);
                            sheet.insertDataTable(datatable, true, 1, 1);
                            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                            sheet.getAllocatedRange().autoFitColumns();
                            sheet.getAllocatedRange().autoFitRows();
                                
                            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                             
                            result.close();
                            cstmt.close();
                            con.close();
                            workbook.setActiveSheetIndex(0);
                            JFileChooser mentes_helye = new JFileChooser();
                            mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                            mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                            mentes_helye.showOpenDialog(mentes_helye);
                            File menteshelye = mentes_helye.getSelectedFile();
                            workbook.saveToFile(menteshelye.getAbsolutePath(), ExcelVersion.Version2016);
                            
                            FileInputStream fileStream = new FileInputStream(menteshelye.getAbsolutePath());
                            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                            {
                                for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                                {    
                                    workbook2.removeSheetAt(i); 
                                }      
                                FileOutputStream output = new FileOutputStream(menteshelye.getAbsolutePath());
                                workbook2.write(output);
                                output.close();
                            }
                        }
                        else
                        {
                            sql = "select nev, videoton.fkov.*\r\n"
                                    + "from videoton.fkov\r\n"
                                    + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                    + "where 3=3\r\n"
                                    + " and szeriaszam = '"+ szeriaszam_mezo.getText() +"'";
                            System.out.println("Szériaszám "+result.getString(5));
                            cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                            result = cstmt.getResultSet();
                            
                            if(result.next())
                            {
                                sql = "select nev, videoton.fkov.*\r\n"
                                        + "from videoton.fkov\r\n"
                                        + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                        + "where 3=3\r\n"
                                        + " and panel = '"+ result.getString(5) +"'";
                                System.out.println("Panleszám "+ result.getString(5));
                                cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                                result = cstmt.getResultSet();
                            }
                            
                            
                            datatable = new DataTable();
                            
                            workbook = new Workbook();
                            workbook.setVersion(ExcelVersion.Version2016); 
                            jdbcAdapter = new JdbcAdapter();         
                            jdbcAdapter.fillDataTable(datatable, result);
                         
                            //Get the first worksheet
                            Worksheet sheet = workbook.getWorksheets().get(0);
                            sheet.insertDataTable(datatable, true, 1, 1);
                            sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                            sheet.getAllocatedRange().autoFitColumns();
                            sheet.getAllocatedRange().autoFitRows();
                                
                            sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                             
                            result.close();
                            cstmt.close();
                            con.close();
                            workbook.setActiveSheetIndex(0);
                            
                            workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                            
                            FileInputStream fileStream = new FileInputStream(menteshelye);
                            try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                            {
                                for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                                {    
                                    workbook2.removeSheetAt(i); 
                                }      
                                FileOutputStream output = new FileOutputStream(menteshelye);
                                workbook2.write(output);
                                output.close();
                            }
                        }
                    }
                    else
                    {
                        sql = "select nev, videoton.fkov.*\r\n"
                                + "from videoton.fkov\r\n"
                                + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                + "where 3=3\r\n"
                                + " and szeriaszam = '"+ szeriaszam_mezo.getText() +"'";
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        result = cstmt.getResultSet();
                        
                        if(result.next())
                        {
                            sql = "select nev, videoton.fkov.*\r\n"
                                    + "from videoton.fkov\r\n"
                                    + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                    + "where 3=3\r\n"
                                    + " and panel = '"+ result.getString(5) +"'";
                            System.out.println("Panleszám "+ result.getString(5));
                            cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                            result = cstmt.getResultSet();
                        }
                        
                        
                        datatable = new DataTable();
                        
                        workbook = new Workbook();
                        workbook.setVersion(ExcelVersion.Version2016); 
                        jdbcAdapter = new JdbcAdapter();         
                        jdbcAdapter.fillDataTable(datatable, result);
                     
                        //Get the first worksheet
                        Worksheet sheet = workbook.getWorksheets().get(0);
                        sheet.insertDataTable(datatable, true, 1, 1);
                        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                        sheet.getAllocatedRange().autoFitColumns();
                        sheet.getAllocatedRange().autoFitRows();
                            
                        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                         
                        result.close();
                        cstmt.close();
                        con.close();
                        workbook.setActiveSheetIndex(0);
                        
                        workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                        
                        FileInputStream fileStream = new FileInputStream(menteshelye);
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(menteshelye);
                            workbook2.write(output);
                            output.close();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Mentés sikeres", "Infó", 1);
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
                finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
                {
                    try 
                    {
                      if (stmt != null)
                         con.close();
                    } 
                    catch (SQLException se) {}
                    try 
                    {
                      if (con != null)
                         con.close();
                    } 
                    catch (SQLException se) 
                    {
                      se.printStackTrace();
                    }  
                }
             }
            
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }

}
