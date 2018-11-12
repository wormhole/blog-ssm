package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.pojo.entity.Menu;
import xyz.stackoverflow.blog.pojo.vo.MenuVO;
import xyz.stackoverflow.blog.service.MenuService;
import xyz.stackoverflow.blog.util.MapUtil;
import xyz.stackoverflow.blog.util.ValidationUtil;
import xyz.stackoverflow.blog.util.db.PageParameter;
import xyz.stackoverflow.blog.util.web.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 菜单管理接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ValidatorFactory validatorFactory;

    /**
     * 获取菜单列表 /api/admin/menu/list
     * 方法 GET
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    public Response list(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) {
        Response response = new Response();

        PageParameter pageParameter = new PageParameter(Integer.valueOf(page), Integer.valueOf(limit), null);
        List<Menu> list = menuService.getLimitMenu(pageParameter);
        int count = menuService.getMenuCount();

        List<MenuVO> voList = new ArrayList<>();
        for (Menu menu : list) {
            MenuVO vo = new MenuVO();
            vo.setId(menu.getId());
            vo.setName(HtmlUtils.htmlEscape(menu.getName()));
            vo.setUrl(menu.getUrl());
            if (menu.getDeleteAble() == 0) {
                vo.setDeleteTag("否");
            } else {
                vo.setDeleteTag("是");
            }
            voList.add(vo);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("items", voList);
        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("菜单查询成功");
        response.setData(map);
        return response;
    }

    /**
     * 删除菜单 /api/admin/menu/delete
     * 方法 POST
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody CommonDTO dto, HttpServletRequest request) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("未找到请求数据");
        }

        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<MenuVO>> violations = validator.validate(menuVO, MenuVO.DeleteGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式出错", map);
        }

        Menu menu = menuService.deleteMenuById(menuVO.getId());

        if (menu == null) {
            throw new BusinessException("未找到该菜单或该菜单不允许删除");
        }

        ServletContext application = request.getServletContext();
        List<Menu> list = menuService.getAllMenu();
        application.setAttribute("menu", list);

        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("删除成功");

        return response;
    }

    /**
     * 新增菜单 /api/admin/menu/insert
     * 方法 POST
     *
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu/insert", method = RequestMethod.POST)
    public Response insert(@RequestBody CommonDTO dto, HttpServletRequest request) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("未找到请求数据");
        }

        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<MenuVO>> violations = validator.validate(menuVO, MenuVO.InsertGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式出错", map);
        }

        Menu menu = menuVO.toMenu();
        menu.setDeleteAble(1);
        menu.setDate(new Date());
        menuService.insertMenu(menu);

        ServletContext application = request.getServletContext();
        List<Menu> list = menuService.getAllMenu();
        application.setAttribute("menu", list);

        response.setStatus(StatusConst.SUCCESS);
        response.setMessage("菜单新增成功");

        return response;
    }

    /**
     * 更新菜单 /api/admin/menu/update
     * 方法 POST
     *
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu/update", method = RequestMethod.POST)
    public Response update(@RequestBody CommonDTO dto, HttpServletRequest request) {
        Response response = new Response();

        Map<String, Class> classMap = new HashMap<String, Class>() {{
            put("menu", MenuVO.class);
        }};
        Map<String, List<SuperVO>> voMap = dto2vo(classMap, dto);

        if (MapUtil.isEmpty(voMap)) {
            throw new BusinessException("未找到请求数据");
        }

        MenuVO menuVO = (MenuVO) voMap.get("menu").get(0);

        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<MenuVO>> violations = validator.validate(menuVO, MenuVO.UpdateGroup.class);
        Map<String, String> map = ValidationUtil.errorMap(violations);

        if (!MapUtil.isEmpty(map)) {
            throw new BusinessException("字段格式错误", map);
        }

        Menu menu = menuVO.toMenu();
        Menu updateMenu = menuService.updateMenu(menu);
        if (updateMenu != null) {

            ServletContext application = request.getServletContext();
            List<Menu> list = menuService.getAllMenu();
            application.setAttribute("menu", list);

            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("更新成功");
        } else {
            throw new BusinessException("该菜单不允许修改或未找到该菜单");
        }

        return response;
    }
}
