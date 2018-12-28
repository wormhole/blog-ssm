package xyz.stackoverflow.blog.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.stackoverflow.blog.util.db.DBUtil;

import javax.servlet.ServletContext;

@Component
public class BackupTask {

    @Autowired
    private ServletContext servletContext;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.host}")
    private String host;
    @Value("${jdbc.db}")
    private String db;

    @Scheduled(initialDelay = 10000, fixedRate = 600000)
    public void backup() {
        System.out.println("xxxxxxxxxxxxxxxx");
        String backupPath = servletContext.getRealPath("WEB-INF/backup");
        DBUtil.backup(host, username, password, backupPath, "blog.sql", db);
    }
}
