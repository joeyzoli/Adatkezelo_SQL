import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Vevoi_reklamacio_lezaras extends JPanel 
{
    private JTextField datum_mezo;
    private JTextField tipus_mezo;
    static JTable table;
    static JTable table_1;

    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_lezaras() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vevői reklamációk nyitott pont lezárása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(355, 23, 353, 20);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(405, 88, 46, 14);
        add(lblNewLabel_1);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(509, 85, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(405, 128, 46, 14);
        add(lblNewLabel_2);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(509, 125, 86, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keres");
        keres_gomb.setBounds(449, 156, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        table.setBounds(161, 201, 738, 127);
        add(table);
        
        table_1 = new JTable();
        table_1.setBounds(161, 339, 738, 123);
        add(table_1);
        
        JButton btnNewButton_1 = new JButton("Lezárás");
        btnNewButton_1.setBounds(449, 508, 89, 23);
        add(btnNewButton_1);

    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL kereses = new SQL();
                kereses.vevoi_lezarashoz(datum_mezo.getText(), tipus_mezo.getText());
                
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
