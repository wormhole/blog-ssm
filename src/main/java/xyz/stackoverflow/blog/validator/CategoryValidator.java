package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.CategoryVO;

import java.util.HashMap;
import java.util.Map;

public class CategoryValidator extends AbstractBaseValidator<CategoryVO> {
    @Override
    public Map validate(CategoryVO categoryVO) {
        Map map = new HashMap<String, String>();
        if ((categoryVO.getCategoryName() != null) && (!validateCharAndChinese(categoryVO.getCategoryName()))) {
            map.put("name", "分类名含有特殊字符");
        } else if ((categoryVO.getCategoryCode() != null) && (!validateCode(categoryVO.getCategoryCode()))) {
            map.put("code", "编码中只能含有数字,字母,下划线");
        }
        return map;
    }
}
