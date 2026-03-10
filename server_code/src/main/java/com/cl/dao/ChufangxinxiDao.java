package com.cl.dao;

import com.cl.entity.ChufangxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChufangxinxiView;


/**
 * 处方信息
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface ChufangxinxiDao extends BaseMapper<ChufangxinxiEntity> {
	
	List<ChufangxinxiView> selectListView(@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);

	List<ChufangxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);
	
	ChufangxinxiView selectView(@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);



}
