package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.RegisterVO;

import java.util.HashMap;
import java.util.Map;

public class RegisterInfoValidator extends AbstractBaseValidator<RegisterVO> {

    @Override
    public Map validate(RegisterVO registerVO){
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
}
