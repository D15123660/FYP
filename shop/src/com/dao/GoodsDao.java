package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Goods;

public interface GoodsDao {
    int deleteById(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectById(Integer id);

    int updateByIdSelective(Goods record);

    int updateById(Goods record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
    
	/**
	 * Get list
	 * @param page
	 * @param size
	 * @return
	 */
    @Select("select * from goods order by id desc limit #{begin}, #{size}")
	public List<Goods> getList(@Param("begin")int begin, @Param("size")int size);
	
	/**
	 * Get total number
	 * @return
	 */
    @Select("select count(*) from goods")
	public long getTotal();
	
	/**
	 * Get list by type
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
    @Select("select * from goods where type_id=#{typeid} order by id desc limit #{begin}, #{size}")
	public List<Goods> getListByType(@Param("typeid")int typeid, @Param("begin")int begin, @Param("size")int size);
	
	/**
	 * Get the total number by type
	 * @param typeid
	 * @return
	 */
    @Select("select count(*) from goods where type_id=#{typeid}")
	public long getTotalByType(@Param("typeid")int typeid);
	
	/**
	 * Get list by name
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
    @Select("select * from goods where name like concat('%',#{name},'%') order by id desc limit #{begin}, #{size}")
	public List<Goods> getListByName(@Param("name")String name, @Param("begin")int begin, @Param("size")int size);
	
	/**
	 * Get the total by name
	 * @param name
	 * @return
	 */
    @Select("select count(*) from goods where name like concat('%',#{name},'%')")
	public long getTotalByName(@Param("name")String name);
}