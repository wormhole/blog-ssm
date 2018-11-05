package xyz.stackoverflow.blog.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Visit;
import xyz.stackoverflow.blog.pojo.vo.ResponseVO;
import xyz.stackoverflow.blog.pojo.vo.VisitVO;
import xyz.stackoverflow.blog.service.VisitService;
import xyz.stackoverflow.blog.service.VisitorService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 后端欢迎页控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin")
public class WelcomeController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitorService visitorService;

    /**
     * 获取三十天内的流量记录
     *
     * @return
     */
    @RequestMapping(value = "/flow", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO flow() {
        ResponseVO response = new ResponseVO();
        List<String> dateList = new ArrayList<>();
        List<Integer> visitList = new ArrayList<>();
        List<Integer> visitorList = new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -29);

        for (int i = 0; i < 30; i++) {
            Date startDate = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            int visitCount = visitService.getVisitCountByDate(startDate, endDate);
            int visitorCount = visitorService.getVisitorCountByDate(startDate, endDate);
            dateList.add(sdf.format(startDate));
            visitList.add(visitCount);
            visitorList.add(visitorCount);
        }

        map.put("dateList", dateList);
        map.put("visitList", visitList);
        map.put("visitorList", visitorList);
        response.setStatus(SUCCESS);
        response.setMessage("获取成功");
        response.setData(map);
        return response;
    }

    /**
     * 获取今日访问记录
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO today(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        ResponseVO response = new ResponseVO();

        PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date endDate = calendar.getTime();

        List<Visit> list = visitService.getLimitVisitByDate(startDate, endDate, pageParameter);
        int count = visitService.getVisitCountByDate(startDate, endDate);

        List<VisitVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Visit visit : list) {
            VisitVO vo = new VisitVO();
            vo.setIp(visit.getIp());
            vo.setUrl(visit.getUrl());
            vo.setStatus(visit.getStatus());
            vo.setAgent(visit.getAgent());
            vo.setDateString(sdf.format(visit.getDate()));
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 异常访问数据
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public ResponseVO error(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        ResponseVO response = new ResponseVO();

        PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);

        int count = visitService.getErrorVisitCount();
        List<Visit> list = visitService.getLimitErrorVisit(pageParameter);


        List<VisitVO> voList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Visit visit : list) {
            VisitVO vo = new VisitVO();
            vo.setIp(visit.getIp());
            vo.setUrl(visit.getUrl());
            vo.setStatus(visit.getStatus());
            vo.setAgent(visit.getAgent());
            vo.setDateString(sdf.format(visit.getDate()));
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }
}
