package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.ArticleVO;

import java.util.HashMap;
import java.util.Map;

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
