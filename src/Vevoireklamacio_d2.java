import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    
    public Vevoireklamacio_d2() {
        setLayout(null);
        setBackground(Foablak.hatter_szine);
        
        fajlok = new ArrayList<String>();
        
        JLabel lblNewLabel = new JLabel("Mi a probléma?");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(228, 64, 102, 14);
        add(lblNewLabel);
        
        miaproblema_mezo = new JTextArea();
        miaproblema_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        miaproblema_mezo.setBounds(340, 59, 260, 64);
        add(miaproblema_mezo);
        
        JLabel lblNewLabel_1 = new JLabel("Hol detektálta");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(723, 64, 94, 14);
        add(lblNewLabel_1);
        
        holdetektalta_mezo = new JTextArea();
        holdetektalta_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        holdetektalta_mezo.setBounds(827, 59, 260, 64);
        add(holdetektalta_mezo);
        
        JLabel lblNewLabel_2 = new JLabel("Miért probléma?");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(228, 145, 102, 14);
        add(lblNewLabel_2);
        
        miertproblema_mezo = new JTextArea();
        miertproblema_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        miertproblema_mezo.setBounds(340, 140, 260, 64);
        add(miertproblema_mezo);
        
        JLabel lblNewLabel_3 = new JLabel("Hogyan detektálta?");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(693, 145, 124, 14);
        add(lblNewLabel_3);
        
        hogyandetektalta_mezo = new JTextArea();
        hogyandetektalta_mezo.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        hogyandetektalta_mezo.setBounds(827, 140, 260, 64);
        add(hogyandetektalta_mezo);
        
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
        nok_kep.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        nok_kep.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
        nok_kep.addMouseListener (new MouseListener () {
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
                        Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                        ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                        nok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                        modell.addRow(new Object[]{fajl.getName()});
                        table.setModel(modell);
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
        });
        nok_kep.setBounds(135, 334, 465, 338);
        add(nok_kep);
        
        ok_kep = new JLabel("");
        ok_kep.addKeyListener(new Vevoireklamacio_fejlec.Valtozas_figyelo());
        ok_kep.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
        ok_kep.addMouseListener (new MouseListener () {
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
                        Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                        ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                        ok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                        modell.addRow(new Object[]{fajl.getName()});
                        table.setModel(modell);
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
        });
        ok_kep.setBounds(676, 334, 465, 338);
        add(ok_kep);
        
        JLabel lblNewLabel_10 = new JLabel("Egyéb fájl hozzáadása");
        lblNewLabel_10.setBounds(1175, 334, 142, 14);
        add(lblNewLabel_10);
        
        JButton fajl_gomb = new JButton("Hozzáad");
        fajl_gomb.addActionListener(new Hozzaad());
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
    
    public void okkep(String fajlhelye)
    {
        
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
                mentes_helye.showOpenDialog(mentes_helye);
                mentes_helye.setMultiSelectionEnabled(true);
                File[] fajl = mentes_helye.getSelectedFiles();
                if(fajl != null)
                {
                    for(int szamlalo = 0; szamlalo < fajl.length;szamlalo++)
                    {
                        fajlok.add(fajl[szamlalo].getName() +";"+fajl[szamlalo].getAbsolutePath()+";*");
                        modell.addRow(new Object[]{fajl[szamlalo].getName()});
                        table.setModel(modell);
                    }
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
           try 
           {
              Class.forName("com.mysql.cj.jdbc.Driver");
           } 
           catch (Exception e) 
           {
              System.out.println(e);
              String hibauzenet2 = e.toString();
              JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
        }
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
                Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                ok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
                tipus = "ok";
            }
            if(rset.getString(3).equals("nok"))
            {
                ImageIcon icon2 = null;
                icon2 = new ImageIcon(f.getAbsolutePath());
                Image icon = icon2.getImage();  
                Image resizedImage = icon.getScaledInstance(icon2.getIconWidth()/3, icon2.getIconHeight()/3,  java.awt.Image.SCALE_SMOOTH);                            //betöltendő kép méretezés
                ImageIcon meretezett = new ImageIcon(resizedImage);                                                             //kép képldányosítása
                nok_kep.setIcon(meretezett);                                                                                   //kép hozzáadása a képernyőhöz
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
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
               hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
           hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
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
}
