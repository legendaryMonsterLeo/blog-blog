package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.domain.Vote;
import com.liangyue.spring.boot.blog.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }
    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

}