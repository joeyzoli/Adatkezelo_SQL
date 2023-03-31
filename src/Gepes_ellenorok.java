import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Gepes_ellenorok extends JPanel 
{
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    private JTextField ellenor_mezo;
    private JTextField nxt_mezo;
    static JTable table;
    private JTextField cikkszam_mezo;
    static JTable table_1;

    /**
     * Create the panel.
     */
    public Gepes_ellenorok() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Gépes folyamatellenőri adatok lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(466, 11, 294, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(493, 58, 69, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(493, 89, 69, 14);
        add(lblNewLabel_2);
        
        datumtol_mezo = new JTextField();
        datumtol_mezo.setBounds(618, 55, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        datumig_mezo = new JTextField();
        datumig_mezo.setBounds(618, 86, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        ellenor_mezo = new JTextField();
        ellenor_mezo.setBounds(618, 117, 86, 20);
        add(ellenor_mezo);
        ellenor_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Ellenőr");
        lblNewLabel_3.setBounds(493, 120, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("NXT sor");
        lblNewLabel_4.setBounds(493, 151, 46, 14);
        add(lblNewLabel_4);
        
        nxt_mezo = new JTextField();
        nxt_mezo.setBounds(618, 148, 86, 20);
        add(nxt_mezo);
        nxt_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Ellenorzes_mutat());
        keres_gomb.setBounds(534, 213, 89, 23);
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(61, 270, 1039, 172);
        add(gorgeto);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(618, 179, 86, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Cikkszám");
        lblNewLabel_5.setBounds(493, 182, 69, 14);
        add(lblNewLabel_5);
        
        table_1 = new JTable();
        JScrollPane gorgeto2 = new JScrollPane(table_1);
        gorgeto2.setBounds(61, 453, 1039, 239);
        add(gorgeto2);

    }
    
    class Ellenorzes_mutat implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String ellenor ="";
                String nxt = "";
                String cikk = "";
                if(ellenor_mezo.getText().equals("")){             
                    ellenor = "%";
                }
                else{              
                    ellenor = ellenor_mezo.getText();
                }
                if(nxt_mezo.getText().equals("")) {
                    nxt = "%";
                }
                else {
                    nxt = nxt_mezo.getText();
                }
                if(cikkszam_mezo.getText().equals("")) {
                    cikk = "%";
                }
                else {
                    cikk = cikkszam_mezo.getText();
                }
                String sql = "select * from qualitydb.Folyamatellenori_gyartas where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() +"' and Nev like '"+ ellenor +
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";                                                                 //tárolt eljárás Stringje
                String sql2 = "select * from qualitydb.Folyamatellenori_nxt where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() + "' and Nev like '"+ ellenor +                       
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";
                SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                lekerdezo.lekerdez_ellenorok(sql, sql2);   //függvénymeghívása a paraméterekkel
             
                Foablak.frame.setCursor(null);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
      
    }
}
