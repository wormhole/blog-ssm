package xyz.stackoverflow.blog.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.stackoverflow.blog.pojo.entity.Visitor;
import xyz.stackoverflow.blog.service.VisitorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 访客记录监听器
 *
 * @author 凉衫薄
 */
public class VisitorListener implements SessionListener {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void onStart(Session session) {
        String ip = request.getRemoteAddr();
        String agent = request.getHeader("User-Agent");
        Date date = new Date();
        Visitor visitor = new Visitor(null, ip, agent, date);
        visitorService.insert(visitor);
    }

    @Override
    public void onStop(Session session) {

    }

    @Override
    public void onExpiration(Session session) {

    }
}
