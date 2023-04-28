import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import javax.swing.JButton;

public class Monitoring extends JPanel 
{
    private JTable table;
    private DefaultTableModel modell;

    /**
     * Create the panel.
     */
    public Monitoring() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Napi OQC mozgások monitorozása");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBounds(372, 28, 281, 28);
        add(lblNewLabel);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(33, 202, 1048, 238);
        add(gorgeto);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes());
        start_gomb.setBounds(457, 114, 89, 23);
        add(start_gomb);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Megnevezés", "OQC-BE összesen", "OQC-BE ma", "OQC-BE tegnap", "OQC-BE régebben", "OQC-KI"});
        table.setModel(modell);

    }
    
    class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                             
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date date = new Date();
                String[] datum = formatter.format(date).split("\\.");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery("select\r\n"
                        + "    PART_NO, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO), \r\n"
                        + "    sum(QUANTITY)\r\n"
                        + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "    where 3 = 3\r\n"
                        + "    and DATE_CREATED between to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) and to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) ) \r\n"
                        + "    and LOCATION_NO = 'OQC-KI'\r\n"
                        + "    group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO), location_no order by part_no asc");
                
                DataTable datatable = new DataTable();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                //table.setModel(SQL.buildTableModel(rs));
                
                rs = stmt.executeQuery("select part_no,\r\n"
                        + "ifsapp.INVENTORY_PART_API.Get_Description(contract,part_no),\r\n"                        
                        + "sum (case when (TO_DATE(SYSDATE, 'YYYY-MM-DD') -  \r\n"
                        + "\r\n"
                        + "       TO_DATE(RECEIPT_DATE, 'YYYY-MM-DD') = 0) then QTY_ONHAND else 0 end) Ma,\r\n"
                        + "sum (case when (TO_DATE(SYSDATE, 'YYYY-MM-DD') -  \r\n"
                        + "\r\n"
                        + "       TO_DATE(RECEIPT_DATE, 'YYYY-MM-DD') = 1) then QTY_ONHAND else 0 end) Tegnap,\r\n"
                        + "sum (case when (TO_DATE(SYSDATE, 'YYYY-MM-DD') -  \r\n"
                        + "\r\n"
                        + "       TO_DATE(RECEIPT_DATE, 'YYYY-MM-DD') > 1) then QTY_ONHAND else 0 end) Regebben\r\n"
                        + "from ifsapp.INVENTORY_PART_IN_STOCK_UIV\r\n"
                        + "where 3 = 3\r\n"
                        + "and QTY_ONHAND > 0\r\n"
                        + "and LOCATION_NO = 'OQC-BE'\r\n"
                        + "group by part_no, ifsapp.INVENTORY_PART_API.Get_Description(contract,part_no), RECEIPT_DATE order by part_no asc");
                
                DataTable datatable2 = new DataTable();
                JdbcAdapter jdbcAdapter2 = new JdbcAdapter();
                jdbcAdapter2.fillDataTable(datatable2, rs);
                int szam = 0;                
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(0).equals(datatable2.getRows().get(szamlalo2).getString(0)))
                        {
                            int osszesen = Integer.parseInt(datatable2.getRows().get(szamlalo2).getString(2)) + Integer.parseInt(datatable2.getRows().get(szamlalo2).getString(3)) + Integer.parseInt(datatable2.getRows().get(szamlalo2).getString(4));
                            modell.addRow(new Object[]{datatable2.getRows().get(szamlalo2).getString(0),datatable2.getRows().get(szamlalo2).getString(1), osszesen, datatable2.getRows().get(szamlalo2).getString(2), 
                                    datatable2.getRows().get(szamlalo2).getString(3), datatable2.getRows().get(szamlalo2).getString(4), datatable.getRows().get(szamlalo).getString(2)});
                            szam = 1;
                        }                        
                    }
                    if(szam == 1){}
                    else
                    {
                        modell.addRow(new Object[]{datatable.getRows().get(szamlalo).getString(0),datatable.getRows().get(szamlalo).getString(1), 0, 0,0,0, datatable.getRows().get(szamlalo).getString(2)});
                    }
                    szam = 0;
                }
                
                
                for(int szamlalo = 0; szamlalo < datatable2.getRows().size(); szamlalo++)
                {
                    for(int szamlalo2 = 0; szamlalo2 < datatable.getRows().size(); szamlalo2++)
                    {
                        if(datatable2.getRows().get(szamlalo).getString(0).equals(datatable.getRows().get(szamlalo2).getString(0)))
                        {                           
                            szam = 1;
                        }                        
                    }
                    if(szam == 0)
                    {
                        int osszesen = Integer.parseInt(datatable2.getRows().get(szamlalo).getString(2)) + Integer.parseInt(datatable2.getRows().get(szamlalo).getString(3)) + Integer.parseInt(datatable2.getRows().get(szamlalo).getString(4));
                        modell.addRow(new Object[]{datatable2.getRows().get(szamlalo).getString(0),datatable2.getRows().get(szamlalo).getString(1), osszesen, datatable2.getRows().get(szamlalo).getString(2),
                                datatable2.getRows().get(szamlalo).getString(3), datatable2.getRows().get(szamlalo).getString(4)});
                    }                   
                    szam = 0;
                }
                table.setModel(modell);
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
