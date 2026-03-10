package com.cl.dao;

import com.cl.entity.YaopinxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YaopinxinxiView;


/**
 * 药品信息
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface YaopinxinxiDao extends BaseMapper<YaopinxinxiEntity> {
	
	List<YaopinxinxiView> selectListView(@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);

	List<YaopinxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);
	
	YaopinxinxiView selectView(@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);



}
