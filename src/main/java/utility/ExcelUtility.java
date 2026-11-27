
package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtility() {}

    public void loadExceFileAndSheet(String filePathName, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePathName);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();
            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet not found: " + sheetName
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Excel file", e);
        }
    }

    public int getTotalNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getTotalNumberOfColumns() {
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(
                sheet.getRow(rowNum).getCell(colNum)
        );
    }

    public void readAllData() {
        int totalRows = getTotalNumberOfRows();
        int totalColumns = getTotalNumberOfColumns();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                String data = getCellData(i, j);
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }

    public List<String> readColumnWiseData()
    {
        List<String> list = new ArrayList<>();
        int totalRows = getTotalNumberOfRows();
        int rowColumns = 0;

        for (int i = 1 ; i < totalRows ; i++)
        {
            list.add(getCellData(i, rowColumns));
        }
        return list;
    }

    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    public static void main(String[] args) {

        ExcelUtility excelUtility = new ExcelUtility();
        String filePath = System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";
        excelUtility.loadExceFileAndSheet(filePath, "Sheet3");
        System.out.println(
                "Total Rows: " + excelUtility.getTotalNumberOfRows()
        );
        System.out.println(
                "Total Columns: " + excelUtility.getTotalNumberOfColumns()
        );
        String username = excelUtility.getCellData(1, 0);
        String password = excelUtility.getCellData(1, 1);
        System.out.println("=="+username);
        System.out.println("=="+password);
        List<String> list =  excelUtility.readColumnWiseData();
        for (String str : list)
        {
            System.out.println(str);
        }
        try {
            excelUtility.closeWorkbook();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
