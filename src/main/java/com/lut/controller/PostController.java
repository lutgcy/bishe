package com.lut.controller;

import com.github.pagehelper.PageInfo;
import com.lut.entity.PostEntity;
import com.lut.service.impl.DepartmentServiceImpl;
import com.lut.service.impl.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private DepartmentServiceImpl departmentService;
    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/api/posts")
    public PageInfo listPost(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return postService.queryAllPost(pageNum, pageSize);
    }

    @GetMapping("/api/post/options")  // select表单选项
    public List<Map<String, Object>> getAllPostIdAndName() {
        return postService.queryPostIdAndName();
    }

    @PostMapping("/api/post")
    public int insertPost(@RequestBody PostEntity postEntity, HttpServletResponse response) {
        int count = 0;
        try {
            count = postService.insertPost(postEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return count;
    }

    @PostMapping("/api/post/update")
    public int updatePost(@RequestBody PostEntity postEntity) {
        return postService.updatePost(postEntity);
    }

    @PostMapping("/api/post/delete")
    public int deletePost(@RequestBody Map<String, String> map, HttpServletResponse response) {
        try {
            map.values().forEach(value -> {
                postService.deletePost(Integer.parseInt(value));
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
        }
        return map.values().size();
    }
}
