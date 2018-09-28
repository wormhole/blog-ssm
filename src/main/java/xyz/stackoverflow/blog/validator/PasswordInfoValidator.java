package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.PasswordVO;

import java.util.HashMap;
import java.util.Map;

public class PasswordInfoValidator extends AbstractBaseValidator<PasswordVO> {

    @Override
    public Map validate(PasswordVO passwordVO) {
        Map map = new HashMap<String,String>();
        if (!validatePassword(passwordVO.getNewPassword())) {
            map.put("password","密码长度要大于等于6,且为0-9,a-z,A-Z之间");
        }
        return map;
    }
}
