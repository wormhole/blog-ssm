package xyz.stackoverflow.blog.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.util.web.SuperVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 菜单VO
 *
 * @author 凉衫薄
 */
public class MenuVO implements SuperVO {

    @NotNull(message = "缺少主键字段", groups = {DeleteGroup.class, UpdateGroup.class})
    private String id;

    @NotNull(message = "缺少菜单名字段", groups = {InsertGroup.class, UpdateGroup.class})
    @Length(min = 1, max = 10, message = "菜单名的长度必须在1到10之间", groups = {InsertGroup.class, UpdateGroup.class})
    private String name;

    @NotNull(message = "缺少URL字段", groups = {InsertGroup.class, UpdateGroup.class})
    @Length(min = 1, max = 50, message = "URL的长度必须在1到50之间", groups = {InsertGroup.class, UpdateGroup.class})
    @Pattern(regexp = "(^(http://|https://)([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$)|(^/([a-zA-Z0-9\\-]+/)*[a-zA-Z0-9]+/?$)", message = "URL格式错误", groups = {InsertGroup.class, UpdateGroup.class})
    private String url;

    private Integer deleteAble;
    private Date date;

    //以下为扩展字段
    private String deleteTag;

    public interface DeleteGroup {
    }

    public interface InsertGroup {
    }

    public interface UpdateGroup {
    }

    public MenuVO() {

    }

    public MenuVO(String id, String name, String url, Integer deleteAble, Date date, String deleteTag) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.deleteAble = deleteAble;
        this.date = date;
        this.deleteTag = deleteTag;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag;
    }

    /**
     * VO类转实体类
     *
     * @return
     */
    public Menu toMenu() {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setUrl(url);
        menu.setDeleteAble(deleteAble);
        menu.setDate(date);
        return menu;
    }
}
