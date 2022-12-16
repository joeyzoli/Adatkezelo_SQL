import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

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

	/**
	 * Create the panel.
	 */
	public Adat_torles() 
	{
		this.setPreferredSize(new Dimension(1100, 650));
		
		combobox_tomb = new ComboBox();
		
		JLabel lblNewLabel = new JLabel("Hibásan felvitt adatok törlése");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("VT azonosító");
		
		JButton torlo_gomb = new JButton("Törlés");
		torlo_gomb.addActionListener(new PCB_torlo());
		
		JLabel lblNewLabel_3 = new JLabel("Dátum");
		
		JLabel lblNewLabel_4 = new JLabel("Műszak");
		
		JLabel lblNewLabel_5 = new JLabel("Hibagyűjtés helye");
		
		JLabel lblNewLabel_6 = new JLabel("Felajanlott");
		
		JLabel lblNewLabel_7 = new JLabel("Hibakód");
		
		datum = new JTextField();
		datum.setColumns(10);
		
		muszak = new JTextField();
		muszak.setColumns(10);
		
		hibahelye_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hiba_helye));				//combobox_tomb.getCombobox_hiba()
		
		vt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vt_azon));						//combobox_tomb.getCombobox()
		
		felajanlott = new JTextField();
		felajanlott.setColumns(10);
		
		hibakod_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.hibakodok));					//combobox_tomb.getCombobox_hibakodok()
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(409)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(368)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_7))
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(hibakod_box, 0, 193, Short.MAX_VALUE)
								.addComponent(felajanlott, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(hibahelye_box, 0, 193, Short.MAX_VALUE)
								.addComponent(muszak)
								.addComponent(datum)
								.addComponent(vt_box, 0, 193, Short.MAX_VALUE)
								.addComponent(torlo_gomb))))
					.addGap(412))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(lblNewLabel)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(vt_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(datum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(muszak, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(hibahelye_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(felajanlott, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(hibakod_box, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(torlo_gomb)
					.addContainerGap(256, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

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
}
