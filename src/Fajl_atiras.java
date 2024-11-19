import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Fajl_atiras extends JPanel {
    private JTextField fajlvege_mezo;
    private JTextField mit_mezo;
    private JTextField mire_mezo;

    /**
     * Create the panel.
     */
    public Fajl_atiras() {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Szöveges fájlok átírása");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(456, 83, 231, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("Fájl végződés");
        lblNewLabel_2.setBounds(408, 138, 89, 14);
        add(lblNewLabel_2);
        
        fajlvege_mezo = new JTextField();
        fajlvege_mezo.setBounds(557, 135, 86, 20);
        add(fajlvege_mezo);
        fajlvege_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Mit");
        lblNewLabel_3.setBounds(408, 182, 46, 14);
        add(lblNewLabel_3);
        
        mit_mezo = new JTextField();
        mit_mezo.setBounds(557, 179, 86, 20);
        add(mit_mezo);
        mit_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Mire");
        lblNewLabel_4.setBounds(408, 220, 46, 14);
        add(lblNewLabel_4);
        
        mire_mezo = new JTextField();
        mire_mezo.setBounds(557, 217, 86, 20);
        add(mire_mezo);
        mire_mezo.setColumns(10);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Ertek_atiras());
        start_gomb.setBounds(491, 276, 89, 23);
        add(start_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Ertek_atiras implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String hely = "";
                File mappa;
                File[] fajlok;
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop"));
                mentes_helye.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                hely = fajl.getAbsolutePath();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                mappa = new File(hely);                                         //mappa beolvasása
                
                FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                {
                    
                    @Override
                    public boolean accept(File f, String name) 
                    {
                                                                                                        // csak az xlsx fájlokat listázza ki 
                        return name.endsWith("."+ fajlvege_mezo.getText());  
                    }
                };
                
                fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                {                   
                    try 
                    {
                        Path path = Paths.get(fajlok[szamlalo].getAbsolutePath());
                        Charset charset = StandardCharsets.UTF_8;

                        String content = new String(Files.readAllBytes(path), charset);
                        content = content.replaceAll(mit_mezo.getText(), mire_mezo.getText());
                        Files.write(path, content.getBytes(charset));
                    }           
                    catch(Exception e1)                          //kivételkezelés
                    {
                        System.out.println(e1);
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                    }                    
                    
                }
                JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
                Foablak.frame.setCursor(null);
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }                                         
         }
    }
}
