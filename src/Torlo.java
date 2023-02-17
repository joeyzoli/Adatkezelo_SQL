import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.spire.data.table.DataTable;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

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
		
		JLabel lblNewLabel = new JLabel("Adatbázis átírása");
		
		JLabel lblNewLabel_1 = new JLabel("Gyártási adatok törlse");
		
		JButton db_atiro = new JButton("Átír");
		db_atiro.addActionListener(new DB_atiras());
		
		JButton gyartas_torles = new JButton("Törlés");
		gyartas_torles.addActionListener(new Torles_gyartas());
		
		JLabel lblNewLabel_2 = new JLabel("CSV gyártó");
		
		JButton csv_gomb = new JButton("CSV");
		csv_gomb.addActionListener(new CSV_gyart());
		
		JLabel lblNewLabel_3 = new JLabel("Adatbázis feltöltése");
		
		JButton feltolt = new JButton("feltölt");
		feltolt.addActionListener(new Feltolto());
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
		    groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		            .addGap(132)
		            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(lblNewLabel)
		                .addComponent(db_atiro)
		                .addComponent(lblNewLabel_2)
		                .addComponent(csv_gomb))
		            .addGap(197)
		            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(feltolt)
		                .addComponent(lblNewLabel_3)
		                .addComponent(gyartas_torles)
		                .addComponent(lblNewLabel_1))
		            .addContainerGap(317, Short.MAX_VALUE))
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
		                .addComponent(db_atiro)
		                .addComponent(gyartas_torles))
		            .addGap(50)
		            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		                .addComponent(lblNewLabel_2)
		                .addComponent(lblNewLabel_3))
		            .addGap(18)
		            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		                .addComponent(csv_gomb)
		                .addComponent(feltolt))
		            .addContainerGap(244, Short.MAX_VALUE))
		);
		setBackground(Foablak.hatter_szine);
		setLayout(groupLayout);

	}
	
	class DB_atiras implements ActionListener																						//törlés gomb megnyomáskor hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
			    Db_iro atiras = new Db_iro();
			    Workbook workbook = new Workbook();
			    workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\Java projekt\\emerson felosztás.xlsx");
	            Worksheet sheet = workbook.getWorksheets().get(0);
	            DataTable dataTable = sheet.exportDataTable();
	            for (int i = 0; i < dataTable.getRows().size(); i++) 
	            {
	                atiras.atir(dataTable.getRows().get(i).getString(0), dataTable.getRows().get(i).getString(1));
	            }
	            JOptionPane.showMessageDialog(null, "Átírás kész", "Info", 1);
   
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
				String[] adatbazisok = {"qualitydb.Vevoireklamacio_alapadat", "qualitydb.Vevoireklamacio_felelosok", "qualitydb.Vevoireklamacio_detekt", "qualitydb.Vevoireklamacio_kepek"};
				for(int szamlalo = 0; szamlalo < adatbazisok.length; szamlalo++)
				{
				    torol.torlo(adatbazisok[szamlalo]);
				}
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
	
	class Feltolto implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Db_iro feltolto = new Db_iro();
            feltolto.feltolt();
         }
    }
}
