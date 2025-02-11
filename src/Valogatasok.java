import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Valogatasok extends JPanel {
    private JTextField datum_mezo;
    private JTextField ok_mezo;
    private JTextField nok_mezo;
    private JTextField muszak_mezo;
    private JTextField ossz_mezo;
    private JTextField id_mezo;

    /**
     * Create the panel.
     */
    public Valogatasok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Válogatás eredményének rögzítése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(416, 29, 293, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cikkszám");
        lblNewLabel_1.setBounds(47, 96, 64, 14);
        add(lblNewLabel_1);
        
        JComboBox<String> cikk_box = new JComboBox<String>();
        cikk_box.setBounds(121, 92, 278, 22);
        add(cikk_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum");
        lblNewLabel_2.setBounds(441, 96, 46, 14);
        add(lblNewLabel_2);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(497, 93, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("OK");
        lblNewLabel_3.setBounds(200, 144, 46, 14);
        add(lblNewLabel_3);
        
        ok_mezo = new JTextField();
        ok_mezo.setBounds(230, 141, 86, 20);
        add(ok_mezo);
        ok_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("NOK");
        lblNewLabel_4.setBounds(358, 144, 46, 14);
        add(lblNewLabel_4);
        
        nok_mezo = new JTextField();
        nok_mezo.setBounds(396, 141, 86, 20);
        add(nok_mezo);
        nok_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(47, 144, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(121, 141, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Válogatásra váró mennyiség");
        lblNewLabel_6.setBounds(616, 96, 172, 14);
        add(lblNewLabel_6);
        
        ossz_mezo = new JTextField();
        ossz_mezo.setBounds(798, 93, 86, 20);
        add(ossz_mezo);
        ossz_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("ID");
        lblNewLabel_7.setBounds(47, 51, 46, 14);
        add(lblNewLabel_7);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(121, 48, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);

    }

}
