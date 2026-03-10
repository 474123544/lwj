package com.cl.dao;

import com.cl.entity.UsersEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.UsersView;


/**
 * 管理员
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:14
 */
public interface UsersDao extends BaseMapper<UsersEntity> {
	
	List<UsersView> selectListView(@Param("ew") Wrapper<UsersEntity> wrapper);

	List<UsersView> selectListView(Pagination page,@Param("ew") Wrapper<UsersEntity> wrapper);
	
	UsersView selectView(@Param("ew") Wrapper<UsersEntity> wrapper);


}
