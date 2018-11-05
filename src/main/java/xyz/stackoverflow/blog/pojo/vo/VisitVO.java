package xyz.stackoverflow.blog.pojo.vo;

import xyz.stackoverflow.blog.pojo.entity.Visit;

import java.util.Date;

/**
 * 访问量VO类
 *
 * @author 凉衫薄
 */
public class VisitVO extends Visit {

    protected String dateString;

    public VisitVO() {

    }

    public VisitVO(String id, String url, Integer status, String ip, String agent, Date date, String dateString) {
        super(id, url, status, ip, agent, date);
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * VO类转实体类
     *
     * @return
     */
    public Visit toVisit() {
        Visit visit = new Visit();
        visit.setId(id);
        visit.setUrl(url);
        visit.setId(ip);
        visit.setAgent(agent);
        visit.setStatus(status);
        visit.setDate(date);
        return visit;
    }
}
