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
    private JCheckBox elfog3;
    private JCheckBox elfog4;
    private JCheckBox elfog5;
    private JCheckBox nemelfog3;
    private JCheckBox nemelfog4;
    private JCheckBox nemelfog5;
    private JLabel kerdes2;
    private JLabel kerdes4;
    private JLabel kerdes5;
    private JLabel kerdes6;
    private JLabel kerdes3;
    private JLabel lblNewLabel;
    private String valasz1;
    private String valasz2;
    private String valasz3;
    private String valasz4;
    private String valasz5;
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
        
        elfog3 = new JCheckBox("Elfogadható");
        elfog3.setBounds(66, 301, 97, 23);
        add(elfog3);
        
        nemelfog3 = new JCheckBox("Nem elfogadható");
        nemelfog3.setBounds(319, 301, 140, 23);
        add(nemelfog3);
        
        elfog4 = new JCheckBox("Elfogadható");
        elfog4.setBounds(66, 410, 147, 23);
        add(elfog4);
        
        nemelfog4 = new JCheckBox("Nem elfogadható");
        nemelfog4.setBounds(319, 410, 183, 23);
        add(nemelfog4);
        
        elfog5 = new JCheckBox("Elfogadható");
        elfog5.setBounds(66, 493, 97, 23);
        add(elfog5);
        
        nemelfog5 = new JCheckBox("Nem elfogadható");
        nemelfog5.setBounds(320, 493, 155, 23);
        add(nemelfog5);
        
        lblNewLabel = new JLabel("Viszga befejezése:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setBounds(479, 695, 140, 14);
        add(lblNewLabel);
        
        beolvas();
        visszair();
    }
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                if(elfog1.isSelected())
                {
                    valasz1 = elfog1.getText();
                }
                else
                {
                    valasz1 = nemelfog1.getText();
                }
                if(elfog2.isSelected())
                {
                    valasz2 = elfog2.getText();
                }
                else
                {
                    valasz2 = nemelfog2.getText();
                }
                if(elfog3.isSelected())
                {
                    valasz3 = elfog3.getText();
                }
                else
                {
                    valasz3 = nemelfog3.getText();
                }
                if(elfog4.isSelected())
                {
                    valasz4 = elfog4.getText();
                }
                else
                {
                    valasz4 = nemelfog4.getText();
                }
                if(elfog5.isSelected())
                {
                    valasz5 = elfog5.getText();
                }
                else
                {
                    valasz5 = nemelfog5.getText();
                }
                SQL_teszt dbiras = new SQL_teszt();
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz34 = '" + valasz1 +"', Valasz35 = '" + valasz2 +"', Valasz36 = '" + valasz3 +
                        "', Valasz37 = '" + valasz4 + "', Valasz38 = '" + valasz5 + "', Vizsga_hossza = '" + (Teszt_kezdes.measureTime(false)/1000000000) + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                System.out.println((Teszt_kezdes.measureTime(false)/1000000000));
                JOptionPane.showMessageDialog(null, "Teszt sikeresen mentve!", "Infó", 1);
                Teszt_vege  vege= new Teszt_vege();
                Foablak.frame.setContentPane(vege);
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
                if(elfog1.isSelected())
                {
                    valasz1 = elfog1.getText();
                }
                else
                {
                    valasz1 = nemelfog1.getText();
                }
                if(elfog2.isSelected())
                {
                    valasz2 = elfog2.getText();
                }
                else
                {
                    valasz2 = nemelfog2.getText();
                }
                if(elfog3.isSelected())
                {
                    valasz3 = elfog3.getText();
                }
                else
                {
                    valasz3 = nemelfog3.getText();
                }
                if(elfog4.isSelected())
                {
                    valasz4 = elfog4.getText();
                }
                else
                {
                    valasz4 = nemelfog4.getText();
                }
                if(elfog5.isSelected())
                {
                    valasz5 = elfog5.getText();
                }
                else
                {
                    valasz5 = nemelfog5.getText();
                }
                SQL_teszt dbiras = new SQL_teszt();
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz19 = '" + valasz1 +"', Valasz20 = '" + valasz2 +"', Valasz21 = '" + valasz3 +
                        "', Valasz22 = '" + valasz4 + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                Teszt_6 hatodik = new Teszt_6();
                Foablak.frame.setContentPane(hatodik);
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
    
    private void visszair()
    {
        try
        {
            SQL_teszt eddigi = new SQL_teszt();
            eddigi.beirva(Teszt_kezdes.id);
            
            if(SQL_teszt.beirt.get(47) != null)
            {
                
            }
            if(SQL_teszt.beirt.get(47).equals(elfog1.getText()));
            {
                elfog1.setSelected(true);
            }
            if(SQL_teszt.beirt.get(47).equals(nemelfog1.getText()));
            {
                nemelfog1.setSelected(true);
            }
            if(SQL_teszt.beirt.get(48).equals(elfog2.getText()));
            {
                elfog2.setSelected(true);
            }
            if(SQL_teszt.beirt.get(48).equals(nemelfog2.getText()));
            {
                nemelfog2.setSelected(true);
            }
            if(SQL_teszt.beirt.get(49).equals(elfog3.getText()));
            {
                elfog3.setSelected(true);
            }
            if(SQL_teszt.beirt.get(49).equals(nemelfog3.getText()));
            {
                nemelfog3.setSelected(true);
            }
            if(SQL_teszt.beirt.get(50).equals(elfog4.getText()));
            {
                elfog4.setSelected(true);
            }
            if(SQL_teszt.beirt.get(50).equals(nemelfog4.getText()));
            {
                nemelfog4.setSelected(true);
            }
            if(SQL_teszt.beirt.get(51).equals(elfog5.getText()));
            {
                elfog5.setSelected(true);
            }
            if(SQL_teszt.beirt.get(51).equals(nemelfog5.getText()));
            {
                nemelfog5.setSelected(true);
            }
            
        }
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }
    }
}
