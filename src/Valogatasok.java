import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Valogatasok extends JPanel {
    private JTextField kezdet_mezo;
    private JTextField ok_mezo;
    private JTextField nok_mezo;
    private JTextField muszak_mezo;
    private JTextField ossz_mezo;
    private JTextField id_mezo;
    private JTextField allok_mezo;
    private JTextField allnok_mezo;
    private JTextField datum_mezo;
    private JTextField sorszam_mezo;
    private JTextField valogatasoka_mezo;
    private JTable table;
    private JComboBox<String> cikk_box;
    private JComboBox<String> email_box;
    private JRadioButton zarolasmiatt_gomb;
    private JRadioButton zarolasnelkül_gomb;
    private DefaultTableModel modell;
    private String cc = "rabine.anita@veas.videoton.hu,sagi.szilvia@veas.videoton.hu,juhasz.iren@veas.videoton.hu,tatai.mihaly@veas.videoton.hu";
    //private String cc = "kovacs.zoltan@veas.videoton.hu";


    /**
     * Create the panel.
     */
    public Valogatasok(int szam, String id) {
        setLayout(null);
        
        setBackground(Foablak.hatter_szine);
        
        this.setPreferredSize(new Dimension(1245, 759));
        Foablak.meretek.setSize(1245, 750);
        
        JLabel lblNewLabel = new JLabel("Válogatás eredményének rögzítése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(477, 22, 293, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cikkszám");
        lblNewLabel_1.setBounds(11, 96, 64, 14);
        add(lblNewLabel_1);
        
        cikk_box = new JComboBox<String>();
        cikk_box.setBounds(85, 92, 278, 22);
        add(cikk_box);
        
        JLabel lblNewLabel_2 = new JLabel("Válogatás kezdete");
        lblNewLabel_2.setBounds(389, 96, 120, 14);
        add(lblNewLabel_2);
        
        kezdet_mezo = new JTextField();
        kezdet_mezo.setBounds(503, 93, 86, 20);
        add(kezdet_mezo);
        kezdet_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("OK");
        lblNewLabel_3.setBounds(332, 144, 31, 14);
        add(lblNewLabel_3);
        
        ok_mezo = new JTextField();
        ok_mezo.setBounds(366, 141, 86, 20);
        add(ok_mezo);
        ok_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("NOK");
        lblNewLabel_4.setBounds(462, 144, 37, 14);
        add(lblNewLabel_4);
        
        nok_mezo = new JTextField();
        nok_mezo.setBounds(503, 141, 86, 20);
        add(nok_mezo);
        nok_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Műszak");
        lblNewLabel_5.setBounds(197, 144, 46, 14);
        add(lblNewLabel_5);
        
        muszak_mezo = new JTextField();
        muszak_mezo.setBounds(253, 141, 46, 20);
        add(muszak_mezo);
        muszak_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Válogatásra váró mennyiség");
        lblNewLabel_6.setBounds(609, 96, 172, 14);
        add(lblNewLabel_6);
        
        ossz_mezo = new JTextField();
        ossz_mezo.setBounds(791, 93, 86, 20);
        add(ossz_mezo);
        ossz_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("ID");
        lblNewLabel_7.setBounds(50, 51, 25, 14);
        add(lblNewLabel_7);
        
        id_mezo = new JTextField();
        id_mezo.setText(id);
        id_mezo.setBounds(85, 48, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("All OK");
        lblNewLabel_8.setBounds(903, 96, 46, 14);
        add(lblNewLabel_8);
        
        allok_mezo = new JTextField();
        allok_mezo.setText("0");
        allok_mezo.setBounds(959, 93, 46, 20);
        add(allok_mezo);
        allok_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("All NOK");
        lblNewLabel_9.setBounds(1019, 96, 46, 14);
        add(lblNewLabel_9);
        
        allnok_mezo = new JTextField();
        allnok_mezo.setText("0");
        allnok_mezo.setBounds(1075, 93, 46, 20);
        add(allnok_mezo);
        allnok_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Dátum");
        lblNewLabel_10.setBounds(11, 144, 46, 14);
        add(lblNewLabel_10);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(85, 141, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        zarolasmiatt_gomb = new JRadioButton("Zárolás miatti válogatás");
        zarolasmiatt_gomb.setBounds(85, 190, 174, 23);
        add(zarolasmiatt_gomb);
        
        zarolasnelkül_gomb = new JRadioButton("Zárolás nélküli válogatás");
        zarolasnelkül_gomb.setBounds(87, 228, 172, 23);
        add(zarolasnelkül_gomb);
        
        JLabel lblNewLabel_11 = new JLabel("Zároló papír sorszáma");
        lblNewLabel_11.setBounds(275, 194, 130, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Válogatás oka");
        lblNewLabel_12.setBounds(275, 232, 109, 14);
        add(lblNewLabel_12);
        
        sorszam_mezo = new JTextField();
        sorszam_mezo.setBounds(415, 191, 86, 20);
        add(sorszam_mezo);
        sorszam_mezo.setColumns(10);
        
        valogatasoka_mezo = new JTextField();
        valogatasoka_mezo.setBounds(415, 229, 706, 20);
        add(valogatasoka_mezo);
        valogatasoka_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Címzettek");
        lblNewLabel_13.setBounds(11, 282, 64, 14);
        add(lblNewLabel_13);
        
        String[] emailcimek = {"gepesfolyamatellenor@veas.videoton.hu","quality.control@veas.videoton.hu","quality.inspection@veas.videoton.hu","folyamatellenor.163@veas.videoton.hu","hager.security.oqc@veas.videoton.hu","loxoneoqc@veas.videoton.hu",
                "avmoqc@veas.videoton.hu","osram_vegellenorzes@veas.videoton.hu","loxoneoqc@veas.videoton.hu","hager.security.oqc@veas.videoton.hu","techemoqc@veas.videoton.hu","telecomdesignoqc@veas.videoton.hu","proglove@veas.videoton.hu",
                "borbely.szilvia@veas.videoton.hu","mile.jozsef@veas.videoton.hu","reznyak.norbert@veas.videoton.hu","szatmari.edina@veas.videoton.hu","tisler.peter@veas.videoton.hu","pinter.attila@veas.videoton.hu",
                "ternak.sandor@veas.videoton.hu","kadar.zoltan@veas.videoton.hu","etl.csaba@veas.videoton.hu","nagy.balint@veas.videoton.hu","molnar.jozsef@veas.videoton.hu","csader.zsolt@veas.videoton.hu",
                "QA-Assy@veas.videoton.hu","kovacs.zoltan@veas.videoton.hu","tatai.mihaly@veas.videoton.hu"};
        email_box = new JComboBox<String>(emailcimek);
        email_box.setBounds(87, 278, 268, 22);
        email_box.addActionListener(new Hozzaad());
        add(email_box);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Címzettek"});
                
        table = new JTable(){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
          };;
        table.setModel(modell);
        table.setBounds(415, 271, 278, 76);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                   JTable target = (JTable)me.getSource();
                   int row = target.getSelectedRow(); // select a row 
                   try
                   {
                       modell.removeRow(row);
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
          });

        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(415, 271, 268, 121);
        add(gorgeto);
        
        JButton mentes_gomb = new JButton("Metés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(524, 425, 89, 23);
        add(mentes_gomb);
        
        ButtonGroup G = new ButtonGroup();
        G.add(zarolasmiatt_gomb);
        G.add(zarolasnelkül_gomb);
        
        cikkszamok();
      
        if(szam == 1)
        {
            visszatolt();
        }
        else
        {
            Utolso_sor sorszam = new Utolso_sor();
            int kovetkezo = Integer.parseInt(sorszam.utolso("qualitydb.Valogatasok_alap"));
            id_mezo.setText(String.valueOf(kovetkezo + 1));
        }

    }
    
    class Mentes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            Statement stmt2 = null;
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            try 
            {
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               stmt2 = (Statement) conn.createStatement();
               
               String cimzettek = "";
               String oka = "";
               String mi = "";
               if(zarolasmiatt_gomb.isSelected())
               {
                   oka = sorszam_mezo.getText();
                   mi = "zarolasmiatt";
               }
               else
               {
                   oka = valogatasoka_mezo.getText();
                   mi = "zarolasnelkul";
               }
               
               for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
               {
                   cimzettek += table.getValueAt(szamlalo, 0).toString() +";";
               }
 
               ResultSet rs = null;              
               rs = stmt.executeQuery("select * from qualitydb.Valogatasok_alap where id = '"+ id_mezo.getText() +"'");
               
               if(rs.next())
               {
                   stmt2.execute("update qualitydb.Valogatasok_alap set Cikkszam = '"+ String.valueOf(cikk_box.getSelectedItem()) +"',kezdet = '"+ kezdet_mezo.getText() +"',"
                           + "Db = '"+ ossz_mezo.getText() +"',Oka = '"+ mi +"',sorszam = '"+ oka +"',"
                           + "Cimzettek = '"+ cimzettek +"' where id = '"+ id_mezo.getText() +"'");
                   
                   stmt2.execute("insert into qualitydb.Valogatasok_adatok (Valogatas_ID, Datum, Muszak, OK, NOK) Values('"+ id_mezo.getText() +"','"+ datum_mezo.getText() +"',"
                           + " '"+ muszak_mezo.getText() +"','"+ ok_mezo.getText() +"','"+ nok_mezo.getText() +"')");
               }
               else
               {
                   stmt2.execute("insert into qualitydb.Valogatasok_alap (Cikkszam, Kezdet, Db, Oka, Sorszam, Cimzettek) Values('"+ String.valueOf(cikk_box.getSelectedItem()) +"','"+ kezdet_mezo.getText() +"',"
                           + " '"+ ossz_mezo.getText() +"','"+ mi +"','"+ oka +"','"+ cimzettek +"')");
                   
                   stmt2.execute("insert into qualitydb.Valogatasok_adatok (Valogatas_ID, Datum, Muszak, OK, NOK) Values('"+ id_mezo.getText() +"','"+ datum_mezo.getText() +"',"
                           + " '"+ muszak_mezo.getText() +"','"+ ok_mezo.getText() +"','"+ nok_mezo.getText() +"')");
               }
               
               cimzettek = "";
               for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
               {
                   cimzettek += table.getValueAt(szamlalo, 0).toString() +",";
               }
               cimzettek = cimzettek.substring(0, cimzettek.length() - 1);
               
               Email email = new Email();
               String tartalom = "Sziasztok!\r\n"
                       + "\r\n"
                       + "Az alábbi eredmény született a tárgyban szereplő cikkszámra:\r\n"
                       + "Dátum: "+ datum_mezo.getText() +"\r\n"
                       + "Műszak: "+ muszak_mezo.getText() +"\r\n"
                       + "OK: "+ ok_mezo.getText() +"\r\n"
                       + "NOK: "+ nok_mezo.getText() +"\r\n"
                       + "\r\n"
                       + "A válogatás eddigi eredménye:\r\n"
                       + "Össz OK: "+ allok_mezo.getText() +"\r\n"
                       + "Össz NOK: "+ allnok_mezo.getText() +"\r\n"
                       + "\r\n"
                       + "Üdvözlettel: EASQAS program";
               
               email.mindenes_email("easqas@veas.videoton.hu", cimzettek, cc, "Válogatás eredménye "+ String.valueOf(cikk_box.getSelectedItem()), tartalom);
               
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
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
    
    private void cikkszamok()
    {
        //String[] cikkbox = null;
        DefaultComboBoxModel<String> model;
        try
        {           
            SQA_SQL betolt = new SQA_SQL();            
            model = new DefaultComboBoxModel<String>(betolt.tombvissza_sajat("select cikkek from qualitydb.Cikk_valtozatok where 3 = 3"));                     //cikkbox
            cikk_box.setModel(model); 
        }
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
        }
    }
    
    class Hozzaad implements ActionListener                                                                                             //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                modell.addRow(new Object[]{String.valueOf(email_box.getSelectedItem())});
                table.setModel(modell);
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
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
    
    private void visszatolt()
    {
        try
        {           
            Connection conn = null;
            Statement stmt = null;
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            try 
            {
               Class.forName("com.mysql.cj.jdbc.Driver");
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               ResultSet rs = null;              
               rs = stmt.executeQuery("select * from qualitydb.Valogatasok_alap where id = '"+ id_mezo.getText() +"'");
               if(rs.next())
               {
                   cikk_box.setSelectedItem(rs.getString(2));
                   kezdet_mezo.setText(rs.getString(3));
                   ossz_mezo.setText(rs.getString(4));
                   if(rs.getString(5).equals("zarolasmiatt"))
                   {
                       sorszam_mezo.setText(rs.getString(6));
                       zarolasmiatt_gomb.setSelected(true);
                   }
                   else
                   {
                       valogatasoka_mezo.setText(rs.getString(6));
                       zarolasnelkül_gomb.setSelected(true);
                   }
                   String[] emailok = rs.getString(7).split(";");
                   for(int szamlalo = 0; szamlalo < emailok.length; szamlalo++)
                   {
                       modell.addRow(new Object[]{emailok[szamlalo]});
                       
                   }
                   table.setModel(modell);
               }
               
               rs = stmt.executeQuery("select cast(sum(OK) as decimal(15,0)), cast(sum(NOK) as decimal(15,0)) from qualitydb.Valogatasok_adatok where Valogatas_ID = '"+ id_mezo.getText() +"'");
               if(rs.next())
               {
                   allok_mezo.setText(rs.getString(1));
                   allnok_mezo.setText(rs.getString(2));
               }
               
               
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
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
        catch (Exception e1) 
        {
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
        }
    }
}
