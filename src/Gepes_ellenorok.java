import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Gepes_ellenorok extends JPanel 
{
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    private JTextField textField_1;
    private JTextField textField;
    static JTable table;

    /**
     * Create the panel.
     */
    public Gepes_ellenorok() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Gépes folyamatellenőri adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(466, 11, 294, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(493, 75, 69, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(493, 100, 69, 14);
        add(lblNewLabel_2);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(618, 72, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(618, 103, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(618, 134, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Ellenőr");
        lblNewLabel_3.setBounds(492, 131, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("NXT sor");
        lblNewLabel_4.setBounds(492, 168, 46, 14);
        add(lblNewLabel_4);
        
        textField = new JTextField();
        textField.setBounds(618, 165, 86, 20);
        add(textField);
        textField.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Ellenorzes_mutat());
        keres_gomb.setBounds(534, 213, 89, 23);
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(61, 270, 1039, 172);
        add(gorgeto);

    }
    
    class Ellenorzes_mutat implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String querry = "call qualitydb.projekt_lekerdezo(?,?,?,?)";                                                                 //tárolt eljárás Stringje
                SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                lekerdezo.lekerdez_ellenorok(querry, datumtol_mezo.getText(), datumig_mezo.getText());   //függvénymeghívása a paraméterekkel
             
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
