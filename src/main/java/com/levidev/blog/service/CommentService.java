package com.levidev.blog.service;

import com.levidev.blog.po.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listComments(Long blogId);

    Comment saveComment(Comment comment);
}
