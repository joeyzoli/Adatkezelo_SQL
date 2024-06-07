import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;

import com.spire.data.table.DataTable;
import com.spire.xls.ExcelVersion;
import com.spire.xls.OrderBy;
import com.spire.xls.SortComparsionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class Beszallitoi_PPM extends JPanel 
{
    static JTable table;
    private DefaultTableModel modell;
    //private Dimension meretek = new Dimension(1800, 650);
    private int ellenorzo = 0;
    private Workbook workbook = new Workbook();
    private Worksheet sheet = workbook.getWorksheets().get(0);
    private JTextField csoport_mezo;
    private JTextField beszallito_mezo;
    private JComboBox<String> csoport_box;
    private JComboBox<String> beszallito_box;
    private SQA_SQL tomb;
    private JXDatePicker datum_tol;
    private JXDatePicker datum_ig;
    
    /**
     * Create the panel.
     */
    public Beszallitoi_PPM() 
    {
        setLayout(null);
        this.setPreferredSize(new Dimension(1826, 912));
        JLabel lblNewLabel = new JLabel("Beszállító PPM lekérdezés");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(508, 33, 204, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(507, 83, 86, 14);
        add(lblNewLabel_1);
        
        datum_tol = new JXDatePicker();
        datum_tol.setBounds(603, 80, 120, 20);
        add(datum_tol);

        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(505, 119, 72, 14);
        add(lblNewLabel_2);
        
        datum_ig = new JXDatePicker();
        datum_ig.setBounds(603, 116, 120, 20);
        add(datum_ig);
        
        JButton szamol_gomb = new JButton("Számol");
        szamol_gomb.addActionListener(new Lekerdezes());
        szamol_gomb.setBounds(544, 157, 89, 23);
        add(szamol_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(20, 204, 1755, 241);
        add(gorgeto);
               
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám","Cikk megnevezés", "Beszállító", "Beérkezve", "Fehasználva", "Zárolt", "Felszabadítva", "PPM", "Beszállítói target", "Cikkcsoport target", "Csoport", "Osztály", "Típus"});
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
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_4 = new JLabel("Csoport target beállítása");
        lblNewLabel_4.setBounds(122, 627, 167, 14);
        add(lblNewLabel_4);
        
        tomb = new SQA_SQL();
        String sql = "select   attr_value\r\n"
                + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                + "where 3=3\r\n"
                + "-- and part_no = '878283-E'\r\n"
                + " and characteristic_code = 'GRP' -- csoport\r\n"
                + "-- and characteristic_code = 'CLASS' -- osztály\r\n"
                + "-- and characteristic_code = 'TYPE' -- típus\r\n"
                + " group by attr_value";
        
        csoport_box = new JComboBox<String>(tomb.tombvissza(sql));                              //tomb.tombvissza(sql)
        csoport_box.setBounds(345, 623, 344, 22);
        add(csoport_box);
        
        JLabel lblNewLabel_5 = new JLabel("Beszállítói target beállítása");
        lblNewLabel_5.setBounds(122, 688, 155, 14);
        add(lblNewLabel_5);  
        
        sql = "select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO),\r\n"
                + "VENDOR_NO\r\n"
                + "from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                + "where 3=3\r\n"
                + "group by VENDOR_NO ORDER by ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO) asc";
        
        beszallito_box = new JComboBox<String>(tomb.tombvissza(sql));                           //tomb.tombvissza(sql)
        beszallito_box.setBounds(345, 684, 344, 22);
        add(beszallito_box);
        
        csoport_mezo = new JTextField();
        csoport_mezo.setBounds(745, 624, 114, 20);
        add(csoport_mezo);
        csoport_mezo.setColumns(10);
 
        beszallito_mezo = new JTextField();
        beszallito_mezo.setBounds(745, 685, 114, 20);
        add(beszallito_mezo);
        beszallito_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("mentés");
        mentes_gomb.addActionListener(new Targer_beallit());
        mentes_gomb.setBounds(544, 753, 89, 23);
        add(mentes_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int rowCount = modell.getRowCount();
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                
                table.setModel(modell);
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
                    
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                    String[] datumtol = dateFormat.format(datum_tol.getDate()).toString().replace(" ", "").split("\\.");
                    String[] datumig = dateFormat.format(datum_ig.getDate()).toString().replace(" ", "").split("\\.");
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
                        rs = stmt.executeQuery("select   attr_value\r\n"
                                + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                                + "where 3=3\r\n"
                                + "and part_no =  '"+ cikkszamok.get(szamlalo) +"'\r\n"
                                + " and characteristic_code = 'GRP'");
                        if(rs.next())
                        {
                            csoport = rs.getString(1);
                        }
                        
                        rs = stmt.executeQuery("select   attr_value\r\n"
                                + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                                + "where 3=3\r\n"
                                + "and part_no =  '"+ cikkszamok.get(szamlalo) +"'\r\n"
                                + " and characteristic_code = 'CLASS'");
                        if(rs.next())
                        {
                            osztaly = rs.getString(1);
                        }
                        
                        rs = stmt.executeQuery("select   attr_value\r\n"
                                + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                                + "where 3=3\r\n"
                                + "and part_no =  '"+ cikkszamok.get(szamlalo) +"'\r\n"
                                + " and characteristic_code = 'TYPE'");
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
                                sheet.getRange().get("J" + cellaszam).setText(osztaly);
                                sheet.getRange().get("I" + cellaszam).setText(csoport);
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
                                sheet.getRange().get("J" + cellaszam).setText(osztaly);
                                sheet.getRange().get("I" + cellaszam).setText(csoport);
                                sheet.getRange().get("K" + cellaszam).setText(tipus);
                            }
                        }
                        osztaly = "";
                        csoport = "";
                        tipus = "";
                        
                        cellaszam++;
                    }                    
                    sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                    sheet.getAllocatedRange().autoFitColumns();
                    sheet.getAllocatedRange().autoFitRows();
                    sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    workbook.getDataSorter().getSortColumns().add(7, SortComparsionType.Values, OrderBy.Descending);
                    workbook.getDataSorter().sort(sheet.getCellRange("A1:K"+ sheet.getLastRow()));
                    sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable();
                    
                    SQA_SQL leker = new SQA_SQL();
                    String beszall_target = "";
                    String csop_target = "";
                    for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        if(leker.tombvissza_sajat("SELECT * FROM qualitydb.Beszallitoi_target where Beszallito = '"+ datatable.getRows().get(szamlalo).getString(2) +"'").length > 0)
                        {
                            beszall_target = leker.tombvissza_sajat("SELECT Target FROM qualitydb.Beszallitoi_target where Beszallito = '"+ datatable.getRows().get(szamlalo).getString(2) +"'")[0];
                        }
                        else
                        {
                            beszall_target = "";
                        }
                        
                        if(leker.tombvissza_sajat("SELECT * FROM qualitydb.Beszallitoi_target where Beszallito = '"+ datatable.getRows().get(szamlalo).getString(8) +"'").length > 0)
                        {
                            csop_target = leker.tombvissza_sajat("SELECT Target FROM qualitydb.Beszallitoi_target where Beszallito = '"+ datatable.getRows().get(szamlalo).getString(8) +"'")[0];
                        }
                        else
                        {
                            csop_target = "";
                        }
                        
                        modell.addRow(new Object[]{datatable.getRows().get(szamlalo).getString(0), datatable.getRows().get(szamlalo).getString(1), datatable.getRows().get(szamlalo).getString(2),
                                datatable.getRows().get(szamlalo).getString(3), datatable.getRows().get(szamlalo).getString(4), datatable.getRows().get(szamlalo).getString(5),
                                datatable.getRows().get(szamlalo).getString(6), datatable.getRows().get(szamlalo).getString(7), 
                                beszall_target,csop_target,
                                datatable.getRows().get(szamlalo).getString(8),
                                datatable.getRows().get(szamlalo).getString(9), datatable.getRows().get(szamlalo).getString(10)});
                    }
                      
                table.setModel(modell);
                
                RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modell);

                table.setRowSorter(sorter);   
                table.getColumnModel().getColumn(7).setCellRenderer(new StatusColumnCellRenderer());
                ellenorzo = 1;               
                con.close();
                Foablak.frame.setCursor(null);                            
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                String hibauzenet = e.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            }                                 
       }
    }
    

    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

          //Cells are by default rendered as a JLabel.
          JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

          //Get the status for the current row.
          if (Integer.valueOf(table.getValueAt(row, 7).toString()) > Integer.valueOf(table.getValueAt(row, 9).toString())) 
          {
              l.setBackground(Color.RED);
              
          } 
          else 
          {             
              l.setBackground(Color.GREEN);
          }
          if(table.getValueAt(row, 8).toString().equals("")) {}
          else
          {
              if (Integer.valueOf(table.getValueAt(row, 7).toString()) > Integer.valueOf(table.getValueAt(row, 8).toString())) 
              {
                  l.setBackground(Color.RED);
                  
              } 
              else 
              {             
                  l.setBackground(Color.GREEN);
              }
          }

        //Return the JLabel which renders the cell.
        return l;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");                     
            String[] datumtol = dateFormat.format(datum_tol.getDate()).split("\\.");
            String[] datumig = dateFormat.format(datum_ig.getDate()).split("\\.");
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
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
        }  
         }    
    }
    
    class Targer_beallit implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL targer_ment = new SQA_SQL();
                String sql = "SELECT * FROM qualitydb.Beszallitoi_target where Beszallito = '"+ csoport_box.getSelectedItem() +"'";
                
                targer_ment.tombvissza_sajat(sql);
                if(csoport_mezo.getText().equals("")) {}
                else
                {
                    if(targer_ment.tombvissza_sajat(sql).length > 0)
                    {
                        sql = "UPDATE qualitydb.Beszallitoi_target set Target = '"+ csoport_mezo.getText() +"' WHERE Beszallito = '"+ csoport_box.getSelectedItem() +"'";
                        targer_ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "INSERT INTO qualitydb.Beszallitoi_target (Beszallito, Target) VALUES('"+ csoport_box.getSelectedItem() +"', '"+ csoport_mezo.getText() +"')";
                        targer_ment.mindenes(sql);
                    }
                }
                if(beszallito_mezo.getText().equals("")) {}
                else
                {
                    sql = "SELECT * FROM qualitydb.Beszallitoi_target where Beszallito = '"+ beszallito_box.getSelectedItem() +"'";
                    if(targer_ment.tombvissza_sajat(sql).length > 0)
                    {
                        sql = "UPDATE qualitydb.Beszallitoi_target set Target = '"+ beszallito_mezo.getText() +"' WHERE Beszallito = '"+ beszallito_box.getSelectedItem() +"'";
                        targer_ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "INSERT INTO qualitydb.Beszallitoi_target (Beszallito, Target) VALUES('"+ beszallito_box.getSelectedItem() +"', '"+ beszallito_mezo.getText() +"')";
                        targer_ment.mindenes(sql);
                    }
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();;
                Foablak.frame.setCursor(null);
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
