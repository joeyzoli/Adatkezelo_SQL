import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Vevoireklamacio_d6 extends JPanel {

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d6() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Intézkedések hatékonyságának megerősítése");
        lblNewLabel.setBounds(545, 49, 293, 14);
        add(lblNewLabel);
        
        JTextArea megerosites_mezo = new JTextArea();
        megerosites_mezo.setLineWrap(true);
        megerosites_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(megerosites_mezo);        
        gorgeto.setBounds(121, 84, 980, 465);
        add(gorgeto);

    }

}
