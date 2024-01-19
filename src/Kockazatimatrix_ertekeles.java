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

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Kockazatimatrix_ertekeles extends JPanel {
    private JTable table;
    private JComboBox<String> vezeto_box;
    private ComboBox combobox_tomb = new ComboBox();
    private DefaultTableModel modell;

    /**
     * Create the panel.
     */
    public Kockazatimatrix_ertekeles() {
        setLayout(null);
        
        this.setPreferredSize(new Dimension(1400, 910));
        Foablak.meretek.setSize(1450, 999);
        
        JLabel lblNewLabel = new JLabel("Kockázatok értékelése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(560, 43, 228, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Vezető");
        lblNewLabel_1.setBounds(459, 111, 46, 14);
        add(lblNewLabel_1);
        
        vezeto_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.kockazati_vezetok));                     //combobox_tomb.getCombobox2(ComboBox.kockazati_vezetok)
        vezeto_box.addActionListener(new Kereses());
        vezeto_box.setBounds(560, 107, 258, 22);
        add(vezeto_box);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(588, 174, 89, 23);
        add(keres_gomb);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Folyamat","Alfolyamat","Kockázat","Jelen intézkedés","Valószínűség","Következmény"});
        table = new JTable(modell);
        table.setRowHeight(32);
        /*table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {     // to detect doble click events
                    int columnIndex = table.getSelectedColumn();
                    
                }
             }
          });  */     
        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(40, 221, 1350, 239);
        add(gorgeto);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(588, 488, 89, 23);
        add(mentes_gomb);
        
        setBackground(Foablak.hatter_szine);

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

    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String vezeto = String.valueOf(vezeto_box.getSelectedItem());
                String sql = "";
                int rowCount = modell.getRowCount();
                for (int i = rowCount - 1; i > -1; i--) 
                {
                  modell.removeRow(i);
                }
                if(vezeto.equals("Klambauer Csaba"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, KCS_v, KCS_K from qualitydb.Kockazatimatrix_alap where KCS_kell = 'x'";
                }
                else if(vezeto.equals("Tóth Attila"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, TA_v, TA_K from qualitydb.Kockazatimatrix_alap where TA_kell = 'x'";
                }
                else if(vezeto.equals("Fábián Zoltán"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, FZ_v, FZ_K from qualitydb.Kockazatimatrix_alap where FZ_kell = 'x'";
                }
                else if(vezeto.equals("Sinkó Szabolcs"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, SSZ_v, SSZ_K from qualitydb.Kockazatimatrix_alap where SSZ_kell = 'x'";
                }
                else if(vezeto.equals("Bakk Attila"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, BaA_v, BaA_K from qualitydb.Kockazatimatrix_alap where BaA_kell = 'x'";
                }
                else if(vezeto.equals("Faragó András"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, FA_v, FA_K from qualitydb.Kockazatimatrix_alap where FA_kell = 'x'";
                }
                else if(vezeto.equals("Tóth Csaba"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, TCS_v, TCS_K from qualitydb.Kockazatimatrix_alap where TCS_kell = 'x'";
                }
                else if(vezeto.equals("Breznai András"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, BrA_v, BrA_K from qualitydb.Kockazatimatrix_alap where BrA_kell = 'x'";
                }
                else if(vezeto.equals("Müller Tamás"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, MT_v, MT_K from qualitydb.Kockazatimatrix_alap where MT_kell = 'x'";
                }
                else if(vezeto.equals("Makk Áron"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, MA_v, MA_K from qualitydb.Kockazatimatrix_alap where MA_kell = 'x'";
                }
                else if(vezeto.equals("Pass Zoltán"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, PZ_v, PZ_K from qualitydb.Kockazatimatrix_alap where PZ_kell = 'x'";
                }
                else if(vezeto.equals("Bakter László"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, BL_v, BL_K from qualitydb.Kockazatimatrix_alap where BL_kell = 'x'";
                }
                else if(vezeto.equals("Dr. Gódányné T. Katalin"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, TK_v, TK_K from qualitydb.Kockazatimatrix_alap where TK_kell = 'x'";
                }
                else if(vezeto.equals("Bolla Balázs"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, BB_v, BB_K from qualitydb.Kockazatimatrix_alap where BB_kell = 'x'";
                }
                else if(vezeto.equals("Purmann Ildikó"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, PI_v, PI_K from qualitydb.Kockazatimatrix_alap where PI_kell = 'x'";
                }
                else if(vezeto.equals("Csókás-Fülöp Brigitta"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, FB_v, FB_K from qualitydb.Kockazatimatrix_alap where FB_kell = 'x'";
                }
                else if(vezeto.equals("Krausz Balázs"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, KB_v, KB_K from qualitydb.Kockazatimatrix_alap where KB_kell = 'x'";
                }
                else if(vezeto.equals("Mikó Csaba"))
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, MCS_v, MCS_K from qualitydb.Kockazatimatrix_alap where MCS_kell = 'x'";
                }
                else
                {
                    sql = "select Folyamat, Alfolyamat, Kockazat, Jelen_intezkedes, FT_v, FT_K from qualitydb.Kockazatimatrix_alap where FT_kell = 'x'";
                }
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    modell.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
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
                columnModel.getColumn(0).setPreferredWidth(30);
                columnModel.getColumn(1).setPreferredWidth(50);
                /*
                for (int row = 0; row < table.getRowCount(); row++)
                {
                    int rowHeight = table.getRowHeight();

                    for (int column = 0; column < table.getColumnCount(); column++)
                    {
                        Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                    }

                    table.setRowHeight(row, rowHeight);
                }*/
                table.setModel(modell);
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
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL ment = new SQA_SQL();
                String sql = "";
                String vezeto = String.valueOf(vezeto_box.getSelectedItem());
                for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                {
                    if(vezeto.equals("Klambauer Csaba"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KCS_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', KCS_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Tóth Attila"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TA_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', TA_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Fábián Zoltán"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FZ_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', FZ_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Sinkó Szabolcs"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set SSZ_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', SSZ_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Bakk Attila"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BaA_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', BaA_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Faragó András"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FA_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', FA_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Tóth Csaba"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TCS_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', TCS_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Breznai András"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BrA_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', BrA_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Müller Tamás"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MT_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', MT_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Makk Áron"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MA_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', MA_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Pass Zoltán"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PZ_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', PZ_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Bakter László"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BL_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', BL_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Dr. Gódányné T. Katalin"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set TK_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', TK_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Bolla Balázs"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set BB_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', BB_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Purmann Ildikó"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set PI_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', PI_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Csókás-Fülöp Brigitta"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FB_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', FB_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Krausz Balázs"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set KB_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', KB_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else if(vezeto.equals("Mikó Csaba"))
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set MCS_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', MCS_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                    }
                    else
                    {
                        sql = "update qualitydb.Kockazatimatrix_alap set FT_v = '"+ table.getValueAt(szamlalo, 4).toString() +"', FT_K = '"+ table.getValueAt(szamlalo, 5).toString() +"' "
                                + "where folyamat = '"+ table.getValueAt(szamlalo, 0).toString() +"' and alfolyamat = '"+ table.getValueAt(szamlalo, 1).toString() +"' and "
                                + "kockazat = '"+ table.getValueAt(szamlalo, 2).toString() +"' and jelen_intezkedes = '"+ table.getValueAt(szamlalo, 3).toString() +"'";
                        System.out.println(table.getValueAt(szamlalo, 0).toString() +" és "+ table.getValueAt(szamlalo, 1).toString());
                        System.out.println(table.getValueAt(szamlalo, 2).toString() +" és "+ table.getValueAt(szamlalo, 3).toString());
                        System.out.println(table.getValueAt(szamlalo, 4).toString() +" és "+ table.getValueAt(szamlalo, 5).toString());
                    }
                    
                    ment.mindenes(sql);
                }
                
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
