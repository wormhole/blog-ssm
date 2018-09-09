package xyz.stackoverflow.blog.web.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.stackoverflow.blog.exception.IncorrectVCodeException;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Autowired
    private UserService userService;

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        String vcode = (String) session.getAttribute("vcode");
        String vcode1 = httpServletRequest.getParameter("vcode");
        if(vcode1 == null || vcode1.equalsIgnoreCase(vcode)){
            return super.onPreHandle(request, response, mappedValue);
        }else{
            request.setAttribute(getFailureKeyAttribute(),IncorrectVCodeException.class.getName());
            return true;
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String email = (String) subject.getPrincipal();
        User user = userService.getUserByEmail(email);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",user);
        return super.onLoginSuccess(token, subject, request, response);
    }
}