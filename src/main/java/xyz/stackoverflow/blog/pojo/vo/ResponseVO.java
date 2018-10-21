package xyz.stackoverflow.blog.pojo.vo;

import java.io.Serializable;

/**
 * 所有返回消息外包VO
 *
 * @author 凉衫薄
 */
public class ResponseVO implements Serializable {

    private Integer status;
    private String message;
    private Object data;

    public ResponseVO(){

    }

    public ResponseVO(Integer status, String message, Object data) {
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
