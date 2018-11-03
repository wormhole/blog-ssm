package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.CategoryVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CategoryVO字段校验器
 *
 * @author 凉衫薄
 */
public class CategoryValidator extends AbstractBaseValidator<CategoryVO> {

    private final Pattern categoryNamePattern = Pattern.compile("^[\\u4e00-\\u9fa50-9a-zA-Z_]+$");

    /**
     * 校验CategoryVO字段
     *
     * @param categoryVO
     * @return 返回验证结果集
     */
    @Override
    public Map<String, String> validate(CategoryVO categoryVO) {
        Map<String, String> map = new HashMap<>();
        if ((categoryVO.getCategoryName() != null) && (!validateCategoryName(categoryVO.getCategoryName()))) {
            map.put("name", "分类名长度只能在0到20之间,且只能是中文,数字,字母,下划线");
        } else if ((categoryVO.getCategoryCode() != null) && (!validateCategoryCode(categoryVO.getCategoryCode()))) {
            map.put("code", "编码中只能含有数字,字母,下划线");
        }
        return map;
    }

    /**
     * 验证分类名
     *
     * @param categoryName
     * @return 通过返回true, 不通过返回false
     */
    private boolean validateCategoryName(String categoryName) {
        if (0 < categoryName.length() && categoryName.length() <= 20) {
            Matcher m = categoryNamePattern.matcher(categoryName);
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateCategoryCode(String categoryCode) {
        if (0 < categoryCode.length() && categoryCode.length() <= 20) {
            return validateCode(categoryCode);
        } else {
            return false;
        }
    }
}
