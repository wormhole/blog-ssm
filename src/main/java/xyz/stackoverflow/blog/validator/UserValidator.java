package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.UserVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserVO字段校验器
 *
 * @author 凉衫薄
 */
public class UserValidator extends AbstractBaseValidator<UserVO> {

    private final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9_]+$");
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    /**
     * 校验UserVO字段
     *
     * @param userVO
     * @return 返回验证结果集
     */
    @Override
    public Map<String,String> validate(UserVO userVO) {
        Map<String,String> map = new HashMap<>();

        if ((userVO.getEmail() != null) && (!validateEmail(userVO.getEmail()))) {
            map.put("email", "邮箱格式错误或邮箱长度不在0-50之间");
        } else if ((userVO.getNickname() != null) && (!validateNickName(userVO.getNickname()))) {
            map.put("nickname", "昵称长度只能在0-50之间");
        } else if ((userVO.getSignature() != null) && (!validateSignature(userVO.getSignature()))) {
            map.put("signature", "签名长度只能在0-50之间");
        } else if ((userVO.getPassword() != null) && (!validatePassword(userVO.getPassword()))) {
            map.put("password", "密码长度只能在6-20之间");
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
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证昵称
     *
     * @param nickname
     * @return 通过返回true,不通过返回false
     */
    private boolean validateNickName(String nickname) {
        if (0 < nickname.length() && nickname.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return 通过返回true,不通过返回false
     */
    private boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        if (0 < email.length() && email.length() <= 50) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 验证密码
     *
     * @param password
     * @return 通过返回true,不通过返回false
     */
    private boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if (6 <= password.length() && password.length() <= 20) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
