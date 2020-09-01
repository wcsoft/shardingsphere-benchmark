package org.apache.shardingsphere.benchmark.common.file.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkExcelWriter {
    
    private static List<String> CELL_HEADS;
    
    static{
        CELL_HEADS = new ArrayList<String>();
        CELL_HEADS.add("版本");
        CELL_HEADS.add("场景");
        CELL_HEADS.add("Rules");
        CELL_HEADS.add("数据库操作");
        CELL_HEADS.add("产品");
        CELL_HEADS.add("TPS");
        CELL_HEADS.add("并发数");
        CELL_HEADS.add("采样总量");
        CELL_HEADS.add("最大耗时");
        CELL_HEADS.add("最小耗时");
        CELL_HEADS.add("SQL");
        CELL_HEADS.add("分库数量");
        CELL_HEADS.add("分表数量");
    }
    
    public static void writeExcel(String excelPath, String sheetName, boolean isHeader, int rowNum, List<BenchmarkResultBean> dataList){
        Workbook workbook = null;
        File exportFile = null;
        FileOutputStream fileOut = null;
        
        try {
            exportFile = new File(excelPath);
            if(exportFile.exists()){
                workbook = new HSSFWorkbook(new FileInputStream(excelPath));
            } else {
                workbook = new HSSFWorkbook();
                exportFile.createNewFile();
            }
            workbook = buildDataSheet(workbook, sheetName, isHeader, rowNum, dataList);
    
            fileOut = new FileOutputStream(excelPath);
            workbook.write(fileOut);
            fileOut.flush();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    
        }
    }
    
    private static Workbook buildDataSheet(Workbook workbook, String sheetName, boolean isHeader, int rowNum, List<BenchmarkResultBean> dataList) {
        
        Sheet sheet = null;
        if(null == workbook.getSheet(sheetName)){
            sheet = workbook.createSheet(sheetName);
        } else {
            sheet = workbook.getSheet(sheetName);
        }
        
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        if(isHeader){
            for (int i=0; i < CELL_HEADS.size(); i++) {
                sheet.setColumnWidth(i, 4000);
            }
            sheet.setDefaultRowHeight((short) 400);
            Row head = sheet.createRow(0);
            for (int i = 0; i < CELL_HEADS.size(); i++) {
                Cell cell = head.createCell(i);
                cell.setCellValue(CELL_HEADS.get(i));
                cell.setCellStyle(cellStyle);
            }
        }
        
        for (int i = 0; i < dataList.size(); i++) {
            BenchmarkResultBean benchmarkResultBean = dataList.get(i);
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(benchmarkResultBean, row);
        }
        return workbook;
    }
    

    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);
        
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
    

    private static void convertDataToRow(BenchmarkResultBean data, Row row){
        int cellNum = 0;
        Cell cell;
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getVersion());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getScenario());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getRules());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getDbAction());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getProduct());
        Map benchmarkResult = data.getBenchmarkResult();
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("tps"));
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getConcurrency());
        cell = row.createCell(cellNum++);
        cell.setCellValue((int)benchmarkResult.get("total"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("maxCost"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("minCost"));
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSql());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getDbShardingCount());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getTableShardingCount());
    }
}
