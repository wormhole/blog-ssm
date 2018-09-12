package xyz.stackoverflow.blog.util;

import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;
import xyz.stackoverflow.blog.pojo.vo.PasswordVO;
import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    public static Integer validateRegisterVO(RegisterVO registerVO){
        if(!validateEmail(registerVO.getEmail())){
            return ResponseStatusEnum.EMAILERROR.getStatus();
        }
        if(!validateNickName(registerVO.getNickname())){
            return ResponseStatusEnum.NICKNAMEERROR.getStatus();
        }
        if(!validatePassword(registerVO.getPassword())){
            return ResponseStatusEnum.PASSWORDERROR.getStatus();
        }
        return ResponseStatusEnum.SUCCESS.getStatus();
    }

    public static Integer validateBaseInfoVO(BaseInfoVO baseInfoVO){
        if(!validateEmail(baseInfoVO.getEmail())){
            return ResponseStatusEnum.EMAILERROR.getStatus();
        }
        if(!validateNickName(baseInfoVO.getNickname())){
            return ResponseStatusEnum.NICKNAMEERROR.getStatus();
        }
        return ResponseStatusEnum.SUCCESS.getStatus();
    }

    public static Integer validatePasswordVO(PasswordVO passwordVO){
        if(!validatePassword(passwordVO.getNewPassword())){
            return ResponseStatusEnum.PASSWORDERROR.getStatus();
        }
        return ResponseStatusEnum.SUCCESS.getStatus();
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
