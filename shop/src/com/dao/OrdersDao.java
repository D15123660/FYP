package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Orders;

public interface OrdersDao {
    int deleteById(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectById(Integer id);

    int updateByIdSelective(Orders record);

    int updateById(Orders record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	/**
	 * Get order list
	 * @param status
	 * @param page
	 * @param row
	 */
    @Select("select * from orders order by id desc limit #{begin}, #{size}")
	public List<Orders> getList(@Param("begin")int begin, @Param("size")int size);

	/**
	 * Get total order number
	 * @param status
	 * @return
	 */
    @Select("select count(*) from orders")
	public int getTotal();
    
    /**
     * Get order list by status
     * @param status
     * @param page
     * @param row
     */
    @Select("select * from orders where status=#{status} order by id desc limit #{begin}, #{size}")
    public List<Orders> getListByStatus(@Param("status")byte status, @Param("begin")int begin, @Param("size")int size);
    
    /**
     * Get total number
     * @param status
     * @return
     */
    @Select("select count(*) from orders where status=#{status}")
    public int getTotalByStatus(@Param("status")byte status);

	/**
	 * Get list by user
	 * @param userid
	 */
    @Select("select * from orders where user_id=#{userid} order by id desc")
	public List<Orders> getListByUserid(@Param("userid")int userid);

}