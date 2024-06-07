import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelPicture;
import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
import com.spire.xls.VerticalAlignType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
    static String outlook_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\outlook.jpg";
    static String excel_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\excel.jpg";
    static String pdf_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\pdf.jpg";
    static String kep_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\kep.jpg";
    static String word_kep = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Ikonok\\word.jpg";
    private String d8 = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Q-JK-003 8D report.xlsx";
    static JButton mentes_gomb;
    static JRadioButton koltseg_gomb;
    private JButton ujreklamacio_gomb;
    private JFileChooser mentes_helye;

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
        fajta_box.addActionListener(new Mentesgomb_aktival());
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
        
        koltseg_gomb = new JRadioButton("Költségek");
        koltseg_gomb.addActionListener(e -> Vevoireklamacio_V2.cardLayout.show(Vevoireklamacio_V2.kartyak, "koltseg"));
        koltseg_gomb.setBounds(1215, 117, 91, 23);
        add(koltseg_gomb);
        
        
        
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
        csoport.add(koltseg_gomb);
        
        mentes_gomb = new JButton("Mentés");
        mentes_gomb.setEnabled(false);
        mentes_gomb.addActionListener(new Mentes());
        mentes_gomb.setBounds(1257, 64, 116, 23);
        add(mentes_gomb);
        
        JButton attekinto_gomb = new JButton("Áttekintő");
        attekinto_gomb.addActionListener(new Attekinto());
        attekinto_gomb.setBounds(1257, 20, 116, 23);
        add(attekinto_gomb);
        
        lezaras_cimke = new JLabel("New label");
        lezaras_cimke.setBounds(1073, 68, 81, 14);
        add(lezaras_cimke);
        
        JLabel lblNewLabel_1 = new JLabel("Vevői értesítés");
        lblNewLabel_1.setBounds(427, 24, 91, 14);
        add(lblNewLabel_1);
        
        mentes_helye = new JFileChooser();
        mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
        mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        mentes_helye.setDragEnabled(true);
        
        JLabel qrcimke = new JLabel("QR < 24");
        qrcimke.addMouseListener (new MouseListener () {
            //override the method
            public void mousePressed(MouseEvent e) {
                
        }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Klikkelve");
                
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();                                
                if(fajl != null)
                {                  
                    Vevoireklamacio_fejlec.d3 = Color.YELLOW;
                    Vevoireklamacio_fejlec.qr = Color.GREEN;
                    Vevoireklamacio_d2.fajlok.add(fajl.getName()+";"+fajl.getAbsolutePath()+";"+hozzaad(fajl.getName()));
                    mentes_gomb.setEnabled(true);
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
        
        ujreklamacio_gomb = new JButton("Új reklamáció");
        ujreklamacio_gomb.addActionListener(new Panelcsere_Vevoi2());
        ujreklamacio_gomb.setBounds(184, 20, 136, 23);
        add(ujreklamacio_gomb);
        
        Vevoireklamacio_fejlec.ertesitve = Color.gray;
        Vevoireklamacio_fejlec.qr = Color.gray;
        Vevoireklamacio_fejlec.d3 = Color.gray;
        Vevoireklamacio_fejlec.d5 = Color.gray;
        Vevoireklamacio_fejlec.lezaras = Color.gray;
        
        MyDragDropListener myDragDropListener = new MyDragDropListener();
        new DropTarget(qrcimke, myDragDropListener);
        new DropTarget(mentes_helye, myDragDropListener);
  
    }
    
    public String hozzaad(String fajlnev)
    {                            
        ImageIcon icon = null;
        String tipus = "*";
        if(fajlnev.endsWith("msg"))
        {
            icon = new ImageIcon(outlook_kep);
            tipus = "email";
        }
        else if(fajlnev.endsWith("xlsx") || fajlnev.endsWith("xls"))
        {
            icon = new ImageIcon(excel_kep);
        }
        else if(fajlnev.endsWith("pdf"))
        {
            icon = new ImageIcon(pdf_kep);
        }
        else if(fajlnev.endsWith("jpg"))
        {
            icon = new ImageIcon(kep_kep);
        }
        else if(fajlnev.endsWith("png"))
        {
            icon = new ImageIcon(kep_kep);
        }
        else if(fajlnev.endsWith("doc"))
        {
            icon = new ImageIcon(word_kep);
        }
        else if(fajlnev.endsWith("docx"))
        {
            icon = new ImageIcon(word_kep);
        }
        mentes_gomb.setEnabled(true);
        Vevoireklamacio_d0.modell.addRow(new Object[]{icon,fajlnev});
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
        return tipus;
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
                if(Vevoireklamacio_d8.lezaras_datuma.getText().equals(""))
                {
                    Vevoireklamacio_V2.d0.mentes();
                    Vevoireklamacio_V2.d1.mentes();
                    Vevoireklamacio_V2.d2.mentes();
                    Vevoireklamacio_V2.d3.mentes();
                    Vevoireklamacio_V2.d4.mentes();
                    Vevoireklamacio_V2.d5.mentes();
                    Vevoireklamacio_V2.d6.mentes();
                    Vevoireklamacio_V2.d7.mentes();
                    Vevoireklamacio_V2.d8.mentes();
                    Vevoireklamacio_fejlec.mentes_gomb.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Mentve", "Info", 1);
                }
                else
                {
                    SQA_SQL ment = new SQA_SQL();
                    String sql = "select D3 from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                    ment.tombvissza_sajat(sql);
                    if(ment.tombvissza_sajat(sql)[0] == null)
                    {
                        JOptionPane.showMessageDialog(null, "A D3 lezárása nélkül nem lehet lezárni a reklamációt!", "Hiba üzenet", 2);
                    }
                    else
                    {
                        sql = "update qualitydb.Vevoireklamacio_alap set D8 = '"+ Vevoireklamacio_d8.lezaras_datuma.getText() +"' where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                        ment.mindenes(sql);
                        Vevoireklamacio_fejlec.lezaras = Color.GREEN;
                        Vevoireklamacio_V2.d0.mentes();
                        Vevoireklamacio_V2.d1.mentes();
                        Vevoireklamacio_V2.d2.mentes();
                        Vevoireklamacio_V2.d3.mentes();
                        Vevoireklamacio_V2.d4.mentes();
                        Vevoireklamacio_V2.d5.mentes();
                        Vevoireklamacio_V2.d6.mentes();
                        Vevoireklamacio_V2.d7.mentes();
                        Vevoireklamacio_V2.d8.mentes();
                        Vevoireklamacio_V2.koltseg.mentes();
                        Vevoireklamacio_fejlec.mentes_gomb.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Mentve", "Info", 1);
                    }
                }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
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
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                    mentes_gomb.setEnabled(false);
                    Vevoireklamacio_d2.fajlok.clear();
                    Vevoireklamacio_V2.d0.visszatolt();
                    Vevoireklamacio_V2.d1.visszatolt();
                    Vevoireklamacio_V2.d2.visszatolt();
                    Vevoireklamacio_V2.d3.visszatolt();
                    Vevoireklamacio_V2.d4.visszatolt();
                    Vevoireklamacio_V2.d5.visszatolt();
                    Vevoireklamacio_V2.d6.visszatolt();
                    Vevoireklamacio_V2.d7.visszatolt();
                    Vevoireklamacio_V2.d8.visszatolt();
                    Vevoireklamacio_V2.koltseg.visszatolt();
                    Vevoireklamacio_fejlec.mentes_gomb.setEnabled(false);
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
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
    
    static class Valtozas_figyelo implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            try 
            {
                mentes_gomb.setEnabled(true);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
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
    
    class Attekinto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Connection conn = null;
                Statement stmt = null;
                Statement stmt2 = null;
                Statement stmt3 = null;
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                stmt2 = (Statement) conn.createStatement();
                stmt3 = (Statement) conn.createStatement();
                String sql = "select * from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                
                sql = "select * from qualitydb.Vevoireklamacio_elo where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                stmt2.execute(sql);
                ResultSet rs2 = stmt2.getResultSet();
                
                sql = "select * from qualitydb.Vevoireklamacio_det where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                stmt3.execute(sql);
                ResultSet rs3 = stmt3.getResultSet();
                Workbook workbook = new Workbook();
                workbook.loadFromFile(d8);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                if(rs.next())
                {
                    ////////D1
                    sheet.getRange().get("C4").setText(rs.getString(3));
                    sheet.getRange().get("F4").setText(rs.getString(4));
                    sheet.getRange().get("K4").setText(rs.getString(5));
                    sheet.getRange().get("C5").setText(rs.getString(6));
                    sheet.getRange().get("F5").setText(rs.getString(1));                    
                    sheet.getRange().get("K5").setText(rs.getString(7));
                    String[] felelosok = rs.getString(8).split(";");
                    if(felelosok.length > 1)
                    {
                        sheet.getRange().get("C6").setText(felelosok[0]);
                        sheet.getRange().get("F6").setText(felelosok[1]);
                    }
                    else if(felelosok.length == 1)
                    {
                        sheet.getRange().get("C6").setText(felelosok[0]);
                        sheet.getRange().get("F6").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C6").setText("");
                        sheet.getRange().get("F6").setText("");
                    }
                    String[] cikkszamok = rs.getString(9).split(";");
                    if(cikkszamok.length == 1)
                    {
                        sheet.getRange().get("K6").setText(rs.getString(9));
                    }
                    else
                    {
                        String cikkek = "";
                        for(int szamlalo = 0; szamlalo < cikkszamok.length;szamlalo++)
                        {
                            if(szamlalo == cikkszamok.length-1)
                            {
                                cikkek += cikkszamok[szamlalo];
                            }
                            else
                            {
                                cikkek += cikkszamok[szamlalo] +"\n";
                            }
                        }
                        sheet.getRange().get("K6").setText(cikkek);
                    }
                    String[] email = rs.getString(10).split(";");
                    if(email.length > 1)
                    {
                        sheet.getRange().get("C7").setText(email[0]);
                        sheet.getRange().get("F7").setText(email[1]);
                    }
                    else if(email.length == 1)
                    {
                        sheet.getRange().get("C7").setText(email[0]);
                        sheet.getRange().get("F7").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C7").setText("");
                        sheet.getRange().get("F7").setText("");
                    }                   
                    sheet.getRange().get("K7").setText(rs.getString(11));
                    String[] telefon = rs.getString(12).split(";");
                    if(telefon.length > 1)
                    {
                        sheet.getRange().get("C8").setText(telefon[0]);
                        sheet.getRange().get("F8").setText(telefon[1]);
                    }
                    else if(telefon.length == 1)
                    {
                        sheet.getRange().get("C8").setText(telefon[0]);
                        sheet.getRange().get("F8").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C8").setText("");
                        sheet.getRange().get("F8").setText("");
                    }                    
                    sheet.getRange().get("K8").setText(rs.getString(13));
                    String[] csapat = rs.getString(14).split(";");
                    String[] csapattagok = csapat[1].split("\n");
                    sheet.getRange().get("C11").setText(csapat[0]);
                    String emberek = "";
                    for(int szamlalo = 0; szamlalo < csapattagok.length; szamlalo++)
                    {
                        emberek += csapattagok[szamlalo]+", ";
                    }
                    sheet.getRange().get("C12").setText(emberek);
                    ///////D2
                    String alap = sheet.getRange().get("B16").getText();
                    sheet.getRange().get("B16").setText(alap +" "+rs.getString(15));
                    alap = sheet.getRange().get("B18").getText();
                    sheet.getRange().get("B18").setText(alap +" "+rs.getString(17));
                    alap = sheet.getRange().get("B20").getText();
                    sheet.getRange().get("B20").setText(alap +" "+rs.getString(19));
                    alap = sheet.getRange().get("B22").getText();
                    sheet.getRange().get("B22").setText(alap +" "+rs.getString(21));
                    alap = sheet.getRange().get("B24").getText();
                    sheet.getRange().get("B24").setText(alap +" "+rs.getString(16));
                    alap = sheet.getRange().get("B26").getText();
                    sheet.getRange().get("B26").setText(alap +" "+rs.getString(18));
                    alap = sheet.getRange().get("B28").getText();
                    sheet.getRange().get("B28").setText(alap +" "+rs.getString(20));
                    alap = sheet.getRange().get("I28").getText();
                    sheet.getRange().get("I28").setText(alap +" "+rs.getString(22));
                    //////D3
                    sheet.getRange().get("J32").setText(rs.getString(23));
                    sheet.getRange().get("K32").setText(rs.getString(24));
                    sheet.getRange().get("M32").setText(rs.getString(25));
                    sheet.getRange().get("J33").setText(rs.getString(26));
                    sheet.getRange().get("K33").setText(rs.getString(27));
                    sheet.getRange().get("M33").setText(rs.getString(28));
                    sheet.getRange().get("J34").setText(rs.getString(29));
                    sheet.getRange().get("K34").setText(rs.getString(30));
                    sheet.getRange().get("M34").setText(rs.getString(31));
                    sheet.getRange().get("J35").setText(rs.getString(32));
                    sheet.getRange().get("K35").setText(rs.getString(33));
                    sheet.getRange().get("M35").setText(rs.getString(34));
                    sheet.getRange().get("G36").setText(rs.getString(35));
                    String[] dbszam = rs.getString(36).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("D37").setText(dbszam[0]);
                        sheet.getRange().get("G37").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("D37").setText(dbszam[0]);
                        sheet.getRange().get("G37").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("D37").setText("");
                        sheet.getRange().get("G37").setText("");
                    }       
                    
                    dbszam = rs.getString(37).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("D38").setText(dbszam[0]);
                        sheet.getRange().get("G38").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("D38").setText(dbszam[0]);
                        sheet.getRange().get("G38").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("D38").setText("");
                        sheet.getRange().get("G38").setText("");
                    }     
                    
                    dbszam = rs.getString(38).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("D39").setText(dbszam[0]);
                        sheet.getRange().get("G39").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("D39").setText(dbszam[0]);
                        sheet.getRange().get("G39").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("D39").setText("");
                        sheet.getRange().get("G39").setText("");
                    }     
                    
                    //////D4
                    dbszam = rs.getString(39).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("C45").setText(dbszam[0]);
                        sheet.getRange().get("C50").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("C45").setText(dbszam[0]);
                        sheet.getRange().get("C50").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C45").setText("");
                        sheet.getRange().get("C50").setText("");
                    }
                    
                    dbszam = rs.getString(40).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("C46").setText(dbszam[0]);
                        sheet.getRange().get("C51").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("C46").setText(dbszam[0]);
                        sheet.getRange().get("C51").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C46").setText("");
                        sheet.getRange().get("C51").setText("");
                    }
                    
                    dbszam = rs.getString(41).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("C47").setText(dbszam[0]);
                        sheet.getRange().get("C52").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("C47").setText(dbszam[0]);
                        sheet.getRange().get("C52").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C47").setText("");
                        sheet.getRange().get("C52").setText("");
                    }
                    
                    dbszam = rs.getString(42).split(";");
                    if(dbszam.length > 1)
                    {
                        sheet.getRange().get("C48").setText(dbszam[0]);
                        sheet.getRange().get("C53").setText(dbszam[1]);
                    }
                    else if(dbszam.length == 1)
                    {
                        sheet.getRange().get("C48").setText(dbszam[0]);
                        sheet.getRange().get("C53").setText("");
                    }
                    else
                    {
                        sheet.getRange().get("C48").setText("");
                        sheet.getRange().get("C53").setText("");
                    }
                    
                    //////D5
                    int sor = 0;
                    int cella = 57;
                    int sorhozzaad = 0;
                    int d5sor = 61;
                    int d6sor = 66;
                    int d7sor = 73;
                    int d8sor = 84;
                    while(rs2.next())
                    {
                        if(rs2.getString(7).equals("D5"))
                        {
                            if(sor > 2)
                            {
                                sheet.insertRow(cella);
                                sheet.getRange().get("B"+cella+":I"+cella).merge();
                                sheet.getCellRange("J"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("J"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getRange().get("K"+cella+":L"+cella).merge();
                                sheet.getCellRange("K"+cella+":L"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("K"+cella+":k"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                            sheet.getRange().get("B"+cella).setText(rs2.getString(3));
                            sheet.getRange().get("J"+cella).setText(rs2.getString(4));
                            sheet.getRange().get("K"+cella).setText(rs2.getString(5));
                            sheet.getRange().get("M"+cella).setText(rs2.getString(6));
                            cella++;
                            sor++;
                        }
                    }                    
                    cella += 1;
                    if(sor > 3)
                    {
                        sorhozzaad = sor -3;
                        System.out.println(sorhozzaad);
                    }
                    else
                    {
                        cella = d5sor;
                    }
                    sor = 0;
                    while(rs3.next())
                    {
                        if(rs3.getString(7).equals("D5"))
                        {
                            if(sor > 3)
                            {
                                sheet.insertRow(cella);
                                sheet.getRange().get("B"+cella+":I"+cella).merge();
                                sheet.getCellRange("J"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("J"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getRange().get("K"+cella+":L"+cella).merge();
                                sheet.getCellRange("K"+cella+":L"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("K"+cella+":k"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                            sheet.getRange().get("B"+cella).setText(rs3.getString(3));
                            sheet.getRange().get("J"+cella).setText(rs3.getString(4));
                            sheet.getRange().get("K"+cella).setText(rs3.getString(5));
                            sheet.getRange().get("M"+cella).setText(rs3.getString(6));
                            cella++;
                            sor++;
                        }
                    }
                    if(sor > 3)
                    {
                        int koztes = sorhozzaad;
                        sorhozzaad = sor -3;
                        sorhozzaad = koztes + sorhozzaad;
                        System.out.println(sorhozzaad);
                    }                    
                    cella = d6sor + sorhozzaad;
                    ////////D6
                    sheet.getRange().get("B"+cella).setText(rs.getString(43));
                    ////////D7
                    sql = "select * from qualitydb.Vevoireklamacio_elo where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                    stmt2.execute(sql);
                    rs2 = stmt2.getResultSet();
                    
                    sql = "select * from qualitydb.Vevoireklamacio_det where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
                    stmt3.execute(sql);
                    rs3 = stmt3.getResultSet();
                    cella = d7sor + sorhozzaad;
                    sor = 0;
                    while(rs2.next())
                    {
                        if(rs2.getString(7).equals("D7"))
                        {
                            if(sor > 3)
                            {
                                sheet.insertRow(cella);
                                sheet.getRange().get("B"+cella+":I"+cella).merge();
                                sheet.getCellRange("J"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("J"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getRange().get("K"+cella+":L"+cella).merge();
                                sheet.getCellRange("K"+cella+":L"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("K"+cella+":k"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                            sheet.getRange().get("B"+cella).setText(rs2.getString(3));
                            sheet.getRange().get("J"+cella).setText(rs2.getString(4));
                            sheet.getRange().get("K"+cella).setText(rs2.getString(5));
                            sheet.getRange().get("M"+cella).setText(rs2.getString(6));
                            cella++;
                            sor++;
                        }
                    }                    
                    cella += 1;
                    if(sor > 3)
                    {
                        int koztes = sorhozzaad;
                        sorhozzaad = sor -3;
                        sorhozzaad = koztes + sorhozzaad;
                        System.out.println(sorhozzaad);
                    }
                    sor = 1;
                    while(rs3.next())
                    {
                        if(rs3.getString(7).equals("D7"))
                        {
                            if(sor > 3)
                            {
                                sheet.insertRow(cella);
                                sheet.getRange().get("B"+cella+":I"+cella).merge();
                                sheet.getCellRange("J"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("J"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getRange().get("K"+cella+":L"+cella).merge();
                                sheet.getCellRange("K"+cella+":L"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("K"+cella+":k"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setVerticalAlignment(VerticalAlignType.Center);
                                sheet.getCellRange("M"+cella).getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
                            }
                            sheet.getRange().get("B"+cella).setText(rs3.getString(3));
                            sheet.getRange().get("J"+cella).setText(rs3.getString(4));
                            sheet.getRange().get("K"+cella).setText(rs3.getString(5));
                            sheet.getRange().get("M"+cella).setText(rs3.getString(6));
                            cella++;
                            sor++;
                        }
                    }
                    if(sor > 3)
                    {
                        int koztes = sorhozzaad;
                        sorhozzaad = sor -3;
                        sorhozzaad = koztes + sorhozzaad;
                        System.out.println(sorhozzaad);
                    }
                    ///////D8
                    cella = d8sor + sorhozzaad;
                    sheet.getRange().get("B"+cella).setText(rs.getString(44));
                    sheet.getRange().get("K"+cella).setText(rs.getString(48));
                    /////////Kép
                    PreparedStatement ps= conn.prepareStatement("SELECT Fajl_neve, Fajl, tipus FROM qualitydb.Vevoireklamacio_fajlok WHERE Rek_ID = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
                    ResultSet rset = ps.executeQuery();         
                    Blob blob;
                    byte b[];
                    FileOutputStream fs=null;                    
                    while(rset.next())
                    {                       
                        File f = new File(System.getProperty("user.home") + "\\Desktop\\Vevoireklamacio ID "+ Vevoireklamacio_fejlec.id_mezo.getText() +"\\"+ rset.getString(1));        //"+ Vevoireklamacio_fejlec.id_mezo.getText() +"
                        f.getParentFile().mkdirs(); 
                        f.createNewFile();  
                        fs = new FileOutputStream(f);
                        blob = rset.getBlob("Fajl");
                        b = blob.getBytes(1, (int)blob.length());
                        fs.write(b);
                        fs.close();
                        if(rset.getString(3).equals("nok"))
                        {
                            ExcelPicture pic = sheet.getPictures().add(16, 9,f.getAbsolutePath());
                            ImageIcon icon2 = null;
                            icon2 = new ImageIcon(f.getAbsolutePath());
                            @SuppressWarnings("unused")
                            Image icon = icon2.getImage();  
                            int szelesseg = 0;
                            int magassag = 0;
                            if(icon2.getIconWidth() > icon2.getIconHeight())
                            {
                                float egyszazalek = icon2.getIconWidth() /100;
                                float szazalek = 530/egyszazalek;
                                //float szeles = icon2.getIconHeight()/100*szazalek;
                                float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = 530;
                                magassag = (int) magas;
                            }
                            else
                            {
                                float egyszazalek = icon2.getIconHeight() /100;
                                float szazalek = 285/egyszazalek;
                                float szeles = icon2.getIconWidth()/100*szazalek;
                                //float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = (int) szeles;
                                magassag = 285;
                            }
                            pic.setWidth(szelesseg);
                            pic.setHeight(magassag);
                        }
                    }                    
                }
                //sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                //sheet.getAllocatedRange().autoFitColumns();
                //sheet.getAllocatedRange().autoFitRows();
                //sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Vevoireklamacio ID "+ Vevoireklamacio_fejlec.id_mezo.getText() +"\\8D REk_ID "+ Vevoireklamacio_fejlec.id_mezo.getText() +".xlsx";
                workbook.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook3.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook3.write(output);
                    output.close();
                }
                stmt.close();
                conn.close();                

                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Vevoireklamacio ID "+ Vevoireklamacio_fejlec.id_mezo.getText() +" néven", "Info", 1);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Mentesgomb_aktival implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                mentes_gomb.setEnabled(true);
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Panelcsere_Vevoi2 implements ActionListener                                                                                   //menüelem megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Vevoireklamacio_V2 vevoirek = new Vevoireklamacio_V2();
            JScrollPane ablak = new JScrollPane(vevoirek);
            Foablak.frame.setContentPane(ablak);
            Foablak.frame.pack();
         }
    }
    
    class MyDragDropListener implements DropTargetListener {

        @Override
        public void drop(DropTargetDropEvent event) {

            // Accept copy drops
            event.acceptDrop(DnDConstants.ACTION_COPY);

            // Get the transfer which can provide the dropped item data
            Transferable transferable = event.getTransferable();

            // Get the data formats of the dropped item
            DataFlavor[] flavors = transferable.getTransferDataFlavors();

            // Loop through the flavors
            for (DataFlavor flavor : flavors) 
            {
                try 
                {
                   // If the drop items are files
                    if (flavor.isFlavorJavaFileListType()) 
                    {
                        //System.out.println("A nok képhozzáadás fut");
                        // Get all of the dropped files
                        List<?> files = (List<?>) transferable.getTransferData(flavor);
                        //System.out.println(files.toString());
                        //System.out.println(files.toString().substring(1,files.toString().length()-1));
                        File fajl = new File(files.toString().substring(1,files.toString().length()-1));
                        if(fajl != null)
                        {                  
                            Vevoireklamacio_fejlec.d3 = Color.YELLOW;
                            Vevoireklamacio_fejlec.qr = Color.GREEN;
                            Vevoireklamacio_d2.fajlok.add(fajl.getName()+";"+fajl.getAbsolutePath()+";"+hozzaad(fajl.getName()));
                            mentes_gomb.setEnabled(true);
                            JOptionPane.showMessageDialog(null, "Email csatolva!", "Info", 1);
                        }
                     }

                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                    String hibauzenet = e.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                }
            }
            // Inform that the drop is complete
            event.dropComplete(true);
        }

        @Override
        public void dragEnter(DropTargetDragEvent event) {
        }

        @Override
        public void dragExit(DropTargetEvent event) {
        }

        @Override
        public void dragOver(DropTargetDragEvent event) {
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent event) {
        }

    }
}
