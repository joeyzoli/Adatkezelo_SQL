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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

public class Forraszthatosagi_osszesito extends JPanel {
    private JTable table;
    private DefaultTableModel modell;

    /**
     * Create the panel.
     */
    public Forraszthatosagi_osszesito() {
        setLayout(null);
        this.setPreferredSize(new Dimension(1600, 759));
        Foablak.meretek.setSize(1600, 750);
        JLabel lblNewLabel = new JLabel("Forrsztahatósági vizsgálatok");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBackground(Color.BLUE);
        lblNewLabel.setBounds(608, 52, 259, 14);
        add(lblNewLabel);
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"ID","Jegyzőköny száma", "Cikkszam", "Megnevezés", "Projekt","Cikkszám kora","Terület","Mennyiség","Időpont","Eredméyn"});
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(68, 119, 1467, 356);
        add(gorgeto);
        
        JLabel lblNewLabel_1 = new JLabel("Kiválasztott vizsgálat megynyitása");
        lblNewLabel_1.setBounds(431, 525, 186, 14);
        add(lblNewLabel_1);
        
        JButton betolt_gomb = new JButton("Betölt");
        betolt_gomb.addActionListener(new Visszatolt());
        betolt_gomb.setBounds(643, 521, 89, 23);
        add(betolt_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Új vizsgálat felvitele");
        lblNewLabel_2.setBounds(431, 574, 186, 14);
        add(lblNewLabel_2);
        
        JButton ujreki_gomb = new JButton("Start");
        ujreki_gomb.addActionListener(new Uj_ellenorzes());
        ujreki_gomb.setBounds(643, 570, 89, 23);
        add(ujreki_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JButton excel_gomb = new JButton("excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(643, 618, 89, 23);
        add(excel_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Excel export");
        lblNewLabel_3.setBounds(431, 622, 79, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Kiválasztott sor törlése");
        lblNewLabel_4.setBounds(939, 525, 145, 14);
        add(lblNewLabel_4);
        
        JButton torles_gomb = new JButton("Törlés");
        //torles_gomb.addActionListener(new Torles());
        torles_gomb.setBounds(1109, 521, 89, 23);
        add(torles_gomb);
        adatok();
        
    }
    
    private void adatok()
    {
        Connection conn = null;
        Statement stmt = null;               
        ResultSet rs;       
        try 
        {
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
           }
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
        String sql = "select *"
                + "from qualitydb.Forraszthatosagi_vizsgalat\r\n"
                + "where 3 = 3";                                        
        stmt.execute(sql);      
        rs = stmt.getResultSet();
        while(rs.next())
        {
            modell.addRow(new Object[]{rs.getString(1),rs.getString(7), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(6),rs.getString(8),rs.getString(11),rs.getString(12),rs.getString(15)});                                 
        }
        final TableColumnModel columnModel = table.getColumnModel();
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
        //table.setModel(modell);
        
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
    }
    
    class Uj_ellenorzes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Forraszthatosagi_vizsgalat forr = new Forraszthatosagi_vizsgalat();
                JScrollPane ablak = new JScrollPane(forr);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Visszatolt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                int sor = table.getSelectedRow();
                if(sor< 0)
                {
                    JOptionPane.showMessageDialog(null, "Nincs kiválasztva Ellenőrzés!!", "Hiba üzenet", 2);                                                     // hibaüzenetet kiratása
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre                
                    String id = table.getValueAt(sor, 0).toString();
                    Forraszthatosagi_vizsgalat forr = new Forraszthatosagi_vizsgalat(id);
                    JScrollPane ablak = new JScrollPane(forr);
                    ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    Foablak.frame.setContentPane(ablak);
                    Foablak.frame.pack();
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_SQL lekerdez = new SQA_SQL();
                String sql = "select * from qualitydb.Forraszthatosagi_vizsgalat where 3=3";
                lekerdez.minden_excel(sql, "Forraszthatósági vizsgálatok.xlsx");
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    /*
    class Torles implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                int reply = JOptionPane.showConfirmDialog(null, "Biztos minden rendben le lett ellnőrizve? LOT szám?", "Értesítés", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) 
                {
                    SQA_SQL torol = new SQA_SQL();
                    int sor = table.getSelectedRow();
                    String sql = "delete from qualitydb.Forraszthatosagi_vizsgalat where id = '"+table.getValueAt(sor, 0).toString()+"'";
                    torol.mindenes(sql);
                    modell.removeRow(table.getSelectedRow());
                    table.setModel(modell);
                }
                else {}
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }*/
}
