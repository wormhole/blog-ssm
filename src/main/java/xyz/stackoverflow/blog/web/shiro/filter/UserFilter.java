package xyz.stackoverflow.blog.web.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义UserFilter在记住密码登陆时,往session中添加用户
 *
 * @author 凉衫薄
 */
public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() != null) {
            String email = (String) subject.getPrincipal();
            User user = userService.getUserByEmail(email);
            ((HttpServletRequest) request).getSession().setAttribute("user", user);
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
