import javax.swing.JPanel;

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
import java.nio.charset.StandardCharsets;
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
		feltolt.addActionListener(new Szeriaszam_gyarto());
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
			    Db_iro atiras = new Db_iro();
			    Workbook workbook = new Workbook();
			    workbook.loadFromFile("c:\\Users\\kovacs.zoltan\\Desktop\\Mappák\\Java projekt\\emerson felosztás.xlsx");
	            Worksheet sheet = workbook.getWorksheets().get(0);
	            DataTable dataTable = sheet.exportDataTable();
	            for (int i = 0; i < dataTable.getRows().size(); i++) 
	            {
	                atiras.atir("Leroy Somer", "Leroy-Somer");
	            }
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
            catch (SQLException e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
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
	
	class Lekerdezes implements ActionListener                                                                                      //csv-t gyárt a gomb
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {              
                DataTable datatable = new DataTable();
                String menteshelye = System.getProperty("user.home") + "\\Desktop\\Rendelések.xlsx";

                  DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                  Class.forName("oracle.jdbc.OracleDriver");  //.driver
                                      
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@IFSORA.IFS.videoton.hu:1521/IFSPROD","ZKOVACS","ZKOVACS");                                      
                  Statement stmt = con.createStatement();                      
                  
                  
                  ResultSet rs = stmt.executeQuery("select majdnem.*,\n"
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
                          + "and majdnem.Gyartoi_cikkszam = nyilatkozatok.manu_part_no");
                  
                  Workbook workbook = new Workbook();
                  JdbcAdapter jdbcAdapter = new JdbcAdapter();
                  jdbcAdapter.fillDataTable(datatable, rs);
       
                  //Get the first worksheet
                  Worksheet sheet = workbook.getWorksheets().get(0);
                  sheet.insertDataTable(datatable, true, 1, 1);
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
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0) +"0"+ szamlalo2);
                        }
                        else
                        {
                            sheet2.getRange().get("A" + cellaszam).setText(datatable.getRows().get(szamlalo).getString(0) +"10");
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
}
