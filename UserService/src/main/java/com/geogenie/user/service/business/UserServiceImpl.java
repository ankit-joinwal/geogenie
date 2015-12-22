package com.geogenie.user.service.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geogenie.data.model.SmartDevice;
import com.geogenie.data.model.User;
import com.geogenie.user.service.dao.UserDAO;
import com.geogenie.user.service.util.PKeyGenerator;

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
	public SmartDevice registerUser(SmartDevice device) {
		logger.debug("### Inside registerUser of UserServiceImpl ###");
		logger.info("### Generating Private key for new device ###");
		String pKey = PKeyGenerator.generateKey();
		device.setPrivateKey(pKey);
		logger.debug("### Private key generated : {} ###",pKey);
		logger.info("### Proceeding to persist new device ###");
		return this.userDAO.registerUser(device);
	}
	
	@Override
	public User getUser(long id) {
		return this.userDAO.getUserById(id);
	}
	
	@Override
	public List<User> getAllUsers() {
		return this.userDAO.getAllUsers();
	}
}
