package com.levidev.blog.service;

import com.levidev.blog.po.Blog;
import com.levidev.blog.po.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogService {
    Blog getBlog(Long id);

    Blog getConvertedBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(String query, Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Map<String, List<Blog>> archiveBlogs();

    Long blogCount();

    Blog saveBlog(Blog blog);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listRecommendTop(Integer size);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
