import javax.swing.JPanel;
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

public class FD302_Kalibralas extends JPanel {
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField dm_telepitett;
    private JTextField textField_1;
    private static Long timer_start;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JLabel mennyiseg_label;
    private JTextArea megjegyzes_mezo;

    /**
     * Create the panel.
     */
    public FD302_Kalibralas() {
        setLayout(null);
        new ComboBox();
        
        JLabel lblNewLabel = new JLabel("Gyűjtődoboz");
        lblNewLabel.setBounds(38, 35, 93, 14);
        add(lblNewLabel);
        
        raklap_mezo = new JTextField();
        raklap_mezo.setText("kalibrációs termék");
        raklap_mezo.setBounds(141, 32, 243, 20);
        add(raklap_mezo);
        raklap_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Szériaszám doboz");
        lblNewLabel_1.setBounds(38, 85, 105, 14);
        add(lblNewLabel_1);
        
        doboz_mezo = new JTextField();
        doboz_mezo.setBounds(167, 82, 217, 20);
        add(doboz_mezo);
        doboz_mezo.setColumns(10);
        
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
        
        textField_1 = new JTextField();
        textField_1.setBounds(335, 216, 49, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("DM érték - Max fűtés");
        lblNewLabel_7.setBounds(38, 261, 117, 14);
        add(lblNewLabel_7);
        
        textField_2 = new JTextField();
        textField_2.setBounds(167, 258, 93, 20);
        add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(335, 258, 49, 20);
        add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel(">1,6");
        lblNewLabel_8.setBounds(270, 261, 46, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("DM érték - \"Hópihe\"");
        lblNewLabel_9.setBounds(38, 310, 117, 14);
        add(lblNewLabel_9);
        
        textField_4 = new JTextField();
        textField_4.setBounds(167, 307, 93, 20);
        add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("min: -0,1");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_10.setBounds(270, 305, 55, 14);
        add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("max: 0,2");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblNewLabel_11.setBounds(270, 315, 55, 14);
        add(lblNewLabel_11);
        
        textField_5 = new JTextField();
        textField_5.setBounds(335, 305, 49, 20);
        add(textField_5);
        textField_5.setColumns(10);
        
        megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        megjegyzes_mezo.setBounds(592, 356, 308, 111);
        add(megjegyzes_mezo);
        
        JButton mentes_gomb = new JButton("Mentés");
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
        
        JLabel lblNewLabel_2 = new JLabel("Megjegyzés");
        lblNewLabel_2.setBounds(482, 361, 100, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Végezd el a digitális múltiméter");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_3.setBounds(482, 85, 409, 29);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_12 = new JLabel("kalibrálását az alábbi");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_12.setBounds(482, 125, 308, 29);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Szériaszámú termékkel:");
        lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_13.setBounds(482, 165, 308, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("P434796A0122525");
        lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_14.setBounds(625, 213, 332, 14);
        add(lblNewLabel_14);

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
                    
                    String formazo = raklap_mezo.getText().replace(" ","");
                    SimpleDateFormat rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                                                          //
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String sql = "INSERT INTO qualitydb.OQC_FD302 (Datum, Tesztelo,Tipus,Raklapszam,Szeriaszam_doboz,Hiba,Hibacsoport,"
                            + "Megjegyzes,Kritikus_hiba, Sulyos_hiba,Enyhe_hiba,Rogzites_ido, Teszt_ido) VALUES('"+ FD302_Fejlec.datum_mezo.getText() +"','"+ String.valueOf(FD302_Fejlec.ellenor_box.getSelectedItem()) +"',"
                            + "'"+ String.valueOf(FD302_Fejlec.tipus_box.getSelectedItem()) +"','"+ formazo +"','"+ doboz_mezo.getText() +"'," 
                            +"'"+ megjegyzes_mezo.getText() +"',"
                            + "'"+ rogzites.format(timestamp) +"','"+ tesztido +"')";
                    SQA_SQL ment = new SQA_SQL();
                    ment.mindenes(sql);
                    visszaallit();
                    int mennyiseg = Integer.valueOf(mennyiseg_label.getText());
                    mennyiseg++;
                    mennyiseg_label.setText(String.valueOf(mennyiseg));
                    visszaallit();
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
        /*tipus_mezo.setText("");        
        hiba_box.setSelectedIndex(0);
        hibacsoport_box.setSelectedIndex(0);
        hibakategoria_box.setSelectedIndex(0);
        megjegyzes_mezo.setText("");*/
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

