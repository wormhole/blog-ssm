package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterVOValidator {
    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    public static Map validate(RegisterVO registerVO){
        Map map = new HashMap<String,String>();

        if(!validateEmail(registerVO.getEmail())){
            map.put("email","邮箱格式错误");
        }else if(!validateNickName(registerVO.getNickname())){
            map.put("nickname","昵称长度要大于等于0");
        }else if(!validatePassword(registerVO.getPassword())){
            map.put("password","密码长度要大于等于6,且为0-9,a-z,A-Z之间");
        }
        return map;
    }

    private static boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateNickName(String nickname) {
        if (nickname.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
