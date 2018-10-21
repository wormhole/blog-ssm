package xyz.stackoverflow.blog.web.listener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 初始化监听器
 *
 * @author 凉衫薄
 */
public class InitListener extends ContextLoaderListener {

    /**
     * 初始化数据库及表
     *
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            Properties props = Resources.getResourceAsProperties("db.properties");
            String server = props.getProperty("jdbc.server");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String driver = props.getProperty("jdbc.driver");
            String isExistSQL = "SELECT count(SCHEMA_NAME) as COUNT FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME='blog'";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(server, username, password);
            PreparedStatement ps = conn.prepareStatement(isExistSQL);
            ResultSet rs = ps.executeQuery();
            if(rs.next() && rs.getInt("COUNT") == 0){
                ScriptRunner runner = new ScriptRunner(conn);
                runner.setErrorLogWriter(null);
                runner.setLogWriter(null);
                runner.runScript(Resources.getResourceAsReader("sql/blog.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/user.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/role.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/permission.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/role_permission.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/user_role.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/category.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/article.sql"));
                runner.runScript(Resources.getResourceAsReader("sql/init.sql"));
            }
            ps.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        super.contextInitialized(event);
    }

}
