package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: UserVO字段校验器
 */
public class UserValidator extends AbstractBaseValidator<UserVO> {

    @Override
    public Map validate(UserVO userVO) {
        Map map = new HashMap<String, String>();

        if ((userVO.getEmail() != null) && (!validateEmail(userVO.getEmail()))) {
            map.put("email", "邮箱格式错误或邮箱长度不在0-50之间");
        } else if ((userVO.getNickname() != null) && (!validateNickName(userVO.getNickname()))) {
            map.put("nickname", "昵称包含特殊字符或昵称长度不在0-50之间");
        } else if ((userVO.getSignature() != null) && (!validateSignature(userVO.getSignature()))) {
            map.put("signature", "签名包含特殊字符或签名长度不在0-50之间");
        } else if ((userVO.getPassword() != null) && (!validatePassword(userVO.getPassword()))) {
            map.put("password", "密码包含特殊字符或密码长度不在6-20之间");
        }

        return map;
    }

    private boolean validateSignature(String signature) {
        if (0 < signature.length() && signature.length() <= 50) {
            return validateCharAndChinese(signature);
        } else {
            return false;
        }
    }
}
