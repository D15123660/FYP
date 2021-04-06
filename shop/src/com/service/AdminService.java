package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminsDao;
import com.entity.Admins;
import com.util.SafeUtil;

/**
 * Administrator service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class AdminService {

	@Autowired
	private AdminsDao adminDao;
	
	
	/**
	 * Verify user password
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password){
		return adminDao.getByUsernameAndPassword(username, SafeUtil.encode(password)) != null;
	}
	
	/**
	 * Check if the username exist.
	 * @param username
	 * @return
	 */
	public boolean isExist(String username) {
		return adminDao.getByUsername(username) != null;
	}

	/**
	 * Get by username
	 * @param username
	 * @return
	 */
	public Admins getByUsername(String username) {
		return adminDao.getByUsername(username);
	}
	
	/**
	 * Get list
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Admins> getList(int page, int rows) {
		return adminDao.getList(rows * (page-1), rows);
	}

	/**
	 * Get total
	 * @return
	 */
	public long getTotal() {
		return adminDao.getTotal();
	}

	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public Admins get(int id) {
		return adminDao.selectById(id);
	}
	
	/**
	 * Add
	 * @param admin
	 */
	public Integer add(Admins admin) {
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		return adminDao.insert(admin);
	}
	
	/**
	 * Update
	 * @param user
	 */
	public boolean update(Admins admin) {
		return adminDao.updateById(admin) > 0;
	}

	/**
	 * Delete
	 * @param user
	 */
	public boolean delete(Admins admin) {
		return adminDao.deleteById(admin.getId()) > 0;
	}

	
}
