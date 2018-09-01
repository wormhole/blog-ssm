package xyz.stackoverflow.blog.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static final int hashIterations = 1;

    public static String getSalt(){
        return randomNumberGenerator.nextBytes().toHex();
    }

    public static String encryptPassword(String salt,String password){
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
        return newPassword;
    }

}