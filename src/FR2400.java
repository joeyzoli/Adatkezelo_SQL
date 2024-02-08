import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class FR2400 extends JPanel {
    
    private JTextField datum_mezo;
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField termek_mezo;
    private JTextField egyezes_mezo;
    private JTextField guide_mezo;
    private JTextField firmware_mezo;
    private JTextField tx_gui2;
    private JTextField tx_gui5;
    private JTextField rx_gui2;
    private JTextField rx_gui5;
    private JTextField tx_iperf2;
    private JTextField tx_iperf5;
    private JTextField rx_iperf2;
    private JTextField rx_iperf5;
    private JTextField bandwith2;
    private JTextField bandwith5;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> ellenor_box ;
    private JComboBox<String> tipus_box;
    private JComboBox<String> teszttipus_box;
    private JComboBox<String> hiba_box;
    private JComboBox<String> hibakategoria_box;
    private JTextArea megjegyzes_mezo;
    private JLabel mennyiseg_label;
    private static Long timer_start;
    private JTextField wlanmac_back;
    private JTextField wlankey_back;
    private JTextField wlanmac_gui;
    private JTextField wlankey_gui;
    private JTextField egyezes2_mezo;
    private JTextField egyezes3_mezo;

    /**
     * Create the panel.
     */
    public FR2400() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("FR2400 OQC adatgyűjtő");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(499, 29, 188, 14);
        add(lblNewLabel);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_1 = new JLabel("Tesztelő");
        lblNewLabel_1.setBounds(47, 79, 68, 14);
        add(lblNewLabel_1);
        
        ComboBox combobox_tomb = new ComboBox();
        ellenor_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.ellenorok));                                                  //combobox_tomb.getCombobox2(ComboBox.ellenorok)
        ellenor_box.setSelectedIndex(1);
        ellenor_box.setBounds(125, 75, 208, 22);
        add(ellenor_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum");
        lblNewLabel_2.setBounds(384, 79, 46, 14);
        add(lblNewLabel_2);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(454, 76, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Típus");
        lblNewLabel_3.setBounds(583, 79, 46, 14);
        add(lblNewLabel_3);
        
        String[] tipus = {"FR2400","FR2400 INTER"};
        tipus_box = new JComboBox<String> (tipus);                                                   //tipus
        tipus_box.setBounds(639, 75, 150, 22);
        add(tipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Teszt típus");
        lblNewLabel_4.setBounds(874, 79, 91, 14);
        add(lblNewLabel_4);
        
        String[] teszttipus = {"F","V"};
        teszttipus_box = new JComboBox<String> (teszttipus);                                              //teszttipus
        teszttipus_box.setBounds(975, 75, 52, 22);
        add(teszttipus_box);
        
        JLabel lblNewLabel_5 = new JLabel("Gyűjtődoboz száma");
        lblNewLabel_5.setBounds(47, 175, 115, 14);
        add(lblNewLabel_5);
        
        raklap_mezo = new JTextField();
        raklap_mezo.setBounds(188, 172, 288, 20);
        add(raklap_mezo);
        raklap_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Szériaszám doboz");
        lblNewLabel_6.setBounds(47, 220, 115, 14);
        add(lblNewLabel_6);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(188, 217, 144, 20);
        doboz_mezo.addKeyListener(new Enter2());
        add(doboz_mezo);
        doboz_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Szériaszám termék");
        lblNewLabel_7.setBounds(47, 260, 115, 14);
        add(lblNewLabel_7);
        
        termek_mezo = new JTextField();
        termek_mezo.setBounds(188, 257, 144, 20);
        termek_mezo.addKeyListener(new Enter());
        add(termek_mezo);
        termek_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Szériák egyezése");
        lblNewLabel_8.setBounds(47, 305, 115, 14);
        add(lblNewLabel_8);
        
        egyezes_mezo = new JTextField();       
        egyezes_mezo.setEditable(false);
        egyezes_mezo.setBounds(188, 302, 86, 20);
        add(egyezes_mezo);
        egyezes_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("ARTICLE NUMBER");
        lblNewLabel_9.setBounds(47, 344, 168, 14);
        add(lblNewLabel_9);
        
        guide_mezo = new JTextField();
        guide_mezo.setBounds(188, 341, 288, 20);
        add(guide_mezo);
        guide_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Firmware verzió");
        lblNewLabel_10.setBounds(47, 382, 115, 14);
        add(lblNewLabel_10);
        
        firmware_mezo = new JTextField();
        firmware_mezo.setBounds(188, 379, 288, 20);
        add(firmware_mezo);
        firmware_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Wlan TX (Mbit\\s) on GUI");
        lblNewLabel_12.setBounds(583, 175, 150, 14);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Wlan RX (Mbit\\s) on GUI");
        lblNewLabel_13.setBounds(583, 220, 150, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Wlan TX (Mbit\\s) on iperf");
        lblNewLabel_14.setBounds(583, 260, 150, 14);
        add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Wlan RX (Mbit\\s) on iperf");
        lblNewLabel_15.setBounds(583, 305, 150, 14);
        add(lblNewLabel_15);
        
        JLabel lblNewLabel_16 = new JLabel("Wlan Bandwith (Mhz)");
        lblNewLabel_16.setBounds(583, 344, 150, 14);
        add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("2,4 Ghz");
        lblNewLabel_17.setBounds(792, 136, 46, 14);
        add(lblNewLabel_17);
        
        JLabel lblNewLabel_18 = new JLabel("5 Ghz");
        lblNewLabel_18.setBounds(880, 136, 46, 14);
        add(lblNewLabel_18);
        
        tx_gui2 = new JTextField();
        tx_gui2.setBounds(786, 172, 52, 20);
        tx_gui2.addKeyListener(new TX_gui2());
        add(tx_gui2);
        tx_gui2.setColumns(10);
        
        tx_gui5 = new JTextField();
        tx_gui5.setBounds(874, 172, 52, 20);
        tx_gui5.addKeyListener(new TX_gui5());
        add(tx_gui5);
        tx_gui5.setColumns(10);
        
        rx_gui2 = new JTextField();
        rx_gui2.addKeyListener(new RX_gui2());
        rx_gui2.setBounds(786, 217, 52, 20);
        add(rx_gui2);
        rx_gui2.setColumns(10);
        
        rx_gui5 = new JTextField();
        rx_gui5.addKeyListener(new RX_gui5());
        rx_gui5.setBounds(874, 217, 52, 20);
        add(rx_gui5);
        rx_gui5.setColumns(10);
        
        tx_iperf2 = new JTextField();
        tx_iperf2.addKeyListener(new TX_iperf2());
        tx_iperf2.setBounds(786, 257, 52, 20);
        add(tx_iperf2);
        tx_iperf2.setColumns(10);
        
        tx_iperf5 = new JTextField();
        tx_iperf5.addKeyListener(new TX_iperf5());
        tx_iperf5.setBounds(874, 257, 52, 20);
        add(tx_iperf5);
        tx_iperf5.setColumns(10);
        
        rx_iperf2 = new JTextField();
        rx_iperf2.addKeyListener(new RX_iperf2());
        rx_iperf2.setBounds(786, 302, 52, 20);
        add(rx_iperf2);
        rx_iperf2.setColumns(10);
        
        rx_iperf5 = new JTextField();
        rx_iperf5.addKeyListener(new RX_iperf5());
        rx_iperf5.setBounds(874, 302, 52, 20);
        add(rx_iperf5);
        rx_iperf5.setColumns(10);
        
        bandwith2 = new JTextField();
        bandwith2.addKeyListener(new Bandwith2());
        bandwith2.setBounds(786, 341, 52, 20);
        add(bandwith2);
        bandwith2.setColumns(10);
        
        bandwith5 = new JTextField();
        bandwith5.addKeyListener(new Bandwith5());
        bandwith5.setBounds(874, 341, 52, 20);
        add(bandwith5);
        bandwith5.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Hibacsoport");
        lblNewLabel_19.setBounds(47, 441, 103, 14);
        add(lblNewLabel_19);
        
        String[] hibacsoport = {"","Címke","Funkció","Papír","Tartozék","Termék","Szerelés"};
        hibacsoport_box = new JComboBox<String>(hibacsoport);                                              //hibacsoport
        hibacsoport_box.setBounds(188, 437, 215, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_20 = new JLabel("Hiba");
        lblNewLabel_20.setBounds(47, 488, 46, 14);
        add(lblNewLabel_20);
        
        hiba_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hiba_box.setBounds(188, 484, 215, 22);
        add(hiba_box);
        
        JLabel lblNewLabel_21 = new JLabel("Hiba kategória");
        lblNewLabel_21.setBounds(47, 531, 103, 14);
        add(lblNewLabel_21);
        
        String[] kategoria = {"","Kritikus hiba","Súlyos hiba","Enyhe hiba"};
        hibakategoria_box = new JComboBox<String>(kategoria);                                    //kategoria
        hibakategoria_box.setBounds(188, 527, 215, 22);
        add(hibakategoria_box);
        
        JLabel lblNewLabel_25 = new JLabel("Ellenörzött mennyiség");
        lblNewLabel_25.setBounds(47, 136, 129, 14);
        add(lblNewLabel_25);
        
        mennyiseg_label = new JLabel("0");
        mennyiseg_label.setBounds(188, 136, 46, 14);
        add(mennyiseg_label);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(499, 600, 89, 23);
        add(mentes_gomb);
        
        JButton torles_gomb = new JButton("Törlés");
        torles_gomb.addActionListener(new Torles());
        torles_gomb.setBounds(47, 600, 89, 23);
        add(torles_gomb);        
        
        JLabel lblNewLabel_26 = new JLabel("Megjegyzés");
        lblNewLabel_26.setBounds(583, 467, 85, 14);
        add(lblNewLabel_26);
        
        megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(megjegyzes_mezo);
        gorgeto.setBounds(583, 481, 280, 84);
        add(gorgeto);
        
        
        JLabel lblNewLabel_11 = new JLabel("WLAN MAC Adress");
        lblNewLabel_11.setBounds(583, 412, 129, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_22 = new JLabel("WLAN Key");
        lblNewLabel_22.setBounds(583, 437, 104, 14);
        add(lblNewLabel_22);
        
        wlanmac_back = new JTextField();
        wlanmac_back.setBounds(722, 409, 162, 20);
        add(wlanmac_back);
        wlanmac_back.setColumns(10);
        
        wlankey_back = new JTextField();
        wlankey_back.setBounds(722, 438, 162, 20);
        add(wlankey_back);
        wlankey_back.setColumns(10);
        
        wlanmac_gui = new JTextField();
        wlanmac_gui.addKeyListener(new Enter3());
        wlanmac_gui.setBounds(928, 409, 162, 20);
        add(wlanmac_gui);
        wlanmac_gui.setColumns(10);
        
        wlankey_gui = new JTextField();
        wlankey_gui.addKeyListener(new Enter4());
        wlankey_gui.setBounds(928, 438, 162, 20);
        add(wlankey_gui);
        wlankey_gui.setColumns(10);
        
        JLabel lblNewLabel_23 = new JLabel("from the GUI");
        lblNewLabel_23.setBounds(989, 382, 86, 14);
        add(lblNewLabel_23);
        
        JLabel lblNewLabel_24 = new JLabel("from the back side og product");
        lblNewLabel_24.setBounds(737, 382, 178, 14);
        add(lblNewLabel_24);
        
        JLabel lblNewLabel_27 = new JLabel("Egyezés");
        lblNewLabel_27.setBounds(1133, 382, 58, 14);
        add(lblNewLabel_27);
        
        egyezes2_mezo = new JTextField();
        egyezes2_mezo.setEditable(false);
        egyezes2_mezo.setBounds(1117, 409, 86, 20);
        add(egyezes2_mezo);
        egyezes2_mezo.setColumns(10);
        
        egyezes3_mezo = new JTextField();
        egyezes3_mezo.setEditable(false);
        egyezes3_mezo.setBounds(1117, 438, 86, 20);
        add(egyezes3_mezo);
        egyezes3_mezo.setColumns(10);
        
        ido();

    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datum_mezo.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
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
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
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
                else
                {
                    /*end = System.currentTimeMillis();
                    long millis = end - start;                    
                    int seconds = (int) (millis / 1000) % 60 ;
                    int minutes = (int) ((millis / (1000*60)) % 60);
                    int hours   = (int) ((millis / (1000*60*60)) % 24);                    
                    System.out.println(hours +":"+ minutes +":"+ seconds); */                   
                    int petifele = (int) (measureTime(false) / 1000000);
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
                    String sql = "INSERT INTO qualitydb.OQC_FR2400 (Datum, Tesztelo,Tipus,Artikel_szam,Raklapszam,Szeriaszam_doboz,Szeriaszam_termek,Egyezes,Teszt_tipusa,Wlan_macadress_prod,"
                            + "Wlan_macadress_gui,Mac_egyezes,Wlan_key_prod,Wlan_key_gui,wlan_egyezes,Wlan_tx_gui_5,Wlan_rx_gui_5,Wlan_tx_iperf_5,Wlan_rx_iperf_5,Wlan_Bandwith_5,Wlan_tx_gui,Wlan_rx_gui,"
                            + "Wlan_tx_iperf,Wlan_rx_iperf,Wlan_Bandwith,Firmware,Hiba,Hibacsoport,"
                            + "Megjegyzes,Kritikus_hiba, Sulyos_hiba,Enyhe_hiba,Rogzites_ido, Teszt_ido) VALUES('"+ datum_mezo.getText() +"','"+ String.valueOf(ellenor_box.getSelectedItem()) +"',"
                            + "'"+ String.valueOf(tipus_box.getSelectedItem()) +"','"+ guide_mezo.getText() +"','"+ formazo +"','"+ doboz_mezo.getText() +"','"+ termek_mezo.getText() +"',"
                            + "'"+ egyezes_mezo.getText() +"','"+ String.valueOf(teszttipus_box.getSelectedItem()) +"','"+wlanmac_back.getText() +"','"+ wlanmac_gui.getText() +"','"+ egyezes2_mezo.getText() +"',"
                            + "'"+ wlankey_back.getText() +"','"+ wlankey_gui.getText() +"','"+ egyezes3_mezo.getText() +"',"
                            + "'"+ tx_gui5.getText() +"','"+ rx_gui5.getText() +"',"
                            + "'"+ tx_iperf5.getText() +"','"+ rx_iperf5.getText() +"','"+ bandwith5.getText() +"','"+ tx_gui2.getText() +"','"+ rx_gui2.getText() +"',"
                            + "'"+ tx_iperf2.getText() +"','"+ rx_iperf2.getText() +"','"+ bandwith2.getText() +"','"+ firmware_mezo.getText() +"',"
                            + "'"+ String.valueOf(hiba_box.getSelectedItem()) +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ megjegyzes_mezo.getText() +"',"
                            + "'"+ kritikus +"','"+ sulyos +"','"+ enyhe +"','"+ rogzites.format(timestamp) +"','"+ tesztido +"')";
                    
                    SQA_SQL ment = new SQA_SQL();
                    ment.mindenes(sql);
                    
                    visszaallit();
                    int mennyiseg = Integer.valueOf(mennyiseg_label.getText());
                    mennyiseg++;
                    mennyiseg_label.setText(String.valueOf(mennyiseg));
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
    
    private void visszaallit()
    {
        raklap_mezo.setText("");
        doboz_mezo.setText("");
        termek_mezo.setText("");
        guide_mezo.setText("");
        egyezes_mezo.setText("");
        egyezes_mezo.setBackground(Color.WHITE);        
        tx_gui2.setText("");
        tx_gui2.setBackground(Color.WHITE);
        rx_gui2.setText("");
        rx_gui2.setBackground(Color.WHITE);
        tx_iperf2.setText("");
        tx_iperf2.setBackground(Color.WHITE);
        rx_iperf2.setText("");
        rx_iperf2.setBackground(Color.WHITE);
        bandwith2.setText("");
        bandwith2.setBackground(Color.WHITE);       
        tx_gui5.setText("");
        tx_gui5.setBackground(Color.WHITE);
        rx_gui5.setText("");
        rx_gui5.setBackground(Color.WHITE);
        tx_iperf5.setText("");
        tx_iperf5.setBackground(Color.WHITE);
        rx_iperf5.setText("");
        rx_iperf5.setBackground(Color.WHITE);
        bandwith5.setText("");
        bandwith5.setBackground(Color.WHITE);
        firmware_mezo.setText("");        
        hiba_box.setSelectedIndex(0);
        hibacsoport_box.setSelectedIndex(0);
        hibakategoria_box.setSelectedIndex(0);
        megjegyzes_mezo.setText("");
        wlanmac_back.setText("");
        wlanmac_gui.setText("");
        wlankey_back.setText("");
        wlankey_gui.setText("");
        egyezes2_mezo.setText("");
        egyezes2_mezo.setBackground(Color.WHITE);
        egyezes3_mezo.setText("");
        egyezes3_mezo.setBackground(Color.WHITE);
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                if(doboz_mezo.getText().equals(termek_mezo.getText()))
                {
                    egyezes_mezo.setText("OK");
                    egyezes_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    egyezes_mezo.setText("NOK");
                    egyezes_mezo.setBackground(Color.RED);
                }
            }       
        }
        @Override
        public void keyTyped(KeyEvent e){                                                 //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom         
        }
        @Override
        public void keyReleased(KeyEvent e){                                              //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom           
        }    
    }
    
    class Enter3 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                if(wlanmac_back.getText().equals(wlanmac_gui.getText()))
                {
                    egyezes2_mezo.setText("OK");
                    egyezes2_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    egyezes2_mezo.setText("NOK");
                    egyezes2_mezo.setBackground(Color.RED);
                }
            }       
        }
        @Override
        public void keyTyped(KeyEvent e){                                                 //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom         
        }
        @Override
        public void keyReleased(KeyEvent e){                                              //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom           
        }    
    }
    
    class Enter4 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                if(wlankey_back.getText().equals(wlankey_gui.getText()))
                {
                    egyezes3_mezo.setText("OK");
                    egyezes3_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    egyezes3_mezo.setText("NOK");
                    egyezes3_mezo.setBackground(Color.RED);
                }
            }       
        }
        @Override
        public void keyTyped(KeyEvent e){                                                 //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom         
        }
        @Override
        public void keyReleased(KeyEvent e){                                              //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom           
        }    
    }
    
    class Enter2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
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
    
    class TX_gui2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(tx_gui2.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(tx_gui2.getText());
                if(jel >= 240 && jel <= 1000)
                {
                    tx_gui2.setBackground(Color.GREEN);
                }
                else
                {
                    tx_gui2.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class TX_gui5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(tx_gui5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(tx_gui5.getText());
                if(jel >= 1320 && jel <= 2000)
                {
                    tx_gui5.setBackground(Color.GREEN);
                }
                else
                {
                    tx_gui5.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class RX_gui2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(rx_gui2.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(rx_gui2.getText());
                if(jel >= 240 && jel <= 1000)
                {
                    rx_gui2.setBackground(Color.GREEN);
                }
                else
                {
                    rx_gui2.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class RX_gui5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(rx_gui5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(rx_gui5.getText());
                if(jel >= 1300 && jel <= 2000)
                {
                    rx_gui5.setBackground(Color.GREEN);
                }
                else
                {
                    rx_gui5.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class TX_iperf2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(tx_iperf2.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(tx_iperf2.getText());
                if(jel >= 100 && jel <= 220)
                {
                    tx_iperf2.setBackground(Color.GREEN);
                }
                else
                {
                    tx_iperf2.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class TX_iperf5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(tx_iperf5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(tx_iperf5.getText());
                if(jel >= 390 && jel <= 1000)
                {
                    tx_iperf5.setBackground(Color.GREEN);
                }
                else
                {
                    tx_iperf5.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class RX_iperf2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(rx_iperf2.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(rx_iperf2.getText());
                if(jel >= 100 && jel <= 220)
                {
                    rx_iperf2.setBackground(Color.GREEN);
                }
                else
                {
                    rx_iperf2.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class RX_iperf5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(rx_iperf5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(rx_iperf5.getText());
                if(jel >= 390 && jel <= 1000)
                {
                    rx_iperf5.setBackground(Color.GREEN);
                }
                else
                {
                    rx_iperf5.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class Bandwith2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(bandwith2.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(bandwith2.getText());
                if(jel == 20)
                {
                    bandwith2.setBackground(Color.GREEN);
                }
                else
                {
                    bandwith2.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class Bandwith5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(bandwith5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(bandwith5.getText());
                if(jel == 80)
                {
                    bandwith5.setBackground(Color.GREEN);
                }
                else
                {
                    bandwith5.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class Torles implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                visszaallit();
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

