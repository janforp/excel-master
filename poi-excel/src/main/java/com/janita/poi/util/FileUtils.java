package com.janita.poi.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Janita on 2017/6/13 0013- 下午 3:28
 * 该类是：
 */
public class FileUtils {

    /**
     *
     * @param out   文件流
     * @param filePath  文件路径
     * @return
     * @throws IOException
     */
    public static File file(ByteArrayOutputStream out, String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fos2 = new FileOutputStream(file);
        out.writeTo(fos2);
        out.close();

        return file;
    }
}
