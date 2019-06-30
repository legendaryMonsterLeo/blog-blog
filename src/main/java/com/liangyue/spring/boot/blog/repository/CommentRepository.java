package com.liangyue.spring.boot.blog.repository;

import com.liangyue.spring.boot.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
