package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseInfoVOValidator {

    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    public static Map validate(BaseInfoVO baseInfoVO){
        Map map = new HashMap<String,String>();

        if(!validateEmail(baseInfoVO.getEmail())){
            map.put("email","邮箱格式错误");
        }else if(!validateNickName(baseInfoVO.getNickname())){
            map.put("nickname","昵称长度要大于等于0");
        }else if(!validateSignature(baseInfoVO.getSignature())){
            map.put("signature","个性签名长度要大于0");
        }
        return map;
    }

    private static boolean validateSignature(String signature) {
        if (signature.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
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
