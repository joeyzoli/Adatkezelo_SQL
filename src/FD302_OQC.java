import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class FD302_OQC extends JPanel {
    private JTextField raklap_mezo;
    private JTextField doboz_mezo;
    private JTextField kod_mezo;
    private JTextField dm_telepitett;
    private JTextField textField_1;
    private JComboBox<String> hibacsoport_box;
    private JComboBox<String> hiba_box;
    private JComboBox<String> hibakategoria_box;
    private static Long timer_start;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField kod2_mezo;
    private JTextField textField_12;
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
        
        textField_1 = new JTextField();
        textField_1.setBounds(335, 216, 49, 20);
        add(textField_1);
        textField_1.setColumns(10);        
        
        JLabel lblNewLabel_19 = new JLabel("Hibacsoport");
        lblNewLabel_19.setBounds(38, 361, 103, 14);
        add(lblNewLabel_19);
        
        String[] hibacsoport = {"","Címke","Funkció","Papír","Tartozék","Termék","Szerelés"};
        hibacsoport_box = new JComboBox<String>();                                              //hibacsoport
        hibacsoport_box.setBounds(167, 357, 215, 22);
        add(hibacsoport_box);
        
        JLabel lblNewLabel_20 = new JLabel("Hiba");
        lblNewLabel_20.setBounds(38, 406, 46, 14);
        add(lblNewLabel_20);
        
        hiba_box = new JComboBox<String>();                //combobox_tomb.getCombobox(ComboBox.hibakodok)
        hiba_box.setBounds(167, 402, 215, 22);
        add(hiba_box);
        
        JLabel lblNewLabel_21 = new JLabel("Hiba kategória");
        lblNewLabel_21.setBounds(38, 453, 103, 14);
        add(lblNewLabel_21);
        
        String[] kategoria = {"","Kritikus hiba","Súlyos hiba","Enyhe hiba"};
        hibakategoria_box = new JComboBox<String>();                                    //kategoria
        hibakategoria_box.setBounds(167, 449, 215, 22);
        add(hibakategoria_box);
        
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
        
        JLabel lblNewLabel_12 = new JLabel("Article Number");
        lblNewLabel_12.setBounds(489, 35, 105, 14);
        add(lblNewLabel_12);
        
        textField_6 = new JTextField();
        textField_6.setBounds(665, 32, 125, 20);
        add(textField_6);
        textField_6.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("IPEI (GUI-ból)");
        lblNewLabel_13.setBounds(489, 85, 105, 14);
        add(lblNewLabel_13);
        
        textField_7 = new JTextField();
        textField_7.setBounds(665, 82, 125, 20);
        add(textField_7);
        textField_7.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Firmware verzió (GUI-ból)");
        lblNewLabel_14.setBounds(489, 126, 153, 14);
        add(lblNewLabel_14);
        
        textField_8 = new JTextField();
        textField_8.setBounds(665, 123, 125, 20);
        add(textField_8);
        textField_8.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Akkumulátor (GUI-ból)");
        lblNewLabel_15.setBounds(489, 175, 153, 14);
        add(lblNewLabel_15);
        
        textField_9 = new JTextField();
        textField_9.setBounds(665, 172, 125, 20);
        add(textField_9);
        textField_9.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Hőmérséklet (GUI-ból)");
        lblNewLabel_16.setBounds(489, 219, 153, 14);
        add(lblNewLabel_16);
        
        textField_10 = new JTextField();
        textField_10.setBounds(665, 216, 125, 20);
        add(textField_10);
        textField_10.setColumns(10);
        
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
        
        textField_12 = new JTextField();
        textField_12.setBounds(665, 307, 125, 20);
        add(textField_12);
        textField_12.setColumns(10);
        
        JLabel lblNewLabel_22 = new JLabel("Megjegyzés");
        lblNewLabel_22.setBounds(489, 361, 93, 14);
        add(lblNewLabel_22);
        
        JTextArea megjegyzes_mezo = new JTextArea();
        megjegyzes_mezo.setLineWrap(true);
        megjegyzes_mezo.setWrapStyleWord(true);
        megjegyzes_mezo.setBounds(592, 356, 198, 111);
        add(megjegyzes_mezo);
        
        JButton mentes_gomb = new JButton("Mentés");
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
        fehler_box = new JComboBox<String> (eredmeny);
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
}
