package com.liangyue.spring.boot.blog.repository;

import com.liangyue.spring.boot.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
