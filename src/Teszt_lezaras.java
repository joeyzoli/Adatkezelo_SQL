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
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import javax.swing.JTable;

public class Teszt_lezaras extends JPanel 
{
    private JTextField nev_mezo;
    static JTable table;
    private int bennevan = 0;
    //private Object zar_1= new Object();

    /**
     * Create the panel.
     */
    public Teszt_lezaras() 
    {
        ellenorzes();
        /*
        synchronized(zar_1)
        {
            try
            {
                zar_1.wait();           // Várakozunk a jelzésre
            }
            catch (InterruptedException e)
            {
                System.out.print(e);
            }
        }*/
        if(bennevan > 0)
        {
            setLayout(null);
            
            JLabel lblNewLabel = new JLabel("Vizsga eredmények pontozása és lekérdezése");
            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNewLabel.setBounds(421, 39, 328, 23);
            add(lblNewLabel);
            
            JLabel lblNewLabel_1 = new JLabel("Név");
            lblNewLabel_1.setBounds(471, 137, 46, 14);
            add(lblNewLabel_1);
            
            nev_mezo = new JTextField();
            nev_mezo.setBounds(527, 134, 156, 20);
            nev_mezo.addKeyListener(new Enter());
            add(nev_mezo);
            nev_mezo.setColumns(10);
            
            JButton keres_gomb = new JButton("Keresés");
            keres_gomb.addActionListener(new Keres());
            keres_gomb.setBounds(506, 194, 89, 23);
            add(keres_gomb);
            
            table = new JTable();
            JScrollPane gorgeto = new JScrollPane(table);
            gorgeto.setBounds(313, 239, 500, 117);
            add(gorgeto);
            
            JButton pontoz_gomb = new JButton("Pontoz");
            pontoz_gomb.addActionListener(new Pontoz());
            pontoz_gomb.setBounds(506, 386, 89, 23);
            add(pontoz_gomb);
            
            JLabel lblNewLabel_2 = new JLabel("Eredmények");
            lblNewLabel_2.setBounds(416, 457, 86, 14);
            add(lblNewLabel_2);
            
            JButton excel_gomb = new JButton("Excel");
            excel_gomb.addActionListener(new Excel());
            excel_gomb.setBounds(583, 453, 89, 23);
            add(excel_gomb);
            
            setBackground(Foablak.hatter_szine);
        }
        else
        {
            setLayout(null);
        
            JLabel felirat = new JLabel("Nincs jogosultságod megnézni ezt a menüpontot");
            felirat.setHorizontalAlignment(SwingConstants.CENTER);
            felirat.setFont(new Font("Tahoma", Font.BOLD, 16));
            felirat.setBounds(206, 176, 713, 61);
            add(felirat);
            
            setBackground(Foablak.hatter_szine);
        }
        

    }
    
    class Keres implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt lekerdez = new SQL_teszt();
                String sql= "select id, nev, datum, pont from qualitydb.Ellenori_vizsga where 3 = 3 and nev like '" + nev_mezo.getText() + "'";
                lekerdez.lekerdez_mutat(sql, "igen");
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
                try 
                {
                    SQL_teszt lekerdez = new SQL_teszt();
                    String sql= "select id, nev, datum, pont from qualitydb.Ellenori_vizsga where 3 = 3 and nev like '" + nev_mezo.getText() + "'";
                    lekerdez.lekerdez_mutat(sql, "igen");
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
    
    class Pontoz implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt lekerdez = new SQL_teszt();
                for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                {
                    String sql= "update qualitydb.Ellenori_vizsga set  Pont = '"+ table.getValueAt(szamlalo, 3).toString() + "' where id = '"+ table.getValueAt(szamlalo, 0).toString() +"'";
                    lekerdez.lekerdez_mutat(sql, "nem");
                }
                JOptionPane.showMessageDialog(null, "Pontok sikeresn elmentve!", "Infó", 1);
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
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                int column = 0;
                int row = table.getSelectedRow();
                String id = table.getModel().getValueAt(row, column).toString();
                SQL_teszt lekerdez = new SQL_teszt();
                lekerdez.eredmenyek_excel(id);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                JOptionPane.showMessageDialog(null, "Nincs sor kiválasztva!!", "Hiba üzenet", 2);
            }
         }
    }
    
    private void ellenorzes()
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
            String sajat = "select nev from qualitydb.Vizsga_hozzaferes where 3 = 3";
            stmt.execute(sajat);
            ResultSet resultSet = stmt.getResultSet();

            while(resultSet.next())
            {
                if(resultSet.getString(1).equals(System.getProperty("user.name")))
                {
                    bennevan++;
                    System.out.println(resultSet.getString(1));
                }
            }           
            resultSet.close();
            stmt.close();
            conn.close();
            /*
            synchronized(zar_1)
            {
                zar_1.notify();     // Értesítjük a zar_1-t, hogy mehet
            }
            */
        }         
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
    }
}
