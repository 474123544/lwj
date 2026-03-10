package com.cl.dao;

import com.cl.entity.RukujiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.RukujiluView;


/**
 * 入库记录
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface RukujiluDao extends BaseMapper<RukujiluEntity> {
	
	List<RukujiluView> selectListView(@Param("ew") Wrapper<RukujiluEntity> wrapper);

	List<RukujiluView> selectListView(Pagination page,@Param("ew") Wrapper<RukujiluEntity> wrapper);
	
	RukujiluView selectView(@Param("ew") Wrapper<RukujiluEntity> wrapper);


}
