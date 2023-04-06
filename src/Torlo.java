import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		lblNewLabel.setBounds(132, 105, 104, 14);
		
		JLabel lblNewLabel_1 = new JLabel("Gyártási adatok törlse");
		lblNewLabel_1.setBounds(412, 105, 134, 14);
		
		JButton db_atiro = new JButton("Átír");
		db_atiro.setBounds(132, 163, 83, 23);
		db_atiro.addActionListener(new DB_atiras());
		
		JButton gyartas_torles = new JButton("Törlés");
		gyartas_torles.setBounds(412, 163, 77, 23);
		gyartas_torles.addActionListener(new Torles_gyartas());
		
		JLabel lblNewLabel_2 = new JLabel("CSV gyártó");
		lblNewLabel_2.setBounds(132, 236, 54, 14);
		
		JButton csv_gomb = new JButton("CSV");
		csv_gomb.setBounds(132, 268, 54, 23);
		csv_gomb.addActionListener(new CSV_gyart());
		
		JLabel lblNewLabel_3 = new JLabel("Adatbázis feltöltése");
		lblNewLabel_3.setBounds(412, 236, 134, 14);
		
		JButton feltolt = new JButton("feltölt");
		feltolt.setBounds(412, 268, 77, 23);
		feltolt.addActionListener(new Feltolto());
		setBackground(Foablak.hatter_szine);
		setLayout(null);
		add(lblNewLabel);
		add(db_atiro);
		add(lblNewLabel_2);
		add(csv_gomb);
		add(feltolt);
		add(lblNewLabel_3);
		add(gyartas_torles);
		add(lblNewLabel_1);

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
				String[] adatbazisok = {"qualitydb.Ellenori_vizsga"};
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
            try
            {  
              //step1 load the driver class  
              DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
              Class.forName("oracle.jdbc.OracleDriver");  //.driver
                
              //step2 create  the connection object  
              Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");  
                
              //step3 create the statement object  
              Statement stmt = con.createStatement();
              SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
              Date date = new Date();
              System.out.println(formatter.format(date));
                
              //step4 execute query  
              ResultSet rs = stmt.executeQuery("select PART_NO,\n"
                      + "        manuf_date\n"
                      + "from ifsapp.C_OPER_TRACY_OVW \n"
                      + "where 3=3 \n"
                      + "and SCAN_LOC = 'NXT01'\n"
                      + "and MANUF_DATE between to_date( '20230405000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '20230405235959', 'YYYYMMDDHH24:MI:SS' )\n"
                      + "order by manuf_date DESC \n"
                      + "FETCH FIRST 1 ROWS ONLY");  
              while(rs.next())
              { 
                  System.out.println(rs.getString(1));  
              }
              //step5 close the connection object  
              con.close();  
                
              }
            catch(Exception e1)
            { 
                System.out.println(e1);
            }  
                               
         }
    }
}
