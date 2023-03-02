import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Teszt_4 extends JPanel 
{
    
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private DataTable dataTable;
    private JButton elozo_gomb;
    private JLabel kerdes2;
    private JLabel kerdes3;
    private JTextField valasz1;
    private JTextField valasz2;
    private JTextField valasz3;
    private JTextField valasz4;
    private JLabel kerdes4;
    private JLabel kerdes5;
    private JTextField valasz5;
    private JLabel kerdes6;
    private JLabel kerdes1;
    private JTextField valasz6;
    private JLabel kerdes7;

    /**
     * Create the panel.
     */
    public Teszt_4() 
    {
        setLayout(null);
        
        JButton kovi_gomb = new JButton("Következő");
        kovi_gomb.addActionListener(new Kovetkezo());
        kovi_gomb.setBounds(965, 692, 89, 23);
        add(kovi_gomb);
        
        elozo_gomb = new JButton("Előző");
        elozo_gomb.setBounds(97, 692, 89, 23);
        elozo_gomb.addActionListener(new Elozo());
        add(elozo_gomb);
        
        kerdes2 = new JLabel("New label");
        kerdes2.setBounds(29, 60, 583, 14);
        add(kerdes2);
        
        kerdes3 = new JLabel("New label");
        kerdes3.setBounds(29, 91, 583, 14);
        add(kerdes3);
        
        valasz1 = new JTextField();
        valasz1.setBounds(622, 57, 86, 20);
        add(valasz1);
        valasz1.setColumns(10);
        
        valasz2 = new JTextField();
        valasz2.setBounds(622, 88, 86, 20);
        add(valasz2);
        valasz2.setColumns(10);
        
        valasz3 = new JTextField();
        valasz3.setBounds(622, 119, 86, 20);
        add(valasz3);
        valasz3.setColumns(10);
        
        valasz4 = new JTextField();
        valasz4.setBounds(622, 150, 86, 20);
        add(valasz4);
        valasz4.setColumns(10);
        
        kerdes4 = new JLabel("New label");
        kerdes4.setBounds(29, 122, 583, 14);
        add(kerdes4);
        
        kerdes5 = new JLabel("New label");
        kerdes5.setBounds(29, 153, 583, 14);
        add(kerdes5);
        
        valasz5 = new JTextField();
        valasz5.setBounds(622, 181, 86, 20);
        add(valasz5);
        valasz5.setColumns(10);
        
        kerdes6 = new JLabel("New label");
        kerdes6.setBounds(29, 184, 583, 14);
        add(kerdes6);
        
        kerdes1 = new JLabel("New label");
        kerdes1.setBounds(29, 22, 1097, 14);
        add(kerdes1);
        
        valasz6 = new JTextField();
        valasz6.setBounds(622, 214, 86, 20);
        add(valasz6);
        valasz6.setColumns(10);
        
        kerdes7 = new JLabel("New label");
        kerdes7.setBounds(29, 217, 583, 14);
        add(kerdes7);
        
        beolvas();
    }
    
    class Kovetkezo implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Teszt_4 harmadik = new Teszt_4();
                Foablak.frame.setContentPane(harmadik);
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
                Teszt_3 harmadik = new Teszt_3();
                Foablak.frame.setContentPane(harmadik);
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
        kerdes1.setText(dataTable.getRows().get(22).getString(0));
        kerdes2.setText(dataTable.getRows().get(23).getString(0));
        kerdes3.setText(dataTable.getRows().get(24).getString(0));
        kerdes4.setText(dataTable.getRows().get(25).getString(0));
        kerdes5.setText(dataTable.getRows().get(26).getString(0));
        kerdes6.setText(dataTable.getRows().get(27).getString(0));
        kerdes7.setText(dataTable.getRows().get(28).getString(0));
        
    }

}
