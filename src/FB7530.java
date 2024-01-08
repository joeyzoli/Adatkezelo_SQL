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

public class FB7530 extends JPanel {
    private JTextField datum_mezo;
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField termek_mezo;
    private JTextField egyezes_mezo;
    private JTextField guide_mezo;
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

    /**
     * Create the panel.
     */
    public FB7530() {
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
        
        JLabel lblNewLabel_6 = new JLabel("Szériaszám doboz");
        lblNewLabel_6.setBounds(47, 220, 115, 14);
        add(lblNewLabel_6);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(188, 217, 144, 20);
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
        
        JLabel lblNewLabel_9 = new JLabel("Szériaszám Quick guide");
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
        rx_gui2.setBounds(786, 257, 52, 20);
        add(rx_gui2);
        rx_gui2.setColumns(10);
        
        rx_gui5 = new JTextField();
        rx_gui5.setBounds(874, 257, 52, 20);
        add(rx_gui5);
        rx_gui5.setColumns(10);
        
        tx_iperf2 = new JTextField();
        tx_iperf2.setBounds(786, 302, 52, 20);
        add(tx_iperf2);
        tx_iperf2.setColumns(10);
        
        tx_iperf5 = new JTextField();
        tx_iperf5.setBounds(874, 302, 52, 20);
        add(tx_iperf5);
        tx_iperf5.setColumns(10);
        
        rx_iperf2 = new JTextField();
        rx_iperf2.setBounds(786, 341, 52, 20);
        add(rx_iperf2);
        rx_iperf2.setColumns(10);
        
        rx_iperf5 = new JTextField();
        rx_iperf5.setBounds(874, 341, 52, 20);
        add(rx_iperf5);
        rx_iperf5.setColumns(10);
        
        bandwith2 = new JTextField();
        bandwith2.setBounds(786, 379, 52, 20);
        add(bandwith2);
        bandwith2.setColumns(10);
        
        bandwith5 = new JTextField();
        bandwith5.setBounds(874, 379, 52, 20);
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
        download_mezo.setBounds(740, 485, 46, 20);
        add(download_mezo);
        download_mezo.setColumns(10);
        
        upload_mezo = new JTextField();
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
        
        ido();

    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datum_mezo.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
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
                SimpleDateFormat rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                                                          //
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String sql = "INSERT INTO qualitydb.OQC_FB7530 (Datum, Tesztelo,Tipus,Raklapszam,Szeriaszam_doboz,Szeriaszam_termek,Szeriaszam_quick,Egyezes,Teszt_tipusa,Jelerosseg,Wlan_tx_gui,Wlan_rx_gui,"
                        + "Wlan_tx_iperf,Wlan_rx_iperf,Wlan_Bandwith,Jelerosseg_5,Wlan_tx_gui_5,Wlan_rx_gui_5,Wlan_tx_iperf_5,Wlan_rx_iperf_5,Wlan_Bandwith_5,Firmware,Phone,Download,Upload,Hiba,Hibacsoport,"
                        + "Megjegyzes,Kritikus_hiba, Súlyos_hiba,Enyhe_hiba,Rogzites_ido) VALUES('"+ datum_mezo.getText() +"','"+ String.valueOf(ellenor_box.getSelectedItem()) +"',"
                        + "'"+ String.valueOf(tipus_box.getSelectedItem()) +"','"+ raklap_mezo.getText() +"','"+ doboz_mezo.getText() +"','"+ termek_mezo.getText() +"','"+ guide_mezo.getText() +"',"
                        + "'"+ egyezes_mezo.getText() +"','"+ String.valueOf(teszttipus_box.getSelectedItem()) +"','"+ jel2_mezo.getText() +"','"+ tx_gui2.getText() +"','"+ rx_gui2.getText() +"',"
                        + "'"+ tx_iperf2.getText() +"','"+ rx_iperf2.getText() +"','"+ bandwith2.getText() +"','"+ jel5.getText()+"','"+ tx_gui5.getText() +"','"+ rx_gui5.getText() +"',"
                        + "'"+ tx_iperf5.getText() +"','"+ rx_iperf5.getText() +"','"+ bandwith5.getText() +"','"+ firmware_mezo.getText() +"','"+ phone_mezo.getText() +"','"+ download_mezo.getText() +"',"
                        + "'"+ upload_mezo.getText() +"','"+ String.valueOf(hiba_box.getSelectedItem()) +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ megjegyzes_mezo.getText() +"',"
                        + "'"+ kritikus +"','"+ sulyos +"','"+ enyhe +"','"+ rogzites.format(timestamp) +"')";
                SQA_SQL ment = new SQA_SQL();
                ment.mindenes(sql);
                
                raklap_mezo.setText("");
                doboz_mezo.setText("");
                termek_mezo.setText("");
                guide_mezo.setText("");
                egyezes_mezo.setText("");
                egyezes_mezo.setBackground(Color.WHITE);
                jel2_mezo.setText("");
                tx_gui2.setText("");
                rx_gui2.setText("");
                tx_iperf2.setText("");
                rx_iperf2.setText("");
                bandwith2.setText("");
                jel5.setText("");
                tx_gui5.setText("");
                rx_gui5.setText("");
                tx_iperf5.setText("");
                rx_iperf5.setText("");
                bandwith5.setText("");
                firmware_mezo.setText("");
                phone_mezo.setText("");
                download_mezo.setText("");
                upload_mezo.setText("");
                hiba_box.setSelectedIndex(0);
                hibacsoport_box.setSelectedIndex(0);
                hibakategoria_box.setSelectedIndex(0);
                megjegyzes_mezo.setText("");
                int mennyiseg = Integer.valueOf(mennyiseg_label.getText());
                mennyiseg++;
                mennyiseg_label.setText(String.valueOf(mennyiseg));
                
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
}
