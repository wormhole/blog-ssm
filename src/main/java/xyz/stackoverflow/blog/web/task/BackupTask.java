package xyz.stackoverflow.blog.web.task;

import org.apache.ibatis.io.Resources;
import xyz.stackoverflow.blog.util.DBUtil;

import java.io.*;
import java.util.Properties;
import java.util.TimerTask;

/**
 * 数据库备份周期任务
 *
 * @author 凉衫薄
 */
public class BackupTask extends TimerTask {

    private String backupPath;

    public BackupTask(String backupPath) {
        this.backupPath = backupPath;
    }

    @Override
    public void run() {
        Properties props = null;
        try {
            props = Resources.getResourceAsProperties("db.properties");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String host = props.getProperty("jdbc.host");
            String db = props.getProperty("jdbc.db");
            DBUtil.backup(host, username, password, backupPath, "blog.sql", db);
        } catch (IOException e) {

        }
    }

}