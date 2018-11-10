package xyz.stackoverflow.blog.util;

import java.util.Map;

/**
 * 字典工具类
 *
 * @author 凉衫薄
 */
public class MapUtil {

    /**
     * 判断字典是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.size() == 0);
    }
}
