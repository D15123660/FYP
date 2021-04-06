package com.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ItemsDao;
import com.dao.OrdersDao;
import com.entity.Items;
import com.entity.Orders;
import com.entity.Shopcart;
import com.entity.Users;

/**
 * Product order service
 */
@Service	// Annotated as service layer spring management bean
@Transactional	// Annotate that all methods of this type are added to the spring transaction, 
				// and the specific settings are defaulted
public class OrderService {

	@Autowired
	private OrdersDao orderDao;
	@Autowired
	private ItemsDao itemDao;
	@Autowired
	private GoodService goodService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * Save order
	 * @param order
	 */
	public int save(List<Shopcart> shopcartList, Users user) {
		if(Objects.isNull(shopcartList) || shopcartList.isEmpty()) {
			return -1;
		}
		int total = 0;
		int amount = shopcartList.size();
		for(Shopcart cart : shopcartList) {
			total += cart.getGood().getPrice() * cart.getAmount();
		}
		Orders order = new Orders();
		order.setTotal(total);
		order.setAmount(amount);
		order.setUserId(user.getId());
		order.setStatus(Orders.STATUS_UNPAY);
		order.setSystime(new Date());
		orderDao.insert(order);
		int orderid = order.getId();
		for(Shopcart cart : shopcartList){
			Items item = new Items();
			item.setOrderId(orderid);
			item.setGoodId(cart.getGoodId());
			item.setPrice(cart.getGood().getPrice());
			item.setAmount(cart.getAmount());
			item.setColorId(cart.getColorId());
			item.setSizeId(cart.getSizeId());
			itemDao.insert(item);
			// Reduce inventory
			skuService.lessStock(cart.getGoodId(), cart.getColorId(), cart.getSizeId(), cart.getAmount());
		}
		// Empty shopping cart
		shopcartService.clean(user.getId());
		return orderid;
	}
	
	/**
	 * Order payment
	 * @param order
	 */
	public void pay(Orders order) {
		Orders old = orderDao.selectById(order.getId());
		// Simulation payment completed
		old.setStatus(Orders.STATUS_PAYED);
		old.setPaytype(order.getPaytype());
		old.setName(order.getName());
		old.setPhone(order.getPhone());
		old.setAddress(order.getAddress());
		orderDao.updateById(old);
	}
	
	/**
	 * Get order list
	 * @param page
	 * @param row
	 * @return
	 */
	public List<Orders> getList(byte status, int page, int row) {
		return pack(status>0 ? orderDao.getListByStatus(status, row * (page-1), row) : orderDao.getList(row * (page-1), row));
	}
	
	/**
	 * Get the total order
	 * @return
	 */
	public int getTotal(byte status) {
		return status>0 ? orderDao.getTotalByStatus(status) : orderDao.getTotal();
	}

	/**
	 * Order shipping
	 * @param id
	 * @return 
	 */
	public boolean dispose(int id) {
		Orders order = orderDao.selectById(id);
		order.setStatus(Orders.STATUS_SEND);
		return orderDao.updateByIdSelective(order) > 0;
	}
	
	/**
	 * Order completed
	 * @param id
	 * @return 
	 */
	public boolean finish(int id) {
		Orders order = orderDao.selectById(id);
		order.setStatus(Orders.STATUS_FINISH);
		return orderDao.updateByIdSelective(order) > 0;
	}

	/**
	 * Delete order
	 * @param id
	 */
	public boolean delete(int id) {
		return orderDao.deleteById(id) > 0;
	}
	
	/**
	 * Get all orders of a user
	 * @param userid
	 */
	public List<Orders> getListByUserid(int userid) {
		return pack(orderDao.getListByUserid(userid));
	}

	/**
	 * Get by id
	 * @param orderid
	 * @return
	 */
	public Orders get(int orderid) {
		return pack(orderDao.selectById(orderid));
	}
	
	/**
	 * Get a list of order items
	 * @param orderid
	 * @return
	 */
	public List<Items> getItemList(int orderid){
		List<Items> itemList = itemDao.getItemList(orderid);
		for(Items item : itemList) {
			item.setGood(goodService.get(item.getGoodId()));
			item.setColor(skuService.getColor(item.getColorId()));
			item.setSize(skuService.getSize(item.getSizeId()));
		}
		return itemList;
	}
	
	

	/**
	 * Encapsulation
	 * @param order
	 * @return
	 */
	private Orders pack(Orders order) {
		if(Objects.nonNull(order)) {
			order.setItemList(this.getItemList(order.getId()));
			order.setUser(userService.get(order.getUserId()));
		}
		return order;
	}
	
	/**
	 * Encapsulation
	 * @param order
	 * @return
	 */
	private List<Orders> pack(List<Orders> list) {
		if(Objects.nonNull(list) && !list.isEmpty()) {
			for(Orders order : list) {
				order = pack(order);
			}
		}
		return list;
	}
}
