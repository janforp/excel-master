package com.janita.poi.util;

import com.janita.excel.common.entity.BaseDto;
import com.janita.excel.common.entity.Book;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.janita.poi.util.StyleUtils.cellStyleOne;

/**
 * Created by Janita on 2017/6/12 0012- 上午 10:52
 * 该类是：poi excel工具类
 */
public class ExcelUtils {

    /**
     * 有基本书就生成几个 sheet
     * @param bookList  书
     * @return  有多个sheet 的 excel
     */
    public static HSSFWorkbook createExcel(List<Book> bookList, BaseDto baseDto) throws UnsupportedEncodingException {
        //1.实例化一个 excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (Book book : bookList) {
            //2.每本书生成一个 sheet
            HSSFSheet sheet = workbook.createSheet(book.getBookName());
            //设置默认的行高
            sheet.setDefaultRowHeight((short) (14.25 * 20));
            sheet.setColumnWidth(0, (int) (27.63 * 256));
            sheet.setColumnWidth(1, (int) (27.63 * 256));
            //sheet 前面几行
            frontRows(workbook,sheet, baseDto);

        }
        return workbook;
    }

    private static void frontRows(HSSFWorkbook workbook, HSSFSheet sheet, BaseDto baseDto) throws UnsupportedEncodingException {
        //1.第一行，大写的红色字体，合并八个单元格
        HSSFRow row1 = sheet.createRow(0);
        row1.setHeight((short) (100 * 5));
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(cellStyleOne(workbook));
        cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell1.setCellValue(("中文"));
        //合并
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));

        //第二行
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2_1 = row2.createCell(0);
        cell2_1.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell2_1.setCellValue(("中文"));
        HSSFCell cell2_2 = row2.createCell(1);
        cell2_2.setCellValue(baseDto.getClazzId());

        //第三行
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell3_1 = row3.createCell(0);
        cell3_1.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell3_1.setCellValue(("中文"));
        HSSFCell cell3_2 = row3.createCell(1);
        cell3_2.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell3_2.setCellValue(("中文"));
    }

    private static String code(String string) throws UnsupportedEncodingException {
        if (string == null) {
            return null;
        }
        string = new String(string.getBytes("GB2312"),"8859_1");
        return string;
    }
}
