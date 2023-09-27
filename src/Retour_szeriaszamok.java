import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class Retour_szeriaszamok extends JPanel {
    private JTextField retourid_mezo;
    private JTextField szeriaszam_mezo;
    private DefaultTableModel modell;

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
        lblNewLabel_1.setBounds(78, 95, 83, 14);
        add(lblNewLabel_1);
        
        retourid_mezo = new JTextField();
        retourid_mezo.addKeyListener(new Enter_id());
        retourid_mezo.setBounds(171, 92, 86, 20);
        add(retourid_mezo);
        retourid_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Retour szériaszám");
        lblNewLabel_2.setBounds(78, 134, 132, 14);
        add(lblNewLabel_2);
        
        szeriaszam_mezo = new JTextField();
        szeriaszam_mezo.addKeyListener(new Enter_szeriaszam());
        szeriaszam_mezo.setBounds(220, 131, 251, 20);
        add(szeriaszam_mezo);
        szeriaszam_mezo.setColumns(10);
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"VEAS Szériaszám", "Vevői Szériaszám","Retour ID", "Torténet", "Dátum"});
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
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("");
        chckbxNewCheckBox.setBounds(220, 225, 37, 23);
        add(chckbxNewCheckBox);
        
        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
        chckbxNewCheckBox_1.setBounds(271, 225, 37, 23);
        add(chckbxNewCheckBox_1);
        
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
        
        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
        chckbxNewCheckBox_2.setBounds(220, 269, 37, 23);
        add(chckbxNewCheckBox_2);
        
        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
        chckbxNewCheckBox_3.setBounds(220, 315, 37, 23);
        add(chckbxNewCheckBox_3);
        
        JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
        chckbxNewCheckBox_4.setBounds(220, 362, 37, 23);
        add(chckbxNewCheckBox_4);
        
        JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
        chckbxNewCheckBox_5.setBounds(220, 411, 37, 23);
        add(chckbxNewCheckBox_5);
        
        JCheckBox chckbxNewCheckBox_6 = new JCheckBox("");
        chckbxNewCheckBox_6.setBounds(220, 461, 37, 23);
        add(chckbxNewCheckBox_6);
        
        JCheckBox chckbxNewCheckBox_7 = new JCheckBox("");
        chckbxNewCheckBox_7.setBounds(271, 269, 37, 23);
        add(chckbxNewCheckBox_7);
        
        JCheckBox chckbxNewCheckBox_8 = new JCheckBox("");
        chckbxNewCheckBox_8.setBounds(271, 315, 37, 23);
        add(chckbxNewCheckBox_8);
        
        JCheckBox chckbxNewCheckBox_9 = new JCheckBox("");
        chckbxNewCheckBox_9.setBounds(271, 362, 37, 23);
        add(chckbxNewCheckBox_9);
        
        JCheckBox chckbxNewCheckBox_10 = new JCheckBox("");
        chckbxNewCheckBox_10.setBounds(271, 411, 37, 23);
        add(chckbxNewCheckBox_10);
        
        JCheckBox chckbxNewCheckBox_11 = new JCheckBox("");
        chckbxNewCheckBox_11.setBounds(271, 461, 37, 23);
        add(chckbxNewCheckBox_11);
        
        JLabel lblNewLabel_12 = new JLabel("Javítás előtt");
        lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_12.setBounds(425, 177, 100, 14);
        add(lblNewLabel_12);
        
        JCheckBox chckbxNewCheckBox_12 = new JCheckBox("");
        chckbxNewCheckBox_12.setBounds(425, 225, 37, 23);
        add(chckbxNewCheckBox_12);
        
        JCheckBox chckbxNewCheckBox_13 = new JCheckBox("");
        chckbxNewCheckBox_13.setBounds(425, 269, 37, 23);
        add(chckbxNewCheckBox_13);
        
        JCheckBox chckbxNewCheckBox_14 = new JCheckBox("");
        chckbxNewCheckBox_14.setBounds(425, 315, 37, 23);
        add(chckbxNewCheckBox_14);
        
        JCheckBox chckbxNewCheckBox_15 = new JCheckBox("");
        chckbxNewCheckBox_15.setBounds(425, 362, 37, 23);
        add(chckbxNewCheckBox_15);
        
        JCheckBox chckbxNewCheckBox_16 = new JCheckBox("");
        chckbxNewCheckBox_16.setBounds(425, 411, 37, 23);
        add(chckbxNewCheckBox_16);
        
        JCheckBox chckbxNewCheckBox_17 = new JCheckBox("");
        chckbxNewCheckBox_17.setBounds(425, 461, 37, 23);
        add(chckbxNewCheckBox_17);
        
        JLabel lblNewLabel_13 = new JLabel("OK");
        lblNewLabel_13.setBounds(430, 202, 37, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("NOK");
        lblNewLabel_14.setBounds(481, 202, 46, 14);
        add(lblNewLabel_14);
        
        JCheckBox chckbxNewCheckBox_18 = new JCheckBox("");
        chckbxNewCheckBox_18.setBounds(481, 225, 28, 23);
        add(chckbxNewCheckBox_18);
        
        JCheckBox chckbxNewCheckBox_19 = new JCheckBox("");
        chckbxNewCheckBox_19.setBounds(481, 269, 28, 23);
        add(chckbxNewCheckBox_19);
        
        JCheckBox chckbxNewCheckBox_20 = new JCheckBox("");
        chckbxNewCheckBox_20.setBounds(481, 315, 28, 23);
        add(chckbxNewCheckBox_20);
        
        JCheckBox chckbxNewCheckBox_21 = new JCheckBox("");
        chckbxNewCheckBox_21.setBounds(481, 362, 28, 23);
        add(chckbxNewCheckBox_21);
        
        JCheckBox chckbxNewCheckBox_22 = new JCheckBox("");
        chckbxNewCheckBox_22.setBounds(481, 411, 28, 23);
        add(chckbxNewCheckBox_22);
        
        JCheckBox chckbxNewCheckBox_23 = new JCheckBox("");
        chckbxNewCheckBox_23.setBounds(481, 461, 28, 23);
        add(chckbxNewCheckBox_23);
        
        JLabel lblNewLabel_15 = new JLabel("Javítás után");
        lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_15.setBounds(623, 178, 100, 14);
        add(lblNewLabel_15);
        
        JCheckBox chckbxNewCheckBox_24 = new JCheckBox("");
        chckbxNewCheckBox_24.setBounds(623, 225, 28, 23);
        add(chckbxNewCheckBox_24);
        
        JCheckBox chckbxNewCheckBox_25 = new JCheckBox("");
        chckbxNewCheckBox_25.setBounds(623, 269, 28, 23);
        add(chckbxNewCheckBox_25);
        
        JCheckBox chckbxNewCheckBox_26 = new JCheckBox("");
        chckbxNewCheckBox_26.setBounds(623, 315, 28, 23);
        add(chckbxNewCheckBox_26);
        
        JCheckBox chckbxNewCheckBox_27 = new JCheckBox("");
        chckbxNewCheckBox_27.setBounds(623, 362, 28, 23);
        add(chckbxNewCheckBox_27);
        
        JCheckBox chckbxNewCheckBox_28 = new JCheckBox("");
        chckbxNewCheckBox_28.setBounds(623, 411, 28, 23);
        add(chckbxNewCheckBox_28);
        
        JCheckBox chckbxNewCheckBox_29 = new JCheckBox("");
        chckbxNewCheckBox_29.setBounds(623, 461, 28, 23);
        add(chckbxNewCheckBox_29);
        
        JCheckBox chckbxNewCheckBox_30 = new JCheckBox("");
        chckbxNewCheckBox_30.setBounds(668, 225, 28, 23);
        add(chckbxNewCheckBox_30);
        
        JCheckBox chckbxNewCheckBox_31 = new JCheckBox("");
        chckbxNewCheckBox_31.setBounds(668, 269, 28, 23);
        add(chckbxNewCheckBox_31);
        
        JCheckBox chckbxNewCheckBox_32 = new JCheckBox("");
        chckbxNewCheckBox_32.setBounds(668, 315, 28, 23);
        add(chckbxNewCheckBox_32);
        
        JCheckBox chckbxNewCheckBox_33 = new JCheckBox("");
        chckbxNewCheckBox_33.setBounds(668, 362, 28, 23);
        add(chckbxNewCheckBox_33);
        
        JCheckBox chckbxNewCheckBox_34 = new JCheckBox("");
        chckbxNewCheckBox_34.setBounds(668, 411, 28, 23);
        add(chckbxNewCheckBox_34);
        
        JCheckBox chckbxNewCheckBox_35 = new JCheckBox("");
        chckbxNewCheckBox_35.setBounds(668, 461, 28, 23);
        add(chckbxNewCheckBox_35);

    }
    
    class Enter_id implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
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
                    String sql = "select * from qualitydb.Retour_szeriaszamok where Retour_id = '"+ retourid_mezo.getText() +"'";
                    stmt.execute(sql);
                    ResultSet rs = stmt.getResultSet();
                    int rowCount = modell.getRowCount();                               
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }
                    while(rs.next())
                    {
                        modell.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                    }
                    stmt.close();
                    conn.close();
                            
                    } 
                    catch (SQLException e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                    ResultSet rs = stmt.getResultSet();
                    int rowCount = modell.getRowCount();                               
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }
                    while(rs.next())
                    {
                        modell.addRow(new Object[]{rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
                    }
                    szeriaszam_mezo.setText("");
                    stmt.close();
                    conn.close();
                            
                    } 
                    catch (SQLException e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet = e1.toString();
                        Email hibakuldes = new Email();
                        hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
}
