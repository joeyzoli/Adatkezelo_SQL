import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class Sajat_SQL extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLehetsgVanSajt;
	private SQL egyeni_sql;
	private JTextArea sql_mezo;

	/**
	 * Create the panel.
	 */
	public Sajat_SQL() 
	{
		
		JLabel lblNewLabel = new JLabel("Saját SQL írására van lehetőség");
		lblNewLabel.setForeground(Color.ORANGE);
		
		txtLehetsgVanSajt = new JTextField();
		txtLehetsgVanSajt.setHorizontalAlignment(SwingConstants.CENTER);
		txtLehetsgVanSajt.setText("Lehetőség van saját SQL írására. Az alapokat kitöltöttem a többit Neked kell");
		txtLehetsgVanSajt.setColumns(10);
		
		sql_mezo = new JTextArea();
		sql_mezo.setText("select * \n from qualitydb.Gyartasi_adatok \n where 3 = 3");
		
		JButton futtat = new JButton("Futtatás");
		futtat.addActionListener(new SQL_futtat());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(137)
							.addComponent(sql_mezo, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(txtLehetsgVanSajt, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(236)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(288)
							.addComponent(futtat)))
					.addContainerGap(259, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(txtLehetsgVanSajt, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(sql_mezo, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(futtat)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	class SQL_futtat implements ActionListener																						//futtat gomb megnyomáskor hívodik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			egyeni_sql = new SQL();
			egyeni_sql.sajat_sql(sql_mezo.getText());																				//Jtextfieldben levő string futtatása
		 }
	}
}
