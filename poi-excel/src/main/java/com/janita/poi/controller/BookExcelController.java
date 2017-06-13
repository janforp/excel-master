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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
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
     * 读取本地文件到页面直接下载
     * @throws IOException
     */
    @PostMapping("/excelAndDownload")
    @ApiOperation(value = "excelAndDownload")
    public void createExcelAndDownload(HttpServletResponse response) throws IOException {
        //把该文件写入 response
        response.setContentType("application/x-msdownload");
        response.setCharacterEncoding("utf-8");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+"x.txt");
        //读取文件
        InputStream in = new FileInputStream("c://新建文本文档.txt");
        Writer outputStream = response.getWriter();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            outputStream.write(b);
        }

        in.close();
        outputStream.close();
    }

    @PostMapping("/down3")
    public void down3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = "c://新建文本文档.txt";
        String filePath = "c://新建文本文档.txt";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        bis = new BufferedInputStream(new FileInputStream(filePath));
        bos = new BufferedOutputStream(response.getOutputStream());
        long fileLength = new File(filePath).length();
        response.setContentType("multipart/form-data");
        /**
        *  解决各浏览器的中文乱码问题
        */
        String userAgent = request.getHeader("User-Agent");
        byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
        fileName = new String(bytes, "UTF-8");
        response.setHeader("Content-disposition",
                String.format("attachment; filename=\"%s\"", fileName));
        response.setHeader("Content-Length", String.valueOf(fileLength));
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
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

    /**
     * 页面下载的第二种方法
     * @param response
     * @return
     * @throws IOException
     *
     * TODO 页面下载是乱码
     */
    @PostMapping("/down2")
    public Result download2(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = ExcelUtils.createExcel(bookList, dto);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(("名字" + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff,0,buff.length))){
                bos.write(buff,0,bytesRead);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
        return null;
    }
}