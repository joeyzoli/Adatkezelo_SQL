import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Vevoireklamacio_d4 extends JPanel {

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d4() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Gyökérok analízis előfordulásra");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(270, 56, 231, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Anyag");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(415, 81, 86, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Gép");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(455, 139, 46, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Ember");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(455, 192, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Mód");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(455, 247, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Gyökérok analízis nem detektálhatóságra");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(191, 310, 310, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Anyag");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(455, 335, 46, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Gép");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(455, 396, 46, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Ember");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(455, 453, 46, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Mód");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(455, 509, 46, 14);
        add(lblNewLabel_9);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(523, 76, 720, 39);
        add(textArea);
        
        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(523, 134, 720, 39);
        add(textArea_1);
        
        JTextArea textArea_2 = new JTextArea();
        textArea_2.setBounds(523, 187, 720, 39);
        add(textArea_2);
        
        JTextArea textArea_3 = new JTextArea();
        textArea_3.setBounds(523, 242, 720, 39);
        add(textArea_3);
        
        JTextArea textArea_4 = new JTextArea();
        textArea_4.setBounds(523, 330, 720, 39);
        add(textArea_4);
        
        JTextArea textArea_5 = new JTextArea();
        textArea_5.setBounds(523, 391, 720, 39);
        add(textArea_5);
        
        JTextArea textArea_6 = new JTextArea();
        textArea_6.setBounds(523, 448, 720, 39);
        add(textArea_6);
        
        JTextArea textArea_7 = new JTextArea();
        textArea_7.setBounds(523, 504, 720, 39);
        add(textArea_7);

    }
}
