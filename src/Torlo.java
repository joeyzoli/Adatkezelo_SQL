import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.spire.data.table.DataTable;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.xls.ExcelVersion;
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
	private String hova = System.getProperty("user.home") + "\\Desktop\\log_0201_15.xlsx";
    private Workbook workbook = new Workbook();
    private Worksheet sheet;

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
		feltolt.addActionListener(new Atallas());
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
	                atiras.atir("924", "Óngolyó (HotBar, Szelektív)");
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
				String[] adatbazisok = {"qualitydb.Folyamatellenori_kepek"};
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
	/*
	class Logolvasas implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {   
                workbook.setVersion(ExcelVersion.Version2016);
                FileInputStream fis;
                sheet  = workbook.getWorksheets().get(0);
                for(int sorok = 1; sorok < 7; sorok++)
                {
                    File f = new File("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\extra lekérdezések\\log fájlok\\0313_27\\"+sorok+"\\");                                         //mappa beolvasása
                                       
                    FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus                
                    {                   
                        @Override
                        public boolean accept(File f, String name) 
                        {                                                                                                       // csak az xlsx fájlokat listázza ki 
                            return name.endsWith(".log");   
                        }
                    };
                    
                    File[] files = f.listFiles(filter);                                                         //a beolvasott adatok egy fájl tömbbe rakja
                    System.out.println(files.length);                    
                    
                    int cellaszam = 1;
                    int oszlop = 1;
                    for (int i = 0; i < files.length; i++)
                    {
                        fis = new FileInputStream("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\extra lekérdezések\\log fájlok\\0313_27\\"+sorok+"\\" +files[i].getName());   
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                        String sor;
                        br.readLine();
                        br.readLine();
                        br.readLine();
                        while((sor = br.readLine()) != null)
                        {
                            String[] tarolo = sor.split(",");
                            for (int a = 0; a < tarolo.length; a++)
                            {
                                sheet.getRange().get(oszlop, cellaszam).setText(tarolo[a]);                     
                                cellaszam++;                               
                            }
                            oszlop++;
                            cellaszam = 1;
                        }
                        System.out.println("Vajon hányszor fut le");
                    }
                }
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                workbook.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook3.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook3.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
            }       
                catch (Exception e1) 
                {
                }
            }         
            }                                  
    }*/
	
	class Oszloptorles implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
             {
                workbook.setVersion(ExcelVersion.Version2016);
                workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\0201_15.xlsx");
                sheet  = workbook.getWorksheets().get(0);
                int cellaszam = 11;                
                for (int a = 0; a < sheet.getLastColumn(); a++)
                {
                    String adat = sheet.getRange().get(1, cellaszam).toString();
                    if(adat.contains("value"))
                    {
                        
                    }
                    else
                    {
                        sheet.deleteColumn(cellaszam);
                    }
                    cellaszam++;                               
                }
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                workbook.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook3.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook3.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook3.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
               
             }
            catch(Exception ex2)
             {
                ex2.printStackTrace();
                String hibauzenet2 = ex2.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
             }
            
         }
    }
	
	class PDF implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            PdfDocument pdf = new PdfDocument();
            pdf.loadFromFile(System.getProperty("user.home") + "\\Desktop\\L2216691.pdf");
            pdf.saveToFile(System.getProperty("user.home") + "\\Desktop\\L2216691.xlsx", FileFormat.XLSX);
         }
    }
	
	class Atallas implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            ResultSet resultSet = null; 
          
            try 
            {
               try 
               {
                  Class.forName("com.mysql.cj.jdbc.Driver");
               } 
               catch (Exception e1) 
               {
                  System.out.println(e);
                  String hibauzenet2 = e.toString();
                  JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
               }
                conn = DriverManager.getConnection("jdbc:mysql://192.168.28.2", "veasnxt", "Veas8000");
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);           
                stmt.execute("SELECT\n"
                        + "folyamat_tabla.id,\n"
                        + "folyamat_tabla.user_id,\n"
                        + "folyamat_tabla.machine_id,\n"
                        + "folyamat_tabla.allas_id,\n"
                        + "folyamat_tabla.allas_ok_id,\n"
                        + "folyamat_tabla.`comment`,\n"
                        + "folyamat_tabla.start_tstamp,\n"
                        + "folyamat_tabla.end_tstamp,\n"
                        + "folyamat_tabla.auto_store,\n"
                        + "folyamat_tabla.end_date,\n"
                        + "folyamat_tabla.nxt_job_id,\n"
                        + "allas_tabla.allas_name,\n"
                        + "allas_ok_tabla.allas_ok_name\n"
                        + "FROM\n"
                        + "folyamat_tabla\n"
                        + "INNER JOIN allas_tabla ON allas_tabla.id = folyamat_tabla.allas_id\n"
                        + "INNER JOIN allas_ok_tabla ON allas_ok_tabla.id_allas = folyamat_tabla.allas_id AND allas_ok_tabla.id = folyamat_tabla.allas_ok_id\n"
                        + "where ((allas_id = 1 and (allas_ok_id = 3 or allas_ok_id = 10)) or (allas_id = 3 and allas_ok_id = 5))\n"
                        + "and start_tstamp >= now() \n"
                        + "");
                resultSet = stmt.getResultSet();
                
                while(resultSet.next())
                {
                    System.out.println(resultSet.getString(1));
                }
                
               
                
                
                   

                resultSet.close();
                stmt.close();
                conn.close();
                
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
}
