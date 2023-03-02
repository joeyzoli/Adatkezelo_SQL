import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Teszt_3 extends JPanel 
{
    
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private DataTable dataTable;
    private JLabel kerdes1;
    private JTextArea valasz1;
    private JButton elozo_gomb;
    private JCheckBox csekk1;
    private JCheckBox csekk2;
    private JCheckBox csekk3;
    private JCheckBox csekk4;
    private JLabel kerdes2;
    private JLabel kerdes4;
    private JLabel kerdes5;
    private JTextField valasz3;
    private JTextField valasz4;
    private JTextField valasz5;
    private JTextField valasz6;
    private JLabel kerdes6;
    private JLabel kerdes7;
    private JTextField valasz7;
    private JLabel kerdes8;
    private JTextArea vlasz2;
    private JLabel kerdes3;
    private JLabel kerdes9;
    private JTextField valasz8;
    /**
     * Create the panel.
     */
    public Teszt_3()
    {
        setLayout(null);
        
        kerdes1 = new JLabel("New label");
        kerdes1.setBounds(29, 24, 1097, 14);
        add(kerdes1);
        
        valasz1 = new JTextArea();
        valasz1.setBounds(39, 49, 1087, 63);
        valasz1.setLineWrap(true);
        valasz1.setWrapStyleWord(true);
        add(valasz1);
        
        JButton kovi_gomb = new JButton("Következő");
        kovi_gomb.addActionListener(new Kovetkezo());
        kovi_gomb.setBounds(965, 692, 89, 23);
        add(kovi_gomb);
        
        elozo_gomb = new JButton("Előző");
        elozo_gomb.setBounds(97, 692, 89, 23);
        elozo_gomb.addActionListener(new Elozo());
        add(elozo_gomb);
        
        csekk1 = new JCheckBox("New check box");
        csekk1.setBounds(39, 152, 205, 23);
        add(csekk1);
        
        csekk2 = new JCheckBox("New check box");
        csekk2.setBounds(304, 152, 235, 23);
        add(csekk2);
        
        csekk3 = new JCheckBox("New check box");
        csekk3.setBounds(582, 152, 235, 23);
        add(csekk3);
        
        csekk4 = new JCheckBox("New check box");
        csekk4.setBounds(916, 152, 235, 23);
        add(csekk4);
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(csekk1);
        csoport.add(csekk2);
        csoport.add(csekk3);
        csoport.add(csekk4);
        
        kerdes2 = new JLabel("New label");
        kerdes2.setBounds(29, 236, 1097, 14);
        add(kerdes2);
        
        kerdes4 = new JLabel("New label");
        kerdes4.setBounds(29, 424, 583, 14);
        add(kerdes4);
        
        kerdes5 = new JLabel("New label");
        kerdes5.setBounds(29, 455, 583, 14);
        add(kerdes5);
        
        valasz3 = new JTextField();
        valasz3.setBounds(622, 421, 86, 20);
        add(valasz3);
        valasz3.setColumns(10);
        
        valasz4 = new JTextField();
        valasz4.setBounds(622, 452, 86, 20);
        add(valasz4);
        valasz4.setColumns(10);
        
        valasz5 = new JTextField();
        valasz5.setBounds(622, 483, 86, 20);
        add(valasz5);
        valasz5.setColumns(10);
        
        valasz6 = new JTextField();
        valasz6.setBounds(622, 514, 86, 20);
        add(valasz6);
        valasz6.setColumns(10);
        
        kerdes6 = new JLabel("New label");
        kerdes6.setBounds(29, 486, 583, 14);
        add(kerdes6);
        
        kerdes7 = new JLabel("New label");
        kerdes7.setBounds(29, 517, 583, 14);
        add(kerdes7);
        
        valasz7 = new JTextField();
        valasz7.setBounds(622, 545, 86, 20);
        add(valasz7);
        valasz7.setColumns(10);
        
        kerdes8 = new JLabel("New label");
        kerdes8.setBounds(29, 548, 583, 14);
        add(kerdes8);
        
        vlasz2 = new JTextArea();
        vlasz2.setBounds(39, 261, 1087, 63);
        add(vlasz2);
        
        kerdes3 = new JLabel("New label");
        kerdes3.setBounds(29, 384, 1097, 14);
        add(kerdes3);
        
        kerdes9 = new JLabel("New label");
        kerdes9.setBounds(29, 583, 583, 14);
        add(kerdes9);
        
        valasz8 = new JTextField();
        valasz8.setBounds(622, 580, 86, 20);
        add(valasz8);
        valasz8.setColumns(10);
        
        beolvas();
    }
    
    class Kovetkezo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Teszt_4 negyedik = new Teszt_4();
                Foablak.frame.setContentPane(negyedik);
                Foablak.frame.pack();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
                Teszt_2 masodik = new Teszt_2();
                Foablak.frame.setContentPane(masodik);
                Foablak.frame.pack();
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
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
        kerdes1.setText(dataTable.getRows().get(10).getString(0));
        csekk1.setText(dataTable.getRows().get(11).getString(0));
        csekk2.setText(dataTable.getRows().get(12).getString(0));
        csekk3.setText(dataTable.getRows().get(13).getString(0));
        csekk4.setText(dataTable.getRows().get(14).getString(0));
        kerdes2.setText(dataTable.getRows().get(15).getString(0));
        kerdes3.setText(dataTable.getRows().get(15).getString(0));
        kerdes4.setText(dataTable.getRows().get(16).getString(0));
        kerdes5.setText(dataTable.getRows().get(17).getString(0));
        kerdes6.setText(dataTable.getRows().get(18).getString(0));
        kerdes7.setText(dataTable.getRows().get(19).getString(0));
        kerdes8.setText(dataTable.getRows().get(20).getString(0));
        kerdes9.setText(dataTable.getRows().get(21).getString(0));
        
    }
}
