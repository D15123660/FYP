package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Admins;

public interface AdminsDao {
    int deleteById(Integer id);

    int insert(Admins record);

    int insertSelective(Admins record);

    Admins selectById(Integer id);

    int updateByIdSelective(Admins record);

    int updateById(Admins record);
    
    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
    
	/**
	 * Find by username
	 * @param username
	 * @return
	 */
    @Select("select * from admins where username=#{username}")
	public Admins getByUsername(String username);
	
	/**
	 * Find by username and password
	 * @param username
	 * @param password
	 * @return if no record returns null
	 */
    @Select("select * from admins where username=#{username} and password=#{password}")
	public Admins getByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

	/**
	 * Get list
	 * @param page
	 * @param rows
	 * @return if no record returns null
	 */
    @Select("select * from admins order by id desc limit #{begin}, #{size}")
	public List<Admins> getList(@Param("begin")int begin, @Param("size")int size);

	/**
	 * Total
	 * @return
	 */
    @Select("select count(*) from admins")
	public long getTotal();
}