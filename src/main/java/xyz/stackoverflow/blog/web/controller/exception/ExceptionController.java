package xyz.stackoverflow.blog.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.exception.ServerException;
import xyz.stackoverflow.blog.util.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理控制器
 *
 * @author 凉衫薄
 */
@ControllerAdvice
public class ExceptionController {

    private final Integer SERVER_STATUS = 2;
    private final Integer BUSINESS_STATUS = 1;

    /**
     * 处理业务异常
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Response handleBusinessException(BusinessException e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            Response response = new Response();
            response.setStatus(BUSINESS_STATUS);
            response.setMessage(e.getMessage());
            response.setData(e.getData());
            return response;
        } else {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 处理AJAX请求500错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response handleException(Exception e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            Response response = new Response();
            response.setStatus(SERVER_STATUS);
            response.setMessage(e.getMessage());
            response.setData(e.getStackTrace());
            return response;
        } else {
            throw new ServerException(e.getClass().getName());
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        if ((request.getHeader("accept") != null && request.getHeader("accept").contains("application/json")) || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
            return true;
        } else {
            return false;
        }
    }
}
