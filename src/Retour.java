import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Retour extends JPanel 
{
    static JTextField id_mezo;
    static JTextField datum_mezo;
    static JTextField beerkezett_mezo;
    static JTextField elteres_mezo;
    private JComboBox<String> projekt_box;
    private JComboBox<String> cikk_box;
    private JComboBox<String> javagy_box;
    static JTextField rma_mezo;
    static JTextField megjegyzes_mezo;
    static JTextField hova_mezo;
    static JTextField kiadas_mezo;
    static JTextField felelos_mezo;
    static JTextField teszt_mezo;
    static JTextField felelos2_mezo;
    static JTextField veg_mezo;
    static JTextField felelos3_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    static JTextField raktarra_mezo;
    static JTextField raktarradb_mezo;
    static JTextField selejt_mezo;
    private ArrayList<String> kivalasztott;

    /**
     * Create the panel.
     */
    public Retour() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Retour adatok");
        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
        lblNewLabel.setBounds(501, 11, 143, 24);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(29, 49, 46, 14);
        add(lblNewLabel_1);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(85, 46, 46, 20);
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JButton id_keresgomb = new JButton("Keresés");
        id_keresgomb.addActionListener(new ID());
        id_keresgomb.setBounds(141, 45, 89, 23);
        add(id_keresgomb);
        
        JLabel lblNewLabel_2 = new JLabel("Beérkezés dátuma");
        lblNewLabel_2.setBounds(29, 92, 112, 14);
        add(lblNewLabel_2);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(151, 89, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Vevő");
        lblNewLabel_3.setBounds(271, 92, 35, 14);
        add(lblNewLabel_3);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                      //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(316, 88, 143, 22);
        projekt_box.addActionListener(new Kivalaszt());
        add(projekt_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(501, 92, 46, 14);
        add(lblNewLabel_4);
        
        cikk_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_cikk));                         //combobox_tomb.getCombobox(ComboBox.vevoi_cikk)
        cikk_box.setBounds(557, 88, 261, 22);
        add(cikk_box);
        
        String[] vagy = {"Javítás", "Átmunkálás"};
        javagy_box = new JComboBox<String>(vagy);                       //vagy
        javagy_box.setBounds(877, 88, 132, 22);
        add(javagy_box);
        
        JLabel lblNewLabel_5 = new JLabel("Beérkezett mennyiség");
        lblNewLabel_5.setBounds(29, 144, 132, 14);
        add(lblNewLabel_5);
        
        beerkezett_mezo = new JTextField();
        beerkezett_mezo.setBounds(166, 141, 46, 20);
        add(beerkezett_mezo);
        beerkezett_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Eltérés");
        lblNewLabel_6.setBounds(222, 144, 46, 14);
        add(lblNewLabel_6);
        
        elteres_mezo = new JTextField();
        elteres_mezo.setBounds(278, 141, 46, 20);
        elteres_mezo.setText("0");
        add(elteres_mezo);
        elteres_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("RMA, RDP vagy reklamációs szám");
        lblNewLabel_7.setBounds(348, 144, 207, 14);
        add(lblNewLabel_7);
        
        rma_mezo = new JTextField();
        rma_mezo.setBounds(557, 141, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Megjegyzés");
        lblNewLabel_8.setBounds(666, 144, 84, 14);
        add(lblNewLabel_8);
        
        megjegyzes_mezo = new JTextField();
        megjegyzes_mezo.setBounds(744, 141, 428, 20);
        add(megjegyzes_mezo);
        megjegyzes_mezo.setColumns(10);
        
        JButton mentesgomb = new JButton("Mentés");
        mentesgomb.addActionListener(new Mentes());
        mentesgomb.setBounds(505, 200, 89, 23);
        add(mentesgomb);
        
        JLabel lblNewLabel_9 = new JLabel("Gépes/Kézi/Szilárd");
        lblNewLabel_9.setBounds(29, 253, 112, 14);
        add(lblNewLabel_9);
        
        hova_mezo = new JTextField();
        hova_mezo.setBounds(151, 250, 86, 20);
        add(hova_mezo);
        hova_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Kiadás dátuma");
        lblNewLabel_10.setBounds(278, 253, 89, 14);
        add(lblNewLabel_10);
        
        kiadas_mezo = new JTextField();
        kiadas_mezo.setBounds(377, 250, 86, 20);
        add(kiadas_mezo);
        kiadas_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Felelős");
        lblNewLabel_11.setBounds(501, 253, 46, 14);
        add(lblNewLabel_11);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(557, 250, 143, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Tesztre átadva dátuma");
        lblNewLabel_12.setBounds(29, 313, 132, 14);
        add(lblNewLabel_12);
        
        teszt_mezo = new JTextField();
        teszt_mezo.setBounds(166, 310, 86, 20);
        add(teszt_mezo);
        teszt_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Felelős");
        lblNewLabel_13.setBounds(303, 313, 46, 14);
        add(lblNewLabel_13);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.setBounds(377, 310, 132, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Végellenőrzésre átadva dátuma");
        lblNewLabel_14.setBounds(29, 370, 183, 14);
        add(lblNewLabel_14);
        
        veg_mezo = new JTextField();
        veg_mezo.setBounds(222, 367, 86, 20);
        add(veg_mezo);
        veg_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Felelős");
        lblNewLabel_15.setBounds(377, 370, 46, 14);
        add(lblNewLabel_15);
        
        felelos3_mezo = new JTextField();
        felelos3_mezo.setBounds(433, 367, 132, 20);
        add(felelos3_mezo);
        felelos3_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ratárra adás dátuma");
        lblNewLabel_16.setBounds(29, 418, 118, 14);
        add(lblNewLabel_16);
        
        raktarra_mezo = new JTextField();
        raktarra_mezo.setBounds(166, 415, 86, 20);
        add(raktarra_mezo);
        raktarra_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Raktárra adott db szám");
        lblNewLabel_17.setBounds(278, 418, 145, 14);
        add(lblNewLabel_17);
        
        raktarradb_mezo = new JTextField();
        raktarradb_mezo.setBounds(433, 415, 46, 20);
        add(raktarradb_mezo);
        raktarradb_mezo.setColumns(10);
        
        JLabel lblNewLabel_18 = new JLabel("Selejt");
        lblNewLabel_18.setBounds(522, 418, 46, 14);
        add(lblNewLabel_18);
        
        selejt_mezo = new JTextField();
        selejt_mezo.setBounds(578, 415, 46, 20);
        add(selejt_mezo);
        selejt_mezo.setColumns(10);
        
        JButton folyamat_gomb = new JButton("Folyamat felvisz");
        folyamat_gomb.addActionListener(new Folyamatok());
        folyamat_gomb.setBounds(505, 497, 119, 23);
        add(folyamat_gomb);
        
        kivalasztott = new ArrayList<String>();

    }
    
    class Mentes implements ActionListener                                                                                        //bevitt retour adatokat menti el
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Db_iro iras = new Db_iro();
                iras.iro_retour(datum_mezo.getText(), String.valueOf(projekt_box.getSelectedItem()), String.valueOf(cikk_box.getSelectedItem()), String.valueOf(javagy_box.getSelectedItem()), Integer.parseInt(beerkezett_mezo.getText()),
                        Integer.parseInt(elteres_mezo.getText()), rma_mezo.getText(), megjegyzes_mezo.getText());
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
                Urlap_torlo torles = new Urlap_torlo();
                torles.urlaptorles_retour(datum_mezo, beerkezett_mezo, elteres_mezo, rma_mezo, megjegyzes_mezo, hova_mezo, kiadas_mezo, felelos_mezo, teszt_mezo, felelos2_mezo, veg_mezo, felelos3_mezo, raktarra_mezo, raktarradb_mezo, selejt_mezo);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Folyamatok implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Db_iro iras = new Db_iro();
                if(hova_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Hiányzó adat!! \n", "Hiba üzenet", 2);
                }
                else
                {
                    String sql = "UPDATE qualitydb.Retour set Hova = '" + hova_mezo.getText() +"', Hova_datum = '" + kiadas_mezo.getText() +"', Hova_felelos = '" + felelos_mezo.getText() +"' where id = '" + id_mezo.getText() +"'"; 
                    iras.iro_retour_ido(sql);
                    if(teszt_mezo.getText().equals(""))
                    {
                        
                    }
                    else
                    {
                        String sql2 = "UPDATE qualitydb.Retour set Teszt_datum = '" + teszt_mezo.getText() +"', Teszt_felelos = '" + felelos2_mezo.getText() +"' where id = '" + id_mezo.getText() +"'"; 
                        iras.iro_retour_ido(sql2);
                    }
                    if(veg_mezo.getText().equals(""))
                    {
                        
                    }
                    else
                    {
                        String sql2 = "UPDATE qualitydb.Retour set Vegell_datum = '" + veg_mezo.getText() +"', Vegell_felelos = '" + felelos3_mezo.getText() +"' where id = '" + id_mezo.getText() +"'"; 
                        iras.iro_retour_ido(sql2);
                    }
                    if(raktarra_mezo.getText().equals(""))
                    {
                        
                    }
                    else
                    {
                        String sql2 = "UPDATE qualitydb.Retour set raktar_datum = '" + raktarra_mezo.getText() +"', Raktar_db = '" + raktarradb_mezo.getText() +"', Selejt = '" + selejt_mezo.getText() +"'"
                                + " where id = '" + id_mezo.getText() +"'"; 
                        iras.iro_retour_ido(sql2);
                    }
                }
                Urlap_torlo torles = new Urlap_torlo();
                torles.urlaptorles_retour(datum_mezo, beerkezett_mezo, elteres_mezo, rma_mezo, megjegyzes_mezo, hova_mezo, kiadas_mezo, felelos_mezo, teszt_mezo, felelos2_mezo, veg_mezo, felelos3_mezo, raktarra_mezo, raktarradb_mezo, selejt_mezo);
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class ID implements ActionListener                                                                                        //ID alapján visszatölti az adatokat
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL visszair = new SQL();                                                               
                visszair.retour_vissza(id_mezo.getText());
            }
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    SQL visszair = new SQL();
                    visszair.retour_vissza(id_mezo.getText());
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
    
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                
                    for(int szamlalo = 0; szamlalo < combobox_tomb.getCombobox2(ComboBox.vevoi_cikk).length; szamlalo++)
                    {
                        if(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo].contains(keresett))
                        {
                            kivalasztott.add(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo]); 
                        }
                    }
                    
                    String[] ujmodell = new String[kivalasztott.size()];
                    for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                    {
                        ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                    }
                    model = new DefaultComboBoxModel<>(ujmodell);
                
                cikk_box.setModel(model);
                kivalasztott.clear();
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
