import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Vevoireklamacio_d4 extends JPanel {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d4() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Gyökérok analízis előfordulásra");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(270, 43, 231, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Anyag");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(415, 81, 86, 14);
        add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setBounds(511, 78, 705, 20);
        add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(511, 123, 705, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Gép");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(455, 126, 46, 14);
        add(lblNewLabel_2);
        
        textField_2 = new JTextField();
        textField_2.setBounds(511, 166, 705, 20);
        add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Ember");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(455, 169, 46, 14);
        add(lblNewLabel_3);
        
        textField_3 = new JTextField();
        textField_3.setBounds(511, 207, 705, 20);
        add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Mód");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(455, 210, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Gyökérok analízis nem detektálhatóságra");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(191, 272, 310, 14);
        add(lblNewLabel_5);
        
        textField_4 = new JTextField();
        textField_4.setBounds(511, 302, 705, 20);
        add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(511, 351, 705, 20);
        add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setBounds(511, 407, 705, 20);
        add(textField_6);
        textField_6.setColumns(10);
        
        textField_7 = new JTextField();
        textField_7.setBounds(511, 457, 705, 20);
        add(textField_7);
        textField_7.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Anyag");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(455, 305, 46, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Gép");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(455, 354, 46, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Ember");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(455, 410, 46, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Mód");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(455, 460, 46, 14);
        add(lblNewLabel_9);

    }

}
