import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class OQC_adatok extends JPanel {
    private JTable table;
    private JTable table_2;
    private JTable table_3;
    private Dimension meretek = new Dimension(1100, 850);
    private DefaultTableModel modell;
    private DefaultTableModel modell2;
    private DefaultTableModel modell3;
    private JTextField dobozdb_mezo;
    private JTextField termekdb_mezo;
    

    /**
     * Create the panel.
     */
    public OQC_adatok() {
        setLayout(null);
        setPreferredSize(new Dimension(1156, 850));
        JLabel lblNewLabel = new JLabel("OQC riport készítés");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
        lblNewLabel.setBounds(535, 11, 218, 23);
        add(lblNewLabel);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(37, 121, 1113, 310);
        add(gorgeto);
        
        modell = new DefaultTableModel();
        modell.setColumnIdentifiers(new Object[]{"Termék", "Cikkszám", "Cikk megnevezés", "P Vonalkód", "Elérhető menny.", "Raktárhely száma","Raktár", "OQC-zett", "Zárolt"});
        table.setModel(modell);
        
        JLabel lblNewLabel_1 = new JLabel("Termék választás");
        lblNewLabel_1.setBounds(37, 59, 108, 14);
        add(lblNewLabel_1);
        
        JComboBox<String> termek_box = new JComboBox<String>();
        termek_box.setBounds(144, 55, 269, 22);
        add(termek_box);
        
        JLabel lblNewLabel_2 = new JLabel("Doboz db");
        lblNewLabel_2.setBounds(462, 59, 58, 14);
        add(lblNewLabel_2);
        
        dobozdb_mezo = new JTextField();
        dobozdb_mezo.setBounds(535, 56, 46, 20);
        add(dobozdb_mezo);
        dobozdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Termék db");
        lblNewLabel_3.setBounds(622, 59, 63, 14);
        add(lblNewLabel_3);
        
        termekdb_mezo = new JTextField();
        termekdb_mezo.setBounds(695, 56, 46, 20);
        add(termekdb_mezo);
        termekdb_mezo.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("OQC hiba adatok");
        lblNewLabel_4.setBounds(37, 442, 120, 14);
        add(lblNewLabel_4);
        
        table_2 = new JTable();
        JScrollPane gorgeto2 = new JScrollPane(table_2);
        gorgeto2.setBounds(37, 467, 664, 158);
        add(gorgeto2);
        
        modell2 = new DefaultTableModel();
        modell2.setColumnIdentifiers(new Object[]{"Szériaszám termék", "Hibakód", "Hiba", "Hibacsoport", "Megjegyzés","Hiba kategória"});
        table_2.setModel(modell2);
        
        JLabel lblNewLabel_5 = new JLabel("Kiajánlásra váró dobozok");
        lblNewLabel_5.setBounds(837, 442, 168, 14);
        add(lblNewLabel_5);
        
        table_3 = new JTable();
        JScrollPane gorgeto3 = new JScrollPane(table_3);
        gorgeto3.setBounds(837, 467, 313, 158);       
        add(gorgeto3);
        
        modell3 = new DefaultTableModel();
        modell3.setColumnIdentifiers(new Object[]{"P Vonalkód"});
        table_3.setModel(modell3);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.setBounds(473, 705, 89, 23);
        add(excel_gomb);
        
        JButton kiajaln_gomb = new JButton("Kiajánlás");
        kiajaln_gomb.setBounds(724, 705, 89, 23);
        add(kiajaln_gomb);
        
        JButton oqcadatok_gomb = new JButton("OQC adatok");
        oqcadatok_gomb.setBounds(581, 777, 120, 23);
        add(oqcadatok_gomb);

    }
}
