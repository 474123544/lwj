package com.cl.dao;

import com.cl.entity.JiuzhentongzhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiuzhentongzhiView;


/**
 * 就诊通知
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface JiuzhentongzhiDao extends BaseMapper<JiuzhentongzhiEntity> {
	
	List<JiuzhentongzhiView> selectListView(@Param("ew") Wrapper<JiuzhentongzhiEntity> wrapper);

	List<JiuzhentongzhiView> selectListView(Pagination page,@Param("ew") Wrapper<JiuzhentongzhiEntity> wrapper);
	
	JiuzhentongzhiView selectView(@Param("ew") Wrapper<JiuzhentongzhiEntity> wrapper);


}
