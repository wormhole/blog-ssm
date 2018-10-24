package xyz.stackoverflow.blog.exception;

/**
 * 服务器异常类
 *
 * @author 凉衫薄
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
