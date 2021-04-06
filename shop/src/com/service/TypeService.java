package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TypesDao;
import com.entity.Types;

/**
 * Type of service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class TypeService {

	@Autowired	
	private TypesDao typeDao;
	
	
	/**
	 * Get type list
	 * @return
	 */
	public List<Types> getList(){
		return typeDao.getList();
	}

	/**
	 * Search by id 
	 * @param id
	 * @return
	 */
	public Types get(int id) {
		return typeDao.selectById(id);
	}
	
	/**
	 * Add type
	 * @param type
	 * @return
	 */
	public Integer add(Types type) {
		return typeDao.insert(type);
	}

	/**
	 * Update type
	 * @param type
	 */
	public boolean update(Types type) {
		return typeDao.updateById(type) > 0;
	}

	/**
	 * Delete type
	 * @param type
	 */
	public boolean delete(Types type) {
		return typeDao.deleteById(type.getId()) > 0;
	}
	
}
