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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class AVM_javitasok extends JPanel {
    private int fajlszam = 1;
    private JDatePickerImpl datum_tol;
    private JDatePickerImpl datum_ig;

    /**
     * Create the panel.
     */
    public AVM_javitasok() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        Date date2 = new Date();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate date = LocalDate.parse(formatter2.format(date2), formatter);
        // Increment the date by one day
        LocalDate newDate = date.minusDays(1);
        
        Date date3 = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        /*String dateValue = "2024.03.23";
        try {
            date3 = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        UtilDateModel model = new UtilDateModel();
        model.setValue(date3);
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);        
        UtilDateModel model2 = new UtilDateModel();        
        model2.setValue(date3);
        Properties p2 = new Properties();
        p2.put("text.today", "Ma");
        p2.put("text.month", "Hónap");
        p2.put("text.year", "Év");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2); 
        
        JLabel lblNewLabel = new JLabel("AVM javítára került panelek adatai");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(463, 62, 270, 14);
        add(lblNewLabel);
        
        datum_tol = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        datum_tol.setBounds(569, 119, 120, 20);
        add(datum_tol);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(475, 122, 64, 14);
        add(lblNewLabel_1);
        
        JButton keres_gomb = new JButton("Keres");
        keres_gomb.addActionListener(new Hozzaad());
        keres_gomb.setBounds(518, 205, 89, 23);
        add(keres_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(475, 159, 84, 14);
        add(lblNewLabel_2);
        
        datum_ig = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        datum_ig.setBounds(569, 156, 120, 20);
        add(datum_ig);
        /*
        Date date2 = new Date();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate date = LocalDate.parse(formatter2.format(date2), formatter);
        // Increment the date by one day
        LocalDate newDate = date.minusDays(1);
        // Format the new date as a string             
        
        JXDatePicker picker = new JXDatePicker();      
        picker.setBounds(100, 100, 120, 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        picker.setFormats(dateFormat);
        picker.getEditor().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("You have foucs");
                System.out.println(picker.getDate().toString());
                System.out.println(dateFormat.format(picker.getDate()));
            }
        });
        picker.getMonthView().setZoomable(true);
        add(picker);*/
    }
    
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy.MM.dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            //System.out.println(datePicker.getJFormattedTextField().getText());
            return "";
        }       

    }
    
    class Hozzaad implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Connection conn = null;
                Connection conn2 = null;
                Statement stmt = null;
                Statement stmt2 = null; 
                try 
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    stmt = (Statement) conn.createStatement();
                    conn2 = DriverManager.getConnection("jdbc:mysql://192.168.5.145/", "quality", "Qua25!");
                    stmt2 = conn2.createStatement();
                    String sql = "select Panelszam, Idopont from qualitydb.Beolvasott_panelek where Idopont >= '"+ datum_tol.getJFormattedTextField().getText() +" 00:00:00' and Idopont <= '"+ datum_ig.getJFormattedTextField().getText() +" 23:59:59'  and Projekt = 'AVM'";
                    stmt.execute(sql);
                    ResultSet rs = stmt.getResultSet();
                    ResultSet rs2 = null;
                    Workbook workbook = new Workbook();
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    int cellaszam = 1;
                    sheet.getRange().get("A" + cellaszam).setText("Javítási bevétel");
                    sheet.getRange().get("B" + cellaszam).setText("Panelszám");
                    sheet.getRange().get("C" + cellaszam).setText("UT beolvasás");
                    sheet.getRange().get("D" + cellaszam).setText("Kiesés időpontja");
                    sheet.getRange().get("E" + cellaszam).setText("UT teszt óta eltelt nap");
                    sheet.getRange().get("F" + cellaszam).setText("kiesés helye");
                    sheet.getRange().get("G" + cellaszam).setText("Eredmény");
                    sheet.getRange().get("H" + cellaszam).setText("failtestnames");
                    sheet.getRange().get("I" + cellaszam).setText("Error");
                    sheet.getRange().get("J" + cellaszam).setText("beolvasás előtti tesztek száma");
                    sheet.getRange().get("K" + cellaszam).setText("Op ID");
                    sheet.getRange().get("L" + cellaszam).setText("Pozíció");
                    sheet.getRange().get("M" + cellaszam).setText("Hibakód");
                    cellaszam++;
                    int szam = 1;
                    while(rs.next())
                    {
                        System.out.println("Fut a while" + szam);
                        szam++;
                        sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                        if(rs.getString(1).indexOf(",") > 0)
                        {
                            String[] ain = rs.getString(1).split(",");
                            rs2 = stmt2.executeQuery("select panel from videoton.fkovavm WHERE ain = '"+ ain[0] +"'");
                            if(rs2.next())
                            {                        
                                sheet.getRange().get("B" + cellaszam).setText(rs2.getString(1));                                
                            }                      
                            else
                            {
                                sheet.getRange().get("B" + cellaszam).setText("");
                            }
                        }
                        else
                        { 
                            sheet.getRange().get("B" + cellaszam).setText(rs.getString(1));
                        }                        
                        sheet.getRange().get("C" + cellaszam).setText(rs.getString(2));
                        if(sheet.getRange().get("B" + cellaszam).getText().equals("")) {}
                        else
                        {
                            rs2 = stmt2.executeQuery("select nev, ido, \r\n"
                                    + "        if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as 'eredmény',\r\n"
                                    + "        failtestnames, error, tesztszam, dolgozo, poz, hibakod\r\n"
                                    + "from videoton.fkov\r\n"
                                    + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                                    + "where 3 = 3\r\n"
                                    + "and panel like '"+ sheet.getRange().get("B" + cellaszam).getText() +"'\r\n"
                                    + "and nev not like 'Javítás%' and ido < '"+ rs.getString(2).replace("-", ".") +"'\r\n"
                                    + "order by ido desc limit 1");
                        }
                        if(rs2.next())
                        { 
                            sheet.getRange().get("D" + cellaszam).setText(rs2.getString(2));
                            SimpleDateFormat dtf = new SimpleDateFormat("yyyy.MM.dd");
                            String[] datum2 = rs.getString(2).replace("-", ".").split(" ");
                            String[] datum1 = rs2.getString(2).replace("-", ".").split(" ");
                            String inputString1 = datum1[0];
                            String inputString2 = datum2[0];

                            Date date1 = dtf.parse(inputString1);
                            Date date2 = dtf.parse(inputString2);
                            long diff = date2.getTime() - date1.getTime();
                            //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                            sheet.getRange().get("E" + cellaszam).setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
                            sheet.getRange().get("F" + cellaszam).setText(rs2.getString(1));
                            sheet.getRange().get("G" + cellaszam).setText(rs2.getString(3));
                            sheet.getRange().get("H" + cellaszam).setText(rs2.getString(4));
                            sheet.getRange().get("I" + cellaszam).setText(rs2.getString(5));
                            sheet.getRange().get("J" + cellaszam).setText(rs2.getString(6));
                            sheet.getRange().get("K" + cellaszam).setText(rs2.getString(7));
                            sheet.getRange().get("L" + cellaszam).setText(rs2.getString(8));
                            sheet.getRange().get("M" + cellaszam).setText(rs2.getString(9));
                        }
                        cellaszam++;
                    }
                    
                    sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                    sheet.getAllocatedRange().autoFitColumns();
                    sheet.getAllocatedRange().autoFitRows();
                    sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\Desktop\\AVM javítási adatok.xlsx";
                    // FileLock lock = null;
                    try 
                    {                                                
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
                        System.out.println("file is not locked");
                        JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra AVM javítási adatok.xlsx néven!", "Info", 1);
                    } 
                    catch (Exception e1) 
                    {
                        System.out.println("file is locked");
                        hova = System.getProperty("user.home") + "\\Desktop\\AVM javítási adatok_"+ fajlszam +".xlsx";
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
                        JOptionPane.showMessageDialog(null, "Kész! \n A fájl meg volt nyitva így AVM javítási adatok_"+ fajlszam +".xlsx néven!", "Info", 1);
                        fajlszam++;
                    } /*finally {
                        lock.release();
                    }*/                    
                    Foablak.frame.setCursor(null);
                    stmt.close();
                    conn.close();
                    stmt2.close();
                    conn2.close();                
                }          
                catch (Exception e1) 
                {
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                } finally 
                {
                   try 
                   {
                      if (stmt != null)
                          stmt.close();
                   } 
                   catch (SQLException se) {}
                   try 
                   {
                      if (conn != null)
                         conn.close();
                   } 
                   catch (SQLException se) 
                   {
                       se.printStackTrace();
                       String hibauzenet = se.toString();
                       Email hibakuldes = new Email();
                       hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                       JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
                   }  
                }
                
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
}
