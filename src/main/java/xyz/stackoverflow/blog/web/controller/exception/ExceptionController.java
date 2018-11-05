package xyz.stackoverflow.blog.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.stackoverflow.blog.exception.ServiceException;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理控制器
 *
 * @author 凉衫薄
 */
public class ExceptionController {

    private final Integer STATUS = -1;

    /**
     * 处理AJAX请求500错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handleException(Exception e, HttpServletRequest request) {
        if (request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
            ResponseVO response = new ResponseVO();
            response.setStatus(STATUS);
            response.setMessage(e.getClass().getSimpleName());
            response.setData(e.getMessage());
            return response;
        } else {
            throw new ServiceException(e.getClass().getSimpleName());
        }
    }
}
