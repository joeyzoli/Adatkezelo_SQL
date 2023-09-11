import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Hitlista extends JPanel 
{
    private JTextField datumtol;
    private JTextField datumig;
    private JTextField pozicio;
    private ComboBox combobox_tomb;
    private JComboBox<String> projekt_box;
    private JComboBox<String> cikk_box;

    /**
     * Create the panel.
     */
    public Hitlista() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Hit lista lekérdezése");
        lblNewLabel.setForeground(Color.BLUE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(426, 45, 127, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setBounds(365, 116, 46, 14);
        add(lblNewLabel_1);
        
        combobox_tomb = new ComboBox();
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.projekt));
        projekt_box.setBounds(532, 112, 142, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(365, 165, 58, 14);
        add(lblNewLabel_2);
        
        cikk_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));
        cikk_box.setBounds(532, 161, 195, 22);
        add(cikk_box);
        
        JLabel lblNewLabel_3 = new JLabel("Dátum -tól");
        lblNewLabel_3.setBounds(365, 207, 75, 14);
        add(lblNewLabel_3);
        
        datumtol = new JTextField();
        datumtol.setBounds(532, 204, 112, 20);
        add(datumtol);
        datumtol.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Dátum -ig");
        lblNewLabel_4.setBounds(365, 244, 58, 14);
        add(lblNewLabel_4);
        
        datumig = new JTextField();
        datumig.setBounds(532, 241, 112, 20);
        add(datumig);
        datumig.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Pozíció");
        lblNewLabel_5.setBounds(365, 285, 46, 14);
        add(lblNewLabel_5);
        
        pozicio = new JTextField();
        pozicio.setBounds(532, 282, 142, 20);
        add(pozicio);
        pozicio.setColumns(10);
        
        JButton lekerdez = new JButton("Lekérdezés");
        lekerdez.addActionListener(new Hitlista_lekerdezo());
        lekerdez.setBounds(426, 351, 110, 24);
        add(lekerdez);

    }
    
    class Hitlista_lekerdezo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String[] koztes = String.valueOf(cikk_box.getSelectedItem()).split(" - ");
                System.out.println(String.valueOf(cikk_box.getSelectedItem()));
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String minden = "%";
                SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                if(String.valueOf(cikk_box.getSelectedItem()).equals("-"))
                {
                    lekerdezo.hitlista(String.valueOf(projekt_box.getSelectedItem()), minden, datumtol.getText(), datumig.getText(), pozicio.getText());
                }
                else
                {
                    lekerdezo.hitlista(String.valueOf(projekt_box.getSelectedItem()), koztes[0], datumtol.getText(), datumig.getText(), pozicio.getText());
                }
                Foablak.frame.setCursor(null);
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
