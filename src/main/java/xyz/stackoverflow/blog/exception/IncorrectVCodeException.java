package xyz.stackoverflow.blog.exception;

/**
 * 验证码错误异常类
 *
 * @author 凉衫薄
 */
public class IncorrectVCodeException extends Exception {
    public IncorrectVCodeException(String message) {
        super(message);
    }
}
