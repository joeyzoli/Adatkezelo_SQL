import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.swing.JComboBox;

public class Retour_lekerdez extends JPanel 
{
    private JTextField datumtol;
    private JTextField datumig;
    static JTable table;
    private SQL lekerdezes = new SQL();
    private JTextField id_mezo;
    private ComboBox combobox_tomb;
    private JComboBox<String> vevo_box;
    /**
     * Create the panel.
     */
    public Retour_lekerdez() 
    {
        this.setPreferredSize(new Dimension(1906, 837));
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
        keres_gomb.setBounds(520, 232, 89, 23);
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
        
        JLabel lblNewLabel_8 = new JLabel("Excel grafikonnal");
        lblNewLabel_8.setBounds(387, 565, 102, 14);
        add(lblNewLabel_8);
        
        JButton excelmentes = new JButton("Mentés");
        excelmentes.addActionListener(new Excelmentes());
        excelmentes.setBounds(517, 561, 89, 23);
        add(excelmentes);
        ido();
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_1 = new JLabel("Retour szériaszámok");
        lblNewLabel_1.setBounds(387, 672, 125, 14);
        add(lblNewLabel_1);
        
        JButton szeriaszam_mentes = new JButton("Mentés");
        szeriaszam_mentes.addActionListener(new Szeriaszammentes());
        szeriaszam_mentes.setBounds(520, 668, 89, 23);
        add(szeriaszam_mentes);
        
        JLabel lblNewLabel_5 = new JLabel("Vevő");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(464, 195, 46, 14);
        add(lblNewLabel_5);
        
        combobox_tomb = new ComboBox();
        vevo_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.projekt));               //combobox_tomb.getCombobox(ComboBox.projekt)
        vevo_box.setBounds(520, 191, 225, 22);
        add(vevo_box);
        
        JButton mindenadat_gomb = new JButton("Mentés");
        mindenadat_gomb.addActionListener(new Retour_minden());
        mindenadat_gomb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        mindenadat_gomb.setBounds(520, 614, 89, 23);
        add(mindenadat_gomb);
        
        JLabel lblNewLabel_6 = new JLabel("Excel minden adat");
        lblNewLabel_6.setBounds(387, 618, 123, 14);
        add(lblNewLabel_6);
        
        JButton kepmentes_gomb = new JButton("Mentés");
        kepmentes_gomb.addActionListener(new Szeriaszammentes_kepek());
        kepmentes_gomb.setBounds(520, 720, 89, 23);
        add(kepmentes_gomb);
        
        JLabel lblNewLabel_7 = new JLabel("Szériaszámokhoz tartozó képek mentése");
        lblNewLabel_7.setBounds(266, 724, 244, 14);
        add(lblNewLabel_7);
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
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Retour_minden implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL excel = new SQA_SQL();
                if(vevo_box.getSelectedItem().equals("-"))
                {
                    String sql = "select * from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"'";
                    excel.minden_excel(sql, "Retourok minden adat.xlsx");
                }
                else
                {
                    String sql = "select * from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"' and Vevo = '"+ vevo_box.getSelectedItem() +"'";
                    excel.minden_excel(sql, "Retourok minden adat.xlsx");
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
    
    class Szeriaszammentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SQA_SQL excel = new SQA_SQL();
                if(id_mezo.getText().equals(""))
                {
                    Connection conn = null;
                    Statement stmt = null;        
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
                    String sql = "";
                    if(id_mezo.getText().equals(""))
                    {
                        sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"'";
                    
                    
                        if(vevo_box.getSelectedItem().equals("-"))
                        {
                            sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"'";
                        }
                        else
                        {
                            sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"' and Vevo = '"+ vevo_box.getSelectedItem() +"'";
                        }
                    
                        stmt.execute(sql);
                        ResultSet rs = stmt.getResultSet();
                        String idk = "";
                        while(rs.next())
                        {
                            idk += rs.getString(1) +",";
                        }
                        idk = idk.substring(0, idk.length() - 1);
                        stmt.close();
                        conn.close();
                        
                        sql = "select * from qualitydb.Retour_szeriaszamok where Retour_ID in ("+ idk +")";
                        excel.minden_excel(sql, "Retour szériaszámok.xlsx");                      
                    }
                    else
                    {
                        sql = "select * from qualitydb.Retour_szeriaszamok where Retour_ID = '"+ id_mezo.getText() +"'";
                        excel.minden_excel(sql, "Retour szériaszámok.xlsx"); 
                    }
                }
                else
                {
                    String sql = "select * from qualitydb.Retour_szeriaszamok where Retour_ID = '"+ id_mezo.getText() +"'";
                    excel.minden_excel(sql, "Retour szériaszámok.xlsx");
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
    
    class Szeriaszammentes_kepek implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Connection conn = null;
                Statement stmt = null;
                FileOutputStream fs=null;
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                if(id_mezo.getText().equals(""))
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
                    String sql = "";
                    sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"'";
                    
                    
                    if(vevo_box.getSelectedItem().equals("-"))
                    {
                        sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"'";
                    }
                    else
                    {
                        sql = "select ID from qualitydb.Retour where datum >= '"+ datumtol.getText() +"' and datum <= '" + datumig.getText() +"' and Vevo = '"+ vevo_box.getSelectedItem() +"'";
                    }
                
                    stmt.execute(sql);
                    ResultSet rs = stmt.getResultSet();
                    String idk = "";
                    while(rs.next())
                    {
                        idk += rs.getString(1) +",";
                    }
                    idk = idk.substring(0, idk.length() - 1);                   
                    
                    sql = "select * from qualitydb.Retour_kepek where Retour_ID in ("+ idk +")";
                    stmt.execute(sql);
                    rs = stmt.getResultSet();         
                    byte b[];
                    Blob blob;
                    int szam = 0;
                    while(rs.next())
                    {        
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Retour "+ id_mezo.getText() +" képek\\"+ rs.getString(5) +"_"+ rs.getString(2) +"_"+ rs.getString(3));
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();
                        fs = new FileOutputStream(f);
                        blob = rs.getBlob("Kep");
                        b = blob.getBytes(1, (int)blob.length());
                        fs.write(b);
                        fs.close();
                        szam++;
                    }
                    if(szam > 0)
                    {
                        JOptionPane.showMessageDialog(null, "Kép/ek mentve az asztalra", "Info", 1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Nincsen csatolt kép", "Info", 1);
                    }         
                         
                    
                    
                }
                else
                {
                    String sql = "select kep, szeriaszam, kepneve, retour_id from qualitydb.Retour_kepek where Retour_ID = '"+ id_mezo.getText() +"'";
 
                    stmt.execute(sql);
                    ResultSet rs = stmt.getResultSet();         
                    byte b[];
                    Blob blob;
                    int szam = 0;
                    while(rs.next())
                    {        
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Retour "+ id_mezo.getText() +" képek\\"+ rs.getString(4) +"_"+ rs.getString(2) +"_"+ rs.getString(3));
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();
                        fs = new FileOutputStream(f);
                        blob = rs.getBlob("Kep");
                        b = blob.getBytes(1, (int)blob.length());
                        fs.write(b);
                        fs.close();
                        szam++;
                    }
                    if(szam > 0)
                    {
                        JOptionPane.showMessageDialog(null, "Kép/ek mentve az asztalra", "Info", 1);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Nincsen csatolt kép", "Info", 1);
                    }
                }
                stmt.close();
                conn.close();
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
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datumig.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }
}
