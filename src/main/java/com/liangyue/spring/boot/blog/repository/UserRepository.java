package com.liangyue.spring.boot.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liangyue.spring.boot.blog.domain.User;

import java.util.Collection;
import java.util.List;

/**
 * 用户仓库.
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * 根据用户名分页查询用户列表
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name, Pageable pageable);
	
	User findByUsername(String username);
	List<User> findByUsernameIn(Collection<String> usernames);
}
