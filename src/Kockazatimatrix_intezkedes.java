import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class Kockazatimatrix_intezkedes extends JPanel {
    private JTextField felelos_mezo;
    private JTextField kockazatiszint_mezo;
    private JTextField allapot_mezo;
    private JTextField valoszinu_mezo;
    private JTextField kovetkezmeny_mezo;
    private JTextField ujkockazatiszint_mezo;
    private JTextArea kockazat_mezo;
    private JTextArea jelenintezkedes_mezo;
    private JTextArea ujintezkedes_mezo;
    private JTextArea hatarido_mezo;
    private JTextArea ujhatarido_mezo;

    /**
     * Create the panel.
     */
    public Kockazatimatrix_intezkedes(String kockazat, String intezkedes, String szint) {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Magas kockázatú kockázatok kezelése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(520, 36, 385, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Kockázat");
        lblNewLabel_1.setBounds(37, 100, 74, 14);
        add(lblNewLabel_1);
        
        kockazat_mezo = new JTextArea();
        kockazat_mezo.setEditable(false);
        kockazat_mezo.setLineWrap(true);
        kockazat_mezo.setWrapStyleWord(true);
        kockazat_mezo.setText(kockazat);
        JScrollPane gorgeto = new JScrollPane(kockazat_mezo);
        gorgeto.setBounds(121, 95, 221, 96);
        add(gorgeto);
        
        JLabel lblNewLabel_2 = new JLabel("Jelenlegi intézkedés");
        lblNewLabel_2.setBounds(380, 100, 123, 14);
        add(lblNewLabel_2);
        
        jelenintezkedes_mezo = new JTextArea();
        jelenintezkedes_mezo.setEditable(false);
        jelenintezkedes_mezo.setLineWrap(true);
        jelenintezkedes_mezo.setWrapStyleWord(true);
        jelenintezkedes_mezo.setText(intezkedes);
        JScrollPane gorgeto2 = new JScrollPane(jelenintezkedes_mezo);
        gorgeto2.setBounds(520, 95, 221, 96);
        add(gorgeto2);
        
        JLabel lblNewLabel_3 = new JLabel("Új intézkedés");
        lblNewLabel_3.setBounds(37, 232, 85, 14);
        add(lblNewLabel_3);
        
        ujintezkedes_mezo = new JTextArea();
        ujintezkedes_mezo.setLineWrap(true);
        ujintezkedes_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto3 = new JScrollPane(ujintezkedes_mezo);
        gorgeto3.setBounds(121, 232, 221, 96);
        add(gorgeto3);
        
        JLabel lblNewLabel_4 = new JLabel("Felelős");
        lblNewLabel_4.setBounds(380, 232, 46, 14);
        add(lblNewLabel_4);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(484, 229, 221, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Határidő");
        lblNewLabel_5.setBounds(380, 265, 74, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Jelenlegi kockázati szint");
        lblNewLabel_6.setBounds(794, 100, 148, 14);
        add(lblNewLabel_6);
        
        kockazatiszint_mezo = new JTextField();
        kockazatiszint_mezo.setEditable(false);
        kockazatiszint_mezo.setText(szint);
        kockazatiszint_mezo.setBounds(952, 97, 46, 20);
        add(kockazatiszint_mezo);
        kockazatiszint_mezo.setColumns(10);
        
        hatarido_mezo = new JTextArea();
        hatarido_mezo.setLineWrap(true);
        hatarido_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto4 = new JScrollPane(hatarido_mezo);
        gorgeto4.setBounds(484, 260, 135, 68);
        add(gorgeto4);
        
        JLabel lblNewLabel_7 = new JLabel("Új határidő");
        lblNewLabel_7.setBounds(722, 265, 74, 14);
        add(lblNewLabel_7);
        
        ujhatarido_mezo = new JTextArea();
        ujhatarido_mezo.setBounds(819, 260, 123, 68);
        add(ujhatarido_mezo);
        
        JLabel lblNewLabel_8 = new JLabel("Állapot");
        lblNewLabel_8.setBounds(37, 385, 46, 14);
        add(lblNewLabel_8);
        
        allapot_mezo = new JTextField();
        allapot_mezo.setBounds(121, 382, 46, 20);
        add(allapot_mezo);
        allapot_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Valószínűség");
        lblNewLabel_9.setBounds(37, 442, 85, 14);
        add(lblNewLabel_9);
        
        valoszinu_mezo = new JTextField();
        valoszinu_mezo.setBounds(121, 439, 46, 20);
        add(valoszinu_mezo);
        valoszinu_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Következmény");
        lblNewLabel_10.setBounds(190, 442, 97, 14);
        add(lblNewLabel_10);
        
        kovetkezmeny_mezo = new JTextField();
        kovetkezmeny_mezo.setBounds(279, 439, 46, 20);
        kovetkezmeny_mezo.addKeyListener(new Szamol());
        add(kovetkezmeny_mezo);
        kovetkezmeny_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Kockázati szint");
        lblNewLabel_11.setBounds(380, 442, 97, 14);
        add(lblNewLabel_11);
        
        ujkockazatiszint_mezo = new JTextField();
        ujkockazatiszint_mezo.setBounds(484, 439, 46, 20);
        add(ujkockazatiszint_mezo);
        ujkockazatiszint_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(567, 513, 89, 23);
        add(mentes_gomb);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(37, 418, 1166, 10);
        add(separator);
        
        setBackground(Foablak.hatter_szine);
        
        JButton vissza_gomb = new JButton("Vissza");
        vissza_gomb.addActionListener(new Vissza());
        vissza_gomb.setBounds(1078, 96, 89, 23);
        add(vissza_gomb);
        
        visszatolt();

    }
    
    private void visszatolt()
    {
        try 
        {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
            Statement stmt = con.createStatement();
            String sql = "Select * from qualitydb.Kockazatimatrix_intezkedes where Kockazat = '"+ kockazat_mezo.getText() +"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                ujintezkedes_mezo.setText(rs.getString(3));
                felelos_mezo.setText(rs.getString(4));
                hatarido_mezo.setText(rs.getString(5));
                ujhatarido_mezo.setText(rs.getString(7));
                allapot_mezo.setText(rs.getString(6));
                valoszinu_mezo.setText(rs.getString(8));
                kovetkezmeny_mezo.setText(rs.getString(9));
                ujkockazatiszint_mezo.setText(rs.getString(10));                
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
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {               
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("com.mysql.cj.jdbc.Driver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                                      
                Statement stmt = con.createStatement();
                String sql = "Select * from qualitydb.Kockazatimatrix_intezkedes where Kockazat = '"+ kockazat_mezo.getText() +"'";
                ResultSet rs = stmt.executeQuery(sql);
                SQA_SQL ment = new SQA_SQL();
                if(rs.next())
                {
                    sql = "update qualitydb.Kockazatimatrix_intezkedes set Intezkedes = '"+ ujintezkedes_mezo.getText() +"', Felelos = '"+ felelos_mezo.getText() +"', Hatarido = '"+ hatarido_mezo.getText() +"',"
                          + " Allapot = '"+ allapot_mezo.getText() +"', Uj_Hatarido = '"+ ujhatarido_mezo.getText() +"', Valoszinuseg = '"+ valoszinu_mezo.getText() +"', "
                          + " Kovetkezmeny = '"+ kovetkezmeny_mezo.getText() +"', Kockazati_szint = '"+ ujkockazatiszint_mezo.getText() +"' where kockazat = '"+ kockazat_mezo.getText() +"'";
                    ment.mindenes(sql);
                }
                else
                {
                    sql = "insert into qualitydb.Kockazatimatrix_intezkedes (Kockazat, Intezkedes,Felelos,Hatarido,Allapot,Uj_hatarido,Valoszinuseg,Kovetkezmeny,Kockazati_szint) "
                            + "Values('"+kockazat_mezo.getText()+"','"+ujintezkedes_mezo.getText()+"','"+felelos_mezo.getText()+"','"+hatarido_mezo.getText()+"','"+allapot_mezo.getText()+"',"
                            + "'"+ujhatarido_mezo.getText()+"','"+valoszinu_mezo.getText()+"','"+kovetkezmeny_mezo.getText() +"','"+ ujkockazatiszint_mezo.getText() +"')";
                    ment.mindenes(sql);
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Mentve", "Info", 1);
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
    
    class Vissza implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {               
                Kockazatimatrix_osszesites osszesites = new Kockazatimatrix_osszesites();
                JScrollPane ablak = new JScrollPane(osszesites);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
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
    
    class Szamol implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) {            
        }
        @Override
        public void keyTyped(KeyEvent e) {                                                //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom                    
        }
        @Override
        public void keyReleased(KeyEvent e)                                             //kötelezően kell implementálni, de ezt nem akarom figyelni, így üresen hagyom 
        {
            try
            {
                int valo = Integer.parseInt(valoszinu_mezo.getText());
                int kovet = Integer.parseInt(kovetkezmeny_mezo.getText());
                int kockazat = valo * kovet;
                ujkockazatiszint_mezo.setText(String.valueOf(kockazat));
            }
            catch (Exception e1) 
            {                             
                JOptionPane.showMessageDialog(null, "Nincs megadva a Valószínűség vagy a következmény!", "Hiba üzenet", 2);
            }
        }    
    }
}
