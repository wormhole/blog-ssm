package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.entity.Category;

import java.util.HashMap;
import java.util.Map;

public class CategoryValidator extends AbstractBaseValidator<Category> {
    @Override
    public Map validate(Category category) {
        Map map = new HashMap<String, String>();
        if ((category.getCategoryName() != null) && (!validateCharAndChinese(category.getCategoryName()))) {
            map.put("name", "分类名只能含有特殊字符");
        } else if ((category.getCategoryCode() != null) && (!validateCode(category.getCategoryCode()))) {
            map.put("code", "编码中只能含有数字,字母,下划线");
        }
        return map;
    }
}
