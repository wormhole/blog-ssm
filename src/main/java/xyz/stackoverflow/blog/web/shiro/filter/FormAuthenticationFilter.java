package xyz.stackoverflow.blog.web.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.stackoverflow.blog.exception.VCodeException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 表单验证过滤器
 *
 * @author 凉衫薄
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Autowired
    private UserService userService;

    /**
     * 验证验证码
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        String vcode = (String) session.getAttribute("vcode");
        String vcode1 = httpServletRequest.getParameter("vcode");
        if (vcode1 == null || vcode1.equalsIgnoreCase(vcode)) {
            return super.onPreHandle(request, response, mappedValue);
        } else {
            request.setAttribute(getFailureKeyAttribute(), VCodeException.class.getName());
            return true;
        }
    }

    /**
     * 认证成功后将用户对象添加到会话对象中
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String email = (String) subject.getPrincipal();
        User user = userService.selectByCondition(new HashMap<String, Object>() {{
            put("email", email);
        }}).get(0);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", user);
        return super.onLoginSuccess(token, subject, request, response);
    }
}