package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.HashMap;
import java.util.Map;

public class RegisterInfoValidator extends AbstractBaseValidator<RegisterVO> {

    @Override
    public Map validate(RegisterVO registerVO){
        Map map = new HashMap<String,String>();

        if ((registerVO.getEmail() != null) && (!validateEmail(registerVO.getEmail()))) {
            map.put("email", "邮箱格式错误或邮箱长度不在0-50之间");
        } else if ((registerVO.getNickname() != null) && (!validateNickName(registerVO.getNickname()))) {
            map.put("nickname", "昵称包含特殊字符或昵称长度不在0-50之间");
        } else if ((registerVO.getPassword() != null) && (!validatePassword(registerVO.getPassword()))) {
            map.put("password", "密码包含特殊字符或密码长度不在6-20之间");
        }

        return map;
    }
}
