package com.janita.download.controller;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Janita on 2017/6/13 0013- 下午 6:28
 * 该类是：
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    private final String APPLICATION_PDF = "application/pdf";

    @RequestMapping(value = "/a", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public void downloadA(HttpServletResponse response) throws IOException {
        File file = getFile();
        InputStream in = new FileInputStream(file);

        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    private File getFile(){
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "static/1.xls");
        try {
            File file = new File(resource.getURL().getPath());
            if (!file.exists()){
                return null;
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
