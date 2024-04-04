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

public class FB7530_Mesh extends JPanel {
    private JTextField datum_mezo;
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField termek_mezo;
    private JTextField egyezes_router;
    private JTextField firmware_mezo;
    private JTextField jel2_mezo;
    private JTextField jel5;
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
    private JTextField phone_mezo;
    private JTextField download_mezo;
    private JTextField upload_mezo;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> ellenor_box ;
    private JComboBox<String> tipus_box;
    private JComboBox<String> teszttipus_box;
    private JComboBox<String> hiba_box;
    private JComboBox<String> hibakategoria_box;
    private JTextArea megjegyzes_mezo;
    private JLabel mennyiseg_label;
    private static Long timer_start;
    private JTextField textField;
    private JTextField egyezes_repeater;
    private JTextField router_firmware;
    private JTextField repeater_firmware;

    /**
     * Create the panel.
     */
    public FB7530_Mesh() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("FB7530 OQC adatgyűjtő");
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
        
        String[] tipus = {"FB7530_2","FB7530 INTER_2","FB7530AX_2","FB7530AX INTER_2"};
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
        
        JLabel lblNewLabel_6 = new JLabel("Router Szériaszám doboz");
        lblNewLabel_6.setBounds(47, 220, 150, 14);
        add(lblNewLabel_6);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(230, 217, 144, 20);
        doboz_mezo.addKeyListener(new Enter2());
        add(doboz_mezo);
        doboz_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Router Szériaszám termék");
        lblNewLabel_7.setBounds(47, 260, 150, 14);
        add(lblNewLabel_7);
        
        termek_mezo = new JTextField();
        termek_mezo.setBounds(230, 257, 144, 20);
        termek_mezo.addKeyListener(new Enter());
        add(termek_mezo);
        termek_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Szériák egyezése");
        lblNewLabel_8.setBounds(47, 424, 115, 14);
        add(lblNewLabel_8);
        
        egyezes_router = new JTextField();       
        egyezes_router.setEditable(false);
        egyezes_router.setBounds(188, 421, 86, 20);
        add(egyezes_router);
        egyezes_router.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Repeatr SN termék");
        lblNewLabel_10.setBounds(47, 347, 115, 14);
        add(lblNewLabel_10);
        
