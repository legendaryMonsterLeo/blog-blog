package com.liangyue.spring.boot.blog.service;

import com.liangyue.spring.boot.blog.domain.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.liangyue.spring.boot.blog.domain.Blog;
import com.liangyue.spring.boot.blog.domain.User;

/**
 * Blog 服务接口.
 *
 */
public interface BlogService {
	/**
	 * 保存Blog
	 * @param Blog
	 * @return
	 */
	Blog saveBlog(Blog blog);
	
	/**
	 * 删除Blog
	 * @param id
	 * @return
	 */
	void removeBlog(Long id);
	
	/**
	 * 更新Blog
	 * @param Blog
	 * @return
	 */
	Blog updateBlog(Blog blog);
	
	/**
	 * 根据id获取Blog
	 * @param id
	 * @return
	 */
	Blog getBlogById(Long id);
	
	/**
	 * 根据用户名进行分页模糊查询（最新）
	 * @param user
	 * @return
	 */
	Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable);
 
	/**
	 * 根据用户名进行分页模糊查询（最热）
	 * @param user
	 * @return
	 */
	Page<Blog> listBlogsByTitleLikeAndSort(User suser, String title, Pageable pageable);
	
	/**
	 * 阅读量递增
	 * @param id
	 */
	void readingIncrease(Long id);

	Blog createComment(Long blogId,String commentContent);

	void removeComment(Long blogId,Long commentId);

	Blog createVote(Long blogId);

	void removeVote(Long blogId,Long voteId);

	Page<Blog> listBlogsByCatalog(Catalog catalog,Pageable pageable);
}
