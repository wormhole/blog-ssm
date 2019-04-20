package net.stackoverflow.blog.task;

import net.stackoverflow.blog.util.DBUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 数据库备份任务
 *
 * @author 凉衫薄
 */
@Component
public class BackupTask {

    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.host}")
    private String host;
    @Value("${jdbc.db}")
    private String db;
    @Value("${server.backup.path}")
    private String path;

    @Scheduled(initialDelay = 10000, fixedRate = 600000)
    @Async
    public void backup() {
        DBUtils.backup(host, username, password, path, "blog.sql", db);
    }
}
