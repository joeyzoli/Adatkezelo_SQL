import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vevoi_reklamacio_lekerdezes extends JPanel 
{
    private JTextField datumtol;
    private JTextField datumig;
    static JTable table;
    private ComboBox combobox;
    private JComboBox<String> projekt_box;
    private JRadioButton lezart_gomb;
    private JRadioButton nyitott_gomb;
    private SQL lekerdezes = new SQL();
    private JTextField id_mezo;
    private final String excelhelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\reklamáció adatbázis.xlsx";
    /**
     * Create the panel.
     */
    public Vevoi_reklamacio_lekerdezes() 
    {
        this.setPreferredSize(new Dimension(1160, 700));
        setLayout(null);
        
        combobox = new ComboBox();
        JLabel lblNewLabel = new JLabel("Vevői reklamációk lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(494, 11, 264, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Projekt");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(464, 57, 46, 14);
        add(lblNewLabel_1);
        
        projekt_box = new JComboBox<String>(combobox.getCombobox(ComboBox.projekt));                   //combobox.getCombobox2(ComboBox.projekt)
        projekt_box.setBounds(520, 53, 172, 22);
        add(projekt_box);
        
        JLabel lblNewLabel_2 = new JLabel("Dátum -tól");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(420, 93, 90, 14);
        add(lblNewLabel_2);
        
        datumtol = new JTextField();
        datumtol.setBounds(520, 90, 86, 20);
        datumtol.setText("2022.08.01");
        add(datumtol);
        datumtol.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Dátim -ig");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setBounds(430, 124, 80, 14);
        add(lblNewLabel_3);
        
        datumig = new JTextField();
        datumig.setBounds(520, 121, 86, 20);
        add(datumig);
        datumig.setColumns(10);
        
        lezart_gomb = new JRadioButton("Nyitott");
        lezart_gomb.setBounds(481, 220, 66, 14);
        add(lezart_gomb);
        
        nyitott_gomb = new JRadioButton("Lezárt");
        nyitott_gomb.setBounds(587, 216, 80, 23);
        add(nyitott_gomb);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.setBounds(520, 262, 89, 23);
        keres_gomb.addActionListener(new Kereses());
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(49, 314, 1070, 200);
        add(pane);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(520, 525, 89, 23);
        add(excel_gomb);
        
        id_mezo = new JTextField();
        id_mezo.setBounds(520, 152, 86, 20);
        id_mezo.addKeyListener(new Enter());
        add(id_mezo);
        id_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("ID");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setBounds(464, 155, 46, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Gafikonok");
        lblNewLabel_5.setBounds(430, 529, 80, 14);
        add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("6D");
        lblNewLabel_6.setBounds(430, 573, 46, 14);
        add(lblNewLabel_6);
        
        JButton d_gomb = new JButton("6D excel");
        d_gomb.setBounds(520, 569, 89, 23);
        d_gomb.addActionListener(new D_gyarto());
        add(d_gomb);
        
        JLabel lblNewLabel_7 = new JLabel("Képek");
        lblNewLabel_7.setBounds(430, 617, 46, 14);
        add(lblNewLabel_7);
        
        JButton mentes_gomb = new JButton("Mentés");
        mentes_gomb.addActionListener(new Kepmentes());
        mentes_gomb.setBounds(520, 615, 89, 23);
        add(mentes_gomb);
        
        JLabel lblNewLabel_8 = new JLabel("Excel");
        lblNewLabel_8.setBounds(430, 655, 46, 14);
        add(lblNewLabel_8);
        
        JButton excelmentes = new JButton("Mentés");
        excelmentes.setBounds(520, 651, 89, 23);
        excelmentes.addActionListener(new Excelmentes());
        add(excelmentes);
        ido();
        
        setBackground(Foablak.hatter_szine);
    }
    
    class Kereses implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                                
                lekerdezes.vevoi_lekerdezes(String.valueOf(projekt_box.getSelectedItem()), datumtol.getText(), datumig.getText(), nyitott, lezart, id_mezo.getText());
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő, kiszámolja mennyit kell ellenőrizni
    {
        public void keyPressed (KeyEvent e) 
        {    
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER)                                                                                               //ha az entert nyomják le akkor hívódik meg
            {
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                                
                lekerdezes.vevoi_lekerdezes(String.valueOf(projekt_box.getSelectedItem()), datumtol.getText(), datumig.getText(), nyitott, lezart, id_mezo.getText());
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
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String nyitott = "";
                String lezart = "";
                if(lezart_gomb.isSelected())
                {
                    lezart = "igen";
                }
                else
                {
                    lezart = "nem";
                }
                
                if(nyitott_gomb.isSelected())
                {
                    nyitott = "igen";
                }
                else
                {
                    nyitott = "nem";
                }
                lekerdezes.vevoi_lekerdezes_excel(String.valueOf(projekt_box.getSelectedItem()), datumtol.getText(), datumig.getText(), nyitott, lezart);
                Foablak.frame.setCursor(null);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class D_gyarto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.vevoi_6d(table.getValueAt(0, 1).toString(), table.getValueAt(0, 3).toString(), excelhelye);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Kepmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.vevoi_kepmentes(table.getValueAt(0, 1).toString(), table.getValueAt(0, 3).toString());
                JOptionPane.showMessageDialog(null, "Kép/ek mentve az asztalra", "Info", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Excelmentes implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                lekerdezes.vevoi_excelmentes(table.getValueAt(0, 1).toString(), table.getValueAt(0, 3).toString());
                JOptionPane.showMessageDialog(null, "Excel/ek mentve az asztalra", "Info", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    public void ido()                                                                   //a pontos dátu meghatározására szolgáló függvény
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        datumig.setText(formatter.format(date));                                        //az aktuális dátumot hozzáadja az időpont mezőhöz
    }
}
