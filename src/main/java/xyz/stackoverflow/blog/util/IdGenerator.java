package xyz.stackoverflow.blog.util;

import java.util.UUID;

public class IdGenerator {

    private static final UUID uuid = UUID.randomUUID();

    public static String getId(){
        return uuid.toString();
    }
}
