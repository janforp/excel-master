package com.janita.download.controller;

import com.janita.download.bean.UserBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.janita.download.constant.DownloadConsts.APPLICATION_PDF;
import static com.janita.download.util.DownloadUtils.getFile;
import static com.janita.download.util.DownloadUtils.setFileDownloadHeader;

/**
 * Created by Janita on 2017/6/14 0014- 上午 9:22
 * 该类是：
 */
@RestController
@RequestMapping(value = "/down2")
public class DownloadByEntityController {

    /**
     * get请求下载能成功，且不礼貌
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/b",method = RequestMethod.GET, produces = APPLICATION_PDF)
    public HttpEntity<byte[]> downloadB() throws IOException {
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition", "attachment; filename=" + file.getName());
        header.setContentLength(document.length);
        return new HttpEntity<>(document, header);
    }


    /**
     * post请求
     * @return
     * @throws IOException
     * 返回如下：
    {
    "timestamp": 1497404093987,
    "status": 405,
    "error": "Method Not Allowed",
    "exception": "org.springframework.web.HttpRequestMethodNotSupportedException",
    "message": "Request method 'POST' not supported",
    "path": "/down2/b"
    }
     */
    @RequestMapping(value = "/bb", method = RequestMethod.POST, produces = APPLICATION_PDF)
    public HttpEntity<byte[]> downloadB_POST() throws IOException {
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.setContentLength(document.length);
        header.set("Content-Disposition", "attachment; filename=" + file.getName());
        return new HttpEntity<>(document, header);
    }

    /**
     * get请求，看能不能通过请求体传参
     * @param userBean
     * @return
     */
    @RequestMapping(value = "/bbbb", method = RequestMethod.POST, produces = APPLICATION_PDF)
    public HttpEntity<byte[]> download_get_2(@RequestBody UserBean userBean, HttpServletResponse response) throws IOException {
        System.out.println("\n****** " + userBean);
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.setContentLength(document.length);
//        header.set("Content-Disposition", "attachment; filename=" + "中文.pdf");
        setFileDownloadHeader(response,"中文.pdf");
        return new HttpEntity<>(document, header);
    }
}
