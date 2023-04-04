import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Retour_lekerdez extends JPanel 
{
    private JTextField datumtol;
    private JTextField datumig;
    static JTable table;
    private SQL lekerdezes = new SQL();
    private JTextField id_mezo;
    /**
     * Create the panel.
     */
    public Retour_lekerdez() 
    {
        this.setPreferredSize(new Dimension(1906, 700));
        setLayout(null);
        
        new ComboBox();
        JLabel lblNewLabel = new JLabel("Retour adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(481, 33, 264, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -tól");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(420, 93, 90, 14);
        add(lblNewLabel_2);
        
        datumtol = new JTextField();
        datumtol.setBounds(520, 90, 86, 20);
        datumtol.setText("2023.01.01");
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
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(517, 204, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(54, 288, 1806, 226);
        add(pane);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(520, 152, 86, 20);
        
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(464, 155, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_8 = new JLabel("Excel");
        lblNewLabel_8.setBounds(443, 565, 46, 14);
        add(lblNewLabel_8);
        
        JButton excelmentes = new JButton("Mentés");
        excelmentes.addActionListener(new Excelmentes());
        excelmentes.setBounds(517, 561, 89, 23);
        add(excelmentes);
        ido();
    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.lekerdez_retour(datumtol.getText(), datumig.getText(), id_mezo.getText());
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                lekerdezes.lekerdez_retour(datumtol.getText(), datumig.getText(), id_mezo.getText());
            }
         
        }
        @Override
        public void keyTyped(KeyEvent e)                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            // TODO Auto-generated method stub           
        }    
    }
    
    class Excelmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.retour_excel(datumtol.getText(), datumig.getText(), id_mezo.getText());
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datumig.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }

}
