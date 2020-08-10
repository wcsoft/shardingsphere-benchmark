package org.apache.shardingsphere.benchmark.common.excel;

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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.shardingsphere.benchmark.bean.BenchmarkResultBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BenchmarkExcelWriter {
    
    private static List<String> CELL_HEADS; //列头
    
    static{
        CELL_HEADS = new ArrayList<>();
        CELL_HEADS.add("产品");
        CELL_HEADS.add("版本");
        CELL_HEADS.add("场景");
        CELL_HEADS.add("TPS");
        CELL_HEADS.add("并发量");
        CELL_HEADS.add("TP50th");
        CELL_HEADS.add("TP90th");
        CELL_HEADS.add("TP95th");
        CELL_HEADS.add("最大耗时");
        CELL_HEADS.add("最小耗时");
        CELL_HEADS.add("SQL");
    }
    
    public static void writeExcel(String excelPath, String sheetName, boolean isHeader, int rowNum, List<BenchmarkResultBean> dataList){
        FileOutputStream fileOut = null;
    
        Workbook workbook = exportData(sheetName, isHeader, rowNum, dataList);
        
        File exportFile = new File(excelPath);
        if (!exportFile.exists()) {
            try {
                exportFile.createNewFile();
                fileOut = new FileOutputStream(excelPath);
                workbook.write(fileOut);
                fileOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(null != fileOut){
                    try {
                        fileOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    
    public static Workbook exportData(String sheetName, boolean isHeader, int rowNum, List<BenchmarkResultBean> dataList){
    
        Workbook workbook = new SXSSFWorkbook();
        buildDataSheet(workbook, sheetName, isHeader, rowNum, dataList);
        return workbook;
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
    
    /**
     * 设置第一行列头的样式
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
    
    /**
     * 将数据转换成行
     * @param data 源数据
     * @param row 行对象
     * @return
     */
    private static void convertDataToRow(BenchmarkResultBean data, Row row){
        int cellNum = 0;
        Cell cell;
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getProduct());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getVersion());
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getScenario());
        Map benchmarkResult = data.getBenchmarkResult();
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("tps"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((int)benchmarkResult.get("total"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("tp50th"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("tp90th"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("tp95th"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("maxCost"));
        cell = row.createCell(cellNum++);
        cell.setCellValue((double)benchmarkResult.get("minCost"));
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSql());
    }
}
