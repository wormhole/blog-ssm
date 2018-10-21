package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.ArticleVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 凉衫薄
 * @Date: 2018-10-21
 * @Description: ArticleVO字段校验器
 */
public class ArticleValidator extends AbstractBaseValidator<ArticleVO> {
    @Override
    public Map validate(ArticleVO articleVO) {
        Map map = new HashMap<String, String>();

        if ((articleVO.getTitle() != null) && (!validateCharAndChinese(articleVO.getTitle()))) {
            map.put("title", "标题含有特殊字符");
        } else if ((articleVO.getArticleCode() != null) && (!validateCode(articleVO.getArticleCode()))) {
            map.put("code", "编码只能为数字,字母,下划线");
        }

        return map;
    }
}
