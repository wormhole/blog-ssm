package xyz.stackoverflow.blog.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractBaseValidator<T> implements Validator<T> {

    protected final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9_]+$");
    protected final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");
    protected final Pattern codePattern = Pattern.compile("^[a-zA-Z0-9_]+$");
    protected final Pattern charAndChinesePattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$");

    protected boolean validateEmail(String email) {
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

    protected boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if (0 < password.length() && password.length() <= 20) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected boolean validateNickName(String nickname) {
        if (0 < nickname.length() && nickname.length() <= 50) {
            return validateCharAndChinese(nickname);
        } else {
            return false;
        }
    }

    protected boolean validateCode(String code) {
        Matcher m = codePattern.matcher(code);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean validateCharAndChinese(String str) {
        Matcher m = charAndChinesePattern.matcher(str);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

}
