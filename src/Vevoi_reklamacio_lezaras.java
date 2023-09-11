import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Vevoi_reklamacio_lezaras extends JPanel 
{
    private JTextField datum_mezo;
    private JTextField tipus_mezo;
    static JTable table;
    static JTable table_1;
    private JTextField veglegido_mezo;
    private int tablavege1 = 0;
    private int tablavege2 = 0;
    private JTextField id_mezo;
    static JTextField koltseg_1;
    static JTextField koltseg_2;
    static JTextField koltseg_3;
    static JTextField koltseg_4;

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
        lblNewLabel_1.setBounds(453, 72, 46, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(509, 69, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(443, 103, 56, 14);
        add(lblNewLabel_2);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(509, 100, 86, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keres");
        keres_gomb.setBounds(509, 162, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 205, 1054, 127);
        add(scroll);
        
        table_1 = new JTable();
        JScrollPane scroll2 = new JScrollPane(table_1);
        scroll2.setBounds(10, 356, 1054, 123);
        add(scroll2);
        
        JButton lezar_gomb = new JButton("Lezárás");
        lezar_gomb.setBounds(506, 509, 89, 23);
        lezar_gomb.addActionListener(new Visszair());
        add(lezar_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Vevői reklamáció lezárás időpontja");
        lblNewLabel_3.setBounds(291, 565, 208, 14);
        add(lblNewLabel_3);
        
        veglegido_mezo = new JTextField();
        veglegido_mezo.setBounds(509, 562, 86, 20);
        add(veglegido_mezo);
        veglegido_mezo.setColumns(10);
        
        JButton veglegzar_gomb = new JButton("Reklamáció zárása");
        veglegzar_gomb.setBounds(606, 561, 157, 23);
        veglegzar_gomb.addActionListener(new Veglegzar());
        add(veglegzar_gomb);
        
        JButton sorgomb1 = new JButton("Sor hozzáadása");
        sorgomb1.setBounds(1084, 256, 117, 23);
        sorgomb1.addActionListener(new Sorhozzaad1());
        add(sorgomb1);
        
        JButton sorgomb2 = new JButton("Sor hozzáadása");
        sorgomb2.setBounds(1084, 402, 117, 23);
        sorgomb2.addActionListener(new Sorhozzaad2());
        add(sorgomb2);
        
        JLabel lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(453, 134, 46, 14);
        add(lblNewLabel_4);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(509, 131, 86, 20);
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Kiválasztott felelős törlése");
        lblNewLabel_5.setBounds(747, 513, 157, 14);
        add(lblNewLabel_5);
        
        JButton torles_gomb = new JButton("Törlés");
        torles_gomb.addActionListener(new Felelos_torles());
        torles_gomb.setBounds(914, 509, 89, 23);
        add(torles_gomb);
        
        JLabel lblNewLabel_6 = new JLabel("Belső költség");
        lblNewLabel_6.setBounds(399, 630, 90, 14);
        add(lblNewLabel_6);
        
        koltseg_1 = new JTextField();
        koltseg_1.setBounds(509, 627, 86, 20);
        koltseg_1.setText("0");
        add(koltseg_1);
        koltseg_1.setColumns(10);
        
        koltseg_2 = new JTextField();
        koltseg_2.setBounds(509, 658, 86, 20);
        koltseg_2.setText("0");
        add(koltseg_2);
        koltseg_2.setColumns(10);
        
        koltseg_3 = new JTextField();
        koltseg_3.setBounds(509, 689, 86, 20);
        koltseg_3.setText("0");
        add(koltseg_3);
        koltseg_3.setColumns(10);
        
        koltseg_4 = new JTextField();
        koltseg_4.setBounds(509, 720, 86, 20);
        koltseg_4.setText("0");
        add(koltseg_4);
        koltseg_4.setColumns(10);
        
        JButton koltseghozza_gomb = new JButton("Költség hozzáadása");
        koltseghozza_gomb.addActionListener(new Koltseg());
        koltseghozza_gomb.setBounds(635, 668, 145, 23);
        add(koltseghozza_gomb);
        
        JLabel lblNewLabel_7 = new JLabel("Fuvar költség");
        lblNewLabel_7.setBounds(399, 661, 90, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Selejt költség");
        lblNewLabel_8.setBounds(399, 692, 90, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Egyéb költség");
        lblNewLabel_9.setBounds(399, 723, 90, 14);
        add(lblNewLabel_9);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL kereses = new SQL();
                kereses.vevoi_lezarashoz(datum_mezo.getText(), tipus_mezo.getText(), id_mezo.getText());
                tablavege1 = table.getRowCount();
                tablavege2 = table_1.getRowCount();
                
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
    
    class Koltseg implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Db_iro iras = new Db_iro();
                String sql = "update qualitydb.Vevoireklamacio_alapadat set Belso_koltseg = '"+ koltseg_1.getText() +"', Fuvar_koltseg = '"+ koltseg_2.getText() +"', Selejt_koltseg = '"+ koltseg_3.getText()
                +"', Egyeb_koltseg = '"+ koltseg_4.getText() +"' where ID = '"+  id_mezo.getText() +"'";
                iras.ujrair_vevoi(sql);               
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
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    SQL kereses = new SQL();
                    kereses.vevoi_lezarashoz(datum_mezo.getText(), tipus_mezo.getText(), id_mezo.getText());
                    tablavege1 = table.getRowCount();
                    tablavege2 = table_1.getRowCount();
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
                    if(table.getValueAt(szamlalo, 10) == null || table.getValueAt(szamlalo, 10).equals(""))
                    {
                        //table.setValueAt("", szamlalo, 9);
                    }
                    else
                    {
                        String sql = "update qualitydb.Vevoireklamacio_felelosok set  Lezarva = '"+ table.getValueAt(szamlalo, 10).toString() +"' where "
                                + "Datum = '"+ table.getValueAt(szamlalo, 1).toString() +"' and Cikkszam = '"+ table.getValueAt(szamlalo, 2).toString() 
                                +"' and Zarolt = '"+ table.getValueAt(szamlalo, 3).toString() +"' "
                                +" and Zarolt_db = "+ Integer.parseInt(table.getValueAt(szamlalo, 4).toString()) 
                                +" and Talalt_db = "+ Integer.parseInt(table.getValueAt(szamlalo, 5).toString()) 
                                +" and Muszaki_doku = '"+ table.getValueAt(szamlalo, 6).toString() +"' and Termeles = '"+ table.getValueAt(szamlalo, 7).toString() 
                                +"' and Felelos = '"+ table.getValueAt(szamlalo, 8).toString() +"' and Hatarido = '"+ table.getValueAt(szamlalo, 9).toString() +"'";
                        visszair.ujrair_vevoi(sql);
                    }
                }
                
                for(int szamlalo = 0; szamlalo < tablavege2; szamlalo++)
                {
                    if(table_1.getValueAt(szamlalo, 6) == null || table_1.getValueAt(szamlalo, 6).equals(""))
                    {
                        //table_1.setValueAt("", szamlalo, 5);
                    }
                    else
                    {
                        String sql = "update qualitydb.Vevoireklamacio_detekt set  Lezarva = '"+ table_1.getValueAt(szamlalo, 6).toString() +"' where "
                                + "Datum = '"+ table_1.getValueAt(szamlalo, 1).toString() +"' and Cikkszam = '"+ table_1.getValueAt(szamlalo, 2).toString() 
                                +"' and Intezkedes = '"+ table_1.getValueAt(szamlalo, 3).toString() +""                                             
                                +"' and Felelos = '"+ table_1.getValueAt(szamlalo, 4).toString() +"' and Hatarido = '"+ table_1.getValueAt(szamlalo, 5).toString() +"'";
                        visszair.ujrair_vevoi(sql);
                    }
                }
                if(tablavege1 < table.getRowCount())
                {
                    for(int szamlalo = tablavege1; szamlalo < table.getRowCount(); szamlalo++)
                    {
                        visszair.iro_vevoi_felelos(table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString(), table.getValueAt(szamlalo, 3).toString(), Integer.parseInt(table.getValueAt(szamlalo, 4).toString()),
                                Integer.parseInt(table.getValueAt(szamlalo, 5).toString()), table.getValueAt(szamlalo, 6).toString(), table.getValueAt(szamlalo, 7).toString(), 
                                table.getValueAt(szamlalo, 8).toString(), table.getValueAt(szamlalo, 9).toString());
                    }
                    visszair.iro_vevoi_id(table.getValueAt(0, 1).toString(), table.getValueAt(0, 2).toString());
                }
                if(tablavege2 < table_1.getRowCount())
                {
                    for(int szamlalo = tablavege2; szamlalo < table_1.getRowCount(); szamlalo++)
                    {
                        visszair.iro_vevoi_intezkedes(table_1.getValueAt(szamlalo, 1).toString(), table_1.getValueAt(szamlalo, 2).toString(), table_1.getValueAt(szamlalo, 3).toString(),
                                table_1.getValueAt(szamlalo, 4).toString(), table_1.getValueAt(szamlalo, 5).toString());
                    }
                    visszair.iro_vevoi_id(table_1.getValueAt(0, 1).toString(), table_1.getValueAt(0, 2).toString());
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();     
                Foablak.frame.setCursor(null);
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
                        + "Datum = '"+ table.getValueAt(0, 1).toString() +"' and Tipus = '"+ table.getValueAt(0, 2).toString() +"'";
                        
                visszair.ujrair_vevoi(sql);
                String query = "call qualitydb.vevoi_hanynapig(?,?)";        
                hanynapja.vevoi_napok(query, table.getValueAt(0, 1).toString(), table.getValueAt(0, 2).toString(), table.getValueAt(0, 1).toString(), table.getValueAt(0, 2).toString());        
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();;
                Foablak.frame.setCursor(null);
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
                model.addRow(new Object[]{table.getValueAt(0, 0).toString(), table.getValueAt(0, 1).toString(), table.getValueAt(0, 2).toString(), "","","","","","",""});
                table.setModel(model);;
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
    
    class Sorhozzaad2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                DefaultTableModel model =(DefaultTableModel) table_1.getModel();
                model.addRow(new Object[]{table_1.getValueAt(0, 0).toString(), table_1.getValueAt(0, 1).toString(), table_1.getValueAt(0, 2).toString(), "","","","","","",""});
                table_1.setModel(model);              
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
    
    class Felelos_torles implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Db_iro visszair = new Db_iro();
                int szamlalo = table.getSelectedRow();
                if(szamlalo > -1)
                {
                    String sql = "delete from qualitydb.Vevoireklamacio_felelosok where felelos = '"+ table.getValueAt(szamlalo, 8).toString() +"'"
                            +" and id ='"+ table.getValueAt(szamlalo, 0).toString() +"'";
                    visszair.ujrair_vevoi(sql);
                }
                szamlalo = table_1.getSelectedRow();
                if(szamlalo > -1)
                {
                    
                    String sql = "delete from qualitydb.Vevoireklamacio_detekt where felelos = '"+ table.getValueAt(szamlalo, 4).toString() +"'"
                            +" and id ='"+ table.getValueAt(szamlalo, 0).toString() +"'";
                    visszair.ujrair_vevoi(sql);
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Törlés sikeres!", "Infó", 1);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                Foablak.frame.setCursor(null);
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
