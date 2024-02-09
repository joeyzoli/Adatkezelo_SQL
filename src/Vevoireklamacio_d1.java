import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Vevoireklamacio_d1 extends JPanel {
    private JTextField vezeto_mezo;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d1() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Csapatvezető");
        lblNewLabel.setBounds(425, 51, 90, 14);
        add(lblNewLabel);
        
        vezeto_mezo = new JTextField();
        vezeto_mezo.setBounds(553, 48, 263, 20);
        add(vezeto_mezo);
        vezeto_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Csapat tagjai");
        lblNewLabel_1.setBounds(425, 98, 99, 14);
        add(lblNewLabel_1);
        
        JTextArea tagok_mezo = new JTextArea();
        tagok_mezo.setLineWrap(true);
        tagok_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(tagok_mezo);
        gorgeto.setBounds(553, 93, 263, 248);
        add(gorgeto);
        
        setBackground(Foablak.hatter_szine);

    }
}
