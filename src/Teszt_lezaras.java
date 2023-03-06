import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Teszt_lezaras extends JPanel 
{
    private JTextField nev_mezo;
    static JTable table;

    /**
     * Create the panel.
     */
    public Teszt_lezaras() 
    {
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Vizsga eredmények pontozása és lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(421, 39, 328, 23);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Név");
        lblNewLabel_1.setBounds(471, 137, 46, 14);
        add(lblNewLabel_1);
        
        nev_mezo = new JTextField();
        nev_mezo.setBounds(527, 134, 156, 20);
        add(nev_mezo);
        nev_mezo.setColumns(10);
        
        JButton keres_gomb = new JButton("Keresés");
        keres_gomb.addActionListener(new Keres());
        keres_gomb.setBounds(506, 194, 89, 23);
        add(keres_gomb);
        
        table = new JTable();
        JScrollPane gorgeto = new JScrollPane(table);
        gorgeto.setBounds(313, 239, 500, 117);
        add(gorgeto);
        
        JButton pontoz_gomb = new JButton("Pontoz");
        pontoz_gomb.addActionListener(new Pontoz());
        pontoz_gomb.setBounds(506, 386, 89, 23);
        add(pontoz_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Eredmények");
        lblNewLabel_2.setBounds(416, 457, 86, 14);
        add(lblNewLabel_2);
        
        JButton excel_gomb = new JButton("Excel");
        excel_gomb.addActionListener(new Excel());
        excel_gomb.setBounds(583, 453, 89, 23);
        add(excel_gomb);

    }
    
    class Keres implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt lekerdez = new SQL_teszt();
                String sql= "select id, nev, datum, pont from qualitydb.Ellenori_vizsga where 3 = 3 and nev = '" + nev_mezo.getText() + "'";
                lekerdez.lekerdez_mutat(sql, "igen");
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Pontoz implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt lekerdez = new SQL_teszt();
                for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
                {
                    String sql= "update qualitydb.Ellenori_vizsga set  Pont = '"+ table.getValueAt(szamlalo, 3).toString() + "' where id = '"+ table.getValueAt(szamlalo, 0).toString() +"'";
                    lekerdez.lekerdez_mutat(sql, "nem");
                }
                JOptionPane.showMessageDialog(null, "Pontok sikeresn elmentve!", "Infó", 1);
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
    
    class Excel implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try 
            {
                SQL_teszt lekerdez = new SQL_teszt();
                lekerdez.eredmenyek_excel(nev_mezo.getText());
            } 
            catch (Exception e1) 
            {              
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
}
