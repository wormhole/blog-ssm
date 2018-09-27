package xyz.stackoverflow.blog.web.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.util.ResponseJson;

@ControllerAdvice
public class ExceptionController {

    private final Integer exceptionStatus = -1;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseJson handleException(Exception e){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(exceptionStatus);
        responseJson.setData(e.getClass().getSimpleName());
        return responseJson;
    }
}
