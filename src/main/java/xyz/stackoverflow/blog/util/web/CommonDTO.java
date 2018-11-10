package xyz.stackoverflow.blog.util.web;

import java.io.Serializable;
import java.util.Map;

/**
 * DTO基类
 *
 * @author 凉衫薄
 */
public class CommonDTO implements Serializable {

    private Map<String, Map<String,Object>[]> data;

    public CommonDTO() {
    }

    public CommonDTO(Map<String, Map<String, Object>[]> data) {
        this.data = data;
    }

    public Map<String, Map<String, Object>[]> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Object>[]> data) {
        this.data = data;
    }
}
