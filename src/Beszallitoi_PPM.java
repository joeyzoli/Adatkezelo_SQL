import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JTable;

public class Beszallitoi_PPM extends JPanel 
{
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    static JTable table;
    private DefaultTableModel modell;

    /**
     * Create the panel.
     */
    public Beszallitoi_PPM() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Beszállító PPM lekérdezés");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(304, 43, 204, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(255, 83, 86, 14);
        add(lblNewLabel_1);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(385, 80, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(255, 119, 72, 14);
        add(lblNewLabel_2);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(385, 116, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JButton szamol_gomb = new JButton("Számol");
        szamol_gomb.addActionListener(new Lekerdezes());
        szamol_gomb.setBounds(322, 170, 89, 23);
        add(szamol_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(56, 204, 699, 241);
        add(gorgeto);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Beszállító", "Beérkezve", "Fehasználva", "Selejt", "PPM"});
        table.setModel(modell);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.setBounds(322, 485, 89, 23);
        add(excel_gomb);

    }
    
    class Lekerdezes implements ActionListener                                                                                      
    {
        @SuppressWarnings("resource")
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String[] datumtol = datumtol_mezo.getText().split("\\.");
                String[] datumig = datumig_mezo.getText().split("\\.");
                String cikkszam = "";
                String megnevezes = "";
                int beerkezve = 0;
                int felhasznalva = 0;
                int selejt = 0;
                float ppm;
                ArrayList<String> cikkszamok = new ArrayList<String>();
                ArrayList<Object[]> ppmek = new ArrayList<Object[]>();
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
                        megnevezes = rs.getString(1);                      
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
                            + "    and DATE_CREATED between to_date( '"+datumtol[0]+ datumtol[1]+ datumtol[2] +"000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '"+ datumig[0]+datumig[1]+datumig[2]+ "235959', 'YYYYMMDDHH24:MI:SS' )\r\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"'");
                    if(rs.next())
                    {            
                        selejt = rs.getInt(1);
                    }
                    ppm = ((float)selejt/(float)felhasznalva)*(float)1000000; 
                    ppmek.add(new Object[]{cikkszam, megnevezes, beerkezve, felhasznalva, selejt, ppm});
                    modell.addRow(new Object[]{cikkszam, megnevezes, beerkezve, felhasznalva, selejt, ppm});                   
                }
                table.setModel(modell);
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
                table.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
                sorter.sort();    

                con.close();  
                Foablak.frame.setCursor(null);          
                  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
            }  
                               
         }
    }
    
    class Excel implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Workbook workbook = new Workbook();
            Worksheet sheet = workbook.getWorksheets().get(0);
            int cellaszam = 1;
            sheet.getRange().get("A" + cellaszam).setText("Szériaszám");
         }
    }
}
