import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JButton;

public class FD302_Vizualis extends JPanel {
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> hiba_box;
    private JComboBox<String> hibakategoria_box;
    private static Long timer_start;
    private JTextField tipus_mezo;
    private JLabel mennyiseg_label;
    private JTextArea megjegyzes_mezo;

    /**
     * Create the panel.
     */
    public FD302_Vizualis() {
        setLayout(null);
        ComboBox combobox_tomb = new ComboBox();
        
        JLabel lblNewLabel = new JLabel("Gyűjtődoboz");
        lblNewLabel.setBounds(38, 35, 93, 14);
        add(lblNewLabel);
        
        raklap_mezo = new JTextField();
        raklap_mezo.setBounds(141, 32, 243, 20);
        raklap_mezo.addKeyListener(new Enter1());
        add(raklap_mezo);
        raklap_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Szériaszám doboz");
        lblNewLabel_1.setBounds(38, 85, 105, 14);
        add(lblNewLabel_1);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(167, 82, 217, 20);
        add(doboz_mezo);
        doboz_mezo.setColumns(10);
        
        JLabel lblNewLabel_19 = new JLabel("Hibacsoport");
        lblNewLabel_19.setBounds(38, 361, 103, 14);
        add(lblNewLabel_19);
        
        String[] hibacsoport = {"","Címke","Funkció","Papír","Tartozék","Termék","Szerelés"};
        hibacsoport_box = new JComboBox<String>(hibacsoport);                                               //hibacsoport
        hibacsoport_box.setBounds(167, 357, 215, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_20 = new JLabel("Hiba");
        lblNewLabel_20.setBounds(38, 406, 46, 14);
        add(lblNewLabel_20);
        
        hiba_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                    //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hiba_box.setBounds(167, 402, 215, 22);
        add(hiba_box);
        
        JLabel lblNewLabel_21 = new JLabel("Hiba kategória");
        lblNewLabel_21.setBounds(38, 453, 103, 14);
        add(lblNewLabel_21);
        
        String[] kategoria = {"","Kritikus hiba","Súlyos hiba","Enyhe hiba"};
        hibakategoria_box = new JComboBox<String>(kategoria);                                               //kategoria
        hibakategoria_box.setBounds(167, 449, 215, 22);
        add(hibakategoria_box);
        
        JLabel lblNewLabel_12 = new JLabel("Article Number");
        lblNewLabel_12.setBounds(489, 35, 105, 14);
        add(lblNewLabel_12);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(665, 32, 125, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        JLabel lblNewLabel_22 = new JLabel("Megjegyzés");
        lblNewLabel_22.setBounds(489, 361, 93, 14);
        add(lblNewLabel_22);
        
        megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        megjegyzes_mezo.setBounds(592, 356, 302, 111);
        add(megjegyzes_mezo);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(701, 525, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_23 = new JLabel("Ellenőrzött mennyiség");
        lblNewLabel_23.setBounds(346, 529, 143, 14);
        add(lblNewLabel_23);
        
        mennyiseg_label = new JLabel("0");
        mennyiseg_label.setBounds(499, 529, 46, 14);
        add(mennyiseg_label);
        
        JButton torles_gomb = new JButton("Törlés");
        torles_gomb.setBounds(82, 525, 89, 23);
        add(torles_gomb);

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
                    String sql = "INSERT INTO qualitydb.OQC_FD302 (Datum, Tesztelo,Tipus,Raklapszam,Szeriaszam_doboz,Hiba,Hibacsoport,"
                            + "Megjegyzes,Kritikus_hiba, Sulyos_hiba,Enyhe_hiba,Rogzites_ido, Teszt_ido) VALUES('"+ FD302_Fejlec.datum_mezo.getText() +"','"+ String.valueOf(FD302_Fejlec.ellenor_box.getSelectedItem()) +"',"
                            + "'"+ String.valueOf(FD302_Fejlec.tipus_box.getSelectedItem()) +"','"+ formazo +"','"+ doboz_mezo.getText() +"'," 
                            +"'"+ String.valueOf(hiba_box.getSelectedItem()) +"','"+ String.valueOf(hibacsoport_box.getSelectedItem()) +"','"+ megjegyzes_mezo.getText() +"',"
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
        tipus_mezo.setText("");        
        hiba_box.setSelectedIndex(0);
        hibacsoport_box.setSelectedIndex(0);
        hibakategoria_box.setSelectedIndex(0);
        megjegyzes_mezo.setText("");
    }
    
    class Enter1 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
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
}

