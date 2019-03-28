package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.stackoverflow.blog.common.Page;
import xyz.stackoverflow.blog.common.Response;
import xyz.stackoverflow.blog.pojo.dto.VisitDTO;
import xyz.stackoverflow.blog.pojo.po.VisitPO;
import xyz.stackoverflow.blog.service.VisitService;
import xyz.stackoverflow.blog.service.VisitorService;
import xyz.stackoverflow.blog.util.DateUtil;

import java.util.*;

/**
 * 浏览量数据获取接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class VisitDataController {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitorService visitorService;

    /**
     * 获取三十天内的流量记录接口
     * /api/admin/visit/chart
     * 方法 GET
     *
     * @return
     */
    @RequestMapping(value = "/visit/chart", method = RequestMethod.GET)
    public Response flow() {
        Response response = new Response();
        List<String> dateList = new ArrayList<>();
        List<Integer> visitList = new ArrayList<>();
        List<Integer> visitorList = new ArrayList<>();
        Map<String, List> map = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -29);

        for (int i = 0; i < 30; i++) {
            Date startDate = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            Date endDate = calendar.getTime();
            int visitCount = visitService.selectByDate(startDate, endDate).size();
            int visitorCount = visitorService.selectByDate(startDate, endDate).size();
            dateList.add(DateUtil.formatDate(startDate));
            visitList.add(visitCount);
            visitorList.add(visitorCount);
        }

        map.put("dateList", dateList);
        map.put("visitList", visitList);
        map.put("visitorList", visitorList);
        response.setStatus(Response.SUCCESS);
        response.setMessage("流量记录获取成功");
        response.setData(map);
        return response;
    }

    /**
     * 获取访问记录接口
     * /api/admin/visit/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/visit/list", method = RequestMethod.GET)
    @ResponseBody
    public Response today(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        Page page1 = new Page(Integer.valueOf(page), Integer.valueOf(limit), null);

        List<VisitPO> list = visitService.selectByPage(page1);
        int count = visitService.selectByCondition(new HashMap<>()).size();

        List<VisitDTO> voList = new ArrayList<>();

        for (VisitPO visit : list) {
            VisitDTO vo = new VisitDTO();
            vo.setIp(visit.getIp());
            vo.setUrl(visit.getUrl());
            vo.setStatus(visit.getStatus());
            vo.setAgent(visit.getAgent());
            vo.setReferer(visit.getReferer());
            vo.setDate(visit.getDate());
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(Response.SUCCESS);
        response.setMessage("查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 获取访问量和访客量数据接口
     * /api/admin/visit/count
     * 方法 GET
     *
     * @return
     */
    @RequestMapping(value = "/visit/count", method = RequestMethod.GET)
    @ResponseBody
    public Response count() {
        Response response = new Response();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date endDate = calendar.getTime();

        int todayVisit = visitService.selectByDate(startDate, endDate).size();
        int todayVisitor = visitorService.selectByDate(startDate, endDate).size();
        int totalVisit = visitService.selectByCondition(new HashMap<>()).size();
        int totalVisitor = visitorService.selectByCondition(new HashMap<>()).size();

        Map<String, Integer> map = new HashMap<>();
        map.put("todayVisit", todayVisit);
        map.put("todayVisitor", todayVisitor);
        map.put("totalVisit", totalVisit);
        map.put("totalVisitor", totalVisitor);

        response.setStatus(Response.SUCCESS);
        response.setMessage("获取成功");
        response.setData(map);

        return response;
    }
}
