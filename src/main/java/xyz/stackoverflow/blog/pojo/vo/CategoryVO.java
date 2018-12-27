package xyz.stackoverflow.blog.pojo.vo;

import org.hibernate.validator.constraints.Length;
import xyz.stackoverflow.blog.pojo.entity.Category;
import xyz.stackoverflow.blog.util.web.SuperVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 分类VO
 *
 * @author 凉衫薄
 */
public class CategoryVO implements SuperVO {

    @NotNull(message = "缺少主键字段", groups = {DeleteGroup.class, UpdateGroup.class})
    private String id;

    @NotNull(message = "缺少分类名字段", groups = {InsertGroup.class, UpdateGroup.class})
    @Length(min = 1, max = 100, message = "分类名长度只能在1到100之间", groups = {InsertGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[\\u4e00-\\u9fa50-9a-zA-Z_]+$", message = "分类名只能包含中文数字字母下划线", groups = {InsertGroup.class, UpdateGroup.class})
    private String name;

    @NotNull(message = "缺少编码字段", groups = {InsertGroup.class, UpdateGroup.class})
    @Length(min = 1, max = 100, message = "编码长度只能在1到100之间", groups = {InsertGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "编码只能为字母数字下划线", groups = {InsertGroup.class, UpdateGroup.class})
    private String code;

    private Integer deleteAble;

    //以下为扩展字段
    private Integer articleCount;
    private String deleteTag;

    public interface InsertGroup {
    }

    public interface UpdateGroup {
    }

    public interface DeleteGroup {
    }

    public CategoryVO() {

    }

    public CategoryVO(String id, String name, String code, Integer deleteAble, Integer articleCount, String deleteTag) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.deleteAble = deleteAble;
        this.articleCount = articleCount;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag;
    }

    /**
     * 转换成实体类
     *
     * @return 转换后的实体类
     */
    public Category toCategory() {
        Category category = new Category();
        category.setId(id);
        category.setCode(code);
        category.setName(name);
        category.setDeleteAble(deleteAble);
        return category;
    }
}
