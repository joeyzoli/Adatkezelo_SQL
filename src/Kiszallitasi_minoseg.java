import javax.swing.JPanel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spire.xls.CellRange;
import com.spire.xls.Chart;
import com.spire.xls.ExcelChartType;
import com.spire.xls.ExcelVersion;
import com.spire.xls.LegendPositionType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import com.spire.xls.charts.ChartSerie;
import com.spire.xls.charts.ChartSeries;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Kiszallitasi_minoseg extends JPanel {
    
    private String[] honapok = {"Január","Február","Március","Április","Május","Június","Július","Augusztus","Szeptember","Október","November","December"};
    private JComboBox<String> honaptol_box;
    private JComboBox<String> honapig_box;
    private JComboBox<String> projekt_box ;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Kiszállítási minőség.xlsx";
    //private String hely2 = "\\\\\\10.1.0.11\\minosegbiztositas\\Reznyák_N\\Minőségi mutatószámok 2023.xlsx";
    private String hely = "";    //"\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\KPI_Quality\\2024\\Minőségi mutatószámok 2024_ szerkesztés alatt.xlsx";
    private ComboBox combobox_tomb = new ComboBox();
    private String fajlhelye = "\\\\\\10.1.0.11\\minosegbiztositas\\Fájlok\\kpifajlhelye.txt";

    /**
     * Create the panel.
     */
    public Kiszallitasi_minoseg() {
        setLayout(null);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Excelcsinal());
        start_gomb.setBounds(507, 143, 89, 23);
        add(start_gomb);
        
        JLabel lblNewLabel = new JLabel("Kiszállítási minőség lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(469, 37, 224, 14);
        add(lblNewLabel);
        
        honaptol_box = new JComboBox<String>(honapok);                         //honapok
        honaptol_box.setBounds(418, 214, 275, 22);
        add(honaptol_box);
        
        honapig_box = new JComboBox<String>(honapok);                          //honapok
        honapig_box.setBounds(418, 260, 275, 22);
        add(honapig_box);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));                          //combobox_tomb.getCombobox(ComboBox.vevoi_projekt)
        projekt_box.setBounds(418, 318, 275, 22);
        add(projekt_box);
        
        JButton start2_gomb = new JButton("Start");
        start2_gomb.addActionListener(new Projekt());
        start2_gomb.setBounds(507, 373, 89, 23);
        add(start2_gomb);
        
        JLabel lblNewLabel_1 = new JLabel("Éves összesítő készítése");
        lblNewLabel_1.setBounds(496, 118, 150, 14);
        add(lblNewLabel_1);
        
        setBackground(Foablak.hatter_szine);
        
        JButton csatol_gomb = new JButton("Csatol");
        csatol_gomb.addActionListener(new Fajl_csatol());
        csatol_gomb.setBounds(942, 71, 89, 23);
        add(csatol_gomb);
        
        JLabel lblNewLabel_2 = new JLabel("Fájl csatolása");
        lblNewLabel_2.setBounds(840, 75, 92, 14);
        add(lblNewLabel_2);
        try 
        {
            File letezik = new File(fajlhelye);
            if(letezik.exists())
            {
                //FileReader fr = new FileReader(System.getProperty("user.home") + "\\sqa_szures.txt");
                FileInputStream fis = new FileInputStream(fajlhelye);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                try (BufferedReader br = new BufferedReader(isr)) 
                {
                    String sor = br.readLine();
                    hely = sor;
                    Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
                }       
                catch (Exception e1) 
                {
                    String hibauzenet = e1.toString();
                    Email hibakuldes = new Email();
                    hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                    JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);
                    e1.printStackTrace();
                }
            }
        }       
        catch (Exception e1) 
        {
            String hibauzenet = e1.toString();
            Email hibakuldes = new Email();
            hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
            JOptionPane.showMessageDialog(null, getClass()+" "+ hibauzenet, "Hiba üzenet", 2);
            e1.printStackTrace();
        }

    }
    
    class Excelcsinal implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int honap = 17;                              
                File excel = new File(hely);
                Workbook workbook = new Workbook();             
                workbook.loadFromFile(excel.getAbsolutePath());
                Worksheet sheet = null;
                for (Object sheet2: workbook.getWorksheets()) {
                    String sheetName = ((Worksheet) sheet2).getName();                            
                    //System.out.println(sheetName);
                    if(sheetName.contains("External"))
                    {
                        sheet = workbook.getWorksheets().get(sheetName);
                    }
                }
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                //int sor = 2;
                int oszlop = 2;
                sheet2.getRange().get("A2").setText("Kiszállított db");
                sheet2.getRange().get("A3").setText("Kiesett db");
                sheet2.getRange().get("A4").setText("PPM");
                sheet2.getRange().get("A5").setText("Cél");
                for(int szamlalo = 6; szamlalo < 70;szamlalo+=3)
                {
                    CellRange cell = sheet.getCellRange(szamlalo, honap);        //honap                  
                    if(cell.hasFormula()) {
                        if(String.valueOf(cell.getFormulaValue()).equals("")) {
                            
                        }
                        else {
                            if(Float.parseFloat( String.valueOf(cell.getFormulaValue())) > 0) {
                                
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get("C"+(szamlalo-2)).getText().toString());
                                CellRange cell2 = sheet.getCellRange(szamlalo-2, honap);
                                cell2.convertToNumber();
                                sheet2.getCellRange(2, oszlop).setValue(cell2.getFormulaValue().toString());
                                cell2 = sheet.getCellRange(szamlalo-1, honap);
                                cell2.convertToNumber();
                                sheet2.getRange().get(3, oszlop).setValue(cell2.getFormulaValue().toString());
                                String[] ppm = cell.getFormulaValue().toString().split("\\.");
                                sheet2.getRange().get(4, oszlop).setValue(ppm[0]);                                
                                cell2 = sheet.getCellRange(szamlalo-2, 19);
                                System.out.println(cell2.getValue().toString());
                                sheet2.getRange().get(5, oszlop).setValue(cell2.getValue().toString()); 
                                oszlop++;
                            }
                        }                    
                    }                    
                }
                
              //Add a chart
                Chart chart = sheet2.getCharts().add();
                //Set region of chart data
                chart.setDataRange(sheet2.getCellRange(1,1,5,sheet2.getLastColumn()));
                chart.setSeriesDataFromRange(true);

                //Set position of chart
                chart.setLeftColumn(1);
                chart.setTopRow(6);
                chart.setRightColumn(11);
                chart.setBottomRow(29);       

                //Chart title
                chart.setChartTitle("Kiszállítási minőség");
                chart.getChartTitleArea().isBold(true);
                chart.getChartTitleArea().setSize(12);

                //Chart Axis
                chart.getPrimaryCategoryAxis().setTitle("Projekt");
                chart.getPrimaryCategoryAxis().getFont().isBold(true);
                chart.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart.getSecondaryValueAxis().setTitle("PPM");

                chart.getPrimaryValueAxis().setTitle("Db szám");
                chart.getPrimaryValueAxis().hasMajorGridLines(false);
                chart.getPrimaryValueAxis().setMinValue(0);
                chart.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);

                ChartSeries series = chart.getSeries();
                for (int i = 0;i < series.size();i++)
                {
                    ChartSerie cs = series.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);
                    //cs.setSerieType(ExcelChartType.ColumnStacked);
                }
                
                ChartSerie cs6 = (ChartSerie)chart.getSeries().get(2);
                cs6.setSerieType(ExcelChartType.LineMarkers);
                
                ChartSerie cs7 = (ChartSerie)chart.getSeries().get(3);
                cs7.setSerieType(ExcelChartType.LineMarkers);
         
                //Add a secondary Y axis to the chart
                cs6.setUsePrimaryAxis(false);
                cs7.setUsePrimaryAxis(false);

                //Chart legend
                chart.getLegend().setPosition(LegendPositionType.Top);

                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
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
                setCursor(null);
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Kiszállítási minőség.xlsx", "Info", 1);
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
    
    class Projekt implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int honaptol = 0;
                if(honaptol_box.getSelectedItem().equals("Január")){              
                    honaptol = 5;
                }
                else if(honaptol_box.getSelectedItem().equals("Február")){              
                    honaptol = 6;
                }
                else if(honaptol_box.getSelectedItem().equals("Március")){              
                    honaptol = 7;
                }
                else if(honaptol_box.getSelectedItem().equals("Április")){              
                    honaptol = 8;
                }
                else if(honaptol_box.getSelectedItem().equals("Május")){              
                    honaptol = 9;
                }
                else if(honaptol_box.getSelectedItem().equals("Június")){              
                    honaptol = 10;
                }
                else if(honaptol_box.getSelectedItem().equals("Július")){              
                    honaptol = 11;
                }
                else if(honaptol_box.getSelectedItem().equals("Augusztus")){              
                    honaptol = 12;
                }
                else if(honaptol_box.getSelectedItem().equals("Szeptember")){              
                    honaptol = 13;
                }
                else if(honaptol_box.getSelectedItem().equals("Október")){              
                    honaptol = 14;
                }
                else if(honaptol_box.getSelectedItem().equals("November")){              
                    honaptol = 15;
                }
                else if(honaptol_box.getSelectedItem().equals("December")){              
                    honaptol = 16;
                }
                
                int honapig = 0;
                if(honapig_box.getSelectedItem().equals("Január")){              
                    honapig = 5;
                }
                else if(honapig_box.getSelectedItem().equals("Február")){              
                    honapig = 6;
                }
                else if(honapig_box.getSelectedItem().equals("Március")){              
                    honapig = 7;
                }
                else if(honapig_box.getSelectedItem().equals("Április")){              
                    honapig = 8;
                }
                else if(honapig_box.getSelectedItem().equals("Május")){              
                    honapig = 9;
                }
                else if(honapig_box.getSelectedItem().equals("Június")){              
                    honapig = 10;
                }
                else if(honapig_box.getSelectedItem().equals("Július")){              
                    honapig = 11;
                }
                else if(honapig_box.getSelectedItem().equals("Augusztus")){              
                    honapig = 12;
                }
                else if(honapig_box.getSelectedItem().equals("Szeptember")){              
                    honapig = 13;
                }
                else if(honapig_box.getSelectedItem().equals("Október")){              
                    honapig = 14;
                }
                else if(honapig_box.getSelectedItem().equals("November")){              
                    honapig = 15;
                }
                else if(honapig_box.getSelectedItem().equals("December")){              
                    honapig = 16;
                }
                
                File excel = new File(hely);
                Workbook workbook = new Workbook();             
                workbook.loadFromFile(excel.getAbsolutePath());
                Worksheet sheet = null;
                for (Object sheet2: workbook.getWorksheets()) {
                    String sheetName = ((Worksheet) sheet2).getName();                            
                    //System.out.println(sheetName);
                    if(sheetName.contains("External"))
                    {
                        sheet = workbook.getWorksheets().get(sheetName);
                    }
                }
                Workbook workbook2 = new Workbook();
                Worksheet sheet2 = workbook2.getWorksheets().get(0);
                //int sor = 2;
                int oszlop = 2;
                sheet2.getRange().get("A2").setText("Kiszállított db");
                sheet2.getRange().get("A3").setText("Kiesett db");
                sheet2.getRange().get("A4").setText("PPM");
                //System.out.println(sheet.getRange().get("B4").getText().toString());
                
                for(int szamlalo2 = 4; szamlalo2 < 70;szamlalo2 +=3)
                {
                    if(String.valueOf(projekt_box.getSelectedItem()).toLowerCase().contains(sheet.getRange().get("C"+szamlalo2).getText().toString().toLowerCase())) 
                    {
                        for(int szamlalo = honaptol; szamlalo < (honapig+1);szamlalo++)        //(honapig+1)
                        {
                            CellRange cell = sheet.getCellRange(szamlalo2+2, honaptol);                            
                            if(String.valueOf(cell.getFormulaValue()).equals("")) {
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get(3,honaptol).getText().toString());
                                sheet2.getCellRange(2, oszlop).setValue("0");
                                sheet2.getRange().get(3, oszlop).setValue("0");
                                sheet2.getRange().get(4, oszlop).setValue("0");
                            }
                            else {                                
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get(3,honaptol).getText().toString());
                                CellRange cell2 = sheet.getCellRange(szamlalo2, honaptol);
                                cell2.convertToNumber();
                                sheet2.getCellRange(2, oszlop).setNumberValue(sheet.getCellRange(szamlalo2, honaptol).getNumberValue());
                                cell2 = sheet.getCellRange(szamlalo2+1, honaptol);
                                cell2.convertToNumber();
                                sheet2.getRange().get(3, oszlop).setNumberValue(sheet.getCellRange(szamlalo2+1, honaptol).getNumberValue());
                                if(cell.getFormulaValue() != null)
                                {
                                    String[] ppm = cell.getFormulaValue().toString().split("\\.");
                                    sheet2.getRange().get(4, oszlop).setValue(ppm[0]);
                                }
                                else {
                                    sheet2.getRange().get(4, oszlop).setValue("0");
                                }
                                oszlop++;
                                honaptol++;   
                            }                                      
                        }
                        break;
                    }
                }
                
              //Add a chart
                Chart chart = sheet2.getCharts().add();

                //Set region of chart data
                chart.setDataRange(sheet2.getCellRange(1,1,4,sheet2.getLastColumn()));
                chart.setSeriesDataFromRange(true);

                //Set position of chart
                chart.setLeftColumn(1);
                chart.setTopRow(6);
                chart.setRightColumn(11);
                chart.setBottomRow(29);       

                //Chart title
                chart.setChartTitle("Kiszállítási minőség");
                chart.getChartTitleArea().isBold(true);
                chart.getChartTitleArea().setSize(12);

                //Chart Axis
                chart.getPrimaryCategoryAxis().setTitle("Hónap");
                chart.getPrimaryCategoryAxis().getFont().isBold(true);
                chart.getPrimaryCategoryAxis().getTitleArea().isBold(true);
                chart.getSecondaryValueAxis().setTitle("PPM");

                chart.getPrimaryValueAxis().setTitle("Db szám");
                chart.getPrimaryValueAxis().hasMajorGridLines(false);
                chart.getPrimaryValueAxis().setMinValue(0);
                chart.getPrimaryValueAxis().getTitleArea().isBold(true);
                chart.getPrimaryValueAxis().getTitleArea().setTextRotationAngle(90);

                ChartSeries series = chart.getSeries();
                for (int i = 0;i < series.size();i++)
                {
                    ChartSerie cs = series.get(i);
                    cs.getFormat().getOptions().isVaryColor(true);
                    cs.getDataPoints().getDefaultDataPoint().getDataLabels().hasValue(true);
                    //cs.setSerieType(ExcelChartType.ColumnStacked);
                }
                
                ChartSerie cs6 = (ChartSerie)chart.getSeries().get(2);
                cs6.setSerieType(ExcelChartType.LineMarkers);
         
                //Add a secondary Y axis to the chart
                cs6.setUsePrimaryAxis(false);

                //Chart legend
                chart.getLegend().setPosition(LegendPositionType.Top);

                sheet2.getAutoFilters().setRange(sheet2.getCellRange("A1:Z1"));
                sheet2.getAllocatedRange().autoFitColumns();
                sheet2.getAllocatedRange().autoFitRows();                
                sheet2.getCellRange("A1:Z1").getCellStyle().getExcelFont().isBold(true);                          // félkövér beállítás
                
                menteshelye = System.getProperty("user.home") + "\\Desktop\\Kiszállítási minőség "+ String.valueOf(projekt_box.getSelectedItem()) +".xlsx";
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
                setCursor(null);
                JOptionPane.showMessageDialog(null, "Mentve az asztalra Kiszállítási minőség "+ String.valueOf(projekt_box.getSelectedItem()) +".xlsx", "Info", 1);
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
    
    class Fajl_csatol implements ActionListener                                                                                        //termék gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                PrintWriter writer = new PrintWriter(fajlhelye, "UTF-8");
                JFileChooser mentes_helye = new JFileChooser();
                mentes_helye.setCurrentDirectory(new java.io.File("\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\KPI_Quality\\"));
                mentes_helye.showOpenDialog(mentes_helye);
                File fajl = mentes_helye.getSelectedFile();
                if(fajl != null)
                {
                    writer.print(fajl.getAbsolutePath());
                    writer.close();
                }
                Foablak.frame.setCursor(null);                                                                                          //egér mutató alaphelyzetbe állítása
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
                String hibauzenet = e1.toString();
                Email hibakuldes = new Email();
                hibakuldes.hibauzenet(System.getProperty("user.name")+"@veas.videoton.hu", hibauzenet);
                JOptionPane.showMessageDialog(null, hibauzenet, "Hiba üzenet", 2);                                                   //kivétel esetén kiírja a hibaüzenetet
            }
         }
    }
}
