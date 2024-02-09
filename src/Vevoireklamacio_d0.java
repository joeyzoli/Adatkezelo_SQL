import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class Vevoireklamacio_d0 extends JPanel {
    private JTextField vevo_mezo;
    private JTextField beszallito_mezo;
    private JTextField ertesites_mezo;
    private JTextField vevorek_mezo;
    private JTextField veasrek_mezo;
    private JTextField frissites_mezo;
    private JTextField felelos_mezo;
    private JTextField felelos2_mezo;
    private JTextField tipus_mezo;
    private JTextField email_mezo;
    private JTextField emial2_mezo;
    private JTextField rma_mezo;
    private JTextField telefonszam_mezo;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblNewLabel_15;
    private JTable table;
    private DefaultTableModel modell;
    private JSeparator separator;

    /**
     * Create the panel.
     */
    public Vevoireklamacio_d0() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vevő");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(214, 57, 46, 14);
        add(lblNewLabel);
        
        vevo_mezo = new JTextField();
        vevo_mezo.setBounds(274, 54, 162, 20);
        add(vevo_mezo);
        vevo_mezo.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Beszállító");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(537, 57, 71, 14);
        add(lblNewLabel_1);
        
        beszallito_mezo = new JTextField();
        beszallito_mezo.setText("Videoton EAS Kft.");
        beszallito_mezo.setBounds(618, 54, 162, 20);
        add(beszallito_mezo);
        beszallito_mezo.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Értesítés dátuma");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(900, 57, 102, 14);
        add(lblNewLabel_2);
        
        ertesites_mezo = new JTextField();
        ertesites_mezo.addKeyListener(new Enter());
        ertesites_mezo.setBounds(1012, 54, 86, 20);
        add(ertesites_mezo);
        ertesites_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Vevői reklamációs szám");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(119, 105, 141, 14);
        add(lblNewLabel_3);
        
        vevorek_mezo = new JTextField();
        vevorek_mezo.setBounds(274, 102, 86, 20);
        add(vevorek_mezo);
        vevorek_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("VEAS reklmációs szám");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(462, 105, 146, 14);
        add(lblNewLabel_4);
        
        veasrek_mezo = new JTextField();
        veasrek_mezo.setBounds(618, 102, 86, 20);
        add(veasrek_mezo);
        veasrek_mezo.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Utolsó frissítés");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setBounds(916, 105, 86, 14);
        add(lblNewLabel_5);
        
        frissites_mezo = new JTextField();
        frissites_mezo.setBounds(1012, 102, 86, 20);
        add(frissites_mezo);
        frissites_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Felelős");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(214, 163, 46, 14);
        add(lblNewLabel_6);
        
        felelos_mezo = new JTextField();
        felelos_mezo.setBounds(274, 160, 162, 20);
        add(felelos_mezo);
        felelos_mezo.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Felelős");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_7.setBounds(562, 163, 46, 14);
        add(lblNewLabel_7);
        
        felelos2_mezo = new JTextField();
        felelos2_mezo.setBounds(618, 160, 162, 20);
        add(felelos2_mezo);
        felelos2_mezo.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Típus");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_8.setBounds(956, 163, 46, 14);
        add(lblNewLabel_8);
        
        tipus_mezo = new JTextField();
        tipus_mezo.setBounds(1012, 160, 151, 20);
        add(tipus_mezo);
        tipus_mezo.setColumns(10);
        
        email_mezo = new JTextField();
        email_mezo.setBounds(274, 208, 162, 20);
        add(email_mezo);
        email_mezo.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("E-mail");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_9.setBounds(189, 211, 71, 14);
        add(lblNewLabel_9);
        
        JLabel lblNewLabel_10 = new JLabel("E-mail");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_10.setBounds(562, 211, 46, 14);
        add(lblNewLabel_10);
        
        emial2_mezo = new JTextField();
        emial2_mezo.setBounds(618, 208, 162, 20);
        add(emial2_mezo);
        emial2_mezo.setColumns(10);
        
        rma_mezo = new JTextField();
        rma_mezo.setBounds(1012, 208, 86, 20);
        add(rma_mezo);
        rma_mezo.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("RMA szám");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_11.setBounds(931, 211, 71, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Telefonszám");
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_12.setBounds(158, 264, 102, 14);
        add(lblNewLabel_12);
        
        telefonszam_mezo = new JTextField();
        telefonszam_mezo.setBounds(274, 261, 162, 20);
        add(telefonszam_mezo);
        telefonszam_mezo.setColumns(10);
        
        textField = new JTextField();
        textField.setBounds(618, 261, 162, 20);
        add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_13 = new JLabel("Telefonszám");
        lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_13.setBounds(522, 264, 86, 14);
        add(lblNewLabel_13);
        
        textField_1 = new JTextField();
        textField_1.setBounds(1012, 261, 86, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_14 = new JLabel("Visszaküldés dátuma");
        lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_14.setBounds(873, 264, 129, 14);
        add(lblNewLabel_14);
        
        setBackground(Foablak.hatter_szine);
        
        lblNewLabel_15 = new JLabel("Feltöltött fájlok");
        lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_15.setBounds(142, 373, 118, 14);
        add(lblNewLabel_15);
        
        table = new JTable();        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Fájl tipusa", "Fájl neve"});  
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(274, 373, 577, 132);        
        add(gorgeto);
        
        separator = new JSeparator();
        separator.setBounds(53, 314, 1254, 46);
        add(separator);

    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Vevoireklamacio_fejlec.ertesites_cimke.setText(ertesites_mezo.getText());
                    SimpleDateFormat rovid = new SimpleDateFormat("yyyy.mm.dd");
                    Date datum = rovid.parse(ertesites_mezo.getText());
                    Date dt = rovid.parse(ertesites_mezo.getText());
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(dt); 
                    c.add(Calendar.DATE, 1);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.qr_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 2);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.d3_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 10);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.d5_cimke.setText(rovid.format(dt));
                    c.setTime(datum); 
                    c.add(Calendar.DATE, 30);
                    dt = c.getTime();
                    Vevoireklamacio_fejlec.lezaras_cimke.setText(rovid.format(dt));
                    
                    Vevoireklamacio_fejlec.ertesitve = Color.GREEN;
                    Vevoireklamacio_fejlec.qr = Color.YELLOW;
                    Vevoireklamacio_fejlec.d3 = Color.gray;
                    Vevoireklamacio_fejlec.d5 = Color.gray;
                    Vevoireklamacio_fejlec.lezaras = Color.GRAY;
                    repaint();
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
}
