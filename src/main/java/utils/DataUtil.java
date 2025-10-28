package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.*;

public class DataUtil {

    /** ---------------- CSV ---------------- **/

    public static List<String[]> readCSV(String path) {
        List<String[]> rows = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                rows.add(nextLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static void writeCSV(String path, List<String[]> data) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ---------------- JSON ---------------- **/

    public static <T> T readJSON(String path, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(path), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeJSON(String path, Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ---------------- Excel ---------------- **/

    public static List<Map<String, String>> readExcel(String path, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return data;

            Iterator<Row> rowIt = sheet.iterator();
            Row header = rowIt.next();
            List<String> headers = new ArrayList<>();
            header.forEach(cell -> headers.add(cell.getStringCellValue()));

            while (rowIt.hasNext()) {
                Row row = rowIt.next();
                Map<String, String> rowMap = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    rowMap.put(headers.get(i), cell.getStringCellValue());
                }
                data.add(rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeExcel(String path, String sheetName, List<Map<String, String>> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);

            if (data.isEmpty()) return;

            Row headerRow = sheet.createRow(0);
            List<String> headers = new ArrayList<>(data.get(0).keySet());
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i));
            }

            for (int r = 0; r < data.size(); r++) {
                Row row = sheet.createRow(r + 1);
                Map<String, String> rowData = data.get(r);
                for (int c = 0; c < headers.size(); c++) {
                    row.createCell(c).setCellValue(rowData.get(headers.get(c)));
                }
            }

            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ---------------- TestNG DataProviders ---------------- **/

    @DataProvider(name = "csvData", parallel = true)
    public static Iterator<Object[]> csvDataProvider() {
        List<String[]> rows = readCSV("src/main/java/resources/testdata/login.csv");
        List<Object[]> out = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) { // skip header
            out.add(new Object[]{ rows.get(i) });
        }
        return out.iterator();
    }

    @DataProvider(name = "excelData", parallel = true)
    public static Iterator<Object[]> excelDataProvider() {
        List<Map<String, String>> data = readExcel("src/main/java/resources/testdata/login.xlsx", "Sheet1");
        List<Object[]> out = new ArrayList<>();
        for (Map<String, String> row : data) {
            out.add(new Object[]{ row });
        }
        return out.iterator();
    }

    @DataProvider(name = "jsonData", parallel = true)
    public static Iterator<Object[]> jsonDataProvider() {
        // Assuming JSON is an array of objects -> [{ "username": "...", "password": "..." }, ...]
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(new File("src/main/java/resources/testdata/login.json"), List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object[]> out = new ArrayList<>();
        for (Map<String, Object> row : data) {
            out.add(new Object[]{ row });
        }
        return out.iterator();
    }
    
    
    @DataProvider(name = "excelDataQAExcercise", parallel = true)
    public static Iterator<Object[]> excelDataProviderQA() {
        List<Map<String, String>> data = readExcel("src/main/java/resources/testdata/loginQAExcercise.xlsx", "Sheet1");
        List<Object[]> out = new ArrayList<>();
        for (Map<String, String> row : data) {
            out.add(new Object[]{ row });
        }
        return out.iterator();
    }
}