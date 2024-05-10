import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

public class Vevoireklamacio_koltsegek extends JPanel {
    private JTextField belso_mezo;
    private JTextField fuvar_mezo;
    private JTextField selejt_mezo;
    private JTextField egyeb_mezo;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_koltsegek() {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Belső költség");
        lblNewLabel.setBounds(539, 87, 87, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Fuvar költség");
        lblNewLabel_1.setBounds(539, 128, 87, 14);
        add(lblNewLabel_1);
        
        belso_mezo = new JTextField();
        belso_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        belso_mezo.setText("0");
        belso_mezo.setBounds(654, 84, 86, 20);
        add(belso_mezo);
        belso_mezo.setColumns(10);
        
        fuvar_mezo = new JTextField();
        fuvar_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        fuvar_mezo.setText("0");
        fuvar_mezo.setBounds(654, 125, 86, 20);
        add(fuvar_mezo);
        fuvar_mezo.setColumns(10);
        
        selejt_mezo = new JTextField();
        selejt_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        selejt_mezo.setText("0");
        selejt_mezo.setBounds(654, 171, 86, 20);
        add(selejt_mezo);
        selejt_mezo.setColumns(10);
        
        egyeb_mezo = new JTextField();
        egyeb_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        egyeb_mezo.setText("0");
        egyeb_mezo.setBounds(654, 216, 86, 20);
        add(egyeb_mezo);
        egyeb_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Selejt költség");
        lblNewLabel_2.setBounds(539, 174, 87, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Egyéb költség");
        lblNewLabel_3.setBounds(539, 219, 87, 14);
        add(lblNewLabel_3);
        
        JButton szamol_gomb = new JButton("Számol");
        szamol_gomb.addActionListener(new Koltseg_szamol());
        szamol_gomb.setBounds(651, 278, 89, 23);
        add(szamol_gomb);
        
        JLabel lblNewLabel_4 = new JLabel("Válogatási költség számítása");
        lblNewLabel_4.setBounds(455, 282, 171, 14);
        add(lblNewLabel_4);

    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Belso_koltseg = '"+ belso_mezo.getText() +"', Fuvar_koltseg = '"+ fuvar_mezo.getText() +"', "
                    + "Selejt_koltseg = '"+ selejt_mezo.getText() +"', Fuvar_koltseg = '"+ egyeb_mezo.getText() +"'   where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";           
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String sql = "select Belso_koltseg, Fuvar_koltseg, Selejt_koltseg, Fuvar_koltseg from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {
                belso_mezo.setText(rs.getString(1));
                fuvar_mezo.setText(rs.getString(2));
                selejt_mezo.setText(rs.getString(3));
                egyeb_mezo.setText(rs.getString(4));
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
    
    class Koltseg_szamol implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                SQA_SQL lekerdezes = new SQA_SQL();
                String[] cikkszam = String.valueOf(Vevoireklamacio_d0.tipus_box.getSelectedItem()).split(" ");
                String sql = "select sum(ellenorzes_ido)/60*7 \r\n"
                        + "from qualitydb.Zarolasok\r\n"
                        + "where 3 = 3 \r\n"
                        + "    and Eszleles_helye like '%Vevői%'\r\n"
                        + "    and Tipus like '"+ cikkszam[0] +"%'\r\n"
                        + "    and DATEDIFF(Zarolas_datuma, '"+ Vevoireklamacio_d0.ertesitve.getJFormattedTextField().getText() +"') < 3";
                lekerdezes.tombvissza_sajat(sql);
                float eddig = Float.valueOf(belso_mezo.getText()) + Float.valueOf(lekerdezes.tombvissza_sajat(sql)[0]);
                belso_mezo.setText(String.valueOf((int) eddig));
                Foablak.frame.setCursor(null);                                                                                  //egér mutató alaphelyzetbe állítása
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
