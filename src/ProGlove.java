import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ProGlove extends JPanel 
{
    private JTextField idopont;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private ComboBox combobox_tomb;
    private JComboBox<String> nev;
    private JComboBox<String> ell_helye;
    private JComboBox<String> termek;
    private JComboBox<String> folyamat;
    private JComboBox<String> hibakod;
    private final String infohelye = "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Proglove_info.xlsx";
    private JLabel kepkeret;
    private JTextArea textArea;
    static JTable table_1;
    static JScrollPane scrollPane;

    /**
     * Create the panel.
     */
    public ProGlove() 
    {
        setLayout(null);
        combobox_tomb = new ComboBox();
        JLabel lblNewLabel = new JLabel("Időpont");
        lblNewLabel.setBounds(148, 50, 46, 14);
        add(lblNewLabel);
        
        idopont = new JTextField();
        idopont.setBounds(148, 75, 113, 20);
        add(idopont);
        idopont.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Név");
        lblNewLabel_1.setBounds(309, 50, 46, 14);
        add(lblNewLabel_1);
        
        nev = new JComboBox<String>(combobox_tomb.getCombobox_ellenorok());              //combobox_tomb.getCombobox_ellenorok()
        nev.setBounds(309, 74, 153, 22);
        add(nev);
        
        JLabel lblNewLabel_2 = new JLabel("Ellenőrzés helye");
        lblNewLabel_2.setBounds(148, 106, 86, 14);
        add(lblNewLabel_2);
        
        ell_helye = new JComboBox<String>(combobox_tomb.getCombobox_hiba());            //combobox_tomb.getCombobox_hiba()
        ell_helye.setBounds(148, 131, 113, 22);
        add(ell_helye);
        
        JLabel lblNewLabel_3 = new JLabel("Termék");
        lblNewLabel_3.setBounds(309, 107, 46, 14);
        add(lblNewLabel_3);
        
        termek = new JComboBox<String>(combobox_tomb.getCombobox_proglove());               //combobox_tomb.getCombobox_proglove()
        termek.setBounds(309, 131, 153, 22);
        termek.addActionListener(new Elem_valaszto());
        add(termek);
        
        JLabel lblNewLabel_4 = new JLabel("Ellenörzésre váró");
        lblNewLabel_4.setBounds(148, 164, 86, 14);
        add(lblNewLabel_4);
        
        textField = new JTextField();
        textField.setBounds(148, 189, 46, 20);
        textField.addKeyListener(new Enter());
        add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_5 = new JLabel("Szükséges mintavételi db");
        lblNewLabel_5.setBounds(309, 164, 113, 14);
        add(lblNewLabel_5);
        
        textField_1 = new JTextField();
        textField_1.setBounds(309, 189, 46, 20);
        textField_1.setEditable(false);
        add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("ProGlove folyamat ellenőrzés és végátvétel");
        lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel_6.setBounds(444, 11, 289, 14);
        add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Átvételi adatok");
        lblNewLabel_7.setBounds(148, 220, 113, 14);
        add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("Sum ellenörzött");
        lblNewLabel_8.setBounds(309, 220, 64, 14);
        add(lblNewLabel_8);
        
        textField_2 = new JTextField();
        textField_2.setBounds(383, 217, 39, 20);
        add(textField_2);
        textField_2.setColumns(10);
        
        table = new JTable();
        table.setBounds(148, 245, 274, 85);
        add(table);
        
        JLabel lblNewLabel_9 = new JLabel("Hiba arány:");
        lblNewLabel_9.setBounds(148, 341, 64, 14);
        add(lblNewLabel_9);
        
        textField_3 = new JTextField();
        textField_3.setBounds(222, 338, 29, 20);
        textField_3.setEditable(false);
        add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Hibás db:");
        lblNewLabel_10.setBounds(309, 341, 46, 14);
        add(lblNewLabel_10);
        
        textField_4 = new JTextField();
        textField_4.setBounds(365, 338, 46, 20);
        textField_4.setEditable(false);
        add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblNewLabel_11 = new JLabel("Hibás alaktrész");
        lblNewLabel_11.setBounds(148, 366, 86, 14);
        add(lblNewLabel_11);
        
        JLabel lblNewLabel_12 = new JLabel("Folyamat");
        lblNewLabel_12.setBounds(148, 391, 64, 14);
        add(lblNewLabel_12);
        
        JLabel lblNewLabel_13 = new JLabel("Hibakód");
        lblNewLabel_13.setBounds(148, 416, 46, 14);
        add(lblNewLabel_13);
        
        textField_5 = new JTextField();
        textField_5.setBounds(244, 363, 86, 20);
        add(textField_5);
        textField_5.setColumns(10);
        
        folyamat = new JComboBox<String>(combobox_tomb.getCombobox_folyamat());
        folyamat.setBounds(244, 387, 167, 22);
        add(folyamat);
        
        hibakod = new JComboBox<String>(combobox_tomb.getCombobox_hibakodok());              //combobox_tomb.getCombobox_hibakodok()
        hibakod.setBounds(244, 412, 167, 22);
        add(hibakod);
        
        JLabel lblNewLabel_14 = new JLabel("Megjegyzés");
        lblNewLabel_14.setBounds(148, 441, 86, 14);
        add(lblNewLabel_14);
        
        textField_6 = new JTextField();
        textField_6.setBounds(148, 466, 263, 20);
        add(textField_6);
        textField_6.setColumns(10);
        
        JButton torles = new JButton("Törlés");
        torles.setBounds(148, 509, 64, 23);
        add(torles);
        
        JLabel lblNewLabel_15 = new JLabel("Hiba:");
        lblNewLabel_15.setBounds(232, 513, 29, 14);
        add(lblNewLabel_15);
        
        textField_7 = new JTextField();
        textField_7.setBounds(269, 510, 29, 20);
        add(textField_7);
        textField_7.setColumns(10);
        
        JButton mentes = new JButton("Mentés");
        mentes.setBounds(333, 509, 89, 23);
        add(mentes);
        
        JLabel lblNewLabel_16 = new JLabel("Informásió az ellenőrzött termékről");
        lblNewLabel_16.setBounds(528, 50, 205, 14);
        add(lblNewLabel_16);
        
        table_1 = new JTable();
        table_1.setBounds(528, 75, 516, 110);
        scrollPane = new JScrollPane(table_1);
        //scrollPane.setBounds(528, 75, 516, 110);
        add(table_1);
        add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBounds(528, 610, 369, 63);
        add(textArea);
        
        JButton info = new JButton("Egyéb infó");
        info.setBounds(918, 640, 89, 23);
        add(info);
        
        kepkeret = new JLabel("");
        kepkeret.setBounds(527, 190, 520, 410);
        
        add(kepkeret);
        setBackground(Foablak.hatter_szine);

    }
    
    class Elem_valaszto implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String valasztott = String.valueOf(termek.getSelectedItem());
                Workbook excel = new Workbook();
                excel.loadFromFile(infohelye);
                Worksheet sheet = excel.getWorksheets().get(0);
                DataTable dataTable = sheet.exportDataTable();
                
                for (int szamlalo = 0; szamlalo < dataTable.getRows().size(); szamlalo++) 
                {
                    if(valasztott.contains(dataTable.getRows().get(szamlalo).getString(0)))
                    {
                        ImageIcon icon2 = new ImageIcon(dataTable.getRows().get(szamlalo).getString(6));
                        Image icon = icon2.getImage();  
                        Image resizedImage = icon.getScaledInstance(520, 410,  java.awt.Image.SCALE_SMOOTH);
                        ImageIcon meretezett = new ImageIcon(resizedImage);
                        kepkeret.setIcon(meretezett);
                        textArea.setText(dataTable.getRows().get(szamlalo).getString(5));
                    }
                }
                SQL sql = new SQL();
                String[] koztes = String.valueOf(termek.getSelectedItem()).split(",");
                sql.top_hiba(koztes[0]);
                        
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
    
    class Enter implements KeyListener                                                                                                 //billentyűzet figyelő eseménykezelő
    {
        public void keyPressed (KeyEvent e) 
        {    
            int ellenorizendo = Integer.parseInt(textField.getText());
            textField_1.setText(String.valueOf(ellenorizendo/3));
         
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
