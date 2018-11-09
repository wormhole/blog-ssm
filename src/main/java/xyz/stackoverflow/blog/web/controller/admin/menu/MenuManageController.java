package xyz.stackoverflow.blog.web.controller.admin.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.pojo.vo.MenuVO;
import xyz.stackoverflow.blog.service.MenuService;
import xyz.stackoverflow.blog.util.*;
import xyz.stackoverflow.blog.validator.MenuValidator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 菜单管理控制器
 *
 * @author 凉衫薄
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuManageController extends BaseController {

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
     * @param dto
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody BaseDTO dto, HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("未找到请求数据");
        }
        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);
        Menu menu = menuService.getMenuById(menuVO.getId());
        if (menu.getDeleteAble() == 0) {
            throw new BusinessException("该菜单不允许删除");
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
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Response insert(@RequestBody BaseDTO dto, HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("未找到请求数据");
        }
        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);

        Map<String, String> map = menuValidator.validate(menuVO);
        if (map.size() != 0) {
            throw new BusinessException("字段格式有误", map);
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
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestBody BaseDTO dto, HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Response response = new Response();

        Map<String, Class<? extends AbstractVO>> classMap = new HashMap<String, Class<? extends AbstractVO>>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<AbstractVO>> voMap = dto2vo(classMap, dto);
        if (voMap == null || voMap.size() == 0) {
            throw new BusinessException("未找到请求数据");
        }
        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);
        Map<String, String> map = menuValidator.validate(menuVO);
        if (map.size() != 0) {
            throw new BusinessException("字段格式错误", map);
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
                throw new BusinessException("该菜单不允许修改");
            }
        }
        return response;
    }
}
