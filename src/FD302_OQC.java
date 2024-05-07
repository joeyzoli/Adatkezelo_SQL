import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class FD302_OQC extends JPanel {
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField kod_mezo;
    private JTextField dm_telepitett;
    private JTextField dm_ok1;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> hiba_box;
    private JComboBox<String> hibakategoria_box;
    private static Long timer_start;
    private JTextField dm_max;
    private JTextField dm_ok2;
    private JTextField dm_hopihe;
    private JTextField dm_ok3;
    private JTextField article_mezo;
    private JTextField ipei_mezo;
    private JTextField firmware_mezo;
    private JTextField akku_mezo;
    private JTextField homerseklet_mezo;
    private JTextField kod2_mezo;
    private JTextField egyezes_mezo;
    private JComboBox<String> fehler_box;

    /**
     * Create the panel.
     */
    public FD302_OQC() {
        setLayout(null);
        ComboBox combobox_tomb = new ComboBox();
        
        JLabel lblNewLabel = new JLabel("Gyűjtődoboz");
        lblNewLabel.setBounds(38, 35, 93, 14);
        add(lblNewLabel);
        
        raklap_mezo = new JTextField();
        raklap_mezo.setBounds(141, 32, 243, 20);
        add(raklap_mezo);
        raklap_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Szériaszám doboz");
        lblNewLabel_1.setBounds(38, 85, 105, 14);
        add(lblNewLabel_1);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(167, 82, 217, 20);
        doboz_mezo.addKeyListener(new Enter());
        add(doboz_mezo);
        doboz_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Fehler_1");
        lblNewLabel_2.setBounds(38, 126, 78, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("2 D kód a kijelzőn");
        lblNewLabel_3.setBounds(38, 175, 117, 14);
        add(lblNewLabel_3);
        
        kod_mezo = new JTextField();
        kod_mezo.setBounds(167, 172, 217, 20);
        add(kod_mezo);
        kod_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("DM érték - telepített");
        lblNewLabel_4.setBounds(38, 219, 117, 14);
        add(lblNewLabel_4);
        
        dm_telepitett = new JTextField();
        dm_telepitett.setBounds(167, 216, 93, 20);
        add(dm_telepitett);
        dm_telepitett.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("min: -0,1");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_5.setBounds(270, 213, 55, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("max: 0,2");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_6.setBounds(270, 222, 55, 14);
        add(lblNewLabel_6);
        
        dm_ok1 = new JTextField();
        dm_ok1.setBounds(335, 216, 49, 20);
        add(dm_ok1);
        dm_ok1.setColumns(10);        
        
        JLabel lblNewLabel_19 = new JLabel("Hibacsoport");
        lblNewLabel_19.setBounds(38, 361, 103, 14);
        add(lblNewLabel_19);
        
        String[] hibacsoport = {"","Címke","Funkció","Papír","Tartozék","Termék","Szerelés"};
        hibacsoport_box = new JComboBox<String>(hibacsoport);                                              //hibacsoport
        hibacsoport_box.setBounds(167, 357, 215, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_20 = new JLabel("Hiba");
        lblNewLabel_20.setBounds(38, 406, 46, 14);
        add(lblNewLabel_20);
        
        hiba_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hiba_box.setBounds(167, 402, 215, 22);
        add(hiba_box);
        
        JLabel lblNewLabel_21 = new JLabel("Hiba kategória");
        lblNewLabel_21.setBounds(38, 453, 103, 14);
        add(lblNewLabel_21);
        
        String[] kategoria = {"","Kritikus hiba","Súlyos hiba","Enyhe hiba"};
        hibakategoria_box = new JComboBox<String>(kategoria);                                    //kategoria
        hibakategoria_box.setBounds(167, 449, 215, 22);
        add(hibakategoria_box);
        
        JLabel lblNewLabel_7 = new JLabel("DM érték - Max fűtés");
        lblNewLabel_7.setBounds(38, 261, 117, 14);
        add(lblNewLabel_7);
        
        dm_max = new JTextField();
        dm_max.setBounds(167, 258, 93, 20);
        add(dm_max);
        dm_max.setColumns(10);
        
        dm_ok2 = new JTextField();
        dm_ok2.setBounds(335, 258, 49, 20);
        add(dm_ok2);
        dm_ok2.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel(">1,6");
        lblNewLabel_8.setBounds(270, 261, 46, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("DM érték - \"Hópihe\"");
        lblNewLabel_9.setBounds(38, 310, 117, 14);
        add(lblNewLabel_9);
        
        dm_hopihe = new JTextField();
        dm_hopihe.setBounds(167, 307, 93, 20);
        add(dm_hopihe);
        dm_hopihe.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("min: -0,1");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_10.setBounds(270, 305, 55, 14);
        add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("max: 0,2");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_11.setBounds(270, 315, 55, 14);
        add(lblNewLabel_11);
        
        dm_ok3 = new JTextField();
        dm_ok3.setBounds(335, 305, 49, 20);
        add(dm_ok3);
        dm_ok3.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Article Number");
        lblNewLabel_12.setBounds(489, 35, 105, 14);
        add(lblNewLabel_12);
        
        article_mezo = new JTextField();
        article_mezo.setBounds(665, 32, 125, 20);
        add(article_mezo);
        article_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("IPEI (GUI-ból)");
        lblNewLabel_13.setBounds(489, 85, 105, 14);
        add(lblNewLabel_13);
        
        ipei_mezo = new JTextField();
        ipei_mezo.setBounds(665, 82, 125, 20);
        add(ipei_mezo);
        ipei_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Firmware verzió (GUI-ból)");
        lblNewLabel_14.setBounds(489, 126, 153, 14);
        add(lblNewLabel_14);
        
        firmware_mezo = new JTextField();
        firmware_mezo.setBounds(665, 123, 125, 20);
        add(firmware_mezo);
        firmware_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Akkumulátor (GUI-ból)");
        lblNewLabel_15.setBounds(489, 175, 153, 14);
        add(lblNewLabel_15);
        
        akku_mezo = new JTextField();
        akku_mezo.setBounds(665, 172, 125, 20);
        add(akku_mezo);
        akku_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Hőmérséklet (GUI-ból)");
        lblNewLabel_16.setBounds(489, 219, 153, 14);
        add(lblNewLabel_16);
        
        homerseklet_mezo = new JTextField();
        homerseklet_mezo.setBounds(665, 216, 125, 20);
        add(homerseklet_mezo);
        homerseklet_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("2D kód a címkéről");
        lblNewLabel_17.setBounds(489, 261, 117, 14);
        add(lblNewLabel_17);
        
        kod2_mezo = new JTextField();
        kod2_mezo.setBounds(665, 258, 125, 20);
        add(kod2_mezo);
        kod2_mezo.setColumns(10);
        
        JLabel lblNewLabel_18 = new JLabel("Szériák egyezése");
        lblNewLabel_18.setBounds(489, 310, 117, 14);
        add(lblNewLabel_18);
        
        egyezes_mezo = new JTextField();
        egyezes_mezo.setBounds(665, 307, 125, 20);
        add(egyezes_mezo);
        egyezes_mezo.setColumns(10);
        
        JLabel lblNewLabel_22 = new JLabel("Megjegyzés");
        lblNewLabel_22.setBounds(489, 361, 93, 14);
        add(lblNewLabel_22);
        
        JTextArea megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        megjegyzes_mezo.setBounds(592, 356, 198, 111);
        add(megjegyzes_mezo);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(701, 525, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_23 = new JLabel("Ellenőrzött mennyiség");
        lblNewLabel_23.setBounds(346, 529, 143, 14);
        add(lblNewLabel_23);
        
        JLabel mennyiseg_label = new JLabel("0");
        mennyiseg_label.setBounds(499, 529, 46, 14);
        add(mennyiseg_label);
        
        JButton torles_gomb = new JButton("Törlés");
        torles_gomb.setBounds(82, 525, 89, 23);
        add(torles_gomb);
        
        String[] eredmeny = {"-", "OK", "NOK"};
        fehler_box = new JComboBox<String> (eredmeny);                  //eredmeny
        fehler_box.setBounds(167, 122, 136, 22);
        add(fehler_box);

    }  
    
    static public float measureTime(boolean run)                    //idõmérõ metódus
    {
        long current_time = System.nanoTime();                      //a rendszeridõt nekiadjuk egy változónak
                
        if (run == true)                                            //ha igazra állítjuk elindul
        {
                timer_start = System.nanoTime();                    //idõzítõ indulási értéke a rendszer aktuális ideje
                return (-1.0f);
        }
        else
        {
            long elapsed_time = current_time - timer_start;         //ha false lesz az érték
            return (elapsed_time);                                  //visszatér a különbséggel
        }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                measureTime(true);
                System.out.println("Elindult a mérés");
            }       
        }
        @Override
        public void keyTyped(KeyEvent e){                                                 //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom         
        }
        @Override
        public void keyReleased(KeyEvent e){                                              //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom           
        }    
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {/*
                if(raklap_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a gyűjtődoboz!", "Hiba üzenet", 2);
                }
                else if(doboz_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a szériaszám doboz!", "Hiba üzenet", 2);
                }
                else if(termek_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a szériaszám termék!", "Hiba üzenet", 2);
                }
                else if(guide_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a quick guide!", "Hiba üzenet", 2);
                }
                else if(firmware_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a firmware verzió!", "Hiba üzenet", 2);
                }
                else if(jel2_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a jel 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(jel5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a jel 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(tx_gui2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a TX GUI 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(tx_gui5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a TX GUI 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(rx_gui2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a RX GUI 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(rx_gui5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a RX GUI 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(tx_iperf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a TX iperf 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(tx_iperf5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a TX iperf 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(rx_iperf2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a RX iperf 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(rx_iperf5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a RX iperf 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(bandwith2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a Bandwith 2.4 Ghz!", "Hiba üzenet", 2);
                }
                else if(bandwith5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a Bandwith 5 Ghz!", "Hiba üzenet", 2);
                }
                else if(phone_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a Phone!", "Hiba üzenet", 2);
                }
                else if(download_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a Download!", "Hiba üzenet", 2);
                }
                else if(upload_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nincs megadva a Upload!", "Hiba üzenet", 2);
                }
                else
                {
                    /*end = System.currentTimeMillis();
                    long millis = end - start;                    
                    int seconds = (int) (millis / 1000) % 60 ;
                    int minutes = (int) ((millis / (1000*60)) % 60);
                    int hours   = (int) ((millis / (1000*60*60)) % 24);                    
                    System.out.println(hours +":"+ minutes +":"+ seconds); */                   
                    /*int petifele = (int) (measureTime(false) / 1000000);
                    //System.out.println("Azzal számolva: "+ (petifele/ (1000*60*60) % 24)+" óra "+ ((petifele / (1000*60)) % 60) +" perc "+ (petifele / 1000) % 60  +" másodperc");
                    String tesztido = String.valueOf( (petifele/ (1000*60*60) % 24))+":"+ String.valueOf(((petifele / (1000*60)) % 60)) +":"+ String.valueOf((petifele / 1000) % 60);
                    System.out.println(tesztido);
                    String kritikus = "";
                    String sulyos = "";
                    String enyhe = "";
                    if(String.valueOf(hibakategoria_box.getSelectedItem()).equals("Kritikus hiba"))
                    {
                        kritikus = "X";
                    }
                    if(String.valueOf(hibakategoria_box.getSelectedItem()).equals("Súlyos hiba"))
                    {
                        sulyos = "X";
                    }
                    if(String.valueOf(hibakategoria_box.getSelectedItem()).equals("Enyhe hiba"))
                    {
                        enyhe = "X";
                    }
                    String formazo = raklap_mezo.getText().replace(" ","");
                    SimpleDateFormat rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                                                          //
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String sql = "INSERT INTO qualitydb.OQC_FB7530 (Datum, Tesztelo,Tipus,Raklapszam,Szeriaszam_doboz,Szeriaszam_termek,Szeriaszam_quick,Egyezes,Teszt_tipusa,Jelerosseg,Wlan_tx_gui,Wlan_rx_gui,"
                            + "Wlan_tx_iperf,Wlan_rx_iperf,Wlan_Bandwith,Jelerosseg_5,Wlan_tx_gui_5,Wlan_rx_gui_5,Wlan_tx_iperf_5,Wlan_rx_iperf_5,Wlan_Bandwith_5,Firmware,Phone,Download,Upload,Hiba,Hibacsoport,"
                            + "Megjegyzes,Kritikus_hiba, Sulyos_hiba,Enyhe_hiba,Rogzites_ido, Teszt_ido) VALUES('"+ datum_mezo.getText() +"','"+ String.valueOf(ellenor_box.getSelectedItem()) +"',"
                            + "'"+ String.valueOf(tipus_box.getSelectedItem()) +"','"+ formazo +"','"+ doboz_mezo.getText() +"','"+ termek_mezo.getText() +"','"+ guide_mezo.getText() +"',"
                            + "'"+ egyezes_mezo.getText() +"','"+ String.valueOf(teszttipus_box.getSelectedItem()) +"','"+ jel2_mezo.getText() +"','"+ tx_gui2.getText() +"','"+ rx_gui2.getText() +"',"
                            + "'"+ tx_iperf2.getText() +"','"+ rx_iperf2.getText() +"','"+ bandwith2.getText() +"','"+ jel5.getText()+"','"+ tx_gui5.getText() +"','"+ rx_gui5.getText() +"',"
                            + "'"+ tx_iperf5.getText() +"','"+ rx_iperf5.getText() +"','"+ bandwith5.getText() +"','"+ firmware_mezo.getText() +"','"+ phone_mezo.getText() +"','"+ download_mezo.getText() +"',"
                            + "'"+ upload_mezo.getText() +"','"+ String.valueOf(hiba_box.getSelectedItem()) +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ megjegyzes_mezo.getText() +"',"
                            + "'"+ kritikus +"','"+ sulyos +"','"+ enyhe +"','"+ rogzites.format(timestamp) +"','"+ tesztido +"')";
                    SQA_SQL ment = new SQA_SQL();
                    ment.mindenes(sql);
                    
                    visszaallit();
                    int mennyiseg = Integer.valueOf(mennyiseg_label.getText());
                    mennyiseg++;
                    mennyiseg_label.setText(String.valueOf(mennyiseg));
                }
                */
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
