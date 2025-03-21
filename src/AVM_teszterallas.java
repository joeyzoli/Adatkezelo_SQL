import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import java.awt.Cursor;
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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;

public class AVM_teszterallas extends JPanel {
    
    private JDatePickerImpl datum;
    private JXDatePicker datum_tol;
    private JXDatePicker datum_ig;

    /**
     * Create the panel.
     */
    public AVM_teszterallas() {
        setLayout(null);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new AVM());
        start_gomb.setBounds(488, 121, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        Date date2 = new Date();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate date = LocalDate.parse(formatter2.format(date2), formatter);
        // Increment the date by one day
        LocalDate newDate = date.minusDays(1);
        
        Date date3 = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());       
        UtilDateModel model = new UtilDateModel();
        model.setValue(date3);
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);                

        datum = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        datum.setBounds(478, 81, 120, 20);
        add(datum);
        
        JLabel lblNewLabel = new JLabel("AVM teszterállás");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(488, 45, 160, 14);
        add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(28, 153, 1139, 14);
        add(separator);
        
        JLabel lblNewLabel_1 = new JLabel("AVM Succes Rate");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(488, 195, 160, 14);
        add(lblNewLabel_1);
        
        datum_tol = new JXDatePicker();
        datum_tol.setBounds(502, 233, 110, 20);
        add(datum_tol);
        
        datum_ig = new JXDatePicker();
        datum_ig.setBounds(502, 280, 110, 20);
        add(datum_ig);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -tól");
        lblNewLabel_2.setBounds(403, 236, 89, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Dátum -ig");
        lblNewLabel_3.setBounds(403, 283, 89, 14);
        add(lblNewLabel_3);
        
        JButton start2_gomb = new JButton("Start");
        start2_gomb.addActionListener(new Succes_rate());
        start2_gomb.setBounds(488, 336, 89, 23);
        add(start2_gomb);

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
    
    class AVM implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            Statement stmt = null;
            try
            {
                
                ResultSet rs = null;
                Workbook workbook = new Workbook();;
                workbook.setVersion(ExcelVersion.Version2016); 
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));     
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ip címhez való csatlakozás
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszó felhasználóval
                System.out.println("Connection established......");
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook.getWorksheets().get(1);
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);           
                
                int cellaszam = 1;
                sheet.getRange().get("A" + cellaszam).setText("Teszter szám");
                sheet.getRange().get("B" + cellaszam).setText("BS");
                sheet.getRange().get("C" + cellaszam).setText("Teszter szám");
                sheet.getRange().get("D" + cellaszam).setText("Wlan");
                sheet.getRange().get("E" + cellaszam).setText("Teszter szám");
                sheet.getRange().get("F" + cellaszam).setText("Life");
                sheet.getRange().get("G" + cellaszam).setText("Teszter szám");
                sheet.getRange().get("H" + cellaszam).setText("Jelölő");
                sheet.getRange().get("I" + cellaszam).setText("Teszter szám");
                sheet.getRange().get("J" + cellaszam).setText("Csomagolás");
                cellaszam++;
                int bs = 0;            
                int hely = 0;
                
                int cellaszam2 = 1;
                sheet2.getRange().get("A" + cellaszam2).setText("Teszter szám");
                sheet2.getRange().get("B" + cellaszam2).setText("Folyamat");
                sheet2.getRange().get("C" + cellaszam2).setText("Állás kezdete");
                sheet2.getRange().get("D" + cellaszam2).setText("Állás idő");
                cellaszam2++;
                
                //////////////////////////////////////////////////////// délelött
                for(int szamlalo3 = 1; szamlalo3 < 6; szamlalo3++)
                {
                    if(szamlalo3 ==1)
                    {
                        hely = 112;
                        cellaszam = 2;
                    }
                    else if(szamlalo3 ==2)
                    {
                        hely = 113;
                        cellaszam = 2;
                    }
                    else if(szamlalo3 ==3)
                    {
                        hely = 114;
                        cellaszam = 2;
                    }
                    else if(szamlalo3 ==4)
                    {
                        hely = 115;
                        cellaszam = 2;
                    }
                    else
                    {
                        hely = 116;
                        cellaszam = 2;
                    }
                    for(int szamlalo = 1; szamlalo < 12; szamlalo++)
                    {
                        String sql = "";
                        if(hely == 116)
                        {
                            sql = "select    ido, cast(ido as char), alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"05:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"13:55:00\"), \"-\", \".\") and\r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  ido asc";
                        }
                        else
                        {
                            sql = "select    teststarttime, testfinishtime, alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"05:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"13:55:00\"), \"-\", \".\") and\r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  teststarttime asc";
                        }
                        
                        
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        rs = cstmt.getResultSet();
                        
                        DataTable datatable = new DataTable();
                        JdbcAdapter jdbcAdapter = new JdbcAdapter();
                        jdbcAdapter.fillDataTable(datatable, rs);
                        if(datatable.getRows().size() > 0)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size() - 1; szamlalo2++)
                            {
                                String hely2 = "";
                                if(hely == 112)
                                {
                                    hely2 = "BS";
                                }
                                else if(hely == 113)
                                {
                                    hely2 = "Wlan";
                                }
                                else if(hely == 114)
                                {
                                    hely2 = "Life";
                                }
                                else if(hely == 115)
                                {
                                    hely2 = "Jelölő";
                                }
                                else
                                {
                                    hely2 = "Csomagolás";
                                }
                                if(szamlalo2 == 0)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 05:55:00");
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        bs += (diff/1000)/60;
                                        //System.out.println((diff/1000)/60);
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
                                else if(szamlalo2 == datatable.getRows().size() - 2)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse((datatable.getRows().get(datatable.getRows().size() - 1)).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 13:54:59");
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        bs += (diff/1000)/60;
                                        //System.out.println((diff/1000)/60);
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
                                else
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datatable.getRows().get(szamlalo2).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2+1)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        bs += (diff/1000)/60;
                                        //System.out.println((diff/1000)/60);
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
            
                            }
                            
                            if(szamlalo3 ==1)
                            {
                                sheet.getRange().get("A" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("B" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("BS");
                            }
                            else if(szamlalo3 ==2)
                            {
                                sheet.getRange().get("C" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("D" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Wlan");
                            }
                            else if(szamlalo3 ==3)
                            {
                                sheet.getRange().get("E" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("F" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Life");
                            }
                            else if(szamlalo3 ==4)
                            {
                                sheet.getRange().get("G" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("H" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Jelölő");
                            }
                            else
                            {
                                sheet.getRange().get("I" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("J" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Csomagolás");
                            }
        
                            //System.out.println(bs/60);
                            
                            bs = 0;
                        }
                        else
                        {
                            System.out.println("nincs eredmény");
                            System.out.println("Hely: "+ hely);
                            System.out.println("alsor: "+ szamlalo);
                        }
                    }
                    System.out.println("Fut a legkülső for");
                }
                bs = 0;
                sheet.getRange().get("A" + 13).setText("Délután");
                ////////////////////////////////////////////////////////////////////// délután
                
                for(int szamlalo3 = 1; szamlalo3 < 6; szamlalo3++)
                {
                    if(szamlalo3 == 1)
                    {
                        hely = 112;
                        cellaszam = 14;
                    }
                    else if(szamlalo3 == 2)
                    {
                        hely = 113;
                        cellaszam = 14;
                    }
                    else if(szamlalo3 == 3)
                    {
                        hely = 114;
                        cellaszam = 14;
                    }
                    else if(szamlalo3 == 4)
                    {
                        hely = 115;
                        cellaszam = 14;
                    }
                    else
                    {
                        hely = 116;
                        cellaszam = 14;
                    }
                    for(int szamlalo = 1; szamlalo < 12; szamlalo++)
                    {
                        String sql = "";
                        if(hely == 116)
                        {
                            sql = "select    ido, cast(ido as char), alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"13:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"21:55:00\"), \"-\", \".\") and\r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  ido asc";
                        }
                        else
                        {
                            sql = "select    teststarttime, testfinishtime, alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"13:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"21:55:00\"), \"-\", \".\") and\r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  teststarttime asc";
                        }                      
                        
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        rs = cstmt.getResultSet();
                        
                        DataTable datatable = new DataTable();
                        JdbcAdapter jdbcAdapter = new JdbcAdapter();
                        jdbcAdapter.fillDataTable(datatable, rs);
                        if(datatable.getRows().size() > 0)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size() - 1; szamlalo2++)
                            {
                                String hely2 = "";
                                if(hely == 112)
                                {
                                    hely2 = "BS";
                                }
                                else if(hely == 113)
                                {
                                    hely2 = "Wlan";
                                }
                                else if(hely == 114)
                                {
                                    hely2 = "Life";
                                }
                                else if(hely == 115)
                                {
                                    hely2 = "Jelölő";
                                }
                                else
                                {
                                    hely2 = "Csomagolás";
                                }
                                if(szamlalo2 == 0)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 13:55:00");
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
                                else if(szamlalo2 == datatable.getRows().size() - 2)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse((datatable.getRows().get(datatable.getRows().size() - 1)).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 21:54:59");
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                    
                                    //System.out.println("Fut az utoló sor");
                                }
                                else
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datatable.getRows().get(szamlalo2).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2+1)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
            
                            }
                            
                            if(szamlalo3 ==1)
                            {
                                sheet.getRange().get("A" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("B" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("BS");
                            }
                            else if(szamlalo3 ==2)
                            {
                                sheet.getRange().get("C" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("D" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Wlan");
                            }
                            else if(szamlalo3 ==3)
                            {
                                sheet.getRange().get("E" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("F" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Life");
                            }
                            else if(szamlalo3 ==4)
                            {
                                sheet.getRange().get("G" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("H" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Jelölő");
                            }   
                            else
                            {
                                sheet.getRange().get("I" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("J" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Csomagolás");
                            }   
        
                            //System.out.println(bs/60);
                            
                            bs = 0;
                        }
                        else
                        {
                            System.out.println("nincs eredmény");
                            System.out.println("Hely: "+ hely);
                            System.out.println("alsor: "+ szamlalo);
                        }
                    }
                    System.out.println("Fut a legkülső for");
                }
                
                
                bs = 0;
                sheet.getRange().get("A" + 25).setText("Éjszaka");
                ////////////////////////////////////////////////////////////////////// Éjszaka
                
                for(int szamlalo3 = 1; szamlalo3 < 6; szamlalo3++)
                {
                    if(szamlalo3 == 1)
                    {
                        hely = 112;
                        cellaszam = 26;
                    }
                    else if(szamlalo3 == 2)
                    {
                        hely = 113;
                        cellaszam = 26;
                    }
                    else if(szamlalo3 == 3)
                    {
                        hely = 114;
                        cellaszam = 26;
                    }
                    else if(szamlalo3 == 4)
                    {
                        hely = 115;
                        cellaszam = 26;
                    }
                    else
                    {
                        hely = 116;
                        cellaszam = 26;
                    }
                    for(int szamlalo = 1; szamlalo < 12; szamlalo++)
                    {
                        String sql = "";
                        if(hely == 116)
                        {
                            sql = "select    ido, cast(ido as char), alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"21:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"' , interval 1 day), \" \", \"05:55:00 \"), \"-\", \".\" ) and \r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  teststarttime asc";
                        }
                        else
                        {
                            sql = "select    teststarttime, testfinishtime, alsor as teszterszam     -- testfinishtime\r\n"
                                    + "    from    videoton.fkov\r\n"
                                    + "    where   3 = 3 and\r\n"
                                    + "            videoton.fkov.ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"' , \" \", \"21:55:00\"), \"-\", \".\") and\r\n"
                                    + "            videoton.fkov.ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"' , interval 1 day), \" \", \"05:55:00 \"), \"-\", \".\" ) and \r\n"
                                    + "            hely = '"+ hely +"' and alsor = '"+ szamlalo +"' order by  teststarttime asc";
                        }
                        
                        
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        rs = cstmt.getResultSet();
                        
                        DataTable datatable = new DataTable();
                        JdbcAdapter jdbcAdapter = new JdbcAdapter();
                        jdbcAdapter.fillDataTable(datatable, rs);
                        if(datatable.getRows().size() > 0)
                        {
                            for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size() - 1; szamlalo2++)
                            {
                                String hely2 = "";
                                if(hely == 112)
                                {
                                    hely2 = "BS";
                                }
                                else if(hely == 113)
                                {
                                    hely2 = "Wlan";
                                }
                                else if(hely == 114)
                                {
                                    hely2 = "Life";
                                }
                                else if(hely == 115)
                                {
                                    hely2 = "Jelölő";
                                }
                                else
                                {
                                    hely2 = "Csomagolás";
                                }
                                if(szamlalo2 == 0)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 21:55:00");
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
                                else if(szamlalo2 == datatable.getRows().size() - 2)
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse((datatable.getRows().get(datatable.getRows().size() - 1)).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datum.getJFormattedTextField().getText() +" 05:54:59");
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                    
                                    //System.out.println("Fut az utoló sor");
                                }
                                else
                                {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                    Date firstParsedDate = dateFormat.parse(datatable.getRows().get(szamlalo2).getString(1));
                                    Date secondParsedDate = dateFormat.parse(datatable.getRows().get((szamlalo2+1)).getString(0));
                                    long diff = secondParsedDate.getTime() - firstParsedDate.getTime();
                                    if((diff/1000) > 299 && (diff/1000) < 2700)
                                    {
                                        //System.out.println(diff/1000);
                                        bs += (diff/1000)/60;
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        cellaszam2++;
                                    }
                                    else if((diff/1000) > 2699)
                                    {
                                        sheet2.getRange().get("A" + cellaszam2).setText(String.valueOf(szamlalo));
                                        sheet2.getRange().get("B" + cellaszam2).setText(String.valueOf(hely2));
                                        sheet2.getRange().get("C" + cellaszam2).setText(dateFormat.format(firstParsedDate));
                                        sheet2.getRange().get("D" + cellaszam2).setText(String.valueOf((diff/1000)/60));
                                        sheet2.getRange().get("E" + cellaszam2).setText("Hosszabb teszterállás");
                                        cellaszam2++;
                                    }
                                    else{}
                                }
            
                            }
                            
                            if(szamlalo3 ==1)
                            {
                                sheet.getRange().get("A" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("B" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("BS");
                            }
                            else if(szamlalo3 ==2)
                            {
                                sheet.getRange().get("C" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("D" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Wlan");
                            }
                            else if(szamlalo3 ==3)
                            {
                                sheet.getRange().get("E" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("F" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Life");
                            }
                            else if(szamlalo3 ==4)
                            {
                                sheet.getRange().get("G" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("H" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Jelölő");
                            }
                            else
                            {
                                sheet.getRange().get("I" + cellaszam).setText(String.valueOf(szamlalo));
                                sheet.getRange().get("J" + cellaszam).setNumberValue(bs-30);
                                cellaszam++;
                                System.out.println("Csomagolás");
                            }
        
                            //System.out.println(bs/60);
                            
                            bs = 0;
                        }
                        else
                        {
                            System.out.println("nincs eredmény");
                            System.out.println("Hely: "+ hely);
                            System.out.println("alsor: "+ szamlalo);
                        }
                    }
                    System.out.println("Fut a legkülső for");
                }
                
                sheet.getRange().get("L" + 1).setText("Folyamat");
                sheet.getRange().get("L" + 2).setText("BS");
                sheet.getRange().get("L" + 3).setText("Wlan");
                sheet.getRange().get("L" + 4).setText("Life");
                sheet.getRange().get("L" + 13).setText("BS");
                sheet.getRange().get("L" + 14).setText("Wlan");
                sheet.getRange().get("L" + 15).setText("Life");
                sheet.getRange().get("L" + 24).setText("BS");
                sheet.getRange().get("L" + 25).setText("Wlan");
                sheet.getRange().get("L" + 26).setText("Life");
                
                sheet.getRange().get("M" + 1).setText("Össz állásidő");
                sheet.getCellRange("M" + 2).setFormula("=SZUM(B2:B7)");
                sheet.getCellRange("M" + 3).setFormula("=SZUM(D2:D12)");
                sheet.getCellRange("M" + 4).setFormula("=SZUM(F2:F12)");               
                sheet.getCellRange("M" + 13).setFormula("=SZUM(B14:B19)");
                sheet.getCellRange("M" + 14).setFormula("=SZUM(D14:D24)");
                sheet.getCellRange("M" + 15).setFormula("=SZUM(F14:F24)");
                sheet.getCellRange("M" + 24).setFormula("=SZUM(B26:B31)");
                sheet.getCellRange("M" + 25).setFormula("=SZUM(D26:D36)");
                sheet.getCellRange("M" + 26).setFormula("=SZUM(F26:F35)");
                
                sheet.getRange().get("O" + 1).setText("SUM");
                sheet.getCellRange("O" + 2).setFormula("=M2+M13+M24");
                sheet.getCellRange("O" + 3).setFormula("=M3+M14+M25");
                sheet.getCellRange("O" + 4).setFormula("=M4+M15+M26");
                
                //Get the first worksheet
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();                    
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítása
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();                    
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítása
                 
                rs.close();
                cstmt.close();
                con.close();
                //workbook.setActiveSheetIndex(0);
                /*JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);*/
                String mentes_helye = System.getProperty("user.home") + "\\Desktop\\állásidők "+ datum.getJFormattedTextField().getText() +".xlsx";
                workbook.saveToFile(mentes_helye, ExcelVersion.Version2016);  
                FileInputStream fileStream = new FileInputStream(mentes_helye);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>1 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(mentes_helye);
                    workbook2.write(output);
                    output.close();
                }
                Foablak.frame.setCursor(null); 
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
    
    class Succes_rate implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            Statement stmt = null;
            try
            {
                ResultSet result = null;
                JdbcAdapter jdbcAdapter;
                DataTable datatable;
                Workbook workbook;
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));     
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ip címhez való csatlakozás
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszó felhasználóval
                System.out.println("Connection established......");
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\AVM helyek.xlsx"; 
                Workbook workbook2 = new Workbook();
                workbook2.loadFromFile(excelfile1);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                DataTable datatable2 = new DataTable();
                datatable2 = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false );
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                
                for(int szamlalo = 1; szamlalo < datatable2.getRows().size(); szamlalo++)          //datatable2.getRows().size()
                {
                    String sql = "select  tempTable.Week,\n"
                            + "        if(fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as Result,\n"
                            + "        count(*) as Count\n"
                            + "from   videoton.fkov\n"
                            + "inner join  (select yearweek(ido, 1) as Week,\n"
                            + "            panel,\n"
                            + "            max(ido) as MaxTimestamp\n"
                            + "            from  videoton.fkov\n"
                            + "            where hely = '"+ datatable2.getRows().get(szamlalo).getString(1) +"' and\n"
                            + "            ido >= '"+ dateFormat.format(datum_tol.getDate()) +"' and ido <= '"+ dateFormat.format(datum_ig.getDate()) +"' and (yearweek(ido, 1) - concat('20',SUBSTRING(panel, 12,4))) < 4\n"
                            + "            group by 1, 2) as tempTable on tempTable.panel = fkov.panel and tempTable.MaxTimestamp = fkov.ido\n"
                            + "group by             1, 2;\n"
                            + "";
                
                    cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                    result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                    datatable = new DataTable();
                    System.out.println("Lefutott");
                    System.out.println(datatable2.getRows().get(szamlalo).getString(1));
                    workbook = new Workbook();
                    workbook.setVersion(ExcelVersion.Version2016); 
                    jdbcAdapter = new JdbcAdapter();         
                    jdbcAdapter.fillDataTable(datatable, result);
                    System.out.println("Datatableban");
                    //Get the first worksheet
                    Worksheet sheet = workbook.getWorksheets().get(0);                   
                    
                    sheet.insertDataTable(datatable, true, 1, 1);
                    sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                    sheet.getAllocatedRange().autoFitColumns();
                    sheet.getAllocatedRange().autoFitRows();
                        
                    sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s                                       
                    
                    String path = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\AVM Succes Rate\\"+ datatable2.getRows().get(szamlalo).getString(0) + ".xlsx";
                    
                    //workbook.setActiveSheetIndex(0);
                    //String path = System.getProperty("user.home") + "\\Desktop\\AVM Succes Rate\\"+ datatable2.getRows().get(szamlalo).getString(0) +".xlsx";
                    Workbook workbook4 = new Workbook();
                    workbook4.loadFromFile(path);
                    Worksheet sheet4 = workbook4.getWorksheets().get(0);                   
                    System.out.println(sheet.getRange().get("A" + 2).getNumberText());
                    System.out.println(sheet.getRange().get("B" + 2).getText());
                    
                    int sor = sheet4.getLastRow()+1;
                    if(sheet.getRange().get("B" + 2).getText().equals("Hiba"))
                    {
                        sheet4.getRange().get("D" + sor).setText(sheet.getRange().get("A" + 2).getNumberText());
                        sheet4.getRange().get("E" + sor).setText(sheet.getRange().get("B" + 2).getText());
                        sheet4.getRange().get("F" + sor).setNumberValue(Integer.valueOf(sheet.getRange().get("C" + 2).getNumberText()));
                        sheet4.getRange().get("A" + sor).setText(sheet.getRange().get("A" + 3).getNumberText());
                        sheet4.getRange().get("B" + sor).setText(sheet.getRange().get("B" + 3).getText());
                        sheet4.getRange().get("C" + sor).setNumberValue(Integer.valueOf(sheet.getRange().get("C" + 3).getNumberText()));                            
                    }
                    else
                    {
                        sheet4.getRange().get("D" + sor).setText(sheet.getRange().get("A" + 3).getNumberText());
                        sheet4.getRange().get("E" + sor).setText(sheet.getRange().get("B" + 3).getText());
                        sheet4.getRange().get("F" + sor).setNumberValue(Integer.valueOf(sheet.getRange().get("C" + 3).getNumberText()));
                        sheet4.getRange().get("A" + sor).setText(sheet.getRange().get("A" + 2).getNumberText());
                        sheet4.getRange().get("B" + sor).setText(sheet.getRange().get("B" + 2).getText());
                        sheet4.getRange().get("C" + sor).setNumberValue(Integer.valueOf(sheet.getRange().get("C" + 2).getNumberText()));
                    }                                      
                    
                    workbook4.saveToFile(path, ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(path);
                    try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook3.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(path);
                        workbook3.write(output);
                        output.close();
                    }
                }
                
                result.close();
                cstmt.close();
                con.close();
                Foablak.frame.setCursor(null); 
                JOptionPane.showMessageDialog(null, "Mentés sikeres \nMentve az alábbi helyre: minosegbiztositas\\Fájlok\\AVM Succes Rate\\ ", "Infó", 1);
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
}


