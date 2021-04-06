package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.entity.SkuSize;

public interface SkuSizeDao {
    int deleteById(Integer id);

    int insert(SkuSize record);

    int insertSelective(SkuSize record);

    SkuSize selectById(Integer id);

    int updateByIdSelective(SkuSize record);

    int updateById(SkuSize record);
    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	/**
	 * Get size list
	 * @return
	 */
    @Select("select * from sku_size order by id desc")
	public List<SkuSize> getList();

    
	/**
	 * Get product with size
	 * @return
	 */
    @Select("select * from sku_size where id in (select size_id from sku_good where good_id=#{goodid})")
	List<SkuSize> getListByGoodid(int goodid);
}