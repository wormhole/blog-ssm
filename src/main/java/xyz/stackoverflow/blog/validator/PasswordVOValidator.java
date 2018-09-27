package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.PasswordVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordVOValidator {

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");

    public static Map validatePasswordVO(PasswordVO passwordVO) {
        Map map = new HashMap<String,String>();
        if (!validatePassword(passwordVO.getNewPassword())) {
            map.put("password","密码长度要大于等于6,且为0-9,a-z,A-Z之间");
        }
        return map;
    }

    private static boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
