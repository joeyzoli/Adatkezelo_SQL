import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class Ellenorok extends JPanel 
{
    private JTextField ujnev_mezo;
    private JTextField ujpecset_mezo;
    private JTextField nev_mezo;
    private JTextField pecset_mezo;
    static JTable table;

    /**
     * Create the panel.
     */
    public Ellenorok() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Folyamatellenőri névsor kezelése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(484, 21, 242, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Név");
        lblNewLabel_1.setBounds(507, 66, 46, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Pecsét száma");
        lblNewLabel_2.setBounds(450, 113, 97, 14);
        add(lblNewLabel_2);
        
        ujnev_mezo = new JTextField();
        ujnev_mezo.setBounds(563, 63, 222, 20);
        add(ujnev_mezo);
        ujnev_mezo.setColumns(10);
        
        ujpecset_mezo = new JTextField();
        ujpecset_mezo.setBounds(563, 110, 46, 20);
        add(ujpecset_mezo);
        ujpecset_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(520, 160, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Új ellenőr felvitele:");
        lblNewLabel_3.setBounds(294, 91, 133, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Pecsétszám megadása:");
        lblNewLabel_4.setBounds(294, 238, 165, 14);
        add(lblNewLabel_4);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(55, 205, 1087, 9);
        add(separator);
        
        JLabel lblNewLabel_5 = new JLabel("Név");
        lblNewLabel_5.setBounds(507, 238, 46, 14);
        add(lblNewLabel_5);
        
        nev_mezo = new JTextField();
        nev_mezo.setBounds(563, 235, 222, 20);
        add(nev_mezo);
        nev_mezo.setColumns(10);
        
        pecset_mezo = new JTextField();
        pecset_mezo.setBounds(563, 266, 46, 20);
        add(pecset_mezo);
        pecset_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Pecsét száma");
        lblNewLabel_6.setBounds(450, 269, 97, 14);
        add(lblNewLabel_6);
        
        JButton keres_gomb = new JButton("Keres");
        keres_gomb.addActionListener(new Kereses());
        keres_gomb.setBounds(520, 310, 89, 23);
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(334, 366, 450, 136);
        add(scroll);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(520, 571, 89, 23);
        add(excel_gomb);
        
        JButton pecset_mentes = new JButton("Pecsétszám mentés");
        pecset_mentes.addActionListener(new Pecset());
        pecset_mentes.setBounds(496, 525, 143, 23);
        add(pecset_mentes);
        setBackground(Foablak.hatter_szine);
    }
    
    class Mentes implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {             
                Db_iro iro = new Db_iro();
                String sql = "insert into qualitydb.Alapadatok_ellenorok (Nev, Pecset_szam) Values('"+ ujnev_mezo.getText() +"','"+ ujpecset_mezo.getText() +"')";
                iro.ellenori_nevek(sql);
                JOptionPane.showMessageDialog(null, "Kész!", "Info", 1);
                ujnev_mezo.setText("");
                ujpecset_mezo.setText("");
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
    }
    
    class Kereses implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {             
                SQL lekerdez = new SQL();
                String nev = "";
                String sql = "";
                if(pecset_mezo.getText().equals(""))
                { 
                    if(nev_mezo.getText().equals(""))
                    { 
                        nev = "%";
                    }
                    else
                    { 
                        nev = "%"+ nev_mezo.getText() +"%";
                    }
                    sql = "select * from qualitydb.Alapadatok_ellenorok where nev like '"+ nev +"' order by nev asc";
                }
                else
                { 
                    sql = "select * from qualitydb.Alapadatok_ellenorok where Pecset_szam = '"+ pecset_mezo.getText() +"' order by nev asc";
                }
                lekerdez.lekerdez_ellenori_nevsor(sql, 1);               
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
    }
    
    class Pecset implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {             
                Db_iro iro = new Db_iro();
                for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                {
                    String sql = "update qualitydb.Alapadatok_ellenorok set Pecset_szam = '"+ table.getValueAt(szamlalo, 2).toString() +"' where ID = '"+ table.getValueAt(szamlalo, 0).toString() +"'" ;
                    iro.ellenori_nevek(sql);
                }
                JOptionPane.showMessageDialog(null, "Kész!", "Info", 1); 
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
    }
    
    class Excel implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {             
                SQL lekerdez = new SQL();
                String nev = "";
                String sql = "";
                if(pecset_mezo.getText().equals(""))
                { 
                    if(nev_mezo.getText().equals(""))
                    { 
                        nev = "%";
                    }
                    else
                    { 
                        nev = nev_mezo.getText();
                    }
                    sql = "select * from qualitydb.Alapadatok_ellenorok where nev like '"+ nev +"' order by nev asc";
                }
                else
                { 
                    sql = "select * from qualitydb.Alapadatok_ellenorok where Pecset_szam = '"+ pecset_mezo.getText() +"' order by nev asc";
                }
                lekerdez.lekerdez_ellenori_nevsor(sql, 2);
                JOptionPane.showMessageDialog(null, "Kész!", "Info", 1); 
                
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
    }
}
