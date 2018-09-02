package xyz.stackoverflow.blog.util;

import xyz.stackoverflow.blog.pojo.RegisterInfoValidateResult;
import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    private static final String emailErrorInfo = "邮箱格式错误";
    private static final String nicknameErrorInfo = "昵称长度不能为0";
    private static final String passwordErrorInfo = "密码长度要大于等于6,且为0-9,a-z,A-Z";

    public static RegisterInfoValidateResult validateRegisterInfo(RegisterVO registerVO){
        RegisterInfoValidateResult result = new RegisterInfoValidateResult();
        if(!validateEmail(registerVO.getEmail())){
            result.setEmailErrorInfo(emailErrorInfo);
        }
        if(!validateNickName(registerVO.getNickname())){
            result.setNicknameErrorInfo(nicknameErrorInfo);
        }
        if(!validatePassword(registerVO.getPassword())){
            result.setPasswordErrorInfo(passwordErrorInfo);
        }
        if((result.getEmailErrorInfo()!="") || (result.getNicknameErrorInfo()!="") || (result.getPasswordErrorInfo()!="")){
            result.setStatus(1);
        }else{
            result.setStatus(0);
        }
        return result;
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
