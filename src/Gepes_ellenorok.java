import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class Gepes_ellenorok extends JPanel 
{
    private JTextField datumtol_mezo;
    private JTextField datumig_mezo;
    static JTable table;
    static JTable table_1;
    private JComboBox<String> ellenor_box;
    private JComboBox<String> nxt_box;
    private ArrayList<String> ellenorok = new ArrayList<String>();
    private DefaultComboBoxModel<String> model;
    private JTextField cikkszam_mezo;
    private Dimension meretek = new Dimension(1820, 850);

    /**
     * Create the panel.
     */
    public Gepes_ellenorok() 
    {
        setLayout(null);
        setPreferredSize(meretek);
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
        
        datumtol_mezo = new JTextField("2023.04.21");
        datumtol_mezo.setBounds(618, 55, 86, 20);
        add(datumtol_mezo);
        datumtol_mezo.setColumns(10);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datumig_mezo = new JTextField(formatter.format(date));
        datumig_mezo.setBounds(618, 86, 86, 20);
        add(datumig_mezo);
        datumig_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Ellenőr");
        lblNewLabel_3.setBounds(493, 120, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("NXT sor");
        lblNewLabel_4.setBounds(493, 151, 46, 14);
        add(lblNewLabel_4);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Ellenorzes_mutat());
        keres_gomb.setBounds(534, 213, 89, 23);
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(10, 270, 1765, 172);
        add(gorgeto);
        
        JLabel lblNewLabel_5 = new JLabel("Cikkszám");
        lblNewLabel_5.setBounds(493, 182, 69, 14);
        add(lblNewLabel_5);
        
        table_1 = new JTable();
        JScrollPane gorgeto2 = new JScrollPane(table_1);
        gorgeto2.setBounds(10, 453, 1765, 239);
        add(gorgeto2);
        
        ellenor_box = new JComboBox<String>();
        ellenor_box.setBounds(618, 117, 182, 22);
        add(ellenor_box);
        
        String[] nxtk = {"-","NXT01","NXT02","NXT03","NXT04","NXT05","NXT06","NXT07","NXT08","NXT09","NXT10"};
        nxt_box = new JComboBox<String>(nxtk);          //nxtk
        nxt_box.setBounds(618, 147, 69, 22);
        add(nxt_box);
        
        cikkszam_mezo = new JTextField();
        cikkszam_mezo.setBounds(618, 179, 182, 20);
        add(cikkszam_mezo);
        cikkszam_mezo.setColumns(10);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(534, 740, 89, 23);
        add(excel_gomb);
        setBackground(Foablak.hatter_szine);
        Ellenori_nevsor();
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
                if(ellenor_box.getSelectedItem().equals("-")){             
                    ellenor = "%";
                }
                else{              
                    ellenor = String.valueOf(ellenor_box.getSelectedItem());
                }
                if(nxt_box.getSelectedItem().equals("-")) {
                    nxt = "%";
                }
                else {
                    nxt = String.valueOf(nxt_box.getSelectedItem());
                }
                if(cikkszam_mezo.getText().equals("")) {
                    cikk = "%";
                }
                else {
                    cikk = String.valueOf(cikkszam_mezo.getText());
                }
                String sql = "select * from qualitydb.Folyamatellenori_gyartas where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() +"' and Nev like '"+ ellenor +
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";                                                                 
                String sql2 = "select * from qualitydb.Folyamatellenori_nxt where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() + "' and Nev like '"+ ellenor +                       
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";
                String sql3 = "select Kep, Kep_nev from qualitydb.Folyamatellenori_kepek where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() +"' and Nev like '"+ ellenor +
                        "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";
                SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                lekerdezo.lekerdez_ellenorok(sql, sql2, sql3, 1);   //függvénymeghívása a paraméterekkel
             
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
    
    class Excel implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String ellenor ="";
                String nxt = "";
                String cikk = "";
                if(ellenor_box.getSelectedItem().equals("-")){             
                    ellenor = "%";
                }
                else{              
                    ellenor = String.valueOf(ellenor_box.getSelectedItem());
                }
                if(nxt_box.getSelectedItem().equals("-")) {
                    nxt = "%";
                }
                else {
                    nxt = String.valueOf(nxt_box.getSelectedItem());
                }
                if(cikkszam_mezo.getText().equals("")) {
                    cikk = "%";
                }
                else {
                    cikk = String.valueOf(cikkszam_mezo.getText());
                }
                String sql = "select * from qualitydb.Folyamatellenori_gyartas where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() +"' and Nev like '"+ ellenor +
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";                                                                 
                String sql2 = "select * from qualitydb.Folyamatellenori_nxt where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() + "' and Nev like '"+ ellenor +                       
                                "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";
                String sql3 = "select Kep, Kep_nev from qualitydb.Folyamatellenori_kepek where Datum >= '"+ datumtol_mezo.getText() +"' and Datum <= '"+ datumig_mezo.getText() +"' and Nev like '"+ ellenor +
                        "' and NXT like '"+ nxt +"' and cikkszam like '"+ cikk +"'";
                SQL lekerdezo = new SQL();                                                                                                  //példányosítás
                lekerdezo.lekerdez_ellenorok(sql, sql2, sql3, 2);   //függvénymeghívása a paraméterekkel
             
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
    
    private void Ellenori_nevsor()
    {
        try
        {
            Connection conn = null;
            Statement stmt = null;
            try 
            {
               Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (Exception e) 
            {
               System.out.println(e);
               String hibauzenet2 = e.toString();
               JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
             conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
             stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);           
             String sql = "Select nev from qualitydb.Alapadatok_ellenorok where 3=3 order by nev asc";
             stmt.execute(sql);
             ResultSet resultSet = stmt.getResultSet();
             ellenorok.add("-");
             while(resultSet.next())
             {
                 ellenorok.add(resultSet.getString(1));
             }
             String[] ellenortomb = (String[]) ellenorok.toArray(new String[0]);
             model = new DefaultComboBoxModel<String>(ellenortomb);
             ellenor_box.setModel(model);            
        }           
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet2 = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }                                                  
    }   
}
