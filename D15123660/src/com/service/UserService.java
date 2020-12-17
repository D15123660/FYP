package com.service;

import java.util.List;

import com.dao.UsersDao;
import com.entity.Users;


public class UserService {
	
	private UsersDao userDao;
	




	public boolean add(Users user) {
		user.setPassword(user.getPassword());
		return userDao.insert(user) > 0;
	}
	

	public Users get(int userid){
		return userDao.selectById(userid);
	}
	


	public boolean update(Users user) {
		return userDao.updateById(user) > 0;
	}


	public boolean delete(Users user) {
		return userDao.deleteById(user.getId()) > 0;
	}
	
}
