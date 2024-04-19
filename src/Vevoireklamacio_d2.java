import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.ExcelPicture;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
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
    static ArrayList<String> fajlok;
    private String epl = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\EPL_ÜRES_HUN.xlsx";
    
    
    public Vevoireklamacio_d2() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        fajlok = new ArrayList<String>();
        
        JLabel lblNewLabel = new JLabel("Mi a probléma?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(228, 64, 102, 14);
        add(lblNewLabel);
        
        miaproblema_mezo = new JTextArea();
        miaproblema_mezo.setLineWrap(true);
        miaproblema_mezo.setWrapStyleWord(true);
        miaproblema_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        JScrollPane gorgeto1 = new JScrollPane(miaproblema_mezo);
        gorgeto1.setBounds(340, 59, 260, 64);
        add(gorgeto1);
        
        JLabel lblNewLabel_1 = new JLabel("Hol detektálta");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(723, 64, 94, 14);
        add(lblNewLabel_1);
        
        holdetektalta_mezo = new JTextArea();
        holdetektalta_mezo.setLineWrap(true);
        holdetektalta_mezo.setWrapStyleWord(true);
        holdetektalta_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        JScrollPane gorgeto2 = new JScrollPane(holdetektalta_mezo);
        gorgeto2.setBounds(827, 59, 260, 64);
        add(gorgeto2);
        
        JLabel lblNewLabel_2 = new JLabel("Miért probléma?");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(228, 145, 102, 14);
        add(lblNewLabel_2);
        
        miertproblema_mezo = new JTextArea();
        miertproblema_mezo.setLineWrap(true);
        miertproblema_mezo.setWrapStyleWord(true);
        miertproblema_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        JScrollPane gorgeto3 = new JScrollPane(miertproblema_mezo);
        gorgeto3.setBounds(340, 140, 260, 64);
        add(gorgeto3);
        
        JLabel lblNewLabel_3 = new JLabel("Hogyan detektálta?");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(693, 145, 124, 14);
        add(lblNewLabel_3);
        
        hogyandetektalta_mezo = new JTextArea();
        hogyandetektalta_mezo.setLineWrap(true);
        hogyandetektalta_mezo.setWrapStyleWord(true);
        hogyandetektalta_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        JScrollPane gorgeto4 = new JScrollPane(hogyandetektalta_mezo);
        gorgeto4.setBounds(827, 140, 260, 64);
        add(gorgeto4);
        
        datum_mezo = new JTextField();
        datum_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
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
        db_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        db_mezo.setBounds(827, 232, 86, 20);
        add(db_mezo);
        db_mezo.setColumns(10);
        
        ki_mezo = new JTextField();
        ki_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        ki_mezo.setBounds(340, 280, 188, 20);
        add(ki_mezo);
        ki_mezo.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Ki detektálta?");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_6.setBounds(228, 283, 102, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Mi látható a képen?");
        lblNewLabel_7.setBounds(693, 283, 124, 14);
        add(lblNewLabel_7);
        
        kepleiras_mezo = new JTextField();
        kepleiras_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        kepleiras_mezo.setBounds(827, 280, 314, 20);
        add(kepleiras_mezo);
        kepleiras_mezo.setColumns(10);
        
        nok_kep = new JLabel("");
        nok_kep.addMouseMotionListener((MouseMotionListener) new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JLabel lbl = (JLabel) e.getSource();
                TransferHandler handle = lbl.getTransferHandler();
                handle.exportAsDrag(lbl, e, TransferHandler.COPY);
                System.out.println("Katt!");
            }
        });
        nok_kep.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
        /*nok_kep.addMouseListener (new MouseListener () {
            //override the method
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                try
                {
                    JFileChooser mentes_helye = new JFileChooser();
                    mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                    mentes_helye.setMultiSelectionEnabled(true);
                    mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    mentes_helye.showOpenDialog(mentes_helye);
                    File fajl = mentes_helye.getSelectedFile();
                    if(fajl != null)
                    {
                        fajlok.add(fajl.getName() +";"+fajl.getAbsolutePath()+";nok");
                        ImageIcon icon2 = null;
                        icon2 = new ImageIcon(fajl.getAbsolutePath());
                        Image icon = icon2.getImage();
                        int szelesseg = 0;
                        int magassag = 0;
                        if(icon2.getIconWidth() > icon2.getIconHeight())
                        {
                            float egyszazalek = icon2.getIconWidth() /100;
                            float szazalek = 465/egyszazalek;
                            //float szeles = icon2.getIconHeight()/100*szazalek;
                            float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = 465;
                            magassag = (int) magas;
                        }
                        else
                        {
                            float egyszazalek = icon2.getIconHeight() /100;
                            float szazalek = 338/egyszazalek;
                            float szeles = icon2.getIconWidth()/100*szazalek;
                            //float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = (int) szeles;
                            magassag = 338;
                        }
                        Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                        ImageIcon meretezett = new ImageIcon(resizedImage);                                                                             //kép képldányosítása
                        nok_kep.setIcon(meretezett);                                                                                                    //kép hozzáadása a képernyőhöz
                        modell.addRow(new Object[]{fajl.getName()});
                        table.setModel(modell);
                        Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                        Vevoireklamacio_V2.fejlec.hozzaad(fajl.getName());
                        //nok_kep.setBounds(135, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                        //nok_kep.setBounds(135, 334, 465, 338);
                    }
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
        });*/
        nok_kep.setBounds(135, 334, 465, 338);
        add(nok_kep);
        
        ok_kep = new JLabel("");
        ok_kep.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        ok_kep.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
        /*ok_kep.addMouseListener (new MouseListener () {
            //override the method
            public void mousePressed(MouseEvent e) {
                
        }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                try
                {
                    JFileChooser mentes_helye = new JFileChooser();
                    mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                    mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    mentes_helye.setMultiSelectionEnabled(true);
                    mentes_helye.showOpenDialog(mentes_helye);
                    File fajl = mentes_helye.getSelectedFile();
                    
                    if(fajl != null)
                    {
                        fajlok.add(fajl.getName() +";"+fajl.getAbsolutePath()+";ok");
                        ImageIcon icon2 = null;
                        icon2 = new ImageIcon(fajl.getAbsolutePath());
                        Image icon = icon2.getImage();  
                        int szelesseg = 0;
                        int magassag = 0;
                        if(icon2.getIconWidth() > icon2.getIconHeight())
                        {
                            float egyszazalek = icon2.getIconWidth() /100;
                            float szazalek = 465/egyszazalek;
                            //float szeles = icon2.getIconHeight()/100*szazalek;
                            float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = 465;
                            magassag = (int) magas;
                        }
                        else
                        {
                            float egyszazalek = icon2.getIconHeight() /100;
                            float szazalek = 338/egyszazalek;
                            float szeles = icon2.getIconWidth()/100*szazalek;
                            //float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = (int) szeles;
                            magassag = 338;
                        }
                        Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                        ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                        ok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                        modell.addRow(new Object[]{fajl.getName()});
                        table.setModel(modell);
                        Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                        Vevoireklamacio_V2.fejlec.hozzaad(fajl.getName());
                        //ok_kep.setBounds(676, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                    }                                      
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
        });*/
        ok_kep.setBounds(676, 334, 465, 338);
        add(ok_kep);
        
        MyDragDropListener myDragDropListener = new MyDragDropListener();
        MyDragDropListener2 myDragDropListener2 = new MyDragDropListener2();
        new DropTarget(nok_kep, myDragDropListener);
        new DropTarget(ok_kep, myDragDropListener2);
        
        JLabel lblNewLabel_10 = new JLabel("Egyéb fájl hozzáadása");
        lblNewLabel_10.setBounds(1175, 334, 142, 14);
        add(lblNewLabel_10);
        
        JButton fajl_gomb = new JButton("Hozzáad");
        fajl_gomb.addActionListener(new Hozzaad());
        fajl_gomb.setBounds(1327, 330, 89, 23);
        add(fajl_gomb);
        
        JLabel lblNewLabel_11 = new JLabel("Aktuálisan csatolt fájok listája");
        lblNewLabel_11.setBounds(1197, 397, 210, 14);
        add(lblNewLabel_11);
        
        table = new JTable();
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Fájl neve"});  
        table.setModel(modell);
        JScrollPane gorgeto = new JScrollPane(table);        
        gorgeto.setBounds(1172, 422, 244, 146);
        add(gorgeto);
        
        JLabel lblNewLabel_8 = new JLabel("Egypontos lecke készítése");
        lblNewLabel_8.setBounds(1236, 96, 169, 14);
        add(lblNewLabel_8);
        
        JButton epl_keszites = new JButton("EPL");
        epl_keszites.addActionListener(new EPL_keszito());
        epl_keszites.setBounds(1250, 123, 89, 23);
        add(epl_keszites);

    }
    
    class EPL_keszito implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                                                //egér mutató változtatása munka a háttérbenre
                Workbook workbook = new Workbook();
                workbook.loadFromFile(epl);
                workbook.setVersion(ExcelVersion.Version2016);
                Worksheet sheet = workbook.getWorksheets().get(0);
                sheet.getRange().get("B5").setText(String.valueOf(Vevoireklamacio_d0.vevo_box.getSelectedItem()));
                String cikkszamok = "";
                for(int szamlalo = 0; szamlalo < Vevoireklamacio_d0.table_1.getRowCount();szamlalo++)
                {
                    if(szamlalo == Vevoireklamacio_d0.table_1.getRowCount() - 1)
                    {
                        cikkszamok += Vevoireklamacio_d0.table_1.getValueAt(szamlalo, 0).toString();
                    }
                    else
                    {
                        cikkszamok += Vevoireklamacio_d0.table_1.getValueAt(szamlalo, 0).toString() +"\n";
                    }
                }
                sheet.getRange().get("J5").setText(cikkszamok);
                sheet.getRange().get("B10").setText(miaproblema_mezo.getText());
                sheet.getRange().get("B12").setText(miertproblema_mezo.getText());
                sheet.getRange().get("J12").setText(holdetektalta_mezo.getText());
                sheet.getRange().get("B14").setText(ki_mezo.getText());
                sheet.getRange().get("J14").setText(hogyandetektalta_mezo.getText());
                sheet.getRange().get("B16").setText(datum_mezo.getText());
                sheet.getRange().get("J16").setText(db_mezo.getText());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date date = new Date();
                sheet.getRange().get("D44").setText(formatter.format(date));
                sheet.getRange().get("L44").setText(Vevoireklamacio_d1.vezeto_mezo.getText());
                for(int szamlalo = 0; szamlalo < fajlok.size();szamlalo++)
                {
                    String[] darabol = fajlok.get(szamlalo).split(";");
                    if(darabol[2].equals("nok"))
                    {
                        ExcelPicture pic = sheet.getPictures().add(20, 2,darabol[1]);                        
                        ImageIcon icon2 = null;
                        icon2 = new ImageIcon(darabol[1]);
                        @SuppressWarnings("unused")
                        Image icon = icon2.getImage();  
                        int szelesseg = 0;
                        int magassag = 0;
                        if(icon2.getIconWidth() > icon2.getIconHeight())
                        {
                            float egyszazalek = icon2.getIconWidth() /100;
                            float szazalek = 458/egyszazalek;
                            //float szeles = icon2.getIconHeight()/100*szazalek;
                            float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = 458;
                            magassag = (int) magas;
                        }
                        else
                        {
                            float egyszazalek = icon2.getIconHeight() /100;
                            float szazalek = 397/egyszazalek;
                            float szeles = icon2.getIconWidth()/100*szazalek;
                            //float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = (int) szeles;
                            magassag = 397;
                        }
                        pic.setWidth(szelesseg);
                        pic.setHeight(magassag);
                    }
                    if(darabol[2].equals("ok"))
                    {
                        ExcelPicture pic = sheet.getPictures().add(20, 10,darabol[1]);
                        ImageIcon icon2 = null;
                        icon2 = new ImageIcon(darabol[1]);
                        @SuppressWarnings("unused")
                        Image icon = icon2.getImage();  
                        int szelesseg = 0;
                        int magassag = 0;
                        if(icon2.getIconWidth() > icon2.getIconHeight())
                        {
                            float egyszazalek = icon2.getIconWidth() /100;
                            float szazalek = 458/egyszazalek;
                            //float szeles = icon2.getIconHeight()/100*szazalek;
                            float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = 458;
                            magassag = (int) magas;
                        }
                        else
                        {
                            float egyszazalek = icon2.getIconHeight() /100;
                            float szazalek = 397/egyszazalek;
                            float szeles = icon2.getIconWidth()/100*szazalek;
                            //float magas = icon2.getIconHeight()/100*szazalek;
                            szelesseg = (int) szeles;
                            magassag = 397;
                        }
                        pic.setWidth(szelesseg);
                        pic.setHeight(magassag);
                    }
                }
                String hova = System.getProperty("user.home") + "\\Desktop\\EPL Vevőireklmáció ID_ "+ Vevoireklamacio_fejlec.id_mezo.getText() +".xlsx";
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
                
                String hova2 = System.getProperty("user.home") + "\\Ideiglenes Fájlok\\EPL Vevőireklmáció ID_ "+ Vevoireklamacio_fejlec.id_mezo.getText() +".xlsx";
                workbook.saveToFile(hova2, ExcelVersion.Version2016);
                fileStream = new FileInputStream(hova2);
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
                File epl = new File(hova2);
                fajlok.add(epl.getName()+";"+epl.getAbsolutePath()+";*");
                Foablak.frame.setCursor(null);                                                //egér mutató változtatása munka a háttérbenre
                JOptionPane.showMessageDialog(null, "Mentve az asztalra EPL Vevőireklmáció ID_ "+ Vevoireklamacio_fejlec.id_mezo.getText() +".xlsx néven", "Info", 1);
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
        
    class Hozzaad implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.setMultiSelectionEnabled(true);
                mentes_helye.showOpenDialog(mentes_helye);               
                File[] fajl = mentes_helye.getSelectedFiles();
                if(fajl != null)
                {
                    for(int szamlalo = 0; szamlalo < fajl.length;szamlalo++)
                    {
                        fajlok.add(fajl[szamlalo].getName() +";"+fajl[szamlalo].getAbsolutePath()+";*");
                        modell.addRow(new Object[]{fajl[szamlalo].getName()});
                        table.setModel(modell);
                        Vevoireklamacio_V2.fejlec.hozzaad(fajl[szamlalo].getName());
                    }
                    Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                }
                
            }
            catch (Exception e1) 
            {;
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kiírja a hibaüzenetet
            }
         }
    }
    
    public void mentes()
    {
        try 
        {
            SQA_SQL ment = new SQA_SQL();
            String sql = "update qualitydb.Vevoireklamacio_alap set miaproblema = '"+ miaproblema_mezo.getText() +"', holaproblema = '"+ holdetektalta_mezo.getText() +"', "
                    + "Miertproblema = '"+ miertproblema_mezo.getText() +"', hogydetektalta = '"+ hogyandetektalta_mezo.getText() +"', mikordetektalta = '"+ datum_mezo.getText() +"',"
                    + "hanydb ='"+ db_mezo.getText() +"', kidetektalta = '"+ ki_mezo.getText()+"', mivanakepen = '"+ kepleiras_mezo.getText() +"' "
                    + "where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            ment.mindenes(sql);
            for(int szamlalo = 0; szamlalo < fajlok.size(); szamlalo++)
            {
                String[] darabol = fajlok.get(szamlalo).split(";");
                sql = "select Fajl_neve from qualitydb.Vevoireklamacio_fajlok where Rek_id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"' and Fajl_neve = '"+ darabol[0] +"'";
                String[] letezik = ment.tombvissza_sajat(sql);
                if(letezik.length > 0){}
                else
                {
                    fajl_mentes(Vevoireklamacio_fejlec.id_mezo.getText(),darabol[0],darabol[1],darabol[2]);
                }
            }
            fajlok.clear();
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
    
    public void visszatolt()
    {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try 
        {          
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
            stmt = (Statement) conn.createStatement();
            String sql = "select miaproblema,holaproblema,Miertproblema,hogydetektalta,mikordetektalta,hanydb,kidetektalta,mivanakepen from qualitydb.Vevoireklamacio_alap where id = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if(rs.next())
            {
                miaproblema_mezo.setText(rs.getString(1));
                holdetektalta_mezo.setText(rs.getString(2));
                miertproblema_mezo.setText(rs.getString(3));
                hogyandetektalta_mezo.setText(rs.getString(4));
                datum_mezo.setText(rs.getString(5));
                db_mezo.setText(rs.getString(6));
                ki_mezo.setText(rs.getString(7));
                kepleiras_mezo.setText(rs.getString(8));           
            }       
        
            File fajl = new File(System.getProperty("user.home") + "\\Ideiglenes Fájlok\\");
            if(fajl.exists()) {}
            else
            {
                fajl.mkdir();
            }
            File[] torlendofajlok = fajl.listFiles();
            for(int szamlalo = 0; szamlalo < torlendofajlok.length; szamlalo++)
            {
                torlendofajlok[szamlalo].delete();
            }
            ps= conn.prepareStatement("SELECT Fajl_neve, Fajl,Tipus FROM qualitydb.Vevoireklamacio_fajlok WHERE Rek_ID = '"+ Vevoireklamacio_fejlec.id_mezo.getText() +"'");        //Datum = '"+ datum +"' and Cikkszam = '"+ cikkszam +"'"
            ResultSet rset = ps.executeQuery();         
            Blob blob;
            byte b[];
            FileOutputStream fs=null;
            ok_kep.setIcon(null);
            nok_kep.setIcon(null);
            while(rset.next())
            {        
                File f = new File(System.getProperty("user.home") + "\\Ideiglenes Fájlok\\"+ rset.getString(1));
                f.getParentFile().mkdirs(); 
                f.createNewFile();  
                fs = new FileOutputStream(f);
                blob = rset.getBlob("Fajl");
                b = blob.getBytes(1, (int)blob.length());
                fs.write(b);
                fs.close();
                String tipus = "*";
                if(rset.getString(3).equals("ok"))
                {
                    ImageIcon icon2 = null;
                    icon2 = new ImageIcon(f.getAbsolutePath());
                    Image icon = icon2.getImage();
                    int szelesseg = 0;
                    int magassag = 0;
                    if(icon2.getIconWidth() > icon2.getIconHeight())
                    {
                        float egyszazalek = icon2.getIconWidth() /100;
                        float szazalek = 458/egyszazalek;
                        //float szeles = icon2.getIconHeight()/100*szazalek;
                        float magas = icon2.getIconHeight()/100*szazalek;
                        szelesseg = 458;
                        magassag = (int) magas;
                    }
                    else
                    {
                        float egyszazalek = icon2.getIconHeight() /100;
                        float szazalek = 397/egyszazalek;
                        float szeles = icon2.getIconWidth()/100*szazalek;
                        //float magas = icon2.getIconHeight()/100*szazalek;
                        szelesseg = (int) szeles;
                        magassag = 397;
                    }
                    Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                    ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                    ok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                    //ok_kep.setBounds(676, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                    tipus = "ok";
                }
                if(rset.getString(3).equals("nok"))
                {
                    ImageIcon icon2 = null;
                    icon2 = new ImageIcon(f.getAbsolutePath());
                    Image icon = icon2.getImage();  
                    int szelesseg = 0;
                    int magassag = 0;
                    if(icon2.getIconWidth() > icon2.getIconHeight())
                    {
                        float egyszazalek = icon2.getIconWidth() /100;
                        float szazalek = 458/egyszazalek;
                        //float szeles = icon2.getIconHeight()/100*szazalek;
                        float magas = icon2.getIconHeight()/100*szazalek;
                        szelesseg = 458;
                        magassag = (int) magas;
                    }
                    else
                    {
                        float egyszazalek = icon2.getIconHeight() /100;
                        float szazalek = 397/egyszazalek;
                        float szeles = icon2.getIconWidth()/100*szazalek;
                        //float magas = icon2.getIconHeight()/100*szazalek;
                        szelesseg = (int) szeles;
                        magassag = 397;
                    }
                    Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                    ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                    nok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                    //nok_kep.setBounds(135, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                    tipus = "nok";
                }
                
                ImageIcon icon = null;
                String[] fajltipus = rset.getString(1).split("\\.");
                if(fajltipus[1].equals("msg"))
                {
                    icon = new ImageIcon(Vevoireklamacio_fejlec.outlook_kep);
                    tipus = "email";
                }
                else if(fajltipus[1].equals("xlsx") || fajltipus[1].equals("xls"))
                {
                    icon = new ImageIcon(Vevoireklamacio_fejlec.excel_kep);
                }
                else if(fajltipus[1].equals("pdf"))
                {
                    icon = new ImageIcon(Vevoireklamacio_fejlec.pdf_kep);
                }
                else if(fajltipus[1].equals("jpg"))
                {
                    icon = new ImageIcon(Vevoireklamacio_fejlec.kep_kep);
                }
                else if(fajltipus[1].equals("png"))
                {
                    icon = new ImageIcon(Vevoireklamacio_fejlec.kep_kep);
                }
                fajlok.add(rset.getString(1)+";"+f.getAbsolutePath()+";"+tipus);
                Vevoireklamacio_d0.modell.addRow(new Object[]{icon,rset.getString(1)});
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
            }
            stmt.close();
            conn.close();        
        }          
        catch (Exception e) 
        {
            e.printStackTrace();
            String hibauzenet = e.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                     //kivétel esetén kiírja a hibaüzenetet
        } finally 
        {
           try 
           {
              if (stmt != null)
                  stmt.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
               se.printStackTrace();
               String hibauzenet = se.toString();
               Email hibakuldes = new Email();
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
               JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                //kivétel esetén kiírja a hibaüzenetet
           }  
        }
        //JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
    }
    
    public void fajl_mentes(String Rek_ID, String Fajl_neve, String fajl, String tipus)
    {   
        Connection conn = null;
        PreparedStatement stmt = null;
        try 
        {           
            Class.forName("com.mysql.cj.jdbc.Driver");                                //Driver meghívása   
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");                           //kapcsolat létrehozása                                                                                                          //csatlakozás
            
            File image = new File(fajl);
            FileInputStream fis = new FileInputStream (image);
            String sql = "INSERT INTO qualitydb.Vevoireklamacio_fajlok(Rek_ID, Fajl_neve, fajl, tipus) VALUES(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Rek_ID);
            stmt.setString(2, Fajl_neve);
            stmt.setBinaryStream (3, fis, (int) image.length());
            stmt.setString(4, tipus);
            stmt.executeUpdate();                                                                                                                 //sql utasítás végrehajtása
        }         
        catch (Exception e) 
        {
           e.printStackTrace();
           String hibauzenet = e.toString();
           Email hibakuldes = new Email();
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", getClass()+" "+ hibauzenet);
           JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
        } 
        finally                                                                     //finally rész mindenképpen lefut, hogy hiba esetén is lezárja a kacsolatot
        {
           try 
           {
              if (stmt != null)
                 conn.close();
           } 
           catch (SQLException se) {}
           try 
           {
              if (conn != null)
                 conn.close();
           } 
           catch (SQLException se) 
           {
              se.printStackTrace();
           }  
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
                            fajlok.add(fajl.getName() +";"+fajl.getAbsolutePath()+";nok");
                            ImageIcon icon2 = null;
                            icon2 = new ImageIcon(fajl.getAbsolutePath());
                            Image icon = icon2.getImage();
                            int szelesseg = 0;
                            int magassag = 0;
                            if(icon2.getIconWidth() > icon2.getIconHeight())
                            {
                                float egyszazalek = icon2.getIconWidth() /100;
                                float szazalek = 465/egyszazalek;
                                //float szeles = icon2.getIconHeight()/100*szazalek;
                                float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = 465;
                                magassag = (int) magas;
                            }
                            else
                            {
                                float egyszazalek = icon2.getIconHeight() /100;
                                float szazalek = 338/egyszazalek;
                                float szeles = icon2.getIconWidth()/100*szazalek;
                                //float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = (int) szeles;
                                magassag = 338;
                            }
                            Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                            ImageIcon meretezett = new ImageIcon(resizedImage);                                                                             //kép képldányosítása
                            nok_kep.setIcon(meretezett);                                                                                                    //kép hozzáadása a képernyőhöz
                            modell.addRow(new Object[]{fajl.getName()});
                            table.setModel(modell);
                            Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                            Vevoireklamacio_V2.fejlec.hozzaad(fajl.getName());
                            //nok_kep.setBounds(135, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                            //nok_kep.setBounds(135, 334, 465, 338);
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
    
    class MyDragDropListener2 implements DropTargetListener {

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
                        //System.out.println("Az ok képhozzáadás fut");
                        // Get all of the dropped files
                        List<?> files = (List<?>) transferable.getTransferData(flavor);
                        File fajl = new File(files.toString().substring(1,files.toString().length()-1));
                        if(fajl != null)
                        {
                            fajlok.add(fajl.getName() +";"+fajl.getAbsolutePath()+";ok");
                            ImageIcon icon2 = null;
                            icon2 = new ImageIcon(fajl.getAbsolutePath());
                            Image icon = icon2.getImage();
                            int szelesseg = 0;
                            int magassag = 0;
                            if(icon2.getIconWidth() > icon2.getIconHeight())
                            {
                                float egyszazalek = icon2.getIconWidth() /100;
                                float szazalek = 465/egyszazalek;
                                //float szeles = icon2.getIconHeight()/100*szazalek;
                                float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = 465;
                                magassag = (int) magas;
                            }
                            else
                            {
                                float egyszazalek = icon2.getIconHeight() /100;
                                float szazalek = 338/egyszazalek;
                                float szeles = icon2.getIconWidth()/100*szazalek;
                                //float magas = icon2.getIconHeight()/100*szazalek;
                                szelesseg = (int) szeles;
                                magassag = 338;
                            }
                            Image resizedImage = icon.getScaledInstance(szelesseg, magassag,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                            ImageIcon meretezett = new ImageIcon(resizedImage);                                                                             //kép képldányosítása
                            ok_kep.setIcon(meretezett);                                                                                                    //kép hozzáadása a képernyőhöz
                            modell.addRow(new Object[]{fajl.getName()});
                            table.setModel(modell);
                            Vevoireklamacio_fejlec.mentes_gomb.setEnabled(true);
                            Vevoireklamacio_V2.fejlec.hozzaad(fajl.getName());
                            //nok_kep.setBounds(135, 334, icon2.getIconWidth()/3, icon2.getIconHeight()/3);
                            //nok_kep.setBounds(135, 334, 465, 338);
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
