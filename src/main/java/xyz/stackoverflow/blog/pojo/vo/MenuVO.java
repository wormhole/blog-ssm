package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Menu;

/**
 * 菜单VO
 *
 * @author 凉衫薄
 */
public class MenuVO extends Menu {

    protected String deleteTag;

    public MenuVO() {

    }

    public MenuVO(String id, String name, String url, Integer deleteAble, String deleteTag) {
        super(id, name, url, deleteAble);
        this.deleteTag = deleteTag;
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
        return menu;
    }
}
