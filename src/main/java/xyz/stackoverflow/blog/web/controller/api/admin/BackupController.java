package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.stackoverflow.blog.util.db.DBProperties;
import xyz.stackoverflow.blog.util.db.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 数据库备份接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class BackupController {

    @Autowired
    private DBProperties properties;

    /**
     * 导出sql备份文件 /api/admin/backup/sql
     * 方法 GET
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/backup/sql", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportSql(HttpServletRequest request) throws IOException {
        String filename = "blog.sql";
        String backupPath = request.getServletContext().getRealPath("backup");
        DBUtil.backup(properties.getHost(), properties.getUsername(), properties.getPassword(), backupPath, filename, properties.getDb());

        InputStream is = new FileInputStream(new File(backupPath, filename));
        byte[] body = new byte[is.available()];
        is.read(body);

        filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + filename);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, status);
        return entity;
    }
}
