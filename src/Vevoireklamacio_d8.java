import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vevoireklamacio_d8 extends JPanel {
    private JTextField textField;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d8() {
        setBackground(Foablak.hatter_szine);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Megelőző intézkedés(ek) validálása/szabványosítása ");
        lblNewLabel.setBounds(583, 48, 320, 14);
        add(lblNewLabel);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(114, 90, 1183, 356);
        add(textArea);
        
        JLabel lblNewLabel_1 = new JLabel("Lezárás dátuma");
        lblNewLabel_1.setBounds(590, 527, 106, 14);
        add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(706, 524, 86, 20);
        add(textField);
        textField.setColumns(10);

    }

}
