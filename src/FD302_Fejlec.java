import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class FD302_Fejlec extends JPanel {

    /**
     * Create the panel.
     */
    public FD302_Fejlec() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(85, 50, 46, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("AVM FD302 OQC adatgyűjtő");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(598, 23, 251, 14);
        add(lblNewLabel_1);

    }
}
