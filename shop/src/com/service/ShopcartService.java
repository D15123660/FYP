package com.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ShopcartDao;
import com.entity.Shopcart;

/**
 * Shopping cart service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class ShopcartService {

	@Autowired
	private ShopcartDao shopcartDao;
	@Autowired
	private GoodService goodService ;
	@Autowired
	private SkuService skuService;
	
	/**
	 * Get the shopping cart list
	 * @param userid
	 * @return
	 */
	public List<Shopcart> getList(int userid){
		return pack(shopcartDao.getList(userid));
	}
	
	/**
	 * Add to cart
	 * @param good
	 * @return
	 */
	public boolean save(Shopcart shopcart) {
		Shopcart old = shopcartDao.getCart(shopcart.getUserId(), shopcart.getGoodId(), shopcart.getColorId(), shopcart.getSizeId());
		if(Objects.isNull(old)) {
			return shopcartDao.insert(shopcart) > 0;
		}
		old.setAmount(old.getAmount() + shopcart.getAmount());
		return shopcartDao.updateById(old) > 0;
	}

	/**
	 * Get the total number of shopping carts
	 * @param userid
	 */
	public int getTotal(int userid) {
		return shopcartDao.getTotal(userid);
	}
	
	/**
	 * Quantity+1
	 * @param id
	 * @return
	 */
	public boolean add(int id) {
		Shopcart cart = shopcartDao.get(id);
		cart.setAmount(cart.getAmount() + 1);
		return shopcartDao.updateById(cart) > 0;
	}
	
	
	/**
	 * Quantity-1
	 * @param id
	 * @return
	 */
	public boolean less(int id) {
		Shopcart cart = shopcartDao.get(id);
		if(cart.getAmount()<=1) {
			return delete(id);
		}
		cart.setAmount(cart.getAmount() - 1);
		return shopcartDao.updateById(cart) > 0;
	}
	
	
	/**
	 * Delete item
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		return shopcartDao.deleteById(id) > 0;
	}
	
	/**
	 * Empty shopping cart
	 * @param userid
	 * @return
	 */
	public boolean clean(Integer userid) {
		return shopcartDao.deleteByUserid(userid);
	}
	

	/**
	 * Encapsulation
	 * @param list
	 * @return
	 */
	private List<Shopcart> pack(List<Shopcart> list) {
		for(Shopcart cart : list) {
			cart.setGood(goodService.get(cart.getGoodId()));
			cart.setColor(skuService.getColor(cart.getColorId()));
			cart.setSize(skuService.getSize(cart.getSizeId()));
		}
		return list;
	}

	/**
	 * Get the total amount of the order list
	 * @param id
	 * @return
	 */
	public int getTotalPrice(int userid) {
		int total = 0;
		List<Shopcart> cartList = this.getList(userid);
		if(Objects.nonNull(cartList) && ! cartList.isEmpty()) {
			for(Shopcart cart : cartList) {
				total += cart.getGood().getPrice() * cart.getAmount();
			}
		}
		return total;
	}
	
}
