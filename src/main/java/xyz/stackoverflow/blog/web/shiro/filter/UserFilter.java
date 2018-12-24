package xyz.stackoverflow.blog.web.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

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
            List<User> list = userService.selectByCondition(new HashMap<String, Object>() {{
                put("email", email);
            }});
            if (list.size() != 0) {
                ((HttpServletRequest) request).getSession().setAttribute("user", list.get(0));
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
