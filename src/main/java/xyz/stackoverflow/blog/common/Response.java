package xyz.stackoverflow.blog.common;

import java.io.Serializable;

/**
 * 所有返回消息外包VO
 *
 * @author 凉衫薄
 */
public class Response implements Serializable {

    public final static Integer SUCCESS = 0;
    public final static Integer FAILURE = 1;
    public final static Integer SERVER_ERROR = -1;

    private Integer status;
    private String message;
    private Object data;

    public Response() {

    }

    public Response(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
