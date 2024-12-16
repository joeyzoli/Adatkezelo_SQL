import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class Techem_OQC extends JPanel {
    
    private String excelfile = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\OQC_control_datasheet.xlsx";
    private String excelfile2 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\OQC_control_datasheet_URES_UJ.xlsx";
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    private JTextField datumtol2_mezo;
    private JTextField datumig2_mezo;
    private JComboBox<String> cikk_box;
    private String excelfile3 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";
    
    /**
     * Create the panel.
     */
    public Techem_OQC() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Techem OQC adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(462, 40, 314, 14);
        add(lblNewLabel);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes());
        start_gomb.setBounds(519, 136, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(519, 227, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(519, 275, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(441, 230, 68, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(441, 278, 68, 14);
        add(lblNewLabel_2);
        
        JButton start2_gomb = new JButton("Start");
        start2_gomb.addActionListener(new Manualis_osszerak());
        start2_gomb.setBounds(519, 335, 89, 23);
        add(start2_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_3 = new JLabel("KPI adatok");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_3.setBounds(342, 407, 89, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Dátum -tól");
        lblNewLabel_4.setBounds(441, 407, 68, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Dátum -ig");
        lblNewLabel_5.setBounds(441, 451, 68, 14);
        add(lblNewLabel_5);
        
        datumtol2_mezo = new JTextField();
        datumtol2_mezo.setBounds(519, 404, 86, 20);
        add(datumtol2_mezo);
        datumtol2_mezo.setColumns(10);
        
        datumig2_mezo = new JTextField();
        datumig2_mezo.setBounds(519, 448, 86, 20);
        add(datumig2_mezo);
        datumig2_mezo.setColumns(10);
        
        JButton start3_gomb = new JButton("Start");
        start3_gomb.addActionListener(new Kereses());
        start3_gomb.setBounds(519, 497, 89, 23);
        add(start3_gomb);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(84, 382, 1045, 14);
        add(separator);
        
        JLabel lblNewLabel_6 = new JLabel("Alap lekérdezés");
        lblNewLabel_6.setBounds(389, 140, 107, 14);
        add(lblNewLabel_6);
        
        String[] cikkszamok = {"1742", "376421"};
        cikk_box = new JComboBox<String>(cikkszamok);                         //cikkszamok
        cikk_box.setBounds(519, 87, 161, 22);
        add(cikk_box);
        
        JLabel lblNewLabel_7 = new JLabel("Cikkszám");
        lblNewLabel_7.setBounds(414, 91, 82, 14);
        add(lblNewLabel_7);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();
                Workbook workbook2 = new Workbook();
                Workbook workbook3 = new Workbook();
                Workbook workbook5 = new Workbook();
                SQA_SQL napok = new SQA_SQL();
                if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                {
                    workbook.loadFromFile(excelfile);
                    workbook3.loadFromFile(excelfile);
                    workbook5.loadFromFile(excelfile);
                }
                else
                {
                    workbook.loadFromFile(excelfile2);
                    workbook3.loadFromFile(excelfile2);
                    workbook5.loadFromFile(excelfile2);
                }               
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                Worksheet sheet3 = workbook3.getWorksheets().get(0);
                Worksheet sheet5 = workbook5.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();  
                //ResultSet rs2 = null;
                //ResultSet rs3 = null;
                //Connection conn = null;
                //Statement stmt2 = null;        
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
                Connection con2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                Statement stmt2 = (Statement) con2.createStatement();
                
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                Connection con3 = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");
                Statement stmt3 = con3.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select contract, part_no, location_no, waiv_dev_rej_no, availability_control_id, to_char(RECEIPT_DATE ,'YYYY.MM.DD') from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '"+ String.valueOf(cikk_box.getSelectedItem()) +"' and QTY_ONHAND > 0\r\n"
                        + "and (location_no like 'TE0%' or location_no like 'MR%')");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                sheet2.insertDataTable(datatable, true, 1, 1);
                
                stmt.execute("Select location_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '"+ String.valueOf(cikk_box.getSelectedItem()) +"' and QTY_ONHAND > 0 and (location_no like 'TE0%' or location_no like 'KE%' or location_no like 'MR%') \r\n"
                        + " group by location_no");          //TE0%                 and location_no like 'TE0%'
                //int szam = 1;
                
                rs = stmt.getResultSet();
                ArrayList<String> raklapok = new ArrayList<String>();
                ArrayList<String> szeriaszamok = new ArrayList<String>();
                ArrayList<String> meszamok = new ArrayList<String>();
                while(rs.next())
                {
                    raklapok.add(rs.getString(1));
                }
                for(int szamlalo = 0; szamlalo < raklapok.size(); szamlalo++)      //raklapok.size()
                {
                    stmt.execute("Select waiv_dev_rej_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                            + "where location_no = '"+ raklapok.get(szamlalo) +"' and QTY_ONHAND > 0");
                    
                    rs = stmt.getResultSet();
                    int vanilyen = 0;
                    int vanilyen2 = 0;
                    int vanilyen3 = 0;
                    int cellaszam = 6;
                    int cellaszam2 = 6;
                    int cellaszam3 = 6;
                    int megjegyzes = 39;
                    int megjegyzes2 = 43;
                    int sorszam = 1;
                    meszamok.clear();
                    szeriaszamok.clear();
                    while(rs.next())
                    {
                        meszamok.add(rs.getString(1));  
                    }
                    
                    int harmincketto = 1;
                    for(int szamlalo3 = 0; szamlalo3 < meszamok.size(); szamlalo3++)
                    {
                        if(meszamok.get(szamlalo3).equals("*")) {}
                        else
                        {
                            ResultSet rs2 = null;
                            if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                            {
                                rs2 = stmt.executeQuery("select tracy_serial_no \r\n"
                                        + "from ifsapp.C_TRACY\r\n"
                                        + "where PACKAGE_ID = '"+ meszamok.get(szamlalo3) +"'");
                            }
                            else
                            {
                                rs2 = stmt.executeQuery("select ALT_TRACY_SERIAL_NO2 \r\n"
                                        + "from ifsapp.C_TRACY\r\n"
                                        + "where PACKAGE_ID = '"+ meszamok.get(szamlalo3) +"'");
                            }
                            //System.out.println("Nézi a raklapokat");
                            szeriaszamok.clear();
                            while(rs2.next())
                            {
                                szeriaszamok.add(rs2.getString(1));
                            }
                            
                            for(int szamlalo2 = 0; szamlalo2 < szeriaszamok.size(); szamlalo2++)
                            {
                                if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                                {
                                    if(harmincketto >= 65) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet5.getRange().get("D" + 2).setText(datum[0]);
                                            sheet5.getRange().get("J" + 2).setText(rs3.getString(3));
                                            sheet5.getRange().get("B" + cellaszam3).setText(rs3.getString(4));
                                            sheet5.getRange().get("C" + cellaszam3).setText(rs3.getString(5));
                                            sheet5.getRange().get("D" + cellaszam3).setText(rs3.getString(6));
                                            sheet5.getRange().get("E" + cellaszam3).setText(rs3.getString(7));
                                            sheet5.getRange().get("F" + cellaszam3).setText(rs3.getString(8));
                                            sheet5.getRange().get("G" + cellaszam3).setText(rs3.getString(9));
                                            sheet5.getRange().get("H" + cellaszam3).setText(rs3.getString(10));
                                            sheet5.getRange().get("I" + cellaszam3).setText(rs3.getString(11));
                                            sheet5.getRange().get("J" + cellaszam3).setText(rs3.getString(12));
                                            sheet5.getRange().get("K" + cellaszam3).setText(rs3.getString(13));
                                            sheet5.getRange().get("L" + cellaszam3).setText(rs3.getString(14));
                                            
                                            System.out.println(harmincketto);
                                            rs = stmt3.executeQuery("select max(ido)\r\n"
                                                    + "from videoton.fkov\r\n"
                                                    + "where\r\n"
                                                    + "videoton.fkov.hely = \"93\"   and\r\n"
                                                    + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                    + "and poz = 2");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                Date mikor = hosszu.parse(rs.getString(1));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("M" + cellaszam3).setText(szoveg);
                                                    sheet5.getCellRange("M" + cellaszam3 +":O"+ cellaszam3).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("M" + cellaszam3).setText(szoveg);
                                                    sheet5.getCellRange("M" + cellaszam3 +":O"+ cellaszam3).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet5.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam3++;
                                            vanilyen3++;
                                        }
                                    }
                                    else if(harmincketto >= 33 && harmincketto < 65) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet3.getRange().get("D" + 2).setText(datum[0]);
                                            sheet3.getRange().get("J" + 2).setText(rs3.getString(3));
                                            sheet3.getRange().get("B" + cellaszam2).setText(rs3.getString(4));
                                            sheet3.getRange().get("C" + cellaszam2).setText(rs3.getString(5));
                                            sheet3.getRange().get("D" + cellaszam2).setText(rs3.getString(6));
                                            sheet3.getRange().get("E" + cellaszam2).setText(rs3.getString(7));
                                            sheet3.getRange().get("F" + cellaszam2).setText(rs3.getString(8));
                                            sheet3.getRange().get("G" + cellaszam2).setText(rs3.getString(9));
                                            sheet3.getRange().get("H" + cellaszam2).setText(rs3.getString(10));
                                            sheet3.getRange().get("I" + cellaszam2).setText(rs3.getString(11));
                                            sheet3.getRange().get("J" + cellaszam2).setText(rs3.getString(12));
                                            sheet3.getRange().get("K" + cellaszam2).setText(rs3.getString(13));
                                            sheet3.getRange().get("L" + cellaszam2).setText(rs3.getString(14));
                                            
                                            System.out.println(harmincketto);
                                            rs = stmt3.executeQuery("select max(ido)\r\n"
                                                    + "from videoton.fkov\r\n"
                                                    + "where\r\n"
                                                    + "videoton.fkov.hely = \"93\"   and\r\n"
                                                    + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                    + "and poz = 2");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                Date mikor = hosszu.parse(rs.getString(1));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("M" + cellaszam2).setText(szoveg);
                                                    sheet3.getCellRange("M" + cellaszam2 +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("M" + cellaszam2).setText(szoveg);
                                                    sheet3.getCellRange("M" + cellaszam2 +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam2++;
                                            vanilyen2++;
                                        }
                                    }
                                    else
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                                            {
                                                String[] datum = rs3.getString(2).split(" ");
                                                sheet.getRange().get("D" + 2).setText(datum[0]);
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
                                                
                                                System.out.println(harmincketto);
                                                rs = stmt3.executeQuery("select max(ido)\r\n"
                                                        + "from videoton.fkov\r\n"
                                                        + "where\r\n"
                                                        + "videoton.fkov.hely = \"93\"   and\r\n"
                                                        + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                        + "and poz = 2");
                                                if(rs.next())
                                                {
                                                    String[] csakdatum =  rs.getString(1).split(" ");
                                                    if(Integer.valueOf(napok.tombvissza_sajat("select  DATEDIFF('" +csakdatum[0]+"', '"+sheet.getRange().get("D" + 2).getText().replace("-", ".")+"') from qualitydb.Techem_OQC")[0]) > 14)
                                                    {
                                                        
                                                        ResultSet rs4 = stmt3.executeQuery("select min(ido)\r\n"
                                                                + "from videoton.fkov\r\n"
                                                                + "where\r\n"
                                                                + "videoton.fkov.hely = '93'   and\r\n"
                                                                + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                                + "and poz = 2 and ido > '"+ datum[0] +"'");
                                                        if(rs4.next())
                                                        {
                                                            SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                            Date mikor = hosszu.parse(rs4.getString(1));
                                                            System.out.println("Régi "+ napok.tombvissza_sajat("select  DATEDIFF('" +csakdatum[0]+"', '"+sheet.getRange().get("D" + 2).getText().replace("-", ".")+"') from qualitydb.Techem_OQC")[0] +" Dátum: "+ rs4.getString(1)+ " dátum amivel keres: "+ sheet.getRange().get("D" + 2).getText()) ;
                                                            Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                            cal.setTime(mikor);               // sets calendar time/date
                                                            //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                            //cal.getTime();                         // returns new date object plus one hour
                                                            //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                            //String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                            //Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                            //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                            cal.add(Calendar.HOUR_OF_DAY, 300);
                                                            String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                            String szoveg = "Result after " + lejar[0];
                                                            sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                                            sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                        }
                                                    }
                                                    else
                                                    {
                                                        SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                        Date mikor = hosszu.parse(rs.getString(1));
                                                        Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                        cal.setTime(mikor);               // sets calendar time/date
                                                        //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                        //cal.getTime();                         // returns new date object plus one hour
                                                        //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                        String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                        Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                        //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                        if(ellenorzes.compareTo(mikor) > 0) 
                                                        {
                                                            //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                            cal.add(Calendar.HOUR_OF_DAY, 600);
                                                            String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                            String szoveg = "Result after " + lejar[0];
                                                            sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                                            sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                        }
                                                        else
                                                        {
                                                            cal.add(Calendar.HOUR_OF_DAY, 300);
                                                            String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                            String szoveg = "Result after " + lejar[0];
                                                            sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                                            sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                        }
                                                    }
                                                }
                                                if(rs3.getString(15) == null) {}
                                                else if(rs3.getString(15).equals(""))  {}
                                                else
                                                {
                                                    sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                    megjegyzes++;
                                                    sorszam++;
                                                }
                                                harmincketto++;
                                                cellaszam++;
                                                vanilyen++;
                                            }                                            
                                        }
                                    }
                                }   ///////if vége innen kezdődik a Funk
                                else
                                {
                                    if(harmincketto >= 73) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet5.getRange().get("D" + 2).setText(datum[0]);
                                            sheet5.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet5.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet5.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet5.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet5.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet5.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet5.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet5.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet5.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet5.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet5.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet5.getRange().get("D" + megjegyzes2).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes2++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                    else if(harmincketto >= 37 && harmincketto < 73) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet3.getRange().get("D" + 2).setText(datum[0]);
                                            sheet3.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet3.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet3.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet3.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet3.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet3.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet3.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet3.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet3.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet3.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet3.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet3.getRange().get("D" + megjegyzes2).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes2++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                    else
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet.getRange().get("D" + 2).setText(datum[0]);
                                            sheet.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet.getRange().get("D" + megjegyzes2).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes2++;
                                                sorszam++;
                                                System.out.println("Van megjegyzés "+ rs3.getString(15));
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                }
                            }       //for vége
                        }
                        
                    }
                    //System.out.println("Végzett az ME szám");
                    //System.out.println("Cellaszám "+ cellaszam);
                    //System.out.println("Vanilyen "+ vanilyen);
                    if(vanilyen > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +".xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook.getConverterSetting().setSheetFitToPage(true);
                        workbook.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                        System.out.println("Mentette az excelt " + raklapok.get(szamlalo) +".xlsx");
                    }
                    if(vanilyen2 > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +"-2.xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook3.getConverterSetting().setSheetFitToPage(true);
                        workbook3.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                    }
                    if(vanilyen3 > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +"-3.xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook5.getConverterSetting().setSheetFitToPage(true);
                        workbook5.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                    }
                    ///
                    System.out.println("végzett a raklappal");
                }
                               
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:AC1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:AC1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Raktárban levő anyag.xlsx");         //Techem OQC\\
                f.getParentFile().mkdirs(); 
                f.createNewFile();
                String hova = f.getAbsolutePath();
                workbook2.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook4.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook4.write(output);
                    output.close();
                }

                con.close(); 
                con2.close();
                con3.close();
                
                Process p = Runtime.getRuntime().exec("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\PDF_keszito.jar");               
                System.out.println(p);
                
                JOptionPane.showMessageDialog(null, "Mentve az asztalra", "Info", 1); 
                Foablak.frame.setCursor(null); 
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Manualis_osszerak implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();                
                workbook.loadFromFile(excelfile);               
                Worksheet sheet = workbook.getWorksheets().get(0);               
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
                Connection con2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                Statement stmt2 = (Statement) con2.createStatement();
                
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                Connection con3 = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");
                Statement stmt3 = con3.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = null;
      
                ArrayList<String> szeriaszamok = new ArrayList<String>();
                new ArrayList<String>();
                int cellaszam = 6;
                    int megjegyzes = 39;
                    int sorszam = 1;
                    int mentesszam = 1;
                    szeriaszamok.clear();
                   
                    
                    int harmincketto = 1;
                    stmt2.execute("select * from qualitydb.Techem_OQC where datum  >= '"+ datumtol_mezo.getText() +"' and datum <= '"+ datumig_mezo.getText() +"' order by id asc");
                    ResultSet rs2 = stmt2.getResultSet();
                    String[] datum = null;
                    while(rs2.next())
                    {
                        if(harmincketto == 33)
                        {
                            File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem "+ datum[0]+"_"+ mentesszam +".xlsx");          //\\Techem OQC
                            f.getParentFile().mkdirs(); 
                            f.createNewFile();                
                            String hova = f.getAbsolutePath();
                            workbook.getConverterSetting().setSheetFitToPage(true);
                            workbook.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                            FileInputStream fileStream = new FileInputStream(hova);
                            try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                            {
                                for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                                {    
                                    workbook4.removeSheetAt(i); 
                                }
                                
                                FileOutputStream output = new FileOutputStream(hova);
                                workbook4.write(output);
                                output.close();                           
                            }
                            mentesszam++;
                            cellaszam = 6;
                            harmincketto = 1;
                        }
                        //ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                        datum = rs2.getString(2).split(" ");
                        sheet.getRange().get("D" + 2).setText(datum[0]);
                        sheet.getRange().get("J" + 2).setText(rs2.getString(3));
                        sheet.getRange().get("B" + cellaszam).setText(rs2.getString(4));
                        sheet.getRange().get("C" + cellaszam).setText(rs2.getString(5));
                        sheet.getRange().get("D" + cellaszam).setText(rs2.getString(6));
                        sheet.getRange().get("E" + cellaszam).setText(rs2.getString(7));
                        sheet.getRange().get("F" + cellaszam).setText(rs2.getString(8));
                        sheet.getRange().get("G" + cellaszam).setText(rs2.getString(9));
                        sheet.getRange().get("H" + cellaszam).setText(rs2.getString(10));
                        sheet.getRange().get("I" + cellaszam).setText(rs2.getString(11));
                        sheet.getRange().get("J" + cellaszam).setText(rs2.getString(12));
                        sheet.getRange().get("K" + cellaszam).setText(rs2.getString(13));
                        sheet.getRange().get("L" + cellaszam).setText(rs2.getString(14));

                        rs = stmt3.executeQuery("select max(ido)\r\n"
                                + "from videoton.fkov\r\n"
                                + "where\r\n"
                                + "videoton.fkov.hely = \"93\"   and\r\n"
                                + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                + "and poz = 2");
                        if(rs.next())
                        {
                            SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                            Date mikor = hosszu.parse(rs.getString(1));
                            Calendar cal = Calendar.getInstance(); // creates calendar                                        
                            cal.setTime(mikor);               // sets calendar time/date
                            //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                            //cal.getTime();                         // returns new date object plus one hour
                            //System.out.println(sheet.getRange().get("D" + 2).getText());
                            String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                            Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                            //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                            if(ellenorzes.compareTo(mikor) > 0) 
                            {
                                //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                cal.add(Calendar.HOUR_OF_DAY, 600);
                                String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                String szoveg = "Result after " + lejar[0];
                                sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                            else
                            {
                                cal.add(Calendar.HOUR_OF_DAY, 300);
                                String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                String szoveg = "Result after " + lejar[0];
                                sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                        }
                        if(rs2.getString(15) == null) {}
                        else if(rs2.getString(15).equals(""))  {}
                        else
                        {
                            sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs2.getString(15));
                            megjegyzes++;
                            sorszam++;
                        }
                        harmincketto++;
                        cellaszam++;
                    }
                    
                    File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem "+ datum[0]+"_"+ mentesszam +".xlsx");          //\\Techem OQC
                    f.getParentFile().mkdirs(); 
                    f.createNewFile();                
                    String hova = f.getAbsolutePath();
                    workbook.getConverterSetting().setSheetFitToPage(true);
                    workbook.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                    FileInputStream fileStream = new FileInputStream(hova);
                    try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                        {    
                            workbook4.removeSheetAt(i); 
                        }
                        
                        FileOutputStream output = new FileOutputStream(hova);
                        workbook4.write(output);
                        output.close();                           
                    }

                con2.close();
                con3.close();
                
                Process p = Runtime.getRuntime().exec("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\PDF_keszito.jar");               
                System.out.println(p);
                
                JOptionPane.showMessageDialog(null, "Mentve az asztalra", "Info", 1); 
                Foablak.frame.setCursor(null); 
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
    
    class Kereses implements ActionListener                                                                                       //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL letolt = new SQA_SQL();
                if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                {
                    String sql = "select yearweek(datum, 1) as Ev_het, count(Szeriaszam) as Ellenorzott_szeriaszam, sum(if(megjegyzes = '', 0,1)) as Hibas_darab\r\n"
                            + "from qualitydb.Techem_OQC\r\n"
                            + "where datum >= '"+ datumtol2_mezo.getText() +"' and datum <= '"+ datumig2_mezo.getText() +"' and szeriaszam like '4TCH%' \r\n"
                            + "group by yearweek(datum, 1)";
                    letolt.minden_excel(sql, "KPI adatok.xlsx");
                }
                else
                {
                    String sql = "select yearweek(datum, 1) as Ev_het, count(Szeriaszam) as Ellenorzott_szeriaszam, sum(if(megjegyzes = '', 0,1)) as Hibas_darab\r\n"
                            + "from qualitydb.Techem_OQC\r\n"
                            + "where datum >= '"+ datumtol2_mezo.getText() +"' and datum <= '"+ datumig2_mezo.getText() +"' and szeriaszam like 'F003764210%' \r\n"
                            + "group by yearweek(datum, 1)";
                    letolt.minden_excel(sql, "KPI adatok.xlsx");
                }
                
                Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
      
    }
    
    class Lekerdezes2 implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();
                Workbook workbook2 = new Workbook();
                Workbook workbook3 = new Workbook();
                Workbook workbook5 = new Workbook();
                Workbook workbook6 = new Workbook();
                if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                {
                    workbook.loadFromFile(excelfile);
                    workbook3.loadFromFile(excelfile);
                    workbook5.loadFromFile(excelfile);
                }
                else
                {
                    workbook.loadFromFile(excelfile2);
                    workbook3.loadFromFile(excelfile2);
                    workbook5.loadFromFile(excelfile2);
                }      
                workbook6.loadFromFile(excelfile3);
                Worksheet sheet6 = workbook6.getWorksheets().get(0);
                DataTable datatable2 = new DataTable();
                datatable2 = sheet6.exportDataTable(sheet6.getAllocatedRange(), false, false );
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                Worksheet sheet3 = workbook3.getWorksheets().get(0);
                Worksheet sheet5 = workbook5.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();  
                //ResultSet rs2 = null;
                //ResultSet rs3 = null;
                //Connection conn = null;
                //Statement stmt2 = null;        
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
                Connection con2 = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                Statement stmt2 = (Statement) con2.createStatement();
                
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                Connection con3 = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");
                Statement stmt3 = con3.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("Select contract, part_no, location_no, waiv_dev_rej_no, availability_control_id, to_char(RECEIPT_DATE ,'YYYY.MM.DD') from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '"+ String.valueOf(cikk_box.getSelectedItem()) +"' and QTY_ONHAND > 0\r\n"
                        + "and location_no like 'TE0%'");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                sheet2.insertDataTable(datatable, true, 1, 1);
                
                stmt.execute("Select location_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where PART_NO = '"+ String.valueOf(cikk_box.getSelectedItem()) +"' and QTY_ONHAND > 0 and (location_no like 'TE0%' or location_no like 'KE%') \r\n"
                        + " group by location_no");          //TE0%                 and location_no like 'TE0%'
                //int szam = 1;
                
                rs = stmt.getResultSet();
                ArrayList<String> raklapok = new ArrayList<String>();
                ArrayList<String> szeriaszamok = new ArrayList<String>();
                ArrayList<String> meszamok = new ArrayList<String>();
                while(rs.next())
                {
                    raklapok.add(rs.getString(1));
                }
                for(int szamlalo = 0; szamlalo < 1; szamlalo++)      //raklapok.size()
                {
                    stmt.execute("Select waiv_dev_rej_no from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                            + "where location_no = '"+ raklapok.get(szamlalo) +"' and QTY_ONHAND > 0");
                    
                    rs = stmt.getResultSet();
                    int vanilyen = 0;
                    int vanilyen2 = 0;
                    int vanilyen3 = 0;
                    int cellaszam = 6;
                    int cellaszam2 = 6;
                    int cellaszam3 = 6;
                    int megjegyzes = 39;
                    int sorszam = 1;
                    meszamok.clear();
                    szeriaszamok.clear();
                    for(int szamlalo6 = 1; szamlalo6 < datatable2.getRows().size(); szamlalo6++)      //raklapok.size()
                    {
                        meszamok.add(datatable2.getRows().get(szamlalo6).getString(0));
                        System.out.println(datatable2.getRows().get(szamlalo6).getString(0));
                    }
                    
                    int harmincketto = 1;
                    System.out.println("Me mérete "+ meszamok.size());
                    for(int szamlalo3 = 0; szamlalo3 < meszamok.size(); szamlalo3++)
                    {
                        if(meszamok.get(szamlalo3).equals("*")) {}
                        else
                        {
                            ResultSet rs2 = null;
                            if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                            {
                                rs2 = stmt.executeQuery("select tracy_serial_no \r\n"
                                        + "from ifsapp.C_TRACY\r\n"
                                        + "where PACKAGE_ID = '"+ meszamok.get(szamlalo3) +"'");
                            }
                            else
                            {
                                rs2 = stmt.executeQuery("select ALT_TRACY_SERIAL_NO2 \r\n"
                                        + "from ifsapp.C_TRACY\r\n"
                                        + "where PACKAGE_ID = '"+ meszamok.get(szamlalo3) +"'");
                            }
                            //System.out.println("Nézi a raklapokat");
                            szeriaszamok.clear();
                            while(rs2.next())
                            {
                                szeriaszamok.add(rs2.getString(1));
                            }
                            System.out.println("Szériaszámok mérete "+ szeriaszamok.size() );
                            for(int szamlalo2 = 0; szamlalo2 < szeriaszamok.size(); szamlalo2++)
                            {
                                if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                                {
                                    if(harmincketto >= 65) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet5.getRange().get("D" + 2).setText(datum[0]);
                                            sheet5.getRange().get("J" + 2).setText(rs3.getString(3));
                                            sheet5.getRange().get("B" + cellaszam3).setText(rs3.getString(4));
                                            sheet5.getRange().get("C" + cellaszam3).setText(rs3.getString(5));
                                            sheet5.getRange().get("D" + cellaszam3).setText(rs3.getString(6));
                                            sheet5.getRange().get("E" + cellaszam3).setText(rs3.getString(7));
                                            sheet5.getRange().get("F" + cellaszam3).setText(rs3.getString(8));
                                            sheet5.getRange().get("G" + cellaszam3).setText(rs3.getString(9));
                                            sheet5.getRange().get("H" + cellaszam3).setText(rs3.getString(10));
                                            sheet5.getRange().get("I" + cellaszam3).setText(rs3.getString(11));
                                            sheet5.getRange().get("J" + cellaszam3).setText(rs3.getString(12));
                                            sheet5.getRange().get("K" + cellaszam3).setText(rs3.getString(13));
                                            sheet5.getRange().get("L" + cellaszam3).setText(rs3.getString(14));
                                            
                                            System.out.println(harmincketto);
                                            rs = stmt3.executeQuery("select max(ido)\r\n"
                                                    + "from videoton.fkov\r\n"
                                                    + "where\r\n"
                                                    + "videoton.fkov.hely = \"93\"   and\r\n"
                                                    + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                    + "and poz = 2");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                Date mikor = hosszu.parse(rs.getString(1));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("M" + cellaszam3).setText(szoveg);
                                                    sheet5.getCellRange("M" + cellaszam3 +":O"+ cellaszam3).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("M" + cellaszam3).setText(szoveg);
                                                    sheet5.getCellRange("M" + cellaszam3 +":O"+ cellaszam3).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet5.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam3++;
                                            vanilyen3++;
                                        }
                                    }
                                    else if(harmincketto >= 33 && harmincketto < 65) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet3.getRange().get("D" + 2).setText(datum[0]);
                                            sheet3.getRange().get("J" + 2).setText(rs3.getString(3));
                                            sheet3.getRange().get("B" + cellaszam2).setText(rs3.getString(4));
                                            sheet3.getRange().get("C" + cellaszam2).setText(rs3.getString(5));
                                            sheet3.getRange().get("D" + cellaszam2).setText(rs3.getString(6));
                                            sheet3.getRange().get("E" + cellaszam2).setText(rs3.getString(7));
                                            sheet3.getRange().get("F" + cellaszam2).setText(rs3.getString(8));
                                            sheet3.getRange().get("G" + cellaszam2).setText(rs3.getString(9));
                                            sheet3.getRange().get("H" + cellaszam2).setText(rs3.getString(10));
                                            sheet3.getRange().get("I" + cellaszam2).setText(rs3.getString(11));
                                            sheet3.getRange().get("J" + cellaszam2).setText(rs3.getString(12));
                                            sheet3.getRange().get("K" + cellaszam2).setText(rs3.getString(13));
                                            sheet3.getRange().get("L" + cellaszam2).setText(rs3.getString(14));
                                            
                                            System.out.println(harmincketto);
                                            rs = stmt3.executeQuery("select max(ido)\r\n"
                                                    + "from videoton.fkov\r\n"
                                                    + "where\r\n"
                                                    + "videoton.fkov.hely = \"93\"   and\r\n"
                                                    + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                    + "and poz = 2");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                Date mikor = hosszu.parse(rs.getString(1));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("M" + cellaszam2).setText(szoveg);
                                                    sheet3.getCellRange("M" + cellaszam2 +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("M" + cellaszam2).setText(szoveg);
                                                    sheet3.getCellRange("M" + cellaszam2 +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam2++;
                                            vanilyen2++;
                                        }
                                    }
                                    else
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            if(String.valueOf(cikk_box.getSelectedItem()).equals("1742"))
                                            {
                                                String[] datum = rs3.getString(2).split(" ");
                                                sheet.getRange().get("D" + 2).setText(datum[0]);
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
                                                
                                                System.out.println(harmincketto);
                                                rs = stmt3.executeQuery("select max(ido)\r\n"
                                                        + "from videoton.fkov\r\n"
                                                        + "where\r\n"
                                                        + "videoton.fkov.hely = \"93\"   and\r\n"
                                                        + "videoton.fkov.panel like \"4TCH%\" \r\n"
                                                        + "and poz = 2");
                                                if(rs.next())
                                                {
                                                    SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                    Date mikor = hosszu.parse(rs.getString(1));
                                                    Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                    cal.setTime(mikor);               // sets calendar time/date
                                                    //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                    //cal.getTime();                         // returns new date object plus one hour
                                                    //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                    String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                    Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                    //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                    if(ellenorzes.compareTo(mikor) > 0) 
                                                    {
                                                        //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                        cal.add(Calendar.HOUR_OF_DAY, 600);
                                                        String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                        String szoveg = "Result after " + lejar[0];
                                                        sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                                        sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                    }
                                                    else
                                                    {
                                                        cal.add(Calendar.HOUR_OF_DAY, 300);
                                                        String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                        String szoveg = "Result after " + lejar[0];
                                                        sheet.getRange().get("M" + cellaszam).setText(szoveg);
                                                        sheet.getCellRange("M" + cellaszam +":O"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                    }
                                                }
                                                if(rs3.getString(15) == null) {}
                                                else if(rs3.getString(15).equals(""))  {}
                                                else
                                                {
                                                    sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                    megjegyzes++;
                                                    sorszam++;
                                                }
                                                harmincketto++;
                                                cellaszam++;
                                                vanilyen++;
                                            }                                            
                                        }
                                    }
                                }   ///////if vége innen kezdődik a Funk
                                else
                                {
                                    if(harmincketto >= 73) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet5.getRange().get("D" + 2).setText(datum[0]);
                                            sheet5.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet5.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet5.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet5.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet5.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet5.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet5.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet5.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet5.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet5.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet5.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet5.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet5.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                    else if(harmincketto >= 37 && harmincketto < 73) 
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet3.getRange().get("D" + 2).setText(datum[0]);
                                            sheet3.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet3.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet3.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet3.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet3.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet3.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet3.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet3.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet3.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet3.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet3.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet3.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet3.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                    else
                                    {
                                        ResultSet rs3 = stmt2.executeQuery("select * from qualitydb.Techem_OQC where Szeriaszam like '"+ szeriaszamok.get(szamlalo2) +"%'");                        
                                        if(rs3.next())
                                        {
                                            String[] datum = rs3.getString(2).split(" ");
                                            sheet.getRange().get("D" + 2).setText(datum[0]);
                                            sheet.getRange().get("I" + 2).setText(rs3.getString(3));
                                            sheet.getRange().get("B" + cellaszam).setText(rs3.getString(4));
                                            sheet.getRange().get("C" + cellaszam).setText(rs3.getString(5));
                                            sheet.getRange().get("D" + cellaszam).setText(rs3.getString(6));
                                            sheet.getRange().get("E" + cellaszam).setText(rs3.getString(7));
                                            sheet.getRange().get("F" + cellaszam).setText(rs3.getString(8));
                                            sheet.getRange().get("G" + cellaszam).setText(rs3.getString(9));
                                            sheet.getRange().get("H" + cellaszam).setText(rs3.getString(10));                                           
                                            
                                            //System.out.println(harmincketto);
                                            rs = stmt.executeQuery("select max(manuf_date)\r\n"
                                                    + "from ifsapp.C_OPER_TRACY_EXT kamra\r\n"
                                                    + "where 3 = 3 and SCAN_LOC = 'TFFRT'");
                                            if(rs.next())
                                            {
                                                SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd");
                                                String[] ifsdatum = rs.getString(1).split(" ");
                                                Date mikor = hosszu.parse(ifsdatum[0].replace("-", "."));
                                                Calendar cal = Calendar.getInstance(); // creates calendar                                        
                                                cal.setTime(mikor);               // sets calendar time/date
                                                //cal.add(Calendar.HOUR_OF_DAY, 300);      // adds one hour
                                                //cal.getTime();                         // returns new date object plus one hour
                                                //System.out.println(sheet.getRange().get("D" + 2).getText());
                                                String[] pontos = sheet.getRange().get("D" + 2).getText().split("-"); 
                                                Date ellenorzes = hosszu.parse(pontos[0]+"."+pontos[1]+"."+pontos[2]); 
                                                //System.out.println("Tagolt dátum: "+pontos[0]+"."+pontos[1]+"."+pontos[2]);
                                                if(ellenorzes.compareTo(mikor) > 0) 
                                                {
                                                    //System.out.println("Múltban Ellenorzes ideje: "+ ellenorzes + "   Berakva idő: "+ mikor);
                                                    cal.add(Calendar.HOUR_OF_DAY, 600);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                                else
                                                {
                                                    cal.add(Calendar.HOUR_OF_DAY, 300);
                                                    String[] lejar = hosszu.format(cal.getTime()).split(" ");
                                                    String szoveg = "Result after " + lejar[0];
                                                    sheet.getRange().get("I" + cellaszam).setText(szoveg);
                                                    sheet.getCellRange("I" + cellaszam +":K"+ cellaszam).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                                }
                                            }
                                            if(rs3.getString(15) == null) {}
                                            else if(rs3.getString(15).equals(""))  {}
                                            else
                                            {
                                                sheet.getRange().get("D" + megjegyzes).setText(sorszam +" "+rs3.getString(15));
                                                megjegyzes++;
                                                sorszam++;
                                            }
                                            harmincketto++;
                                            cellaszam++;
                                            vanilyen++;                                       
                                        }
                                    }
                                }
                            }       //for vége
                        }
                        
                    }
                    System.out.println("Végzett az ME szám");
                    System.out.println("Cellaszám "+ cellaszam);
                    System.out.println("Vaniylen "+ vanilyen);
                    if(vanilyen > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +".xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook.getConverterSetting().setSheetFitToPage(true);
                        workbook.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                        System.out.println("Mentette az excelt " + raklapok.get(szamlalo) +".xlsx");
                    }
                    if(vanilyen2 > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +"-2.xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook3.getConverterSetting().setSheetFitToPage(true);
                        workbook3.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                    }
                    if(vanilyen3 > 0)
                    {
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Techem OQC_Rhsz_"+ raklapok.get(szamlalo) +"-3.xlsx");          //\\Techem OQC
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();                
                        String hova = f.getAbsolutePath();
                        workbook5.getConverterSetting().setSheetFitToPage(true);
                        workbook5.saveToFile(hova, ExcelVersion.Version2016);                                //ExcelVersion.Version2016
                        FileInputStream fileStream = new FileInputStream(hova);
                        try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                            {    
                                workbook4.removeSheetAt(i); 
                            }
                            
                            FileOutputStream output = new FileOutputStream(hova);
                            workbook4.write(output);
                            output.close();                           
                        }
                    }
                    ///
                    System.out.println("végzett a raklappal");
                }
                               
                sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:AC1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:AC1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                File f = new File(System.getProperty("user.home") + "\\Desktop\\Techem OQC\\Raktárban levő anyag.xlsx");         //Techem OQC\\
                f.getParentFile().mkdirs(); 
                f.createNewFile();
                String hova = f.getAbsolutePath();
                workbook2.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook4 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook4.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook4.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook4.write(output);
                    output.close();
                }

                con.close(); 
                con2.close();
                con3.close();
                
                Process p = Runtime.getRuntime().exec("java -jar \\\\172.20.22.7\\kozos\\Jar-ok\\PDF_keszito.jar");               
                System.out.println(p);
                
                JOptionPane.showMessageDialog(null, "Mentve az asztalra", "Info", 1); 
                Foablak.frame.setCursor(null); 
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
}
