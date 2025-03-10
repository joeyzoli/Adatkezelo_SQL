import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class Retour extends JPanel 
{
    private JTextField id_mezo;
    private JTextField datum_mezo;
    private JTextField beerkezett_mezo;
    private JTextField elteres_mezo;
    private JComboBox<String> projekt_box;
    private JComboBox<String> cikk_box;
    private JComboBox<String> javagy_box;
    private JTextField rma_mezo;
    private JTextField megjegyzes_mezo;
    private JTextField datum3_mezo;
    private JTextField datum1_mezo;
    private JTextField felelos3_mezo;
    private JTextField datum4_mezo;
    private JTextField felelos4_mezo;
    private JTextField datum5_mezo;
    private JTextField felelos5_mezo;
    private ComboBox combobox_tomb = new ComboBox();
    private JTextField raktarra_mezo;
    private JTextField raktarradb_mezo;
    private JTextField selejt_mezo;
    private JTextField vevoirma_mezo;
    private DefaultTableModel modell;
    private JTextField datum2_mezo;
    private JTextField felelos2_mezo;
    private JTextField felelos1_mezo;
    private JTextField veas_mezo;
    private JTextField vevo_mezo;
    private JTable table;
    private JLabel db_cimke;
    private SQA_SQL cikkszamok = new SQA_SQL();
    private String excelhelye2 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\CCS2.xlsx";
    //private Kivalaszt valaszto = new Kivalaszt();
    private Workbook workbook = new Workbook();
    private Kivalaszt2 valaszto = new Kivalaszt2();
    private JTextField hibaleiras_mezo;
    
    /**
     * Create the panel.
     */
    public Retour() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Retour adatok");
        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
        lblNewLabel.setBounds(501, 11, 143, 24);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(29, 49, 46, 14);
        add(lblNewLabel_1);
        
        Utolso_sor utolso = new Utolso_sor();
        id_mezo = new JTextField();
        id_mezo.setBounds(85, 46, 46, 20);
        int uccso = Integer.parseInt(utolso.utolso("qualitydb.Retour")) + 1;
        id_mezo.setText(String.valueOf(uccso));
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JButton id_keresgomb = new JButton("Keresés");
        id_keresgomb.addActionListener(new ID());
        id_keresgomb.setBounds(141, 45, 89, 23);
        add(id_keresgomb);
        
        JLabel lblNewLabel_2 = new JLabel("Beérkezés dátuma");
        lblNewLabel_2.setBounds(29, 92, 112, 14);
        add(lblNewLabel_2);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(151, 89, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Vevő");
        lblNewLabel_3.setBounds(29, 129, 35, 14);
        add(lblNewLabel_3);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                      //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(101, 125, 143, 22);
        projekt_box.addActionListener(valaszto);
        add(projekt_box);
        
        JLabel lblNewLabel_4 = new JLabel("Típus");
        lblNewLabel_4.setBounds(29, 169, 46, 14);
        add(lblNewLabel_4);
        
        String sql = "select part_no || '  ' || REVISION_TEXT || '  ' || ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok\r\n"
                + "from ifsapp.PART_REVISION\r\n"
                + "where 3 = 3\r\n"
                + "and ifsapp.inventory_part_api.Get_Part_Product_Code(contract,part_no) = '1'\r\n"
                + "-- and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = 'VLOXN'\r\n"
                + "group by part_no, REVISION_TEXT, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO)\r\n"
                + "ORDER by part_no";
        cikk_box = new JComboBox<String>(cikkszamok.tombvissza(sql));                      //cikkszamok.tombvissza(sql)   //combobox_tomb.getCombobox(ComboBox.vevoi_cikk)
        cikk_box.setBounds(101, 165, 416, 22);
        add(cikk_box);
        
        String[] vagy = {"Javítás", "Átmunkálás"};
        javagy_box = new JComboBox<String>(vagy);                       //vagy
        javagy_box.setBounds(29, 218, 132, 22);
        add(javagy_box);
        
        JLabel lblNewLabel_5 = new JLabel("Beérkezett mennyiség");
        lblNewLabel_5.setBounds(29, 302, 132, 14);
        add(lblNewLabel_5);
        
        beerkezett_mezo = new JTextField();
        beerkezett_mezo.setBounds(171, 299, 46, 20);
        add(beerkezett_mezo);
        beerkezett_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Eltérés");
        lblNewLabel_6.setBounds(245, 302, 46, 14);
        add(lblNewLabel_6);
        
        elteres_mezo = new JTextField();
        elteres_mezo.setBounds(301, 299, 46, 20);
        elteres_mezo.setText("0");
        add(elteres_mezo);
        elteres_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("RMA VEAS");
        lblNewLabel_7.setBounds(29, 344, 84, 14);
        add(lblNewLabel_7);
        
        rma_mezo = new JTextField();
        rma_mezo.setBounds(101, 341, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Megjegyzés");
        lblNewLabel_8.setBounds(29, 265, 84, 14);
        add(lblNewLabel_8);
        
        megjegyzes_mezo = new JTextField();
        megjegyzes_mezo.setBounds(101, 262, 428, 20);
        add(megjegyzes_mezo);
        megjegyzes_mezo.setColumns(10);
        
        JButton mentesgomb = new JButton("Mentés");
        mentesgomb.addActionListener(new Mentes());
        mentesgomb.setBounds(485, 392, 89, 23);
        add(mentesgomb);
        
        JLabel lblNewLabel_9 = new JLabel("Kézire átadva dátuma");
        lblNewLabel_9.setBounds(29, 549, 132, 14);
        add(lblNewLabel_9);
        
        datum3_mezo = new JTextField();
        datum3_mezo.setBounds(220, 546, 86, 20);
        add(datum3_mezo);
        datum3_mezo.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Analízisre átadva dátuma");
        lblNewLabel_10.setBounds(29, 453, 153, 14);
        add(lblNewLabel_10);
        
        datum1_mezo = new JTextField();
        datum1_mezo.setBounds(222, 450, 86, 20);
        add(datum1_mezo);
        datum1_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Felelős");
        lblNewLabel_11.setBounds(366, 549, 46, 14);
        add(lblNewLabel_11);
        
        felelos3_mezo = new JTextField();
        felelos3_mezo.setBounds(444, 546, 143, 20);
        add(felelos3_mezo);
        felelos3_mezo.setColumns(10);
        
        JLabel lblNewLabel_12 = new JLabel("Tesztre átadva dátuma");
        lblNewLabel_12.setBounds(29, 595, 132, 14);
        add(lblNewLabel_12);
        
        datum4_mezo = new JTextField();
        datum4_mezo.setBounds(222, 592, 86, 20);
        add(datum4_mezo);
        datum4_mezo.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Felelős");
        lblNewLabel_13.setBounds(366, 595, 46, 14);
        add(lblNewLabel_13);
        
        felelos4_mezo = new JTextField();
        felelos4_mezo.setBounds(444, 592, 143, 20);
        add(felelos4_mezo);
        felelos4_mezo.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Végellenőrzésre átadva dátuma");
        lblNewLabel_14.setBounds(29, 641, 183, 14);
        add(lblNewLabel_14);
        
        datum5_mezo = new JTextField();
        datum5_mezo.setBounds(222, 638, 86, 20);
        add(datum5_mezo);
        datum5_mezo.setColumns(10);
        
        JLabel lblNewLabel_15 = new JLabel("Felelős");
        lblNewLabel_15.setBounds(366, 641, 46, 14);
        add(lblNewLabel_15);
        
        felelos5_mezo = new JTextField();
        felelos5_mezo.setBounds(444, 638, 143, 20);
        add(felelos5_mezo);
        felelos5_mezo.setColumns(10);
        
        JLabel lblNewLabel_16 = new JLabel("Ratárra adás dátuma");
        lblNewLabel_16.setBounds(29, 688, 169, 14);
        add(lblNewLabel_16);
        
        raktarra_mezo = new JTextField();
        raktarra_mezo.setBounds(220, 685, 86, 20);
        add(raktarra_mezo);
        raktarra_mezo.setColumns(10);
        
        JLabel lblNewLabel_17 = new JLabel("Raktárra adott db szám");
        lblNewLabel_17.setBounds(362, 688, 145, 14);
        add(lblNewLabel_17);
        
        raktarradb_mezo = new JTextField();
        raktarradb_mezo.setBounds(528, 685, 46, 20);
        add(raktarradb_mezo);
        raktarradb_mezo.setColumns(10);
        
        JLabel lblNewLabel_18 = new JLabel("Selejt");
        lblNewLabel_18.setBounds(598, 688, 46, 14);
        add(lblNewLabel_18);
        
        selejt_mezo = new JTextField();
        selejt_mezo.setBounds(664, 685, 46, 20);
        add(selejt_mezo);
        selejt_mezo.setColumns(10);
        
        JButton folyamat_gomb = new JButton("Folyamat rögzítése");
        folyamat_gomb.addActionListener(new Folyamatok());
        folyamat_gomb.setBounds(421, 733, 199, 23);
        add(folyamat_gomb);
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_19 = new JLabel("RMA Vevői");
        lblNewLabel_19.setBounds(222, 344, 84, 14);
        add(lblNewLabel_19);
        
        vevoirma_mezo = new JTextField();
        vevoirma_mezo.setBounds(316, 341, 145, 20);
        add(vevoirma_mezo);
        vevoirma_mezo.setColumns(10);
                
        
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel_20 = new JLabel("Gépesre átadva dátuma");
        lblNewLabel_20.setBounds(29, 503, 140, 14);
        add(lblNewLabel_20);
        
        datum2_mezo = new JTextField();
        datum2_mezo.setBounds(222, 500, 86, 20);
        add(datum2_mezo);
        datum2_mezo.setColumns(10);
        
        JLabel lblNewLabel_21 = new JLabel("Felelős");
        lblNewLabel_21.setBounds(366, 503, 46, 14);
        add(lblNewLabel_21);
        
        JLabel lblNewLabel_22 = new JLabel("Felelős");
        lblNewLabel_22.setBounds(366, 453, 46, 14);
        add(lblNewLabel_22);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.setBounds(444, 500, 143, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        felelos1_mezo = new JTextField();
        felelos1_mezo.setBounds(444, 450, 143, 20);
        add(felelos1_mezo);
        felelos1_mezo.setColumns(10);
        
        veas_mezo = new JTextField();
        veas_mezo.addKeyListener(new Enter2());
        veas_mezo.setBounds(829, 46, 266, 20);
        add(veas_mezo);
        veas_mezo.setColumns(10);
        
        vevo_mezo = new JTextField();
        vevo_mezo.setBounds(829, 77, 266, 20);
        vevo_mezo.addKeyListener(new Enter3());
        add(vevo_mezo);
        vevo_mezo.setColumns(10);
        
        JLabel lblNewLabel_23 = new JLabel("VEAS szériaszám");
        lblNewLabel_23.setBounds(677, 49, 132, 14);
        add(lblNewLabel_23);
        
        JLabel lblNewLabel_24 = new JLabel("Vevői szériaszám");
        lblNewLabel_24.setBounds(677, 80, 132, 14);
        add(lblNewLabel_24);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(677, 137, 479, 211);
        add(gorgeto);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"VEAS Szériaszám", "Vevői Szériaszám","Hibaleírás"});
        
        table.setModel(modell);
        
        JLabel lblNewLabel_25 = new JLabel("Belőtt szériaszám mennyiség");
        lblNewLabel_25.setBounds(677, 359, 191, 14);
        add(lblNewLabel_25);
        
        db_cimke = new JLabel("0");
        db_cimke.setBounds(878, 359, 35, 14);
        add(db_cimke);
        
        JLabel lblNewLabel_26 = new JLabel("db");
        lblNewLabel_26.setBounds(923, 359, 46, 14);
        add(lblNewLabel_26);
        
        hibaleiras_mezo = new JTextField();
        hibaleiras_mezo.addKeyListener(new Enter4());
        hibaleiras_mezo.setBounds(829, 108, 348, 20);
        add(hibaleiras_mezo);
        hibaleiras_mezo.setColumns(10);
        
        JLabel lblNewLabel_27 = new JLabel("Hiba leírása");
        lblNewLabel_27.setBounds(677, 112, 112, 14);
        add(lblNewLabel_27);
        new ArrayList<String>();
        
        new Workbook();

    }
    
    class Mentes implements ActionListener                                                                                        //bevitt retour adatokat menti el
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                int szam = Integer.valueOf(beerkezett_mezo.getText()) - Integer.valueOf(elteres_mezo.getText());
                int szam2 =  Integer.valueOf(db_cimke.getText());
                if(szam != szam2)
                {
                    JOptionPane.showMessageDialog(null, "Nem egyezik a belőtt szériaszám a beérkezett menyniséggel!", "Hiba üzenet", 2);
                }
                SQA_SQL iras = new SQA_SQL();
                String sql = "select * from qualitydb.Retour where id = '"+ id_mezo.getText() +"'";
                String[] koztes = String.valueOf(cikk_box.getSelectedItem()).split(",");
                if(iras.tombvissza_sajat(sql).length > 0)
                {
                    sql = "update qualitydb.Retour set  Datum = '"+ datum_mezo.getText() +"', Vevo = '"+ String.valueOf(projekt_box.getSelectedItem()) +"', Tipus = '"+ koztes[0] +"',"
                            + " Vagy = '"+ String.valueOf(javagy_box.getSelectedItem()) +"', "
                            + " Beerkezett = '"+ Integer.parseInt(beerkezett_mezo.getText()) +"', Elteres = '"+ Integer.parseInt(elteres_mezo.getText()) +"', RMA = '"+ rma_mezo.getText() +"',"
                            + " Megjegyzes = '"+ megjegyzes_mezo.getText() +"', "
                            + " Vevoi_rma = '"+ vevoirma_mezo.getText() +"' where Id= '" + id_mezo.getText() + "'";
                    iras.mindenes(sql);
                }
                else
                {
                    sql = "INSERT INTO qualitydb.Retour (Datum, Vevo, Tipus, Vagy, Beerkezett, Elteres, RMA, Megjegyzes,Vevoi_rma)" + 
                            "VALUES ('" + datum_mezo.getText() +"', '"+ String.valueOf(projekt_box.getSelectedItem()) +"', '"+koztes[0]+"', '" + String.valueOf(javagy_box.getSelectedItem()) + "',"
                                    + " '" + Integer.parseInt(beerkezett_mezo.getText()) +"', '" + Integer.parseInt(elteres_mezo.getText()) +"', '" + rma_mezo.getText() +"', '" + megjegyzes_mezo.getText() +"'"
                            + ", '" + vevoirma_mezo.getText() +"')";
                    iras.mindenes(sql);
                }
                
                SQA_SQL modositas = new SQA_SQL();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date date = new Date();
                modositas.mindenes("update qualitydb.Retour set Modositas_Datuma = '"+ formatter.format(date) +"' where id = '"+ id_mezo.getText() +"'");
                
                if(table.getRowCount() > 0)
                {
                    for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                    {
                        if(table.getValueAt(szamlalo, 0).toString().equals(""))
                        {
                            sql = "select Retour_ID from qualitydb.Retour_szeriaszamok where Vevoi_ID = '"+ table.getValueAt(szamlalo, 1).toString() +"'";
                        }
                        else if(table.getValueAt(szamlalo, 1).toString().equals(""))
                        {
                            sql = "select Retour_ID from qualitydb.Retour_szeriaszamok where VEAS_ID = '"+ table.getValueAt(szamlalo, 0).toString() +"'";
                        }
                        else
                        {
                            sql = "select Retour_ID from qualitydb.Retour_szeriaszamok where VEAS_ID = '"+ table.getValueAt(szamlalo, 0).toString() +"' or Vevoi_ID = '"+ table.getValueAt(szamlalo, 1).toString() +"'";
                        }
                        if(modositas.tombvissza_sajat(sql).length > 0)
                        {
                            System.out.println("Van iylen szériaszám");
                            if(modositas.tombvissza_sajat(sql)[0].equals(id_mezo.getText()))
                            {
                                if(table.getValueAt(szamlalo, 0).toString().equals(""))
                                {
                                    sql = "update qualitydb.Retour_szeriaszamok set Hiba_leirasa = '"+ table.getValueAt(szamlalo, 2).toString() +"' where Vevoi_ID = '"+ table.getValueAt(szamlalo, 1).toString() +"'";
                                    modositas.mindenes(sql);
                                    System.out.println("Modosítva vevői szerint");
                                }
                                else if(table.getValueAt(szamlalo, 1).toString().equals(""))
                                {
                                    sql = "update qualitydb.Retour_szeriaszamok set Hiba_leirasa = '"+ table.getValueAt(szamlalo, 2).toString() +"' where VEAS_ID = '"+ table.getValueAt(szamlalo, 0).toString() +"'";
                                    modositas.mindenes(sql);
                                    System.out.println("Modosítva veas szerint");
                                }
                                else
                                {
                                    sql = "update qualitydb.Retour_szeriaszamok set Hiba_leirasa = '"+ table.getValueAt(szamlalo, 2).toString() +"' where VEAS_ID = '"+ table.getValueAt(szamlalo, 0).toString() +"'";
                                    modositas.mindenes(sql);
                                    System.out.println("Modosítva veas2 szerint");
                                }
                            }
                            else
                            {
                                SimpleDateFormat rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                                                          //
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                sql = "Insert Into qualitydb.Retour_szeriaszamok (VEAS_ID,VEVOI_ID,REtour_ID,RMA,VEvoi_RMA, Hiba_leirasa, Rogzites_ideje) Values('"+ table.getValueAt(szamlalo, 0).toString() +"', '"+ table.getValueAt(szamlalo, 1).toString() + "',"
                                        + "'"+ id_mezo.getText() +"', '"+ rma_mezo.getText() + "','"+ vevoirma_mezo.getText() +"','"+ table.getValueAt(szamlalo, 2).toString() +"','"+ rogzites.format(timestamp) +"')";
                                modositas.mindenes(sql);
                                System.out.println("Nincs ilyen szériaszám");
                            }
                        }
                        else
                        {
                            SimpleDateFormat rogzites = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                                                          //
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            sql = "Insert Into qualitydb.Retour_szeriaszamok (VEAS_ID,VEVOI_ID,REtour_ID,RMA,VEvoi_RMA, Hiba_leirasa, Rogzites_ideje) Values('"+ table.getValueAt(szamlalo, 0).toString() +"', '"+ table.getValueAt(szamlalo, 1).toString() + "',"
                                    + "'"+ id_mezo.getText() +"', '"+ rma_mezo.getText() + "','"+ vevoirma_mezo.getText() +"','"+ table.getValueAt(szamlalo, 2).toString() +"','"+ rogzites.format(timestamp) +"')";
                            modositas.mindenes(sql);
                            System.out.println("Nincs ilyen szériaszám");
                        }
                        System.out.println("Fut a for");
                    }
                    int rowCount = modell.getRowCount();
                    
                    for (int i = rowCount - 1; i > -1; i--) 
                    {
                      modell.removeRow(i);
                    }
                    table.setModel(modell);
                    sql = "update qualitydb.Retour set  Beolvaott_szeria = '" + db_cimke.getText()+"' where id = '" + id_mezo.getText() +"',";
                }
                
                Urlap_torlo torles = new Urlap_torlo();
                torles.urlaptorles_retour(datum_mezo, beerkezett_mezo, elteres_mezo, rma_mezo, megjegyzes_mezo, datum3_mezo, datum1_mezo, felelos3_mezo, datum4_mezo, felelos4_mezo, datum5_mezo,
                        felelos5_mezo, raktarra_mezo, raktarradb_mezo, selejt_mezo, vevoirma_mezo);
                felelos1_mezo.setText("");
                felelos2_mezo.setText("");
                datum2_mezo.setText("");
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
    }
    
    class Folyamatok implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQA_SQL modositas = new SQA_SQL();
                String sql = "update qualitydb.Retour set  Analizis_datum = '" + datum1_mezo.getText()+"', Felelos1 = '" + felelos1_mezo.getText()+"',"
                        + "Gepes_datum = '" + datum2_mezo.getText()+"',Felelos2 = '" + felelos2_mezo.getText()+"',"
                        + "Kezi_datum = '" + datum3_mezo.getText()+"',Felelos3 = '" + felelos3_mezo.getText()+"',"
                        + "Teszt_datum = '" + datum4_mezo.getText()+"',Felelos4 = '" + felelos4_mezo.getText()+"', "
                        + "Vegell_datum = '" + datum5_mezo.getText()+"',Felelos5 = '" + felelos5_mezo.getText()+"', "
                        + "Raktar_datum = '" + raktarra_mezo.getText()+"', raktar_db = '" + raktarradb_mezo.getText()+"', Selejt = '" + selejt_mezo.getText()+"' "
                        + " where ID = '" + id_mezo.getText() + "'"; 
                modositas.mindenes(sql);
               
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date date = new Date();
                modositas.mindenes("update qualitydb.Retour set Modositas_Datuma = '"+ formatter.format(date) +"' where id = '"+ id_mezo.getText() +"'");
                Urlap_torlo torles = new Urlap_torlo();
                torles.urlaptorles_retour(datum_mezo, beerkezett_mezo, elteres_mezo, rma_mezo, megjegyzes_mezo, datum3_mezo, datum1_mezo, felelos3_mezo,
                        datum4_mezo, felelos4_mezo, datum5_mezo, felelos5_mezo, raktarra_mezo, raktarradb_mezo, selejt_mezo, vevoirma_mezo);
                felelos1_mezo.setText("");
                felelos2_mezo.setText("");
                datum2_mezo.setText("");
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
    
    class ID implements ActionListener                                                                                        //ID alapján visszatölti az adatokat
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                retour_vissza(id_mezo.getText());
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
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    retour_vissza(id_mezo.getText());
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
    /*
    class Kivalaszt implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                
                    for(int szamlalo = 0; szamlalo < combobox_tomb.getCombobox2(ComboBox.vevoi_cikk).length; szamlalo++)
                    {
                        if(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo].toLowerCase().contains(keresett.toLowerCase()))
                        {
                            kivalasztott.add(combobox_tomb.getCombobox2(ComboBox.vevoi_cikk)[szamlalo]); 
                        }
                    }
                    
                    String[] ujmodell = new String[kivalasztott.size()];
                    for(int szamlalo = 0; szamlalo < kivalasztott.size(); szamlalo++)
                    {
                        ujmodell[szamlalo] = kivalasztott.get(szamlalo);
                    }
                    model = new DefaultComboBoxModel<>(ujmodell);
                
                cikk_box.setModel(model);
                kivalasztott.clear();
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
    }*/
    
    class Enter2 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    vevo_mezo.requestFocusInWindow();
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
    
    class Enter3 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    hibaleiras_mezo.requestFocusInWindow();
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
    
    class Enter4 implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, ID alapján vissztölti az adatokat
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    String veas = "";
                    String vevo = "";
                    if(veas_mezo.getText().startsWith(" "))
                    {
                        veas = veas_mezo.getText().substring(1, veas_mezo.getText().length());
                    }
                    if(veas_mezo.getText().endsWith(" "))
                    {
                        veas = veas_mezo.getText().substring(0, veas_mezo.getText().length() -1);
                    }
                    else
                    {
                        veas = veas_mezo.getText();
                    }
                    
                    if(vevo_mezo.getText().startsWith(" "))
                    {
                        vevo = vevo_mezo.getText().substring(1, vevo_mezo.getText().length());
                    }
                    if(vevo_mezo.getText().endsWith(" "))
                    {
                        vevo = vevo_mezo.getText().substring(0, vevo_mezo.getText().length() -1);
                    }
                    else
                    {
                        vevo = vevo_mezo.getText();
                    }
                    modell.addRow(new Object[]{veas, vevo, hibaleiras_mezo.getText()});
                    table.setModel(modell);
                    int db = Integer.valueOf(db_cimke.getText());
                    db++;
                    db_cimke.setText(String.valueOf(db));
                    veas_mezo.setText("");
                    vevo_mezo.setText("");
                    hibaleiras_mezo.setText("");
                    veas_mezo.requestFocusInWindow();
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
    
    class Kivalaszt2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                DefaultComboBoxModel<String> model;
                String keresett = String.valueOf(projekt_box.getSelectedItem());
                String vevo = "";

                workbook.loadFromFile(excelhelye2);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                for(int szamlalo = 1; szamlalo < sheet.getLastDataRow()+1; szamlalo++)
                {
                    if(keresett.toUpperCase().equals(sheet.getRange().get("B"+szamlalo).getText().toUpperCase()))
                    {
                        vevo =  sheet.getRange().get("A"+szamlalo).getText();
                        System.out.println(vevo);
                    }
                }
                String sql = "select part_no || '  ' || REVISION_TEXT || '  ' || ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok\r\n"
                        + "from ifsapp.PART_REVISION\r\n"
                        + "where 3 = 3\r\n"
                        + "and ifsapp.inventory_part_api.Get_Part_Product_Code(contract,part_no) = '1'\r\n"
                        + "and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = '"+ vevo +"'\r\n"
                        + "group by part_no, REVISION_TEXT, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO)\r\n"
                        + "ORDER by part_no";
                
                String[] cikkek = cikkszamok.tombvissza(sql);                
                String[] ujmodell = new String[cikkek.length];
                for(int szamlalo = 0; szamlalo <cikkek.length; szamlalo++)
                {
                    ujmodell[szamlalo] = cikkek[szamlalo];
                }               
                model = new DefaultComboBoxModel<>(ujmodell);
                   
                cikk_box.setModel(model); 
                Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
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
    
    public void retour_vissza(String id)
    {
        Connection conn = null;
        Statement stmt = null;
        Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
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
        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String sajat = "SELECT * FROM  qualitydb.Retour where ID = '"+ id +"'";         
        stmt.execute(sajat);
        ResultSet resultSet = stmt.getResultSet();
        
        if(resultSet.next())
        {
            projekt_box.removeActionListener(valaszto);
            String[] datum = resultSet.getString(2).split(" ");
            datum_mezo.setText(datum[0].replace("-", "."));
            projekt_box.setSelectedItem(resultSet.getString(3));
            cikk_box.addItem(resultSet.getString(4));
            cikk_box.setSelectedItem(resultSet.getString(4));            
            javagy_box.setSelectedItem(resultSet.getString(5));
            beerkezett_mezo.setText(resultSet.getString(6));
            elteres_mezo.setText(resultSet.getString(7));
            rma_mezo.setText(resultSet.getString(8));
            megjegyzes_mezo.setText(resultSet.getString(9));
            datum1_mezo.setText(resultSet.getString(10));
            felelos1_mezo.setText(resultSet.getString(11));
            datum2_mezo.setText(resultSet.getString(12));
            felelos2_mezo.setText(resultSet.getString(13));
            datum3_mezo.setText(resultSet.getString(14));
            felelos3_mezo.setText(resultSet.getString(15));
            datum4_mezo.setText(resultSet.getString(16));
            felelos4_mezo.setText(resultSet.getString(17));
            datum5_mezo.setText(resultSet.getString(18));
            felelos5_mezo.setText(resultSet.getString(19));
            if(resultSet.getString(20) != null)
            {
                String[] raki = resultSet.getString(20).split(" ");
                raktarra_mezo.setText(raki[0].replace("-", "."));
            }
            else
            {
                raktarra_mezo.setText("");
            }
            raktarradb_mezo.setText(resultSet.getString(21));
            selejt_mezo.setText(resultSet.getString(22));
            vevoirma_mezo.setText(resultSet.getString(23));
            projekt_box.addActionListener(valaszto);
            
        }
        
        sajat = "SELECT VEas_ID, VEvoi_ID, hiba_leirasa FROM  qualitydb.Retour_szeriaszamok where REtour_ID = '"+ id +"'";         
        stmt.execute(sajat);
        resultSet = stmt.getResultSet();
        int rowCount = modell.getRowCount();
        
        for (int i = rowCount - 1; i > -1; i--) 
        {
          modell.removeRow(i);
        }
        while(resultSet.next())
        {
            modell.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2),  resultSet.getString(3)});
            
        }
        db_cimke.setText(String.valueOf(table.getRowCount()));
        table.setModel(modell);
        
        resultSet.close();
        stmt.close();
        conn.close();
        Foablak.frame.setCursor(null);                                                //egér mutató alaphelyzetbe állítása
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", this.getClass().getSimpleName()+" "+ hibauzenet);
        } 
        finally 
        {
           try 
           {
              if (stmt != null)
                 conn.close();
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
           }  
        }
    }
}
