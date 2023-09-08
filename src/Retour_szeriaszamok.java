import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

public class Retour_szeriaszamok extends JPanel {
    private JTextField retourid_mezo;
    private JTextField szeriaszam_mezo;
    private JTable table;
    private JTextField tortenet_mezo;
    private JTextField datum_mezo;
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
        
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"VEAS Szériaszám", "Vevői Szériaszám","Retour ID", "Torténet", "Dátum"});
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(64, 162, 1064, 195);
        add(gorgeto);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(500, 513, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_3 = new JLabel("Minden történethez hozzáad");
        lblNewLabel_3.setBounds(78, 390, 179, 14);
        add(lblNewLabel_3);
        
        tortenet_mezo = new JTextField();
        tortenet_mezo.setBounds(267, 387, 663, 20);
        add(tortenet_mezo);
        tortenet_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Minden dátumot módosít");
        lblNewLabel_4.setBounds(78, 431, 132, 14);
        add(lblNewLabel_4);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(267, 428, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JButton hozzaad_gomb = new JButton("Hozzáad");
        hozzaad_gomb.setBounds(983, 386, 89, 23);
        add(hozzaad_gomb);
        
        JButton datum_gomb = new JButton("Módosít");
        datum_gomb.setBounds(399, 427, 89, 23);
        add(datum_gomb);

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
                    table.setModel(modell);
                    stmt.close();
                    conn.close();
                            
                    } 
                    catch (SQLException e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                    } 
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                           String hibauzenet2 = se.toString();
                           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                       }  
                    }
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
                    table.setModel(modell);
                    szeriaszam_mezo.setText("");
                    stmt.close();
                    conn.close();
                            
                    } 
                    catch (SQLException e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                    } 
                    catch (Exception e1) 
                    {
                        e1.printStackTrace();
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
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
                           String hibauzenet2 = se.toString();
                           JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
                       }  
                    }
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
    
    class Mentes implements ActionListener                                                                                        //bevitt retour adatokat menti el
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
