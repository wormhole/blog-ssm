package xyz.stackoverflow.blog.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段校验器抽象类
 *
 * @author 凉衫薄
 */
public abstract class AbstractBaseValidator<T> implements Validator<T> {


    protected final Pattern codePattern = Pattern.compile("^[a-zA-Z0-9_]+$");
    protected final Pattern chinesePattern = Pattern.compile("^[\\u4e00-\\u9fa5]+$");

    /**
     * 验证编码
     *
     * @param code
     * @return 通过返回true,不通过返回false
     */
    protected boolean validateCode(String code) {
        Matcher m = codePattern.matcher(code);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证中文
     *
     * @param str
     * @return 通过返回true,不通过返回false
     */
    protected boolean validateChinese(String str) {
        Matcher m = chinesePattern.matcher(str);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

}
