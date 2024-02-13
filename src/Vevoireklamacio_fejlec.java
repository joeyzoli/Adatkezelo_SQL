import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;

public class Vevoireklamacio_fejlec extends JPanel {
    static JTextField id_mezo;
    static JComboBox<String> fajta_box;
    static JLabel ertesites_cimke;
    static JLabel qr_cimke;
    static JRadioButton d0_gomb;
    static JRadioButton d1_gomb;
    static JRadioButton d3_gomb;
    static JLabel d3_cimke;
    static JRadioButton d4_gomb;
    static JRadioButton d5_gomb;
    static JLabel d5_cimke;
    static JRadioButton d6_gomb;
    static JRadioButton d7_gomb;
    static JRadioButton d8_gomb;
    static JRadioButton d2_gomb;
    static JLabel lezaras_cimke;
    static Color ertesitve = Color.gray;
    static Color qr = Color.gray;
    static Color d3 = Color.gray;
    static Color d5 = Color.gray;
    static Color lezaras = Color.gray;
    private String outlook_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\outlook.jpg";
    private String excel_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\excel.jpg";
    private String pdf_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\pdf.jpg";
    static ArrayList<String> fajlhelye = new ArrayList<String>();

    /**
     * Create the panel.
     */
    public Vevoireklamacio_fejlec() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setBounds(33, 24, 46, 14);
        add(lblNewLabel);
        
        id_mezo = new JTextField();
        id_mezo.addKeyListener(new Enter());
        id_mezo.setBounds(72, 21, 46, 20);
        add(id_mezo);
        id_mezo.setColumns(10);
        
        String[] fajtak = {"Reklamáció","Visszajelzés","Törölt"};
        fajta_box = new JComboBox<String>(fajtak);                                  //fajtak
        fajta_box.setBounds(72, 64, 199, 22);
        add(fajta_box);
        
        ertesites_cimke = new JLabel("New label");
        ertesites_cimke.setBounds(440, 68, 81, 14);
        add(ertesites_cimke);
        
        qr_cimke = new JLabel("New label");
        qr_cimke.setBounds(613, 68, 69, 14);
        add(qr_cimke);
        
        d0_gomb = new JRadioButton("D0");
        d0_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d0"));
        d0_gomb.setBounds(247, 117, 46, 23);
        add(d0_gomb);
        
        d1_gomb = new JRadioButton("D1");
        d1_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d1"));
        d1_gomb.setBounds(353, 117, 46, 23);
        add(d1_gomb);
        
        d2_gomb = new JRadioButton("D2");
        d2_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d2"));
        d2_gomb.setBounds(474, 117, 51, 23);
        add(d2_gomb);
        
        d3_gomb = new JRadioButton("D3");
        d3_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d3"));
        d3_gomb.setBounds(594, 117, 46, 23);
        add(d3_gomb);
        
        d3_cimke = new JLabel("New label");
        d3_cimke.setBounds(793, 68, 69, 14);
        add(d3_cimke);
        
        d4_gomb = new JRadioButton("D4");
        d4_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d4"));
        d4_gomb.setBounds(722, 117, 51, 23);
        add(d4_gomb);
        
        d5_gomb = new JRadioButton("D5");
        d5_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d5"));
        d5_gomb.setBounds(836, 117, 51, 23);
        add(d5_gomb);
        
        d5_cimke = new JLabel("New label");
        d5_cimke.setBounds(929, 68, 69, 14);
        add(d5_cimke);
        
        d6_gomb = new JRadioButton("D6");
        d6_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d6"));
        d6_gomb.setBounds(929, 117, 51, 23);
        add(d6_gomb);
        
        d7_gomb = new JRadioButton("D7");
        d7_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d7"));
        d7_gomb.setBounds(1016, 117, 51, 23);
        add(d7_gomb);
        
