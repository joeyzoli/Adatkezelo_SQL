import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.JLabel;
import java.awt.Font;

public class Beszallitoi_PPM extends JPanel {
    
    private JXDatePicker datum_tol;
    private JXDatePicker datum_ig;

    /**
     * Create the panel.
     */
    public Beszallitoi_PPM() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Beszállítói PPM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(556, 35, 167, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(507, 83, 86, 14);
        add(lblNewLabel_1);
        
        datum_tol = new JXDatePicker();
        datum_tol.setBounds(603, 80, 120, 20);
        add(datum_tol);

        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(505, 119, 72, 14);
        add(lblNewLabel_2);
        
        datum_ig = new JXDatePicker();
        datum_ig.setBounds(603, 116, 120, 20);
        add(datum_ig);

    }
}
