package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Users;

public interface UsersDao {
    int deleteById(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectById(Integer id);

    int updateByIdSelective(Users record);

    int updateById(Users record);    
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations
    
	/**
	 * Find users by username
	 * @return If no record returns null
	 */
    @Select("select * from users where username=#{username}")
	public Users getByUsername(String username);
	
	/**
	 * Find by username and password
	 * @param username
	 * @param password
	 * @return If no record returns null
	 */
    @Select("select * from users where username=#{username} and password=#{password}")
	public Users getByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	
	/**
	 * Get order list
	 * @param page
	 * @param rows
	 * @return if no record returns null
	 */
    @Select("select * from users order by id desc limit #{begin}, #{size}")
	public List<Users> getList(@Param("begin")int begin, @Param("size")int size);

	/**
	 * Get total order number
	 * @return
	 */
    @Select("select count(*) from users")
	public long getTotal();
	
    
}