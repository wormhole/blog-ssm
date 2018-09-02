package xyz.stackoverflow.blog.web.shiro.filter;

import org.apache.shiro.web.servlet.ShiroHttpSession;
import xyz.stackoverflow.blog.exception.IncorrectVCodeException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest)request;
        ShiroHttpSession session = (ShiroHttpSession) req.getSession();
        String vcode = (String) session.getAttribute("vcode");
        String vcode1 = req.getParameter("vcode");
        if(vcode1 == null || vcode1.equalsIgnoreCase(vcode)){
            return super.onPreHandle(request, response, mappedValue);
        }else{
            request.setAttribute(getFailureKeyAttribute(),IncorrectVCodeException.class.getName());
            return true;
        }
    }
}