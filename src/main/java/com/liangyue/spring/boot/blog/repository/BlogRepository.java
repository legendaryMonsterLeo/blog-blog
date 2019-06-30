package com.liangyue.spring.boot.blog.repository;

import com.liangyue.spring.boot.blog.domain.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.liangyue.spring.boot.blog.domain.Blog;
import com.liangyue.spring.boot.blog.domain.User;

/**
 * Blog 仓库.
 *
 */
public interface BlogRepository extends JpaRepository<Blog, Long>{
	/**
	 * 根据用户名分页查询用户列表
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title, Pageable pageable);
	
	/**
	 * 根据用户名分页查询用户列表
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

	Page<Blog> findByCatalog(Catalog catalog,Pageable pageable);
}
