import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Teszt_2 extends JPanel 
{
    
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private DataTable dataTable;
    private JLabel kerdes1;
    private JLabel kerdes2;
    private JLabel kerdes3;
    private JLabel kerdes4;
    private JLabel kerdes5;
    private JTextArea valasz1;
    private JTextArea valasz2;
    private JTextArea valasz3;
    private JTextArea valasz4;
    private JTextArea valasz5;
    private JButton elozo_gomb;
    private Dimension meretek = new Dimension(1100, 870);
    
    /**
     * Create the panel.
     */
    public Teszt_2() 
    {
        setLayout(null);
        this.setPreferredSize(meretek);
        setBackground(Foablak.hatter_szine);
        kerdes1 = new JLabel("New label");
        kerdes1.setBounds(29, 24, 1097, 14);
        add(kerdes1);
        
        valasz1 = new JTextArea();
        valasz1.setBounds(39, 49, 1087, 63);
        add(valasz1);
        
        kerdes2 = new JLabel("New label");
        kerdes2.setBounds(29, 141, 1097, 14);
        add(kerdes2);
        
        valasz2 = new JTextArea();
        valasz2.setBounds(39, 166, 1087, 63);
        add(valasz2);
        
        kerdes3 = new JLabel("New label");
        kerdes3.setBounds(29, 254, 1097, 14);
        add(kerdes3);
        
        valasz3 = new JTextArea();
        valasz3.setBounds(39, 279, 1087, 63);
        add(valasz3);
        
        kerdes4 = new JLabel("New label");
        kerdes4.setBounds(29, 370, 1097, 14);
        add(kerdes4);
        
        valasz4 = new JTextArea();
        valasz4.setBounds(39, 395, 1087, 63);
        add(valasz4);
        
        kerdes5 = new JLabel("New label");
        kerdes5.setBounds(29, 483, 1097, 14);
        add(kerdes5);
        
        valasz5 = new JTextArea();
        valasz5.setBounds(39, 508, 1087, 63);
        add(valasz5);
        
        valasz1.setLineWrap(true);
        valasz1.setWrapStyleWord(true);
        valasz2.setLineWrap(true);
        valasz2.setWrapStyleWord(true);
        valasz3.setLineWrap(true);
        valasz3.setWrapStyleWord(true);
        valasz4.setLineWrap(true);
        valasz4.setWrapStyleWord(true);
        valasz5.setLineWrap(true);
        valasz5.setWrapStyleWord(true);
        
        JButton kovi_gomb = new JButton("Következő");
        kovi_gomb.addActionListener(new Kovetkezo());
        kovi_gomb.setBounds(944, 692, 110, 23);
        add(kovi_gomb);
        
        elozo_gomb = new JButton("Előző");
        elozo_gomb.setBounds(97, 692, 89, 23);
        elozo_gomb.addActionListener(new Elozo());
        add(elozo_gomb);
        
        beolvas();
        visszair();
    }
    
    class Kovetkezo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt dbiras = new SQL_teszt();
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz6 = '" + valasz1.getText() +"', Valasz7 = '" + valasz2.getText() +"', Valasz8 = '" + valasz3.getText() +
                        "', Valasz9 = '" + valasz4.getText() + "', Valasz10 = '" + valasz5.getText() + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                Teszt_3 harmadik = new Teszt_3();
                JScrollPane ablak = new JScrollPane(harmadik);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
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
    
    class Elozo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt dbiras = new SQL_teszt();
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz6 = '" + valasz1.getText() +"', Valasz7 = '" + valasz2.getText() +"', Valasz8 = '" + valasz3.getText() +
                        "', Valasz9 = '" + valasz4.getText() + "', Valasz10 = '" + valasz5.getText() + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                Teszt_1 elso = new Teszt_1();
                JScrollPane ablak = new JScrollPane(elso);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
                Foablak.frame.pack();
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
    
    private void beolvas()
    {
        Workbook excel = new Workbook();
        excel.loadFromFile(kerdesek);                                                                                          //infot tartalamzó excel betöltése
        Worksheet sheet = excel.getWorksheets().get(Teszt_kezdes.tesztszam);                                                                         //excel tábla létrehozása
        dataTable = sheet.exportDataTable();
        kerdes1.setText(dataTable.getRows().get(5).getString(0));
        kerdes2.setText(dataTable.getRows().get(6).getString(0));
        kerdes3.setText(dataTable.getRows().get(7).getString(0));
        kerdes4.setText(dataTable.getRows().get(8).getString(0));
        kerdes5.setText(dataTable.getRows().get(9).getString(0));
    }
    
    private void visszair()
    {
        try
        {
            SQL_teszt eddigi = new SQL_teszt();
            eddigi.beirva(Teszt_kezdes.id);
            valasz1.setText(SQL_teszt.beirt.get(9));
            valasz2.setText(SQL_teszt.beirt.get(10));
            valasz3.setText(SQL_teszt.beirt.get(11));
            valasz4.setText(SQL_teszt.beirt.get(12));
            valasz5.setText(SQL_teszt.beirt.get(13));
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
