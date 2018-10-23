package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.ArticleVO;

import java.util.HashMap;
import java.util.Map;

/**
 * ArticleVO字段校验器
 *
 * @author 凉衫薄
 */
public class ArticleValidator extends AbstractBaseValidator<ArticleVO> {

    /**
     * 校验articleVO字段
     *
     * @param articleVO
     * @return 返回验证结果集
     */
    @Override
    public Map<String,String> validate(ArticleVO articleVO) {
        Map<String,String> map = new HashMap<>();

        if ((articleVO.getTitle() != null) && (!validateTitle(articleVO.getTitle()))) {
            map.put("title", "标题长度只能在0到50之间");
        } else if ((articleVO.getArticleCode() != null) && (!validateCode(articleVO.getArticleCode()))) {
            map.put("code", "编码只能为数字,字母,下划线");
        }

        return map;
    }

    /**
     * 验证标题
     *
     * @param title
     * @return 通过返回true,不通过返回false
     */
    private boolean validateTitle(String title) {
        if (0 < title.length() && title.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }
}
