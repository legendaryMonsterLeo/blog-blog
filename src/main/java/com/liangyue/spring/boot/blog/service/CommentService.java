package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.domain.Comment;

public interface CommentService {
    Comment getCommentById(Long id);

    void removeComment(Long id);
}
