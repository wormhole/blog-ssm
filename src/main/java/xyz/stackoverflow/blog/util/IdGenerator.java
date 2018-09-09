package xyz.stackoverflow.blog.util;

import java.util.UUID;

public class IdGenerator {

    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
