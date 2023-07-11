import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Ell_szeriaszam extends JPanel {
    private JTextField datum_mezo;
    private ComboBox combobox_tomb =  new ComboBox();
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Átvételi adatok.xlsx";
    private JComboBox<String> projekt_box;

    /**
     * Create the panel.
     */
    public Ell_szeriaszam() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Végátvételi adatok lekérdezése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBounds(500, 47, 331, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setBounds(472, 116, 46, 14);
        add(lblNewLabel_1);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.projekt));
        projekt_box.setBounds(575, 112, 193, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum");
        lblNewLabel_2.setBounds(472, 166, 46, 14);
        add(lblNewLabel_2);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(575, 163, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Kereses());
        keres_gomb.setBounds(539, 245, 89, 23);
        add(keres_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Kereses implements ActionListener                                                                                       //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQL kereses = new SQL();
                String sql = "select * from qualitydb.Gyartasi_adatok where Datum ='"+ datum_mezo.getText() +"' and Vevo = '"+ String.valueOf(projekt_box.getSelectedItem()) +"'";
                kereses.lekerdez_szeriaszamok(sql, menteshelye);
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
