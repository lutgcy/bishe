package com.lut.service;

import com.github.pagehelper.PageInfo;
import com.lut.entity.PostEntity;

import java.util.List;
import java.util.Map;


public interface PostService {

    PageInfo queryAllPost(int pageNum, int pageSize);

    List<Map<String, Object>> queryPostIdAndName();

    int insertPost(PostEntity postEntity);

    int updatePost(PostEntity postEntity);

    int deletePost(Integer postId);

}
