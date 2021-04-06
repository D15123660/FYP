package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.entity.SkuGood;
import com.entity.Tops;

/**
 * Product service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted

public class GoodService {

	@Autowired	
	private GoodsDao goodDao;
	@Autowired
	private TopService topService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private SkuService skuService;
	
	
	/**
	 * Get product list
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Goods> getList(int status, int page, int size){
		if (status == 0) {
			return packGood(goodDao.getList(size * (page-1), size));
		}
		List<Tops> topList = topService.getList((byte)status, page, size);
		if(topList!=null && !topList.isEmpty()) {
			List<Goods> goodList = new ArrayList<>();
			for(Tops top : topList) {
				goodList.add(packGood(goodDao.selectById(top.getGoodId())));
			}
			return goodList;
		}
		return null;
	}

	/**
	 * Get product total number
	 * @return
	 */
	public long getTotal(int status){
		if (status == 0) {
			return goodDao.getTotal();
		}
		return topService.getTotal((byte)status);
	}
	
	/**
	 * Get product list by name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Goods> getListByName(String name, int page, int size){
		return packGood(goodDao.getListByName(name, size * (page-1), size));
	}
	
	/**
	 * Get the total number of products by name
	 * @return
	 */
	public long getTotalByName(String name){
		return goodDao.getTotalByName(name);
	}

	/**
	 * Search by category
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Goods> getListByType(int typeid, int page, int size) {
		return typeid > 0 ? packGood(goodDao.getListByType(typeid, size * (page-1), size)) : goodDao.getList(size * (page-1), size);
	}
	
	/**
	 * Get quantity
	 * @param typeid
	 * @return
	 */
	public long getTotalByType(int typeid){
		return typeid > 0 ? goodDao.getTotalByType(typeid) : goodDao.getTotal();
	}
	
	/**
	 * Get by id
	 * @param productid
	 * @return
	 */
	public Goods get(int id) {
		return packGood(goodDao.selectById(id));
	}
	
	/**
	 * Add goods
	 * @param product
	 */
	public boolean add(Goods good) {
		return goodDao.insert(good) > 0 && this.addSkuGood(good.getId(), good.getSkuList());
	}

	/**
	 * Update goods
	 * @param product
	 * @return 
	 */
	public boolean update(Goods good) {
		return goodDao.updateById(good) > 0 && this.addSkuGood(good.getId(), good.getSkuList());
	}
	
	/**
	 * Add product sku
	 * @param goodid
	 * @param skuList
	 */
	private boolean addSkuGood(int goodid, List<SkuGood> skuList) {
		skuService.deleteGood(goodid);
		if(Objects.nonNull(skuList) && !skuList.isEmpty()) {
			for(SkuGood skuGood : skuList) {
				if(Objects.nonNull(skuGood.getColorId()) && Objects.nonNull(skuGood.getSizeId())) {
					skuGood.setGoodId(goodid);
					skuService.addGood(skuGood);
				}
			}
		}
		return true;
	}
	
	/**
	 * Delete product
	 * Delete the recommended information of this product first
	 * @param product
	 */
	public boolean delete(int goodid) {
		topService.deleteByGoodid(goodid);
		return goodDao.deleteById(goodid) > 0;
	}
	

	/**
	 * Packaged product
	 * @param list
	 * @return
	 */
	private List<Goods> packGood(List<Goods> list) {
		for(Goods good : list) {
			good = packGood(good);
		}
		return list;
	}

	/**
	 * Packaged product
	 * @param good
	 * @return
	 */
	private Goods packGood(Goods good) {
		if(good != null) {
			// Category information
			good.setType(typeService.get(good.getTypeId()));
			// Attribute list
			good.setSkuList(skuService.getGoodList(good.getId()));
			// Top products 
			List<Tops> topList = topService.getListByGoodid(good.getId());
			if (Objects.nonNull(topList) && !topList.isEmpty()) {
				good.setShow(true);
			}
		}
		return good;
	}

}