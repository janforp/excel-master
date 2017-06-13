package com.janita.poi.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Created by Janita on 2017/6/12 0012- 上午 11:49
 * 该类是：
 */
public class StyleUtils {

    /**
     * 设置字体，黑体，红色，14
     * @return  用于表头显示“表格填写注意事项.....”
     */
    public static Font fontOne(HSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        return font;
    }

    /**
     * 设置字体，宋体，黑色，11
     * 用于除警告信息外所有文字
     * @return  设置字体，宋体，黑色，12
     */
    public static Font fontTwo(HSSFWorkbook workbook) {
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);// 设置字体大小

        return font;
    }

    public static CellStyle cellStyleOne(HSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(fontOne(workbook));
        cellStyle.setLocked(true);

        return cellStyle;
    }
}
