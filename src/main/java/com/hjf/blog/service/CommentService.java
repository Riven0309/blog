package com.hjf.blog.service;

import java.util.List;

import com.hjf.blog.entity.Comment;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
