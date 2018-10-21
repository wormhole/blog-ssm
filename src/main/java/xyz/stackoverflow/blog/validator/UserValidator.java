package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

/**
 * UserVO字段校验器
 *
 * @author 凉衫薄
 */
public class UserValidator extends AbstractBaseValidator<UserVO> {

    /**
     * 校验UserVO字段
     *
     * @param userVO
     * @return 返回验证结果集
     */
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

    /**
     * 校验签名方法
     *
     * @param signature
     * @return 通过返回ture,不通过返回false
     */
    private boolean validateSignature(String signature) {
        if (0 < signature.length() && signature.length() <= 50) {
            return validateCharAndChinese(signature);
        } else {
            return false;
        }
    }
}
