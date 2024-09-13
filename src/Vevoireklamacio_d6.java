import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Vevoireklamacio_d6 extends JPanel {

    /**
     * Create the panel.
     */
    private JTextArea megerosites_mezo;
    private JTextField lezaras_mezo;
    private JTextField felelos_mezo;
    
    public Vevoireklamacio_d6() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Intézkedések hatékonyságának megerősítése");
        lblNewLabel.setBounds(545, 49, 293, 14);
        add(lblNewLabel);
        
        megerosites_mezo = new JTextArea();
        megerosites_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        megerosites_mezo.setLineWrap(true);
        megerosites_mezo.setWrapStyleWord(true);
        JScrollPane gorgeto = new JScrollPane(megerosites_mezo);        
        gorgeto.setBounds(121, 84, 980, 465);
        add(gorgeto);
        
        JLabel lblNewLabel_1 = new JLabel("Határidő");
        lblNewLabel_1.setBounds(1151, 168, 65, 14);
        add(lblNewLabel_1);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.setBounds(1212, 165, 86, 20);
        lezaras_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Felelős");
        lblNewLabel_2.setBounds(1151, 111, 46, 14);
        add(lblNewLabel_2);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(1212, 108, 166, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);

    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Hatekonysag = '"+ megerosites_mezo.getText() +"', D6_felelos = '"+ felelos_mezo.getText() +"',"
                    + "D6_lezaras = '"+ lezaras_mezo.getText() +"' where ID = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";           
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
        String sql = "select Hatekonysag, D6_lezaras, D6_felelos from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        megerosites_mezo.setText("");
        lezaras_mezo.setText("");
        if(rs.next())
        {           
            megerosites_mezo.setText(rs.getString(1));
            lezaras_mezo.setText(rs.getString(2));
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
