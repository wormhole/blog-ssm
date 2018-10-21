package xyz.stackoverflow.blog.exception;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: 验证码错误异常类
 */
public class IncorrectVCodeException extends Exception {
    public IncorrectVCodeException(String message) {
        super(message);
    }
}
