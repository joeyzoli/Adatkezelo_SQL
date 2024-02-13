import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Vevoireklamacio_d3 extends JPanel {
    private JTextField felelos_mezo;
    private JTextField hatarido_mezo;
    private JTextField lezaras_mezo;
    private JTextField felelos2_mezo;
    private JTextField hatarido2_mezo;
    private JTextField lezaras2_mezo;
    private JTextField felelos3_mezo;
    private JTextField hatarido3_mezo;
    private JTextField lezaras3_mezo;
    private JTextField felelos4_mezo;
    private JLabel lblNewLabel_6;
    private JTextField hatarido4_mezo;
    private JTextField lezaras4_mezo;
    private JTextField szallitas_mezo;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JTextField textField;
    private JLabel lblNewLabel_9;
    private JTextField textField_1;
    private JLabel lblNewLabel_10;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JLabel lblNewLabel_11;
    private JLabel lblNewLabel_12;
    private JLabel lblNewLabel_13;
    private JButton mentes_gomb;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d3() {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Félkész és késztermékek (és adott esetben nyersanyag) blokkolása");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(174, 61, 363, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Felelős");
        lblNewLabel_1.setBounds(620, 30, 46, 14);
        add(lblNewLabel_1);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(547, 58, 182, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Határidő");
        lblNewLabel_2.setBounds(833, 30, 78, 14);
        add(lblNewLabel_2);
        
        hatarido_mezo = new JTextField();
        hatarido_mezo.setBounds(817, 58, 86, 20);
        add(hatarido_mezo);
        hatarido_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Lezárás dátuma");
        lblNewLabel_3.setBounds(1002, 30, 105, 14);
        add(lblNewLabel_3);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(998, 58, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.setBounds(547, 100, 182, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        hatarido2_mezo = new JTextField();
        hatarido2_mezo.setBounds(817, 100, 86, 20);
        add(hatarido2_mezo);
        hatarido2_mezo.setColumns(10);
        
        lezaras2_mezo = new JTextField();
        lezaras2_mezo.setBounds(998, 100, 86, 20);
        add(lezaras2_mezo);
        lezaras2_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("A munkautasítást/normál működés ellenőrzése. ");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(246, 103, 291, 14);
        add(lblNewLabel_4);
        
        felelos3_mezo = new JTextField();
        felelos3_mezo.setBounds(547, 147, 182, 20);
        add(felelos3_mezo);
        felelos3_mezo.setColumns(10);
        
        hatarido3_mezo = new JTextField();
        hatarido3_mezo.setBounds(817, 147, 86, 20);
        add(hatarido3_mezo);
        hatarido3_mezo.setColumns(10);
        
        lezaras3_mezo = new JTextField();
        lezaras3_mezo.setBounds(998, 147, 86, 20);
        add(lezaras3_mezo);
        lezaras3_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("A hiba észlelhetőségének ellenőrzése tesztberendezéssel. ");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(214, 150, 323, 14);
        add(lblNewLabel_5);
        
        felelos4_mezo = new JTextField();
        felelos4_mezo.setBounds(547, 196, 182, 20);
        add(felelos4_mezo);
        felelos4_mezo.setColumns(10);
        
        lblNewLabel_6 = new JLabel("A termelés és minőség-ellenőrzés informálása. ");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(246, 199, 291, 14);
        add(lblNewLabel_6);
        
        hatarido4_mezo = new JTextField();
        hatarido4_mezo.setBounds(817, 196, 86, 20);
        add(hatarido4_mezo);
        hatarido4_mezo.setColumns(10);
        
        lezaras4_mezo = new JTextField();
        lezaras4_mezo.setBounds(998, 196, 86, 20);
        add(lezaras4_mezo);
        lezaras4_mezo.setColumns(10);
        
        szallitas_mezo = new JTextField();
        szallitas_mezo.setBounds(547, 255, 86, 20);
        add(szallitas_mezo);
        szallitas_mezo.setColumns(10);
        
        lblNewLabel_7 = new JLabel("Az utolsó szállítás dátumának ellenőrzése, ahol lehetséges a hiba. ");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(144, 258, 393, 14);
        add(lblNewLabel_7);
        
        lblNewLabel_8 = new JLabel("Ellenőrzött mennyiség");
        lblNewLabel_8.setBounds(547, 305, 134, 14);
        add(lblNewLabel_8);
        
        textField = new JTextField();
        textField.setBounds(547, 330, 86, 20);
        add(textField);
        textField.setColumns(10);
        
        lblNewLabel_9 = new JLabel("Hibás mennyiség");
        lblNewLabel_9.setBounds(691, 305, 105, 14);
        add(lblNewLabel_9);
        
        textField_1 = new JTextField();
        textField_1.setBounds(692, 330, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        lblNewLabel_10 = new JLabel("Félkész termékek válogatása: ");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_10.setBounds(288, 333, 249, 14);
        add(lblNewLabel_10);
        
        textField_2 = new JTextField();
        textField_2.setBounds(547, 368, 86, 20);
        add(textField_2);
        textField_2.setColumns(10);
        
        textField_3 = new JTextField();
        textField_3.setBounds(691, 368, 86, 20);
        add(textField_3);
        textField_3.setColumns(10);
        
        textField_4 = new JTextField();
        textField_4.setBounds(547, 410, 86, 20);
        add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(692, 410, 86, 20);
        add(textField_5);
        textField_5.setColumns(10);
        
        lblNewLabel_11 = new JLabel("Késztermékek válogatása: ");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_11.setBounds(355, 371, 182, 14);
        add(lblNewLabel_11);
        
        lblNewLabel_12 = new JLabel("Alapanyag válogatása:");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_12.setBounds(372, 413, 165, 14);
        add(lblNewLabel_12);
        
        lblNewLabel_13 = new JLabel("D3 pont lezárása");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_13.setBounds(389, 516, 148, 14);
        add(lblNewLabel_13);
        
        mentes_gomb = new JButton("D3 zárása");
        mentes_gomb.setBounds(547, 512, 119, 23);
        mentes_gomb.addActionListener(new Mentes());
        add(mentes_gomb);

    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Vevoireklamacio_fejlec.d3 = Color.GREEN;
                Vevoireklamacio_fejlec.d5 = Color.YELLOW;
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }


}
