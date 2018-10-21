package xyz.stackoverflow.blog.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;

/**
 * 全局异常处理控制器
 *
 * @author 凉衫薄
 */
public class ExceptionController {

    private final Integer exceptionStatus = -1;

    /**
     * 所有异常统一处理
     *
     * @param e 异常对象
     * @return 返回ResponseVO
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseVO handleException(Exception e){
        ResponseVO response = new ResponseVO();
        response.setStatus(exceptionStatus);
        response.setMessage(e.getClass().getSimpleName());
        response.setData(e);
        return response;
    }
}
