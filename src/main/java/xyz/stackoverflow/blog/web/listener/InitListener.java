package xyz.stackoverflow.blog.web.listener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.web.context.ContextLoaderListener;
import xyz.stackoverflow.blog.pojo.entity.Menu;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

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
        initDataBase();
        initContext(event);
        super.contextInitialized(event);
    }

    /**
     * 初始化上下文
     *
     * @param event
     */
    public void initContext(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        try {
            Properties props = Resources.getResourceAsProperties("db.properties");
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String driver = props.getProperty("jdbc.driver");

            String sql1 = "select * from setting";
            String sql2 = "select * from menu order by date";

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(sql1);
            ResultSet rs = ps.executeQuery();

            Map<String, Object> map = new HashMap<>();
            while (rs.next()) {
                map.put(rs.getString("name"), rs.getObject("value"));
            }

            List<Menu> list = new ArrayList<>();
            ps = conn.prepareStatement(sql2);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getString("id"));
                menu.setName(rs.getString("name"));
                menu.setUrl(rs.getString("url"));
                list.add(menu);
            }

            ps.close();
            conn.close();
            application.setAttribute("setting", map);
            application.setAttribute("menu", list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据库
     */
    public void initDataBase() {
        String[] scripts = new String[]{"sql/blog.sql", "sql/setting.sql", "sql/menu.sql", "sql/user.sql", "sql/role.sql", "sql/permission.sql",
                "sql/role_permission.sql", "sql/user_role.sql", "sql/category.sql", "sql/article.sql", "sql/comment.sql", "sql/visit.sql",
                "sql/visitor.sql", "sql/init.sql"};
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
            if (rs.next() && rs.getInt("COUNT") == 0) {
                ScriptRunner runner = new ScriptRunner(conn);
                runner.setErrorLogWriter(null);
                runner.setLogWriter(null);
                for (int i = 0; i < scripts.length; i++) {
                    runner.runScript(new InputStreamReader(Resources.getResourceAsStream(scripts[i]),"UTF-8"));
                }
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

    }

}
