import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;

public class Vevoireklamacio_d2 extends JPanel {

    /**
     * Create the panel.
     */
    private JTextArea miaproblema_mezo;
    private JTextArea holdetektalta_mezo;
    private JTextArea miertproblema_mezo;
    private JTextArea hogyandetektalta_mezo;
    private JTextField datum_mezo;
    private JTextField db_mezo;
    private JTextField ki_mezo;
    private JTextField kepleiras_mezo;
    private  JLabel nok_kep;
    private  JLabel ok_kep;
    private JTable table;
    private DefaultTableModel modell;
    
    
    public Vevoireklamacio_d2() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        JLabel lblNewLabel = new JLabel("Mi a probléma?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(228, 64, 102, 14);
        add(lblNewLabel);
        
        miaproblema_mezo = new JTextArea();
        miaproblema_mezo.setBounds(340, 59, 260, 64);
        add(miaproblema_mezo);
        
        JLabel lblNewLabel_1 = new JLabel("Hol detektálta");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(723, 64, 94, 14);
        add(lblNewLabel_1);
        
        holdetektalta_mezo = new JTextArea();
        holdetektalta_mezo.setBounds(827, 59, 260, 64);
        add(holdetektalta_mezo);
        
        JLabel lblNewLabel_2 = new JLabel("Miért probléma?");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(228, 145, 102, 14);
        add(lblNewLabel_2);
        
        miertproblema_mezo = new JTextArea();
        miertproblema_mezo.setBounds(340, 140, 260, 64);
        add(miertproblema_mezo);
        
        JLabel lblNewLabel_3 = new JLabel("Hogyan detektálta?");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(693, 145, 124, 14);
        add(lblNewLabel_3);
        
        hogyandetektalta_mezo = new JTextArea();
        hogyandetektalta_mezo.setBounds(827, 140, 260, 64);
        add(hogyandetektalta_mezo);
        
        datum_mezo = new JTextField();
        datum_mezo.setBounds(340, 232, 86, 20);
        add(datum_mezo);
        datum_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Mikor detektálta?");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(228, 235, 102, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Hány darabot érint?");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(657, 235, 160, 14);
        add(lblNewLabel_5);
        
        db_mezo = new JTextField();
        db_mezo.setBounds(827, 232, 86, 20);
        add(db_mezo);
        db_mezo.setColumns(10);
        
        ki_mezo = new JTextField();
        ki_mezo.setBounds(340, 280, 188, 20);
        add(ki_mezo);
        ki_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Ki detektálta?");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(228, 283, 102, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Mi látható a képen?");
        lblNewLabel_7.setBounds(693, 298, 124, 14);
        add(lblNewLabel_7);
        
        kepleiras_mezo = new JTextField();
        kepleiras_mezo.setBounds(827, 295, 260, 20);
        add(kepleiras_mezo);
        kepleiras_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("NOK kép hozzáadása");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(188, 334, 142, 14);
        add(lblNewLabel_8);
        
        JButton nok_gomb = new JButton("Hozzáad");
        nok_gomb.addActionListener(new Megnyit_nok());
        nok_gomb.setBounds(340, 330, 89, 23);
        add(nok_gomb);
        
        JButton ok_gomb = new JButton("Hozzáad");
        ok_gomb.addActionListener(new Megnyit_ok());
        ok_gomb.setBounds(827, 330, 89, 23);
        add(ok_gomb);
        
        JLabel lblNewLabel_9 = new JLabel("Ok kép hozzáadása");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(693, 334, 124, 14);
        add(lblNewLabel_9);
        
        nok_kep = new JLabel("");
        nok_kep.setBounds(239, 397, 377, 275);
        add(nok_kep);
        
        ok_kep = new JLabel("");
        ok_kep.setBounds(740, 397, 377, 275);
        add(ok_kep);
        
        JLabel lblNewLabel_10 = new JLabel("Egyéb fájl hozzáadása");
        lblNewLabel_10.setBounds(1151, 334, 142, 14);
        add(lblNewLabel_10);
        
        JButton fajl_gomb = new JButton("Hozzáad");
        fajl_gomb.setBounds(1327, 330, 89, 23);
        add(fajl_gomb);
        
        JLabel lblNewLabel_11 = new JLabel("Csatolt fájok listája");
        lblNewLabel_11.setBounds(1270, 397, 137, 14);
        add(lblNewLabel_11);
        
        table = new JTable();
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Fájl neve"});  
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);        
        gorgeto.setBounds(1172, 422, 244, 146);
        add(gorgeto);

    }
    
    class Megnyit_nok implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();         
                ImageIcon icon2 = null;
                icon2 = new ImageIcon(fajl.getAbsolutePath());
                Image icon = icon2.getImage();  
                Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                nok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                                                                             //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
    
    class Megnyit_ok implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();         
                ImageIcon icon2 = null;
                icon2 = new ImageIcon(fajl.getAbsolutePath());
                Image icon = icon2.getImage();  
                Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                ok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                                                                             //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
}
