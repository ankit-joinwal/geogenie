package com.geogenie.user.service.business;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geogenie.data.model.User;
import com.geogenie.user.service.dao.UserDAO;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDAO userDAO;
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User registerUser(User user) {
		logger.debug("### Inside registerUser of UserServiceImpl ###");
		
		return this.userDAO.registerUser(user);
	}
	
	@Override
	public User getUser(long id) {
		return this.userDAO.getUserById(id);
	}
	
	@Override
	public List<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDAO.getUserByEmailId(username);
        logger.info("### 123 ");
        if (user == null) {
            String message = "User not found" + username;
            logger.info(message);
            throw new UsernameNotFoundException(message);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        logger.info("Found user in database: " + user);

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}

	
}
