import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.JButton;

public class Vevoireklamacio_d5 extends JPanel {
    private JTextField felelos_mezo;
    private JTextField hatarido_mezo;
    private JTable table;
    private JTextField felelos2_mezo;
    private JTextField hatarido2_mezo;
    private JTable table2;
    private DefaultTableModel modell;
    private DefaultTableModel modell2;
    private JTextArea elofordulas_mezo;
    private JTextArea detektalas_mezo;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d5() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        modell = new DefaultTableModel();
        modell2 = new DefaultTableModel();
        
        JLabel lblNewLabel = new JLabel("Intézkedések:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(148, 46, 156, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Előfordulásra");
        lblNewLabel_1.setBounds(148, 84, 91, 14);
        add(lblNewLabel_1);
        
        elofordulas_mezo = new JTextArea();
        elofordulas_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        elofordulas_mezo.setLineWrap(true);
        elofordulas_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto3 = new JScrollPane(elofordulas_mezo);
        gorgeto3.setBounds(249, 78, 367, 99);
        add(gorgeto3);
        
        JLabel lblNewLabel_2 = new JLabel("Felelős");
        lblNewLabel_2.setBounds(688, 84, 65, 14);
        add(lblNewLabel_2);
        
        felelos_mezo = new JTextField();
        felelos_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos_mezo.setBounds(763, 81, 177, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Határidő");
        lblNewLabel_3.setBounds(999, 84, 65, 14);
        add(lblNewLabel_3);
        
        hatarido_mezo = new JTextField();
        hatarido_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido_mezo.setBounds(1063, 81, 86, 20);
        add(hatarido_mezo);
        hatarido_mezo.setColumns(10);
        
        table = new JTable();
        modell.setColumnIdentifiers(new Object[]{"Feladat","Felelős","Határidő","Lezárás dátuma","Futó ID"});
        table.setModel(modell);        
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(238, 188, 1018, 133);
        add(gorgeto);
        
        JLabel lblNewLabel_4 = new JLabel("Nem detektálhatóságra");
        lblNewLabel_4.setBounds(100, 374, 146, 14);
        add(lblNewLabel_4);
        
        JButton intezkedes_gomb = new JButton("Hozzáad");
        intezkedes_gomb.addActionListener(new Hozzaad());
        intezkedes_gomb.setBounds(676, 154, 89, 23);
        add(intezkedes_gomb);
        
        detektalas_mezo = new JTextArea();
        detektalas_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        detektalas_mezo.setLineWrap(true);
        detektalas_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto4 = new JScrollPane(detektalas_mezo);
        gorgeto4.setBounds(249, 374, 367, 99);
        add(gorgeto4);
        
        JLabel lblNewLabel_5 = new JLabel("Felelős");
        lblNewLabel_5.setBounds(688, 374, 65, 14);
        add(lblNewLabel_5);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos2_mezo.setBounds(763, 371, 177, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Határidő");
        lblNewLabel_6.setBounds(999, 374, 75, 14);
        add(lblNewLabel_6);
        
        hatarido2_mezo = new JTextField();
        hatarido2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido2_mezo.setBounds(1063, 371, 86, 20);
        add(hatarido2_mezo);
        hatarido2_mezo.setColumns(10);
        
        table2 = new JTable();
        modell2.setColumnIdentifiers(new Object[]{"Feladat","Felelős","Határidő","Lezárás dátuma","Futó ID"});
        table2.setModel(modell2);        
        JScrollPane gorgeto2 = new JScrollPane(table2);
        gorgeto2.setBounds(238, 499, 1018, 133);
        add(gorgeto2);
        
        JButton detektalas_gomb = new JButton("Hozzáad");
        detektalas_gomb.addActionListener(new Hozzaad2());
        detektalas_gomb.setBounds(676, 465, 89, 23);
        add(detektalas_gomb);
        
        JButton d5zaras_gomb = new JButton("D5 zárása");
        d5zaras_gomb.addActionListener(new Mentes());
        d5zaras_gomb.setBounds(676, 673, 89, 23);
        add(d5zaras_gomb);

    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Vevoireklamacio_fejlec.d5 = Color.GREEN;
                Vevoireklamacio_fejlec.lezaras = Color.YELLOW;
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
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
    
    class Hozzaad implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                modell.addRow(new Object[]{elofordulas_mezo.getText(), felelos_mezo.getText(),hatarido_mezo.getText(),"",""});
                table.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
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
                table.setModel(modell);
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
    
    class Hozzaad2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                modell2.addRow(new Object[]{detektalas_mezo.getText(), felelos2_mezo.getText(),hatarido2_mezo.getText(),"",""});
                table2.getColumnModel().getColumn(0).setCellRenderer(new WordWrapCellRenderer());
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
                table2.setModel(modell2);
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "";
            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
            {
                if(table.getValueAt(szamlalo, 4).toString().equals(""))
                {
                    sql = "insert into qualitydb.Vevoireklamacio_elo (Feladat,Felelos,Hatarido,Lezaras_datuma,D,Rek_ID) values('"+ table.getValueAt(szamlalo, 0).toString() +"', '"+table.getValueAt(szamlalo, 1).toString() +"', "
                          + "'"+ table.getValueAt(szamlalo, 2).toString() +"','"+table.getValueAt(szamlalo, 4).toString() +"','D5','"+ Vevoireklamacio_fejlec.id_mezo.getText() +"')";
                }
                else
                {
                    sql = "update qualitydb.Vevoireklamacio_elo set Feladat = '"+ table.getValueAt(szamlalo, 0).toString() +"', Felelos = '"+table.getValueAt(szamlalo, 1).toString() +"', "
                            + "Hatarido = '"+ table.getValueAt(szamlalo, 2).toString() +"', Lezaras_datuma = '"+table.getValueAt(szamlalo, 4).toString() +"' "                   
                            + "where id = '"+ table.getValueAt(szamlalo, 4).toString() +"'";
                }
                ment.mindenes(sql);
            }
            
            for(int szamlalo = 0; szamlalo < table2.getRowCount(); szamlalo++)
            {
                if(table2.getValueAt(szamlalo, 4).toString().equals(""))
                {
                    sql = "insert into qualitydb.Vevoireklamacio_det (Feladat,Felelos,Hatarido,Lezaras_datuma,D,Rek_ID) values('"+ table2.getValueAt(szamlalo, 0).toString() +"', '"+table2.getValueAt(szamlalo, 1).toString() +"', "
                          + "'"+ table2.getValueAt(szamlalo, 2).toString() +"','"+table2.getValueAt(szamlalo, 4).toString() +"','D5','"+ Vevoireklamacio_fejlec.id_mezo.getText() +"')";
                }
                else
                {
                    sql = "update qualitydb.Vevoireklamacio_det set Feladat = '"+ table2.getValueAt(szamlalo, 0).toString() +"', Felelos = '"+table2.getValueAt(szamlalo, 1).toString() +"', "
                            + "Hatarido = '"+ table2.getValueAt(szamlalo, 2).toString() +"', Lezaras_datuma = '"+table2.getValueAt(szamlalo, 4).toString() +"' "                   
                            + "where id = '"+ table2.getValueAt(szamlalo, 4).toString() +"'";
                }
                ment.mindenes(sql);
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
    
    public void visszatolt()
    {
        int rowCount = modell.getRowCount();
        
        for (int i = rowCount - 1; i > -1; i--) 
        {
          modell.removeRow(i);
        }
        table.setModel(modell);
        rowCount = modell2.getRowCount();
        
        for (int i = rowCount - 1; i > -1; i--) 
        {
          modell2.removeRow(i);
        }
        table2.setModel(modell2);
        
        Connection conn = null;
        Statement stmt = null;        
        try 
        {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String sql = "select * from qualitydb.Vevoireklamacio_elo where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"' and D = 'D5'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while(rs.next())
            {
                modell.addRow(new Object[]{rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(1)});
            }
            table.setModel(modell);
            
            sql = "select * from qualitydb.Vevoireklamacio_det where REk_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"' and D = 'D5'";
            stmt.execute(sql);
            rs = stmt.getResultSet();
            while(rs.next())
            {
                modell2.addRow(new Object[]{rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(1)});
            }
            table2.setModel(modell2);
            stmt.close();
            conn.close();        
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                  stmt.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
               se.printStackTrace();
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
}
