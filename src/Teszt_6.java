import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Teszt_6 extends JPanel 
{
    
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private String kepek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\képek\\";
    private JButton elozo_gomb;
    private Dimension meretek = new Dimension(1200, 870);
    private BufferedImage kep1;
    private BufferedImage kep2;
    private BufferedImage kep3;
    private BufferedImage kep4;
    private JTextField valasz1;
    private JTextField valasz2;
    private JButton eredeti3_gomb;
    private JButton eredeti4_gomb;
    private JLabel lblNewLabel_2;
    private JTextField valasz3;
    private JLabel lblNewLabel_3;
    private JTextField valasz4;
    private File kepfajl1 = new File(kepek + Teszt_kezdes.tesztszam +"\\7.jpg");
    private File kepfajl2 = new File(kepek + Teszt_kezdes.tesztszam +"\\8.jpg");
    private File kepfajl3 = new File(kepek + Teszt_kezdes.tesztszam +"\\9.jpg");
    private File kepfajl4 = new File(kepek + Teszt_kezdes.tesztszam +"\\10.jpg");
    
    
    /**
     * Create the panel.
     */
    public Teszt_6() 
    {
        setLayout(null);
        this.setPreferredSize(meretek);
        JButton kovi_gomb = new JButton("Következő");
        kovi_gomb.addActionListener(new Kovetkezo());
        kovi_gomb.setBounds(947, 692, 107, 23);
        add(kovi_gomb);
        
        elozo_gomb = new JButton("Előző");
        elozo_gomb.setBounds(97, 692, 89, 23);
        elozo_gomb.addActionListener(new Elozo());
        add(elozo_gomb);
        
        JButton eredeti1_gomb = new JButton("Eredeti kép");
        eredeti1_gomb.addActionListener(new Eredeti1());
        eredeti1_gomb.setBounds(379, 54, 113, 23);
        add(eredeti1_gomb);
        
        JButton eredeti2_gomb = new JButton("Eredeti kép");
        eredeti2_gomb.addActionListener(new Eredeti2());
        eredeti2_gomb.setBounds(379, 205, 113, 23);
        add(eredeti2_gomb);
        
        JLabel lblNewLabel = new JLabel("Hibakód");
        lblNewLabel.setBounds(622, 58, 60, 14);
        add(lblNewLabel);
        
        valasz1 = new JTextField();
        valasz1.setBounds(692, 55, 86, 20);
        add(valasz1);
        valasz1.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Hibakód");
        lblNewLabel_1.setBounds(622, 209, 60, 14);
        add(lblNewLabel_1);
        
        valasz2 = new JTextField();
        valasz2.setBounds(692, 206, 86, 20);
        add(valasz2);
        valasz2.setColumns(10);
        
        eredeti3_gomb = new JButton("Eredeti kép");
        eredeti3_gomb.setBounds(379, 353, 113, 23);
        eredeti3_gomb.addActionListener(new Eredeti3());
        add(eredeti3_gomb);
        
        eredeti4_gomb = new JButton("Eredeti kép");
        eredeti4_gomb.setBounds(379, 535, 113, 23);
        eredeti4_gomb.addActionListener(new Eredeti4());
        add(eredeti4_gomb);
        
        lblNewLabel_2 = new JLabel("Hibakód");
        lblNewLabel_2.setBounds(622, 357, 46, 14);
        add(lblNewLabel_2);
        
        valasz3 = new JTextField();
        valasz3.setBounds(692, 354, 86, 20);
        add(valasz3);
        valasz3.setColumns(10);
        
        lblNewLabel_3 = new JLabel("Hibakód");
        lblNewLabel_3.setBounds(622, 539, 46, 14);
        add(lblNewLabel_3);
        
        valasz4 = new JTextField();
        valasz4.setBounds(692, 536, 86, 20);
        add(valasz4);
        valasz4.setColumns(10);
        
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
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz30 = '" + valasz1.getText() +"', Valasz31 = '" + valasz2.getText() +"', Valasz32 = '" + valasz3.getText() +
                        "', Valasz33 = '" + valasz4.getText() + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                dbiras.iro_kep(7, kepfajl1.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(8, kepfajl2.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(9, kepfajl3.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(10, kepfajl4.getAbsolutePath(), Teszt_kezdes.id);
                Teszt_7 hetedik = new Teszt_7();
                Foablak.frame.setContentPane(hetedik);
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
                SQL_teszt dbiras = new SQL_teszt();
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz30 = '" + valasz1.getText() +"', Valasz31 = '" + valasz2.getText() +"', Valasz32 = '" + valasz3.getText() +
                        "', Valasz33 = '" + valasz4.getText() + "' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                dbiras.iro_kep(7, kepfajl1.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(8, kepfajl2.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(9, kepfajl3.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(10, kepfajl4.getAbsolutePath(), Teszt_kezdes.id);
                Teszt_5 otodik = new Teszt_5();
                JScrollPane ablak = new JScrollPane(otodik);
                ablak.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                ablak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                Foablak.frame.setContentPane(ablak);
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
    
    class Eredeti1 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\7.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Eredeti2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\8.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Eredeti3 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\9.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Eredeti4 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\10.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
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
        try
        {
            Workbook excel = new Workbook();
            excel.loadFromFile(kerdesek);                                                                                          //infot tartalamzó excel betöltése
            Worksheet sheet = excel.getWorksheets().get(Teszt_kezdes.tesztszam);                                                                         //excel tábla létrehozása
            sheet.exportDataTable();
            
            kep1 = ImageIO.read(new File(kepek + Teszt_kezdes.tesztszam +"\\7.jpg"));
            kep2 = ImageIO.read(new File(kepek + Teszt_kezdes.tesztszam +"\\8.jpg"));
            kep3 = ImageIO.read(new File(kepek + Teszt_kezdes.tesztszam +"\\9.jpg"));  
            kep4 = ImageIO.read(new File(kepek + Teszt_kezdes.tesztszam +"\\10.jpg"));  
        }
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }
    }
    
    private void visszair()
    {
        try
        {
            SQL_teszt eddigi = new SQL_teszt();
            eddigi.beirva(Teszt_kezdes.id);
            valasz1.setText(SQL_teszt.beirt.get(40));
            valasz2.setText(SQL_teszt.beirt.get(42));
            valasz3.setText(SQL_teszt.beirt.get(44));
            valasz4.setText(SQL_teszt.beirt.get(46));
                
        }
        catch (Exception e1) 
        {              
            e1.printStackTrace();
            String hibauzenet = e1.toString();
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(kep1, 60, 20, (kep1.getWidth())/3, (kep1.getHeight())/3, this); // see javadoc for more info on the parameters
        g.drawImage(kep2, 60, 20 +(kep1.getHeight())/3 +20, (kep2.getWidth())/3, (kep2.getHeight())/3, this);
        g.drawImage(kep3, 60, 20 +(kep1.getHeight())/3 +20 +(kep2.getHeight())/3 , (kep3.getWidth())/3, (kep3.getHeight())/3, this);
        g.drawImage(kep4, 60, 20 +(kep1.getHeight())/3 +20 + (kep2.getHeight())/3 + (kep3.getHeight())/3, (kep4.getWidth())/3, (kep4.getHeight())/3, this);
    }

}
