package xyz.stackoverflow.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 文件工具类
 */
public class FileUtil {
    public static String getDatePath(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(date);
        return datePath;
    }
}
