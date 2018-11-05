package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体类
 *
 * @author 凉衫薄
 */
public class Menu implements Serializable {

    protected String id;
    protected String name;
    protected String url;
    protected Integer deleteAble;
    protected Date date;

    public Menu(){

    }

    public Menu(String id, String name, String url, Integer deleteAble, Date date) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.deleteAble = deleteAble;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
