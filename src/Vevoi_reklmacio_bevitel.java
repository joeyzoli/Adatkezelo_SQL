import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Vevoi_reklmacio_bevitel extends JPanel 
{
    private JTextField ev_mezo;
    private JTextField honap_mezo;
    private JTextField datum_mezo;
    private JTextField textField;
    private JTextField textField_1;
    private ComboBox combobox_tomb = new ComboBox();

    /**
     * Create the panel.
     */
    public Vevoi_reklmacio_bevitel() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vevői reklamációk felvitele");
        lblNewLabel.setBounds(551, 23, 153, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Év:");
        lblNewLabel_1.setBounds(34, 72, 33, 14);
        add(lblNewLabel_1);
        
        ev_mezo = new JTextField();
        ev_mezo.setBounds(77, 69, 60, 20);
        add(ev_mezo);
        ev_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Hónap");
        lblNewLabel_2.setBounds(169, 72, 46, 14);
        add(lblNewLabel_2);
        
        honap_mezo = new JTextField();
        honap_mezo.setBounds(225, 69, 46, 20);
        add(honap_mezo);
        honap_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Projekt");
        lblNewLabel_3.setBounds(323, 72, 46, 14);
        add(lblNewLabel_3);
        
        JComboBox projekt_box = new JComboBox();
        projekt_box.setBounds(379, 68, 124, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(551, 72, 46, 14);
        add(lblNewLabel_4);
        
        JComboBox tipus_box = new JComboBox();
        tipus_box.setBounds(607, 68, 134, 22);
        add(tipus_box);
        
        JComboBox vagy_vagy = new JComboBox();
        vagy_vagy.setBounds(803, 68, 124, 22);
        add(vagy_vagy);
        
        JLabel lblNewLabel_5 = new JLabel("Dátum");
        lblNewLabel_5.setBounds(962, 72, 46, 14);
        add(lblNewLabel_5);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(1025, 69, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Reklamált db");
        lblNewLabel_6.setBounds(34, 139, 86, 14);
        add(lblNewLabel_6);
        
        textField = new JTextField();
        textField.setBounds(110, 136, 40, 20);
        add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Hiba leírása");
        lblNewLabel_7.setBounds(225, 139, 72, 14);
        add(lblNewLabel_7);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(323, 134, 274, 59);
        add(textArea);
        
        JLabel lblNewLabel_8 = new JLabel("Gyártás időpontja");
        lblNewLabel_8.setBounds(648, 139, 93, 14);
        add(lblNewLabel_8);
        
        textField_1 = new JTextField();
        textField_1.setBounds(751, 136, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);

    }
}
