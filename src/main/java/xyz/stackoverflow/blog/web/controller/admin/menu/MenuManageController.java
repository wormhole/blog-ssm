package xyz.stackoverflow.blog.web.controller.admin.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.util.PageParameter;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.pojo.vo.MenuVO;
import xyz.stackoverflow.blog.util.Response;
import xyz.stackoverflow.blog.service.MenuService;
import xyz.stackoverflow.blog.validator.MenuValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 菜单管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuManageController {

    private final Integer SUCCESS = 0;
    private final Integer FAILURE = 1;

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuValidator menuValidator;

    /**
     * 获取菜单列表 /admin/menu/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Response list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "limit", required = false) String limit) {
        Response response = new Response();
        List<Menu> list = null;
        List<MenuVO> voList = new ArrayList<>();

        if (page != null && limit != null) {
            PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
            list = menuService.getLimitMenu(pageParameter);
        } else {
            list = menuService.getAllMenu();
        }
        int count = menuService.getMenuCount();

        for (Menu menu : list) {
            MenuVO vo = new MenuVO();
            vo.setId(menu.getId());
            vo.setName(HtmlUtils.htmlEscape(menu.getName()));
            vo.setUrl(menu.getUrl());
            vo.setDeleteAble(menu.getDeleteAble());
            vo.setDate(menu.getDate());
            if (vo.getDeleteAble() == 0) {
                vo.setDeleteTag("否");
            } else {
                vo.setDeleteTag("是");
            }
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
     * 删除菜单 /admin/menu/delete
     * 方法 GET
     *
     * @param menuVO
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody MenuVO menuVO, HttpServletRequest request) {
        Response response = new Response();
        Menu menu = menuService.getMenuById(menuVO.getId());
        if (menu.getDeleteAble() == 0) {
            response.setStatus(FAILURE);
            response.setMessage("该菜单不允许删除");
        } else {
            menuService.deleteMenu(menu);

            ServletContext application = request.getServletContext();
            List<Menu> list = menuService.getAllMenu();
            application.setAttribute("menu", list);

            response.setStatus(SUCCESS);
            response.setMessage("删除成功");
        }
        return response;
    }

    /**
     * 新增菜单 /admin/menu/insert
     * 方法 POST
     *
     * @param menuVO
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Response insert(@RequestBody MenuVO menuVO, HttpServletRequest request) {
        Response response = new Response();

        Map<String, String> map = menuValidator.validate(menuVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Menu menu = menuVO.toMenu();
            menu.setDeleteAble(1);
            menu.setDate(new Date());
            menuService.insertMenu(menu);

            ServletContext application = request.getServletContext();
            List<Menu> list = menuService.getAllMenu();
            application.setAttribute("menu", list);

            response.setStatus(SUCCESS);
            response.setMessage("新增成功");
        }
        return response;
    }

    /**
     * 更新菜单 /admin/menu/update
     * 方法 POST
     *
     * @param menuVO
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody MenuVO menuVO, HttpServletRequest request) {
        Response response = new Response();
        Map<String, String> map = menuValidator.validate(menuVO);
        if (map.size() != 0) {
            response.setStatus(FAILURE);
            response.setMessage("字段格式有误");
            response.setData(map);
        } else {
            Menu menu = menuVO.toMenu();
            Menu updateMenu = menuService.updateMenu(menu);
            if (updateMenu != null) {

                ServletContext application = request.getServletContext();
                List<Menu> list = menuService.getAllMenu();
                application.setAttribute("menu", list);

                response.setStatus(SUCCESS);
                response.setMessage("更新成功");
            } else {
                response.setStatus(FAILURE);
                response.setMessage("该菜单不允许修改");
            }
        }
        return response;
    }
}
