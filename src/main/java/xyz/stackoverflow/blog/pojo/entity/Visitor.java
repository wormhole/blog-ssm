package xyz.stackoverflow.blog.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 访客实体类
 *
 * @author 凉衫薄
 */
public class Visitor implements Serializable {

    private String id;
    private String ip;
    private String agent;
    private Date date;

    public Visitor(){

    }

    public Visitor(String id, String ip, String agent, Date date) {
        this.id = id;
        this.ip = ip;
        this.agent = agent;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
