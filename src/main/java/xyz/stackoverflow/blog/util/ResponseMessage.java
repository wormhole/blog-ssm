package xyz.stackoverflow.blog.util;

import java.io.Serializable;

public class ResponseMessage implements Serializable {

    private Integer status;
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
