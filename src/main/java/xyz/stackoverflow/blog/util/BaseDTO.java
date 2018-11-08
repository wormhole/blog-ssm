package xyz.stackoverflow.blog.util;

import java.io.Serializable;
import java.util.Map;

/**
 * DTO基类
 *
 * @author 凉衫薄
 */
public class BaseDTO implements Serializable {

    private Map<String, Map<String,Object>[]> data;

    public BaseDTO() {
    }

    public BaseDTO(Map<String, Map<String, Object>[]> data) {
        this.data = data;
    }

    public Map<String, Map<String, Object>[]> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Object>[]> data) {
        this.data = data;
    }
}
