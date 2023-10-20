import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CSV extends JPanel {

    /**
     * Create the panel.
     */
    public CSV() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("CSV készítő");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(552, 54, 103, 14);
        add(lblNewLabel);
        
        JButton csv_gomb = new JButton("Megnyitás");
        csv_gomb.addActionListener(new CSV_gyart());
        csv_gomb.setBounds(539, 154, 89, 23);
        add(csv_gomb);

    }
    
    class CSV_gyart implements ActionListener                                                                                       //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
             {
                CSV_gyarto csv = new CSV_gyarto();
                csv.csvalakito();
             }
            catch(Exception ex2)
             {
                ex2.printStackTrace();
                String hibauzenet = ex2.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 1);
             }
            
         }
    }

}
