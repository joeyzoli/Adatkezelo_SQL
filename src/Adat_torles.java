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
import javax.swing.JComboBox;
import javax.swing.JTable;

public class Adat_torles extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Db_torlo adat_torlo;
	private JTextField datum;
	private JTextField muszak;
	private JTextField felajanlott;
	private ComboBox combobox_tomb;
	private JComboBox<String> hibahelye_box;
	private JComboBox<String> vt_box;
	private JComboBox<String> hibakod_box;
	static JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public Adat_torles() 
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		combobox_tomb = new ComboBox();
		
		JLabel lblNewLabel = new JLabel("Hibásan felvitt adatok törlése");
		lblNewLabel.setBounds(424, 22, 174, 17);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("VT azonosító");
		lblNewLabel_1.setBounds(343, 54, 86, 14);
		
		JButton torles_gomb = new JButton("Törlés");
		torles_gomb.setBounds(495, 260, 89, 23);
		torles_gomb.addActionListener(new PCB_torlo());
		
		JLabel lblNewLabel_3 = new JLabel("Dátum");
		lblNewLabel_3.setBounds(343, 86, 86, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Műszak");
		lblNewLabel_4.setBounds(343, 124, 86, 14);
		
		JLabel lblNewLabel_5 = new JLabel("Hibagyűjtés helye");
		lblNewLabel_5.setBounds(343, 149, 102, 14);
		
		JLabel lblNewLabel_6 = new JLabel("Felajanlott");
		lblNewLabel_6.setBounds(343, 181, 86, 14);
		
		JLabel lblNewLabel_7 = new JLabel("Hibakód");
		lblNewLabel_7.setBounds(343, 213, 56, 14);
		
		datum = new JTextField();
		datum.setBounds(495, 83, 193, 20);
		datum.setColumns(10);
		
		muszak = new JTextField();
		muszak.setBounds(495, 114, 193, 20);
		muszak.setColumns(10);
		
		hibahelye_box = new JComboBox<String>(combobox_tomb.getCombobox2(ComboBox.hiba_helye));				//combobox_tomb.getCombobox2(ComboBox.hiba_helye)
		hibahelye_box.setBounds(495, 145, 193, 22);
		
		vt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));						//combobox_tomb.getCombobox(ComboBox.vt_azon)
		vt_box.setBounds(495, 50, 193, 22);
		
		felajanlott = new JTextField();
		felajanlott.setBounds(495, 178, 193, 20);
		felajanlott.setColumns(10);
		
		hibakod_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));                 //combobox_tomb.getCombobox(ComboBox.hibakodok)
		hibakod_box.setBounds(495, 209, 193, 22);
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel);
		add(lblNewLabel_1);
		add(lblNewLabel_3);
		add(lblNewLabel_4);
		add(lblNewLabel_5);
		add(lblNewLabel_6);
		add(lblNewLabel_7);
		add(hibakod_box);
		add(felajanlott);
		add(hibahelye_box);
		add(muszak);
		add(datum);
		add(vt_box);
		add(torles_gomb);
		
		JButton leker_gomb = new JButton("Lekérdés");
		leker_gomb.setBounds(495, 317, 89, 23);
		leker_gomb.addActionListener(new Lekerdezo());
		add(leker_gomb);
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		//table.setBounds(63, 410, 948, -51);
		scrollPane.setBounds(63, 369, 948, 193);
		add(scrollPane);
		
		JButton ujra = new JButton("Újraír");
		ujra.addActionListener(new Visszair());
		ujra.setBounds(495, 585, 89, 23);
		add(ujra);

	}
	
	class PCB_torlo implements ActionListener																						//törlés gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {


			int res = JOptionPane.showConfirmDialog(null, 																			//megerősítést kérő üzenet jelenik meg
			"Figyelem! Biztosan ezt akarod?", 
			"Figyelmeztetés", 
			JOptionPane.YES_NO_OPTION);
			 
			if (res == JOptionPane.YES_OPTION)																						//igen gomb megnyomásakot törli a megadott sort
			{
				adat_torlo = new Db_torlo();
				String[] vt_koztes = String.valueOf(vt_box.getSelectedItem()).split(" - ");
				String[] hibakod_koztes = String.valueOf(hibakod_box.getSelectedItem()).split(" - ");
				
				adat_torlo.adat_torlo(vt_koztes[0], datum.getText(), muszak.getText(), String.valueOf(hibahelye_box.getSelectedItem()),Integer.parseInt(felajanlott.getText()), Integer.parseInt(hibakod_koztes[0]));
				
				System.out.println(hibakod_koztes[0]);
				JOptionPane.showMessageDialog(null, "Törlés kész", "Info", 1);
			}
			else																													//nem válasz estén hibaüzenet és a pcb sorszám törlése
			{
				JOptionPane.showMessageDialog(null, "Törlés megszakítva!!", "Hiba üzenet", 2);
				
			}
		 }
	}
	
	class Lekerdezo implements ActionListener                                                                                      //törlés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {             
                SQL lekerdez = new SQL();
                lekerdez.adat_modositashoz(datum.getText());
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2 + "\n \n Nincs ilyen adat!!", "Hiba üzenet", 2); 
            }           
         }
    }
	
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
            JOptionPane.showMessageDialog(null, "Módosítás sikeres!", "Hiba üzenet", 2); 
         }
    }
}
