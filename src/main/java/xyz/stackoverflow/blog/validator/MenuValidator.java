package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.MenuVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 菜单字段校验器
 *
 * @author 凉衫薄
 */
public class MenuValidator extends AbstractBaseValidator<MenuVO> {

    private final Pattern urlPattern = Pattern.compile("(^(http://|https://)([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$)|(^/?([a-zA-Z0-9\\-]+/)*[a-zA-Z0-9]+/?$)");

    @Override
    public Map<String, String> validate(MenuVO menuVO) {
        Map<String, String> map = new HashMap<>();

        if ((menuVO.getName() != null) && (!validateName(menuVO.getName()))) {
            map.put("name", "菜单名称长度应该在0到10之间");
        } else if ((menuVO.getUrl() != null) && (!validateUrl(menuVO.getUrl()))) {
            map.put("url", "url格式错误,或长度不在0到50之间");
        }

        return map;
    }

    /**
     * 验证菜单名
     *
     * @param name
     * @return
     */
    private boolean validateName(String name) {
        if (0 < name.length() && name.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证url
     *
     * @param url
     * @return
     */
    private boolean validateUrl(String url) {
        Matcher m = urlPattern.matcher(url);
        if (0 < url.length() && url.length() <= 50) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
