import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FD302_Fejlec extends JPanel {
    private JTextField datum_mezo;
    private JComboBox<String> ellenor_box;
    private JComboBox<String> teszttipus_box;
    private JComboBox<String> tipus_box;

    /**
     * Create the panel.
     */
    public FD302_Fejlec() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("TEsztelő");
        lblNewLabel.setBounds(85, 62, 62, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("AVM FD302 OQC adatgyűjtő");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(546, 23, 251, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum");
        lblNewLabel_2.setBounds(85, 107, 46, 14);
        add(lblNewLabel_2);
        
        ComboBox combobox_tomb = new ComboBox();
        ellenor_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.ellenorok));                            //combobox_tomb.getCombobox2(ComboBox.ellenorok)
        ellenor_box.setBounds(172, 58, 193, 22);
        ellenor_box.addActionListener(new Ellenor_valaszto());
        add(ellenor_box);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(172, 104, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Teszt típusa");
        lblNewLabel_3.setBounds(833, 62, 91, 14);
        add(lblNewLabel_3);
        
        String[] tesztipusok = {"F","V","Kalibrálás"};
        teszttipus_box = new JComboBox<String>(tesztipusok);              //tesztipusok
        teszttipus_box.setBounds(947, 58, 142, 22);
        teszttipus_box.addActionListener(new Teszt_valaszto());
        add(teszttipus_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(833, 107, 46, 14);
        add(lblNewLabel_4);
        
        String[] tipusok = {"FDect 302","másik típus","megint másik typus"};
        tipus_box = new JComboBox<String>(tipusok);                       //tipusok
        tipus_box.setBounds(947, 103, 142, 22);
        add(tipus_box);
        
        ido() ;

    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datum_mezo.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }
    
    class Teszt_valaszto implements ActionListener                                                                                       //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String valasztott = String.valueOf(teszttipus_box.getSelectedItem());
                if(valasztott.equals("F"))
                {
                    FD302.cardLayout.show(FD302.kartyak, "oqc");
                }
                else if(valasztott.equals("V"))
                {
                    FD302.cardLayout.show(FD302.kartyak, "visual");
                }
                else
                {
                    FD302.cardLayout.show(FD302.kartyak, "kalib");
                }
                Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Ellenor_valaszto implements ActionListener                                                                                       //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                FD302.cardLayout.show(FD302.kartyak, "kalib");
                teszttipus_box.setSelectedItem("Kalibrálás");
                Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
