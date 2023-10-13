import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Ellenori_lapok extends JPanel {
    private JTextField datum_mezo;

    /**
     * Create the panel.
     */
    public Ellenori_lapok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Begyűjtött ellenőri lapok rögzítése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(481, 27, 285, 14);
        add(lblNewLabel);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(108, 73, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(52, 76, 46, 14);
        add(lblNewLabel_1);

    }

}
