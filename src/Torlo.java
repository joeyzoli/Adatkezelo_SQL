import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JFileChooser;

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
		lblNewLabel_2.setBounds(132, 236, 83, 14);
		
		JButton csv_gomb = new JButton("CSV");
		csv_gomb.setBounds(132, 268, 83, 23);
		csv_gomb.addActionListener(new CSV_gyart());
		
		JLabel lblNewLabel_3 = new JLabel("Mindenes");
		lblNewLabel_3.setBounds(412, 236, 134, 14);
		
		JButton feltolt = new JButton("Bármi");
		feltolt.setBounds(412, 268, 77, 23);
		feltolt.addActionListener(new Excel_szeriaszamgyart());
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
	                atiras.atir("visszajelzés", "Visszajelzés");
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
				String[] adatbazisok = {"qualitydb.Vevoireklamacio_alapadat","qualitydb.Vevoireklamacio_detekt","qualitydb.Vevoireklamacio_excel","qualitydb.Vevoireklamacio_felelosok","qualitydb.Vevoireklamacio_kepek"};
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
	
	class IFS implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Workbook workbook = new Workbook();
                workbook.loadFromFile(fajl.getAbsolutePath());
                Worksheet sheet = workbook.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                 
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  int cellaszam = 1;
                  sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
                  sheet2.getRange().get("B" + cellaszam).setText("Gyártó száma");
                  sheet2.getRange().get("C" + cellaszam).setText("Gyártó cikkszáma");
                  sheet2.getRange().get("D" + cellaszam).setText("Preferált gyártó cikke");
                  sheet2.getRange().get("E" + cellaszam).setText("Jóváhagyva");
                  cellaszam++;
                  
                  for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                  {
                      ResultSet rs = stmt.executeQuery("select part_no, manufacturer_id, manu_part_no, preferred_manu_part, ifsapp.PART_MANU_PART_NO_API.Get_Approved(part_no,manufacturer_id,MANU_PART_NO)\n"
                              + "from ifsapp.PURCH_PART_SUPP_MANUF_PART\n"
                              + "where PART_NO ='"+ datatable.getRows().get(szamlalo).getString(0) + "'");
                      while(rs.next())
                      {                        
                          sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                          sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                          sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                          cellaszam++;
                      }                                        
                  }
                  
                  sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                  sheet2.getAllocatedRange().autoFitColumns();
                  sheet2.getAllocatedRange().autoFitRows();
                  sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  String hova = System.getProperty("user.home") + "\\Desktop\\Beszerzési cikk szállítója.xlsx";
                  workbook2.saveToFile(hova, ExcelVersion.Version2016);
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
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1); 
                  con.close();  
                  Foablak.frame.setCursor(null);  
                  }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Excel_osszefuz implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Átforgatások 20220601-20230531.xlsx";              
                String excelfile2 = System.getProperty("user.home") + "\\Desktop\\Beszerzési cikk szállítója.xlsx";
                String excelfile3 = System.getProperty("user.home") + "\\Desktop\\Cikk katalógus.xlsx";
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile1);
                Workbook workbook2 = new Workbook();
                workbook2.loadFromFile(excelfile2);
                Workbook workbook3 = new Workbook();
                workbook3.loadFromFile(excelfile3);
                Workbook workbook4 = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                Worksheet sheet3 = workbook3.getWorksheets().get(0);
                Worksheet sheet4 = workbook4.getWorksheets().get(0);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                DataTable datatable2 = new DataTable();
                datatable2 = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false );
                DataTable datatable3 = new DataTable();
                datatable3 = sheet3.exportDataTable(sheet3.getAllocatedRange(), false, false );
                
                sheet4.getRange().get("A" + 1).setText("Alapadat");
                sheet4.getRange().get("F" + 1).setText("Beszerzési cik szállító");
                sheet4.getRange().get("K" + 1).setText("Cikk katalógus");
                sheet4.getRange().get("A" + 2).setText("Cikksz.");
                sheet4.getRange().get("B" + 2).setText("Cikk megnevezése");
                sheet4.getRange().get("C" + 2).setText("ME szám");
                sheet4.getRange().get("D" + 2).setText("Gyártó neve");
                sheet4.getRange().get("E" + 2).setText("Gyártói cikkszám");
                sheet4.getRange().get("F" + 2).setText("Cikksám");
                sheet4.getRange().get("G" + 2).setText("Gyártó száma");
                sheet4.getRange().get("H" + 2).setText("Gyártó cikkszáma");
                sheet4.getRange().get("I" + 2).setText("Preferált gyártó cikke");
                sheet4.getRange().get("J" + 2).setText("Jóváhagyva");
                sheet4.getRange().get("K" + 2).setText("Cikksám");
                sheet4.getRange().get("L" + 2).setText("Gyártó száma");
                sheet4.getRange().get("M" + 2).setText("Gyártó cikkszáma");
                sheet4.getRange().get("N" + 2).setText("Preferált gyártó cikke");
                sheet4.getRange().get("O" + 2).setText("Jóváhagyva");
                int cellaszam = 3;
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    cellaszam = sheet4.getLastRow()+1;
                    sheet4.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                    sheet4.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                    sheet4.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(2));
                    sheet4.getRange().get("D" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                    sheet4.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                    int utolsosor = cellaszam;
                    int jovahagyva = 0;
                    for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(4).equals(datatable2.getRows().get(szamlalo2).getString(2)))
                        {
                            if(datatable2.getRows().get(szamlalo2).getString(4).equals("Igen"))
                            {
                               jovahagyva++;
                            }
                        }
                    }
                    if(jovahagyva == 0)
                    {
                        sheet4.getRange().get("E" + cellaszam).getStyle().setColor(Color.red);;
                    }
                    for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(0).equals(datatable2.getRows().get(szamlalo2).getString(0)))
                        {
                            sheet4.getRange().get("F" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(0));
                            sheet4.getRange().get("G" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(1));
                            sheet4.getRange().get("H" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(2));
                            sheet4.getRange().get("I" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(3));
                            sheet4.getRange().get("J" + cellaszam).setText(datatable2.getRows().get(szamlalo2).getString(4));
                            cellaszam++;
                        }
                    }
                    cellaszam = utolsosor;                   
                    jovahagyva = 0;
                    for(int szamlalo2 = 0; szamlalo2 < datatable3.getRows().size(); szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(4).equals(datatable3.getRows().get(szamlalo2).getString(2)))
                        {
                            if(datatable3.getRows().get(szamlalo2).getString(4).equals("Igen"))
                            {
                               jovahagyva++;
                            }
                        }
                    }
                    if(jovahagyva == 0)
                    {
                        sheet4.getRange().get("E" + cellaszam).getStyle().setColor(Color.yellow);;
                    }
                    for(int szamlalo3 = 0; szamlalo3 < datatable3.getRows().size(); szamlalo3++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(0).equals(datatable3.getRows().get(szamlalo3).getString(0)))
                        {
                            sheet4.getRange().get("K" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(0));
                            sheet4.getRange().get("L" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(1));
                            sheet4.getRange().get("M" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(2));
                            sheet4.getRange().get("N" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(3));
                            sheet4.getRange().get("O" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(4));
                            cellaszam++;
                        }
                    }
                }
                
                sheet4.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet4.getAllocatedRange().autoFitColumns();
                sheet4.getAllocatedRange().autoFitRows();
                sheet4.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Összefésült.xlsx";
                workbook4.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook5 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook5.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook5.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook5.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra!", "Info", 1); 
                Foablak.frame.setCursor(null);  
            }                        
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Excel_szeriaszamgyart implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\traciben keresendő.xlsx";                             
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile1);
                Workbook workbook2 = new Workbook();               
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);                
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );               
                sheet2.getRange().get("A" + 1).setText("Szériaszámok");
                
                int cellaszam = 2;
                for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    cellaszam = sheet2.getLastRow()+1;
                    int panelhelye = Integer.parseInt(datatable.getRows().get(szamlalo).getString(4));
                    int sorszam = Integer.parseInt(datatable.getRows().get(szamlalo).getString(1));
                    int start = sorszam -(panelhelye-1);
                    for(int szamlalo2 = 0; szamlalo2 < 12; szamlalo2++)
                    {
                        if(sorszam < 1000)
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3) + datatable.getRows().get(szamlalo).getString(2) +"0"+ start + datatable.getRows().get(szamlalo).getString(5));
                        }
                        else
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3) + datatable.getRows().get(szamlalo).getString(2) + start + datatable.getRows().get(szamlalo).getString(5));
                        }
                        cellaszam++;
                        start++;
                    }
                    
                }
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Szériaszámok.xlsx";
                workbook2.saveToFile(hova, ExcelVersion.Version2016);
                FileInputStream fileStream = new FileInputStream(hova);
                try (XSSFWorkbook workbook5 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook5.getNumberOfSheets()-1; i > 0 ;i--)
                    {    
                        workbook5.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(hova);
                    workbook5.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra!", "Info", 1); 
                Foablak.frame.setCursor(null);  
            }                        
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
            }                                         
         }
    }
}
