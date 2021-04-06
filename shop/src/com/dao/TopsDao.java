package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Tops;

public interface TopsDao {
    int deleteById(Integer id);

    int insert(Tops record);

    int insertSelective(Tops record);

    Tops selectById(Integer id);

    int updateByIdSelective(Tops record);

    int updateById(Tops record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    

	/**
	 * Get product list
	 * @return
	 */
    @Select("select * from tops where type=#{type} order by id desc limit #{begin}, #{size}")
	public List<Tops> getList(@Param("type")byte type, @Param("begin")int begin, @Param("size")int size);
	/**
	 * Get the total number
	 * @param type
	 * @return
	 */
    @Select("select count(*) from tops where type=#{type}")
	public long getTotal(byte type);
	
	/**
	 * Obtained by product id
	 * @param goodid
	 * @return
	 */
    @Select("select * from tops where good_id=#{goodid}")
	public List<Tops> getListByGoodid(int goodid);
	
	/**
	 * Delete by product id and type
	 * @param goodid
	 * @param type
	 * @return
	 */
    @Delete("delete from tops where good_id=#{goodid} and type=#{type}")
	public boolean deleteByGoodidAndType(@Param("goodid")int goodid, @Param("type")byte type);
	
	/**
	 * Delete by good id
	 * @param goodid
	 * @return
	 */
    @Delete("delete from tops where good_id=#{goodid}")
	public boolean deleteByGoodid(@Param("goodid")int goodid);
}