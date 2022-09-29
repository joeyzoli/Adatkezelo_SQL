import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Torlo extends JPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ez a panel csak adtbázi törlésére van meg tesztelni, a végső változsatban nem lesz benne, nehogy valaki véletlen kitörölje az egész adatbázist
	 *
	 */
	public Torlo() 
	{
		
		JLabel lblNewLabel = new JLabel("Hiba adatok törlése");
		
		JLabel lblNewLabel_1 = new JLabel("Gyártási adatok törlse");
		
		JButton hiba_torles = new JButton("Törléss");
		hiba_torles.addActionListener(new Torles_hiba());
		
		JButton gyartas_torles = new JButton("Törlés");
		gyartas_torles.addActionListener(new Torles_gyartas());
		
		JLabel lblNewLabel_2 = new JLabel("CSV gyártó");
		
		JButton csv_gomb = new JButton("CSV");
		csv_gomb.addActionListener(new CSV_gyart());
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(132)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(csv_gomb)
						.addComponent(lblNewLabel_2)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(hiba_torles))
							.addGap(197)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(gyartas_torles)
								.addComponent(lblNewLabel_1))))
					.addContainerGap(306, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(105)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(hiba_torles)
						.addComponent(gyartas_torles))
					.addGap(50)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addComponent(csv_gomb)
					.addContainerGap(244, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	class Torles_hiba implements ActionListener																						//törlés gomb megnyomáskor hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
				Db_torlo torol = new Db_torlo();
				torol.torlo("qualitydb.Hiba_adatok");
				System.out.println("Törlés sikeres");
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace();
				String hibauzenet2 = ex2.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
			 }
			
		 }
	}
	
	class Torles_gyartas implements ActionListener																						//adatbázis törlő gomb megnyomások hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
				Db_torlo torol = new Db_torlo();
				torol.torlo("qualitydb.Gyartasi_adatok");
				System.out.println("Törlés sikeres");
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace();
				String hibauzenet2 = ex2.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
			 }
			
		 }
	}
	
	class CSV_gyart implements ActionListener																						//csv-t gyárt a gomb
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
				CSV_gyarto csv = new CSV_gyarto();
				csv.csvalakito();
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace();
				String hibauzenet2 = ex2.toString();
				JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
			 }
			
		 }
	}
}
