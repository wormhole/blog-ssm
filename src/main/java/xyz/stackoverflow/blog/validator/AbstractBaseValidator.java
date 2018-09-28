package xyz.stackoverflow.blog.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractBaseValidator<T> implements Validator<T>{

    protected final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");
    protected final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");

    protected boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean validatePassword(String password) {
        Matcher m = passwordPattern.matcher(password);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean validateNickName(String nickname) {
        if (nickname.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean validateSignature(String signature) {
        if (signature.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
