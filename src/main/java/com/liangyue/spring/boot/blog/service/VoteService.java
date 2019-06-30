package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.domain.Vote;

public interface VoteService {
    Vote getVoteById(Long id);
    void removeVote(Long id);
}
