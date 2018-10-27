package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.CommentVO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 评论字段校验器
 *
 * @author 凉衫薄
 */
public class CommentValidator extends AbstractBaseValidator<CommentVO> {

    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_\\-]+@([a-zA-Z0-9\\-]+\\.)+[a-zA-Z]+$");
    private final Pattern websitePattern = Pattern.compile("^(http://|https://)([a-zA-Z\\-]+\\.)+[a-zA-Z]+$");

    @Override
    public Map<String, String> validate(CommentVO commentVO) {
        Map<String, String> map = new HashMap<>();

        if ((commentVO.getEmail() != null) && (!validateEmail(commentVO.getEmail()))) {
            map.put("email", "邮箱格式错误或邮箱长度不在0-50之间");
        } else if ((commentVO.getNickname() != null) && (!validateNickName(commentVO.getNickname()))) {
            map.put("nickname", "昵称长度只能在0-50之间");
        } else if ((commentVO.getWebsite() != null) && (!validateWebSite(commentVO.getWebsite()))) {
            map.put("website", "个人网址格式错误或长度不在0-50之间");
        } else if ((commentVO.getContent() != null) && (!validateContent(commentVO.getContent()))) {
            map.put("content", "评论内容长度不在0-50之间");
        } else if ((commentVO.getReplyTo() != null) && (!validateReplyTo(commentVO.getReplyTo()))) {
            map.put("replyTo", "回复对象长度不在0-50之间");
        }
        return map;
    }

    /**
     * 验证昵称
     *
     * @param nickname
     * @return 通过返回true, 不通过返回false
     */
    private boolean validateNickName(String nickname) {
        if (0 < nickname.length() && nickname.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return 通过返回true, 不通过返回false
     */
    private boolean validateEmail(String email) {
        Matcher m = emailPattern.matcher(email);
        if (0 < email.length() && email.length() <= 50) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 验证主页网址
     *
     * @param website
     * @return
     */
    private boolean validateWebSite(String website) {
        Matcher m = websitePattern.matcher(website);
        if (0 < website.length() && website.length() <= 50) {
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 验证评论内容
     *
     * @param content
     * @return
     */
    private boolean validateContent(String content) {
        if (0 < content.length() && content.length() <= 140) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证回复对象
     *
     * @param replyTo
     * @return
     */
    private boolean validateReplyTo(String replyTo) {
        if (0 < replyTo.length() && replyTo.length() <= 50) {
            return true;
        } else {
            return false;
        }
    }
}
