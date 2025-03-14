import javax.swing.JPanel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Vevoireklamacio_d8 extends JPanel {
    static JTextField lezaras_datuma;
    private JTextArea megelozo_mezo;
    private JLabel lblNewLabel_2;
    private JTextField felelos_mezo;
    private JDatePickerImpl lezaras;
    private UtilDateModel model;
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
        
        
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Ma");
        p.put("text.month", "Hónap");
        p.put("text.year", "Év");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        lezaras = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        lezaras_datuma = new JTextField();
        lezaras_datuma.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        lezaras.setBounds(706, 524, 130, 20);
        add(lezaras);
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
            SimpleDateFormat rogzites = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");                                                          //
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sql = "update qualitydb.Vevoireklamacio_alap set Megelozointezkedes = '"+ megelozo_mezo.getText() +"', Lezaras_datuma = '"+ lezaras_datuma.getText() +"', "
                    + "D8_felelos = '"+ felelos_mezo.getText() +"', Modositas_ideje = '"+ rogzites.format(timestamp) + " "+ System.getProperty("user.name") +"' "
                    + "where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            if(lezaras_datuma.getText().equals("")) {}
            else
            {
                sql = "select D5 from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                if(ment.tombvissza_sajat(sql)[0] == null || ment.tombvissza_sajat(sql)[0].equals(""))
                {
                    Vevoireklamacio_fejlec.d5 = Color.GREEN;
                    sql = "update qualitydb.Vevoireklamacio_alap set D5 = '"+ lezaras_datuma.getText() +"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                    ment.mindenes(sql);
                }
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
                if(rs.getString(2).equals("")) {}
                else
                {
                    Date date3 = null;
                    String dateValue = rs.getString(2);
                    try {
                        date3 = new SimpleDateFormat("yyyy.MM.dd").parse(dateValue);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    model.setValue(date3);
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
    
    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy.MM.dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;                   
                
                Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                lezaras_datuma.setText(dateFormatter.format(cal.getTime()));
                return dateFormatter.format(cal.getTime());
            }
            //System.out.println(datePicker.getJFormattedTextField().getText());
            return "";
        }

    }

}
