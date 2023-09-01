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
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Kiszallitasi_minoseg extends JPanel {
    
    private String[] honapok = {"Január","Február","Március","Április","Május","Június","Július","Augusztus","Szeptember","Október","November","December"};
    private JComboBox<String> honap_box;
    private JComboBox<String> honaptol_box;
    private JComboBox<String> honapig_box;
    private JComboBox<String> projekt_box ;
    private String menteshelye = System.getProperty("user.home") + "\\Desktop\\Kiszállítási minőség.xlsx";
    //private String hely2 = "\\\\\\10.1.0.11\\minosegbiztositas\\Reznyák_N\\Minőségi mutatószámok 2023.xlsx";
    private String hely = "\\\\\\172.20.22.7\\kozos\\Gyártási_minőség_követése\\KPI_Quality\\2023\\Minőségi mutatószámok 2023.xlsx";
    private ComboBox combobox_tomb = new ComboBox();

    /**
     * Create the panel.
     */
    public Kiszallitasi_minoseg() {
        setLayout(null);
        
        honap_box = new JComboBox<String>(honapok);                            //honapok
        honap_box.setBounds(418, 91, 275, 22);
        add(honap_box);
        
        JButton start_gomb = new JButton("Start");
        start_gomb.addActionListener(new Excelcsinal());
        start_gomb.setBounds(507, 143, 89, 23);
        add(start_gomb);
        
        JLabel lblNewLabel = new JLabel("Kiszállítási minőség lekérdezése");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(469, 37, 224, 14);
        add(lblNewLabel);
        
        honaptol_box = new JComboBox<String>(honapok);
        honaptol_box.setBounds(418, 214, 275, 22);
        add(honaptol_box);
        
        honapig_box = new JComboBox<String>(honapok);
        honapig_box.setBounds(418, 260, 275, 22);
        add(honapig_box);
        
        projekt_box = new JComboBox<String>(combobox_tomb.getCombobox(ComboBox.vevoi_projekt));
        projekt_box.setBounds(418, 318, 275, 22);
        add(projekt_box);
        
        JButton start2_gomb = new JButton("Start");
        start2_gomb.addActionListener(new Projekt());
        start2_gomb.setBounds(507, 373, 89, 23);
        add(start2_gomb);

    }
    
    class Excelcsinal implements ActionListener                                                                                      //mentés gomb megnyomáskor hívodik meg
    {
        public void actionPerformed(ActionEvent e)
         {
            try
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                int honap = 0;
                if(honap_box.getSelectedItem().equals("Január")){              
                    honap = 4;
                }
                else if(honap_box.getSelectedItem().equals("Február")){              
                    honap = 5;
                }
                else if(honap_box.getSelectedItem().equals("Március")){              
                    honap = 6;
                }
                else if(honap_box.getSelectedItem().equals("Április")){              
                    honap = 7;
                }
                else if(honap_box.getSelectedItem().equals("Május")){              
                    honap = 8;
                }
                else if(honap_box.getSelectedItem().equals("Június")){              
                    honap = 9;
                }
                else if(honap_box.getSelectedItem().equals("Július")){              
                    honap = 10;
                }
                else if(honap_box.getSelectedItem().equals("Augusztus")){              
                    honap = 11;
                }
                else if(honap_box.getSelectedItem().equals("Szeptember")){              
                    honap = 12;
                }
                else if(honap_box.getSelectedItem().equals("Október")){              
                    honap = 13;
                }
                else if(honap_box.getSelectedItem().equals("November")){              
                    honap = 14;
                }
                else if(honap_box.getSelectedItem().equals("December")){              
                    honap = 15;
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
                for(int szamlalo = 4; szamlalo < 102;szamlalo++)
                {
                    CellRange cell = sheet.getCellRange(szamlalo, honap);
                    //cell.convertToNumber();
                    //System.out.println(cell.getValue());
                    //System.out.println(cell.getCount());
                    if(cell.hasFormula()) {
                        if(String.valueOf(cell.getFormulaValue()).equals("")) {
                            
                        }
                        else {
                            if(Float.parseFloat( String.valueOf(cell.getFormulaValue())) > 0) {
                                //sheet.getRange().get("B"+szamlalo +":B"+(szamlalo+3)).unMerge();
                                //System.out.println(sheet.getRange().get("B"+szamlalo).getText().toString());
                                //System.out.println(sheet.getRange().get("B"+(szamlalo-2)).getText().toString());
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get("B"+(szamlalo-2)).getText().toString());
                                //System.out.println(sheet.getRange().get("B"+(szamlalo+2)).getText().toString());
                                CellRange cell2 = sheet.getCellRange(szamlalo-2, honap);
                                cell2.convertToNumber();
                                //System.out.println(cell2.getValue());
                                sheet2.getCellRange(2, oszlop).setValue(cell2.getValue());
                                cell2 = sheet.getCellRange(szamlalo-1, honap);
                                cell2.convertToNumber();
                                //System.out.println(cell2.getValue());
                                sheet2.getRange().get(3, oszlop).setValue(cell2.getValue());
                                //System.out.println(cell.getFormulaValue().toString());
                                String[] ppm = cell.getFormulaValue().toString().split("\\.");
                                sheet2.getRange().get(4, oszlop).setValue(ppm[0]);
                                oszlop++;
                            }
                        }                    
                    }                    
                }
                
              //Add a chart
                Chart chart = sheet2.getCharts().add();

                //Set region of chart data
                chart.setDataRange(sheet2.getCellRange("A1:G4"));
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
         
                //Add a secondary Y axis to the chart
                cs6.setUsePrimaryAxis(false);

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
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
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
                    honaptol = 4;
                }
                else if(honaptol_box.getSelectedItem().equals("Február")){              
                    honaptol = 5;
                }
                else if(honaptol_box.getSelectedItem().equals("Március")){              
                    honaptol = 6;
                }
                else if(honaptol_box.getSelectedItem().equals("Április")){              
                    honaptol = 7;
                }
                else if(honaptol_box.getSelectedItem().equals("Május")){              
                    honaptol = 8;
                }
                else if(honaptol_box.getSelectedItem().equals("Június")){              
                    honaptol = 9;
                }
                else if(honaptol_box.getSelectedItem().equals("Július")){              
                    honaptol = 10;
                }
                else if(honaptol_box.getSelectedItem().equals("Augusztus")){              
                    honaptol = 11;
                }
                else if(honaptol_box.getSelectedItem().equals("Szeptember")){              
                    honaptol = 12;
                }
                else if(honaptol_box.getSelectedItem().equals("Október")){              
                    honaptol = 13;
                }
                else if(honaptol_box.getSelectedItem().equals("November")){              
                    honaptol = 14;
                }
                else if(honaptol_box.getSelectedItem().equals("December")){              
                    honaptol = 15;
                }
                
                int honapig = 0;
                if(honapig_box.getSelectedItem().equals("Január")){              
                    honapig = 4;
                }
                else if(honapig_box.getSelectedItem().equals("Február")){              
                    honapig = 5;
                }
                else if(honapig_box.getSelectedItem().equals("Március")){              
                    honapig = 6;
                }
                else if(honapig_box.getSelectedItem().equals("Április")){              
                    honapig = 7;
                }
                else if(honapig_box.getSelectedItem().equals("Május")){              
                    honapig = 8;
                }
                else if(honapig_box.getSelectedItem().equals("Június")){              
                    honapig = 9;
                }
                else if(honapig_box.getSelectedItem().equals("Július")){              
                    honapig = 10;
                }
                else if(honapig_box.getSelectedItem().equals("Augusztus")){              
                    honapig = 11;
                }
                else if(honapig_box.getSelectedItem().equals("Szeptember")){              
                    honapig = 12;
                }
                else if(honapig_box.getSelectedItem().equals("Október")){              
                    honapig = 13;
                }
                else if(honapig_box.getSelectedItem().equals("November")){              
                    honapig = 14;
                }
                else if(honapig_box.getSelectedItem().equals("December")){              
                    honapig = 15;
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
                
                for(int szamlalo2 = 4; szamlalo2 < 102;szamlalo2 +=3)
                {
                    if(String.valueOf(projekt_box.getSelectedItem()).toLowerCase().contains(sheet.getRange().get("B"+szamlalo2).getText().toString().toLowerCase())) 
                    {
                        for(int szamlalo = honaptol; szamlalo < (honapig+1);szamlalo++)        //(honapig+1)
                        {
                            CellRange cell = sheet.getCellRange(szamlalo2+2, honaptol);
                            //cell.convertToNumber();
                            //System.out.println(cell.getValue());
                            //System.out.println(cell.getCount());    datatable.getRows().get(szamlalo2).getString(1)
                            if(String.valueOf(cell.getFormulaValue()).equals("")) {
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get(3,honaptol).getText().toString());
                                sheet2.getCellRange(2, oszlop).setValue("0");
                                sheet2.getRange().get(3, oszlop).setValue("0");
                                sheet2.getRange().get(4, oszlop).setValue("0");
                            }
                            else {
                                //System.out.println("Lefutott");
                                //sheet.getRange().get("B"+szamlalo +":B"+(szamlalo+3)).unMerge();
                                //System.out.println(sheet.getRange().get("B"+szamlalo).getText().toString());
                                //System.out.println(sheet.getRange().get("B"+(szamlalo-2)).getText().toString());
                                sheet2.getRange().get(1, oszlop).setText(sheet.getRange().get(3,honaptol).getText().toString());
                                //System.out.println(sheet.getRange().get("B"+(szamlalo+2)).getText().toString());
                                CellRange cell2 = sheet.getCellRange(szamlalo2, honaptol);
                                cell2.convertToNumber();
                                System.out.println(cell2.getValue());
                                //sheet2.getCellRange(2, oszlop).setValue(cell2.getValue());
                                sheet2.getCellRange(2, oszlop).setNumberValue(sheet.getCellRange(szamlalo2, honaptol).getNumberValue());
                                //sheet2.getCellRange(2, oszlop).setValue(datatable.getRows().get(szamlalo2).getString(honaptol));
                                cell2 = sheet.getCellRange(szamlalo2+1, honaptol);
                                cell2.convertToNumber();
                                System.out.println(cell2.getValue());
                                //sheet2.getRange().get(3, oszlop).setValue(cell2.getValue());
                                sheet2.getRange().get(3, oszlop).setNumberValue(sheet.getCellRange(szamlalo2+1, honaptol).getNumberValue());
                                //sheet2.getRange().get(3, oszlop).setValue(datatable.getRows().get(szamlalo2+1).getString(honaptol));
                                //System.out.println(cell.getFormulaValue().toString());
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
                        System.out.println("Kilépett");
                        break;
                    }
                }
                
              //Add a chart
                Chart chart = sheet2.getCharts().add();

                //Set region of chart data
                chart.setDataRange(sheet2.getCellRange("A1:M4"));
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
                String hibauzenet2 = e1.toString();
                JOptionPane.showMessageDialog(null, hibauzenet2, "Hiba üzenet", 2);
            }
         }
    }
}