        firmware_mezo = new JTextField();
        firmware_mezo.setBounds(188, 344, 288, 20);
        add(firmware_mezo);
        firmware_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Jelerősség %");
        lblNewLabel_11.setBounds(583, 175, 103, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Wlan TX (Mbit\\s) on GUI");
        lblNewLabel_12.setBounds(583, 220, 150, 14);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Wlan RX (Mbit\\s) on GUI");
        lblNewLabel_13.setBounds(583, 260, 150, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Wlan TX (Mbit\\s) on iperf");
        lblNewLabel_14.setBounds(583, 305, 150, 14);
        add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Wlan RX (Mbit\\s) on iperf");
        lblNewLabel_15.setBounds(583, 344, 150, 14);
        add(lblNewLabel_15);
        
        JLabel lblNewLabel_16 = new JLabel("Wlan Bandwith (Mhz)");
        lblNewLabel_16.setBounds(583, 382, 150, 14);
        add(lblNewLabel_16);
        
        jel2_mezo = new JTextField();
        jel2_mezo.setBounds(786, 172, 52, 20);
        jel2_mezo.addKeyListener(new Jel2());
        add(jel2_mezo);
        jel2_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("2,4 Ghz");
        lblNewLabel_17.setBounds(792, 136, 46, 14);
        add(lblNewLabel_17);
        
        jel5 = new JTextField();
        jel5.addKeyListener(new Jel5());
        jel5.setBounds(874, 172, 52, 20);
        add(jel5);
        jel5.setColumns(10);
        
        JLabel lblNewLabel_18 = new JLabel("5 Ghz");
        lblNewLabel_18.setBounds(880, 136, 46, 14);
        add(lblNewLabel_18);
        
        tx_gui2 = new JTextField();
        tx_gui2.setBounds(786, 217, 52, 20);
        tx_gui2.addKeyListener(new TX_gui2());
        add(tx_gui2);
        tx_gui2.setColumns(10);
        
        tx_gui5 = new JTextField();
        tx_gui5.setBounds(874, 217, 52, 20);
        tx_gui5.addKeyListener(new TX_gui5());
        add(tx_gui5);
        tx_gui5.setColumns(10);
        
        rx_gui2 = new JTextField();
        rx_gui2.addKeyListener(new RX_gui2());
        rx_gui2.setBounds(786, 257, 52, 20);
        add(rx_gui2);
        rx_gui2.setColumns(10);
        
        rx_gui5 = new JTextField();
        rx_gui5.addKeyListener(new RX_gui5());
        rx_gui5.setBounds(874, 257, 52, 20);
        add(rx_gui5);
        rx_gui5.setColumns(10);
        
        tx_iperf2 = new JTextField();
        tx_iperf2.addKeyListener(new TX_iperf2());
        tx_iperf2.setBounds(786, 302, 52, 20);
        add(tx_iperf2);
        tx_iperf2.setColumns(10);
        
        tx_iperf5 = new JTextField();
        tx_iperf5.addKeyListener(new TX_iperf5());
        tx_iperf5.setBounds(874, 302, 52, 20);
        add(tx_iperf5);
        tx_iperf5.setColumns(10);
        
        rx_iperf2 = new JTextField();
        rx_iperf2.addKeyListener(new RX_iperf2());
        rx_iperf2.setBounds(786, 341, 52, 20);
        add(rx_iperf2);
        rx_iperf2.setColumns(10);
        
        rx_iperf5 = new JTextField();
        rx_iperf5.addKeyListener(new RX_iperf5());
        rx_iperf5.setBounds(874, 341, 52, 20);
        add(rx_iperf5);
        rx_iperf5.setColumns(10);
        
        bandwith2 = new JTextField();
        bandwith2.addKeyListener(new Bandwith2());
        bandwith2.setBounds(786, 379, 52, 20);
        add(bandwith2);
        bandwith2.setColumns(10);
        
        bandwith5 = new JTextField();
        bandwith5.addKeyListener(new Bandwith5());
        bandwith5.setBounds(874, 379, 52, 20);
        add(bandwith5);
        bandwith5.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Hibacsoport");
        lblNewLabel_19.setBounds(47, 488, 103, 14);
        add(lblNewLabel_19);
        
        String[] hibacsoport = {"","Címke","Funkció","Papír","Tartozék","Termék","Szerelés"};
        hibacsoport_box = new JComboBox<String>(hibacsoport);                                              //hibacsoport
        hibacsoport_box.setBounds(188, 484, 215, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_20 = new JLabel("Hiba");
        lblNewLabel_20.setBounds(47, 521, 46, 14);
        add(lblNewLabel_20);
        
        hiba_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hiba_box.setBounds(188, 517, 215, 22);
        add(hiba_box);
        
        JLabel lblNewLabel_21 = new JLabel("Hiba kategória");
        lblNewLabel_21.setBounds(47, 557, 103, 14);
        add(lblNewLabel_21);
        
        String[] kategoria = {"","Kritikus hiba","Súlyos hiba","Enyhe hiba"};
        hibakategoria_box = new JComboBox<String>(kategoria);                                    //kategoria
        hibakategoria_box.setBounds(188, 553, 215, 22);
        add(hibakategoria_box);
        
        JLabel lblNewLabel_22 = new JLabel("Phone (dBm)");
        lblNewLabel_22.setBounds(583, 441, 86, 14);
        add(lblNewLabel_22);
        
        JLabel lblNewLabel_23 = new JLabel("Download (Mbit/s)");
        lblNewLabel_23.setBounds(583, 488, 103, 14);
        add(lblNewLabel_23);
        
        JLabel lblNewLabel_24 = new JLabel("Upload (Mbit/s)");
        lblNewLabel_24.setBounds(583, 531, 104, 14);
        add(lblNewLabel_24);
        
        phone_mezo = new JTextField();
        phone_mezo.setBounds(740, 438, 46, 20);
        add(phone_mezo);
        phone_mezo.setColumns(10);
        
        download_mezo = new JTextField();
        download_mezo.addKeyListener(new Download());
        download_mezo.setBounds(740, 485, 46, 20);
        add(download_mezo);
        download_mezo.setColumns(10);
        
        upload_mezo = new JTextField();
        upload_mezo.addKeyListener(new Upload());
        upload_mezo.setBounds(740, 528, 46, 20);
        add(upload_mezo);
        upload_mezo.setColumns(10);
        
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
        
        megjegyzes_mezo = new JTextArea();       
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(megjegyzes_mezo);
        gorgeto.setBounds(880, 461, 280, 84);
        add(gorgeto);
        
        JLabel lblNewLabel_26 = new JLabel("Megjegyzés");
        lblNewLabel_26.setBounds(880, 441, 85, 14);
        add(lblNewLabel_26);
        
        JLabel lblNewLabel_27 = new JLabel("Router Szériaszám 2");
        lblNewLabel_27.setBounds(47, 305, 129, 14);
        add(lblNewLabel_27);
        
        textField = new JTextField();
        textField.setBounds(230, 302, 144, 20);
        add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_28 = new JLabel("Firmware verzio");
        lblNewLabel_28.setBounds(47, 458, 129, 14);
        add(lblNewLabel_28);
        
        egyezes_repeater = new JTextField();
        egyezes_repeater.setEditable(false);
        egyezes_repeater.setBounds(317, 421, 86, 20);
        add(egyezes_repeater);
        egyezes_repeater.setColumns(10);
        
        router_firmware = new JTextField();
        router_firmware.setBounds(188, 453, 86, 20);
        add(router_firmware);
        router_firmware.setColumns(10);
        
        repeater_firmware = new JTextField();
        repeater_firmware.setBounds(317, 453, 86, 20);
        add(repeater_firmware);
        repeater_firmware.setColumns(10);
        
        JLabel lblNewLabel_29 = new JLabel("Router");
        lblNewLabel_29.setBounds(211, 396, 46, 14);
        add(lblNewLabel_29);
        
        JLabel lblNewLabel_9 = new JLabel("Repeater");
        lblNewLabel_9.setBounds(340, 396, 63, 14);
        add(lblNewLabel_9);
        
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
                    String sql = "INSERT INTO qualitydb.OQC_FB7530 (Datum, Tesztelo,Tipus,Raklapszam,Szeriaszam_doboz,Szeriaszam_termek,Szeriaszam_quick,Egyezes,Teszt_tipusa,Jelerosseg,Wlan_tx_gui,Wlan_rx_gui,"
                            + "Wlan_tx_iperf,Wlan_rx_iperf,Wlan_Bandwith,Jelerosseg_5,Wlan_tx_gui_5,Wlan_rx_gui_5,Wlan_tx_iperf_5,Wlan_rx_iperf_5,Wlan_Bandwith_5,Firmware,Phone,Download,Upload,Hiba,Hibacsoport,"
                            + "Megjegyzes,Kritikus_hiba, Sulyos_hiba,Enyhe_hiba,Rogzites_ido, Teszt_ido) VALUES('"+ datum_mezo.getText() +"','"+ String.valueOf(ellenor_box.getSelectedItem()) +"',"
                            + "'"+ String.valueOf(tipus_box.getSelectedItem()) +"','"+ formazo +"','"+ doboz_mezo.getText() +"','"+ termek_mezo.getText() +"','"+ "" +"',"
                            + "'"+ egyezes_router.getText() +"','"+ String.valueOf(teszttipus_box.getSelectedItem()) +"','"+ jel2_mezo.getText() +"','"+ tx_gui2.getText() +"','"+ rx_gui2.getText() +"',"
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
        egyezes_router.setText("");
        egyezes_router.setBackground(Color.WHITE);
        jel2_mezo.setText("");
        jel2_mezo.setBackground(Color.WHITE);
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
        jel5.setText("");
        jel5.setBackground(Color.WHITE);
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
        phone_mezo.setText("");
        download_mezo.setText("");
        download_mezo.setBackground(Color.WHITE);
        upload_mezo.setText("");
        upload_mezo.setBackground(Color.WHITE);
        hiba_box.setSelectedIndex(0);
        hibacsoport_box.setSelectedIndex(0);
        hibakategoria_box.setSelectedIndex(0);
        megjegyzes_mezo.setText("");
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
                    egyezes_router.setText("OK");
                    egyezes_router.setBackground(Color.GREEN);
                }
                else
                {
                    egyezes_router.setText("NOK");
                    egyezes_router.setBackground(Color.RED);
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
    
    class Jel2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(jel2_mezo.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(jel2_mezo.getText());
                if(jel >= 0 && jel <= 100)
                {
                    jel2_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    jel2_mezo.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class Jel5 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(jel5.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(jel5.getText());
                if(jel >= 0 && jel <= 100)
                {
                    jel5.setBackground(Color.GREEN);
                }
                else
                {
                    jel5.setBackground(Color.RED);
                }
            }           
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
                if(jel >= 100 && jel <= 160)
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
                if(jel >= 900 && jel <= 1500)
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
                if(jel >= 100 && jel <= 160)
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
                if(jel >= 900 && jel <= 1500)
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
                if(jel >= 40 && jel <= 1000)
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
                if(jel >= 400 && jel <= 800)
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
                if(jel >= 40 && jel <= 1000)
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
                if(jel >= 400 && jel <= 800)
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
    
    class Download implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(download_mezo.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(download_mezo.getText());
                if(jel >= 310 && jel <= 345)
                {
                    download_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    download_mezo.setBackground(Color.RED);
                }
            }           
        }    
    }
    
    class Upload implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            if(upload_mezo.getText().equals("")) {}
            else
            {
                int jel  = Integer.valueOf(upload_mezo.getText());
                if(jel == 50)
                {
                    upload_mezo.setBackground(Color.GREEN);
                }
                else
                {
                    upload_mezo.setBackground(Color.RED);
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
