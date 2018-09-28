package xyz.stackoverflow.blog.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.stackoverflow.blog.util.ResponseJson;

@ControllerAdvice
public class ExceptionController {

    private final Integer exceptionStatus = -1;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseJson handleException(Exception e){
        ResponseJson responseJson = new ResponseJson();
        responseJson.setStatus(exceptionStatus);
        responseJson.setMessage(e.getClass().getSimpleName());
        return responseJson;
    }
}
