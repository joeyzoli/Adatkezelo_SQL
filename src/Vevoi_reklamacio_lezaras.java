import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class Vevoi_reklamacio_lezaras extends JPanel 
{
    private JTextField datum_mezo;
    private JTextField tipus_mezo;
    static JTable table;
    static JTable table_1;
    private JTextField veglegido_mezo;
    private int tablavege1 = 0;
    private int tablavege2 = 0;

    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_lezaras() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vevői reklamációk nyitott pont lezárása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(385, 23, 353, 20);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(453, 88, 46, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(509, 85, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(443, 128, 56, 14);
        add(lblNewLabel_2);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(509, 125, 86, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keres");
        keres_gomb.setBounds(506, 156, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(73, 201, 986, 127);
        add(scroll);
        
        table_1 = new JTable();
        JScrollPane scroll2 = new JScrollPane(table_1);
        scroll2.setBounds(207, 339, 738, 123);
        add(scroll2);
        
        JButton lezar_gomb = new JButton("Lezárás");
        lezar_gomb.setBounds(506, 492, 89, 23);
        lezar_gomb.addActionListener(new Visszair());
        add(lezar_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Vevői raklamáció lezárás időpontja");
        lblNewLabel_3.setBounds(310, 597, 180, 14);
        add(lblNewLabel_3);
        
        veglegido_mezo = new JTextField();
        veglegido_mezo.setBounds(509, 594, 86, 20);
        add(veglegido_mezo);
        veglegido_mezo.setColumns(10);
        
        JButton veglegzar_gomb = new JButton("Reklamáció zárása");
        veglegzar_gomb.setBounds(605, 593, 157, 23);
        veglegzar_gomb.addActionListener(new Veglegzar());
        add(veglegzar_gomb);
        
        JButton sorgomb1 = new JButton("Sor hozzáadása");
        sorgomb1.setBounds(1069, 253, 117, 23);
        sorgomb1.addActionListener(new Sorhozzaad1());
        add(sorgomb1);
        
        JButton sorgomb2 = new JButton("Sor hozzáadása");
        sorgomb2.setBounds(955, 397, 117, 23);
        sorgomb2.addActionListener(new Sorhozzaad2());
        add(sorgomb2);

    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL kereses = new SQL();
                kereses.vevoi_lezarashoz(datum_mezo.getText(), tipus_mezo.getText());
                tablavege1 = table.getRowCount();
                tablavege2 = table_1.getRowCount();
                
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Visszair implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Db_iro visszair = new Db_iro();
                for(int szamlalo = 0; szamlalo < tablavege1; szamlalo++)
                {
                    if(table.getValueAt(szamlalo, 9) == null)
                    {
                        //table.setValueAt("", szamlalo, 9);
                    }
                    else
                    {
                        String sql = "update qualitydb.Vevoireklamacio_felelosok set  Lezarva = '"+ table.getValueAt(szamlalo, 9).toString() +"' where "
                                + "Datum = '"+ table.getValueAt(szamlalo, 0).toString() +"' and Cikkszam = '"+ table.getValueAt(szamlalo, 1).toString() 
                                +"' and Zarolt = '"+ table.getValueAt(szamlalo, 2).toString() +"' "
                                +" and Zarolt_db = "+ Integer.parseInt(table.getValueAt(szamlalo, 3).toString()) 
                                +" and Talalt_db = "+ Integer.parseInt(table.getValueAt(szamlalo, 4).toString()) 
                                +" and Muszaki_doku = '"+ table.getValueAt(szamlalo, 5).toString() +"' and Termeles = '"+ table.getValueAt(szamlalo, 6).toString() 
                                +"' and Felelos = '"+ table.getValueAt(szamlalo, 7).toString() +"' and Hatarido = '"+ table.getValueAt(szamlalo, 8).toString() +"'";
                        visszair.ujrair_vevoi(sql);
                    }
                }
                
                for(int szamlalo = 0; szamlalo < tablavege2; szamlalo++)
                {
                    if(table_1.getValueAt(szamlalo, 5) == null)
                    {
                        //table_1.setValueAt("", szamlalo, 5);
                    }
                    else
                    {
                        String sql = "update qualitydb.Vevoireklamacio_detekt set  Lezarva = '"+ table_1.getValueAt(szamlalo, 5).toString() +"' where "
                                + "Datum = '"+ table_1.getValueAt(szamlalo, 0).toString() +"' and Cikkszam = '"+ table_1.getValueAt(szamlalo, 1).toString() 
                                +"' and Intezkedes = '"+ table_1.getValueAt(szamlalo, 2).toString() +""                                             
                                +"' and Felelos = '"+ table_1.getValueAt(szamlalo, 3).toString() +"' and Hatarido = '"+ table_1.getValueAt(szamlalo, 4).toString() +"'";
                        visszair.ujrair_vevoi(sql);
                    }
                }
                if(tablavege1 < table.getRowCount())
                {
                    for(int szamlalo = tablavege1-1; szamlalo < table.getRowCount(); szamlalo++)
                    {
                        visszair.iro_vevoi_felelos(table.getValueAt(szamlalo, 0).toString(), table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString(), Integer.parseInt(table.getValueAt(szamlalo, 3).toString()),
                                Integer.parseInt(table.getValueAt(szamlalo, 4).toString()), table.getValueAt(szamlalo, 5).toString(), table.getValueAt(szamlalo, 6).toString(), 
                                table.getValueAt(szamlalo, 7).toString(), table.getValueAt(szamlalo, 8).toString());
                    }   
                }
                if(tablavege2 < table_1.getRowCount())
                {
                    for(int szamlalo = tablavege2-1; szamlalo < table_1.getRowCount(); szamlalo++)
                    {
                        visszair.iro_vevoi_intezkedes(table_1.getValueAt(szamlalo, 0).toString(), table_1.getValueAt(szamlalo, 1).toString(), table_1.getValueAt(szamlalo, 2).toString(),
                                table_1.getValueAt(szamlalo, 3).toString(), table_1.getValueAt(szamlalo, 4).toString());
                    }
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Veglegzar implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQL hanynapja = new SQL();
                Db_iro visszair = new Db_iro();
                String sql = "update qualitydb.Vevoireklamacio_alapadat set  Lezaras_ido = '"+ veglegido_mezo.getText() +"' where "
                        + "Datum = '"+ datum_mezo.getText() +"' and Tipus = '"+ tipus_mezo.getText() +"'";
                        
                visszair.ujrair_vevoi(sql);
                String query = "call qualitydb.vevoi_hanynapig(?,?)";        
                hanynapja.vevoi_napok(query, datum_mezo.getText(), tipus_mezo.getText(), datum_mezo.getText(), tipus_mezo.getText());        
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Sorhozzaad1 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {     
                DefaultTableModel model =(DefaultTableModel) table.getModel();
                model.addRow(new Object[]{table.getValueAt(0, 0).toString(), table.getValueAt(0, 1).toString(), "", "","","","","","",""});
                table.setModel(model);;
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Sorhozzaad2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                DefaultTableModel model =(DefaultTableModel) table_1.getModel();
                model.addRow(new Object[]{table_1.getValueAt(0, 0).toString(), table_1.getValueAt(0, 1).toString(), "", "","","","","","",""});
                table_1.setModel(model);              
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
