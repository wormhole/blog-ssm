package xyz.stackoverflow.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static String getDatePath(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(date);
        return datePath;
    }
}
