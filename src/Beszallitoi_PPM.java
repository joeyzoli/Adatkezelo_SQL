import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.OrderBy;
import com.spire.xls.SortComparsionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class Beszallitoi_PPM extends JPanel 
{
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    static JTable table;
    private DefaultTableModel modell;
    private Dimension meretek = new Dimension(1800, 650);
    private int ellenorzo = 0;
    private Workbook workbook = new Workbook();
    private Worksheet sheet = workbook.getWorksheets().get(0);

    /**
     * Create the panel.
     */
    public Beszallitoi_PPM() 
    {
        setLayout(null);
        this.setPreferredSize(meretek);
        JLabel lblNewLabel = new JLabel("Beszállító PPM lekérdezés");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(508, 33, 204, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(507, 83, 86, 14);
        add(lblNewLabel_1);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(603, 80, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(505, 119, 72, 14);
        add(lblNewLabel_2);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(603, 116, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JButton szamol_gomb = new JButton("Számol");
        szamol_gomb.addActionListener(new Lekerdezes());
        szamol_gomb.setBounds(544, 157, 89, 23);
        add(szamol_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(20, 204, 1755, 241);
        add(gorgeto);
               
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám","Cikk megnevezés", "Beszállító", "Beérkezve", "Fehasználva", "Zárolt", "Felszabadítva", "PPM", "Osztály", "Csoport", "Típus", "Beszállítói target", "Cikkcsoport target"});
        table.setModel(modell);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(544, 488, 89, 23);
        add(excel_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Megjegyzés:");
        lblNewLabel_3.setBounds(695, 492, 96, 14);
        add(lblNewLabel_3);
        
        JTextArea megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setBounds(784, 487, 275, 102);
        megjegyzes_mezo.setText("A PPM értékeknél a mínusz eredmény azért keletkezik, mert az adott hónapban több db-ot mozgattak ki a raktárhelyről mint amennyit be. Ennek az az oka, hogy előző hónapról volt ott már anyag.");
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        megjegyzes_mezo.setEditable(false);
        add(megjegyzes_mezo);

    }
    
    class Lekerdezes implements ActionListener                                                                                      
    {
        @SuppressWarnings("resource")
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                int cellaszam = 1;
                sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
                sheet.getRange().get("B" + cellaszam).setText("Cikk megnevezés");
                sheet.getRange().get("C" + cellaszam).setText("Beszállító");
                sheet.getRange().get("D" + cellaszam).setText("Beérkezve");
                sheet.getRange().get("E" + cellaszam).setText("Felhasználva");
                sheet.getRange().get("F" + cellaszam).setText("Zárolt");
                sheet.getRange().get("G" + cellaszam).setText("Felszabadítva");
                sheet.getRange().get("H" + cellaszam).setText("PPM");
                sheet.getRange().get("I" + cellaszam).setText("Osztály");
                sheet.getRange().get("J" + cellaszam).setText("Csoport");
                sheet.getRange().get("K" + cellaszam).setText("Típus");
                sheet.getRange().get("L" + cellaszam).setText("Beszállítói target");
                sheet.getRange().get("M" + cellaszam).setText("Cikkcsoport target");
                cellaszam++;
                
                    String[] datumtol = datumtol_mezo.getText().split("\\.");
                    String[] datumig = datumig_mezo.getText().split("\\.");
                    String cikkszam = "";
                    String beszallito = "";
                    int beerkezve = 0;
                    int felhasznalva = 0;
                    int visszakonyvelve = 0;
                    int selejt = 0;
                    float ppm;
                    String osztaly = "";
                    String csoport = "";
                    String tipus = "";
                    ArrayList<String> cikkszamok = new ArrayList<String>();
                    ArrayList<String> megnevezes = new ArrayList<String>();
                    //ArrayList<Object[]> ppmek = new ArrayList<Object[]>();
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                        
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                    Statement stmt = con.createStatement();
                    
                    ResultSet rs = stmt.executeQuery("select PART_NO as Cikkszam,\r\n"
                            + " ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO) as Megnevezes\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"                      
                            + "    and (LOCATION_NO = '80' or LOCATION_NO = '91') group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)");
        
                    while(rs.next())
                    {
                        cikkszamok.add(rs.getString(1));
                        megnevezes.add(rs.getString(2));
                    }
                    for(int szamlalo = 0; szamlalo < cikkszamok.size(); szamlalo++)
                    {
                        rs = stmt.executeQuery("select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO)\r\n"
                                + "    from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                                + "    where 3=3\r\n"
                                + "    and PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {
                            cikkszam = cikkszamok.get(szamlalo);
                            beszallito = rs.getString(1);                      
                        }
                        
                        rs = stmt.executeQuery("select \r\n"
                                + "sum(QUANTITY) as Fogyas\r\n"
                                + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                                + "    where 3=3\r\n"
                                + "    and TRANSACTION_CODE = 'ARRIVAL'\r\n"
                                + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                                + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {            
                            beerkezve = rs.getInt(1);
                        }
                        
                        rs = stmt.executeQuery("select \r\n"
                                + "sum(QUANTITY) as Fogyas\r\n"
                                + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                                + "    where 3=3\r\n"
                                + "    and TRANSACTION_CODE = 'BACFLUSH'\r\n"
                                + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                                + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {            
                            felhasznalva = rs.getInt(1);
                        }
                        
                        rs = stmt.executeQuery("select \r\n"
                                + "sum(QUANTITY) as Fogyas\r\n"
                                + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                                + "    where 3=3\r\n"
                                + "    and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                                + "    and TRANSACTION_CODE = 'INVM-ISS'\r\n"
                                + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                                + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {            
                            visszakonyvelve = rs.getInt(1);
                        }
                        
                        rs = stmt.executeQuery("select \r\n"
                                + "sum(QUANTITY) as Fogyas\r\n"
                                + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                                + "    where 3=3\r\n"
                                + "    and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                                + "    and TRANSACTION_CODE = 'INVM-IN'\r\n"
                                + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                                + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {            
                            selejt = rs.getInt(1);
                        }
                        rs = stmt.executeQuery("select attr_value\r\n"
                                + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                                + "where 3=3\r\n"
                                + "and part_no = '"+ cikkszamok.get(szamlalo) +"'");
                        if(rs.next())
                        {                       
                            osztaly = rs.getString(1);                        
                        }
                        if(rs.next())
                        {                       
                            csoport = rs.getString(1);                        
                        }
                        if(rs.next())
                        {                       
                            tipus = rs.getString(1);                        
                        }
                        selejt = selejt - visszakonyvelve;
                        if(selejt == 0)
                        { 
                            
                        }
                        else
                        { 
                            ppm = (((float)selejt-(float)visszakonyvelve)/((float)felhasznalva+(float)selejt))*(float)1000000;
                            String[] simappm = String.valueOf(ppm).split("\\.");
                            //ppmek.add(new Object[]{cikkszam, megnevezes, beerkezve, felhasznalva, selejt, ppm});
                           
                            
                            if(simappm.length > 1)
                            { 
                                sheet.getRange().get("A" + cellaszam).setText(cikkszam);
                                sheet.getRange().get("B" + cellaszam).setText(megnevezes.get(szamlalo));
                                sheet.getRange().get("C" + cellaszam).setText(beszallito);
                                sheet.getCellRange("D" + cellaszam).setNumberValue(beerkezve);
                                sheet.getCellRange("E" + cellaszam).setNumberValue((felhasznalva + selejt));                       
                                sheet.getCellRange("F" + cellaszam).setNumberValue(selejt);
                                sheet.getCellRange("G" + cellaszam).setNumberValue(visszakonyvelve);
                                sheet.getCellRange("H" + cellaszam).setNumberValue(Integer.valueOf(simappm[0]));
                                sheet.getRange().get("I" + cellaszam).setText(osztaly);
                                sheet.getRange().get("J" + cellaszam).setText(csoport);
                                sheet.getRange().get("K" + cellaszam).setText(tipus);
                            }
                            else
                            { 
                                sheet.getRange().get("A" + cellaszam).setText(cikkszam);
                                sheet.getRange().get("B" + cellaszam).setText(megnevezes.get(szamlalo));
                                sheet.getRange().get("C" + cellaszam).setText(beszallito);
                                sheet.getCellRange("D" + cellaszam).setNumberValue(beerkezve);
                                sheet.getCellRange("E" + cellaszam).setNumberValue(felhasznalva + selejt);                        
                                sheet.getCellRange("F" + cellaszam).setNumberValue(selejt);
                                sheet.getCellRange("G" + cellaszam).setNumberValue(visszakonyvelve);
                                sheet.getCellRange("H" + cellaszam).setNumberValue(ppm);
                                sheet.getRange().get("I" + cellaszam).setText(osztaly);
                                sheet.getRange().get("J" + cellaszam).setText(csoport);
                                sheet.getRange().get("K" + cellaszam).setText(tipus);
                            }
                        }
                        
                        cellaszam++;
                    }                    
                    sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                    sheet.getAllocatedRange().autoFitColumns();
                    sheet.getAllocatedRange().autoFitRows();
                    sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    workbook.getDataSorter().getSortColumns().add(7, SortComparsionType.Values, OrderBy.Descending);
                    workbook.getDataSorter().sort(sheet.getCellRange("A1:I"+ sheet.getLastRow()));
                    sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable();

                    for(int szamlalo = 2; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        modell.addRow(new Object[]{datatable.getRows().get(szamlalo).getString(0), datatable.getRows().get(szamlalo).getString(1), datatable.getRows().get(szamlalo).getString(2),
                                datatable.getRows().get(szamlalo).getString(3), datatable.getRows().get(szamlalo).getString(4), datatable.getRows().get(szamlalo).getString(5),
                                datatable.getRows().get(szamlalo).getString(6), datatable.getRows().get(szamlalo).getString(7), datatable.getRows().get(szamlalo).getString(8),
                                datatable.getRows().get(szamlalo).getString(9), datatable.getRows().get(szamlalo).getString(10)});
                    }
                      
                table.setModel(modell);               
                ellenorzo = 1;               
                con.close();
                Foablak.frame.setCursor(null);                            
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
            }                                 
       }
    }
    
    class Excel implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            try
            {  
            if(ellenorzo == 0)
            {
            int cellaszam = 1;
            sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
            sheet.getRange().get("B" + cellaszam).setText("Cikk megnevezés");
            sheet.getRange().get("C" + cellaszam).setText("Beszállító");
            sheet.getRange().get("D" + cellaszam).setText("Beérkezve");
            sheet.getRange().get("E" + cellaszam).setText("Felhasználva");
            sheet.getRange().get("F" + cellaszam).setText("Zárolt");
            sheet.getRange().get("G" + cellaszam).setText("Felszabadítva");
            sheet.getRange().get("H" + cellaszam).setText("PPM");
            sheet.getRange().get("I" + cellaszam).setText("Osztály");
            sheet.getRange().get("J" + cellaszam).setText("Csoport");
            sheet.getRange().get("K" + cellaszam).setText("Típus");
            sheet.getRange().get("L" + cellaszam).setText("Beszállítói target");
            sheet.getRange().get("M" + cellaszam).setText("Cikkcsoport target");
            cellaszam++;
                                     
                String[] datumtol = datumtol_mezo.getText().split("\\.");
                String[] datumig = datumig_mezo.getText().split("\\.");
                String cikkszam = "";
                String beszallito = "";
                int beerkezve = 0;
                int felhasznalva = 0;
                int visszakonyvelve = 0;
                int selejt = 0;
                float ppm;
                String osztaly = "";
                String csoport = "";
                String tipus = "";
                ArrayList<String> cikkszamok = new ArrayList<String>();
                ArrayList<String> megnevezes = new ArrayList<String>();
                //ArrayList<Object[]> ppmek = new ArrayList<Object[]>();
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                    
                                                      
                Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery("select PART_NO as Cikkszam,\r\n"
                        + " ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO) as Megnevezes\r\n"
                        + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "    where 3=3\r\n"
                        + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"                      
                        + "    and (LOCATION_NO = '80' or LOCATION_NO = '91') group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)");
    
                while(rs.next())
                {
                    cikkszamok.add(rs.getString(1));
                    megnevezes.add(rs.getString(2));
                }
                for(int szamlalo = 0; szamlalo < cikkszamok.size(); szamlalo++)
                {
                    rs = stmt.executeQuery("select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO)\r\n"
                            + "    from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                            + "    where 3=3\r\n"
                            + "    and PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {
                        cikkszam = cikkszamok.get(szamlalo);
                        beszallito = rs.getString(1);                      
                    }
                    
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and TRANSACTION_CODE = 'ARRIVAL'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        beerkezve = rs.getInt(1);
                    }
                    
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and TRANSACTION_CODE = 'BACFLUSH'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        felhasznalva = rs.getInt(1);
                    }
                    
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                            + "    and TRANSACTION_CODE = 'INVM-ISS'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        visszakonyvelve = rs.getInt(1);
                    }
                    
                    rs = stmt.executeQuery("select \r\n"
                            + "sum(QUANTITY) as Fogyas\r\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                            + "    where 3=3\r\n"
                            + "    and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                            + "    and TRANSACTION_CODE = 'INVM-IN'\r\n"
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        selejt = rs.getInt(1);
                    }
                    rs = stmt.executeQuery("select attr_value\r\n"
                            + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                            + "where 3=3\r\n"
                            + "and part_no = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {                       
                        osztaly = rs.getString(1);                        
                    }
                    if(rs.next())
                    {                       
                        csoport = rs.getString(1);                        
                    }
                    if(rs.next())
                    {                       
                        tipus = rs.getString(1);                        
                    }
                    selejt = selejt - visszakonyvelve;
                    if(selejt == 0)
                    { 
                        
                    }
                    else
                    { 
                        ppm = (((float)selejt-(float)visszakonyvelve)/((float)felhasznalva+(float)selejt))*(float)1000000;
                        String[] simappm = String.valueOf(ppm).split("\\.");
                        //ppmek.add(new Object[]{cikkszam, megnevezes, beerkezve, felhasznalva, selejt, ppm});
                       
                        
                        if(simappm.length > 1)
                        { 
                            modell.addRow(new Object[]{cikkszam, megnevezes.get(szamlalo), beszallito, beerkezve, (felhasznalva + selejt), selejt, visszakonyvelve, simappm[0], osztaly, csoport, tipus});
                            sheet.getRange().get("A" + cellaszam).setText(cikkszam);
                            sheet.getRange().get("B" + cellaszam).setText(megnevezes.get(szamlalo));
                            sheet.getRange().get("C" + cellaszam).setText(beszallito);
                            sheet.getCellRange("D" + cellaszam).setNumberValue(beerkezve);
                            sheet.getCellRange("E" + cellaszam).setNumberValue((felhasznalva + selejt));                       
                            sheet.getCellRange("F" + cellaszam).setNumberValue(selejt);
                            sheet.getCellRange("G" + cellaszam).setNumberValue(visszakonyvelve);
                            sheet.getCellRange("H" + cellaszam).setNumberValue(Integer.valueOf(simappm[0]));
                            sheet.getRange().get("I" + cellaszam).setText(osztaly);
                            sheet.getRange().get("J" + cellaszam).setText(csoport);
                            sheet.getRange().get("K" + cellaszam).setText(tipus);
                        }
                        else
                        { 
                            modell.addRow(new Object[]{cikkszam, megnevezes.get(szamlalo), beszallito, beerkezve, (felhasznalva + selejt), selejt, visszakonyvelve, ppm, osztaly, csoport, tipus});
                            sheet.getRange().get("A" + cellaszam).setText(cikkszam);
                            sheet.getRange().get("B" + cellaszam).setText(megnevezes.get(szamlalo));
                            sheet.getRange().get("C" + cellaszam).setText(beszallito);
                            sheet.getCellRange("D" + cellaszam).setNumberValue(beerkezve);
                            sheet.getCellRange("E" + cellaszam).setNumberValue(felhasznalva + selejt);                        
                            sheet.getCellRange("F" + cellaszam).setNumberValue(selejt);
                            sheet.getCellRange("G" + cellaszam).setNumberValue(visszakonyvelve);
                            sheet.getCellRange("H" + cellaszam).setNumberValue(ppm);
                            sheet.getRange().get("I" + cellaszam).setText(osztaly);
                            sheet.getRange().get("J" + cellaszam).setText(csoport);
                            sheet.getRange().get("K" + cellaszam).setText(tipus);
                        }
                    }
                    
                    cellaszam++;
                }
                table.setModel(modell);
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                workbook.getDataSorter().getSortColumns().add(7, SortComparsionType.Values, OrderBy.Descending);
                workbook.getDataSorter().sort(sheet.getCellRange("A1:I"+ sheet.getLastRow()));
                
                String hova = System.getProperty("user.home") + "\\Desktop\\PPM.xlsx";
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
            }
            else
            { 
                String hova = System.getProperty("user.home") + "\\Desktop\\PPM.xlsx";
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
            }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra PPM.xlsx néven!", "Info", 1); 
                  
                Foablak.frame.setCursor(null);          
              
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
        }  
         }    
    }
}
