package xyz.stackoverflow.blog.web.controller.api.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.stackoverflow.blog.exception.BusinessException;
import xyz.stackoverflow.blog.util.web.BaseController;
import xyz.stackoverflow.blog.util.web.Response;
import xyz.stackoverflow.blog.util.web.StatusConst;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 图片管理接口Controller
 *
 * @author 凉衫薄
 */
@RestController
@RequestMapping("/api/admin")
public class ImageController extends BaseController {

    /**
     * 图片删除接口 /api/admin/image/delete
     * 方法 POST
     *
     * @param request
     * @param url
     * @return
     */
    @RequestMapping(value = "/image/delete", method = RequestMethod.POST)
    public Response delete(HttpServletRequest request, @RequestParam("url") String url) {
        Response response = new Response();
        String rootDir = request.getServletContext().getRealPath("");
        File file = new File(rootDir, url);
        if (file.exists()) {
            file.delete();
            response.setStatus(StatusConst.SUCCESS);
            response.setMessage("图片删除成功");
        } else {
            throw new BusinessException("图片删除失败");
        }
        return response;
    }
}
