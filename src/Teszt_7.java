import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import java.awt.Color;
import java.awt.Font;

public class Teszt_7 extends JPanel 
{
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private DataTable dataTable;
    private JLabel kerdes1;
    private JButton elozo_gomb;
    private JCheckBox elfog1;
    private JCheckBox nemelfog1;
    private JCheckBox elfog2;
    private JCheckBox nemelfog2;
    private JLabel kerdes2;
    private JLabel kerdes4;
    private JLabel kerdes5;
    private JLabel kerdes6;
    private JLabel kerdes3;
    private JLabel lblNewLabel;
    /**
     * Create the panel.
     */
    public Teszt_7() 
    {
setLayout(null);
        
        kerdes1 = new JLabel("New label");
        kerdes1.setBounds(29, 24, 1097, 14);
        add(kerdes1);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(622, 692, 109, 23);
        add(mentes_gomb);
        
        elozo_gomb = new JButton("Előző");
        elozo_gomb.setBounds(97, 692, 89, 23);
        elozo_gomb.addActionListener(new Elozo());
        add(elozo_gomb);
        
        elfog1 = new JCheckBox("New check box");
        elfog1.setBounds(66, 102, 205, 23);
        add(elfog1);
        
        nemelfog1 = new JCheckBox("New check box");
        nemelfog1.setBounds(319, 102, 235, 23);
        add(nemelfog1);
        
        elfog2 = new JCheckBox("New check box");
        elfog2.setBounds(66, 190, 235, 23);
        add(elfog2);
        
        nemelfog2 = new JCheckBox("New check box");
        nemelfog2.setBounds(319, 190, 235, 23);
        add(nemelfog2);
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(elfog1);
        csoport.add(nemelfog1);
        csoport.add(elfog2);
        csoport.add(nemelfog2);
        
        kerdes2 = new JLabel("New label");
        kerdes2.setBounds(29, 69, 1097, 14);
        add(kerdes2);
        
        kerdes4 = new JLabel("New label");
        kerdes4.setBounds(29, 254, 1097, 14);
        add(kerdes4);
        
        kerdes5 = new JLabel("New label");
        kerdes5.setBounds(29, 366, 1097, 14);
        add(kerdes5);
        
        kerdes6 = new JLabel("New label");
        kerdes6.setBounds(29, 459, 583, 14);
        add(kerdes6);
        
        kerdes3 = new JLabel("New label");
        kerdes3.setBounds(29, 155, 1097, 14);
        add(kerdes3);
        
        JCheckBox elfog3 = new JCheckBox("Elfogadható");
        elfog3.setBounds(66, 301, 97, 23);
        add(elfog3);
        
        JCheckBox nemelfog3 = new JCheckBox("Nem elfogadható");
        nemelfog3.setBounds(319, 301, 140, 23);
        add(nemelfog3);
        
        JCheckBox elfog4 = new JCheckBox("Elfogadható");
        elfog4.setBounds(66, 410, 147, 23);
        add(elfog4);
        
        JCheckBox nemelfog4 = new JCheckBox("Nem elfogadható");
        nemelfog4.setBounds(319, 410, 183, 23);
        add(nemelfog4);
        
        JCheckBox elfog5 = new JCheckBox("Elfogadható");
        elfog5.setBounds(66, 493, 97, 23);
        add(elfog5);
        
        JCheckBox nemelfog5 = new JCheckBox("Nem elfogadható");
        nemelfog5.setBounds(320, 493, 155, 23);
        add(nemelfog5);
        
        lblNewLabel = new JLabel("Viszga befejezése:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBounds(479, 695, 140, 14);
        add(lblNewLabel);
        
        beolvas();
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {             
                System.out.println(Teszt_kezdes.measureTime(false)/1000000000);
                
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
        elfog1.setText("Elfogadható");
        nemelfog1.setText("Nem elfogadható");
        elfog2.setText("Elfogadható");
        nemelfog2.setText("Nem elfogadható");
        kerdes2.setText(dataTable.getRows().get(30).getString(0));
        kerdes3.setText(dataTable.getRows().get(31).getString(0));
        kerdes4.setText(dataTable.getRows().get(32).getString(0));
        kerdes5.setText(dataTable.getRows().get(33).getString(0));
        kerdes6.setText(dataTable.getRows().get(34).getString(0));
        
    }
}
