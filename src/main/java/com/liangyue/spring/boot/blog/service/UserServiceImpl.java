package com.liangyue.spring.boot.blog.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.liangyue.spring.boot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.liangyue.spring.boot.blog.domain.User;

/**
 * User 服务.
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void removeUser(Long id) {
		userRepository.delete(id);
	}

	@Transactional
	@Override
	public void removeUsersInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}
	
	@Transactional
	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> listUsersByNameLike(String name, Pageable pageable) {
		// 模糊查询
		name = "%" + name + "%";
		Page<User> users = userRepository.findByNameLike(name, pageable);
		return users;
	}

	public List<User> listUsersByUsernames(Collection<String> usernames) {
		return userRepository.findByUsernameIn(usernames);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

}
