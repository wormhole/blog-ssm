package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.PersonalInfoVO;

import java.util.HashMap;
import java.util.Map;

public class PersonalInfoValidator extends AbstractBaseValidator<PersonalInfoVO> {

    @Override
    public Map validate(PersonalInfoVO personalInfoVO) {
        Map map = new HashMap<String, String>();

        if ((personalInfoVO.getEmail() != null) && (!validateEmail(personalInfoVO.getEmail()))) {
            map.put("email", "邮箱格式错误或邮箱长度不在0-50之间");
        } else if ((personalInfoVO.getNickname() != null) && (!validateNickName(personalInfoVO.getNickname()))) {
            map.put("nickname", "昵称包含特殊字符或昵称长度不在0-50之间");
        } else if ((personalInfoVO.getSignature() != null) && (!validateSignature(personalInfoVO.getSignature()))) {
            map.put("signature", "签名包含特殊字符或签名长度不在0-50之间");
        } else if ((personalInfoVO.getNewPassword() != null) && (!validatePassword(personalInfoVO.getNewPassword()))) {
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
