package xyz.stackoverflow.blog.util;

import java.util.UUID;

/**
 * ID生成器工具类
 *
 * @author 凉衫薄
 */
public class UUIDGenerator {

    /**
     * 获取ID
     *
     * @return 返回36位ID
     */
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
