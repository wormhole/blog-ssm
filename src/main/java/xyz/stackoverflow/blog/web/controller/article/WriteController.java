package xyz.stackoverflow.blog.web.controller.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.stackoverflow.blog.pojo.ResponseMessage;
import xyz.stackoverflow.blog.pojo.entity.Blog;
import xyz.stackoverflow.blog.pojo.entity.User;
import xyz.stackoverflow.blog.pojo.vo.BlogVO;
import xyz.stackoverflow.blog.service.BlogService;
import xyz.stackoverflow.blog.util.ResponseStatusEnum;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/article")
public class WriteController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseMessage save(@RequestBody BlogVO blogVO,HttpSession session){
        ResponseMessage response = new ResponseMessage();
        Blog blog = blogVO.toBlog();
        blog.setUserId(((User)session.getAttribute("user")).getId());
        blogService.saveBlog(blog);
        response.setStatus(ResponseStatusEnum.SUCCESS.getStatus());
        response.setData(ResponseStatusEnum.SUCCESS.getMessage());
        return response;
    }

}
