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

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

public class Teszt_4 extends JPanel 
{
    
    private String kerdesek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kérdések\\kérdések.xlsx";
    private String kepek = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\képek\\";
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
    private JLabel kerdes7;
    private Dimension meretek = new Dimension(1200, 870);
    private BufferedImage kep1;
    private BufferedImage kep2;
    private JTextField valasz6;
    private JTextField valasz7;
    private File kepfajl1 = new File(kepek + Teszt_kezdes.tesztszam +"\\1.jpg");
    private File kepfajl2 = new File(kepek + Teszt_kezdes.tesztszam +"\\2.jpg");

    /**
     * Create the panel.
     */
    public Teszt_4() 
    {
        setLayout(null);
        this.setPreferredSize(meretek);
        JButton kovi_gomb = new JButton("Következő");
        kovi_gomb.addActionListener(new Kovetkezo());
        kovi_gomb.setBounds(947, 692, 107, 23);
        add(kovi_gomb);
        setBackground(Foablak.hatter_szine);
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
        
        kerdes7 = new JLabel("New label");
        kerdes7.setBounds(29, 247, 1097, 14);
        add(kerdes7);     
        
        JButton eredeti1_gomb = new JButton("Eredeti kép");
        eredeti1_gomb.addActionListener(new Eredeti1());
        eredeti1_gomb.setBounds(379, 381, 113, 23);
        add(eredeti1_gomb);
        
        JButton eredeti2_gomb = new JButton("Eredeti kép");
        eredeti2_gomb.addActionListener(new Eredeti2());
        eredeti2_gomb.setBounds(379, 549, 113, 23);
        add(eredeti2_gomb);
        
        JLabel lblNewLabel = new JLabel("Hibakód");
        lblNewLabel.setBounds(622, 385, 60, 14);
        add(lblNewLabel);
        
        valasz6 = new JTextField();
        valasz6.setBounds(692, 382, 86, 20);
        add(valasz6);
        valasz6.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Hibakód");
        lblNewLabel_1.setBounds(622, 553, 60, 14);
        add(lblNewLabel_1);
        
        valasz7 = new JTextField();
        valasz7.setBounds(692, 550, 86, 20);
        add(valasz7);
        valasz7.setColumns(10);
        
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
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz19 = '" + valasz1.getText() +"', Valasz20 = '" + valasz2.getText() +"', Valasz21 = '" + valasz3.getText() +
                        "', Valasz22 = '" + valasz4.getText() + "', Valasz23 = '" + valasz5.getText() + "', Valasz24 = '" + valasz6.getText() +"', Valasz25 = '" + valasz7.getText()
                                 +"' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                dbiras.iro_kep(1, kepfajl1.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(2, kepfajl2.getAbsolutePath(), Teszt_kezdes.id);
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
                String sql = "UPDATE qualitydb.Ellenori_vizsga set Valasz19 = '" + valasz1.getText() +"', Valasz20 = '" + valasz2.getText() +"', Valasz21 = '" + valasz3.getText() +
                        "', Valasz22 = '" + valasz4.getText() + "', Valasz23 = '" + valasz5.getText() + "', Valasz24 = '" + valasz6.getText() +"', Valasz25 = '" + valasz7.getText()
                                 +"' where ID = '" + Teszt_kezdes.id +"'";
                
                dbiras.iras(sql, "", "");
                dbiras.iro_kep(1, kepfajl1.getAbsolutePath(), Teszt_kezdes.id);
                dbiras.iro_kep(2, kepfajl2.getAbsolutePath(), Teszt_kezdes.id);
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
    
    class Eredeti1 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\1.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
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
    
    class Eredeti2 implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                ImageIcon icon = new ImageIcon(kepek + Teszt_kezdes.tesztszam +"\\2.jpg");
                JOptionPane.showMessageDialog( null, "", "Hello", JOptionPane.INFORMATION_MESSAGE, icon);
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
        try
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
            
            kep1 = ImageIO.read(new File(kepfajl1.getAbsolutePath()));
            kep2 = ImageIO.read(new File(kepfajl2.getAbsolutePath()));            
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
    
    private void visszair()
    {
        try
        {
            SQL_teszt eddigi = new SQL_teszt();
            eddigi.beirva(Teszt_kezdes.id);
            valasz1.setText(SQL_teszt.beirt.get(22));
            valasz2.setText(SQL_teszt.beirt.get(23));
            valasz3.setText(SQL_teszt.beirt.get(24));
            valasz4.setText(SQL_teszt.beirt.get(25));
            valasz5.setText(SQL_teszt.beirt.get(26));
            valasz6.setText(SQL_teszt.beirt.get(28));
            valasz7.setText(SQL_teszt.beirt.get(30));          
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(kep1, 60, 320, (kep1.getWidth())/3, (kep1.getHeight())/3, this); // see javadoc for more info on the parameters
        g.drawImage(kep2, 60, 320 +(kep1.getHeight())/3 +20, (kep1.getWidth())/3, (kep1.getHeight())/3, this);
        //g.drawImage(kep3, 29, 250, (kep1.getWidth())/3, (kep1.getHeight())/3, this);
        //g.drawImage(kep4, 29, 250, (kep1.getWidth())/3, (kep1.getHeight())/3, this);
    }
}
