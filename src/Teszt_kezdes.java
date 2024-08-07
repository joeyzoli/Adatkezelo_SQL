import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Teszt_kezdes extends JPanel 
{
    private JTextField nev_mezo;
    static int tesztszam;
    static Long timer_start;
    static int id = 0;
    private Dimension meretek = new Dimension(1100, 870);

    /**
     * Create the panel.
     */
    public Teszt_kezdes() 
    {
        setLayout(null);
        this.setPreferredSize(meretek);
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
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Start implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                if(nev_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg nevet!!", "Hiba üzenet", 2);
                    return;
                }
                else
                {
                    int veletlen = (int)(Math.random() * 25);
                    if(veletlen < 5)
                    {
                        tesztszam = 0;
                    }
                    else if(veletlen >= 5 && veletlen < 11)
                    {
                        tesztszam = 1;
                    }
                    else  if(veletlen >= 11)
                    {
                        tesztszam = 2;
                    }
                    SimpleDateFormat idopont = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());                 
                    String sql = "INSERT INTO qualitydb.Ellenori_vizsga (Nev, Datum, Valtozat)VALUES ('" + nev_mezo.getText() +"', '"+ idopont.format(timestamp) +"', '"+ tesztszam +"') ";
                    SQL_teszt dbiras = new SQL_teszt();
                    dbiras.iras(sql, nev_mezo.getText(), idopont.format(timestamp));
                    Teszt_1 elso = new Teszt_1();
                    JScrollPane ablak = new JScrollPane(elso);
                    ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    Foablak.frame.setContentPane(ablak);
                    Foablak.frame.pack();
                    measureTime(true);
                }
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
    
    static public float measureTime(boolean run)                    //idõmérõ metódus
    {
        long current_time = System.nanoTime();                      //a rendszeridõt nekiadjuk egy változónak
                
        if (run == true)                                            //ha igazra állítjuk elindul
        {
                timer_start = System.nanoTime();                    //idõzítõ indulási értéke a rendszer aktuális ideje
                return (-1.0f);
        }
        else
        {
            long elapsed_time = current_time - timer_start;         //ha false lesz az érték
            return (elapsed_time);                                  //visszatér a különbséggel
        }
    }
}
