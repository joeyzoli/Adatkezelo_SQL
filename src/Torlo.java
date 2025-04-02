import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
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
    //private Workbook workbook = new Workbook();
    //private Worksheet sheet;

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
		//gyartas_torles.addActionListener(new Torles_gyartas());
		
		JLabel lblNewLabel_2 = new JLabel("CSV gyártó");
		lblNewLabel_2.setBounds(132, 236, 83, 14);
		
		JButton csv_gomb = new JButton("CSV");
		csv_gomb.setBounds(132, 268, 83, 23);
		csv_gomb.addActionListener(new CSV_gyart());
		
		JLabel lblNewLabel_3 = new JLabel("Mindenes");
		lblNewLabel_3.setBounds(412, 236, 134, 14);
		
		JButton feltolt = new JButton("Bármi");
		feltolt.setBounds(412, 268, 77, 23);
		feltolt.addActionListener(new Tracy_kereses_loxone());
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
		
		setBackground(Foablak.hatter_szine);

	}
	
	class DB_atiras implements ActionListener																						//törlés gomb megnyomáskor hívódik meg
	{
		public void actionPerformed(ActionEvent e)
		 {
			try
			 {
			    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			    Db_iro atiras = new Db_iro();
			    atiras.atir("LEROY-SOMER", "Leroy-Somer");
			    /*
			    Workbook workbook = new Workbook();
			    workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\Java projekt\\emerson felosztás.xlsx");
	            Worksheet sheet = workbook.getWorksheets().get(0);
	            DataTable dataTable = sheet.exportDataTable();
	            for (int i = 0; i < dataTable.getRows().size(); i++) 
	            {
	                atiras.atir("Tóth Zoltánoltán", "Tóth Zoltán");
	            }*/
			    Foablak.frame.setCursor(null);
	            JOptionPane.showMessageDialog(null, "Átírás kész", "Info", 1);
   
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace();
				String hibauzenet = ex2.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
	            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
				String[] adatbazisok = {"qualitydb.SQA_reklamaciok"};
				for(int szamlalo = 0; szamlalo < adatbazisok.length; szamlalo++)
				{
				    torol.torlo(adatbazisok[szamlalo]);
				}
				System.out.println("Törlés sikeres");
			 }
			catch(Exception ex2)
			 {
				ex2.printStackTrace();
				String hibauzenet = ex2.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
	            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
				String hibauzenet = ex2.toString();
	            Email hibakuldes = new Email();
	            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
	            JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
                Workbook workbook = new Workbook();
                Worksheet sheet;
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
                String hibauzenet = ex2.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class IFS_csomagoloanyag implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                      
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
                sheet2.getRange().get("B" + cellaszam).setText("Cikk megnevezés");
                sheet2.getRange().get("C" + cellaszam).setText("Beszállító");
                sheet2.getRange().get("D" + cellaszam).setText("Idén beérkezve");
                sheet2.getRange().get("E" + cellaszam).setText("Melyik cikk használja");
                sheet2.getRange().get("F" + cellaszam).setText("Cikk csoport 2");
                sheet2.getRange().get("G" + cellaszam).setText("Osztály");
                sheet2.getRange().get("H" + cellaszam).setText("Csoport");
                sheet2.getRange().get("I" + cellaszam).setText("Típus");
                cellaszam++;
                
                ResultSet rs = stmt.executeQuery("select part_no, description, second_commodity\n"
                        + "from ifsapp.INVENTORY_PART\n"
                        + "where 3 = 3 \n"
                        + "and (PART_PRODUCT_CODE = '14' or PART_PRODUCT_CODE = '24')");
                /*ResultSet rs = stmt.executeQuery("select belso.part_no,\n"
                        + "kulso.DESCRIPTION\n"
                        + "from ifsapp.INVENTORY_PART kulso,\n"
                        + "(select \n"
                        + "part_no,\n"
                        + "attr_value\n"
                        + "from ifsapp.INVENTORY_PART_CHAR_ALL\n"
                        + "where 3 = 3 \n"
                        + "and attr_value = 'Packaging') belso\n"
                        + "where 3 = 3\n"
                        + "and kulso.part_no = belso.part_no");*/
                
                ArrayList<String> cikkszamok = new ArrayList<String>();
                ArrayList<String> cikkmegnevezes = new ArrayList<String>();                
                while(rs.next())
                { 
                    cikkszamok.add(rs.getString(1));
                    cikkmegnevezes.add(rs.getString(2));                    
                }
                for(int szamlalo = 0; szamlalo < cikkszamok.size(); szamlalo++)
                {
                    sheet2.getRange().get("A" + cellaszam).setText(cikkszamok.get(szamlalo));
                    sheet2.getRange().get("B" + cellaszam).setText(cikkmegnevezes.get(szamlalo));
                    rs = stmt.executeQuery("select ifsapp.SUPPLIER_API.Get_Vendor_Name(VENDOR_NO)\n"
                            + "from ifsapp.PURCHASE_PART_SUPPLIER\n"
                            + "where 3 = 3\n"
                            + "and part_no = '"+ cikkszamok.get(szamlalo) +"'");
                    int regicella = cellaszam;
                    //int szam = 1;
                    while(rs.next())
                    {
                        sheet2.getRange().get("A" + cellaszam).setText(cikkszamok.get(szamlalo));
                        sheet2.getRange().get("B" + cellaszam).setText(cikkmegnevezes.get(szamlalo));
                        sheet2.getRange().get("C" + cellaszam).setText(rs.getString(1));
                        cellaszam++;
                        //System.out.println(rs.getString(1));
                        //System.out.println(szam);
                        //szam++;
                    }
                    int ujcella = cellaszam;
                    cellaszam = regicella;
                    rs = stmt.executeQuery("select PART_NO as Cikkszam,\n"
                            + "ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO) as Megnevezes,\n"
                            + "sum(QUANTITY) as Beerkezve\n"
                            + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\n"
                            + "    where 3=3\n"
                            + "    and TRANSACTION_CODE = 'ARRIVAL'\n"
                            + "    and DATE_CREATED between to_date( '20230101000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '20230801235959', 'YYYYMMDDHH24:MI:SS' )\n"
                            + "    and  PART_NO = '"+ cikkszamok.get(szamlalo) +"' group by part_no, ifsapp.Inventory_Part_API.Get_Description(CONTRACT,PART_NO)\n"
                            + "    ");
                    if(rs.next())
                    { 
                        sheet2.getRange().get("D" + cellaszam).setText(rs.getString(3));
                    }
                    else
                    {
                        sheet2.getRange().get("D" + cellaszam).setText("0");
                    }
                    if(ujcella > cellaszam)
                    {
                        cellaszam = regicella;
                    }
                    rs = stmt.executeQuery("select belso.part_no,\n"
                            + "kulso.second_commodity\n"
                            + "from ifsapp.inventory_part kulso,\n"
                            + "(select part_no\n"
                            + "from ifsapp.MANUF_STRUCTURE\n"
                            + "where 3 = 3\n"
                            + "and component_part = '"+ cikkszamok.get(szamlalo) +"') belso\n"
                            + "where 3 = 3\n"
                            + "and kulso.part_no = belso.part_no");             //cikkszamok.get(szamlalo)
                    int szam2 = 1;
                    
                    while(rs.next())
                    {                       
                        String tartalom = rs.getString(1);
                        String tartalom2 = rs.getString(2);
                        sheet2.getRange().get("A" + cellaszam).setText(cikkszamok.get(szamlalo));
                        sheet2.getRange().get("B" + cellaszam).setText(cikkmegnevezes.get(szamlalo));
                        sheet2.getRange().get("E" + cellaszam).setText(tartalom); 
                        sheet2.getRange().get("F" + cellaszam).setText(tartalom2); 
                        System.out.println("tartalom: " + tartalom);
                        System.out.println(szam2);
                        cellaszam++;
                        szam2++;
                    }                                      
                    
                    rs = stmt.executeQuery("select attr_value\r\n"
                            + "from ifsapp.INVENTORY_PART_CHAR_ALL\r\n"
                            + "where 3=3\r\n"
                            + "and part_no = '"+ cikkszamok.get(szamlalo) +"'");
                    int a = 1;
                    String osztaly = ""; String tipus = ""; String csoport = "";
                    while(rs.next())
                    {
                        if(a == 1)
                        {                       
                            osztaly = rs.getString(1);                        
                        }
                        if(a == 2)
                        {                       
                            csoport = rs.getString(1);                        
                        }
                        if(a == 3)
                        {                       
                            tipus = rs.getString(1);                        
                        }
                        a++;
                    }
                    sheet2.getRange().get("G" + (cellaszam-1)).setText(osztaly);
                    sheet2.getRange().get("H" + (cellaszam-1)).setText(csoport);
                    sheet2.getRange().get("I" + (cellaszam-1)).setText(tipus);
                    if(ujcella > cellaszam)
                    {
                        cellaszam = ujcella;
                        //cellaszam++;
                    }  
                }
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Csomagolóanyagok.xlsx";
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
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra Csomagolóanyag.xlsx néven!", "Info", 1); 
                con.close();  
                Foablak.frame.setCursor(null);  
                 
                  
            }           
            catch(Exception e1)
            { 
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                           //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class IFS_csomag implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
                
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                    
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                Statement stmt = con.createStatement();                      
             
                    ResultSet rs = stmt.executeQuery("select part_no\n"
                            + "from ifsapp.MANUF_STRUCTURE\n"
                            + "where 3 = 3\n"
                            + "and component_part = '320-757-21-B'");             //cikkszamok.get(szamlalo)
                    int szam2 = 1;
                    while(rs.next())
                    {
                        String tartalom = rs.getString(1);
                        
                        System.out.println(tartalom); // +" "+ rs.getString(2)+ " "+ rs.getString(3)+" "+ rs.getString(4)
                        System.out.println(szam2);
                        szam2++;
                    }
                    con.close(); 
                    Foablak.frame.setCursor(null);
            }           
            catch(Exception e1)
            { 
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                       //kiírja a hibaüzenetet
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                 //kiírja a hibaüzenetet
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Szeriaszam_kimnetes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String hely = "";
                File mappa;
                File[] fajlok;
                File fajl = new File(System.getProperty("user.home") + "\\Desktop\\N12 FAIL");
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Szériaszámok dátummal.xlsx";
                hely = fajl.getAbsolutePath();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                mappa = new File(hely);                                         //mappa beolvasása
                
                FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                {
                    
                    @Override
                    public boolean accept(File f, String name) 
                    {
                                                                                                        // csak az xlsx fájlokat listázza ki 
                        return name.endsWith(".log");  
                    }
                };
                fajlok = mappa.listFiles(filter); 
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                int cellaszam = 1;
                
                sheet.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet.getRange().get("B" + cellaszam).setText("Fájl neve");
                
                cellaszam++;
                fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                {
                    String fajlneve = fajlok[szamlalo].getName();
                    String[] nev = fajlneve.split("\\.");
                    File adatok = new File(fajlok[szamlalo].getAbsolutePath());
                    try (BufferedReader beolvaso = new BufferedReader(new FileReader(adatok, StandardCharsets.UTF_8))) 
                    {
                        String buffer = beolvaso.readLine();
                        int szam    = 0; 
                        while((buffer = beolvaso.readLine()) != null)                                       //addig fut amíg nem üres a sor, vagyis a fájl végéig
                        {
                            if(szam == 1)
                            {                                
                                String[] elso = buffer.split(" ");
                                System.out.println(elso.length);
                                String[] masodik = elso[3].split(";");
                                sheet.getRange().get("A" + cellaszam).setText(masodik[0]);
                                sheet.getRange().get("B" + cellaszam).setText(nev[0]);
                                break;
                            }
                            szam++;
                        }
                    }           
                    catch(Exception e1)                          //kivételkezelés
                    {
                        System.out.println(e1);
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                    }                    
                    cellaszam++;
                    
                }
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
                workbook.saveToFile(menteshelye, ExcelVersion.Version2016);            
                FileInputStream fileStream = new FileInputStream(menteshelye);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(menteshelye);
                    workbook2.write(output);
                    output.close();
                }
                JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
                Foablak.frame.setCursor(null);
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }                                         
         }
    }
	/////////////////////////////////////////////////////////////////////IFS keresés ////////////////////////////////////////////////
	class Lekerdezes_IFS implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                //DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Rendelések.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  
                  
                  /*ResultSet rs = stmt.executeQuery("select majdnem.*,\n"
                          + "nyilatkozatok.CF$_cmrt as CMRT,\n"
                          + "nyilatkozatok.CF$_reach as Reach,\n"
                          + "nyilatkozatok.CF$_Rohs as Rohs\n"
                          + "from ifsapp.part_manu_part_no_cfv nyilatkozatok,\n"
                          + "(select alap.*,\n"
                          + "raktar.SECOND_COMMODITY as Projekt\n"
                          + "from ifsapp.INVENTORY_PART raktar,\n"
                          + "(select belso.Cikkszam, \n"
                          + "belso.Megnevezes,\n"
                          + "belso.Szallito,\n"
                          + "ifsapp.MANUFACTURER_INFO_API.Get_Name(kulso.MANUFACTURER_NO) as Gyarto,\n"
                          + "belso.Gyartoi_cikkszam\n"
                          + "from ifsapp.PART_MANUFACTURER kulso,\n"
                          + "(select PART_NO as Cikkszam,\n"
                          + "DESCRIPTION as Megnevezes, \n"
                          + "ifsapp.Supplier_API.Get_Vendor_Name(VENDOR_NO) as Szallito,\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_id(ORDER_NO,LINE_NO,RELEASE_NO) as Gyarto_szama,\n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Part_No(ORDER_NO,LINE_NO,RELEASE_NO) as Gyartoi_cikkszam\n"
                          + "from ifsapp.PURCHASE_ORDER_LINE_ALL\n"
                          + "where\n"
                          + "(OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Visszaigazolt') from dual) or \n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Átvéve') from dual) or \n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Beérkezett') from dual) or \n"
                          + "OBJSTATE = (select ifsapp.PURCHASE_ORDER_LINE_API.FINITE_STATE_ENCODE__('Lezárt') from dual)) and DATE_ENTERED > to_date( '20220901', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) )\n"
                          + "group by ifsapp.Supplier_API.Get_Vendor_Name(VENDOR_NO), PART_NO,\n"
                          + "DESCRIPTION, \n"
                          + "ifsapp.Purchase_Part_Supplier_API.Get_Vendor_Part_No(CONTRACT,PART_NO,VENDOR_NO), \n"
                          + "ifsapp.Purchase_Part_Supplier_API.Get_Vendor_Part_Description(CONTRACT,PART_NO,VENDOR_NO), \n"
                          + "PROJECT_ID, \n"
                          + "ifsapp.Project_API.Get_Name(PROJECT_ID), \n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Id(ORDER_NO,LINE_NO,RELEASE_NO), \n"
                          + "ifsapp.Purchase_Order_Line_Part_Api.Get_Manufacturer_Part_No(ORDER_NO,LINE_NO,RELEASE_NO)) belso\n"
                          + "Where 3 = 3\n"
                          + "and belso.Gyarto_szama = kulso.MANUFACTURER_NO\n"
                          + "and belso.Cikkszam = kulso.part_no) alap\n"
                          + "where raktar.part_no = alap.cikkszam) majdnem\n"
                          + "where 3 = 3 and majdnem.cikkszam = nyilatkozatok.part_no\n"
                          + "and majdnem.Gyartoi_cikkszam = nyilatkozatok.manu_part_no");*/
                  /*ResultSet rs = stmt.executeQuery("select kulso.part_no as cikkszam, to_char(kulso.DATE_TIME_CREATED,'YYYY.MM.DD') as mikor, belso.location_no as honnan, kulso.location_no as hova, kulso.quantity as mennyit \n"
                          + "from ifsapp.INVENTORY_TRANSACTION_HIST2 kulso,\n"
                          + "    (select *\n"
                          + "    from ifsapp.INVENTORY_TRANSACTION_HIST2\n"
                          + "    where 3 = 3\n"
                          + "    and TRANSACTION_CODE = 'INVM-ISS'\n"
                          + "    and DATE_CREATED between to_date( '20230101', 'YYYYMMDD' ) and to_date( '20231231', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) )\n"
                          + "    and (ifsapp.Inventory_Location_API.Get_Warehouse(contract,location_no) = '80' or ifsapp.Inventory_Location_API.Get_Warehouse(contract,location_no) = '91')\n"
                          + "    and PART_NO like 'WA%') belso\n"
                          + "where kulso.part_no = belso.part_no\n"
                          + "and kulso.DATE_CREATED = belso.DATE_CREATED\n"
                          + "and belso.DATE_TIME_CREATED = kulso.DATE_TIME_CREATED\n"
                          + "and kulso.TRANSACTION_CODE = 'INVM-IN'\n"
                          + "and kulso.location_no in('AEL00','AEL00-B','PRGL-K','UGYUJTO','UGYUJTO-B','UKEPRO','UPCB','UPRGL')");
                  /*ResultSet rs = stmt.executeQuery("select part_no, manufacturer_no, manu_part_no, user_changed\n"
                          + "from ifsapp.part_manu_part_no_cfv nyilatkozatok");
                  
                  ResultSet rs = stmt.executeQuery("select MANUF_DATE , TRACY_SERIAL_NO\n"
                          + "from ifsapp.C_OPER_TRACY_OVW\n"
                          + "where OPERATION_NO = 30 and MANUF_DATE between to_date( '20240301000000', 'YYYYMMDDHH24:MI:SS' ) and to_date( '20240617235959', 'YYYYMMDDHH24:MI:SS' ) and (PART_NO like 'PROD%' or PART_NO like 'BORD%')\n"
                          + "group by MANUF_DATE , TRACY_SERIAL_NO");*/
                  ResultSet rs = stmt.executeQuery("select part_no, ifsapp.INVENTORY_PART_API.Get_Description(contract,PART_NO) as cikkszamok, date_created,\n"
                          + "location_no, reject_code, transaction, cost, quantity, source, userid\n"
                          + "from ifsapp.INVENTORY_TRANSACTION_HIST2\n"
                          + "where\n"
                          + "upper( ifsapp.Mpccom_System_Event_Api.Get_Description(TRANSACTION_CODE) ) like upper( '%selejt%' ) \n"
                          + "and  DATE_CREATED between to_date( '20240101', 'YYYYMMDD' ) and to_date( '20241231', 'YYYYMMDD' ) + ( 1 - 1/ ( 60*60*24 ) ) \n"
                          + "and ifsapp.inventory_part_api.Get_Second_Commodity(contract, Part_no) = 'VAVM'");
                  Workbook workbook = new Workbook();
                  workbook.setVersion(ExcelVersion.Version2016);
                  //JdbcAdapter jdbcAdapter = new JdbcAdapter();
                  //jdbcAdapter.fillDataTable(datatable, rs);
       
                  //Get the first worksheet
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  //sheet.insertDataTable(datatable, true, 1, 1);
                  int cellaszam = 1;
                  sheet.getRange().get("A" + cellaszam).setText("Cikkszám");
                  sheet.getRange().get("B" + cellaszam).setText("Megnevezés");
                  sheet.getRange().get("C" + cellaszam).setText("Dátum");
                  sheet.getRange().get("D" + cellaszam).setText("Location no");
                  sheet.getRange().get("E" + cellaszam).setText("Selejt kód");
                  sheet.getRange().get("F" + cellaszam).setText("Transaction");
                  sheet.getRange().get("G" + cellaszam).setText("Cost");
                  sheet.getRange().get("H" + cellaszam).setText("Mennyiség");
                  sheet.getRange().get("I" + cellaszam).setText("Source");
                  sheet.getRange().get("J" + cellaszam).setText("User ID");
                  cellaszam++;
                  while(rs.next())
                  { 
                      sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                      sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                      sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                      sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                      sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));
                      sheet.getRange().get("F" + cellaszam).setText(rs.getString(6));
                      /*String[] lebego = rs.getString(7).split("\\.");
                      String ossze = "";
                      if(lebego.length > 1)
                      {
                          ossze = lebego[0] + "." + lebego[1].substring(0, 2);
                      }
                      else
                      {
                          ossze = lebego[0]+ ".00";
                      }*/
                      System.out.println(rs.getString(7));
                      sheet.getRange().get("G" + cellaszam).setNumberValue(Float.valueOf(rs.getString(7)));
                      sheet.getRange().get("H" + cellaszam).setNumberValue(rs.getInt(8));
                      sheet.getRange().get("I" + cellaszam).setText(rs.getString(9));
                      sheet.getRange().get("J" + cellaszam).setText(rs.getString(10));
                      cellaszam++;
                      System.out.println("Fut a while "+ cellaszam);
                      //System.out.println(ossze);
                  }
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  rs.close();
                  stmt.close();
                  con.close();
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook2.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook2.write(output);
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Szeriaszam_gyarto implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
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
                    for(int szamlalo2 = 1; szamlalo2 < 11; szamlalo2++)
                    {
                        if(szamlalo2 < 10)
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0) +"-0"+ szamlalo2);
                        }
                        else
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0) +"-10");
                        }
                        cellaszam++;
                    }
                    
                }
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Keresendő Szériaszámok.xlsx";
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Osszehasonlito implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\nyiltakozatok.xlsx";                             
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile1);
                Workbook workbook2 = new Workbook();               
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);                
                DataTable datatable = new DataTable();
                DataTable datatable2 = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Gyártói cikk");
                sheet2.getRange().get("B" + cellaszam).setText("VEAS cikk");
                sheet2.getRange().get("C" + cellaszam).setText("Megnevezés");
                sheet2.getRange().get("D" + cellaszam).setText("Szállító");
                sheet2.getRange().get("E" + cellaszam).setText("Gyártó");
                sheet2.getRange().get("F" + cellaszam).setText("Projekt");
                sheet2.getRange().get("G" + cellaszam).setText("Anyag besorolás");
                sheet2.getRange().get("H" + cellaszam).setText("Rohs");
                sheet2.getRange().get("I" + cellaszam).setText("REACH");
                sheet2.getRange().get("J" + cellaszam).setText("Cmrt");
                cellaszam++;
                
                Connection conn = null;
                Statement stmt = null;        
                try 
                {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                } 
                catch (Exception e1) 
                {
                   System.out.println(e1);
                   String hibauzenet2 = e1.toString();
                   JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from qualitydb.Nyilatkozatok where 3 = 3");
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                jdbcAdapter.fillDataTable(datatable2, rs);
                System.out.println(datatable.getRows().size());
                System.out.println(datatable2.getRows().size());
                for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                {                   
                    for(int szamlalo2 = 0; szamlalo2 < datatable2.getRows().size(); szamlalo2++)
                    {
                        if(datatable.getRows().get(szamlalo).getString(0).equals(datatable2.getRows().get(szamlalo2).getString(0)) && datatable.getRows().get(szamlalo).getString(1).equals(datatable2.getRows().get(szamlalo2).getString(1)))
                        {
                            if(datatable.getRows().get(szamlalo).getString(3).equals(datatable2.getRows().get(szamlalo2).getString(3))) 
                            {
                                int szam = 0;
                                if(datatable.getRows().get(szamlalo).getString(7).equals(datatable2.getRows().get(szamlalo2).getString(7))) {}
                                else
                                {
                                    sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                                    sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                    sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(2));
                                    sheet2.getRange().get("D" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                    sheet2.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                    sheet2.getRange().get("F" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(5));
                                    sheet2.getRange().get("G" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(6));
                                    sheet2.getRange().get("H" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(7));
                                    sheet2.getRange().get("I" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(8));
                                    sheet2.getRange().get("J" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(9));
                                    szam++;
                                    System.out.println("Találat");
                                }
                                if(datatable.getRows().get(szamlalo).getString(8).equals(datatable2.getRows().get(szamlalo2).getString(8))) {}
                                else 
                                {
                                    sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                                    sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                    sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(2));
                                    sheet2.getRange().get("D" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                    sheet2.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                    sheet2.getRange().get("F" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(5));
                                    sheet2.getRange().get("G" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(6));
                                    sheet2.getRange().get("H" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(7));
                                    sheet2.getRange().get("I" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(8));
                                    sheet2.getRange().get("J" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(9));
                                    szam++;
                                    System.out.println("Találat");
                                }
                                if(datatable.getRows().get(szamlalo).getString(9).equals(datatable2.getRows().get(szamlalo2).getString(9))) {}
                                else 
                                {
                                    sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                                    sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                    sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(2));
                                    sheet2.getRange().get("D" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                    sheet2.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                    sheet2.getRange().get("F" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(5));
                                    sheet2.getRange().get("G" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(6));
                                    sheet2.getRange().get("H" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(7));
                                    sheet2.getRange().get("I" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(8));
                                    sheet2.getRange().get("J" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(9));
                                    szam++;
                                    System.out.println("Találat");
                                }
                                if(szam > 0)
                                {
                                    cellaszam++;
                                }
                            }
                        }
                        /*else
                        {
                            System.out.println(datatable.getRows().get(szamlalo).getString(0)+ "  "+datatable2.getRows().get(szamlalo2).getString(0));
                        }*/
                    }                  
                }
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Különbség a nyilatkozatokban.xlsx";
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Ertek_atiras implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {                              
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String hely = "";
                File mappa;
                File[] fajlok;
                File fajl = new File(System.getProperty("user.home") + "\\Desktop\\fájlok");
                //String menteshelye = System.getProperty("user.home") + "\\Desktop\\Szériaszámok dátummal.xlsx";
                hely = fajl.getAbsolutePath();
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                mappa = new File(hely);                                         //mappa beolvasása
                
                FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                {
                    
                    @Override
                    public boolean accept(File f, String name) 
                    {
                                                                                                        // csak az xlsx fájlokat listázza ki 
                        return name.endsWith(".mac_$0");  
                    }
                };
                /*fajlok = mappa.listFiles(filter); 
                Workbook workbook = new Workbook();
                Worksheet sheet = workbook.getWorksheets().get(0);
                int cellaszam = 1;
                
                sheet.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet.getRange().get("B" + cellaszam).setText("Fájl neve");
                
                cellaszam++;*/
                fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                {
                    //String fajlneve = fajlok[szamlalo].getName();
                    //String[] nev = fajlneve.split("\\.");
                    //File adatok = new File(fajlok[szamlalo].getAbsolutePath());
                    try 
                    {
                        Path path = Paths.get(fajlok[szamlalo].getAbsolutePath());
                        Charset charset = StandardCharsets.UTF_8;

                        String content = new String(Files.readAllBytes(path), charset);
                        content = content.replaceAll("145", "120");
                        content = content.replaceAll("100", "60");
                        Files.write(path, content.getBytes(charset));
                    }           
                    catch(Exception e1)                          //kivételkezelés
                    {
                        System.out.println(e1);
                        String hibauzenet2 = e1.toString();
                        JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                    }                    
                    
                }/*
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás           
                workbook.saveToFile(menteshelye, ExcelVersion.Version2016);            
                FileInputStream fileStream = new FileInputStream(menteshelye);
                try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                {
                    for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                    {    
                        workbook2.removeSheetAt(i); 
                    }      
                    FileOutputStream output = new FileOutputStream(menteshelye);
                    workbook2.write(output);
                    output.close();
                }*/
                JOptionPane.showMessageDialog(null, "Kész", "Info", 1);
                Foablak.frame.setCursor(null);
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Javitas_eredmenye implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Workbook workbook = new Workbook();
            Worksheet sheet = workbook.getWorksheets().get(0);
            String menteshelye = System.getProperty("user.home") + "\\Desktop\\Javítás eredménye.xlsx";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try
            {              
                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  stmt = con.createStatement();                      
                  
                  
                  rs = stmt.executeQuery("select retest.tracy_id, retest.javitas_datuma, retest.tracy_serial_no, eredmeny.Retest_ideje, eredmeny.Eredmeny\n"
                          + "from\n"
                          + "(select utana.tracy_id, min(utana.MANUF_DATE) as Retest_ideje, utana.PASS as Eredmeny, utana.OPER_TRACY_ID as Oper_tracy\n"
                          + "from ifsapp.C_OPER_TRACY_OVW utana, ifsapp.C_TRACY_REP_EVENT_EXT jav\n"
                          + "where jav.tracy_id = utana.tracy_id\n"
                          + "and jav.DATE_CREATED < utana.MANUF_DATE\n"
                          + "and utana.WORK_CENTER_NO not like 'JA%'   \n"
                          + "group by utana.tracy_id, utana.PASS, utana.OPER_TRACY_ID) eredmeny,\n"
                          + "(select belso.*, kulso.TRACY_SERIAL_NO, Folyamat.Oper_tracy\n"
                          + "from ifsapp.C_OPER_TRACY_OVW kulso, (select min(utana.MANUF_DATE) as Retest_ideje, utana.OPER_TRACY_ID as Oper_tracy\n"
                          + "                                            from ifsapp.C_OPER_TRACY_OVW utana, ifsapp.C_TRACY_REP_EVENT_EXT jav\n"
                          + "                                        where jav.tracy_id = utana.tracy_id\n"
                          + "                                        and jav.DATE_CREATED < utana.MANUF_DATE\n"
                          + "                                        and utana.WORK_CENTER_NO not like 'JA%'   \n"
                          + "                                        group by utana.OPER_TRACY_ID)  Folyamat,\n"
                          + "(select tracy_id, DATE_CREATED as Javitas_datuma\n"
                          + "from ifsapp.C_TRACY_REP_EVENT_EXT\n"
                          + "where 3=3) belso\n"
                          + "where kulso.tracy_id = belso.tracy_id) retest\n"
                          + "where retest.tracy_id = Eredmeny.tracy_id\n"
                          + "and retest.javitas_datuma <  eredmeny.Retest_ideje\n"
                          + "and retest.Oper_tracy =  eredmeny.Oper_tracy\n"
                          + "group by retest.tracy_id, retest.javitas_datuma, retest.tracy_serial_no, eredmeny.Retest_ideje, eredmeny.Eredmeny\n");
                  
                  
                  //JdbcAdapter jdbcAdapter = new JdbcAdapter();
                  //jdbcAdapter.fillDataTable(datatable, rs);
                  int cellaszam = 1;
                  sheet.getRange().get("A" + cellaszam).setText("Tracy_ID");
                  sheet.getRange().get("B" + cellaszam).setText("Javítás dátuma");
                  sheet.getRange().get("C" + cellaszam).setText("Tracy gyári szám");
                  sheet.getRange().get("D" + cellaszam).setText("Retest ideje");
                  sheet.getRange().get("E" + cellaszam).setText("Retest eredméyne");
      
                  cellaszam++;
                  while(rs.next())
                  {
                      sheet.getRange().get("A" + cellaszam).setText(rs.getString(1));
                      sheet.getRange().get("B" + cellaszam).setText(rs.getString(2));
                      sheet.getRange().get("C" + cellaszam).setText(rs.getString(3));
                      sheet.getRange().get("D" + cellaszam).setText(rs.getString(4));
                      sheet.getRange().get("E" + cellaszam).setText(rs.getString(5));                                       
                      cellaszam++;
                  }
                  //Get the first worksheet
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  rs.close();
                  stmt.close();
                  con.close();
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook2.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook2.write(output);
                      output.close();
                  }                       
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra!", "Info", 1); 
                  con.close();  
                  Foablak.frame.setCursor(null);  
                  }           
            catch(Exception e1)
            {
                /*sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                try
                {
                    workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                    rs.close();
                    stmt.close();
                    con.close();
                    
                    FileInputStream fileStream = new FileInputStream(menteshelye);
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(menteshelye);
                        workbook2.write(output);
                        output.close();
                    }
                }
                catch(Exception e2)
                {*/
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
                //}
            }  
                               
         }
    }
	
	class Visszair implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile1);              
                Worksheet sheet = workbook.getWorksheets().get(0);              
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                
                
                Connection conn = null;
                Statement stmt = null;        
                try 
                {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                } 
                catch (Exception e1) 
                {
                   System.out.println(e1);
                   String hibauzenet2 = e1.toString();
                   JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) conn.createStatement();
                
                for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    //stmt.executeUpdate("update qualitydb.Vevoireklamacio_alapadat set Rek_db ='"+ datatable.getRows().get(szamlalo).getString(1) +"' where ID = '"+ datatable.getRows().get(szamlalo).getString(0) +"'");
                    stmt.executeUpdate("delete from qualitydb.Beolvasott_panelek where panelszam ='"+ datatable.getRows().get(szamlalo).getString(0) +"' ");
                    System.out.println(datatable.getRows().get(szamlalo).getString(0));
                }
                
                
                JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra!", "Info", 1); 
                Foablak.frame.setCursor(null);  
            }                        
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	///////////****************************************** TRacy keresés *************************************************//////////////////////////////////////////////
	
	class Tracy_kereses implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            Statement stmt = null;
            try
            {
                ResultSet result;
                JdbcAdapter jdbcAdapter;
                DataTable datatable;
                Workbook workbook;
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));     
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ip címhez való csatlakozás
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszó felhasználóval
                System.out.println("Connection established......");
             
                /*String sql = "select                   fkov.panel, tempTable.idopont -- *\n"
                        + "from                     (select max(ido) as idopont, panel\n"
                        + "                                               from videoton.fkov\n"
                        + "                                               where 3=3\n"
                        + "                                               and hely in (20,21,22,23) and ido > '2023.01.01 00:00:00' group by panel) as tempTable\n"
                        + "inner join            videoton.fkov on fkov.panel = tempTable.panel and fkov.ido = tempTable.idopont\n"
                        + "where                  fkov.ok <> '-1';";       //utolso mérése hiba         */
                /*String sql = "select  panel\n"
                        + "from videoton.fkov\n"
                        + "inner join  videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\n"
                        + "where 3=3\n"
                        + "and nev = 'INSTAGRID FCT'";*/
                /*String sql = "select      (select videoton.fkovsor.nev from videoton.fkovsor where videoton.fkov.hely = videoton.fkovsor.azon) as 'Folyamat',\n"
                        + "                videoton.fkov.alsor as 'Teszter szám',\n"
                        + "                videoton.fkov.panel as 'Panel',\n"
                        + "                videoton.fkov.ido as 'Időpont',\n"
                        + "                videoton.fkov.szeriaszam as 'Szériaszám',\n"
                        + "                videoton.fkov.kod2 as 'Másodlago kód',\n"
                        + "                if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as 'eredmény',\n"
                        + "                videoton.fkov.tesztszam as 'Teszt sorszám',\n"
                        + "                videoton.fkov.teststarttime as 'Teszt kezdete',\n"
                        + "                videoton.fkov.testfinishtime as 'Teszt vége',\n"
                        + "                videoton.fkov.failtestnames as 'Hibás tesztek'\n"
                        + "    from        videoton.fkov\n"
                        + "    where        \n"
                        + "                videoton.fkov.hely in (125,126,127,128,129,130)\n"
                        + "    order by    videoton.fkov.ido asc;";
                
                String sql = "select group_concat(azon separator ',') as helyAVM from videoton.fkovsor where nev like \"PROGLOVE%\" or nev like \"Instagrid%\" or nev like \"AVM%\" or nev like \"TECHEM%\"";
                */
                /*String sql = "select  tempTable.Week,\n"
                        + "        if(fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as Result,\n"
                        + "        count(*) as Count\n"
                        + "from   videoton.fkov\n"
                        + "inner join  (select yearweek(ido, 1) as Week,\n"
                        + "            panel,\n"
                        + "            max(ido) as MaxTimestamp\n"
                        + "            from  videoton.fkov\n"
                        + "            where hely = 39 and\n"
                        + "            ido >= \"2025.01.01 00:00:00\"  and (yearweek(ido, 1) - concat('20',SUBSTRING(panel, 12,4))) < 4\n"
                        + "            group by 1, 2) as tempTable on tempTable.panel = fkov.panel and tempTable.MaxTimestamp = fkov.ido\n"
                        + "group by             1, 2;\n"
                        + "";*/
                
                /*String sql = "select    videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                        + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny,"
                        + "videoton.fkov.hibakod, videoton.fkov.kod2, videoton.fkov.torolt, "
                        + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                        + "videoton.fkov.dolgozo \n"
                        + "from videoton.fkov  \n"
                        + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely "
                        + "left join videoton.FKOVADAT on videoton.FKOVADAT.FKOV = videoton.fkov.azon "                 
                        + " where hely = '52'\n"
                        + "  and ido > '2024.06.28 00:00:00' and ido < '2024.06.28 23:59:59'";
                
                /*String sql = "select    videoton.fkov.azon, videoton.fkov.hely,videoton.fkovsor.nev, videoton.fkov.ido, videoton.fkov.panel, cast(videoton.fkov.alsor as char(5)) as Teszterszam,"
                        + "if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as eredmeny,"
                        + "videoton.fkov.kod2, videoton.fkov.torolt, "
                        + "videoton.fkov.szeriaszam, videoton.fkov.tesztszam, videoton.fkov.poz, videoton.fkov.teljesszam, videoton.fkov.failtestnames, videoton.fkov.error,"
                        + "videoton.fkov.dolgozo \n"
                        + "from videoton.fkov  \n"
                        + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely "
                        + "left join videoton.FKOVADAT on videoton.FKOVADAT.FKOV = videoton.fkov.azon "                 
                        + " where ido > '2024.11.01' and nev like 'Loxone%'";*/
                
                String sql = "select panel,  count(panel) \n"
                        + "from videoton.fkov\n"
                        + "where 3 = 3  \n"
                        + "    and hely = '50'\n"
                        + "    and ido > '2025.03.20 00:00:00' and ido < '2025.03.20 23:59:59'\n"
                        + "    and  panel like '111A%'     \n"
                        + "  group by panel";

                                
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                           
                cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                datatable = new DataTable();
                System.out.println("Lefutott");
                workbook = new Workbook();
                workbook.setVersion(ExcelVersion.Version2016); 
                jdbcAdapter = new JdbcAdapter();         
                jdbcAdapter.fillDataTable(datatable, result);
                System.out.println("Datatableban");
                //Get the first worksheet
                Worksheet sheet = workbook.getWorksheets().get(0);
                /*
                int cellaszam = 1;
                sheet.getRange().get("A" + cellaszam).setText("Panel");
                sheet.getRange().get("B" + cellaszam).setText("Idő");
                sheet.getRange().get("C" + cellaszam).setText("Ok");
                sheet.getRange().get("D" + cellaszam).setText("Szériaszám");
                sheet.getRange().get("E" + cellaszam).setText("Másodlagos kód");
                cellaszam++;
                while(result.next())
                {
                    sheet.getRange().get("A" + cellaszam).setText(result.getString(5));
                    sheet.getRange().get("B" + cellaszam).setText(result.getString(4));
                    sheet.getRange().get("C" + cellaszam).setText(result.getString(6));
                    sheet.getRange().get("D" + cellaszam).setText(result.getString(12));
                    sheet.getRange().get("E" + cellaszam).setText(result.getString(10));
                    cellaszam++;
                    System.out.println(cellaszam);
                }*/
                
                sheet.insertDataTable(datatable, true, 1, 1);
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                    
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                 
                result.close();
                cstmt.close();
                con.close();
                //workbook.setActiveSheetIndex(0);
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                //workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);
                if(fajl.getName().contains(".xlsx"))
                {
                    workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                        workbook2.write(output);
                        output.close();
                    }
                }
                else
                {
                    workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                        workbook2.write(output);
                        output.close();
                    }
                }
                Foablak.frame.setCursor(null); 
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Infó", 1);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
            finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
            {
                try 
                {
                  if (stmt != null)
                     con.close();
                } 
                catch (SQLException se) {}
                try 
                {
                  if (con != null)
                     con.close();
                } 
                catch (SQLException se) 
                {
                  se.printStackTrace();
                }  
            }
         }
    }
	
	class Gazdasagi_totalkar implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            //Connection con2 = null;
            Statement stmt = null;
            int javitasokszama = 0;
            String kizarando = "899";
            String kizarando2 = "1299";
            String kizarando3 = "A";
            try
            {
                ResultSet result;
                ResultSet result2;
                JdbcAdapter jdbcAdapter;
                JdbcAdapter jdbcAdapter2;
                DataTable datatable;
                DataTable datatable2;
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa  
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszÃ³ felhasznÃ¡lÃ³ nÃ©vvel
                
                String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                Workbook workbook = new Workbook();
                workbook.loadFromFile(excelfile1);
                Workbook workbook2 = new Workbook();               
                Worksheet sheet = workbook.getWorksheets().get(0);
                Worksheet sheet2 = workbook2.getWorksheets().get(0);                
                DataTable datatable3 = new DataTable();               
                datatable3 = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet2.getRange().get("B" + cellaszam).setText("Javítások száma");
                sheet2.getRange().get("C" + cellaszam).setText("Hol van");
                cellaszam++;
                for(int szamlalo3 = 0; szamlalo3 < datatable3.getRows().size();szamlalo3++)
                {
                    javitasokszama = 0;
                    String sql = "select fkovsor.nev, videoton.fkov.panel, "                   
                            + "videoton.fkov.hibakod, videoton.fkov.poz \n"                   
                            + "from videoton.fkov \n"
                            + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                            + " where panel = '"+ datatable3.getRows().get(szamlalo3).getString(0) + "' and  fkovsor.nev like 'Javítás%'";           
                    
                    /*String sql = "select fkovsor.nev as 'Folyamat',\r\n"
                            + " videoton.fkov.ido as 'Időpont',\r\n"
                            + " videoton.fkov.panel as 'Panelszám',\r\n"
                            + "    if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as 'eredmény',\r\n"
                            + "    videoton.fkov.failtestnames as 'Hibakód'\r\n"
                            + "from videoton.fkov\r\n"
                            + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                            + " where   videoton.fkov.panel = '"+ panelszam + "'\r\n"
                            + " and fkovsor.nev = 'INSTAGRID FCT'"; */    
                    String sql2 = "select fkovsor.nev, videoton.fkov.panel, videoton.fkov.error, videoton.fkov.ido \n"                                                      
                            + "from videoton.fkov \n"
                            + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                            + " where panel = '"+ datatable3.getRows().get(szamlalo3).getString(0) + "' order by videoton.fkov.ido desc limit 1";           
                    
                    //con2 = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                    //String sql3 = "select Panelszam from qualitydb.Beolvasott_panelek where 3 = 3";
                    
                    Statement cstmt = con.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    
                    Statement cstmt2 = con.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    
                    cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                    result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                    datatable = new DataTable();
                    jdbcAdapter = new JdbcAdapter();
                    jdbcAdapter2 = new JdbcAdapter(); 
                    jdbcAdapter.fillDataTable(datatable, result);
                    //String alkatresz = "";
                    //int bennevan = 0;
                    //String szoveg ="Folyamat                    Időpont           Erdedmény         Hibakód  \n";
                    /*for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                    {
                        szoveg += datatable.getRows().get(szamlalo).getString(0)+"       "+datatable.getRows().get(szamlalo).getString(1)
                                +"       "+datatable.getRows().get(szamlalo).getString(3)+"       "+datatable.getRows().get(szamlalo).getString(4) +"\n";
                        /*if(panelszam.equals(datatable.getRows().get(szamlalo).getString(0)))                  
                        {
                             bennevan++;
                        }
                    }
                    Ablak.eredmeny_mezo.setText(szoveg);
                    */
                    if(datatable.getRows().size() > 0)
                    {
                        /*if(bennevan > 0)
                        {
                            Ablak.eredmeny_mezo.setText("1x már beolvastad a panleszámot!");
                            Ablak.eredmeny_mezo.setForeground(new Color(0, 0, 255)); 
                        }*/
                        
                        for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
                        {
                            char[] data = {datatable.getRows().get(szamlalo).getString(2).charAt(0)};
                            String keresett = new String(data);
                            if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                            {
                                                       
                            }
                            else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                            {
                                                       
                            }
                            else if(kizarando3.equals(keresett))
                            {
                                                       
                            }
                            else
                            {
                                javitasokszama++;
                                //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                            }
                        }
                        
                        cstmt2.execute(sql2); 
                        result2 = cstmt2.getResultSet();
                        datatable2 = new DataTable();
                        jdbcAdapter2.fillDataTable(datatable2, result2);//az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                        sheet2.getRange().get("A" + cellaszam).setText(datatable3.getRows().get(szamlalo3).getString(0));
                        sheet2.getRange().get("B" + cellaszam).setNumberValue(javitasokszama);
                        sheet2.getRange().get("C" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                        cellaszam++;
                    }
                    
                }
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Tényleges Javítások száma.xlsx";
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
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
            finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
            {
                try 
                {
                  if (stmt != null)
                     con.close();
                } 
                catch (SQLException se){}
                try 
                {
                  if (con != null)
                     con.close();
                } 
                catch (SQLException se) 
                {
                  se.printStackTrace();
                }  
            }   
         }
    }
	
	class Excel_szeriaszamgyart_loxone implements ActionListener                                                                                      //
    {
        public void actionPerformed(ActionEvent e)
         {
            String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Loxone.xlsx";                             
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
            Workbook workbook = new Workbook();
            workbook.loadFromFile(excelfile1);
            Workbook workbook2 = new Workbook();               
            Worksheet sheet = workbook.getWorksheets().get(0);
            Worksheet sheet2 = workbook2.getWorksheets().get(0);                
            DataTable datatable = new DataTable();
            try
            {                
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Sort");
                sheet2.getRange().get("B" + cellaszam).setText("Gyártói kód");
                sheet2.getRange().get("C" + cellaszam).setText("Érték");
                cellaszam++;              
                
                for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    long sorszam = Long.parseLong(datatable.getRows().get(szamlalo).getString(1));
                    long kovetkezo;
                    long kulonbseg = 0;
                    if(datatable.getRows().size() > szamlalo+1)
                    {
                        kovetkezo = Long.parseLong(datatable.getRows().get(szamlalo+1).getString(1));
                        kulonbseg = kovetkezo -sorszam;
                    }
                    if(kulonbseg < 2)
                    {
                        sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                        sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                        sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));                           
                    }
                    else
                    {
                        if(kulonbseg > 4) 
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                            sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                            sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                        }
                        else
                        {
                            if(kulonbseg == 4)
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+2));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+3));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                            }
                            else if(kulonbseg == 3)
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+2));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                            }
                            else
                            {
                                sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                cellaszam++;
                                sheet2.getRange().get("A" + cellaszam).setText(String.valueOf(sorszam+1));
                                sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                                sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                                String.valueOf(sorszam+1);
                            }
                        }                                                               
                    }
                    cellaszam++;
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
                try
                { 
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
                }
                catch(Exception e2)
                {
                    
                }
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	//////////////////////////////////////////////////////////////////////////////////////////////REtour frissít//////////////////////////////////////////////////////////////////////////
	class Retour_frissit implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\RMA.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
               for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   stmt.executeUpdate("update qualitydb.Retour_szeriaszamok set  Hiba_leirasa = '" + datatable.getRows().get(szamlalo).getString(1) //+"; "+ datatable.getRows().get(szamlalo).getString(2) +"' "
                           + "' where Vevoi_ID = '" + datatable.getRows().get(szamlalo).getString(0) + "' or Veas_ID = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                   System.out.println("Fut a For");
                   /*stmt.executeUpdate("update qualitydb.Retour set  Analizis_datum = '" + datatable.getRows().get(szamlalo).getString(1)+"', Felelos1 = '" + datatable.getRows().get(szamlalo).getString(2)+"',"
                           + "Gepes_datum = '" + datatable.getRows().get(szamlalo).getString(3)+"',Felelos2 = '" + datatable.getRows().get(szamlalo).getString(4)+"',"
                           + "Kezi_datum = '" + datatable.getRows().get(szamlalo).getString(5)+"',Felelos3 = '" + datatable.getRows().get(szamlalo).getString(6)+"',"
                           + "Teszt_datum = '" + datatable.getRows().get(szamlalo).getString(7)+"',Felelos4 = '" + datatable.getRows().get(szamlalo).getString(8)+"',"
                           + "Vegell_datum = '" + datatable.getRows().get(szamlalo).getString(9)+"',Felelos5 = '" + datatable.getRows().get(szamlalo).getString(10)+"' "
                           + " where ID = '" + datatable.getRows().get(szamlalo).getString(0) + "'");*/
               }              
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Hanyszor_tesztelve implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Eredmények.xlsx";                             
            Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
            Workbook workbook = new Workbook();
            workbook.loadFromFile(excelfile1);
            Workbook workbook2 = new Workbook();               
            Worksheet sheet = workbook.getWorksheets().get(0);
            Worksheet sheet2 = workbook2.getWorksheets().get(0);
            Worksheet sheet3 = workbook.getWorksheets().get(1);
            DataTable datatable = new DataTable();
            DataTable datatable2 = new DataTable();
            int szam = 0;
            try
            {                
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                datatable2 = sheet3.exportDataTable(sheet3.getAllocatedRange(), false, false );
                int cellaszam = 1;
                sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                sheet2.getRange().get("B" + cellaszam).setText("TEsztek száma javítás elött");
                cellaszam++;              
                //int oszlop = 2;
                //int sor = 2;
                int ismetlesek = 0;
                for(int szamlalo = 1; szamlalo < datatable2.getRows().size(); szamlalo++)          //datatable2.getRows().size()
                {
                    ismetlesek = 0;
                    for(int szamlalo2 = 1; szamlalo2 < datatable.getRows().size(); szamlalo2++)               //datatable.getRows().size()
                    {
                        szam = szamlalo2;
                        //System.out.println(datatable.getRows().get(szamlalo2).getString(4));
                        if(sheet3.getRange().get(szamlalo, 1).getText().toString().equals(sheet.getRange().get(szamlalo2, 5).getText().toString()))            //datatable2.getRows().get(szamlalo).getString(0).equals(datatable.getRows().get(szamlalo2).getString(4))
                        {
                            if(sheet.getRange().get(szamlalo2, 3).getText().toString().contains("Javítás"))
                            {
                                if(ismetlesek > 0)
                                {
                                    if(sheet.getRange().get(szamlalo2, 13).getText().toString().toUpperCase().contains("U300"))
                                    {
                                        sheet2.getRange().get("A" + cellaszam).setText(sheet.getRange().get(szamlalo2, 5).getText().toString());
                                        sheet2.getRange().get("B" + cellaszam).setText(String.valueOf(ismetlesek));
                                        cellaszam++;
                                        ismetlesek = 0;
                                    }
                                    else
                                    {
                                        ismetlesek = 0;
                                    }
                                    
                                }                                
                            }                               
                            else           //datatable2.getRows().get(szamlalo).getString(0).equals(datatable.getRows().get(szamlalo2).getString(4))
                            {
                                if(sheet.getRange().get(szamlalo2, 7).getText().toString().contains("Rendben"))
                                {
                                    ismetlesek = 0;
                                }
                                if(sheet.getRange().get(szamlalo2, 7).getText().toString().contains("Hiba"))
                                {
                                    ismetlesek++;                                  
                                }                                
                            }                                                        
                        }
                    }
                    
                }
                
                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                String hova = System.getProperty("user.home") + "\\Desktop\\Hányszor tesztelve.xlsx";
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
                try
                {                   
                    System.out.println(sheet.getRange().get(szam, 3).getText().toString() +" "+sheet.getRange().get(szam, 5).getText().toString() +" "+ sheet.getRange().get(szam, 7).getText().toString());
                    sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                    sheet2.getAllocatedRange().autoFitColumns();
                    sheet2.getAllocatedRange().autoFitRows();
                    sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                    String hova = System.getProperty("user.home") + "\\Desktop\\Hányszor_biztonsági.xlsx";
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
                }
                catch(Exception e2)
                {
                    System.out.println("Hiba történt!!");
                }
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                               //kiírja a hibaüzenetet
            }                                         
         }
    }
	
	class Retour_szeriaszam_hozzaad implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               stmt.execute("select id, rma from  qualitydb.Retour where 3 = 3 ");
               ResultSet rs = stmt.getResultSet();
               ArrayList<String> tomb = new ArrayList<String>();
               
               while(rs.next())
               {
                   tomb.add(rs.getString(1)+";"+ rs.getString(2));
                   System.out.println(rs.getString(1)+";"+ rs.getString(2));
               }
               
               for(int szamlalo = 0; szamlalo < tomb.size(); szamlalo++)
               {
                   String[] darabolt = tomb.get(szamlalo).split(";");
                   if(darabolt.length > 1)
                   {
                       stmt.executeUpdate("update qualitydb.Retour_szeriaszamok set RMA = '"+ darabolt[1] +"' where Retour_ID = '"+ darabolt[0] + "'");
                       System.out.println("update qualitydb.Retour_szeriaszamok set RMA = '"+ darabolt[1] +"' where ID = '"+ darabolt[0] + "'");
                   }
                   
               }              
                                        
               stmt.close();
               conn.close(); 
               Foablak.frame.setCursor(null);     
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Techem_feltolt implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            File[] fajlok = null;
            int szamlalo3 = 0;
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\TEchem\\";
               File fajl = new File(excelfile1);
               if(fajl != null)
               {
                   String hely = fajl.getAbsolutePath();
                   Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                   File mappa = new File(hely);                                         //mappa beolvasása
                   
                   FilenameFilter filter = new FilenameFilter()                                            //fájlnév filter metódus
                   {
                       
                       @Override
                       public boolean accept(File f, String name) 
                       {
                                                                                                           // csak az xlsx fájlokat listázza ki 
                           return name.endsWith(".ods");  
                       }
                   };               
                   
                   fajlok = mappa.listFiles(filter);                                                           //a beolvasott adatok egy fájl tömbbe rakja    
                   Workbook workbook = new Workbook();
                   for(int szamlalo = 0; szamlalo < fajlok.length; szamlalo++)
                   {
                       String fajlneve = fajlok[szamlalo].getName();
                       if(fajlneve.startsWith("~$")){}
                       else
                       {               
                           File excel = new File(hely+ "\\" +fajlok[szamlalo].getName());
                           szamlalo3 = szamlalo;             
                           workbook.loadFromFile(excel.getAbsolutePath());
                           Worksheet sheet =workbook.getWorksheets().get(0);
                           DataTable datatable = new DataTable();
                           datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                           
                           for(int szamlalo2 = 6; szamlalo2 < 38; szamlalo2++)
                           {
                               System.out.println(datatable.getRows().get(1).getString(3));
                               System.out.println(datatable.getRows().get(1).getString(9));
                               /*stmt.executeUpdate("update qualitydb.Retour_szeriaszamok set  Hiba_leirasa = '" + datatable.getRows().get(szamlalo).getString(1) +"; "+ datatable.getRows().get(szamlalo).getString(2) +"' "
                                       + "where Vevoi_ID = '" + datatable.getRows().get(szamlalo).getString(0) + "'"); 
                               System.out.print(datatable.getRows().get(szamlalo2).getString(1));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(2));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(3));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(4));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(5));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(6));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(7));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(8));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(9));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(10));
                               System.out.print(datatable.getRows().get(szamlalo2).getString(11));
                               System.out.println();
                               */
                               String sql = "insert into qualitydb.Techem_OQC (Datum,Ellenor,LOT_szam,Szeriaszam,Cimke,Lezaro_cimke,Meter_lab,Pass,Szigetelo_anyag,Rogzito_fulek,Szabotazs_kapcs,HKRL,Kijelzo,Megjegyzes) "
                               + "Values ('"+datatable.getRows().get(1).getString(3)+"','"+ datatable.getRows().get(1).getString(9)+"','"
                               +datatable.getRows().get(szamlalo2).getString(1)+"','"+datatable.getRows().get(szamlalo2).getString(2)+"'"
                               +",'"+datatable.getRows().get(szamlalo2).getString(3)+"','"+datatable.getRows().get(szamlalo2).getString(4)+"','"+datatable.getRows().get(szamlalo2).getString(5)+"','"
                               +datatable.getRows().get(szamlalo2).getString(6)+"','"+datatable.getRows().get(szamlalo2).getString(7)+"','"+datatable.getRows().get(szamlalo2).getString(8)+"','"+
                               datatable.getRows().get(szamlalo2).getString(9)+"','"+datatable.getRows().get(szamlalo2).getString(10)+"','"+datatable.getRows().get(szamlalo2).getString(11)
                               +"','"+ "" +"')";
                               stmt.executeUpdate(sql);
                           }                           
                       }               
               }
               Foablak.frame.setCursor(null);                
                                       
               stmt.close();
               conn.close();                
            }
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                System.out.println(fajlok[szamlalo3].getName());
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Acces_olvaso implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            //String databaseURL = "jdbc:ucanaccess:c:\\Users\\kovacs.zoltan\\Desktop\\Access\\AVM_OQC_adatok.accdb";
            
         // variables
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
     
            // Step 1: Loading or
            // registering Oracle JDBC driver class
            try {
     
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            }
            catch(ClassNotFoundException cnfex) {
     
                System.out.println("Problem in loading or "
                        + "registering MS Access JDBC driver");
                cnfex.printStackTrace();
            }
     
            // Step 2: Opening database connection
            try {

                //String dbURL = "jdbc:ucanaccess:\\c:\\Users\\kovacs.zoltan\\Desktop\\Access\\AVM_OQC_adatok.accdb";
                String msAccDB = "c:/Users/kovacs.zoltan/Desktop/Access/AVM_OQC_adatok.accdb";
                String dbURL = "jdbc:ucanaccess://"
                        + msAccDB;
                // Step 2.A: Create and
                // get connection using DriverManager class
                connection = DriverManager.getConnection(dbURL);
     
                // Step 2.B: Creating JDBC Statement
                statement = connection.createStatement();
     
                // Step 2.C: Executing SQL and
                // retrieve data into ResultSet
                resultSet = statement.executeQuery("SELECT * FROM FB7530 where Gyűjtődoboz_száma ='P891754501-AA..300.1.17492908.20240205'");
     
                System.out.println("ID\tName\t\t\tAge\tMatches");
                System.out.println("==\t================\t===\t=======");
     
                // processing returned data and printing into console
                while(resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + "\t" +
                            resultSet.getString(2) + "\t" +
                            resultSet.getString(3) + "\t" +
                            resultSet.getString(4));
                }
            }
            catch(SQLException sqlex){
                sqlex.printStackTrace();
            }
            finally {
                // Step 3: Closing database connection
                try {
                    if(null != connection) {
                        // cleanup resources, once after processing
                        resultSet.close();
                        statement.close();
     
                        // and then finally close connection
                        connection.close();
                    }
                }
                catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }
            }
         }
    }
	
	class Zarolt_frissit implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
               for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   /*stmt.executeUpdate("update qualitydb.Zarolasok set  Ellenorzes_ido = '" + datatable.getRows().get(szamlalo).getString(1) +"' "
                           + "where ID = '" + datatable.getRows().get(szamlalo).getString(0) + "'");*/
                   stmt.execute("delete from qualitydb.Beolvasott_panelek  "
                           + "where Panelszam = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                   System.out.println(datatable.getRows().get(szamlalo).getString(0));
                   System.out.println("Fut a For");
               }              
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Osszefuz implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
             {
                Workbook workbook = new Workbook();
                workbook.loadFromFile("\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projektek.xlsx");              
                Worksheet sheet = workbook.getWorksheets().get(0);
                String osszefuzve = "";
                for(int szamlalo = 4; szamlalo < sheet.getLastRow(); szamlalo++ )
                {
                    osszefuzve += "\""+ sheet.getRange().get("A"+szamlalo).getText()+ "\",";
                }
                System.out.print(osszefuzve);
             }
            catch(Exception ex2)
             {
                ex2.printStackTrace();
                String hibauzenet = ex2.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
             }
            
         }
    }
	
	class Gyartoi_cikkek implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
  
            try 
            {
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
               Workbook workbook2 = new Workbook();
               Workbook workbook = new Workbook();
               workbook2.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Rendelések.xlsx");
               Worksheet sheet2 = workbook.getWorksheets().get(0);
               
               workbook.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Központi.xlsx");
               Worksheet sheet = workbook.getWorksheets().get(0);
               int cserelve = 0;
               for(int szamlalo = 2; szamlalo < sheet.getLastRow()+1; szamlalo++)
               {
                   for(int szamlalo2 = 2; szamlalo2 < sheet2.getLastRow()+1; szamlalo2++)
                   {
                       if(sheet.getRange().get("B"+szamlalo).getText().equals(sheet2.getRange().get("A"+szamlalo2).getText()) && sheet.getRange().get("E"+szamlalo).getText().equals(sheet2.getRange().get("B"+szamlalo2).getText()))
                       {
                           System.out.println("egyezik a cikk a két excelbe");
                           if(sheet.getRange().get("A"+szamlalo).getText().equals(sheet2.getRange().get("C"+szamlalo2).getText())) {System.out.println("egyezik");}
                           else
                           {
                               sheet.getRange().get("A"+szamlalo).setText(sheet2.getRange().get("C"+szamlalo2).getText().toString());
                               cserelve++;
                               System.out.println(sheet.getRange().get("B"+szamlalo).getText());
                               System.out.println(cserelve);
                           }
                           break;
                       }
                   }
                   
               }
               /*
               sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
               sheet.getAllocatedRange().autoFitColumns();
               sheet.getAllocatedRange().autoFitRows();
               sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
               String hova = System.getProperty("user.home") + "\\Desktop\\Csomagolás után javításra.xlsx";
               workbook.saveToFile(hova, ExcelVersion.Version2016);
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
                */
               Foablak.frame.setCursor(null);     
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Zarolt_frissit_2 implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            Statement stmt2 = null;
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               stmt2 = (Statement) conn.createStatement();
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\betöltendő zárolás.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
               for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   ResultSet rs = stmt2.executeQuery("select hiba_gyokeroka, Gyokerok_intezkedes, Visszaellenorzes from qualitydb.Zarolasok where Papir_sorszama = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                   if(rs.next())
                   {
                       if(rs.getString(1).equals(""))
                       {
                           stmt.executeUpdate("update qualitydb.Zarolasok set  hiba_gyokeroka = '" + datatable.getRows().get(szamlalo).getString(1) +"' "
                                   + "where Papir_sorszama = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                       }
                       if(rs.getString(2).equals(""))
                       {
                           stmt.executeUpdate("update qualitydb.Zarolasok set  Gyokerok_intezkedes = '" + datatable.getRows().get(szamlalo).getString(2) +"' "
                                   + "where Papir_sorszama = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                       }
                       if(rs.getString(3).equals(""))
                       {
                           stmt.executeUpdate("update qualitydb.Zarolasok set  Visszaellenorzes = '" + datatable.getRows().get(szamlalo).getString(3) +"' "
                                   + "where Papir_sorszama = '" + datatable.getRows().get(szamlalo).getString(0) + "'");
                       }
                   }
                   
                   System.out.println("Fut a For");
               }              
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Beolvasott_torles implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Q-NY-006 Zárolási nyilvántartás-2024.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
               for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   stmt.executeUpdate("UPDATE qualitydb.Zarolasok SET hiba_gyokeroka = '" + datatable.getRows().get(szamlalo).getString(5) +"', Gyokerok_intezkedes = '" + datatable.getRows().get(szamlalo).getString(6) +"' "
                           + "where  Tipus like '" + datatable.getRows().get(szamlalo).getString(0) +"%' and Zarolt_db = '" + datatable.getRows().get(szamlalo).getString(1) +"' "
                                   + "and Zarolas_datuma = '" + datatable.getRows().get(szamlalo).getString(2) +"' and Papir_sorszama = '" + datatable.getRows().get(szamlalo).getString(3) +"'");
                   System.out.println("Fut a For");
                   System.out.println(szamlalo);
               }              
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class IFS_kereses implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Techem FUNK szériaszámok.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  
                  String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
                  Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
                  Workbook workbook = new Workbook();
                  Workbook workbook2 = new Workbook();
                  workbook.loadFromFile(excelfile1);
                  Worksheet sheet = workbook.getWorksheets().get(0);               
                  DataTable datatable = new DataTable();
                  datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                  Worksheet sheet2 = workbook2.getWorksheets().get(0);
                  int cellaszam = 1;
                  sheet2.getRange().get("A"+cellaszam).setText("Szériaszám");                  
                  sheet2.getRange().get("B"+cellaszam).setText("Doboz szám");
                  sheet2.getRange().get("C"+cellaszam).setText("Trac fej");
                  /*sheet2.getRange().get("D"+cellaszam).setText("Beolvasási pont");
                  sheet2.getRange().get("E"+cellaszam).setText("Doboz szám");
                  sheet2.getRange().get("F"+cellaszam).setText("Trac fej státusz");*/
                  cellaszam++;
                  ResultSet rs = null;
                  for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                  {
                      rs = stmt.executeQuery("select TRACY_SERIAL_NO, PACKAGE_ID, tracy_status  \n"
                              + "from ifsapp.C_TRACY\n"
                              + "where 3 = 3 and TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"'");
                      System.out.println("Szériaszám: "+ datatable.getRows().get(szamlalo).getString(0) );
                      if(rs.next())
                      {
                          sheet2.getRange().get("A"+cellaszam).setText(rs.getString(1));
                          sheet2.getRange().get("B"+cellaszam).setText(rs.getString(2));
                          sheet2.getRange().get("C"+cellaszam).setText(rs.getString(3));
                          cellaszam++;
                          System.out.println("Fut a while");
                      }
                      
                      //System.out.println("Fut a for");
                      System.out.println(szamlalo);
                  }
                  
                  sheet2.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet2.getAllocatedRange().autoFitColumns();
                  sheet2.getAllocatedRange().autoFitRows();
                  
                  sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                  stmt.close();
                  con.close();
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook3.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Excel_feldolgozo implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {

            try 
            {
               
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Beépülés 122EMV159-CONT.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.setVersion(ExcelVersion.Version2016);
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
               int szam = 1;
               int cellaszam = 2;
               System.out.println(datatable.getRows().get(0).getString(3));
               for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
               {
                   if(szamlalo < (datatable.getRows().size()-1))
                   {
                       
                       if(datatable.getRows().get(szamlalo).getString(3).equals(datatable.getRows().get((szamlalo+1)).getString(3)))
                       {
                           sheet.getRange().get("E"+cellaszam).setText(String.valueOf(szam));
                           szam++;
                       }
                       else
                       {
                           sheet.getRange().get("E"+cellaszam).setText(String.valueOf(szam));
                           szam = 1;
                           System.out.println("nem egyezik");
                           System.out.println("sor " +szamlalo);
                       }
                       cellaszam++;
                   }
                   
               }

               workbook.saveToFile(excelfile1, ExcelVersion.Version2016);
               Foablak.frame.setCursor(null);                                                      
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class FR1200 implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {

            try 
            {
                Connection con = null;
                //Connection con2 = null;
                Statement stmt = null;
                Connection conn = null;
                Statement stmt2 = null;    
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try
                {
                    Workbook workbook = new Workbook();
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    
                    int cellaszam = 1;
                    sheet.getRange().get("A" + cellaszam).setText("Szériaszám");
                    sheet.getRange().get("B" + cellaszam).setText("Kiesés ideje");
                    sheet.getRange().get("C" + cellaszam).setText("Teszt szám");
                    sheet.getRange().get("D" + cellaszam).setText("Failtestname");
                    sheet.getRange().get("E" + cellaszam).setText("Error");
                    sheet.getRange().get("F" + cellaszam).setText("Javítás ideje");
                    sheet.getRange().get("G" + cellaszam).setText("Javított hibakód");
                    sheet.getRange().get("H" + cellaszam).setText("Hibakód leírás");
                    sheet.getRange().get("I" + cellaszam).setText("Pozíció");
                    sheet.getRange().get("J" + cellaszam).setText("Retest ideje");
                    sheet.getRange().get("K" + cellaszam).setText("Eredmény");
                    cellaszam++;
                    ResultSet rs = null;
                    ResultSet rs2 = null;
                    JdbcAdapter jdbcAdapter;
                    DataTable datatable;
                    //Registering the Driver
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa   
                    //Getting the connection
                    String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
                   
                    con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!");                                           //a megadott ip-re csatlakozik a jelszÃ³ felhasznÃ¡lÃ³ nÃ©vvel

                        //System.out.println(hanyszor);
                        String sql = "SELECT panel \n"
                                + "FROM videoton.fkov \n"
                                + "where ido >= '2024.03.11' and ido < '2024.06.13'\n"
                                + "and hely = '114' \n"
                                + "and ok = '0'\n"
                                + "group by panel";            //panelszam       
     
                        //con2 = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                        //String sql3 = "select Panelszam from qualitydb.Beolvasott_panelek where 3 = 3";
                        
                        Statement cstmt = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                        
                        conn = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                        stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);       
                        
                        cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                        rs = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                        datatable = new DataTable();
                        jdbcAdapter = new JdbcAdapter();
                        new JdbcAdapter(); 
                        jdbcAdapter.fillDataTable(datatable, rs);
                        // datatable.getRows().get(szamlalo).getString(0)
                        
                        System.out.println("Datatable mérete: "+ datatable.getRows().size());
                        int javitva = 0;
                        for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
                        {
                            sql = "select  nev, videoton.fkov.*\n"
                                    + "from videoton.fkov\n"
                                    + " inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\n"
                                    + "where 3=3\n"
                                    + "and panel = '"+ datatable.getRows().get(szamlalo).getString(0) +"' \n"
                                    + "and ido > '2024.03.10' and ido < '2024.06.13' \n"
                                    + "order by ido asc"; 
                            
                            cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                            rs = cstmt.getResultSet(); 
                            javitva = 0;
                            while(rs.next())
                            {
                                if(rs.getString(1).equals("AVM FB7530AX LIFE") && rs.getInt(6) == 0)
                                {
                                    sheet.getRange().get("A" + cellaszam).setText(rs.getString(5));
                                    sheet.getRange().get("B" + cellaszam).setText(rs.getString(4));
                                    sheet.getRange().get("C" + cellaszam).setNumberValue(rs.getInt(15));
                                    sheet.getRange().get("D" + cellaszam).setText(rs.getString(18));
                                    sheet.getRange().get("E" + cellaszam).setText(rs.getString(19));
                                    sheet.getRange().get("K" + cellaszam).setText("Failed");
                                    
                                    while(rs.next())
                                    {
                                        //System.out.println("Fut a belső while");
                                        if(rs.getString(1).equals("AVM FB7530AX LIFE") && rs.getInt(6) == 0)
                                        {                                            
                                            sheet.getRange().get("D" + cellaszam).setText(rs.getString(18));
                                            sheet.getRange().get("E" + cellaszam).setText(rs.getString(19));
                                            sheet.getRange().get("K" + cellaszam).setText("Failed");
                                        }
                                        else if(rs.getString(1).equals("AVM FB7530AX LIFE") && rs.getInt(6) != 0)
                                        {
                                            if(javitva == 0)
                                            {
                                                sheet.getRange().get("K" + cellaszam).setText("Sikeres retest javítás nélkül");
                                                break;
                                            }
                                        }
                                        else if(rs.getString(1).contains("Javítás"))
                                        {
                                            sheet.getRange().get("F" + cellaszam).setText(rs.getString(4));
                                            sheet.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                            sheet.getRange().get("I" + cellaszam).setText(rs.getString(14));
                                            sql = "select Hibaleiras from qualitydb.Hibakodok where Hibakod = '"+ rs.getString(7).replace("A", "") +"'";
                                            
                                            stmt2.execute(sql);
                                            rs2 = stmt2.getResultSet();
                                            if(rs2.next())
                                            {
                                                sheet.getRange().get("H" + cellaszam).setText(rs2.getString(1));
                                                System.out.println(rs.getString(7).replace("A", "") +"   "+ rs2.getString(1));
                                            }
                                            
                                            int volte = 0;
                                            while(rs.next())
                                            {
                                                if(rs.getString(1).contains("Javítás"))
                                                {
                                                    sheet.getRange().get("F" + cellaszam).setText(rs.getString(4));
                                                    sheet.getRange().get("G" + cellaszam).setText(rs.getString(7));
                                                    sheet.getRange().get("I" + cellaszam).setText(rs.getString(14));
                                                    sql = "select Hibaleiras from qualitydb.Hibakodok where Hibakod = '"+ rs.getString(7).replace("A", "") +"'";
                                                    
                                                    stmt2.execute(sql);
                                                    rs2 = stmt2.getResultSet();
                                                    if(rs2.next())
                                                    {
                                                        sheet.getRange().get("H" + cellaszam).setText(rs2.getString(1));
                                                        System.out.println(rs.getString(7).replace("A", "") +"   "+ rs2.getString(1));
                                                    }
                                                }
                                                if(rs.getString(1).equals("AVM FB7530AX LIFE"))
                                                {
                                                    sheet.getRange().get("J" + cellaszam).setText(rs.getString(4));
                                                    if(rs.getInt(6) == 0)
                                                    {
                                                        sheet.getRange().get("K" + cellaszam).setText("Sikertelen");
                                                    }
                                                    else
                                                    {
                                                        sheet.getRange().get("K" + cellaszam).setText("Sikeres");                                                               
                                                    }
                                                    volte = 1;
                                                    javitva = 1;                                                        
                                                }
                                                
                                            }
                                            if(volte == 0)
                                            {
                                                sheet.getRange().get("K" + cellaszam).setText("Nem volt még retest");
                                            }
                                            break;
                                        }
                                        else
                                        {
                                            sheet.getRange().get("K" + cellaszam).setText("Failed"); 
                                        }
                                    }
                                    
                                    cellaszam++;
                                }                                
                                //System.out.println("Fut a fő while és a sorszám pedig: "+ szamlalo + " a szériaszám pedig: " + datatable.getRows().get(szamlalo).getString(0));
                            }
                            //System.out.println(szamlalo);
                        }       
                        con.close();
                        
                        sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                        sheet.getAllocatedRange().autoFitColumns();
                        sheet.getAllocatedRange().autoFitRows();
                        sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                        
                        String hova = System.getProperty("user.home") + "\\Desktop\\FB7530AX LIFE kiesések.xlsx";
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
                        Foablak.frame.setCursor(null);
                        JOptionPane.showMessageDialog(null, "Mentve az asztalra", "Info", 1);
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                    String hibauzenet2 = e1.toString();
                    JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
                }
                finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
                {
                    try 
                    {
                      if (stmt != null)
                         con.close();
                    } 
                    catch (SQLException se){}
                    try 
                    {
                      if (con != null)
                         con.close();
                    } 
                    catch (SQLException se) 
                    {
                      se.printStackTrace();
                    }  
                } 
                                                                     
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class IFS_bol_adatok implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                //DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Kiszállítások OLD-al.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  Workbook workbook = new Workbook();
                  workbook.setVersion(ExcelVersion.Version2016);
                  workbook.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Kiszállítások OLD-al.xlsx");
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  
                  ResultSet rs = null;

                  for(int szamlalo = 2; szamlalo < sheet.getLastRow()+1;szamlalo++)
                  {
                      rs = stmt.executeQuery("select ifsapp.INVENTORY_PART_API.Get_Description(contract,part_no)\n"
                              + "from ifsapp.INVENTORY_PART_IN_STOCK_UIV\n"
                              + "WHERE 3 = 3 and part_no = '"+ sheet.getRange().get("E" + szamlalo).getText() +"'\n"
                              + "group by ifsapp.INVENTORY_PART_API.Get_Description(contract,part_no)");
                      if(rs.next())
                      {
                          sheet.getRange().get("F" + szamlalo).setText(rs.getString(1));
                      }
                      System.out.println(szamlalo);
                  }
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  rs.close();
                  stmt.close();
                  con.close();
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook2.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook2.write(output);
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
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Aron implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                //DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Kiszállítások OLD-al.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  Workbook workbook = new Workbook();
                  workbook.setVersion(ExcelVersion.Version2016);
                  workbook.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Kiszállítások OLD-al.xlsx");
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  DataTable datatable = new DataTable();
                  Worksheet sheet2 = workbook.getWorksheets().get(1);
                  //DataTable datatable2 = new DataTable();
                  datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                  //datatable2 = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false );
                  System.out.println(sheet2.getName());
                  
                  ResultSet rs = null;
                  
                  /*rs = stmt.executeQuery("select DESCRIPTION\n"
                          + "from ifsapp.PART_CATALOG\n"
                          + "WHERE 3 = 3 and part_no = '"+ datatable.getRows().get(szamlalo).getString(0) +"'\n"
                          + "group by DESCRIPTION");*/

                  for(int szamlalo = 1; szamlalo < datatable.getRows().size();szamlalo++)
                  {
                      //for(int szamlalo2 = 1; szamlalo2 < datatable2.getRows().size();szamlalo2++)
                      //{
                          //System.out.println(datatable.getRows().get(szamlalo).getString(0));
                          //System.out.println(datatable2.getRows().get(szamlalo2).getString(4));
                          //if(datatable.getRows().get(szamlalo).getString(0).equals(datatable2.getRows().get(szamlalo2).getString(1)))
                          //{
                              //System.out.println(datatable2.getRows().get(szamlalo2).getString(1));
                              //System.out.println(datatable2.getRows().get(szamlalo2).getString(4));
                              /*CellRange cell = sheet2.getCellRange(szamlalo2, 6);
                              if(cell.hasFormula()) {
                              cell.convertToNumber();
                              if(cell.getFormulaValue().toString() != null)
                              {
                                  sheet.getRange().get("G" + (szamlalo+1)).setText(cell.getFormulaValue().toString());
                              }
                              else
                              {
                                  sheet.getRange().get("G" + (szamlalo+1)).setText(sheet2.getCellRange(szamlalo2, 6).toString());
                              }
                              }
                              System.out.println("Találat");
                          }*/
                          
                      //} 
                      rs = stmt.executeQuery("select ifsapp.inventory_part_api.Get_Second_Commodity('VEAS', Part_no)\n"
                              + "from ifsapp.PART_CATALOG\n"
                              + "WHERE 3 = 3 and part_no = '"+ datatable.getRows().get(szamlalo).getString(0) +"'\n"
                              + "group by ifsapp.inventory_part_api.Get_Second_Commodity('VEAS', Part_no)");
                      if(rs.next())
                      {
                          sheet.getRange().get("H" + (szamlalo+1)).setText(rs.getString(1));
                      }
                      System.out.println("Találat");
                  }
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  //rs.close();
                  stmt.close();
                  con.close();
                  /*
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook2.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook2.write(output);
                      output.close();
                  }    */                   
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1); 
                  con.close();  
                  Foablak.frame.setCursor(null);  
                  }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Norma implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                //DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Normaidő cikkentént.xlsx";

                  Workbook workbook = new Workbook();
                  Workbook workbook2 = new Workbook();
                  workbook.setVersion(ExcelVersion.Version2016);
                  workbook.loadFromFile(System.getProperty("user.home") + "\\Desktop\\Kiszállítások db számmal.xlsx");
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  DataTable datatable = new DataTable();
                  Worksheet sheet2 = workbook2.getWorksheets().get(0);
                  //DataTable datatable2 = new DataTable();
                  datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                  //datatable2 = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false );
                  int cellaszam = 1;
                  sheet2.getRange().get("A" + cellaszam).setText("Cikkszám");
                  sheet2.getRange().get("B" + cellaszam).setText("Megnevezés");
                  sheet2.getRange().get("C" + cellaszam).setText("CCS2");
                  sheet2.getRange().get("D" + cellaszam).setText("Kiszállítás ideje");
                  sheet2.getRange().get("E" + cellaszam).setText("KPISMDQTY");
                  sheet2.getRange().get("F" + cellaszam).setText("KPIASSTIME");
                  sheet2.getRange().get("G" + cellaszam).setText("Kiszállított db szám");
                  cellaszam++;
                  
                  for(int szamlalo = 1; szamlalo < datatable.getRows().size();szamlalo++)
                  {
                      for(int szamlalo2 = szamlalo+1; szamlalo2 < datatable.getRows().size();szamlalo2++)
                      {
                          if(datatable.getRows().get(szamlalo).getString(0).equals(datatable.getRows().get(szamlalo2).getString(0)) 
                                  && datatable.getRows().get(szamlalo).getString(1).equals(datatable.getRows().get(szamlalo2).getString(1))
                                  && datatable.getRows().get(szamlalo).getString(2).equals(datatable.getRows().get(szamlalo2).getString(2))
                                  && datatable.getRows().get(szamlalo).getString(3).equals(datatable.getRows().get(szamlalo2).getString(3))
                                  && datatable.getRows().get(szamlalo).getString(10).equals(datatable.getRows().get(szamlalo2).getString(10))
                                  && datatable.getRows().get(szamlalo).getString(9).equals(datatable.getRows().get(szamlalo2).getString(9)))
                          {
                              sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(3));
                              sheet2.getRange().get("B" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(9));
                              sheet2.getRange().get("C" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(4));
                              sheet2.getRange().get("D" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(1));
                              sheet2.getRange().get("G" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(10));
                              if(szamlalo < 13790)
                              if(datatable.getRows().get(szamlalo).getString(6).equals("KPISMDQTY"))
                              {
                                  sheet2.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(7));
                                  sheet2.getRange().get("F" + cellaszam).setText(datatable.getRows().get(szamlalo+1).getString(7));
                              }
                              else
                              {
                                  sheet2.getRange().get("F" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(7));
                                  sheet2.getRange().get("E" + cellaszam).setText(datatable.getRows().get(szamlalo+1).getString(7));
                              }
                              
                          break;    
                          }
                          System.out.println(szamlalo2);
                      }
                      cellaszam++;
                      System.out.println("Fut a külső for");
                  }
                  sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:J1"));
                  sheet2.getAllocatedRange().autoFitColumns();
                  sheet2.getAllocatedRange().autoFitRows();
                  
                  sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook2.saveToFile(menteshelye, ExcelVersion.Version2016);
                  
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook3.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook3.write(output);
                      output.close();
                  }                       
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1);  
                  Foablak.frame.setCursor(null);  
                  }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Telecom implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                //DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\telecom heti.xlsx";

                  Workbook workbook = new Workbook();
                  workbook.setVersion(ExcelVersion.Version2016);
                  workbook.loadFromFile(menteshelye);
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  DataTable datatable = new DataTable();
                  //DataTable datatable2 = new DataTable();
                  datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                  //datatable2 = sheet2.exportDataTable(sheet2.getAllocatedRange(), false, false );
                  
                  int toroltsorok = 0;
                  for(int szamlalo = 1; szamlalo < datatable.getRows().size();szamlalo++)
                  {
                      String[] datum = datatable.getRows().get(szamlalo).getString(7).split(" ");
                      String[] ido = datum[3].split(":");
                      //System.out.println("Óra: "+ ido[0]);
                      //System.out.println("Perc: "+ ido[1]);
                      if(Integer.valueOf(ido[0]) > 21)
                      {
                          sheet.deleteRow(szamlalo+1);
                          sheet.getRange().get("H" + (szamlalo+1)).setText("");
                          System.out.println("Törlés "+ datatable.getRows().get(szamlalo).getString(7));
                          toroltsorok++;
                      }
                      if(Integer.valueOf(ido[0]) < 5)
                      {
                          sheet.deleteRow(szamlalo+1);
                          System.out.println("Törlés "+ datatable.getRows().get(szamlalo).getString(7));
                          sheet.getRange().get("H" + (szamlalo+1)).setText("");
                          toroltsorok++;
                      }
                      if(Integer.valueOf(ido[0]) == 5)
                      {
                          if(Integer.valueOf(ido[1]) < 55)
                          {
                              sheet.deleteRow(szamlalo+1);
                              sheet.getRange().get("H" + (szamlalo+1)).setText("");
                              System.out.println("Törlés "+ datatable.getRows().get(szamlalo).getString(7));
                              toroltsorok++;
                          }
                      }
                      System.out.println(szamlalo);
                      //System.out.println(datatable.getRows().get(szamlalo).getString(7));
                  }
                  System.out.println("Törölt sorok száma: " +toroltsorok);
                  
                  sheet.getAutoFilters().setRange(sheet.getCellRange("A1:J1"));
                  sheet.getAllocatedRange().autoFitColumns();
                  sheet.getAllocatedRange().autoFitRows();
                  
                  sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                  
                  workbook.saveToFile(menteshelye, ExcelVersion.Version2016);
                  
                  
                  FileInputStream fileStream = new FileInputStream(menteshelye);
                  try (XSSFWorkbook workbook3 = new XSSFWorkbook(fileStream)) 
                  {
                      for(int i = workbook3.getNumberOfSheets()-1; i>0 ;i--)
                      {    
                          workbook3.removeSheetAt(i); 
                      }      
                      FileOutputStream output = new FileOutputStream(menteshelye);
                      workbook3.write(output);
                      output.close();
                  }                       
                  JOptionPane.showMessageDialog(null, "Kész! \n Mentve az asztalra IFS utolsó folyamat.xlsx néven!", "Info", 1);  
                  Foablak.frame.setCursor(null);  
                  }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                        //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Vevoi_frissit implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
            Statement stmt2 = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               stmt2 = (Statement) conn.createStatement();
               String excelfile1 =  "\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\Projekt- mérnök.xlsx";                            
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
               ResultSet rs = null;
               
               rs = stmt2.executeQuery("select id, felelos, vevo from qualitydb.Vevoireklamacio_alap where 3 = 3");
               
               while(rs.next())
               {
                   if(rs.getString(2).equals(";"))
                   {
                       for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
                       {
                           if(rs.getString(3).toUpperCase().equals(datatable.getRows().get(szamlalo).getString(0).toUpperCase()))
                           {
                               stmt.executeUpdate("update qualitydb.Vevoireklamacio_alap set  Felelos = ';" + datatable.getRows().get(szamlalo).getString(1) +"' "
                                       + "where ID = '" + rs.getString(1) + "'");
                               System.out.println("Találat");
                           }
                           System.out.println("Fut a for");
                       }     
                   }
                   System.out.println("Fut a while");
               }
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class IFS_lekerdezes_Marcsi implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                
                Workbook workbook = new Workbook();
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    
                    Workbook workbook2 = new Workbook();
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                     
                      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                      Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                          
                      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                      //Statement stmt = con.createStatement();                      
                      int cellaszam = 1;
                      sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                      sheet2.getRange().get("B" + cellaszam).setText("Tracy Spac Code");
                      sheet2.getRange().get("C" + cellaszam).setText("Numerikus érték");
                      sheet2.getRange().get("D" + cellaszam).setText("Also korlát");
                      sheet2.getRange().get("E" + cellaszam).setText("Felső korlát");
                      sheet2.getRange().get("F" + cellaszam).setText("Pass");
                      cellaszam++;
                      
                      for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                      {
                          /*ResultSet rs = stmt.executeQuery("select TRACY_SERIAL_NO, TRACY_SPEC_CODE, NUM_VALUE, MIN_VALUE, MAX_VALUE, PASS  \n"
                                  + "from ifsapp.C_SPEC_TRACY_UNIT_OVW  \n"
                                  + "  where 3 = 3\n"
                                  + "  and TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"' and (TRACY_SPEC_CODE like 'TFFK33-Seg1%' or TRACY_SPEC_CODE = 'TFFK33-Mkq')");
                          System.out.println("Fut..");
                          while(rs.next())
                          {                        
                              sheet2.getRange().get("A" + cellaszam).setText(rs.getString(1));
                              sheet2.getRange().get("B" + cellaszam).setText(rs.getString(2));
                              sheet2.getRange().get("C" + cellaszam).setText(rs.getString(3));
                              sheet2.getRange().get("D" + cellaszam).setText(rs.getString(4));
                              sheet2.getRange().get("E" + cellaszam).setText(rs.getString(5));
                              sheet2.getRange().get("F" + cellaszam).setText(rs.getString(6));
                              cellaszam++;
                          }                */
                          System.out.println("Fut..");
                      }
                      
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\IFS utolsó folyamat.xlsx";
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
                }
                Foablak.frame.setCursor(null);  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class Tracy_kereses_loxone implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection con = null;
            Statement stmt = null;
            try
            {
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                //ResultSet rs;
                Workbook workbook = new Workbook();;
                workbook.setVersion(ExcelVersion.Version2016); 
                workbook.loadFromFile(fajl.getAbsolutePath());
                //Registering the Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa
                Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));     
                //Getting the connection
                
                con = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                stmt = (Statement) con.createStatement();
                System.out.println("Connection established......");
                Worksheet sheet = workbook.getWorksheets().get(0);
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                DataTable datatable = new DataTable();
                datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
                {
                    /*String sql = "select hibakod from videoton.fkov \r\n"
                            + "WHERE 3 = 3\r\n"
                            + "and panel = '"+ sheet.getRange().get("A" + szamlalo).getText() +"' and hely = 26 "
                                    + "and ido = '"+ sheet.getRange().get("B" + szamlalo).getText() +"'";
                    System.out.println("fut a for: "+szamlalo);
                    cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                    rs = cstmt.getResultSet();
                    if(rs.next())
                    {
                        try
                        {
                            sheet.getRange().get("F" + szamlalo).setText(rs.getString(1).substring(20, 40));
                            sheet.getRange().get("G" + szamlalo).setText(rs.getString(1).substring(60, 80));
                        }
                        catch(Exception e1)
                        {
                            System.out.println("Túl hosszú az xml: "+szamlalo); //26 19 25
                        }
                    }
                    String[] datum = sheet.getRange().get("F" + szamlalo).getText().split(" ");
                    String[] datum2 = datum[0].split("\\.");
                    sheet.getRange().get("F" + szamlalo).setText(datum2[2]+"."+datum2[1]+"."+datum2[0]+ " "+ datum[1]);
                    
                    datum = sheet.getRange().get("G" + szamlalo).getText().split(" ");
                    datum2 = datum[0].split("\\.");
                    sheet.getRange().get("G" + szamlalo).setText(datum2[2]+"."+datum2[1]+"."+datum2[0]+ " "+ datum[1]);
                    System.out.println("fut a for: "+sheet.getRange().get("F" + szamlalo).getText());*/
                    
                    String sql = "update qualitydb.Zarolasok set "
                            + "Projekt = '"+ datatable.getRows().get(szamlalo).getString(1) +"', "
                            + "Tipus = '"+ datatable.getRows().get(szamlalo).getString(2) +"', "
                            + "Eszleles_helye = '"+ datatable.getRows().get(szamlalo).getString(3) +"', "
                            + "Muszak = '"+ datatable.getRows().get(szamlalo).getString(4) +"', "
                            + "Zarolo_mernok = '"+ datatable.getRows().get(szamlalo).getString(5) +"', "
                            + "Zarolt_db = '"+ datatable.getRows().get(szamlalo).getString(6) +"', "
                            + "Hol_van = '"+ datatable.getRows().get(szamlalo).getString(7) +"', "
                            + "Zarolas_oka = '"+ datatable.getRows().get(szamlalo).getString(8) +"', "
                            + "Azonnali_intezkedes = '"+ datatable.getRows().get(szamlalo).getString(9) +"', "
                            + "Zarolas_datuma = '"+ datatable.getRows().get(szamlalo).getString(10) +"', "
                            + "Papir_sorszama = '"+ datatable.getRows().get(szamlalo).getString(11) +"', "
                            + "Zarolta = '"+ datatable.getRows().get(szamlalo).getString(12) +"', "
                            + "Valogatas_eredmenye = '"+ datatable.getRows().get(szamlalo).getString(13) +"', "
                            + "Ujraellenorzes_datuma = '"+ datatable.getRows().get(szamlalo).getString(14) +"', "
                            + "Ellenorzes_ido = '"+ datatable.getRows().get(szamlalo).getString(15) +"', "
                            + "Technikusi_beavatkozas = '"+ datatable.getRows().get(szamlalo).getString(16) +"', "
                            + "Felelos_terulet = '"+ datatable.getRows().get(szamlalo).getString(17) +"', "
                            + "Felelos = '"+ datatable.getRows().get(szamlalo).getString(18) +"', "
                            + "Hiba_gyokeroka = '"+ datatable.getRows().get(szamlalo).getString(19) +"', "
                            + "Gyokerok_intezkedes = '"+ datatable.getRows().get(szamlalo).getString(20) +"', "
                            + "B2_zarolas = '"+ datatable.getRows().get(szamlalo).getString(21) +"', "
                            + "Visszaellenorzes = '"+ datatable.getRows().get(szamlalo).getString(22) +"', "
                            + "Lezaras_datuma = '"+ datatable.getRows().get(szamlalo).getString(23) +"', "
                            + "ME_szam = '"+ datatable.getRows().get(szamlalo).getString(24) +"', "
                            + "Ellenorzott_db = '"+ datatable.getRows().get(szamlalo).getString(25) +"', "
                            + "Hibacsoport = '"+ datatable.getRows().get(szamlalo).getString(26) +"', "
                            + "Hibatipus = '"+ datatable.getRows().get(szamlalo).getString(27) +"', "
                            + "Email = '"+ datatable.getRows().get(szamlalo).getString(28) +"', "
                            + "Email2 = '"+ datatable.getRows().get(szamlalo).getString(29) +"', "
                            + "Hatarido = '"+ datatable.getRows().get(szamlalo).getString(30) +"' "
                            + "where id = '"+ datatable.getRows().get(szamlalo).getString(0) +"' ";
                    System.out.println("fut a for: "+szamlalo);
                    cstmt.execute(sql);     
                    
                }

                //Get the first worksheet
                sheet.getAutoFilters().setRange(sheet.getCellRange("A1:P1"));
                sheet.getAllocatedRange().autoFitColumns();
                sheet.getAllocatedRange().autoFitRows();
                    
                sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // fÃ©lkÃ¶vÃ©r beÃ¡llÃ­tÃ¡s
                 
                //result.close();
                cstmt.close();
                con.close();
                //workbook.setActiveSheetIndex(0);
                //JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                fajl = mentes_helye.getSelectedFile();
                workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);
                if(fajl.getName().contains(".xlsx"))
                {
                    workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                        workbook2.write(output);
                        output.close();
                    }
                }
                else
                {
                    workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                    FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                    try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                    {
                        for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                        {    
                            workbook2.removeSheetAt(i); 
                        }      
                        FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                        workbook2.write(output);
                        output.close();
                    }
                }
                Foablak.frame.setCursor(null); 
                JOptionPane.showMessageDialog(null, "Mentés sikeres", "Infó", 1);
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
                String hibauzenet2 = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet2);
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
            finally                                                                     //finally rÃ©sz mindenkÃ©ppen lefut, hogy hiba esetÃ©n is lezÃ¡rja a kacsolatot
            {
                try 
                {
                  if (stmt != null)
                     con.close();
                } 
                catch (SQLException se) {}
                try 
                {
                  if (con != null)
                     con.close();
                } 
                catch (SQLException se) 
                {
                  se.printStackTrace();
                }  
            }
         }
    }
	
	class Qualitydb_frissit implements ActionListener                                                                                      
    {
        public void actionPerformed(ActionEvent e)
         {
            Connection conn = null;
            Statement stmt = null;
          
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
               conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
               stmt = (Statement) conn.createStatement();
               
               stmt.execute("select id, zarolas_oka from qualitydb.Zarolasok where 3 = 3 order by id asc");
               
               ResultSet rs = stmt.getResultSet();
               SQA_SQL frissit = new SQA_SQL();
               
               while(rs.next())
               {
                   System.out.println(rs.getString(1));
                   System.out.println(rs.getString(2));
                   
                   
                   frissit.mindenes("update qualitydb.Zarolasok set zarolas_oka = '"+ rs.getString(2) + ";-" +"' where "
                           + "ID = '"+ rs.getString(1) +"'");
               }              
               Foablak.frame.setCursor(null);                        
               stmt.close();
               conn.close();                
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Loxone_megnevezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            String hiba = "";
            try
            {              
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                
                Workbook workbook = new Workbook();
                if(fajl != null)
                {
                    Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    workbook.loadFromFile(fajl.getAbsolutePath());
                    Worksheet sheet = workbook.getWorksheets().get(0);
                    DataTable datatable = new DataTable();
                    datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );
                    
                    Workbook workbook2 = new Workbook();
                    workbook2.setVersion(ExcelVersion.Version2016);
                    Worksheet sheet2 = workbook2.getWorksheets().get(0);
                     
                      DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                      Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                          
                      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                      Statement stmt = con.createStatement();                      
                      int cellaszam = 1;
                      sheet2.getRange().get("A" + cellaszam).setText("Szériaszám");
                      sheet2.getRange().get("B" + cellaszam).setText("Cikkszám");
                      sheet2.getRange().get("C" + cellaszam).setText("Megnevezés");                     
                      cellaszam++;
                      
                      for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                      {
                          ResultSet rs = stmt.executeQuery("select part_no, ifsapp.INVENTORY_PART_API.Get_Description(CONTRACT, part_no)\n"
                                  + "from ifsapp.C_TRACY\n"
                                  + "where\n"
                                  + "TRACY_SERIAL_NO = '"+ datatable.getRows().get(szamlalo).getString(0) +"'");
                          System.out.println("Fut.."+ szamlalo );
                          if(rs.next())
                          {
                              hiba = rs.getString(1) +" "+ rs.getString(2);
                              sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));
                              sheet2.getRange().get("B" + cellaszam).setText(rs.getString(1));
                              sheet2.getRange().get("C" + cellaszam).setText(rs.getString(2));                            
                              cellaszam++;
                              System.out.println(rs.getString(1) +" "+ rs.getString(2));
                          }
                          else
                          {
                              sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0));                          
                              cellaszam++;
                          }
                          
                      }
                      
                      sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:P1"));
                      sheet2.getAllocatedRange().autoFitColumns();
                      sheet2.getAllocatedRange().autoFitRows();
                      sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                      String hova = System.getProperty("user.home") + "\\Desktop\\IFS utolsó folyamat.xlsx";
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
                }
                Foablak.frame.setCursor(null);  
            }           
            catch(Exception e1)
            { 
                System.out.println(e1);
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                System.out.println(hiba);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                              //kiírja a hibaüzenetet
            }  
                               
         }
    }
	
	class TXT_keszito implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {          
            try 
            {               
               String excelfile1 = System.getProperty("user.home") + "\\Desktop\\Eredmények.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               Workbook workbook = new Workbook();
               workbook.loadFromFile(excelfile1);
               Worksheet sheet = workbook.getWorksheets().get(0);               
               DataTable datatable = new DataTable();
               datatable = sheet.exportDataTable(sheet.getAllocatedRange(), false, false );  
               for(int szamlalo = 1; szamlalo < datatable.getRows().size(); szamlalo++)
               {
 
                   try (PrintWriter out = new PrintWriter(System.getProperty("user.home") + "\\Desktop\\"+datatable.getRows().get(szamlalo).getString(4) +".txt")) {
                       out.println(datatable.getRows().get(szamlalo).getString(7));
                   }
                   
                   System.out.println("Fut a For");
               }              
               Foablak.frame.setCursor(null);                                                      
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
            }
         }
    }
	
	class Beolvasott_atnez implements ActionListener                                                                                      //csv-t gyárt a gomb
    {

        @SuppressWarnings("resource")
        public void actionPerformed(ActionEvent e)
         {  
            Connection con = null;
            Connection conn = null;
            Statement stmt = null;
            int javitasokszama = 0;
            String kizarando = "899";
            String kizarando2 = "1299";
            String kizarando3 = "A";
            String kizarando4 = "1250";
            int egyformakezi = 0;
            int egyformagepes = 0;
            Workbook workbook = new Workbook();
            workbook.setVersion(ExcelVersion.Version2016);
            //workbook.loadFromFile(excelfile1);
            Worksheet sheet = workbook.getWorksheets().get(0);
            try 
            {
                ResultSet result = null;
                ResultSet result2 = null;
                ResultSet rs = null;
                JdbcAdapter jdbcAdapter;
                JdbcAdapter jdbcAdapter2;
                DataTable datatable;
                //DataTable datatable2;
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());                                                       //jdbc mysql driver meghÃ­vÃ¡sa   
                //Getting the connection
                String mysqlUrl = "jdbc:mysql://192.168.5.145/";                                                                        //mysql szerver ipcÃ­mÃ©hez valÃ³ csatlakozÃ¡s
               
                con = DriverManager.getConnection(mysqlUrl, "quality", "Qua25!"); 
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                Statement cstmt = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                
                Statement cstmt2 = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                stmt = (Statement) conn.createStatement();
                
                //Registering the Driver
 
               //String excelfile1 = System.getProperty("user.home") + "\\Desktop\\panelek.xlsx";                             
               Foablak.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                
               
               int cellaszam = 1;
               sheet.getRange().get("A" + cellaszam).setText("Szériaszám");
               sheet.getRange().get("B" + cellaszam).setText("Beolvasás ideje");
               sheet.getRange().get("C" + cellaszam).setText("Eredmény");
               sheet.getRange().get("D" + cellaszam).setText("Új eredmény");
               sheet.getRange().get("E" + cellaszam).setText("Utolsó beolvasás helye");
               cellaszam++;
               
               String sql3 = "select * from qualitydb.Beolvasott_panelek where 3 = 3 and Projekt = 'AVM' order by id asc";
               rs = stmt.executeQuery(sql3);
               int szamlalo3 = 1;
               while(rs.next())
               {
                   javitasokszama = 0;
                   egyformakezi = 0;
                   egyformagepes = 0;
                   String panelszam = rs.getString(2);
                   String sql = "select fkovsor.nev, videoton.fkov.panel, "                   
                           + "videoton.fkov.hibakod, videoton.fkov.poz, videoton.fkov.ido \n"                   
                           + "from videoton.fkov \n"
                           + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                           + " where panel = '"+ panelszam + "' and  fkovsor.nev like 'Javítás%' order by ido asc";            //panelszam       
                   
                   /*String sql = "select fkovsor.nev as 'Folyamat',\r\n"
                           + " videoton.fkov.ido as 'Időpont',\r\n"
                           + " videoton.fkov.panel as 'Panelszám',\r\n"
                           + "    if(videoton.fkov.ok in ('-1', '1'), \"Rendben\", \"Hiba\") as 'eredmény',\r\n"
                           + "    videoton.fkov.failtestnames as 'Hibakód'\r\n"
                           + "from videoton.fkov\r\n"
                           + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely\r\n"
                           + " where   videoton.fkov.panel = '"+ panelszam + "'\r\n"
                           + " and fkovsor.nev = 'INSTAGRID FCT'"; */    
                   String sql2 = "select fkovsor.nev, videoton.fkov.panel, videoton.fkov.error, videoton.fkov.ido \n"                                                      
                           + "from videoton.fkov \n"
                           + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                           + " where panel = '"+ panelszam + "' order by videoton.fkov.ido desc limit 1";           
                   
                   //con2 = DriverManager.getConnection("jdbc:mysql://172.20.22.29", "veasquality", "kg6T$kd14TWbs9&gd");
                   //String sql3 = "select Panelszam from qualitydb.Beolvasott_panelek where 3 = 3";
                   
                   
                   cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                   result = cstmt.getResultSet();                                                                                              //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak           
                   datatable = new DataTable();
                   //Ablak.eredmeny_mezo.setText("");
                   jdbcAdapter = new JdbcAdapter();
                   jdbcAdapter2 = new JdbcAdapter(); 
                   jdbcAdapter.fillDataTable(datatable, result);
                   //String alkatresz = "";
                   //int bennevan = 0;
                   //String szoveg ="Folyamat                    Időpont           Erdedmény         Hibakód  \n";
                   /*for(int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++)
                   {
                       szoveg += datatable.getRows().get(szamlalo).getString(0)+"       "+datatable.getRows().get(szamlalo).getString(1)
                               +"       "+datatable.getRows().get(szamlalo).getString(3)+"       "+datatable.getRows().get(szamlalo).getString(4) +"\n";
                       /*if(panelszam.equals(datatable.getRows().get(szamlalo).getString(0)))                  
                       {
                            bennevan++;
                       }
                   }
                   Ablak.eredmeny_mezo.setText(szoveg);
                   */
                   //System.out.println(panelszam.substring(11,13));
                   if(panelszam.length() > 12) {
                    if(panelszam.substring(11,13).equals("20")) {
                           //Ablak.eredmeny_mezo.setText("Gazdasági Totálkár!! \n Túl öreg temék!! \n Selejtezhető");
                        System.out.println("Selejt találva "+ panelszam);
                        sheet.getRange().get("A" + cellaszam).setText(panelszam);
                        sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                        sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                        sheet.getRange().get("D" + cellaszam).setText("Gazdasági Totálkár!! \n Túl öreg temék!! \n Selejtezhető");
                        cstmt2.execute(sql2); 
                        result2 = cstmt2.getResultSet();                            
                        DataTable datatable2 = new DataTable();
                        jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                        if(datatable.getRows().size() > 0)
                        {
                            sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                        }                      
                        cellaszam++;
                    }
                   }
                   if(panelszam.startsWith("891593000GE")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20095AB")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("891593000GD")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20042AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20168AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20141AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20095AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A20020AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("891593000GC")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A21125AA")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("891593000GB")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2); 
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }
                   else if(panelszam.startsWith("111A19106AD")) {
                       //Ablak.eredmeny_mezo.setText("Kifutott termék!! \n Selejtezhető");
                       System.out.println("Selejt találva "+ panelszam);
                       sheet.getRange().get("A" + cellaszam).setText(panelszam);
                       sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                       sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                       sheet.getRange().get("D" + cellaszam).setText("Kifutott termék!! \n Selejtezhető");
                       cstmt2.execute(sql2);
                       result2 = cstmt2.getResultSet();                            
                       DataTable datatable2 = new DataTable();
                       jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                       if(datatable.getRows().size() > 0)
                       {
                           sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                       }                      
                       cellaszam++;
                   }               
                   else
                   {     
                       if(datatable.getRows().size() > 0)
                       {
                           /*if(bennevan > 0)
                           {
                               Ablak.eredmeny_mezo.setText("1x már beolvastad a panleszámot!");
                               Ablak.eredmeny_mezo.setForeground(new Color(0, 0, 255)); 
                           }*/                     
                           for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
                           {
                               if(szamlalo < datatable.getRows().size()-1)
                               {
                                   if(datatable.getRows().get(szamlalo).getString(2) != null)
                                   {
                                       char[] data = {datatable.getRows().get(szamlalo).getString(2).charAt(0)};
                                       String keresett = new String(data);
                                       String[] datum = datatable.getRows().get(szamlalo).getString(0).split(" ");
                                       String[] datum2 = datatable.getRows().get(szamlalo+1).getString(0).split(" ");
                                       if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                                       {
                                           //System.out.println(kizarando);                
                                       }
                                       else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                                       {
                                           //System.out.println(kizarando2);                      
                                       }
                                       else if(kizarando4.equals(datatable.getRows().get(szamlalo).getString(2)))
                                       {
                                           //System.out.println(kizarando4);                   
                                       }
                                       else if(kizarando3.equals(keresett))
                                       {
                                           //System.out.println(datatable.getRows().get(szamlalo).getString(2));                 
                                       }
                                       else if(datatable.getRows().get(szamlalo).getString(0).equals("Javítás SMD") && datatable.getRows().get(szamlalo+1).getString(0).equals("Javítás SMD") && datum[0].equals(datum2[0]))
                                       {
                                           //System.out.println("Egyformák");
                                           if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando);                
                                           }
                                           else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando2);                      
                                           }
                                           else if(kizarando4.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando4);                   
                                           }
                                           else if(kizarando3.equals(keresett))
                                           {
                                               //System.out.println(datatable.getRows().get(szamlalo).getString(2));                 
                                           }
                                           else
                                           {
                                               
                                               //System.out.println("Javítás gépes"+ datatable.getRows().get(szamlalo).getString(2));
                                               //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                                               egyformagepes++;
                                           }
                                       }
                                       else if(datatable.getRows().get(szamlalo).getString(0).equals("Javítás kézi") && datatable.getRows().get(szamlalo+1).getString(0).equals("Javítás kézi") && datum[0].equals(datum2[0]))
                                       {
                                           //System.out.println("Egyformák");
                                           if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando);                
                                           }
                                           else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando2);                      
                                           }
                                           else if(kizarando4.equals(datatable.getRows().get(szamlalo).getString(2)))
                                           {
                                               //System.out.println(kizarando4);                   
                                           }
                                           else if(kizarando3.equals(keresett))
                                           {
                                               //System.out.println(datatable.getRows().get(szamlalo).getString(2));                 
                                           }
                                           else
                                           {
                                               //System.out.println("Javítás kézi"+ datatable.getRows().get(szamlalo).getString(2));
                                               //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                                               egyformakezi++;
                                           }
                                       }
                                       else
                                       {
                                           System.out.println("számláló "+ szamlalo);
                                           if(szamlalo > 0)
                                           {
                                               if(datatable.getRows().get(szamlalo).getString(0).equals("Javítás SMD") && datatable.getRows().get(szamlalo-1).getString(0).equals("Javítás SMD") && datum[0].equals(datum2[0]))
                                               {
                                                   
                                               }
                                               else if(datatable.getRows().get(szamlalo).getString(0).equals("Javítás SMD") && datatable.getRows().get(szamlalo-1).getString(0).equals("Javítás SMD") && datum[0].equals(datum2[0]))
                                               {
                                                   
                                               }
                                               else
                                               {
                                                   System.out.println("Javítás kódja "+ datatable.getRows().get(szamlalo).getString(2));
                                                   javitasokszama++;
                                               }
                                           }
                                           else
                                           {
                                               System.out.println("Javítás kódja "+ datatable.getRows().get(szamlalo).getString(2));
                                               javitasokszama++;
                                           }
                                           
                                           //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                                       }
                                   }
                               }
                               else
                               {
                                   char[] data = {datatable.getRows().get(szamlalo).getString(2).charAt(0)};
                                   String keresett = new String(data);                             
                                   if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                                   {
                                                              
                                   }
                                   else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                                   {
                                                              
                                   }
                                   else if(kizarando4.equals(datatable.getRows().get(szamlalo).getString(2)))
                                   {
                                                              
                                   }
                                   else if(kizarando3.equals(keresett))
                                   {
                                                              
                                   }                               
                                   else
                                   {
                                       javitasokszama++;
                                       //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                                   }
                               }
                           }
                           if(egyformakezi > 0)
                           {
                               javitasokszama++;
                           }
                           if(egyformagepes > 0)
                           {
                               javitasokszama++;
                           }
                           //sheet.getRange().get("B" + szamlalo2).setNumberValue(javitasokszama);
                           System.out.println("Javítások száma: "+ javitasokszama);
                           if(javitasokszama >= 5)
                           {
                               //Ablak.eredmeny_mezo.setText("Gazdasági Totálkár a sok javítás miatt!! \n Selejtezhető");                                  
                               sheet.getRange().get("A" + cellaszam).setText(panelszam);
                               sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                               sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                               sheet.getRange().get("D" + cellaszam).setText("Gazdasági Totálkár a sok javítás miatt!! \n Selejtezhető");
                               cstmt2.execute(sql2); 
                               result2 = cstmt2.getResultSet();                            
                               DataTable datatable2 = new DataTable();
                               jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                               if(datatable.getRows().size() > 0)
                               {
                                   sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                               }                               
                               cellaszam++;
                               System.out.println("Selejt találva "+ panelszam +" "+ datatable2.getRows().get(0).getString(0));
                           }
                           
                       }
                       else
                       {
                           String levagva = panelszam.substring(0,panelszam.length()-1);
                           sql = "select panel\r\n"
                                   + "from videoton.fkovavm\r\n"
                                   + "WHERE ain = '"+ levagva + "'"; 
                           cstmt.execute(sql);                                                                                                     //sql llekérdezés futtatása                    
                           result = cstmt.getResultSet();
                           String szeriaszam = null;
                           if(result.next())
                           {
                               szeriaszam = result.getString(1);
                           }
                           sql = "select fkovsor.nev, videoton.fkov.panel, "                   
                                   + "videoton.fkov.hibakod, videoton.fkov.poz \n"                   
                                   + "from videoton.fkov \n"
                                   + "inner join videoton.fkovsor on videoton.fkovsor.azon = videoton.fkov.hely \n"
                                   + " where panel = '"+ szeriaszam + "' and  fkovsor.nev like 'Javítás%'";
                           jdbcAdapter.fillDataTable(datatable, result);
                           if(szeriaszam == null){}
                           else
                           {
                               //Ablak.panelszam_mezo.setText(szeriaszam);
                           }
                           
                           if(datatable.getRows().size() > 0)
                           {
                               /*if(bennevan > 0)
                               {
                                   Ablak.eredmeny_mezo.setText("1x már beolvastad a panleszámot!");
                                   Ablak.eredmeny_mezo.setForeground(new Color(0, 0, 255)); 
                               }*/
                               
                               for (int szamlalo = 0; szamlalo < datatable.getRows().size(); szamlalo++) 
                               {
                                   if(datatable.getRows().get(szamlalo).getString(2) != null)
                                   {
                                       char[] data = {datatable.getRows().get(szamlalo).getString(2).charAt(0)};
                                       String keresett = new String(data);
                                       if(kizarando.equals(datatable.getRows().get(szamlalo).getString(2)))
                                       {
                                                                  
                                       }
                                       else if(kizarando2.equals(datatable.getRows().get(szamlalo).getString(2)))
                                       {
                                                                  
                                       }
                                       else if(kizarando3.equals(keresett))
                                       {
                                                                  
                                       }
                                       else
                                       {
                                           javitasokszama++;
                                           //alkatresz += datatable.getRows().get(szamlalo).getString(3) + " ";
                                       }
                                   }
                               }
                               //sheet.getRange().get("B" + szamlalo2).setNumberValue(javitasokszama);
                               
                               if(javitasokszama >= 5)
                               {
                                   //Ablak.eredmeny_mezo.setText("Gazdasági Totálkár a sok javítás miatt!! \n Selejtezhető");                                  
                                   sheet.getRange().get("A" + cellaszam).setText(panelszam);
                                   sheet.getRange().get("B" + cellaszam).setText(rs.getString(3));
                                   sheet.getRange().get("C" + cellaszam).setText(rs.getString(6));
                                   sheet.getRange().get("D" + cellaszam).setText("Gazdasági Totálkár a sok javítás miatt!! \n Selejtezhető");
                                   cstmt2.execute(sql2); 
                                   result2 = cstmt2.getResultSet();                            
                                   DataTable datatable2 = new DataTable();
                                   jdbcAdapter2.fillDataTable(datatable2, result2);            //az sql lekÃ©rdezÃ©s tartalmÃ¡t odaadja egy result set vÃ¡ltozÃ³nak
                                   if(datatable.getRows().size() > 0)
                                   {
                                       sheet.getRange().get("E" + cellaszam).setText(datatable2.getRows().get(0).getString(0));
                                   }                               
                                   cellaszam++;
                                   System.out.println("Selejt találva "+ panelszam +" "+ datatable2.getRows().get(0).getString(0));
                               }
                               
                           }
                           else
                           {                           
                               //Ablak.eredmeny_mezo.setText("Nincs még a panelnak javítós bejegyzése!");
                           }
                       }
                   }                 
                   System.out.println("Fut a For "+ szamlalo3);
                   szamlalo3++;
               }
               
               sheet.getAutoFilters().setRange(sheet.getCellRange("A1:Z1"));
               sheet.getAllocatedRange().autoFitColumns();
               sheet.getAllocatedRange().autoFitRows();
               
               sheet.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
               
               UIManager.put("FileChooser.openButtonText","Mentés");
               JFileChooser mentes_helye = new JFileChooser();
               mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
               mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
               mentes_helye.showOpenDialog(mentes_helye);
               File fajl = mentes_helye.getSelectedFile();
               //System.out.println(fajl.getAbsolutePath());
               if(fajl != null)
               {
                   if(fajl.getName().contains(".xlsx"))
                   {
                       workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
                       FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                       try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                       {
                           for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                           {    
                               workbook2.removeSheetAt(i); 
                           }      
                           FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                           workbook2.write(output);
                           output.close();
                       }
                   }
                   else
                   {
                       workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                       FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                       try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                       {
                           for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                           {    
                               workbook2.removeSheetAt(i); 
                           }      
                           FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                           workbook2.write(output);
                           output.close();
                       }
                   }
                   JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
               }
               con.close();
               Foablak.frame.setCursor(null);                                                      
            }             
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                try
                {
                UIManager.put("FileChooser.openButtonText","Mentés");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "\\Desktop\\"));
                mentes_helye.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                //System.out.println(fajl.getAbsolutePath());
                if(fajl != null)
                {
                    if(fajl.getName().contains(".xlsx"))
                    {
                        workbook.saveToFile(fajl.getAbsolutePath(), ExcelVersion.Version2016);  
                        FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath());
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath());
                            workbook2.write(output);
                            output.close();
                        }
                    }
                    else
                    {
                        workbook.saveToFile(fajl.getAbsolutePath()+".xlsx", ExcelVersion.Version2016);  
                        FileInputStream fileStream = new FileInputStream(fajl.getAbsolutePath()+".xlsx");
                        try (XSSFWorkbook workbook2 = new XSSFWorkbook(fileStream)) 
                        {
                            for(int i = workbook2.getNumberOfSheets()-1; i>0 ;i--)
                            {    
                                workbook2.removeSheetAt(i); 
                            }      
                            FileOutputStream output = new FileOutputStream(fajl.getAbsolutePath()+".xlsx");
                            workbook2.write(output);
                            output.close();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Mentés sikeres", "Info", 1);
                }
                }             
                catch (Exception e2) 
                {
                    e2.printStackTrace();
                }
            }
         }
    }
	
	
}
