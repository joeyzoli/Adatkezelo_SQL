import javax.swing.JPanel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vevoireklamacio_d8 extends JPanel {
    static JTextField lezaras_datuma;
    private JTextArea megelozo_mezo;
    private JLabel lblNewLabel_2;
    private JTextField felelos_mezo;
    /**
     * Create the panel.
     */
    
    public Vevoireklamacio_d8() {
        setBackground(Foablak.hatter_szine);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Megelőző intézkedés(ek) validálása/szabványosítása ");
        lblNewLabel.setBounds(583, 48, 320, 14);
        add(lblNewLabel);
        
        megelozo_mezo = new JTextArea();
        megelozo_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        megelozo_mezo.setBounds(114, 90, 1183, 356);
        add(megelozo_mezo);
        
        JLabel lblNewLabel_1 = new JLabel("Lezárás dátuma");
        lblNewLabel_1.setBounds(590, 527, 106, 14);
        add(lblNewLabel_1);
        
        lezaras_datuma = new JTextField();
        lezaras_datuma.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras_datuma.setBounds(706, 524, 86, 20);
        add(lezaras_datuma);
        lezaras_datuma.setColumns(10);
        
        lblNewLabel_2 = new JLabel("Felelős");
        lblNewLabel_2.setBounds(590, 485, 46, 14);
        add(lblNewLabel_2);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(706, 482, 171, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);

    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Megelozointezkedes = '"+ megelozo_mezo.getText() +"', Lezaras_datuma = '"+ lezaras_datuma.getText() +"', D8_felelos = '"+ felelos_mezo.getText() +"' "
                    + "where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            if(lezaras_datuma.getText().equals("")) {}
            else
            {
                sql = "update qualitydb.Vevoireklamacio_alap set D8 = '"+ lezaras_datuma.getText() +"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                ment.mindenes(sql);
                Vevoireklamacio_fejlec.lezaras = Color.GREEN;
            }
            
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
            String sql = "select Megelozointezkedes, lezaras_datuma, D8_felelos from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {
                megelozo_mezo.setText(rs.getString(1));
                lezaras_datuma.setText(rs.getString(2));
                felelos_mezo.setText(rs.getString(3));
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

}
