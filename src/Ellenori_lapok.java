import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;

import com.spire.xls.CellRange;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class Ellenori_lapok extends JPanel {
    private JTextField datum_mezo;
    private JCheckBox de1;
    private JCheckBox du1;
    private JCheckBox ej1;
    private JCheckBox de2;
    private JCheckBox du2;
    private JCheckBox ej2;
    private JCheckBox de3;
    private JCheckBox du3;
    private JCheckBox ej3;
    private JCheckBox de4;
    private JCheckBox du4;
    private JCheckBox ej4;
    private JCheckBox de5;
    private JCheckBox du5;
    private JCheckBox ej5;
    private JCheckBox de6;
    private JCheckBox du6;
    private JCheckBox ej6;
    private JCheckBox de7;
    private JCheckBox du7;
    private JCheckBox ej7;
    private JCheckBox de8;
    private JCheckBox du8;
    private JCheckBox ej8;
    private JCheckBox de9;
    private JCheckBox du9;
    private JCheckBox ej9;
    private JCheckBox de10;
    private JCheckBox du10;
    private JCheckBox ej10;
    private JCheckBox de11;
    private JCheckBox du11;
    private JCheckBox ej11;
    private String excelhelye = "";   //"\\\\10.1.0.11\\minosegbiztositas\\Gyártási_minőség_követése\\Gyártási minőségbiztosítás\\Minőségellenőrzési csoport\\Beosztás MEO 250-es épület 2024.xlsx";
    private Workbook excel;
    private Worksheet sheet;
    private String fajlhelye = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\jelenletifajlhelye.txt";

    /**
     * Create the panel.
     */
    public Ellenori_lapok() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Begyűjtött ellenőri lapok rögzítése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(481, 27, 285, 14);
        add(lblNewLabel);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(108, 97, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Dátum");
        lblNewLabel_1.setBounds(52, 100, 46, 14);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("AVM kézi terület folyamatellenőrzés");
        lblNewLabel_2.setBounds(52, 161, 230, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("AVM végszerelés folyamatellenőrzés");
        lblNewLabel_3.setBounds(52, 211, 230, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Telecom Design folyamatellenőrzés");
        lblNewLabel_4.setBounds(52, 257, 187, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Osram folyamat-, gyártásközi- és végellenőrzés");
        lblNewLabel_5.setBounds(52, 295, 268, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("Socomec, Memminger, Pactrol, Brooks, Leroy, Instagrid, OMT, Mars Drinks - Lavazza, Formlabs folyamatellenőrzés");
        lblNewLabel_6.setBounds(52, 336, 570, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Loxone folyamat-, gyártásközi- és végellenőrzés");
        lblNewLabel_7.setBounds(52, 385, 256, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Loxone Wallbox folyamatellenőrzés");
        lblNewLabel_8.setBounds(52, 428, 230, 14);
        add(lblNewLabel_8);
        
        JLabel lblNewLabel_9 = new JLabel("Techem folyamatellenőrzés");
        lblNewLabel_9.setBounds(52, 465, 187, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("ProGlove folyamat-, gyk- és végellenőrzés");
        lblNewLabel_10.setBounds(52, 506, 230, 14);
        add(lblNewLabel_10);
        
        JLabel lblNewLabel_11 = new JLabel("HC folyamatellenőrzés");
        lblNewLabel_11.setBounds(52, 542, 151, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Chamber folyamat-, gyk- és végellenőrzés");
        lblNewLabel_12.setBounds(52, 581, 241, 14);
        add(lblNewLabel_12);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(510, 651, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_13 = new JLabel("Délelött");
        lblNewLabel_13.setBounds(411, 122, 58, 14);
        add(lblNewLabel_13);
        
        JLabel lblNewLabel_14 = new JLabel("Délután");
        lblNewLabel_14.setBounds(530, 122, 46, 14);
        add(lblNewLabel_14);
        
        JLabel lblNewLabel_15 = new JLabel("Éjszaka");
        lblNewLabel_15.setBounds(656, 122, 46, 14);
        add(lblNewLabel_15);
        
        de1 = new JCheckBox("");
        de1.setBounds(411, 157, 29, 23);
        add(de1);
        
        du1 = new JCheckBox("");
        du1.setBounds(530, 157, 29, 23);
        add(du1);
        
        ej1 = new JCheckBox("");
        ej1.setBounds(656, 157, 29, 23);
        add(ej1);
        
        de2 = new JCheckBox("");
        de2.setBounds(411, 207, 29, 23);
        add(de2);
        
        du2 = new JCheckBox("");
        du2.setBounds(530, 207, 29, 23);
        add(du2);
        
        ej2 = new JCheckBox("");
        ej2.setBounds(656, 207, 29, 23);
        add(ej2);
        
        de3 = new JCheckBox("");
        de3.setBounds(411, 253, 29, 23);
        add(de3);
        
        du3 = new JCheckBox("");
        du3.setBounds(530, 253, 29, 23);
        add(du3);
        
        ej3 = new JCheckBox("");
        ej3.setBounds(656, 253, 29, 23);
        add(ej3);
        
        de4 = new JCheckBox("");
        de4.setBounds(411, 291, 29, 23);
        add(de4);
        
        du4 = new JCheckBox("");
        du4.setBounds(530, 291, 29, 23);
        add(du4);
        
        ej4 = new JCheckBox("");
        ej4.setBounds(656, 291, 29, 23);
        add(ej4);
        
        de5 = new JCheckBox("Délelött");
        de5.setBounds(656, 332, 86, 23);
        add(de5);
        
        du5 = new JCheckBox("Délután");
        du5.setBounds(795, 332, 78, 23);
        add(du5);
        
        ej5 = new JCheckBox("Éjszaka");
        ej5.setBounds(953, 332, 78, 23);
        add(ej5);
        
        de6 = new JCheckBox("");
        de6.setBounds(411, 381, 29, 23);
        add(de6);
        
        du6 = new JCheckBox("");
        du6.setBounds(530, 381, 29, 23);
        add(du6);
        
        ej6 = new JCheckBox("");
        ej6.setBounds(656, 381, 29, 23);
        add(ej6);
        
        de7 = new JCheckBox("");
        de7.setBounds(411, 424, 29, 23);
        add(de7);
        
        du7 = new JCheckBox("");
        du7.setBounds(530, 424, 29, 23);
        add(du7);
        
        ej7 = new JCheckBox("");
        ej7.setBounds(656, 424, 29, 23);
        add(ej7);
        
        de8 = new JCheckBox("");
        de8.setBounds(411, 461, 29, 23);
        add(de8);
        
        du8 = new JCheckBox("");
        du8.setBounds(530, 461, 29, 23);
        add(du8);
        
        ej8 = new JCheckBox("");
        ej8.setBounds(656, 461, 29, 23);
        add(ej8);
        
        de9 = new JCheckBox("");
        de9.setBounds(411, 502, 29, 23);
        add(de9);
        
        du9 = new JCheckBox("");
        du9.setBounds(530, 502, 29, 23);
        add(du9);
        
        ej9 = new JCheckBox("");
        ej9.setBounds(656, 502, 29, 23);
        add(ej9);
        
        de10 = new JCheckBox("");
        de10.setBounds(411, 538, 29, 23);
        add(de10);
        
        du10 = new JCheckBox("");
        du10.setBounds(530, 538, 29, 23);
        add(du10);
        
        ej10 = new JCheckBox("");
        ej10.setBounds(656, 538, 29, 23);
        add(ej10);
        
        de11 = new JCheckBox("");
        de11.setBounds(411, 577, 29, 23);
        add(de11);
        
        du11 = new JCheckBox("");
        du11.setBounds(530, 577, 29, 23);
        add(du11);
        
        ej11 = new JCheckBox("");
        ej11.setBounds(656, 577, 29, 23);
        add(ej11);

        setBackground(Foablak.hatter_szine);
        
        JButton csatol_gomb = new JButton("Csatol");
        csatol_gomb.addActionListener(new Fajl_csatol());
        csatol_gomb.setBounds(1085, 55, 89, 23);
        add(csatol_gomb);
        
        JLabel lblNewLabel_16 = new JLabel("Jelenléti csatolása");
        lblNewLabel_16.setBounds(968, 59, 107, 14);
        add(lblNewLabel_16);
        
        try 
        {
            File letezik = new File(fajlhelye);
            if(letezik.exists())
            {
                //FileReader fr = new FileReader(System.getProperty("user.home") + "\\sqa_szures.txt");
                FileInputStream fis = new FileInputStream(fajlhelye);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                try (BufferedReader br = new BufferedReader(isr)) 
                {
                    String sor = br.readLine();
                    excelhelye = sor;
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }       
                catch (Exception e1) 
                {
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                    e1.printStackTrace();
                }
            }
        }       
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }

    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String avmkezi = ""; String avmvegszereles = "";String telecom  = "";String osram  = "";String socomec  = "";String loxonefolyamat  = "";String loxonewallbox  = "";
                String techem  = "";String proglove  = "";String hc  = "";String chamber  = "";
                
                if(de1.isSelected())
                {
                    avmkezi = "igen;";
                }
                else
                {
                    avmkezi = "nem;";
                }
                if(du1.isSelected())
                {
                    avmkezi += "igen;";
                }
                else
                {
                    avmkezi += "nem;";
                }
                if(ej1.isSelected())
                {
                    avmkezi += "igen";
                }
                else
                {
                    avmkezi += "nem";
                }
                /////////////////////////////////
                if(de2.isSelected())
                {
                    avmvegszereles = "igen;";
                }
                else
                {
                    avmvegszereles = "nem;";
                }
                if(du2.isSelected())
                {
                    avmvegszereles += "igen;";
                }
                else
                {
                    avmvegszereles += "nem;";
                }
                if(ej2.isSelected())
                {
                    avmvegszereles += "igen";
                }
                else
                {
                    avmvegszereles += "nem";
                }
                //////////////////////////////
                if(de3.isSelected())
                {
                    telecom = "igen;";
                }
                else
                {
                    telecom = "nem;";
                }
                if(du3.isSelected())
                {
                    telecom += "igen;";
                }
                else
                {
                    telecom += "nem;";
                }
                if(ej3.isSelected())
                {
                    telecom += "igen";
                }
                else
                {
                    telecom += "nem";
                }
                ///////////////////////////
                if(de4.isSelected())
                {
                    osram = "igen;";
                }
                else
                {
                    osram = "nem;";
                }
                if(du4.isSelected())
                {
                    osram += "igen;";
                }
                else
                {
                    osram += "nem;";
                }
                if(ej4.isSelected())
                {
                    osram += "igen";
                }
                else
                {
                    osram += "nem";
                }
                ////////////////////////
                if(de5.isSelected())
                {
                    socomec = "igen;";
                }
                else
                {
                    socomec = "nem;";
                }
                if(du5.isSelected())
                {
                    socomec += "igen;";
                }
                else
                {
                    socomec += "nem;";
                }
                if(ej5.isSelected())
                {
                    socomec += "igen";
                }
                else
                {
                    socomec += "nem";
                }
                ////////////////////////
                if(de5.isSelected())
                {
                    socomec = "igen;";
                }
                else
                {
                    socomec = "nem;";
                }
                if(du5.isSelected())
                {
                    socomec += "igen;";
                }
                else
                {
                    socomec += "nem;";
                }
                if(ej5.isSelected())
                {
                    socomec += "igen";
                }
                else
                {
                    socomec += "nem";
                }
                /////////////////////////////
                if(de6.isSelected())
                {
                    loxonefolyamat = "igen;";
                }
                else
                {
                    loxonefolyamat = "nem;";
                }
                if(du6.isSelected())
                {
                    loxonefolyamat += "igen;";
                }
                else
                {
                    loxonefolyamat += "nem;";
                }
                if(ej6.isSelected())
                {
                    loxonefolyamat += "igen";
                }
                else
                {
                    loxonefolyamat += "nem";
                }
                ///////////////////////////////
                if(de7.isSelected())
                {
                    loxonewallbox = "igen;";
                }
                else
                {
                    loxonewallbox = "nem;";
                }
                if(du7.isSelected())
                {
                    loxonewallbox += "igen;";
                }
                else
                {
                    loxonewallbox += "nem;";
                }
                if(ej7.isSelected())
                {
                    loxonewallbox += "igen";
                }
                else
                {
                    loxonewallbox += "nem";
                }
                ////////////////////////////
                if(de8.isSelected())
                {
                    techem = "igen;";
                }
                else
                {
                    techem = "nem;";
                }
                if(du8.isSelected())
                {
                    techem += "igen;";
                }
                else
                {
                    techem += "nem;";
                }
                if(ej8.isSelected())
                {
                    techem += "igen";
                }
                else
                {
                    techem += "nem";
                }
                //////////////////////////
                if(de9.isSelected())
                {
                    proglove = "igen;";
                }
                else
                {
                    proglove = "nem;";
                }
                if(du9.isSelected())
                {
                    proglove += "igen;";
                }
                else
                {
                    proglove += "nem;";
                }
                if(ej9.isSelected())
                {
                    proglove += "igen";
                }
                else
                {
                    proglove += "nem";
                }
                ////////////////////////
                if(de10.isSelected())
                {
                    hc = "igen;";
                }
                else
                {
                    hc = "nem;";
                }
                if(du10.isSelected())
                {
                    hc += "igen;";
                }
                else
                {
                    hc += "nem;";
                }
                if(ej10.isSelected())
                {
                    hc += "igen";
                }
                else
                {
                    hc += "nem";
                }
                ///////////////////////
                if(de11.isSelected())
                {
                    chamber = "igen;";
                }
                else
                {
                    chamber = "nem;";
                }
                if(du11.isSelected())
                {
                    chamber += "igen;";
                }
                else
                {
                    chamber += "nem;";
                }
                if(ej11.isSelected())
                {
                    chamber += "igen";
                }
                else
                {
                    chamber += "nem";
                }
                
                if(datum_mezo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Nem adtál meg dátumot!!", "Hiba üzenet", 2);
                }
                else
                {
                    String sql = "INSERT INTO qualitydb.Ellenori_lapok (Datum,AVM_kezi,AVM_vegszereles,Telecom,Osram,Socomec_sok,Loxone_folyamat,Loxone_wallbox,Techem,Proglove,HC,Chamber) "
                            + "VALUES('"+ datum_mezo.getText() + "','"+ avmkezi + "','"+ avmvegszereles + "','"+ telecom + "','"+ osram + "','"+ socomec + "','"+ loxonefolyamat + "','"+ loxonewallbox + "','"+ techem + "',"
                                    + "'"+ proglove + "','"+ hc + "','"+ chamber + "')";
                    SQA_SQL feltoltes = new SQA_SQL();
                    feltoltes.mindenes(sql);
                    de1.setSelected(false);de2.setSelected(false);de3.setSelected(false);de4.setSelected(false);de5.setSelected(false);de6.setSelected(false);de7.setSelected(false);de8.setSelected(false);de9.setSelected(false);
                    de10.setSelected(false);de11.setSelected(false);
                    du1.setSelected(false);du2.setSelected(false);du3.setSelected(false);du4.setSelected(false);du5.setSelected(false);du6.setSelected(false);du7.setSelected(false);du8.setSelected(false);du9.setSelected(false);
                    du10.setSelected(false);du11.setSelected(false);
                    ej1.setSelected(false);ej2.setSelected(false);ej3.setSelected(false);ej4.setSelected(false);ej5.setSelected(false);ej6.setSelected(false);ej7.setSelected(false);ej8.setSelected(false);ej9.setSelected(false);
                    ej10.setSelected(false);ej11.setSelected(false);
                    ellenorzes();
                }
                Foablak.frame.setCursor(null);
                JOptionPane.showMessageDialog(null, "Mentve és ellenőrzés kész!", "Info", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    private void ellenorzes()
    {
        Connection conn = null;
        Statement stmt = null;
        excel = new Workbook();
        excel.loadFromFile(excelhelye);
        int oszlopszam = 0;
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
            Utolso_sor id = new Utolso_sor();
            String sorszam = id.utolso("qualitydb.Ellenori_lapok"); 
            String sql = "select *, yearweek(Datum, 1) from qualitydb.Ellenori_lapok where id = '"+ sorszam + "'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {
                String[] datum = rs.getString(2).split(" "); 
                String[] avmkezi = rs.getString(3).split(";"); String[] avmvegszereles =rs.getString(4).split(";");String[] telecom  = rs.getString(5).split(";");String[] osram  = rs.getString(6).split(";");
                String[] socomec  = rs.getString(7).split(";");String[] loxonefolyamat = rs.getString(8).split(";");String[] loxonewallbox  = rs.getString(9).split(";");
                String[] techem  = rs.getString(10).split(";");String[] proglove  = rs.getString(11).split(";");String[] hc  = rs.getString(12).split(";");//String[] chamber  = rs.getString(13).split(";");
                String evhet = rs.getString(14);
                String[] ev = datum_mezo.getText().split("\\.");
                String[] het = evhet.split(ev[0]);
                stmt.close();
                conn.close();
                
                for (Object sheet2: excel.getWorksheets()) {
                    String sheetName = ((Worksheet) sheet2).getName();                            
                    if(sheetName.contains(het[1]))
                    {
                        sheet = excel.getWorksheets().get(sheetName);
                    }
                }
                //sheet = excel.getWorksheets().get("WK41 új");
                for(int szamlalo = 9; szamlalo < 15; szamlalo++)
                {
                    CellRange cell2 = sheet.getCellRange(1, szamlalo);
                    SimpleDateFormat rovid = new SimpleDateFormat("yyyy.mm.dd");
                    String[] koztes = cell2.getValue().toString().split(" ");
                    if(koztes.length > 1)
                    {
                        Date excel = rovid.parse(koztes[0]+koztes[1]+koztes[2]);
                        String[] koztes2 = datum[0].split("-");
                        Date result = rovid.parse(koztes2[0]+"."+koztes2[1]+"."+koztes2[2]);
                        if(excel.compareTo(result) == 0) 
                        {
                            System.out.println("egyezik");
                            System.out.println(szamlalo);
                            oszlopszam = szamlalo;
                        }
                    }
                }                                               //"juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "rabine.anita@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu"
                Email email = new Email();
                for(int szamlalo2 = 2; szamlalo2 < 8; szamlalo2++)
                {
                    for(int szamlalo = 4; szamlalo < sheet.getLastRow(); szamlalo++)
                    {
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("AVM kézi terület folyamatellenőrzés"))
                        {
                            //System.out.println("Keresi az AVM-et");
                            if(avmkezi[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(avmkezi[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(avmkezi[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        ///////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("AVM végszerelés folyamatellenőrzés"))
                        {
                            if(avmvegszereles[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(avmvegszereles[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(avmvegszereles[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        ////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Telecom Design folyamatellenőrzés"))
                        {
                            if(telecom[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(telecom[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(telecom[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Osram folyamat-, gyártásközi- és végellenőrzés"))
                        {
                            //System.out.println("Keresi az osramot");
                            if(osram[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(osram[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(osram[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Socomec, Memminger, Pactrol, Brooks, Leroy, Instagrid, OMT, Mars Drinks - Lavazza, Formlabs folyamatellenőrzés"))
                        {
                            if(socomec[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(socomec[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(socomec[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Loxone folyamat-, gyártásközi- és végellenőrzés"))
                        {
                            if(loxonefolyamat[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(loxonefolyamat[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(loxonefolyamat[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                                
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Loxone Wallbox folyamatellenőrzés"))
                        {
                            if(loxonewallbox[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(loxonewallbox[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(loxonewallbox[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Techem folyamatellenőrzés"))
                        {
                            if(techem[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(techem[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(techem[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("ProGlove folyamat-, gyk- és végellenőrzés"))
                        {
                            if(proglove[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(proglove[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(proglove[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        /////////////////////////////////////////
                        if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("HC folyamatellenőrzés"))
                        {
                            if(hc[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(hc[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(hc[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                        }
                        ///////////////////////////////////////////////////////////////
                        /*if(sheet.getRange().get(szamlalo, szamlalo2).getText().contains("Chamber folyamat-, gyk- és végellenőrzés"))
                        {
                            if(chamber[0].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("de"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(chamber[1].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("du"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());
                                }
                            }
                            if(chamber[2].equals("nem"))
                            {
                                if(sheet.getRange().get(szamlalo, oszlopszam).getText().equals("éj"))
                                {
                                    email.ellenori_email("automataemail@veas.videoton.hu", "juhasz.iren@veas.videoton.hu", "sagi.szilvia@veas.videoton.hu", "egyedne.adrienn@veas.videoton.hu", "tatai.mihaly@veas.videoton.hu",
                                            sheet.getRange().get("A"+ szamlalo).getText(), sheet.getRange().get(szamlalo, szamlalo2).getText(),
                                            datum[0], sheet.getRange().get(szamlalo, oszlopszam).getText());                        
                                }
                            }
                        }*/
                       
                    }// for vége
                }
                        
            }
        }
        catch (Exception e) 
        {
            System.out.println("Amivel kieset oszlopszám: " +oszlopszam);
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
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
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
               JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }       
    }
    
    class Fajl_csatol implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                PrintWriter writer = new PrintWriter(fajlhelye, "UTF-8");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\10.1.0.11\\minosegbiztositas\\Gyártási_minőség_követése\\Gyártási minőségbiztosítás\\Minőségellenőrzési csoport\\"));
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                {
                    writer.print(fajl.getAbsolutePath());
                    writer.close();
                }
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null,getClass()+" "+ hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
}
