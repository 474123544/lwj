package com.cl.dao;

import com.cl.entity.ConfigEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ConfigView;


/**
 * 轮播图
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:14
 */
public interface ConfigDao extends BaseMapper<ConfigEntity> {
	
	List<ConfigView> selectListView(@Param("ew") Wrapper<ConfigEntity> wrapper);

	List<ConfigView> selectListView(Pagination page,@Param("ew") Wrapper<ConfigEntity> wrapper);
	
	ConfigView selectView(@Param("ew") Wrapper<ConfigEntity> wrapper);


}
