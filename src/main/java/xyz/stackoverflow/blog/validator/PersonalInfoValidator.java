package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.PersonalInfoVO;

import java.util.HashMap;
import java.util.Map;

public class PersonalInfoValidator extends AbstractBaseValidator<PersonalInfoVO> {

    @Override
    public Map validate(PersonalInfoVO personalInfoVO) {
        Map map = new HashMap<String, String>();

        if ((personalInfoVO.getEmail() != null) && (!validateEmail(personalInfoVO.getEmail()))) {
            map.put("email", "邮箱格式错误");
        } else if ((personalInfoVO.getNickname() != null) && (!validateNickName(personalInfoVO.getNickname()))) {
            map.put("nickname", "昵称长度要大于等于0");
        } else if ((personalInfoVO.getSignature() != null) && (!validateSignature(personalInfoVO.getSignature()))) {
            map.put("signature", "个性签名长度要大于0");
        } else if ((personalInfoVO.getNewPassword() != null) && (!validatePassword(personalInfoVO.getNewPassword()))) {
            map.put("password", "密码长度要大于等于6,且为0-9,a-z,A-Z之间");
        }

        return map;
    }

    private boolean validateSignature(String signature) {
        if (signature.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
