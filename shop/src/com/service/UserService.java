package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UsersDao;
import com.entity.Users;
import com.util.SafeUtil;

/**
 * User service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class UserService {

	@Autowired		//Spring injection class object
	private UsersDao userDao;
	
	/**
	 * Verify user password
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password){
		return userDao.getByUsernameAndPassword(username, SafeUtil.encode(password)) != null;
	}

	/**
	 * Check if the user exist
	 * @param username
	 * @return
	 */
	public boolean isExist(String username) {
		return userDao.getByUsername(username) != null;
	}

	/**
	 * Add user
	 * @param user
	 * @return
	 */
	public boolean add(Users user) {
		user.setPassword(SafeUtil.encode(user.getPassword()));
		return userDao.insert(user) > 0;
	}
	
	/**
	 * Find user by userid
	 * @param userid
	 * @return
	 */
	public Users get(int userid){
		return userDao.selectById(userid);
	}
	
	/**
	 * Find user by username
	 * @param username
	 * @return
	 */
	public Users get(String username){
		return userDao.getByUsername(username);
	}
	
	/**
	 * User list
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Users> getList(int page, int rows) {
		return userDao.getList(rows * (page-1), rows);
	}

	/**
	 * Total number of user
	 * @return
	 */
	public long getTotal() {
		return userDao.getTotal();
	}

	/**
	 * Update user
	 * @param user
	 */
	public boolean update(Users user) {
		return userDao.updateById(user) > 0;
	}

	/**
	 * Delete user
	 * @param id
	 */
	public boolean delete(Users user) {
		return userDao.deleteById(user.getId()) > 0;
	}
	
}
