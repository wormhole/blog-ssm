package xyz.stackoverflow.blog.util;

import java.util.UUID;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: ID生成器工具类
 */
public class IdGenerator {

    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
