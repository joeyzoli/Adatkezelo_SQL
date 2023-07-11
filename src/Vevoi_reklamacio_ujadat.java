import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vevoi_reklamacio_ujadat extends JPanel 
{
    private JTextField cikk_mezo;
    private JTextField valtozat_mezo;
    private JTextField megnevezes_mezo;
    private String cikkszamok = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Vevői cikkszámok.csv";
    private JComboBox<String> projekt_box;
    private ComboBox combobox_tomb = new ComboBox();

    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_ujadat() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Új vevői cikkszám felvitele");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setBounds(464, 63, 213, 14);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setBounds(427, 142, 46, 14);
        add(lblNewLabel_1);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                              //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(572, 138, 161, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Cikkszám");
        lblNewLabel_2.setBounds(427, 203, 67, 14);
        add(lblNewLabel_2);
        
        cikk_mezo = new JTextField();
        cikk_mezo.setBounds(572, 200, 161, 20);
        add(cikk_mezo);
        cikk_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Változatszám");
        lblNewLabel_3.setBounds(427, 263, 84, 14);
        add(lblNewLabel_3);
        
        valtozat_mezo = new JTextField();
        valtozat_mezo.setBounds(572, 260, 46, 20);
        add(valtozat_mezo);
        valtozat_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Megnevezés");
        lblNewLabel_4.setBounds(427, 325, 84, 14);
        add(lblNewLabel_4);
        
        megnevezes_mezo = new JTextField();
        megnevezes_mezo.setBounds(572, 322, 269, 20);
        add(megnevezes_mezo);
        megnevezes_mezo.setColumns(10);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Ujcikkszam());
        mentes_gomb.setBounds(513, 396, 89, 23);
        add(mentes_gomb);
        
        setBackground(Foablak.hatter_szine);

    }
    
    class Ujcikkszam implements ActionListener                                                                                         //felvisz gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(cikkszamok, true), "UTF-8"));
                writer.append(cikk_mezo.getText() +","+ valtozat_mezo.getText() +","+ megnevezes_mezo.getText() +","+ String.valueOf(projekt_box.getSelectedItem()) + "\n");
                writer.flush();
                writer.close();
                //p.println(ellenor_neve.getText());
                JOptionPane.showMessageDialog(null, "Új cikkszám felvétele kész!", "Infó", 1);
            } 
            catch (IOException e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
}
