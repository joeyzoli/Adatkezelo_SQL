import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vevoi_reklamacio_lekerdezes extends JPanel 
{
    private JTextField datumtol;
    private JTextField datumig;
    static JTable table;
    private ComboBox combobox;
    private JComboBox<String> projekt_box;
    private JRadioButton lezart_gomb;
    private JRadioButton nyitott_gomb;

    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_lekerdezes() 
    {
        this.setPreferredSize(new Dimension(1100, 700));
        setLayout(null);
        
        combobox = new ComboBox();
        JLabel lblNewLabel = new JLabel("Vevői reklamációk lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(494, 11, 264, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(464, 57, 46, 14);
        add(lblNewLabel_1);
        
        projekt_box = new JComboBox<String>(combobox.getCombobox2(ComboBox.projekt));                   //combobox.getCombobox2(ComboBox.projekt)
        projekt_box.setBounds(520, 53, 172, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -tól");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(420, 93, 90, 14);
        add(lblNewLabel_2);
        
        datumtol = new JTextField();
        datumtol.setBounds(520, 90, 86, 20);
        add(datumtol);
        datumtol.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Dátim -ig");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(430, 124, 80, 14);
        add(lblNewLabel_3);
        
        datumig = new JTextField();
        datumig.setBounds(520, 121, 86, 20);
        add(datumig);
        datumig.setColumns(10);
        
        lezart_gomb = new JRadioButton("Nyitott");
        lezart_gomb.setBounds(482, 163, 66, 14);
        add(lezart_gomb);
        
        nyitott_gomb = new JRadioButton("Lezárt");
        nyitott_gomb.setBounds(592, 159, 80, 23);
        add(nyitott_gomb);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(520, 208, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50, 267, 1070, 200);
        add(pane);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.setBounds(520, 478, 89, 23);
        add(excel_gomb);
    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                
                SQL lekerdezes = new SQL();
                lekerdezes.vevoi_lekerdezes(String.valueOf(projekt_box.getSelectedItem()), datumtol.getText(), datumig.getText(), nyitott, lezart);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
               
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
