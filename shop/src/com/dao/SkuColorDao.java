package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.entity.SkuColor;

public interface SkuColorDao {
    int deleteById(Integer id);

    int insert(SkuColor record);

    int insertSelective(SkuColor record);

    SkuColor selectById(Integer id);

    int updateByIdSelective(SkuColor record);

    int updateById(SkuColor record);  
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	/**
	 * Get color list by color id
	 * @return
	 */
    @Select("select * from sku_color order by id desc")
	public List<SkuColor> getList();
    
	/**
	 * Get product with color list
	 * @return
	 */
    @Select("select * from sku_color where id in (select color_id from sku_good where good_id=#{goodid})")
	List<SkuColor> getListByGoodid(int goodid);
}