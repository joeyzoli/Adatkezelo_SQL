import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Teszt_kezdes extends JPanel 
{
    private JTextField nev_mezo;
    static int tesztszam;

    /**
     * Create the panel.
     */
    public Teszt_kezdes() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Üdvözöllek a folyamatellenőri vizsgán!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(434, 60, 301, 27);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Név");
        lblNewLabel_1.setBounds(449, 227, 46, 14);
        add(lblNewLabel_1);
        
        nev_mezo = new JTextField();
        nev_mezo.setBounds(520, 224, 227, 20);
        add(nev_mezo);
        nev_mezo.setColumns(10);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Start());
        start_gomb.setBounds(508, 333, 89, 23);
        add(start_gomb);

    }
    
    class Start implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                if((int)(Math.random() * 21) < 5)
                {
                    tesztszam = 0;
                }
                else
                {
                    tesztszam = 1;
                }
                Teszt_1 elso = new Teszt_1();
                Foablak.frame.setContentPane(elso);
                Foablak.frame.pack();
                System.out.println((int)(Math.random() * 21));
                
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
