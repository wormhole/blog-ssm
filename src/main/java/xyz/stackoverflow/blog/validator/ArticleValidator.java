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

        if ((articleVO.getTitle() != null) && (!validateCharAndChinese(articleVO.getTitle()))) {
            map.put("title", "标题含有特殊字符");
        } else if ((articleVO.getArticleCode() != null) && (!validateCode(articleVO.getArticleCode()))) {
            map.put("code", "编码只能为数字,字母,下划线");
        }

        return map;
    }
}
