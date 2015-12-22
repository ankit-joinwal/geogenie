package com.geogenie.user.service.dao;

import java.util.List;

import com.geogenie.data.model.SmartDevice;
import com.geogenie.data.model.User;

public interface UserDAO {

	public List<User> getAllUsers();
	
	public SmartDevice registerUser(SmartDevice smartDevice);
	
	public User getUserById(Long id);
	
}
