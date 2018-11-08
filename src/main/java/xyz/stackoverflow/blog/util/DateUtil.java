package xyz.stackoverflow.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author 凉衫薄
 */
public class DateUtil {

    /**
     * 获取日期格式路径
     *
     * @return 返回日期格式路径
     */
    public static String getDatePath(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String datePath = sdf.format(date);
        return datePath;
    }
}
