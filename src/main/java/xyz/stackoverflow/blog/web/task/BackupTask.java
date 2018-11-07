package xyz.stackoverflow.blog.web.task;

import org.apache.ibatis.io.Resources;

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
            backup("localhost", username, password, backupPath, "blog.sql", "blog");
        } catch (IOException e) {

        }
    }

    /**
     * 备份数据库
     *
     * @param host
     * @param username
     * @param password
     * @param path
     * @param filename
     * @param database
     * @return
     */
    public boolean backup(String host, String username, String password, String path, String filename, String database) {
        File saveFile = new File(path);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        if(!path.endsWith(File.separator)){
            path = path + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        boolean success = false;
        try {
            process = Runtime.getRuntime().exec("mysqldump -h" + host + " -u" + username + " -p" + password + " " + database);
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path + filename)));
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){
                success = true;
            }
        } catch (Exception e) {
            success = false;
        }  finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {

            }
        }
        return success;
    }
}