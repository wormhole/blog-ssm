package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String catetory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatetory() {
        return catetory;
    }

    public void setCatetory(String catetory) {
        this.catetory = catetory;
    }
}
