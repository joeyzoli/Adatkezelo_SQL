import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Retour_szeriaszamok extends JPanel {
    private JTextField retourid_mezo;
    private JTextField szeriaszam_mezo;
    private JTextField hibakod_mezo;
    private JCheckBox vizualis_csekk1;private JCheckBox vizualis_csekk2;private JCheckBox vizualis_csekk3;private JCheckBox vizualis_csekk4;private JCheckBox vizualis_csekk5;private JCheckBox vizualis_csekk6;
    private JCheckBox ict_csekk1;private JCheckBox ict_csekk2;private JCheckBox ict_csekk3;private JCheckBox ict_csekk4;private JCheckBox ict_csekk5;private JCheckBox ict_csekk6;
    private JCheckBox fct_csekk1;private JCheckBox fct_csekk2;private JCheckBox fct_csekk3;private JCheckBox fct_csekk4;private JCheckBox fct_csekk5;private JCheckBox fct_csekk6;
    private JCheckBox meres_csekk1;private JCheckBox meres_csekk2;private JCheckBox meres_csekk3;private JCheckBox meres_csekk4;private JCheckBox meres_csekk5;private JCheckBox meres_csekk6;
    private JCheckBox rontgen_csekk1;private JCheckBox rontgen_csekk2;private JCheckBox rontgen_csekk3;private JCheckBox rontgen_csekk4;private JCheckBox rontgen_csekk5;private JCheckBox rontgen_csekk6;
    private JCheckBox egyeb_csekk1;private JCheckBox egyeb_csekk2;private JCheckBox egyeb_csekk3;private JCheckBox egyeb_csekk4;private JCheckBox egyeb_csekk5;private JCheckBox egyeb_csekk6;

    /**
     * Create the panel.
     */
    public Retour_szeriaszamok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Retour szériaszámok nyomonkövetése");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(535, 44, 278, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Retour ID");
        lblNewLabel_1.setBounds(78, 95, 67, 14);
        add(lblNewLabel_1);
        
        retourid_mezo = new JTextField();
        retourid_mezo.setBounds(155, 92, 46, 20);
        add(retourid_mezo);
        retourid_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Retour szériaszám");
        lblNewLabel_2.setBounds(78, 134, 118, 14);
        add(lblNewLabel_2);
        
        szeriaszam_mezo = new JTextField();
        szeriaszam_mezo.addKeyListener(new Enter_szeriaszam());
        szeriaszam_mezo.setBounds(206, 131, 303, 20);
        add(szeriaszam_mezo);
        szeriaszam_mezo.setColumns(10);        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_3 = new JLabel("Vizuális ellenőrzés");
        lblNewLabel_3.setBounds(78, 229, 132, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("ICT ellenőrzés");
        lblNewLabel_4.setBounds(78, 273, 86, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("FCT ellenőrzés");
        lblNewLabel_5.setBounds(78, 319, 83, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Méréses ellenőrzés");
        lblNewLabel_6.setBounds(78, 366, 112, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Röntgen ellenőrzés");
        lblNewLabel_7.setBounds(78, 415, 112, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Egyéb");
        lblNewLabel_8.setBounds(78, 465, 46, 14);
        add(lblNewLabel_8);
        
        vizualis_csekk1 = new JCheckBox("");
        vizualis_csekk1.setBounds(220, 225, 37, 23);
        add(vizualis_csekk1);
        
        vizualis_csekk2 = new JCheckBox("");
        vizualis_csekk2.setBounds(271, 225, 37, 23);
        add(vizualis_csekk2);
        
        JLabel lblNewLabel_9 = new JLabel("Igen");
        lblNewLabel_9.setBounds(219, 202, 46, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("Nem");
        lblNewLabel_10.setBounds(271, 202, 46, 14);
        add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("Ellenőrzés / Vizsgálat");
        lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11.setBounds(206, 177, 156, 14);
        add(lblNewLabel_11);
        
        ict_csekk1 = new JCheckBox("");
        ict_csekk1.setBounds(220, 269, 37, 23);
        add(ict_csekk1);
        
        fct_csekk1 = new JCheckBox("");
        fct_csekk1.setBounds(220, 315, 37, 23);
        add(fct_csekk1);
        
        meres_csekk1 = new JCheckBox("");
        meres_csekk1.setBounds(220, 362, 37, 23);
        add(meres_csekk1);
        
        rontgen_csekk1 = new JCheckBox("");
        rontgen_csekk1.setBounds(220, 411, 37, 23);
        add(rontgen_csekk1);
        
        egyeb_csekk1 = new JCheckBox("");
        egyeb_csekk1.setBounds(220, 461, 37, 23);
        add(egyeb_csekk1);
        
        ict_csekk2 = new JCheckBox("");
        ict_csekk2.setBounds(271, 269, 37, 23);
        add(ict_csekk2);
        
        fct_csekk2 = new JCheckBox("");
        fct_csekk2.setBounds(271, 315, 37, 23);
        add(fct_csekk2);
        
        meres_csekk2 = new JCheckBox("");
        meres_csekk2.setBounds(271, 362, 37, 23);
        add(meres_csekk2);
        
        rontgen_csekk2 = new JCheckBox("");
        rontgen_csekk2.setBounds(271, 411, 37, 23);
        add(rontgen_csekk2);
        
        egyeb_csekk2 = new JCheckBox("");
        egyeb_csekk2.setBounds(271, 461, 37, 23);
        add(egyeb_csekk2);
        
        JLabel lblNewLabel_12 = new JLabel("Javítás előtt");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_12.setBounds(425, 177, 100, 14);
        add(lblNewLabel_12);
        
        vizualis_csekk3 = new JCheckBox("");
        vizualis_csekk3.setBounds(425, 225, 37, 23);
        add(vizualis_csekk3);
        
        ict_csekk3 = new JCheckBox("");
        ict_csekk3.setBounds(425, 269, 37, 23);
        add(ict_csekk3);
        
        fct_csekk3 = new JCheckBox("");
        fct_csekk3.setBounds(425, 315, 37, 23);
        add(fct_csekk3);
        
        meres_csekk3 = new JCheckBox("");
        meres_csekk3.setBounds(425, 362, 37, 23);
        add(meres_csekk3);
        
        rontgen_csekk3 = new JCheckBox("");
        rontgen_csekk3.setBounds(425, 411, 37, 23);
        add(rontgen_csekk3);
        
        egyeb_csekk3 = new JCheckBox("");
        egyeb_csekk3.setBounds(425, 461, 37, 23);
        add(egyeb_csekk3);
        
        JLabel lblNewLabel_13 = new JLabel("OK");
        lblNewLabel_13.setBounds(430, 202, 37, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("NOK");
        lblNewLabel_14.setBounds(481, 202, 46, 14);
        add(lblNewLabel_14);
        
        vizualis_csekk4 = new JCheckBox("");
        vizualis_csekk4.setBounds(481, 225, 28, 23);
        add(vizualis_csekk4);
        
        ict_csekk4 = new JCheckBox("");
        ict_csekk4.setBounds(481, 269, 28, 23);
        add(ict_csekk4);
        
        fct_csekk4 = new JCheckBox("");
        fct_csekk4.setBounds(481, 315, 28, 23);
        add(fct_csekk4);
        
        meres_csekk4 = new JCheckBox("");
        meres_csekk4.setBounds(481, 362, 28, 23);
        add(meres_csekk4);
        
        rontgen_csekk4 = new JCheckBox("");
        rontgen_csekk4.setBounds(481, 411, 28, 23);
        add(rontgen_csekk4);
        
        egyeb_csekk4 = new JCheckBox("");
        egyeb_csekk4.setBounds(481, 461, 28, 23);
        add(egyeb_csekk4);
        
        JLabel lblNewLabel_15 = new JLabel("Javítás után");
        lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_15.setBounds(623, 178, 100, 14);
        add(lblNewLabel_15);
        
        vizualis_csekk5 = new JCheckBox("");
        vizualis_csekk5.setBounds(623, 225, 28, 23);
        add(vizualis_csekk5);
        
        ict_csekk5 = new JCheckBox("");
        ict_csekk5.setBounds(623, 269, 28, 23);
        add(ict_csekk5);
        
        fct_csekk5 = new JCheckBox("");
        fct_csekk5.setBounds(623, 315, 28, 23);
        add(fct_csekk5);
        
        meres_csekk5 = new JCheckBox("");
        meres_csekk5.setBounds(623, 362, 28, 23);
        add(meres_csekk5);
        
        rontgen_csekk5 = new JCheckBox("");
        rontgen_csekk5.setBounds(623, 411, 28, 23);
        add(rontgen_csekk5);
        
        egyeb_csekk5 = new JCheckBox("");
        egyeb_csekk5.setBounds(623, 461, 28, 23);
        add(egyeb_csekk5);
        
        vizualis_csekk6 = new JCheckBox("");
        vizualis_csekk6.setBounds(668, 225, 28, 23);
        add(vizualis_csekk6);
        
        ict_csekk6 = new JCheckBox("");
        ict_csekk6.setBounds(668, 269, 28, 23);
        add(ict_csekk6);
        
        fct_csekk6 = new JCheckBox("");
        fct_csekk6.setBounds(668, 315, 28, 23);
        add(fct_csekk6);
        
        meres_csekk6 = new JCheckBox("");
        meres_csekk6.setBounds(668, 362, 28, 23);
        add(meres_csekk6);
        
        rontgen_csekk6 = new JCheckBox("");
        rontgen_csekk6.setBounds(668, 411, 28, 23);
        add(rontgen_csekk6);
        
        egyeb_csekk6 = new JCheckBox("");
        egyeb_csekk6.setBounds(668, 461, 28, 23);
        add(egyeb_csekk6);
        
        JLabel lblNewLabel_16 = new JLabel("Megállapítás");
        lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_16.setBounds(929, 178, 112, 14);
        add(lblNewLabel_16);
        
        JLabel lblNewLabel_17 = new JLabel("Hibakód");
        lblNewLabel_17.setBounds(767, 229, 46, 14);
        add(lblNewLabel_17);
        
        hibakod_mezo = new JTextField();
        hibakod_mezo.setBounds(823, 226, 60, 20);
        add(hibakod_mezo);
        hibakod_mezo.setColumns(10);
        
        JLabel lblNewLabel_18 = new JLabel("Hiba eredete");
        lblNewLabel_18.setBounds(964, 202, 77, 14);
        add(lblNewLabel_18);
        
        JCheckBox beszallito = new JCheckBox("Beszállító");
        beszallito.setBounds(899, 225, 83, 23);
        add(beszallito);
        
        JCheckBox veas = new JCheckBox("VEAS");
        veas.setBounds(980, 225, 67, 23);
        add(veas);
        
        JCheckBox vevo = new JCheckBox("Vevő");
        vevo.setBounds(1049, 225, 67, 23);
        add(vevo);
        
        JLabel lblNewLabel_19 = new JLabel("Hiba leírása");
        lblNewLabel_19.setBounds(767, 278, 77, 14);
        add(lblNewLabel_19);
        
        JTextArea hiba_mezo = new JTextArea();
        hiba_mezo.setBounds(868, 273, 285, 81);
        add(hiba_mezo);
        
        JLabel lblNewLabel_20 = new JLabel("Javítás leírása");
        lblNewLabel_20.setBounds(767, 386, 86, 14);
        add(lblNewLabel_20);
        
        JTextArea javitas_mezo = new JTextArea();
        javitas_mezo.setBounds(868, 381, 285, 81);
        add(javitas_mezo);
        
        JLabel lblNewLabel_21 = new JLabel("OK");
        lblNewLabel_21.setBounds(628, 202, 28, 14);
        add(lblNewLabel_21);
        
        JLabel lblNewLabel_22 = new JLabel("NOK");
        lblNewLabel_22.setBounds(668, 202, 46, 14);
        add(lblNewLabel_22);
        
        JLabel lblNewLabel_23 = new JLabel("Vizsgálat/javítás után a termék kiszállítható");
        lblNewLabel_23.setBounds(78, 537, 270, 14);
        add(lblNewLabel_23);
        
        JCheckBox kiszallithato_igen = new JCheckBox("Igen");
        kiszallithato_igen.setBounds(101, 568, 53, 23);
        add(kiszallithato_igen);
        
        JCheckBox kiszallithato_nem = new JCheckBox("Nem");
        kiszallithato_nem.setBounds(177, 568, 53, 23);
        add(kiszallithato_nem);
        
        JLabel lblNewLabel_24 = new JLabel("Hiba oka");
        lblNewLabel_24.setBounds(380, 537, 60, 14);
        add(lblNewLabel_24);
        
        JTextArea hibaoka_mezo = new JTextArea();
        hibaoka_mezo.setBounds(450, 532, 285, 81);
        add(hibaoka_mezo);
        
        JLabel lblNewLabel_25 = new JLabel("Intézkedés leírása");
        lblNewLabel_25.setBounds(757, 537, 112, 14);
        add(lblNewLabel_25);
        
        JTextArea intezkedes_mezo = new JTextArea();
        intezkedes_mezo.setBounds(868, 532, 285, 81);
        add(intezkedes_mezo);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(550, 655, 89, 23);
        add(mentes_gomb);

    }
    
    class Enter_szeriaszam implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Connection conn = null;
                    Statement stmt = null;        
                    try 
                    {
                       try 
                       {
                          Class.forName("com.mysql.cj.jdbc.Driver");
                       } 
                       catch (Exception e1) 
                       {
                          System.out.println(e1);
                          String hibauzenet2 = e1.toString();
                          JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                    }
                    conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    stmt = (Statement) conn.createStatement();
                    String sql = "select * from qualitydb.Retour_szeriaszamok where VEAS_ID = '"+ szeriaszam_mezo.getText() +"' or Vevoi_ID = '"+ szeriaszam_mezo.getText() +"'";
                    stmt.execute(sql);
                    //ResultSet rs = stmt.getResultSet();
                    
                    szeriaszam_mezo.setText("");
                    stmt.close();
                    conn.close();
                            
                    }                    
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                    } finally 
                    {
                       try 
                       {
                          if (stmt != null)
                             conn.close();
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
                           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                       }  
                    }
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
    
    class Mentes implements ActionListener                                                                                      //futtat gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Connection conn = null;
            Statement stmt = null; 
            try 
            {
               try 
               {
                  Class.forName("com.mysql.cj.jdbc.Driver");
               } 
               catch (Exception e1) 
               {
                  System.out.println(e1);
                  String hibauzenet2 = e1.toString();
                  JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String vizualis1 = "";String vizualis2 = "";String vizualis3 = "";
            String ict1 = "";String ict2 = "";String ict3 = "";
            String fct1 = "";String fct2 = "";String fct3 = "";
            String meres1 = "";String meres2 = "";String meres3 = "";
            String rontgen1 = "";String rontgen2 = "";String rontgen3 = "";
            String egyeb1 = "";String egyeb2 = "";String egyeb3 = "";
            String hibaeredet = "";String kiszallithato = "";
            if(vizualis_csekk1.isSelected()) {
                
            }
            
            String sql = "update from qualitydb.Retour_szeriaszamok set where VEAS_ID = '"+ szeriaszam_mezo.getText() +"' or Vevoi_ID = '"+ szeriaszam_mezo.getText() +"'";
            stmt.execute(sql);
            //ResultSet rs = stmt.getResultSet();
            
            szeriaszam_mezo.setText("");
            stmt.close();
            conn.close();
                    
            }                    
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
            Foablak.frame.setCursor(null);
         }
    }
}
