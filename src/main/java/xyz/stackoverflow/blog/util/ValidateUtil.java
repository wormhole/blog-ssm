package xyz.stackoverflow.blog.util;

import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;
import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    private static final Integer noError = 0;
    private static final Integer emailError = 1;
    private static final Integer nicknameError = 2;
    private static final Integer passwordError = 3;

    public static Integer validateRegisterInfo(RegisterVO registerVO){
        if(!validateEmail(registerVO.getEmail())){
            return emailError;
        }
        if(!validateNickName(registerVO.getNickname())){
            return nicknameError;
        }
        if(!validatePassword(registerVO.getPassword())){
            return passwordError;
        }
        return noError;
    }

    public static Integer validateBaseInfo(BaseInfoVO baseInfoVO){
        if(!validateEmail(baseInfoVO.getEmail())){
            return emailError;
        }
        if(!validateNickName(baseInfoVO.getNickname())){
            return nicknameError;
        }
        return noError;
    }

    private static boolean validateEmail(String email){
        Matcher m = emailPattern.matcher(email);
        if(m.find()){
            return true;
        }else{
            return false;
        }
    }

    private static boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if(m.find()){
            return true;
        }else {
            return false;
        }
    }

    private static boolean validateNickName(String nickname){
        if(nickname.length() == 0){
            return false;
        }else{
            return true;
        }
    }
}
