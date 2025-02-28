import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class Zarolasok_osszesito extends JPanel {
    
    private JTable table;
    private DefaultTableModel modell;
    private JTextField zarolopapir_mezo;
    private JComboBox<String> oszlop_box;
    private JComboBox<String> mireszur_box;
    //private ComboBox combobox_tomb = new ComboBox();
    private JTextField datum_mezo;
    private JXDatePicker datum_tol;
    private JCheckBox nyitott_gomb;

    /**
     * Create the panel.
     */
    public Zarolasok_osszesito() {
        setLayout(null);
        this.setPreferredSize(new Dimension(1600, 759));
        Foablak.meretek.setSize(1600, 750);
        JLabel lblNewLabel = new JLabel("Zárolások összesítő");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBackground(Color.BLUE);
        lblNewLabel.setBounds(616, 52, 180, 14);
        add(lblNewLabel);
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"ID","Zárolos papír sorszáma", "Projekt", "Tipus","Felelős terület", "Észlelés helye","Zárolt db","Hol van","Zárolás oka","Zárolás dátuma"});
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(10, 119, 1580, 356);
        add(gorgeto);
        
        JLabel lblNewLabel_1 = new JLabel("Kiválasztott zárolás megynyitása");
        lblNewLabel_1.setBounds(431, 525, 186, 14);
        add(lblNewLabel_1);
        
        JButton betolt_gomb = new JButton("Betölt");
        betolt_gomb.addActionListener(new Visszatolt());
        betolt_gomb.setBounds(643, 521, 89, 23);
        add(betolt_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Új zárolás felvitele");
        lblNewLabel_2.setBounds(431, 574, 186, 14);
        add(lblNewLabel_2);
        
        JButton ujreki_gomb = new JButton("Start");
        ujreki_gomb.addActionListener(new Uj_zarolas());
        ujreki_gomb.setBounds(643, 570, 89, 23);
        add(ujreki_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_3 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_3.setBounds(847, 525, 137, 14);
        add(lblNewLabel_3);
        
        zarolopapir_mezo = new JTextField();
        zarolopapir_mezo.setBounds(1019, 522, 171, 20);
        zarolopapir_mezo.addKeyListener(new Enter());
        add(zarolopapir_mezo);
        zarolopapir_mezo.setColumns(10);
        
        oszlop_box = new JComboBox<String>();                  //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        oszlop_box.addActionListener(new Oszlop_tartalma());
        oszlop_box.setBounds(44, 86, 243, 22);
        add(oszlop_box);
        
        mireszur_box = new JComboBox<String>();
        mireszur_box.addActionListener(new Mire_szur());
        mireszur_box.setBounds(352, 86, 265, 22);
        add(mireszur_box);
        
        datum_tol = new JXDatePicker();
        datum_tol.addActionListener(new Datum_szures());
        datum_tol.setBounds(765, 87, 120, 20);
        add(datum_tol);
        /*datum_mezo = new JTextField();
        datum_mezo.addKeyListener(new Datum_szuro());
        datum_mezo.setBounds(765, 87, 108, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);*/
        
        JLabel lblNewLabel_4 = new JLabel("Dátum szűrés");
        lblNewLabel_4.setBounds(661, 90, 116, 14);
        add(lblNewLabel_4);
        
        JButton cikkujra_gomb = new JButton("Cikkszámok újratöltése");
        cikkujra_gomb.addActionListener(new Cikk_ujratolt());
        cikkujra_gomb.setBounds(1348, 85, 187, 23);
        //add(cikkujra_gomb);
        
        nyitott_gomb = new JCheckBox("Csak nyitottak");
        nyitott_gomb.addActionListener(new Nyitottra_szur());
        nyitott_gomb.setBounds(956, 86, 116, 23);
        add(nyitott_gomb);
        adatok();
        oszlopok();

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
        String sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                + "zarolas_datuma \r\n"
                + "from qualitydb.Zarolasok\r\n"
                + "where 3 = 3 order by ID desc";                                        
        stmt.execute(sql);      
        rs = stmt.getResultSet();
        while(rs.next())
        {
            String[] datum = rs.getString(10).split(" ");
            modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),datum[0]});                             
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
    }
    
    private void oszlopok()
    {     
        try 
        {     
            String sql = "SHOW COLUMNS FROM qualitydb.Zarolasok";
            DefaultComboBoxModel<String> model;
            SQA_SQL cikkszamok = new SQA_SQL();
            String[] cikkek = cikkszamok.tombvissza_sajat(sql);                
            String[] ujmodell = new String[cikkek.length];
            for(int szamlalo = 0; szamlalo <cikkek.length; szamlalo++)
            {
                ujmodell[szamlalo] = cikkek[szamlalo];
            }               
            model = new DefaultComboBoxModel<>(ujmodell);
               
            oszlop_box.setModel(model);
        }
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }
    }
    
    
    
    class Cikk_ujratolt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String[] cikkbox = null;
                DefaultComboBoxModel<String> model;
            
                    Class.forName("oracle.jdbc.OracleDriver");  //.driver
                    
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                    Statement stmt = con.createStatement();              
                    ResultSet rs = stmt.executeQuery( "select cikkszamok.part_no, \r\n"
                            + "        cikkszamok.REVISION_TEXT,\r\n"
                            + "        cikkszamok.CF$_PRODUCT_CODE_DESC \r\n"
                            + "from ifsapp.PART_REVISION_cfv cikkszamok\r\n"
                            + "where 3 = 3 order by part_no asc");
                    ArrayList<String> cikkszamok = new ArrayList<String>();
                    SQA_SQL betolt = new SQA_SQL();
                    betolt.mindenes("delete from qualitydb.Cikk_valtozatok");   
                    while(rs.next())
                    {
                        if(rs.getString(2) != null)
                        {
                            cikkszamok.add(rs.getString(1)+ "- "+ rs.getString(2) + " "+ rs.getString(3));
                            betolt.mindenes("insert into qualitydb.Cikk_valtozatok (Cikkek) Values('"+ rs.getString(1)+ "- "+ rs.getString(2) + " "+ rs.getString(3) +"')");
                        }
                        else
                        {
                            cikkszamok.add(rs.getString(1)+ " "+ rs.getString(3));
                            betolt.mindenes("insert into qualitydb.Cikk_valtozatok (Cikkek) Values('"+ rs.getString(1)+ " "+ rs.getString(3) +"')");
                        }
                    }
                    cikkbox = cikkszamok.toArray(new String[cikkszamok.size()]);
                    
                    model = new DefaultComboBoxModel<String>(cikkbox);                     //cikkbox
                    mireszur_box.setModel(model);
                    Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Uj_zarolas implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Zarolasok_bevitel zarolasok = new Zarolasok_bevitel();
                JScrollPane ablak = new JScrollPane(zarolasok);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
                Foablak.frame.setCursor(null);                                                                                         //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
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
                    JOptionPane.showMessageDialog(null, "Nincs kiválasztva reklmáció!!", "Hiba üzenet", 2);                                                     // hibaüzenetet kiratása
                }
                else
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre                
                    String id = table.getValueAt(sor, 0).toString();
                    Zarolasok_bevitel zarolasok = new Zarolasok_bevitel(id);
                    JScrollPane ablak = new JScrollPane(zarolasok);
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
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                    SQA_SQL zarolas = new SQA_SQL();
                    String sql = "select id from qualitydb.Zarolasok where Papir_sorszama like '%"+ zarolopapir_mezo.getText() + "%'";
                    if(zarolas.tombvissza_sajat(sql).length > 0)
                    {
                        String id = zarolas.tombvissza_sajat(sql)[0];
                        Zarolasok_bevitel zarolasok = new Zarolasok_bevitel(id);
                        JScrollPane ablak = new JScrollPane(zarolasok);
                        ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        Foablak.frame.setContentPane(ablak);
                        Foablak.frame.pack();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Nincs ilyen sorszámú zárolás!", "Hiba üzenet", 2);
                    }
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }
                
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
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }        
    
    class Datum_szuro implements KeyListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                    
                    int rowCount = modell.getRowCount();
                    
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }
                    table.setModel(modell);
                    Connection conn = null;
                    Statement stmt = null;               
                    ResultSet rs;       
                    
                    conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
                    String sql = "select id, projekt, tipus, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                            + "zarolas_datuma\r\n"
                            + "from qualitydb.Zarolasok\r\n"
                            + "where 3 = 3 and Zarolas_datuma = '"+ datum_mezo.getText() +"' order by ID desc";                                        
                    stmt.execute(sql);      
                    rs = stmt.getResultSet();
                    while(rs.next())
                    {
                        String[] datum = rs.getString(8).split(" ");
                        modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),datum[0]});                             
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
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása                   
                }              
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
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
    
    class Oszlop_tartalma implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                
                int rowCount = modell.getRowCount();
                
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                table.setModel(modell);
                String sql = "";
                sql = "select "+ String.valueOf(oszlop_box.getSelectedItem()) +" "
                        + "from qualitydb.Zarolasok\r\n"
                        + "where 3 = 3 group by "+ String.valueOf(oszlop_box.getSelectedItem()) +"  order by "+ String.valueOf(oszlop_box.getSelectedItem()) +" asc";
                DefaultComboBoxModel<String> model;
                SQA_SQL cikkszamok = new SQA_SQL();
                String[] cikkek = cikkszamok.tombvissza_sajat(sql);                
                String[] ujmodell = new String[cikkek.length];
                for(int szamlalo = 0; szamlalo <cikkek.length; szamlalo++)
                {
                    ujmodell[szamlalo] = cikkek[szamlalo];
                }               
                model = new DefaultComboBoxModel<>(ujmodell);
                   
                mireszur_box.setModel(model);
                
                //table.setModel(modell);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                
            }
            catch (Exception e1) 
            {
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                e1.printStackTrace();
            }
                            
         }
    }
    
    class Mire_szur implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                
                int rowCount = modell.getRowCount();
                
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                table.setModel(modell);
                Connection conn = null;
                Statement stmt = null;               
                ResultSet rs;       
                
                conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String sql = "";
                if(nyitott_gomb.isSelected())
                {
                    sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                            + "zarolas_datuma \r\n"
                            + "from qualitydb.Zarolasok\r\n"
                            + "where 3 = 3 and "+ String.valueOf(oszlop_box.getSelectedItem()) +" = '"+ String.valueOf(mireszur_box.getSelectedItem()) +"'and (Lezaras_datuma is null or lezaras_datuma = '') order by \"+ String.valueOf(oszlop_box.getSelectedItem()) + asc";
                }
                else
                {
                    sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                            + "zarolas_datuma \r\n"
                            + "from qualitydb.Zarolasok\r\n"
                            + "where 3 = 3 and "+ String.valueOf(oszlop_box.getSelectedItem()) +" = '"+ String.valueOf(mireszur_box.getSelectedItem()) +"' order by "+ String.valueOf(oszlop_box.getSelectedItem()) +" desc";
                }
                                                 
                stmt.execute(sql);      
                rs = stmt.getResultSet();
                while(rs.next())
                {
                    String[] datum = rs.getString(10).split(" ");
                    modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),datum[0]});                             
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
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                
            }
            catch (Exception e1) 
            {
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                e1.printStackTrace();
            }
                            
         }
    }
    
    class Datum_szures implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                
                int rowCount = modell.getRowCount();
                
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                table.setModel(modell);
                Connection conn = null;
                Statement stmt = null;               
                ResultSet rs;       
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
                String sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                        + "zarolas_datuma \r\n"
                        + "from qualitydb.Zarolasok\r\n"
                        + "where 3 = 3 and Zarolas_datuma = '"+ dateFormat.format(datum_tol.getDate()) +"' order by ID desc";                                        
                stmt.execute(sql);      
                rs = stmt.getResultSet();
                while(rs.next())
                {
                    String[] datum = rs.getString(8).split(" ");
                    modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),datum[0]});                             
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
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása            
                                       
                
            }
            catch (Exception e1) 
            {
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                e1.printStackTrace();
            }
                            
         }
    }
    
    class Nyitottra_szur implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                
                int rowCount = modell.getRowCount();
                
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                table.setModel(modell);
                Connection conn = null;
                Statement stmt = null;               
                ResultSet rs;       
                
                conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String sql = "";
                //System.out.println(String.valueOf(mireszur_box.getSelectedItem()));
                if(nyitott_gomb.isSelected())
                {
                    if(String.valueOf(mireszur_box.getSelectedItem()) == null)
                    {
                        System.out.println("nincs kiválasztav semmi");
                        sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                                + "zarolas_datuma \r\n"
                                + "from qualitydb.Zarolasok\r\n"
                                + "where 3 = 3 and  (Lezaras_datuma is null or lezaras_datuma = '') order by ID desc";
                    }
                    else
                    {
                        System.out.println("ki van választva");
                        sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                                + "zarolas_datuma \r\n"
                                + "from qualitydb.Zarolasok\r\n"
                                + "where 3 = 3 and "+ String.valueOf(oszlop_box.getSelectedItem()) +" = '"+ String.valueOf(mireszur_box.getSelectedItem()) +"' and (Lezaras_datuma is null or lezaras_datuma = '') order by ID desc";
                    }
                    stmt.execute(sql);      
                    rs = stmt.getResultSet();
                    while(rs.next())
                    {
                        String[] datum = rs.getString(10).split(" ");
                        modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),datum[0]});                             
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
                else
                {
                    ///System.out.println("fut az else ág");
                    if(String.valueOf(mireszur_box.getSelectedItem()) == null)
                    {      
                        sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                                + "zarolas_datuma \r\n"
                                + "from qualitydb.Zarolasok\r\n"
                                + "where 3 = 3 order by ID desc";
                    }
                    else
                    {
                        
                        sql = "select Id,Papir_sorszama, projekt, tipus,Felelos_terulet, eszleles_helye,zarolt_db,hol_van, zarolas_oka,\r\n"
                                + "zarolas_datuma \r\n"
                                + "from qualitydb.Zarolasok\r\n"
                                + "where 3 = 3 and "+ String.valueOf(oszlop_box.getSelectedItem()) +" = '"+ String.valueOf(mireszur_box.getSelectedItem()) +"'  order by ID desc";
                    }
                    stmt.execute(sql);      
                    rs = stmt.getResultSet();
                    while(rs.next())
                    {
                        String[] datum = rs.getString(10).split(" ");
                        modell.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),datum[0]});                             
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
                }
                                                 
                
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                
            }
            catch (Exception e1) 
            {
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                e1.printStackTrace();
            }
                            
         }
    }
}
