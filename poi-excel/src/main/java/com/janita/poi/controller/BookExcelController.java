package com.janita.poi.controller;

import com.janita.excel.common.entity.BaseDto;
import com.janita.excel.common.entity.Book;
import com.janita.excel.common.result.Result;
import com.janita.poi.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

import static com.janita.poi.util.FileUtils.file;

/**
 * Created by Janita on 2017/6/12 0012- 上午 11:24
 * 该类是：
 */
@RequestMapping(value = "/test")
@RestController
@Api(description = "excel")
public class BookExcelController {

    private List<Book> bookList = Book.getBookList();
    private BaseDto dto = BaseDto.getBaseDto();

    /**
     * 生成本地文件的 excel
     * @throws IOException
     */
    @PostMapping
    @ApiOperation(value = "excel")
    public void createExcel() throws IOException {
        HSSFWorkbook workbook = ExcelUtils.createExcel(bookList, dto);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String filePath = "c:\\1.xls";
        workbook.write(out);
        file(out,filePath);
    }

    /**
     * 生成本地文件，并且返回文件路径给前端
     * @param request
     * @throws IOException
     */
    @PostMapping("/path")
    public Result pathExcel(HttpServletRequest request) throws IOException {
        HSSFWorkbook workbook = ExcelUtils.createExcel(bookList, dto);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        String filePath = "poi-excel/src/main/resources/static/"+ UUID.randomUUID().toString() +"name.xls";
        File file = file(out, filePath);
        //TODO  在 static 中动态生成的静态资源，需要重启之后才能直接服务，如何解？
        return Result.success("success", file.getAbsolutePath());
    }

    /**
     * 直接页面下载
     * @param response
     * @return
     *
     * 直接下载下来是乱码
     */
    @PostMapping("/down")
    public Result download(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = ExcelUtils.createExcel(bookList, dto);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);

        byte[] bytes = out.toByteArray();
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment;filename=" + "excel名字" + ".xls");
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        //TODO  页面下载是乱码，何解？


        return Result.success("","");
    }
}