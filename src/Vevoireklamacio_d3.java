import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

public class Vevoireklamacio_d3 extends JPanel {
    private JTextField felelos_mezo;
    private JTextField hatarido_mezo;
    private JTextField lezaras_mezo;
    private JTextField felelos2_mezo;
    private JTextField hatarido2_mezo;
    private JTextField lezaras2_mezo;
    private JTextField felelos3_mezo;
    private JTextField hatarido3_mezo;
    private JTextField lezaras3_mezo;
    private JTextField felelos4_mezo;
    private JLabel lblNewLabel_6;
    private JTextField hatarido4_mezo;
    private JTextField lezaras4_mezo;
    private JTextField szallitas_mezo;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JTextField felkesz_ell;
    private JLabel lblNewLabel_9;
    private JTextField felkesz_hibas;
    private JLabel lblNewLabel_10;
    private JTextField kesz_ell;
    private JTextField kesz_hibas;
    private JTextField alap_ell;
    private JTextField alap_hibas;
    private JLabel lblNewLabel_11;
    private JLabel lblNewLabel_12;
    private JLabel lblNewLabel_13;
    private JButton mentes_gomb;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d3() {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Félkész és késztermékek (és adott esetben nyersanyag) blokkolása");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(144, 61, 393, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Felelős");
        lblNewLabel_1.setBounds(620, 30, 46, 14);
        add(lblNewLabel_1);
        
