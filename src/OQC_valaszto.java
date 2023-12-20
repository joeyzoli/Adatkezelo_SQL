import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OQC_valaszto extends JPanel {

    /**
     * Create the panel.
     */
    public OQC_valaszto() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("OQC-zendő termék kiválasztása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(488, 32, 275, 14);
        add(lblNewLabel);
        
        JButton fr600 = new JButton("FR600");
        fr600.setBounds(536, 98, 89, 23);
        add(fr600);
        
        JButton fr1200 = new JButton("FR1200");
        fr1200.setBounds(536, 161, 89, 23);
        add(fr1200);
        
        JButton fr2400 = new JButton("FR2400");
        fr2400.setBounds(536, 227, 89, 23);
        add(fr2400);
        
        JButton fb7530 = new JButton("FB7530");
        fb7530.addActionListener(new FB7530AX());
        fb7530.setBounds(536, 297, 89, 23);
        add(fb7530);
        
        JButton fd302 = new JButton("FD302");
        fd302.setBounds(536, 368, 89, 23);
        add(fd302);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class FB7530AX implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {               
                FB7530 fb7530 = new FB7530();
                JScrollPane ablak = new JScrollPane(fb7530);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
