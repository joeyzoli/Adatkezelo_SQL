import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class Vevoireklamacio_d0 extends JPanel {
    private JTextField vevo_mezo;
    private JTextField beszallito_mezo;
    private JTextField ertesites_mezo;
    private JTextField vevorek_mezo;
    private JTextField veasrek_mezo;
    private JTextField frissites_mezo;
    private JTextField felelos_mezo;
    private JTextField felelos2_mezo;
    private JTextField tipus_mezo;
    private JTextField email_mezo;
    private JTextField email2_mezo;
    private JTextField rma_mezo;
    private JTextField telefonszam_mezo;
    private JTextField telefonszam2_mezo;
    private JTextField visszakuldes_mezo;
    private JLabel lblNewLabel_15;
    static JTable table;
    static DefaultTableModel modell;
    private JSeparator separator;
    

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d0() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vevő");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(214, 57, 46, 14);
        add(lblNewLabel);
        
        vevo_mezo = new JTextField();
        //vevo_mezo.setText("Nem érdekel mi van itt");
        vevo_mezo.setBounds(274, 54, 162, 20);
        add(vevo_mezo);
        vevo_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Beszállító");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(537, 57, 71, 14);
        add(lblNewLabel_1);
        
        beszallito_mezo = new JTextField();
        beszallito_mezo.setText("Videoton EAS Kft.");
        beszallito_mezo.setBounds(618, 54, 162, 20);
        add(beszallito_mezo);
        beszallito_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Értesítés dátuma");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(900, 57, 102, 14);
        add(lblNewLabel_2);
        
        ertesites_mezo = new JTextField();
        ertesites_mezo.addKeyListener(new Enter());
        ertesites_mezo.setBounds(1012, 54, 86, 20);
        add(ertesites_mezo);
        ertesites_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Vevői reklamációs szám");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(119, 105, 141, 14);
        add(lblNewLabel_3);
        
        vevorek_mezo = new JTextField();
        vevorek_mezo.setBounds(274, 102, 86, 20);
        add(vevorek_mezo);
        vevorek_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("VEAS reklmációs szám");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(462, 105, 146, 14);
        add(lblNewLabel_4);
        
        veasrek_mezo = new JTextField();
        veasrek_mezo.setBounds(618, 102, 86, 20);
        add(veasrek_mezo);
        veasrek_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Utolsó frissítés");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(916, 105, 86, 14);
        add(lblNewLabel_5);
        
        frissites_mezo = new JTextField();
        frissites_mezo.setBounds(1012, 102, 86, 20);
        add(frissites_mezo);
        frissites_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Felelős");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(214, 163, 46, 14);
        add(lblNewLabel_6);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(274, 160, 162, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Felelős");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(562, 163, 46, 14);
        add(lblNewLabel_7);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.setBounds(618, 160, 162, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Típus");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(956, 163, 46, 14);
        add(lblNewLabel_8);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(1012, 160, 151, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        email_mezo = new JTextField();
        email_mezo.setBounds(274, 208, 162, 20);
        add(email_mezo);
        email_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("E-mail");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(189, 211, 71, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("E-mail");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_10.setBounds(562, 211, 46, 14);
        add(lblNewLabel_10);
        
        email2_mezo = new JTextField();
        email2_mezo.setBounds(618, 208, 162, 20);
        add(email2_mezo);
        email2_mezo.setColumns(10);
        
        rma_mezo = new JTextField();
        rma_mezo.setBounds(1012, 208, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("RMA szám");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_11.setBounds(931, 211, 71, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Telefonszám");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_12.setBounds(158, 264, 102, 14);
        add(lblNewLabel_12);
        
        telefonszam_mezo = new JTextField();
        telefonszam_mezo.setBounds(274, 261, 162, 20);
        add(telefonszam_mezo);
        telefonszam_mezo.setColumns(10);
        
        telefonszam2_mezo = new JTextField();
        telefonszam2_mezo.setBounds(618, 261, 162, 20);
        add(telefonszam2_mezo);
        telefonszam2_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Telefonszám");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_13.setBounds(522, 264, 86, 14);
        add(lblNewLabel_13);
        
        visszakuldes_mezo = new JTextField();
        visszakuldes_mezo.setBounds(1012, 261, 86, 20);
        add(visszakuldes_mezo);
        visszakuldes_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Visszaküldés dátuma");
        lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_14.setBounds(873, 264, 129, 14);
        add(lblNewLabel_14);
        
        setBackground(Foablak.hatter_szine);
        
        lblNewLabel_15 = new JLabel("Feltöltött fájlok");
        lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_15.setBounds(142, 373, 118, 14);
        add(lblNewLabel_15);
        
        table = new JTable(){
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
              }
            };;
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    if (me.getClickCount() == 2) {     // to detect doble click events
                       JTable target = (JTable)me.getSource();
                       int row = target.getSelectedRow(); // select a row 
                       try
                       {
                           Desktop.getDesktop().open(new File(Vevoireklamacio_fejlec.fajlhelye.get(row)));                            //megnyitja az kiválaszott fájlt
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
              });  
                 
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Fájl tipusa", "Fájl neve"});
        table.setModel(modell);
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(274, 373, 577, 132);        
        add(gorgeto);
        
        separator = new JSeparator();
        separator.setBounds(53, 314, 1254, 46);
        add(separator);

    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Vevoireklamacio_fejlec.ertesites_cimke.setText(ertesites_mezo.getText());
                    SimpleDateFormat rovid = new SimpleDateFormat("yyyy.mm.dd");
                    Date datum = rovid.parse(ertesites_mezo.getText());
                    Date dt = rovid.parse(ertesites_mezo.getText());
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(dt); 
                    c.add(Calendar.DATE, 1);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.qr_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 2);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.d3_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 10);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.d5_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 30);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.lezaras_cimke.setText(rovid.format(dt));
                    
                    Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                    Vevoireklamacio_fejlec.qr = Color.YELLOW;
                    Vevoireklamacio_fejlec.d3 = Color.gray;
                    Vevoireklamacio_fejlec.d5 = Color.gray;
                    Vevoireklamacio_fejlec.lezaras = Color.GRAY;
                    repaint();
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
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            String[] letezik = ment.tombvissza_sajat(sql);
            if(letezik.length > 0)
            {
                sql = "update qualitydb.Vevoireklamacio_alap set mi = '"+ String.valueOf(Vevoireklamacio_fejlec.fajta_box.getSelectedItem()) +"', Vevo = '"+ vevo_mezo.getText() +"', "
                        + "Beszallito = '"+ beszallito_mezo.getText() +"', Ertesites_datuma = '"+ ertesites_mezo.getText() +"', Vevoi_szam = '"+ vevorek_mezo.getText() +"',"
                        + "Utolso_frissites ='"+ frissites_mezo.getText() +"', Felelos = '"+ felelos_mezo.getText()+";"+ felelos2_mezo.getText() +"', Tipus = '"+ tipus_mezo.getText() +"',"
                        + "Email = '"+ email_mezo.getText() +";"+ email2_mezo.getText() +"', RMA_szam = '"+ rma_mezo.getText() +"', Telefon = '"+ telefonszam_mezo.getText() +";"+ telefonszam2_mezo.getText() +"',"
                        + "Visszakuldes_datuma = '"+ visszakuldes_mezo.getText() +"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";                
            }
            else
            {
                sql = "insert into qualitydb.Vevoireklamacio_alap (mi,Vevo,Beszallito,Ertesites_datuma,Vevoi_szam,Utolso_frissites,Felelos,Tipus,Email,RMA_szam,Telefon,Visszakuldes_datuma)  "
                        + "Values('"+ String.valueOf(Vevoireklamacio_fejlec.fajta_box.getSelectedItem()) +"','"+ vevo_mezo.getText() +"',"
                        + "'"+ beszallito_mezo.getText() +"','"+ ertesites_mezo.getText() +"','"+ vevorek_mezo.getText() +"',"
                        + "'"+ frissites_mezo.getText() +"','"+ felelos_mezo.getText()+";"+ felelos2_mezo.getText() +"','"+ tipus_mezo.getText() +"',"
                        + "'"+ email_mezo.getText() +";"+ email2_mezo.getText() +"','"+ rma_mezo.getText() +"','"+ telefonszam_mezo.getText() +";"+ telefonszam2_mezo.getText() +"',"
                        + "'"+ visszakuldes_mezo.getText() +"')";
            }
            ment.mindenes(sql);
            String szin1 = String.valueOf(Vevoireklamacio_fejlec.ertesitve.getRGB());
            String szin2 = String.valueOf(Vevoireklamacio_fejlec.qr.getRGB());
            String szin3 = String.valueOf(Vevoireklamacio_fejlec.d3.getRGB());
            String szin4 = String.valueOf(Vevoireklamacio_fejlec.d5.getRGB());
            String szin5 = String.valueOf(Vevoireklamacio_fejlec.lezaras.getRGB());
            sql = "update qualitydb.Vevoireklamacio_alap set Szinek = '"+ szin1+";"+ szin2+";"+ szin3+";"+ szin4+";"+ szin5+"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
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
        String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
        {
            Vevoireklamacio_fejlec.fajta_box.setSelectedItem(rs.getString(2));
            vevo_mezo.setText(rs.getString(3));
            beszallito_mezo.setText(rs.getString(4));
            ertesites_mezo.setText(rs.getString(5));
            if(rs.getString(5).equals("")){}
            else
            {
                Vevoireklamacio_fejlec.ertesites_cimke.setText(rs.getString(5));
                Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                String[] szinek = rs.getString(49).split(";");
                Color szin2 = new Color(Integer.parseInt(szinek[1]));
                Color szin3 = new Color(Integer.parseInt(szinek[2]));
                Color szin4 = new Color(Integer.parseInt(szinek[3]));
                Color szin5 = new Color(Integer.parseInt(szinek[4]));
                Vevoireklamacio_fejlec.qr = szin2;
                Vevoireklamacio_fejlec.d3 = szin3;
                Vevoireklamacio_fejlec.d5 = szin4;
                Vevoireklamacio_fejlec.lezaras = szin5;
            }                            
            vevorek_mezo.setText(rs.getString(6));
            frissites_mezo.setText(rs.getString(7));
            String[] felelos = rs.getString(8).split(";");
            if(felelos.length > 1)
            {
                felelos_mezo.setText(felelos[0]);
                felelos2_mezo.setText(felelos[1]); 
            }
            else if(felelos.length == 1)
            {
                felelos_mezo.setText(felelos[0]);
                felelos2_mezo.setText("");
            }
            else
            {
                felelos_mezo.setText("");
                felelos2_mezo.setText("");
            }
            tipus_mezo.setText(rs.getString(9));
            String[] email = rs.getString(10).split(";");
            if(email.length > 1)
            {
                email_mezo.setText(felelos[0]);
                email2_mezo.setText(felelos[1]); 
            }
            else if(email.length == 1)
            {
                email_mezo.setText(felelos[0]);
                email2_mezo.setText("");
            }
            else
            {
                email_mezo.setText("");
                email2_mezo.setText("");
            }            
            rma_mezo.setText(rs.getString(11));
            String[] telefon = rs.getString(12).split(";");
            if(telefon.length > 1)
            {
                telefonszam_mezo.setText(felelos[0]);
                telefonszam_mezo.setText(felelos[1]); 
            }
            else if(telefon.length == 1)
            {
                telefonszam_mezo.setText(felelos[0]);
                telefonszam_mezo.setText("");
            }
            else
            {
                telefonszam_mezo.setText("");
                telefonszam_mezo.setText("");
            }        
            visszakuldes_mezo.setText(rs.getString(13));
        }
        stmt.close();
        conn.close();        
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
}