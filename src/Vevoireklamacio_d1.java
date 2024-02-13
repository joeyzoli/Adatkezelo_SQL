import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Vevoireklamacio_d1 extends JPanel {
    private JTextField vezeto_mezo;
    private JTextArea tagok_mezo;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d1() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Csapatvezető");
        lblNewLabel.setBounds(425, 51, 90, 14);
        add(lblNewLabel);
        
        vezeto_mezo = new JTextField();
        vezeto_mezo.setBounds(553, 48, 263, 20);
        add(vezeto_mezo);
        vezeto_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Csapat tagjai");
        lblNewLabel_1.setBounds(425, 98, 99, 14);
        add(lblNewLabel_1);
        
        tagok_mezo = new JTextArea();
        tagok_mezo.setLineWrap(true);
        tagok_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(tagok_mezo);
        gorgeto.setBounds(553, 93, 263, 248);
        add(gorgeto);
        
        setBackground(Foablak.hatter_szine);

    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Csapattagok = '"+ vezeto_mezo.getText() +";"+ tagok_mezo.getText() +"' where id ='"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";           
            ment.mindenes(sql);
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
        String sql = "select Csapattagok from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
        {           
            String[] csapat = rs.getString(1).split(";");
            if(csapat.length > 1)
            {
                vezeto_mezo.setText(csapat[0]);
                tagok_mezo.setText(csapat[1]); 
            }
            else if(csapat.length == 1)
            {
                vezeto_mezo.setText(csapat[0]);
                tagok_mezo.setText("");
            }
            else
            {
                vezeto_mezo.setText("");
                tagok_mezo.setText("");
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
