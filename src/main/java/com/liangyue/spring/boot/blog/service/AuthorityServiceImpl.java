/**
 * 
 */
package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangyue.spring.boot.blog.domain.Authority;

/**
 * Authority 服务.
 *
 */
@Service
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
