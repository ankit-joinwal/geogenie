package com.geogenie.user.service.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.geogenie.data.model.SmartDevice;
import com.geogenie.data.model.User;
import com.geogenie.user.service.util.UserSVCConstants;
@Repository("userDAO")
public class UserDAOImpl extends AbstractDAO implements UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	private Environment environment;
	
	@Override
	public List<User> getAllUsers() {
		Criteria criteria = getSession().createCriteria(User.class).setFetchMode("smartDevices", FetchMode.JOIN);
		
		return (List<User>) criteria.list();
	}

	@Override
	public SmartDevice registerUser(SmartDevice device) {
		logger.info("### Checking if user exists already ###");
		Query query = getSession().getNamedQuery("getUserByEmail");
		query.setString("emailId", device.getUser().getEmailId());
		User userInDB = (User) query.uniqueResult();
		Date now = new Date();
		if(userInDB!=null){
			logger.info("### User found.Registering only device. ###");
			device.setCreateDt(now);
			device.setUser(userInDB);
			merge(device);
		}else{
			logger.info("### User not found.Do fresh registration. ###");
			device.getUser().setCreateDt(now);
			try{
				device.getUser().setDailyQuota(Integer.parseInt(environment.getRequiredProperty(UserSVCConstants.DEFAULT_USER_DAILY_QUOTA_PROPERTY)));
			}catch(NumberFormatException exception){
				logger.error("Error occured while setting default daily quota",exception);
				
				device.getUser().setDailyQuota(UserSVCConstants.DEFAULT_USER_DAILY_QUOTA);
			}
			device.setCreateDt(now);
			saveOrUpdate(device);
		}
		
		return device;
	}

	@Override
	public User getUserById(Long id) {
		Criteria idCrt = getSession().createCriteria(User.class).add(Restrictions.eq("id", id));
		return (User)idCrt.uniqueResult();
	}

}
