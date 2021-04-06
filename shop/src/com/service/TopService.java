package com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TopsDao;
import com.entity.Tops;

/**
 * Product Recommendation Service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class TopService {

	@Autowired	
	private TopsDao topDao;
	@Autowired
	private GoodService goodService;
	
	
	/**
	 * Get recommendation list
	 * @return
	 */
	public List<Tops> getList(byte type, int page, int size){
		List<Tops> topList = topDao.getList(type, (page-1)*size, size);
		for(Tops top : topList) {
			top.setGood(goodService.get(top.getGoodId()));
		}
		return topList;
	}
	
	/**
	 * Get total number of recommendation list
	 * @param type
	 * @return
	 */
	public long getTotal(byte type) {
		return topDao.getTotal(type);
	}
	
	/**
	 * Get recommendation list by goods id
	 * @return
	 */
	public List<Tops> getListByGoodid(int goodid){
		return topDao.getListByGoodid(goodid);
	}

	/**
	 * Search by id
	 * @param id
	 * @return
	 */
	public Tops get(int id) {
		return topDao.selectById(id);
	}
	
	/**
	 * Add tops
	 * @param top
	 * @return
	 */
	public Integer add(Tops top) {
		return topDao.insert(top);
	}

	/**
	 * Update tops
	 * @param top
	 */
	public boolean update(Tops top) {
		return topDao.updateById(top) > 0;
	}

	/**
	 * Delete tops
	 * @param top
	 */
	public boolean delete(Tops top) {
		return (Objects.nonNull(top.getId())) ? (topDao.deleteById(top.getId()) > 0) : 
			topDao.deleteByGoodidAndType(top.getGoodId(), top.getType());
	}
	
	/**
	 * Delete by product
	 * @param goodid
	 * @return
	 */
	public boolean deleteByGoodid(int goodid) {
		return topDao.deleteByGoodid(goodid);
	}
	
}
