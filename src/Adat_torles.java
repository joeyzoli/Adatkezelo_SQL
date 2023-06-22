import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class Adat_torles extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField datum;
	static JTable table;
	private JScrollPane scrollPane;
	private JTextField nev_mezo;
	private JTextField projekt_mezo;
	private JTextField cikkszam_mezo;
	/**
	 * Create the panel.
	 */
	public Adat_torles() 
	{
		this.setPreferredSize(new Dimension(1549, 713));
		
		new ComboBox();
		
		JLabel lblNewLabel = new JLabel("Hibásan felvitt adatok törlése vagy módosítása");
		lblNewLabel.setBounds(424, 22, 326, 17);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_3 = new JLabel("Dátum");
		lblNewLabel_3.setBounds(399, 71, 86, 14);
		
		datum = new JTextField();
		datum.setBounds(495, 68, 193, 20);
		datum.setColumns(10);
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel);
		add(lblNewLabel_3);
		add(datum);
		
		JButton leker_gomb = new JButton("Lekérdés");
		leker_gomb.setBounds(495, 211, 89, 23);
		leker_gomb.addActionListener(new Lekerdezo());
		add(leker_gomb);
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		//table.setBounds(63, 410, 948, -51);
		scrollPane.setBounds(61, 264, 1458, 352);
		add(scrollPane);
		
		JButton ujra = new JButton("Újraír");
		ujra.addActionListener(new Visszair());
		ujra.setBounds(495, 627, 89, 23);
		add(ujra);
		
		JButton sortorles_gomb = new JButton("Sor törlése");
		sortorles_gomb.addActionListener(new Sortorles());
		sortorles_gomb.setBounds(474, 661, 121, 23);
		add(sortorles_gomb);
		
		JLabel lblNewLabel_1 = new JLabel("Név");
		lblNewLabel_1.setBounds(399, 102, 46, 14);
		add(lblNewLabel_1);
		
		nev_mezo = new JTextField();
		nev_mezo.setBounds(495, 99, 193, 20);
		add(nev_mezo);
		nev_mezo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Projekt");
		lblNewLabel_2.setBounds(399, 133, 46, 14);
		add(lblNewLabel_2);
		
		projekt_mezo = new JTextField();
		projekt_mezo.setBounds(495, 130, 193, 20);
		add(projekt_mezo);
		projekt_mezo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cikkszám");
		lblNewLabel_4.setBounds(399, 173, 65, 14);
		add(lblNewLabel_4);
		
		cikkszam_mezo = new JTextField();
		cikkszam_mezo.setBounds(495, 170, 193, 20);
		add(cikkszam_mezo);
		cikkszam_mezo.setColumns(10);

	}
	
	class Lekerdezo implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String nev;String projekt;String cikk;
                if(nev_mezo.getText().equals(""))
                {
                    nev = "%";                   
                }
                else
                {
                    nev = nev_mezo.getText() +"%";
                }
                if(projekt_mezo.getText().equals(""))
                {
                    projekt = "%";                   
                }
                else
                {
                    projekt = projekt_mezo.getText() +"%";
                }
                if(cikkszam_mezo.getText().equals(""))
                {
                    cikk = "%";                   
                }
                else
                {
                    cikk = cikkszam_mezo.getText() +"%";
                }
                String sql = "SELECT * FROM  qualitydb.Gyartasi_adatok where Datum = '"+ datum.getText() +"' and Ellenor_neve like '"+ nev +"' and Vevo like '"+ projekt +"' and VT_azon like '"+ cikk +"'";
                SQL lekerdez = new SQL();
                lekerdez.adat_modositashoz(sql);
                //resizeColumnWidth(table);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
     }
	 /*
	 public void resizeColumnWidth(JTable table) {
         final TableColumnModel columnModel = table.getColumnModel();
         for (int column = 0; column < table.getColumnCount(); column++) {
             int width = 15; // Min width
             for (int row = 0; row < table.getRowCount(); row++) {
                 TableCellRenderer renderer = table.getCellRenderer(row, column);
                 Component comp = table.prepareRenderer(renderer, row, column);
                 width = Math.max(comp.getPreferredSize().width +1 , width);
             }
             if(width > 300)
                 width=300;
             columnModel.getColumn(column).setPreferredWidth(width);
         }
	 }
	*/
	class Visszair implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Db_iro visszair = new Db_iro();
            for(int szamlalo = 0; szamlalo < table.getRowCount(); szamlalo++)
            {
                visszair.ujrair(Integer.parseInt(table.getValueAt(szamlalo, 0).toString()), table.getValueAt(szamlalo, 1).toString(), table.getValueAt(szamlalo, 2).toString(), 
                    table.getValueAt(szamlalo, 3).toString(), table.getValueAt(szamlalo, 4).toString(), table.getValueAt(szamlalo, 5).toString(), table.getValueAt(szamlalo, 6).toString(),
                    table.getValueAt(szamlalo, 7).toString(), table.getValueAt(szamlalo, 8).toString(), Integer.parseInt(table.getValueAt(szamlalo, 9).toString()), Integer.parseInt(table.getValueAt(szamlalo, 10).toString()),
                    table.getValueAt(szamlalo, 11).toString(), Integer.parseInt(table.getValueAt(szamlalo, 12).toString()), table.getValueAt(szamlalo, 13).toString(), table.getValueAt(szamlalo, 14).toString(), 
                    Integer.parseInt(table.getValueAt(szamlalo, 15).toString()), table.getValueAt(szamlalo, 16).toString());
            }
            Foablak.frame.setCursor(null);
            JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Info", 1); 
         }
    }
	
	class Sortorles implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Db_iro visszair = new Db_iro();
            int column = 0;
            int row = table.getSelectedRow();
            String id = table.getModel().getValueAt(row, column).toString();
            String sql = "DELETE FROM qualitydb.Gyartasi_adatok where id = '" + id +"'";
            visszair.ujrair_vevoi(sql);
            
            Foablak.frame.setCursor(null);
            JOptionPane.showMessageDialog(null, "Törlés sikeres!", "Info", 1); 
         }
    }
}
