package org.epam.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.epam.data.test_data.TestReportData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelUtil {
    private static final String TEST_DATA_RESOURCES_PATH = "src/test/resources/test_data/";

    public static Map<String, List<TestReportData>> getReportExcelData(String fileName) {
        String inputFilePath = TEST_DATA_RESOURCES_PATH + fileName;
        Map<String, List<TestReportData>> testReportDataList = new HashMap<>();
        try (FileInputStream file = new FileInputStream(inputFilePath)) {
            Workbook workbook = new XSSFWorkbook(file);
            for (Sheet sheet : workbook) {
                List<TestReportData> sheetReportData = parseSheetAsReportData(sheet);
                testReportDataList.put(sheet.getSheetName(), sheetReportData);
            }
            workbook.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return testReportDataList;
    }

    public static List<TestReportData> getSheetDataFromReportExcelData(String fileName, String sheetName) {
        String inputFilePath = TEST_DATA_RESOURCES_PATH + fileName;
        List<TestReportData> sheetReportData = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(inputFilePath)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);
            sheetReportData = parseSheetAsReportData(sheet);
            workbook.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sheetReportData;
    }

    private static String getCellValueAsString(Cell cell) {
        CellType cellType = cell.getCellType();
        if (cellType.equals(STRING)) {
            return cell.getStringCellValue();
        } else {
            return String.valueOf((int) cell.getNumericCellValue());
        }
    }

    private static List<TestReportData> parseSheetAsReportData(Sheet reportDataSheet) {
        List<TestReportData> sheetReportData = new ArrayList<>();
        Map<String, String> testSuiteData = new HashMap<>();
        int rowQty = reportDataSheet.getLastRowNum();
        int columnQty = reportDataSheet.getRow(0).getLastCellNum();
        for (int rowIndex = 1; rowIndex <= rowQty; rowIndex++) {
            Row headerRow = reportDataSheet.getRow(0);
            Row dataRow = reportDataSheet.getRow(rowIndex);
            for (int columnCount = 0; columnCount < columnQty; columnCount++) {
                String cellHeaderValue = headerRow.getCell(columnCount).getStringCellValue();
                Cell dataRowCell = dataRow.getCell(columnCount);
                if (dataRowCell != null) {
                    testSuiteData.put(cellHeaderValue, getCellValueAsString(dataRowCell));
                }
            }
            TestReportData testReportData = createTestReportDataFromMap(testSuiteData);
            sheetReportData.add(testReportData);
        }
        return sheetReportData;
    }

    private static TestReportData createTestReportDataFromMap(Map<String, String> testSuiteData) {
        return TestReportData.builder()
                .testSuiteName(testSuiteData.get("NAME"))
                .total(testSuiteData.get("TOTAL"))
                .passed(testSuiteData.get("PASSED"))
                .failed(testSuiteData.get("FAILED"))
                .skipped(testSuiteData.get("SKIPPED"))
                .productBug(testSuiteData.get("PRODUCT BUG"))
                .autoBug(testSuiteData.get("AUTO BUG"))
                .systemIssue(testSuiteData.get("SYSTEM ISSUE"))
                .toInvestigate(testSuiteData.get("TO INVESTIGATE")).build();
    }
}
