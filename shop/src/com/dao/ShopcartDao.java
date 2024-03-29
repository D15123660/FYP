package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Shopcart;

public interface ShopcartDao {
    int deleteById(Integer id);

    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    Shopcart selectById(Integer id);

    int updateByIdSelective(Shopcart record);

    int updateById(Shopcart record); 
    
    // The above is the automatic generation interface of mybatis generator, which is implemented in mapper.xml
    
    // ------------------------------------------------------------
    
    // The following methods are implemented using mybatis annotations

    @Select("select * from shopcart where user_id=#{userid}")
	List<Shopcart> getList(@Param("userid")int userid);
    
    @Select("select * from shopcart where id=#{id}")
    Shopcart get(@Param("id")int id);
    
    @Select("select * from shopcart where user_id=#{userid} and good_id=#{goodid} and color_id=#{colorid} and size_id=#{sizeid}")
    Shopcart getCart(@Param("userid")int userid, @Param("goodid")int goodid, @Param("colorid")int colorid, @Param("sizeid")int sizeid);

    @Select("select count(*) from shopcart where user_id=#{userid}")
	int getTotal(@Param("userid")int userid);

    @Delete("delete from shopcart where user_id=#{userid}")
	boolean deleteByUserid(int userid);
}