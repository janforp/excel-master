package com.janita.download.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

/**
 * Created by Janita on 2017/6/14 0014- 上午 9:24
 * 该类是：
 */
public class DownloadUtils {

    public static File getFile(){
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "excel/template.pdf");
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