        felelos_mezo = new JTextField();
        felelos_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos_mezo.setBounds(547, 58, 182, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Határidő");
        lblNewLabel_2.setBounds(833, 30, 78, 14);
        add(lblNewLabel_2);
        
        hatarido_mezo = new JTextField();
        hatarido_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido_mezo.setBounds(817, 58, 86, 20);
        add(hatarido_mezo);
        hatarido_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Lezárás dátuma");
        lblNewLabel_3.setBounds(1002, 30, 105, 14);
        add(lblNewLabel_3);
        
        lezaras_mezo = new JTextField();
        lezaras_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras_mezo.setBounds(998, 58, 86, 20);
        add(lezaras_mezo);
        lezaras_mezo.setColumns(10);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos2_mezo.setBounds(547, 100, 182, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        hatarido2_mezo = new JTextField();
        hatarido2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido2_mezo.setBounds(817, 100, 86, 20);
        add(hatarido2_mezo);
        hatarido2_mezo.setColumns(10);
        
        lezaras2_mezo = new JTextField();
        lezaras2_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras2_mezo.setBounds(998, 100, 86, 20);
        add(lezaras2_mezo);
        lezaras2_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("A munkautasítást/normál működés ellenőrzése. ");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(246, 103, 291, 14);
        add(lblNewLabel_4);
        
        felelos3_mezo = new JTextField();
        felelos3_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos3_mezo.setBounds(547, 147, 182, 20);
        add(felelos3_mezo);
        felelos3_mezo.setColumns(10);
        
        hatarido3_mezo = new JTextField();
        hatarido3_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido3_mezo.setBounds(817, 147, 86, 20);
        add(hatarido3_mezo);
        hatarido3_mezo.setColumns(10);
        
        lezaras3_mezo = new JTextField();
        lezaras3_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras3_mezo.setBounds(998, 147, 86, 20);
        add(lezaras3_mezo);
        lezaras3_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("A hiba észlelhetőségének ellenőrzése tesztberendezéssel. ");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(166, 150, 371, 14);
        add(lblNewLabel_5);
        
        felelos4_mezo = new JTextField();
        felelos4_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felelos4_mezo.setBounds(547, 196, 182, 20);
        add(felelos4_mezo);
        felelos4_mezo.setColumns(10);
        
        lblNewLabel_6 = new JLabel("A termelés és minőség-ellenőrzés informálása. ");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(246, 199, 291, 14);
        add(lblNewLabel_6);
        
        hatarido4_mezo = new JTextField();
        hatarido4_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hatarido4_mezo.setBounds(817, 196, 86, 20);
        add(hatarido4_mezo);
        hatarido4_mezo.setColumns(10);
        
        lezaras4_mezo = new JTextField();
        lezaras4_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras4_mezo.setBounds(998, 196, 86, 20);
        add(lezaras4_mezo);
        lezaras4_mezo.setColumns(10);
        
        szallitas_mezo = new JTextField();
        szallitas_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        szallitas_mezo.setBounds(547, 255, 86, 20);
        add(szallitas_mezo);
        szallitas_mezo.setColumns(10);
        
        lblNewLabel_7 = new JLabel("Az utolsó szállítás dátumának ellenőrzése, ahol lehetséges a hiba. ");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(144, 258, 393, 14);
        add(lblNewLabel_7);
        
        lblNewLabel_8 = new JLabel("Ellenőrzött mennyiség");
        lblNewLabel_8.setBounds(547, 305, 134, 14);
        add(lblNewLabel_8);
        
        felkesz_ell = new JTextField();
        felkesz_ell.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felkesz_ell.setBounds(547, 330, 86, 20);
        add(felkesz_ell);
        felkesz_ell.setColumns(10);
        
        lblNewLabel_9 = new JLabel("Hibás mennyiség");
        lblNewLabel_9.setBounds(691, 305, 105, 14);
        add(lblNewLabel_9);
        
        felkesz_hibas = new JTextField();
        felkesz_hibas.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        felkesz_hibas.setBounds(692, 330, 86, 20);
        add(felkesz_hibas);
        felkesz_hibas.setColumns(10);
        
        lblNewLabel_10 = new JLabel("Félkész termékek válogatása: ");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_10.setBounds(288, 333, 249, 14);
        add(lblNewLabel_10);
        
        kesz_ell = new JTextField();
        kesz_ell.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        kesz_ell.setBounds(547, 368, 86, 20);
        add(kesz_ell);
        kesz_ell.setColumns(10);
        
        kesz_hibas = new JTextField();
        kesz_hibas.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        kesz_hibas.setBounds(691, 368, 86, 20);
        add(kesz_hibas);
        kesz_hibas.setColumns(10);
        
        alap_ell = new JTextField();
        alap_ell.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        alap_ell.setBounds(547, 410, 86, 20);
        add(alap_ell);
        alap_ell.setColumns(10);
        
        alap_hibas = new JTextField();
        alap_hibas.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        alap_hibas.setBounds(692, 410, 86, 20);
        add(alap_hibas);
        alap_hibas.setColumns(10);
        
        lblNewLabel_11 = new JLabel("Késztermékek válogatása: ");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_11.setBounds(355, 371, 182, 14);
        add(lblNewLabel_11);
        
        lblNewLabel_12 = new JLabel("Alapanyag válogatása:");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_12.setBounds(372, 413, 165, 14);
        add(lblNewLabel_12);
        
        lblNewLabel_13 = new JLabel("D3 pont lezárása");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_13.setBounds(389, 516, 148, 14);
        add(lblNewLabel_13);
        
        mentes_gomb = new JButton("D3 zárása");
        mentes_gomb.setBounds(547, 512, 119, 23);
        mentes_gomb.addActionListener(new Mentes());
        add(mentes_gomb);

    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Vevoireklamacio_fejlec.d3 = Color.GREEN;
                Vevoireklamacio_fejlec.d5 = Color.YELLOW;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();               
                String maiido = formatter.format(date);
                String sql = "update qualitydb.Vevoireklamacio_alap set D3 = '"+ maiido +"' where ID = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                SQA_SQL beir = new  SQA_SQL();
                beir.mindenes(sql);
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set Felelos1 = '"+ felelos_mezo.getText() +"', Hatarido1 = '"+ hatarido_mezo.getText() +"', "
                    + "Lezaras1 = '"+ lezaras_mezo.getText() +"', Felelos2 = '"+ felelos2_mezo.getText() +"', Hatarido2 = '"+ hatarido2_mezo.getText() +"',"
                    + "Lezaras2 ='"+ lezaras2_mezo.getText() +"', Felelos3 = '"+ felelos3_mezo.getText()+"', Hatarido3 = '"+ hatarido3_mezo.getText() +"',"
                    + "Lezaras3 = '"+ lezaras3_mezo.getText() +"', Felelos4 = '"+ felelos4_mezo.getText() +"', Hatarido4 = '"+ hatarido4_mezo.getText() +"',"
                    + "Lezaras4 = '"+ lezaras4_mezo.getText() +"',Szallitas_datuma = '"+ szallitas_mezo.getText() +"',"
                    + "Felkesz = '"+ felkesz_ell.getText()+";"+ felkesz_hibas.getText() +"', kesz = '"+ kesz_ell.getText()+";"+ kesz_hibas.getText()
                    + "', Alapanyag = '"+ alap_ell.getText()+";"+ alap_hibas.getText()
                    + "' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
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
        String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(rs.next())
        {
            if(rs.getString(23) != null)
            {
                felelos_mezo.setText(rs.getString(23));
                hatarido_mezo.setText(rs.getString(24));
                lezaras_mezo.setText(rs.getString(25));
                felelos2_mezo.setText(rs.getString(26));
                hatarido2_mezo.setText(rs.getString(27));
                lezaras2_mezo.setText(rs.getString(28));
                felelos3_mezo.setText(rs.getString(29));
                hatarido3_mezo.setText(rs.getString(30));
                lezaras3_mezo.setText(rs.getString(31));
                felelos4_mezo.setText(rs.getString(32));
                hatarido4_mezo.setText(rs.getString(33));
                lezaras4_mezo.setText(rs.getString(34));
                szallitas_mezo.setText(rs.getString(35));
                String[] felkesz = rs.getString(36).split(";");
                if(felkesz.length > 1)
                {
                    felkesz_ell.setText(felkesz[0]);
                    felkesz_hibas.setText(felkesz[1]); 
                }
                else if(felkesz.length == 1)
                {
                    felkesz_ell.setText(felkesz[0]);
                    felkesz_hibas.setText("");
                }
                else
                {
                    felkesz_ell.setText("");
                    felkesz_hibas.setText("");
                }
                String[] kesz = rs.getString(37).split(";");
                if(kesz.length > 1)
                {
                    kesz_ell.setText(kesz[0]);
                    kesz_hibas.setText(kesz[1]); 
                }
                else if(kesz.length == 1)
                {
                    kesz_ell.setText(kesz[0]);
                    kesz_hibas.setText("");
                }
                else
                {
                    kesz_ell.setText("");
                    kesz_hibas.setText("");
                }            
                String[] alap = rs.getString(38).split(";");
                if(alap.length > 1)
                {
                    alap_ell.setText(alap[0]);
                    alap_hibas.setText(alap[1]); 
                }
                else if(alap.length == 1)
                {
                    alap_ell.setText(alap[0]);
                    alap_hibas.setText("");
                }
                else
                {
                    alap_ell.setText("");
                    alap_hibas.setText("");
                }
            }
            hatarido();
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
    
    public void hatarido()
    {       
        
        Calendar calendar = Calendar.getInstance();
        String[] datum = Vevoireklamacio_fejlec.ertesites_cimke.getText().split("\\.");
        calendar.set(Integer.parseInt(datum[0]),(Integer.parseInt(datum[1])-1),Integer.parseInt(datum[2])); //Integer.parseInt(datum[0])
        //date=calendar.getTime(); 
        SimpleDateFormat s;
        s=new SimpleDateFormat("yyyy.MM.dd");
        
        //System.out.println(s.format(date));
        
        int days = 2;
        for(int i=0;i<days;)
        {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            //here even sat and sun are added
            //but at the end it goes to the correct week day.
            //because i is only increased if it is week day
            if(calendar.get(Calendar.DAY_OF_WEEK)<=5)
            {
                i++;
            }
        }
        Date date2 = calendar.getTime(); 
        s=new SimpleDateFormat("yyyy.MM.dd");
        
        hatarido_mezo.setText(s.format(date2));
        hatarido2_mezo.setText(s.format(date2));
        hatarido3_mezo.setText(s.format(date2));
        hatarido4_mezo.setText(s.format(date2));
        Vevoireklamacio_fejlec.d3_cimke.setText(s.format(date2));
    }


}
