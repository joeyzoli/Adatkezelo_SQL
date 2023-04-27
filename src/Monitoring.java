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
        gorgeto.setBounds(156, 202, 761, 238);
        add(gorgeto);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Lekerdezes());
        start_gomb.setBounds(457, 114, 89, 23);
        add(start_gomb);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Cikkszám", "Megnevezés", "OQC-BE", "OQC-KI"});
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
                
                ResultSet rs = stmt.executeQuery("select PART_NO,\r\n"
                        + "ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO),\r\n"
                        + "sum(QUANTITY)\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "where 3 = 3\r\n"
                        + "and DATE_CREATED between to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) and to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) ) \r\n"
                        + "and LOCATION_NO = 'OQC-BE'\r\n"
                        + "group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO) order by part_no asc");
                
                DataTable datatable = new DataTable();
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable, rs);
                //table.setModel(SQL.buildTableModel(rs));
                
                rs = stmt.executeQuery("select\r\n"
                        + "    PART_NO,\r\n"
                        + "    sum(QUANTITY)\r\n"
                        + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "    where 3 = 3\r\n"
                        + "    and DATE_CREATED between to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) and to_date( '"+ datum[0]+datum[1]+datum[2] +"', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) ) \r\n"
                        + "    and LOCATION_NO = 'OQC-KI'\r\n"
                        + "    group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO), location_no order by part_no asc");
                
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
                            int oqcbe = Integer.parseInt(datatable.getRows().get(szamlalo).getString(2)) - Integer.parseInt(datatable2.getRows().get(szamlalo2).getString(1));
                            modell.addRow(new Object[]{datatable.getRows().get(szamlalo).getString(0),datatable.getRows().get(szamlalo).getString(1), oqcbe,datatable2.getRows().get(szamlalo2).getString(1)});
                            szam = 1;
                        }                        
                    }
                    if(szam == 1){}
                    else
                    {
                        modell.addRow(new Object[]{datatable.getRows().get(szamlalo).getString(0),datatable.getRows().get(szamlalo).getString(1),datatable.getRows().get(szamlalo).getString(2), 0 });
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
