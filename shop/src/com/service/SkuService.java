package com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SkuColorDao;
import com.dao.SkuGoodDao;
import com.dao.SkuSizeDao;
import com.entity.SkuColor;
import com.entity.SkuGood;
import com.entity.SkuSize;

/**
 * SKU service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class SkuService {

	@Autowired	
	private SkuColorDao skuColorDao;
	@Autowired	
	private SkuSizeDao skuSizeDao;
	@Autowired	
	private SkuGoodDao skuGoodDao;
	
	
	/**
	 * Get a list of colors
	 * @return
	 */
	public List<SkuColor> getColorList(){
		return skuColorDao.getList();
	}
	
	/**
	 * Get a list of colors by good id
	 * @return
	 */
	public List<SkuColor> getColorList(int goodid){
		return skuColorDao.getListByGoodid(goodid);
	}
	
	/**
	 * Get size list
	 * @return
	 */
	public List<SkuSize> getSizeList(){
		return skuSizeDao.getList();
	}
	
	/**
	 * Get size list by good id
	 * @return
	 */
	public List<SkuSize> getSizeList(int goodid){
		return skuSizeDao.getListByGoodid(goodid);
	}
	
	/**
	 * Get product list
	 * @return
	 */
	public List<SkuGood> getGoodList(int goodid){
		List<SkuGood> skuList = skuGoodDao.getList(goodid);
		if(Objects.nonNull(skuList) && !skuList.isEmpty()) {
			for(SkuGood sku : skuList) {
				sku.setColor(skuColorDao.selectById(sku.getColorId()));
				sku.setSize(skuSizeDao.selectById(sku.getSizeId()));
			}
		}
		return skuList;
	}

	/**
	 * Get color
	 * @param id
	 * @return
	 */
	public SkuColor getColor(int id) {
		return skuColorDao.selectById(id);
	}
	
	/**
	 * Get size
	 * @param id
	 * @return
	 */
	public SkuSize getSize(int id) {
		return skuSizeDao.selectById(id);
	}
	
	
	/**
	 * Add color
	 * @param type
	 * @return
	 */
	public Integer addColor(String name) {
		SkuColor color = new SkuColor();
		color.setName(name);
		return skuColorDao.insert(color);
	}
	
	/**
	 * Add size
	 * @param type
	 * @return
	 */
	public Integer addSize(String name) {
		SkuSize size = new SkuSize();
		size.setName(name);
		return skuSizeDao.insert(size);
	}
	
	/**
	 * Add goods
	 * @param type
	 * @return
	 */
	public Integer addGood(SkuGood good) {
		return skuGoodDao.insert(good);
	}

	/**
	 * Delete color
	 * @param type
	 */
	public boolean deleteColor(int id) {
		return skuColorDao.deleteById(id) > 0;
	}
	
	/**
	 * Delete size
	 * @param type
	 */
	public boolean deleteSize(int id) {
		return skuSizeDao.deleteById(id) > 0;
	}
	
	/**
	 * Delete goods
	 * @param type
	 */
	public boolean deleteGood(int goodid) {
		return skuGoodDao.deleteByGoodid(goodid) > 0;
	}

	/**
	 * Get inventory
	 * @param goodid
	 * @param colorid
	 * @param sizeid
	 * @return
	 */
	public int getStock(int goodid, int colorid, int sizeid) {
		SkuGood skuGood = skuGoodDao.getStock(goodid, colorid, sizeid);
		return Objects.isNull(skuGood) || Objects.isNull(skuGood.getStock()) ? 0 : skuGood.getStock();
	}
	
	/**
	 * Reduce inventory
	 * @param goodid
	 * @param colorid
	 * @param sizeid
	 * @param stock
	 * @return
	 */
	public boolean lessStock(int goodid, int colorid, int sizeid, int stock) {
		return skuGoodDao.updateStock(goodid, colorid, sizeid, stock);
	}
	
}
