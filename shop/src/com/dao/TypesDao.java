package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.entity.Types;

public interface TypesDao {
    int deleteById(Integer id);

    int insert(Types record);

    int insertSelective(Types record);

    Types selectById(Integer id);

    int updateByIdSelective(Types record);

    int updateById(Types record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	/**
	 * Get type list
	 * @return
	 */
    @Select("select * from types order by num")
	public List<Types> getList();
    
}