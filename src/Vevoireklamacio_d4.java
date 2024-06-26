import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;

public class Vevoireklamacio_d4 extends JPanel {

    /**
     * Create the panel.
     */
    
    private JTextArea anyag_elo;
    private JTextArea gep_elo;
    private JTextArea ember_elo;
    private JTextArea mod_elo;
    private JTextArea anyag_det;
    private JTextArea gep_det;
    private JTextArea ember_det;
    private JTextArea mod_det;
    private JCheckBox eloanyag_gomb;
    private JCheckBox elogep_gomb;    
    private JCheckBox eloember_gomb;
    private JCheckBox elomod_gomb;
    private JCheckBox detanyag_gomb;
    private JCheckBox detgep_gomb;
    private JCheckBox detember_gomb;
    private JCheckBox detmod_gomb;
    
    public Vevoireklamacio_d4() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Gyökérok analízis előfordulásra");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(270, 56, 231, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Anyag");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(445, 81, 56, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Gép");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(455, 139, 46, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Ember");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(455, 192, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Mód");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(455, 247, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Gyökérok analízis nem detektálhatóságra");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(191, 310, 310, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Anyag");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(455, 335, 46, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Gép");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(455, 396, 46, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Ember");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(455, 453, 46, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Mód");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(455, 509, 46, 14);
        add(lblNewLabel_9);
        
        anyag_elo = new JTextArea();
        anyag_elo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        anyag_elo.setLineWrap(true);
        anyag_elo.setWrapStyleWord(true);
        anyag_elo.setBounds(523, 76, 720, 39);
        add(anyag_elo);
        
        gep_elo = new JTextArea();
        gep_elo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        gep_elo.setLineWrap(true);
        gep_elo.setWrapStyleWord(true);
        gep_elo.setBounds(523, 134, 720, 39);
        add(gep_elo);
        
        ember_elo = new JTextArea();
        ember_elo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        ember_elo.setLineWrap(true);
        ember_elo.setWrapStyleWord(true);
        ember_elo.setBounds(523, 187, 720, 39);
        add(ember_elo);
        
        mod_elo = new JTextArea();
        mod_elo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        mod_elo.setLineWrap(true);
        mod_elo.setWrapStyleWord(true);
        mod_elo.setBounds(523, 242, 720, 39);
        add(mod_elo);
        
        anyag_det = new JTextArea();
        anyag_det.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        anyag_det.setLineWrap(true);
        anyag_det.setWrapStyleWord(true);
        anyag_det.setBounds(523, 330, 720, 39);
        add(anyag_det);
        
        gep_det = new JTextArea();
        gep_det.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        gep_det.setLineWrap(true);
        gep_det.setWrapStyleWord(true);
        gep_det.setBounds(523, 391, 720, 39);
        add(gep_det);
        
        ember_det = new JTextArea();
        ember_det.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        ember_det.setLineWrap(true);
        ember_det.setWrapStyleWord(true);
        ember_det.setBounds(523, 448, 720, 39);
        add(ember_det);
        
        mod_det = new JTextArea();
        mod_det.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        mod_det.setLineWrap(true);
        mod_det.setWrapStyleWord(true);
        mod_det.setBounds(523, 504, 720, 39);
        add(mod_det);
        
        eloanyag_gomb = new JCheckBox("");
        eloanyag_gomb.addActionListener(new Mentesgomb_aktival());
        eloanyag_gomb.setBounds(415, 77, 26, 23);
        add(eloanyag_gomb);
        
        elogep_gomb = new JCheckBox("");
        elogep_gomb.addActionListener(new Mentesgomb_aktival());
        elogep_gomb.setBounds(415, 135, 26, 23);
        add(elogep_gomb);
        
        eloember_gomb = new JCheckBox("");
        eloember_gomb.addActionListener(new Mentesgomb_aktival());
        eloember_gomb.setBounds(415, 188, 26, 23);
        add(eloember_gomb);
        
        elomod_gomb = new JCheckBox("");
        elomod_gomb.addActionListener(new Mentesgomb_aktival());
        elomod_gomb.setBounds(415, 243, 26, 23);
        add(elomod_gomb);
        
        detanyag_gomb = new JCheckBox("");
        detanyag_gomb.addActionListener(new Mentesgomb_aktival());
        detanyag_gomb.setBounds(415, 331, 26, 23);
        add(detanyag_gomb);
        
        detgep_gomb = new JCheckBox("");
        detgep_gomb.addActionListener(new Mentesgomb_aktival());
        detgep_gomb.setBounds(415, 392, 26, 23);
        add(detgep_gomb);
        
        detember_gomb = new JCheckBox("");
        detember_gomb.addActionListener(new Mentesgomb_aktival());
        detember_gomb.setBounds(415, 449, 21, 23);
        add(detember_gomb);
        
        detmod_gomb = new JCheckBox("");
        detmod_gomb.addActionListener(new Mentesgomb_aktival());
        detmod_gomb.setBounds(415, 505, 26, 23);
        add(detmod_gomb);

    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Anyag = '"+ anyag_elo.getText()+";"+anyag_det.getText() +"', Gep = '"+gep_elo.getText()+";"+ gep_det.getText() +"', "
                    + "Ember = '"+ ember_elo.getText()+";"+ember_det.getText() +"', Mod_ = '"+mod_elo.getText()+";"+ mod_det.getText() +"' "                   
                    + "where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            
            String gyokerok = ";";
            String gyokerok2 = ";";
            if(eloanyag_gomb.isSelected())
            {
                gyokerok += "anyag;";
            }
            if(elogep_gomb.isSelected())
            {
                gyokerok += "gep;";
            }
            if(eloember_gomb.isSelected())
            {
                gyokerok += "ember;";
            }
            if(elomod_gomb.isSelected())
            {
                gyokerok += "mod;";
            }
            
            if(detanyag_gomb.isSelected())
            {
                gyokerok2 += "anyag;";
            }
            if(detgep_gomb.isSelected())
            {
                gyokerok2 += "gep;";
            }
            if(detember_gomb.isSelected())
            {
                gyokerok2 += "ember;";
            }
            if(detmod_gomb.isSelected())
            {
                gyokerok2 += "mod;";
            }
            
            sql = "update qualitydb.Vevoireklamacio_alap set Gyokerok = '"+ gyokerok +"',  Gyokerok2 = '"+ gyokerok2 +"' "                   
                    + "where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            
        } 
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }
    }
    
    public void visszatolt()
    {
        Connection conn = null;
        Statement stmt = null;        
        try 
        {
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
        conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = (Statement) conn.createStatement();
        String sql = "select anyag, gep, ember, mod_, Gyokerok, Gyokerok2 from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
        {
            eloanyag_gomb.setSelected(false);
            elogep_gomb.setSelected(false);
            eloember_gomb.setSelected(false);
            elomod_gomb.setSelected(false);            
            detanyag_gomb.setSelected(false);
            detgep_gomb.setSelected(false);
            detember_gomb.setSelected(false);
            detmod_gomb.setSelected(false);
            
            if(rs.getString(1) != null)
            {                
                String[] anyag = rs.getString(1).split(";");
                if(anyag.length > 1)
                {
                    anyag_elo.setText(anyag[0]);
                    anyag_det.setText(anyag[1]); 
                }
                else if(anyag.length == 1)
                {
                    anyag_elo.setText(anyag[0]);
                    anyag_det.setText("");
                }
                else
                {
                    anyag_elo.setText("");
                    anyag_det.setText("");
                }
                String[] gep = rs.getString(2).split(";");
                if(gep.length > 1)
                {
                    gep_elo.setText(gep[0]);
                    gep_det.setText(gep[1]); 
                }
                else if(gep.length == 1)
                {
                    gep_elo.setText(gep[0]);
                    gep_det.setText("");
                }
                else
                {
                    gep_elo.setText("");
                    gep_det.setText("");
                }            
                String[] ember = rs.getString(3).split(";");
                if(ember.length > 1)
                {
                    ember_elo.setText(ember[0]);
                    ember_det.setText(ember[1]); 
                }
                else if(ember.length == 1)
                {
                    ember_elo.setText(ember[0]);
                    ember_det.setText("");
                }
                else
                {
                    ember_elo.setText("");
                    ember_det.setText("");
                }
                String[] mod = rs.getString(4).split(";");
                if(mod.length > 1)
                {
                    mod_elo.setText(mod[0]);
                    mod_det.setText(mod[1]); 
                }
                else if(mod.length == 1)
                {
                    mod_elo.setText(mod[0]);
                    mod_det.setText("");
                }
                else
                {
                    mod_elo.setText("");
                    mod_det.setText("");
                }
                
                String[] gyokerok = rs.getString(5).split(";");
                for(int szamlalo = 0; szamlalo < gyokerok.length; szamlalo++)
                {
                    if(gyokerok[szamlalo].equals("anyag"))
                    {
                        eloanyag_gomb.setSelected(true); 
                    }
                    else if(gyokerok[szamlalo].equals("gep"))
                    {
                        elogep_gomb.setSelected(true);
                    }
                    else if(gyokerok[szamlalo].equals("ember"))
                    {
                        eloember_gomb.setSelected(true);
                    }
                    else
                    {
                        elomod_gomb.setSelected(true);
                    }
                }
                
                String[] gyokerok2 = rs.getString(6).split(";");
                for(int szamlalo = 0; szamlalo < gyokerok2.length; szamlalo++)
                {
                    if(gyokerok2[szamlalo].equals("anyag"))
                    {
                        detanyag_gomb.setSelected(true); 
                    }
                    else if(gyokerok2[szamlalo].equals("gep"))
                    {
                        detgep_gomb.setSelected(true);
                    }
                    else if(gyokerok2[szamlalo].equals("ember"))
                    {
                        detember_gomb.setSelected(true);
                    }
                    else
                    {
                        detmod_gomb.setSelected(true);
                    }
                }
            }
        }        
        stmt.close();
        conn.close();        
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                  stmt.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
               se.printStackTrace();
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    class Mentesgomb_aktival implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
