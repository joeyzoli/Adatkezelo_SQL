import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class PO_szam extends JPanel 
{
    private JTextField po_szam_mezo;

    /**
     * Create the panel.
     */
    public PO_szam() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("PO számok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNewLabel.setBounds(499, 63, 190, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("PO szám:");
        lblNewLabel_1.setBounds(409, 182, 64, 14);
        add(lblNewLabel_1);
        
        po_szam_mezo = new JTextField();
        po_szam_mezo.setBounds(499, 179, 150, 20);
        add(po_szam_mezo);
        po_szam_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(694, 178, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);

    }
    
    class Kereses implements ActionListener                                                                                       //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQL po_kereses = new SQL();
                po_kereses.po_kereses(po_szam_mezo.getText());
                Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
      
    }
}