        d8_gomb = new JRadioButton("D8");
        d8_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "d8"));
        d8_gomb.setBounds(1118, 117, 51, 23);
        add(d8_gomb);
        
        
        
        ButtonGroup csoport = new ButtonGroup();
        csoport.add(d0_gomb);
        csoport.add(d1_gomb);
        csoport.add(d2_gomb);
        csoport.add(d3_gomb);
        csoport.add(d4_gomb);
        csoport.add(d5_gomb);
        csoport.add(d6_gomb);
        csoport.add(d7_gomb);
        csoport.add(d8_gomb);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(1257, 64, 116, 23);
        add(mentes_gomb);
        
        JButton attekinto_gomb = new JButton("Áttekintő");
        attekinto_gomb.setBounds(1257, 20, 116, 23);
        add(attekinto_gomb);
        
        lezaras_cimke = new JLabel("New label");
        lezaras_cimke.setBounds(1073, 68, 81, 14);
        add(lezaras_cimke);
        
        JLabel lblNewLabel_1 = new JLabel("Vevői értesítés");
        lblNewLabel_1.setBounds(427, 24, 91, 14);
        add(lblNewLabel_1);
        
        JLabel qrcimke = new JLabel("QR < 24");
        qrcimke.addMouseListener (new MouseListener () {
            //override the method
            public void mousePressed(MouseEvent e) {
                
        }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Klikkelve");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();                                
                if(fajl != null)
                {                  
                    Vevoireklamacio_fejlec.d3 = Color.YELLOW;
                    Vevoireklamacio_fejlec.qr = Color.GREEN;
                    String[] fajltipus = fajl.getName().split("\\.");
                    fajlhelye.add(fajl.getAbsolutePath());
                    ImageIcon icon = null;
                    if(fajltipus[1].equals("msg"))
                    {
                        icon = new ImageIcon(outlook_kep);
                    }
                    else if(fajltipus[1].equals("xlsx") || fajltipus[1].equals("xls"))
                    {
                        icon = new ImageIcon(excel_kep);
                    }
                    else if(fajltipus[1].equals("pdf"))
                    {
                        icon = new ImageIcon(pdf_kep);
                    }
                    
                    Vevoireklamacio_d0.modell.addRow(new Object[]{icon,fajl.getName()});
                    TableColumnModel columnModel = Vevoireklamacio_d0.table.getColumnModel();
                    for (int column = 0; column < Vevoireklamacio_d0.table.getColumnCount(); column++) {
                        int width = 15; // Min width
                        for (int row = 0; row < Vevoireklamacio_d0.table.getRowCount(); row++) {
                            TableCellRenderer renderer = Vevoireklamacio_d0.table.getCellRenderer(row, column);
                            Component comp = Vevoireklamacio_d0.table.prepareRenderer(renderer, row, column);
                            width = Math.max(comp.getPreferredSize().width +1 , width);
                        }
                        if(width > 300)
                            width=300;
                        columnModel.getColumn(column).setPreferredWidth(width);
                    }
                    Vevoireklamacio_d0.table.setModel(Vevoireklamacio_d0.modell);                                      
                    JOptionPane.showMessageDialog(null, "Email csatolva!", "Info", 1);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        qrcimke.setBounds(621, 24, 71, 14);
        add(qrcimke);
        
        JLabel lblNewLabel_3 = new JLabel("Lezárás");
        lblNewLabel_3.setBounds(1074, 24, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("D3 állapot");
        lblNewLabel_4.setBounds(793, 24, 81, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("D5 állapot");
        lblNewLabel_5.setBounds(929, 24, 87, 14);
        add(lblNewLabel_5);
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       g.setColor(ertesitve);
       g.fillOval(455, 43, 15, 15);
       
       g.setColor(qr);
       g.fillOval(630, 43, 15, 15);
       
       g.setColor(d3);
       g.fillOval(807, 43, 15, 15);
       
       g.setColor(d5);
       g.fillOval(950, 43, 15, 15);
       
       g.setColor(lezaras);
       g.fillOval(1090, 43, 15, 15);
       repaint();
    }
    /*
    public void switchRed() {
        this.ertesitve = Color.GREEN;
        this.qr = Color.BLACK;
        this.d3 = Color.BLACK;
        this.d5 = Color.BLACK;
        this.lezaras = Color.BLACK;
 
        repaint();
    }*/
    
    class Mentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                
                Vevoireklamacio_V2.d0.mentes();
                Vevoireklamacio_V2.d1.mentes();
                Vevoireklamacio_V2.d2.mentes();
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
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre               
                Vevoireklamacio_V2.d0.visszatolt();
                Vevoireklamacio_V2.d1.visszatolt();
                Vevoireklamacio_V2.d2.visszatolt();
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
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
