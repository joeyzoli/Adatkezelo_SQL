import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Teszt_vege extends JPanel 
{
    private JTextField ido_mezo;

    /**
     * Create the panel.
     */
    public Teszt_vege() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Sikeresen kitöltötted a tesztet!");
        lblNewLabel.setFont(new Font("Algerian", Font.ITALIC, 18));
        lblNewLabel.setBounds(389, 162, 425, 24);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Teszt kitöltésének ideje");
        lblNewLabel_1.setBounds(351, 240, 170, 14);
        add(lblNewLabel_1);
        
        ido_mezo = new JTextField();
        ido_mezo.setEditable(false);
        ido_mezo.setBounds(491, 237, 158, 20);
        ido_mezo.setText(String.valueOf(Teszt_kezdes.measureTime(false)/1000000000));
        add(ido_mezo);
        ido_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Másodperc");
        lblNewLabel_2.setBounds(673, 240, 94, 14);
        add(lblNewLabel_2);
        
        setBackground(Foablak.hatter_szine);
    }
}
