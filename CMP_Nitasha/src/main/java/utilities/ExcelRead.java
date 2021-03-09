package utilities;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Cell;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import Base.TestBase;

public class ExcelRead extends TestBase
{
    static Logger log;
    static ArrayList<ArrayList<String>> excelData;
    
    static {
        ExcelRead.log = Logger.getLogger(Screenshot.class.getName());
    }
    
    public static ArrayList<ArrayList<String>> excelRead(final String filePath, final String sheetName) {
        ExcelRead.excelData = new ArrayList<ArrayList<String>>();
        final DataFormatter formatter = new DataFormatter();
        try {
            final FileInputStream file = new FileInputStream(filePath);
            final XSSFWorkbook wb = new XSSFWorkbook((InputStream)file);
            wb.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            final XSSFSheet sheet = wb.getSheet(sheetName);
            final Iterator<Row> rowIterator = (Iterator<Row>)sheet.iterator();
            while (rowIterator.hasNext()) {
                final ArrayList<String> columnValues = new ArrayList<String>();
                final Row row = rowIterator.next();
                for (int i = 0; i < row.getLastCellNum(); ++i) {
                    final Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()) {
                        case NUMERIC: {
                            columnValues.add(String.valueOf(formatter.formatCellValue(cell)));
                            break;
                        }
                        case STRING: {
                            columnValues.add(cell.getStringCellValue());
                            break;
                        }
                        case BLANK: {
                            columnValues.add("");
                            break;
                        }
                        default: {
                            columnValues.add("");
                            break;
                        }
                    }
                }
                ExcelRead.excelData.add(columnValues);
            }
            wb.close();
            file.close();
            ExcelRead.log.info((Object)"Successfully read the excel file");
        }
        catch (Exception e) {
            ExcelRead.log.info((Object)"Had issue while reading excel file");
            e.printStackTrace();
        }
        return ExcelRead.excelData;
    }
}
