package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.entity.Items;

public interface ItemsDao {
    int deleteById(Integer id);

    int insert(Items record);

    int insertSelective(Items record);

    Items selectById(Integer id);

    int updateByIdSelective(Items record);

    int updateById(Items record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	
	/**
	 * Order list
	 * @param Ordersid
	 * @param page
	 * @param rows
	 * @return
	 */
    @Select("select * from items where order_id=#{orderid}")
	public List<Items> getItemList(int orderid);
}