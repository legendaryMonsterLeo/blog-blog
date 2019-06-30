package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.repository.CommentRepository;
import com.liangyue.spring.boot.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public void removeComment(Long id) {
        commentRepository.delete(id);
    }
}
