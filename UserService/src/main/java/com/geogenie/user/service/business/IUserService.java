package com.geogenie.user.service.business;

import java.util.List;

import com.geogenie.data.model.SmartDevice;
import com.geogenie.data.model.User;

public interface IUserService {

	public User getUser(long id);
	
	public List<User> getAllUsers();
	
	public SmartDevice registerUser(SmartDevice device);
}
