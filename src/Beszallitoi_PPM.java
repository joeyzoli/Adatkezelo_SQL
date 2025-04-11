import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Beszallitoi_PPM extends JPanel {
    
    private JXDatePicker datum_tol;
    private JXDatePicker datum_ig;
    private JComboBox<String> beszallito_box;
    private JComboBox<String> cikk_box;
    private DefaultComboBoxModel<String> model;
    

    /**
     * Create the panel.
     */
    public Beszallitoi_PPM() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Beszállítói PPM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(556, 35, 167, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum -tól");
        lblNewLabel_1.setBounds(507, 83, 86, 14);
        add(lblNewLabel_1);
        
        datum_tol = new JXDatePicker();
        datum_tol.setBounds(603, 80, 120, 20);
        add(datum_tol);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -ig");
        lblNewLabel_2.setBounds(505, 119, 72, 14);
        add(lblNewLabel_2);
        
        datum_ig = new JXDatePicker();
        datum_ig.setBounds(603, 116, 120, 20);
        add(datum_ig);
        
        JLabel lblNewLabel_3 = new JLabel("Beszállító");
        lblNewLabel_3.setBounds(507, 160, 86, 14);
        add(lblNewLabel_3);
        
        beszallito_box = new JComboBox<String>();
        beszallito_box.setBounds(603, 156, 377, 22);
        add(beszallito_box);
        
        JLabel lblNewLabel_4 = new JLabel("Cikkszám");
        lblNewLabel_4.setBounds(507, 206, 86, 14);
        add(lblNewLabel_4);
        
        cikk_box = new JComboBox<String>();
        cikk_box.setBounds(603, 202, 377, 22);
        add(cikk_box);
        
        beolvasas();
        
        setBackground(Foablak.hatter_szine);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new PPM());
        start_gomb.setBounds(603, 260, 89, 23);
        add(start_gomb);

    }
    
    private void beolvasas()
    {
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO),\r\n"
                    + "VENDOR_NO\r\n"
                    + "from ifsapp.PURCHASE_PART_SUPPLIER\r\n"
                    + "where 3=3\r\n"
                    + "group by VENDOR_NO ORDER by ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO) asc");
            ArrayList<String> adatok = new ArrayList<String>();
            while(rs.next())
            {
                adatok.add(rs.getString(1) +";"+ rs.getString(2));
            }
            String[] beszallitok = new String[adatok.size()];
            for(int szamlalo = 0; szamlalo < adatok.size(); szamlalo++)
            {
                beszallitok[szamlalo] = adatok.get(szamlalo);
            }
            model = new DefaultComboBoxModel<>(beszallitok);
            beszallito_box.setModel(model);
            
            ////////////////////////////////////////////////
            
            rs = stmt.executeQuery("select part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)\r\n"
                    + "from ifsapp.PART_REVISION\r\n"
                    + "where 3 = 3\r\n"
                    + "group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)\r\n"
                    + " order by part_no asc");
            adatok.clear();
            while(rs.next())
            {
                adatok.add(rs.getString(1) +";"+ rs.getString(2));
            }
            beszallitok = new String[adatok.size()];
            for(int szamlalo = 0; szamlalo < adatok.size(); szamlalo++)
            {
                beszallitok[szamlalo] = adatok.get(szamlalo);
            }
            model = new DefaultComboBoxModel<>(beszallitok);
            cikk_box.setModel(model);
        }
        catch(Exception e1)
        { 
            System.out.println(e1);
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
        }
    }
    
    class PPM implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                
                //Workbook workbook = new Workbook();
                //Worksheet sheet = workbook.getWorksheets().get(0);
                
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();
                String sql = "select berkezve.beerkezve, felhasznalas.felhasznalva, befele.be, kifele.ki, ossz_selejt.osszes_selejt, zarolt_selejt.zarolt_selejt\r\n"
                        + "from\r\n"
                        + "(select \r\n"
                        + "    sum(quantity) beerkezve\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "where 3 = 3\r\n"
                        + "    and TRANSACTION_CODE = 'ARRIVAL'\r\n"
                        + "    and part_no = '320-759-69'\r\n"
                        + "    and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) berkezve,\r\n"
                        + "(select \r\n"
                        + "    sum(quantity) felhasznalva\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "where 3 = 3\r\n"
                        + "    and TRANSACTION_CODE in ('OOREC', 'BACFLUSH', 'SOISS')\r\n"
                        + "    and part_no = '320-759-69'\r\n"
                        + "    and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) felhasznalas,\r\n"
                        + "(select \r\n"
                        + "    sum(quantity) ki\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "where 3 = 3\r\n"
                        + "    and TRANSACTION_CODE = 'INVM-ISS'\r\n"
                        + "    and part_no = '320-759-69'\r\n"
                        + "    and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                        + "    and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) kifele,\r\n"
                        + "\r\n"
                        + "(select \r\n"
                        + "        sum(quantity) be\r\n"
                        + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "    where 3 = 3\r\n"
                        + "        and TRANSACTION_CODE = 'INVM-IN'\r\n"
                        + "        and part_no = '320-759-69'\r\n"
                        + "        and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                        + "        and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) befele,\r\n"
                        + "          \r\n"
                        + "(select\r\n"
                        + "sum(quantity) osszes_selejt\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "where 3 = 3\r\n"
                        + "    and ((TRANSACTION_CODE like  '%SCP%' and transaction_code not in('OPFEED-SCP','UNOPFDSCP'))\r\n"
                        + "        or TRANSACTION_CODE like  '%SCRAP%'\r\n"
                        + "        or (TRANSACTION_CODE = 'NREC' and C_REASON_CODE = 'UNSCRAP'))\r\n"
                        + "    and part_no = '320-759-69'\r\n"
                        + "    and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) ossz_selejt,\r\n"
                        + "    \r\n"
                        + "(select sum(meselejtek.quantity) zarolt_selejt\r\n"
                        + "from ifsapp.INVENTORY_TRANSACTION_HIST2 meselejtek,\r\n"
                        + "            (select \r\n"
                        + "                sum(quantity) be, WAIV_DEV_REJ_NO me\r\n"
                        + "            from ifsapp.INVENTORY_TRANSACTION_HIST2\r\n"
                        + "            where 3 = 3\r\n"
                        + "                and (TRANSACTION_CODE = 'INVM-IN' or TRANSACTION_CODE = 'INVM-ISS')\r\n"
                        + "                and part_no = '320-759-69'\r\n"
                        + "                and (LOCATION_NO = '80' or LOCATION_NO = '91')\r\n"
                        + "                and DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )\r\n"
                        + "                group by WAIV_DEV_REJ_NO) befele\r\n"
                        + "\r\n"
                        + "where 3 = 3\r\n"
                        + "    and ((meselejtek.TRANSACTION_CODE like  '%SCP%' and meselejtek.transaction_code not in('OPFEED-SCP','UNOPFDSCP'))\r\n"
                        + "        or meselejtek.TRANSACTION_CODE like  '%SCRAP%'\r\n"
                        + "        or (meselejtek.TRANSACTION_CODE = 'NREC' and meselejtek.C_REASON_CODE = 'UNSCRAP'))\r\n"
                        + "    and meselejtek.part_no = '320-759-69'\r\n"
                        + "    and befele.me = meselejtek.WAIV_DEV_REJ_NO\r\n"
                        + "    and meselejtek.DATE_CREATED between to_date( '2025.03.01', 'YYYY.MM.DD' ) and to_date( '2025.03.30', 'YYYY.MM.DD' ) + ( 1 - 1/ ( 60*60*24 ) )) zarolt_selejt\r\n"
                        + "    ";
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    //cikkbox = rs.getString(1);
                }
                
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                System.out.println(dateFormat.format(datum_tol.getDate()).toString());          //.replace(" ", "")
                con.close();  
                Foablak.frame.setCursor(null);   
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
}
