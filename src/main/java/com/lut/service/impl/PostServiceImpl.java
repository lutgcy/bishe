package com.lut.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lut.entity.PostEntity;
import com.lut.mapper.PostMapper;
import com.lut.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public PageInfo queryAllPost(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> postList = postMapper.getAllPost();
        PageInfo pageInfo = new PageInfo(postList);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> queryPostIdAndName() {
        return postMapper.getPostIdAndName();
    }

    @Override
    public int insertPost(PostEntity postEntity) {
        return postMapper.insertPost(postEntity);
    }

    @Override
    public int updatePost(PostEntity postEntity) {
        return postMapper.updatePost(postEntity);
    }

    @Override
    public int deletePost(Integer postId) {
        return postMapper.deletePost(postId);
    }
}
