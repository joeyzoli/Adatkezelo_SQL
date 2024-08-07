import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class OQC_tesztadatok extends JPanel {
    private JTable table;
    private JTable table2;
    private DefaultTableModel datumszerint;
    private DefaultTableModel nevszerint;
    private JDatePickerImpl datum;
    private UtilDateModel model;
    private JComboBox<String> ellenor_box;
    private JScrollPane gorgeto;
    

    /**
     * Create the panel.
     */
    public OQC_tesztadatok() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("OQC tesztelési adatok");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(612, 27, 202, 14);
        add(lblNewLabel);
        
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datum = new JDatePickerImpl(datePanel, new DateLabelFormatter());        
        datum.setBounds(188, 95, 110, 20);
        datum.addActionListener(new Datumszerint());
        add(datum);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(103, 98, 64, 14);
        add(lblNewLabel_1);
        
        String sql = "SElect nevek.nev\r\n"
                + "from\r\n"
                + "(select OQC_FB7530.Tesztelo as nev\r\n"
                + "from  qualitydb.OQC_FB7530\r\n"
                + "where 3 = 3\r\n"
                + "union\r\n"
                + "select OQC_FR2400.Tesztelo as nev\r\n"
                + "from  qualitydb.OQC_FR2400\r\n"
                + "where 3 = 3\r\n"
                + "union\r\n"
                + "select OQC_FR1200.Tesztelo as nev\r\n"
                + "from  qualitydb.OQC_FR1200\r\n"
                + "where 3 = 3\r\n"
                + "union\r\n"
                + "select OQC_FR600.Tesztelo as nev\r\n"
                + "from  qualitydb.OQC_FR600\r\n"
                + "where 3 = 3\r\n"
                + "union\r\n"
                + "select OQC_FD302.Tesztelo as nev\r\n"
                + "from  qualitydb.OQC_FD302\r\n"
                + "where 3 = 3) nevek\r\n"
                + "order by nevek.nev asc";      //"select Nev from qualitydb.Alapadatok_ellenorok where 3 = 3 order by nev asc"
        SQA_SQL nevek = new SQA_SQL();
        ellenor_box = new JComboBox<String>(nevek.tombvissza_sajat(sql));
        ellenor_box.addActionListener(new Ellenorszerint());
        ellenor_box.setBounds(443, 94, 262, 22);
        add(ellenor_box);
        
        JLabel lblNewLabel_2 = new JLabel("Tesztelő");
        lblNewLabel_2.setBounds(358, 98, 75, 14);
        add(lblNewLabel_2);
        
        datumszerint = new DefaultTableModel();
        nevszerint = new DefaultTableModel();
        datumszerint.setColumnIdentifiers(new Object[]{"Műszak","Tesztelő", "FB7530","FR2400","FR1200","FR600","FD302"});
        nevszerint.setColumnIdentifiers(new Object[]{"Dátum", "Név","FB7530","FR2400","FR1200","FR600","FD302"});
        table = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };
        table.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent me) {
                  if (me.getClickCount() == 2) {     // to detect doble click events
                     JTable target = (JTable)me.getSource();
                     int row2 = target.getSelectedRow(); // select a row 
                     String nev = table.getValueAt(row2, 1).toString();
                     try
                     {
                         JFrame frame = new JFrame();
                         //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                         //frame.setPreferredSize(new Dimension(800, 600));
                         frame.setSize(1100, 600);
                         JPanel panel = new JPanel();
                         panel.setSize(new Dimension(1000, 550));
                         panel.setLayout(null);
                         DefaultTableModel modell3 = new DefaultTableModel();
                         modell3.setColumnIdentifiers(new Object[]{"Név","Típus", "Dátum","Raklapszám","Szériaszám doboz","TEszt idő"});
                         JTable table2 = new JTable();
                         table2.setModel(modell3);
                         JScrollPane gorgeto3 = new JScrollPane(table2);
                         gorgeto3.setBounds(10, 10, 900, 400);
                         
                         DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                         Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                             
                         Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                         Statement stmt = con.createStatement();
                         String sql = "select OQC_FB7530.Tesztelo as nev,OQC_FB7530.Tipus,OQC_FB7530.Datum,OQC_FB7530.Raklapszam,OQC_FB7530.Szeriaszam_doboz,OQC_FB7530.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FB7530\r\n"
                                 + "where OQC_FB7530.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR2400.Tesztelo as nev,OQC_FR2400.Tipus,OQC_FR2400.Datum,OQC_FR2400.Raklapszam,OQC_FR2400.Szeriaszam_doboz,OQC_FR2400.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR2400\r\n"
                                 + "where OQC_FR2400.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR1200.Tesztelo as nev,OQC_FR1200.Tipus,OQC_FR1200.Datum,OQC_FR1200.Raklapszam,OQC_FR1200.Szeriaszam_doboz,OQC_FR1200.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR1200\r\n"
                                 + "where OQC_FR1200.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR600.Tesztelo as nev,OQC_FR600.Tipus,OQC_FR600.Datum,OQC_FR600.Raklapszam,OQC_FR600.Szeriaszam_doboz,OQC_FR600.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR600\r\n"
                                 + "where OQC_FR600.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FD302.Tesztelo as nev,OQC_FD302.Tipus,OQC_FD302.Datum,OQC_FD302.Raklapszam,OQC_FD302.Szeriaszam_doboz,OQC_FD302.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FD302\r\n"
                                 + "where OQC_FD302.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'";
                         stmt.execute(sql);
                         ResultSet rs = stmt.getResultSet();
                         while(rs.next())
                         {
                             modell3.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                         }
                         TableColumnModel columnModel = table2.getColumnModel();
                         for (int column = 0; column < table2.getColumnCount(); column++) {
                             int width = 15; // Min width
                             for (int row = 0; row < table2.getRowCount(); row++) {
                                 TableCellRenderer renderer = table2.getCellRenderer(row, column);
                                 Component comp = table2.prepareRenderer(renderer, row, column);
                                 width = Math.max(comp.getPreferredSize().width +1 , width);
                             }
                             if(width > 300)
                                 width=300;
                             columnModel.getColumn(column).setPreferredWidth(width);
                         }
                         table2.setModel(modell3);
                         
                         con.close();  
                         //JOptionPane.showMessageDialog(null, panel);
                         panel.add(gorgeto3);
                         //frame.add(panel);                        
                         frame.setContentPane(panel);
                         frame.setVisible(true);
                     }
                     catch (Exception e1) 
                     {              
                         e1.printStackTrace();
                         String hibauzenet = e1.toString();
                         Email hibakuldes = new Email();
                         hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                         JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                     }
                  }    
               }
            });
        gorgeto = new JScrollPane(table);
        gorgeto.setBounds(103, 171, 1050, 376);
        add(gorgeto);
        
        table2 = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };
        table2.addMouseListener(new MouseAdapter() {
              public void mouseClicked(MouseEvent me) {
                  if (me.getClickCount() == 2) {     // to detect doble click events
                     JTable target = (JTable)me.getSource();
                     int row2 = target.getSelectedRow(); // select a row 
                     String nev = table2.getValueAt(row2, 1).toString();
                     String datum = table2.getValueAt(row2, 0).toString();
                     try
                     {
                         JFrame frame = new JFrame();
                         //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                         //frame.setPreferredSize(new Dimension(800, 600));
                         frame.setSize(1100, 600);
                         JPanel panel = new JPanel();
                         panel.setSize(new Dimension(1000, 550));
                         panel.setLayout(null);
                         DefaultTableModel modell3 = new DefaultTableModel();
                         modell3.setColumnIdentifiers(new Object[]{"Név","Típus", "Dátum","Raklapszám","Szériaszám doboz","TEszt idő"});
                         JTable table3 = new JTable();
                         table3.setModel(modell3);
                         JScrollPane gorgeto3 = new JScrollPane(table3);
                         gorgeto3.setBounds(10, 10, 900, 400);
                         
                         DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                         Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                             
                         Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                         Statement stmt = con.createStatement();
                         String sql = "select OQC_FB7530.Tesztelo as nev,OQC_FB7530.Tipus,OQC_FB7530.Datum,OQC_FB7530.Raklapszam,OQC_FB7530.Szeriaszam_doboz,OQC_FB7530.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FB7530\r\n"
                                 + "where OQC_FB7530.Datum = '"+ datum +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR2400.Tesztelo as nev,OQC_FR2400.Tipus,OQC_FR2400.Datum,OQC_FR2400.Raklapszam,OQC_FR2400.Szeriaszam_doboz,OQC_FR2400.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR2400\r\n"
                                 + "where OQC_FR2400.Datum = '"+ datum +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR1200.Tesztelo as nev,OQC_FR1200.Tipus,OQC_FR1200.Datum,OQC_FR1200.Raklapszam,OQC_FR1200.Szeriaszam_doboz,OQC_FR1200.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR1200\r\n"
                                 + "where OQC_FR1200.Datum = '"+ datum +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FR600.Tesztelo as nev,OQC_FR600.Tipus,OQC_FR600.Datum,OQC_FR600.Raklapszam,OQC_FR600.Szeriaszam_doboz,OQC_FR600.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FR600\r\n"
                                 + "where OQC_FR600.Datum = '"+ datum +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'\r\n"
                                 + "union\r\n"
                                 + "select OQC_FD302.Tesztelo as nev,OQC_FD302.Tipus,OQC_FD302.Datum,OQC_FD302.Raklapszam,OQC_FD302.Szeriaszam_doboz,OQC_FD302.Teszt_ido\r\n"
                                 + "from  qualitydb.OQC_FD302\r\n"
                                 + "where OQC_FD302.Datum = '"+ datum +"'\r\n"
                                 + "and Tesztelo = '"+ nev +"'";
                         stmt.execute(sql);
                         ResultSet rs = stmt.getResultSet();
                         while(rs.next())
                         {
                             modell3.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                         }
                         TableColumnModel columnModel = table3.getColumnModel();
                         for (int column = 0; column < table3.getColumnCount(); column++) {
                             int width = 15; // Min width
                             for (int row = 0; row < table3.getRowCount(); row++) {
                                 TableCellRenderer renderer = table3.getCellRenderer(row, column);
                                 Component comp = table3.prepareRenderer(renderer, row, column);
                                 width = Math.max(comp.getPreferredSize().width +1 , width);
                             }
                             if(width > 300)
                                 width=300;
                             columnModel.getColumn(column).setPreferredWidth(width);
                         }
                         table3.setModel(modell3);
                         
                         con.close();  
                         //JOptionPane.showMessageDialog(null, panel);
                         panel.add(gorgeto3);
                         //frame.add(panel);                        
                         frame.setContentPane(panel);
                         frame.setVisible(true);
                     }
                     catch (Exception e1) 
                     {              
                         e1.printStackTrace();
                         String hibauzenet = e1.toString();
                         Email hibakuldes = new Email();
                         hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                         JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                     }
                  }    
               }
            });

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
            return "";
        }

    }
    
    
    class Datumszerint implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {            
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int rowCount = datumszerint.getRowCount();           
                for (int i = rowCount - 1; i > -1; i--) 
                {
                    datumszerint.removeRow(i);
                }
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String sql = "select nevek.nev, FB7530.db as FB7530, FR2400.db as FR2400, FR1200.db as FR1200, FR600.db as FR600, FD302.db as FD302\r\n"
                        + "from\r\n"
                        + "(select OQC_FB7530.Tesztelo as nev\r\n"
                        + "from  qualitydb.OQC_FB7530\r\n"
                        + "where OQC_FB7530.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR2400.Tesztelo as nev\r\n"
                        + "from  qualitydb.OQC_FR2400\r\n"
                        + "where OQC_FR2400.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR1200.Tesztelo as nev\r\n"
                        + "from  qualitydb.OQC_FR1200\r\n"
                        + "where OQC_FR1200.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR600.Tesztelo as nev\r\n"
                        + "from  qualitydb.OQC_FR600\r\n"
                        + "where OQC_FR600.Datum = '"+ datum.getJFormattedTextField().getText() +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FD302.Tesztelo as nev\r\n"
                        + "from  qualitydb.OQC_FD302\r\n"
                        + "where OQC_FD302.Datum = '"+ datum.getJFormattedTextField().getText() +"') nevek\r\n"
                        + "left join (select OQC_FB7530.Tesztelo as nev, count(OQC_FB7530.Tesztelo) db\r\n"
                        + "from  qualitydb.OQC_FB7530\r\n"
                        + "where OQC_FB7530.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                        + "                OQC_FB7530.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) group by nev) FB7530 on FB7530.nev = nevek.nev\r\n"
                        + "left join (select OQC_FR2400.Tesztelo as nev, count(OQC_FR2400.Tesztelo) db\r\n"
                        + "from  qualitydb.OQC_FR2400\r\n"
                        + "where OQC_FR2400.Rogzites_ido  >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                        + "                OQC_FR2400.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) group by nev) FR2400 on FR2400.nev = nevek.nev\r\n"
                        + "left join (select OQC_FR1200.Tesztelo as nev, count(OQC_FR1200.Tesztelo) db\r\n"
                        + "from  qualitydb.OQC_FR1200\r\n"
                        + "where OQC_FR1200.Rogzites_ido  >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                        + "                OQC_FR1200.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) group by nev) FR1200 on FR1200.nev = nevek.nev\r\n"
                        + "left join (select OQC_FR600.Tesztelo as nev, count(OQC_FR600.Tesztelo) db\r\n"
                        + "from  qualitydb.OQC_FR600\r\n"
                        + "where OQC_FR600.Rogzites_ido  >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                        + "                OQC_FR600.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) group by nev) FR600 on FR600.nev = nevek.nev\r\n"
                        + "left join (select OQC_FD302.Tesztelo as nev, count(OQC_FD302.Tesztelo) db\r\n"
                        + "from  qualitydb.OQC_FD302\r\n"
                        + "where OQC_FD302.Rogzites_ido  >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                        + "  OQC_FD302.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) group by nev) FD302 on FD302.nev = nevek.nev\r\n";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                SQA_SQL mikor = new SQA_SQL();
                String rogzites = "";
                while(rs.next())
                {
                    if(rs.getString(2) != null)
                    {
                        sql = "select rogzites_ido from qualitydb.OQC_FB7530 where OQC_FB7530.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                                + " OQC_FB7530.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) and tesztelo = '"+ rs.getString(1) +"' order by OQC_FB7530.Rogzites_ido desc limit 1";
                        if(mikor.tombvissza_sajat(sql).length > 0)
                        {
                            rogzites = mikor.tombvissza_sajat(sql)[0];
                        }
                    }
                    else if(rs.getString(3) != null)
                    {
                        sql = "select rogzites_ido from qualitydb.OQC_FR2400 where OQC_FR2400.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                                + "   OQC_FR2400.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) and tesztelo = '"+ rs.getString(1) +"' order by OQC_FR2400.Rogzites_ido desc limit 1";
                        if(mikor.tombvissza_sajat(sql).length > 0)
                        {
                            rogzites = mikor.tombvissza_sajat(sql)[0];
                        }
                    }
                    else if(rs.getString(4) != null)
                    {
                        sql = "select rogzites_ido from qualitydb.OQC_FR1200 where OQC_FR1200.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                                + "  OQC_FR1200.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) and tesztelo = '"+ rs.getString(1) +"' order by OQC_FR1200.Rogzites_ido desc limit 1";
                        if(mikor.tombvissza_sajat(sql).length > 0)
                        {
                            rogzites = mikor.tombvissza_sajat(sql)[0];
                        }
                    }
                    else if(rs.getString(5) != null)
                    {
                        sql = "select rogzites_ido from qualitydb.OQC_FR600 where OQC_FR600.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                                + " OQC_FR600.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) and tesztelo = '"+ rs.getString(1) +"' order by OQC_FR600.Rogzites_ido desc limit 1";
                        if(mikor.tombvissza_sajat(sql).length > 0)
                        {
                            rogzites = mikor.tombvissza_sajat(sql)[0];
                        }
                    }
                    else if(rs.getString(6) != null)
                    {
                        sql = "select rogzites_ido from qualitydb.OQC_FD302 where OQC_FD302.Rogzites_ido >= replace(concat('"+ datum.getJFormattedTextField().getText() +"', ' ', '05:55:00'), '-', '.') and "
                                + "  OQC_FD302.Rogzites_ido < replace(concat(date_add('"+ datum.getJFormattedTextField().getText() +"', interval 1 day), ' ', '05:55:00 '), '-', '.' ) and tesztelo = '"+ rs.getString(1) +"' order by OQC_FD302.Rogzites_ido desc limit 1";
                        if(mikor.tombvissza_sajat(sql).length > 0)
                        {
                            rogzites = mikor.tombvissza_sajat(sql)[0];
                        }
                    }
                    datumszerint.addRow(new Object[]{muszak(datum.getJFormattedTextField().getText(), rogzites),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
                TableColumnModel columnModel = table.getColumnModel();
                for (int column = 0; column < table.getColumnCount(); column++) {
                    int width = 15; // Min width
                    for (int row = 0; row < table.getRowCount(); row++) {
                        TableCellRenderer renderer = table.getCellRenderer(row, column);
                        Component comp = table.prepareRenderer(renderer, row, column);
                        width = Math.max(comp.getPreferredSize().width +1 , width);
                    }
                    if(width > 300)
                        width=300;
                    columnModel.getColumn(column).setPreferredWidth(width);
                }
                table.setModel(datumszerint);
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);

                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
                con.close();
                gorgeto.setViewportView(table);
                Foablak.frame.setCursor(null);        
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }
         }
    }
    
    public String muszak(String datum, String ellenorzesideje)
    {
        String melyikmuszak = "";
        try
        {             
            SimpleDateFormat hosszu = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String maiido = datum.replace("-", ".");
            Date reggelvege = hosszu.parse(maiido+" 13:55:00");
            Date reggelkezd = hosszu.parse(maiido + " 05:55:00");
            Date delutanvege = hosszu.parse(maiido+" 21:55:00");
            Date d3 = hosszu.parse(ellenorzesideje.replace("-", "."));          //hosszu.parse(maihosszu);
            
            if(reggelkezd.compareTo(d3) < 0 && reggelvege.compareTo(d3) > 0) 
            {
               melyikmuszak = "De";
            }            
            else if(reggelvege.compareTo(d3) < 0 && delutanvege.compareTo(d3) > 0) 
            {
               melyikmuszak = "Du";
            }
            else 
            {
                melyikmuszak = "Éj";
            }
                        
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
        return melyikmuszak;    
    }
    
    class Ellenorszerint implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {            
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                table2.setModel(nevszerint);
                int rowCount = nevszerint.getRowCount();
                for (int i = rowCount - 1; i > -1; i--) 
                {
                    nevszerint.removeRow(i);
                }
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String sql = "SElect nevek.datum, nevek.nev, FB7530.db as FB7530, FR2400.db as FR2400, FR1200.db as FR1200, FR600.db as FR600, FD302.db as FD302\r\n"
                        + "from\r\n"
                        + "(select OQC_FB7530.Tesztelo as nev, cast(OQC_FB7530.Datum as date) as datum\r\n"
                        + "from  qualitydb.OQC_FB7530\r\n"
                        + "where OQC_FB7530.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR2400.Tesztelo as nev, cast(OQC_FR2400.Datum as date) as datum\r\n"
                        + "from  qualitydb.OQC_FR2400\r\n"
                        + "where OQC_FR2400.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR1200.Tesztelo as nev, cast(OQC_FR1200.Datum as date) as datum\r\n"
                        + "from  qualitydb.OQC_FR1200\r\n"
                        + "where OQC_FR1200.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FR600.Tesztelo as nev, cast(OQC_FR600.Datum as date) as datum\r\n"
                        + "from  qualitydb.OQC_FR600\r\n"
                        + "where OQC_FR600.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"'\r\n"
                        + "union\r\n"
                        + "select OQC_FD302.Tesztelo as nev, cast(OQC_FD302.Datum as date) as datum\r\n"
                        + "from  qualitydb.OQC_FD302\r\n"
                        + "where OQC_FD302.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"') nevek\r\n"
                        + "left join (select OQC_FB7530.Tesztelo as nev, count(OQC_FB7530.Tesztelo) db, OQC_FB7530.datum as datum\r\n"
                        + "from  qualitydb.OQC_FB7530\r\n"
                        + "where OQC_FB7530.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"' group by nev, datum) FB7530 on FB7530.datum = nevek.datum\r\n"
                        + "left join (select OQC_FR2400.Tesztelo as nev, count(OQC_FR2400.Tesztelo) db, OQC_FR2400.datum as datum\r\n"
                        + "from  qualitydb.OQC_FR2400\r\n"
                        + "where OQC_FR2400.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"' group by nev, datum) FR2400 on FR2400.datum = nevek.datum\r\n"
                        + "left join (select OQC_FR1200.Tesztelo as nev, count(OQC_FR1200.Tesztelo) db, OQC_FR1200.datum as datum\r\n"
                        + "from  qualitydb.OQC_FR1200\r\n"
                        + "where OQC_FR1200.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"' group by nev, datum) FR1200 on FR1200.datum = nevek.datum\r\n"
                        + "left join (select OQC_FR600.Tesztelo as nev, count(OQC_FR600.Tesztelo) db, OQC_FR600.datum as datum\r\n"
                        + "from  qualitydb.OQC_FR600\r\n"
                        + "where OQC_FR600.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"' group by nev, datum) FR600 on FR600.datum = nevek.datum\r\n"
                        + "left join (select OQC_FD302.Tesztelo as nev, count(OQC_FD302.Tesztelo) db, OQC_FD302.datum as datum\r\n"
                        + "from  qualitydb.OQC_FD302\r\n"
                        + "where OQC_FD302.Tesztelo = '"+ String.valueOf(ellenor_box.getSelectedItem()) +"' group by nev, datum) FD302 on FD302.datum = nevek.datum\r\n"
                        + "group by nevek.datum\r\n"
                        + "order by nevek.datum desc";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet(); 
                while(rs.next())
                {
                    nevszerint.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                }
                TableColumnModel columnModel = table2.getColumnModel();
                for (int column = 0; column < table2.getColumnCount(); column++) {
                    int width = 15; // Min width
                    for (int row = 0; row < table2.getRowCount(); row++) {
                        TableCellRenderer renderer = table2.getCellRenderer(row, column);
                        Component comp = table2.prepareRenderer(renderer, row, column);
                        width = Math.max(comp.getPreferredSize().width +1 , width);
                    }
                    if(width > 300)
                        width=300;
                    columnModel.getColumn(column).setPreferredWidth(width);
                }
                table2.setModel(nevszerint);
                con.close();
                gorgeto.setViewportView(table2);
                Foablak.frame.setCursor(null);        
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }
         }
    }
}
