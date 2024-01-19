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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Kockazatimatrix_felvetel extends JPanel {
    private JTable table;
    private JComboBox<String> folyamat_box;
    private JComboBox<String>  alfolyamat_box;
    private ComboBox combobox_tomb = new ComboBox();
    private JTextArea ujkockazat_mezo;
    private JTextArea intezkedes_mezo;
    private DefaultTableModel modell;
    private SQA_SQL lekerdezes = new SQA_SQL();
    private JCheckBox vezeto1;private JCheckBox vezeto2;private JCheckBox vezeto3;private JCheckBox vezeto4;private JCheckBox vezeto5;private JCheckBox vezeto6;private JCheckBox vezeto7;private JCheckBox vezeto8;
    private JCheckBox vezeto9;private JCheckBox vezeto10;private JCheckBox vezeto11;private JCheckBox vezeto12;private JCheckBox vezeto13;private JCheckBox vezeto14;private JCheckBox vezeto15;private JCheckBox vezeto16;
    private JCheckBox vezeto17;private JCheckBox vezeto18;private JCheckBox vezeto19;
    private ArrayList<String> kivalasztott = new ArrayList<String>();
    private String link = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Kockázatimátrix\\Kockázatimátrix folyamat és alfolyamat.xlsx";
    private JLabel lblNewLabel_7;
    private JTextField kulcsszo_mezo;
    

    /**         1400*910
     * Create the panel.
     */
    public Kockazatimatrix_felvetel() {
        
        this.setPreferredSize(new Dimension(1400, 910));
        Foablak.meretek.setSize(1450, 999);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Kockázati mártixba új kockázat felvétele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(508, 46, 493, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Folyamat");
        lblNewLabel_1.setBounds(229, 115, 104, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Alfolyamat");
        lblNewLabel_2.setBounds(229, 160, 93, 14);
        add(lblNewLabel_2);
        
        folyamat_box = new JComboBox<String> (combobox_tomb.getCombobox2(ComboBox.kockazati_folyamat));                        //combobox_tomb.getCombobox2(ComboBox.kockazati_folyamat)
        folyamat_box.addActionListener(new Szukit());
        folyamat_box.setBounds(354, 111, 366, 22);
        add(folyamat_box);
        
        alfolyamat_box = new JComboBox<String> (combobox_tomb.getCombobox2(ComboBox.kockazati_alfolyamat));                      //combobox_tomb.getCombobox2(ComboBox.kockazati_alfolyamat)
        alfolyamat_box.addActionListener(new Szukit2());
        alfolyamat_box.setBounds(354, 156, 745, 22);
        add(alfolyamat_box);
        
        JLabel lblNewLabel_3 = new JLabel("Meglévő kockázatok");
        lblNewLabel_3.setBounds(69, 222, 124, 14);
        add(lblNewLabel_3);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Kockázatok","Jelenlegi intézkedés"}); 
        table = new JTable(modell);
        table.getSelectionModel().addListSelectionListener(new Sorvalaszto());
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(36, 247, 1318, 188);
        add(gorgeto);
        
        JLabel lblNewLabel_4 = new JLabel("Új kockázat");
        lblNewLabel_4.setBounds(229, 451, 104, 14);
        add(lblNewLabel_4);
        
        ujkockazat_mezo = new JTextArea();
        ujkockazat_mezo.setLineWrap(true);
        ujkockazat_mezo.setWrapStyleWord(true);
        ujkockazat_mezo.setBounds(354, 446, 366, 99);
        add(ujkockazat_mezo);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_5 = new JLabel("Vezetők");
        lblNewLabel_5.setBounds(69, 547, 71, 14);
        add(lblNewLabel_5);
        
        vezeto1 = new JCheckBox("Klambauer Csaba");
        vezeto1.setBounds(69, 580, 134, 23);
        add(vezeto1);
        
        vezeto2 = new JCheckBox("Tóth Attila");
        vezeto2.setBounds(229, 580, 97, 23);
        add(vezeto2);
        
        vezeto3 = new JCheckBox("Fábián Zoltán");
        vezeto3.setBounds(354, 580, 109, 23);
        add(vezeto3);
        
        vezeto4 = new JCheckBox("Sinkó Szabolcs");
        vezeto4.setBounds(489, 580, 114, 23);
        add(vezeto4);
        
        vezeto5 = new JCheckBox("Bakk Attila");
        vezeto5.setBounds(635, 580, 97, 23);
        add(vezeto5);
        
        vezeto6 = new JCheckBox("Faragó András");
        vezeto6.setBounds(782, 580, 114, 23);
        add(vezeto6);
        
        vezeto7 = new JCheckBox("Tóth Csaba");
        vezeto7.setBounds(941, 580, 97, 23);
        add(vezeto7);
        
        vezeto8 = new JCheckBox("Breznai András");
        vezeto8.setBounds(1109, 580, 124, 23);
        add(vezeto8);
        
        vezeto9 = new JCheckBox("Müller Tamás");
        vezeto9.setBounds(69, 630, 109, 23);
        add(vezeto9);
        
        vezeto10 = new JCheckBox("Makk Áron");
        vezeto10.setBounds(229, 630, 97, 23);
        add(vezeto10);
        
        vezeto11 = new JCheckBox("Pass Zoltán");
        vezeto11.setBounds(354, 630, 97, 23);
        add(vezeto11);
        
        vezeto12 = new JCheckBox("Bakter László");
        vezeto12.setBounds(489, 630, 114, 23);
        add(vezeto12);
        
        vezeto13 = new JCheckBox("Dr. Gódányné T. Katalin");
        vezeto13.setBounds(635, 630, 172, 23);
        add(vezeto13);
        
        vezeto14 = new JCheckBox("Bolla Balázs");
        vezeto14.setBounds(820, 630, 97, 23);
        add(vezeto14);
        
        vezeto15 = new JCheckBox("Purmann Ildikó");
        vezeto15.setBounds(941, 630, 114, 23);
        add(vezeto15);
        
        vezeto16 = new JCheckBox("Csókás-Fülöp Brigitta");
        vezeto16.setBounds(1109, 630, 151, 23);
        add(vezeto16);
        
        vezeto17 = new JCheckBox("Krausz Balázs");
        vezeto17.setBounds(69, 688, 109, 23);
        add(vezeto17);
        
        vezeto18 = new JCheckBox("Mikó Csaba");
        vezeto18.setBounds(229, 688, 97, 23);
        add(vezeto18);
        
        vezeto19 = new JCheckBox("Fehérvári Tamás");
        vezeto19.setBounds(354, 688, 124, 23);
        add(vezeto19);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(489, 788, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_6 = new JLabel("Jelenlegi intézkedés");
        lblNewLabel_6.setBounds(754, 451, 124, 14);
        add(lblNewLabel_6);
        
        intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setLineWrap(true);
        intezkedes_mezo.setWrapStyleWord(true);
        intezkedes_mezo.setBounds(888, 446, 372, 104);
        add(intezkedes_mezo);
        
        lblNewLabel_7 = new JLabel("Kockázat kulcsszóra keresés");
        lblNewLabel_7.setBounds(229, 201, 184, 14);
        add(lblNewLabel_7);
        
        kulcsszo_mezo = new JTextField();
        kulcsszo_mezo.setBounds(412, 198, 379, 20);
        add(kulcsszo_mezo);
        kulcsszo_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Keres());
        keres_gomb.setBounds(820, 197, 89, 23);
        add(keres_gomb);
        
        table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        
        kockazatok();
    }
    
    private class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
    
    private void kockazatok()
    {
        String sql = "select kockazat from qualitydb.Kockazatimatrix_alap where 3 = 3";
        String[] kockazat = lekerdezes.tombvissza_sajat(sql);
        sql = "select jelen_intezkedes from qualitydb.Kockazatimatrix_alap where 3 = 3";
        String[] intezkedes = lekerdezes.tombvissza_sajat(sql);
        for(int szamlalo = 0; szamlalo < kockazat.length; szamlalo++)
        {
            modell.addRow(new Object[]{kockazat[szamlalo],intezkedes[szamlalo]});
        }
        table.setModel(modell);
    }
    
    private class Sorvalaszto implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            // TODO Auto-generated method stub            
            int sor = table.getSelectedRow();
            //int col = table.getSelectedColumn();
            
            if(e.getValueIsAdjusting())
            {
                //System.out.println(table.getValueAt(row, col));
                try
                {
                    kicsekk();
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                    Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                        
                    Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                    Statement stmt = con.createStatement();
                    String sql = "Select * from qualitydb.Kockazatimatrix_alap where kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                    ResultSet rs = stmt.executeQuery(sql);
                    if(rs.next())
                    {
                        if(rs.getString(6).equals("x"))
                        {
                            vezeto1.setSelected(true);
                        }
                        if(rs.getString(9).equals("x"))
                        {
                            vezeto2.setSelected(true);
                        }
                        if(rs.getString(12).equals("x"))
                        {
                            vezeto3.setSelected(true);
                        }
                        if(rs.getString(15).equals("x"))
                        {
                            vezeto4.setSelected(true);
                        }
                        if(rs.getString(18).equals("x"))
                        {
                            vezeto5.setSelected(true);
                        }
                        if(rs.getString(21).equals("x"))
                        {
                            vezeto6.setSelected(true);
                        }
                        if(rs.getString(24).equals("x"))
                        {
                            vezeto7.setSelected(true);
                        }
                        if(rs.getString(27).equals("x"))
                        {
                            vezeto8.setSelected(true);
                        }
                        if(rs.getString(30).equals("x"))
                        {
                            vezeto9.setSelected(true);
                        }
                        if(rs.getString(33).equals("x"))
                        {
                            vezeto10.setSelected(true);
                        }
                        if(rs.getString(36).equals("x"))
                        {
                            vezeto11.setSelected(true);
                        }
                        if(rs.getString(39).equals("x"))
                        {
                            vezeto12.setSelected(true);
                        }
                        if(rs.getString(42).equals("x"))
                        {
                            vezeto13.setSelected(true);
                        }
                        if(rs.getString(45).equals("x"))
                        {
                            vezeto14.setSelected(true);
                        }
                        if(rs.getString(48).equals("x"))
                        {
                            vezeto15.setSelected(true);
                        }
                        if(rs.getString(51).equals("x"))
                        {
                            vezeto16.setSelected(true);
                        }
                        if(rs.getString(54).equals("x"))
                        {
                            vezeto17.setSelected(true);
                        }
                        if(rs.getString(57).equals("x"))
                        {
                            vezeto18.setSelected(true);
                        }
                        if(rs.getString(60).equals("x"))
                        {
                            vezeto19.setSelected(true);
                        }
                    }
                    con.close();  
                    Foablak.frame.setCursor(null);        
                }           
                catch(Exception e1)
                { 
                    System.out.println(e1);
                    e1.printStackTrace();
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
                }
                                       
             
            }           
        }
    }
    
    private void kicsekk()
    { 
        vezeto1.setSelected(false);
        vezeto2.setSelected(false);
        vezeto3.setSelected(false);
        vezeto4.setSelected(false);
        vezeto5.setSelected(false);
        vezeto6.setSelected(false);
        vezeto7.setSelected(false);
        vezeto8.setSelected(false);
        vezeto9.setSelected(false);
        vezeto10.setSelected(false);
        vezeto11.setSelected(false);
        vezeto12.setSelected(false);
        vezeto13.setSelected(false);
        vezeto14.setSelected(false);
        vezeto15.setSelected(false);
        vezeto16.setSelected(false);
        vezeto17.setSelected(false);
        vezeto18.setSelected(false);
        vezeto19.setSelected(false);
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQA_SQL ment = new SQA_SQL();
                String sql = "";
                String folyamat = String.valueOf(folyamat_box.getSelectedItem());
                String alfolyamat = String.valueOf(alfolyamat_box.getSelectedItem());
                int sor = table.getSelectedRow();
                if(ujkockazat_mezo.getText().equals(""))
                {
                    
                    if(vezeto1.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KCS_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto2.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TA_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto3.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FZ_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto4.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set SSZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set SSZ_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto5.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BaA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BaA_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto6.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FA_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto7.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TCS_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto8.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BrA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BrA_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto9.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MT_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MT_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto10.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MA_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto11.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PZ_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto12.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BL_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BL_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto13.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TK_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TK_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto14.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BB_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto15.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PI_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PI_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto16.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FB_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto17.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KB_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto18.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MCS_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    if(vezeto19.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FT_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FT_kell = '' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ table.getValueAt(sor, 0).toString() +"' and Jelen_intezkedes = '"+ table.getValueAt(sor, 1).toString() +"'";
                        ment.mindenes(sql);
                    }
                }
                else
                {
                    sql = "insert into qualitydb.Kockazatimatrix_alap (Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes)  VALUES('"+ folyamat +"', '"+ alfolyamat +"', "
                            + "'"+ ujkockazat_mezo.getText() +"', '"+ intezkedes_mezo.getText() +"')";
                    ment.mindenes(sql);
                    if(vezeto1.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto2.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto3.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto4.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set SSZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                  
                    if(vezeto5.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BaA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                  
                    if(vezeto6.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto7.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto8.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BrA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto9.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MT_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto10.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MA_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto11.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PZ_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto12.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BL_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto13.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TK_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto14.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto15.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PI_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                    if(vezeto16.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto17.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KB_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto18.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MCS_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                    
                    if(vezeto19.isSelected())
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FT_kell = 'x' where folyamat = '"+ folyamat +"' and alfolyamat = '"+ alfolyamat +"' and "
                                + "kockazat = '"+ ujkockazat_mezo.getText() +"' and Jelen_intezkedes = '"+ intezkedes_mezo.getText() +"'";
                        ment.mindenes(sql);
                    }                   
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Szukit implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String folyamat = String.valueOf(folyamat_box.getSelectedItem());
                Workbook workbook = new Workbook();
                workbook.loadFromFile(link);
                Worksheet sheet = workbook.getWorksheets().get(0);
                kivalasztott.clear();
                kivalasztott.add("-");
                for(int szamlalo = 1; szamlalo < sheet.getLastRow(); szamlalo++)
                {
                    if(sheet.getRange().get("A" + szamlalo).getText().equals(folyamat))
                    {
                        kivalasztott.add(sheet.getRange().get("B" + szamlalo).getText()); 
                    }
                }
                String[] ujmodell = new String[kivalasztott.size()];
                for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                {
                    ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                }
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(ujmodell);
                alfolyamat_box.setModel(model);
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Szukit2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
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
                String folyamat = String.valueOf(folyamat_box.getSelectedItem());
                String alfolyamat = String.valueOf(alfolyamat_box.getSelectedItem());
                //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String sql = "Select kockazat, jelen_intezkedes from qualitydb.Kockazatimatrix_alap where Folyamat = '"+ folyamat +"' and Alfolyamat = '"+ alfolyamat +"'";
                ResultSet rs = stmt.executeQuery(sql);
                modell.setColumnIdentifiers(new Object[]{"Kockázatok","Jelenlegi intézkedés"}); 
                while(rs.next())
                {
                    modell.addRow(new Object[]{rs.getString(1),rs.getString(2)});
                }
                table.setModel(modell);
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Keres implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
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
                //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String sql = "Select folyamat, alfolyamat, kockazat, jelen_intezkedes from qualitydb.Kockazatimatrix_alap where kockazat Like '%"+ kulcsszo_mezo.getText() +"%'";
                ResultSet rs = stmt.executeQuery(sql);
                modell.setColumnIdentifiers(new Object[]{"Folyamat","Alfolyamat","Kockázatok","Jelenlegi intézkedés"}); 
                while(rs.next())
                {
                    modell.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
                }
                table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
                table.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());
                table.setModel(modell);
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
