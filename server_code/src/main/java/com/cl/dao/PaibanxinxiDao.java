package com.cl.dao;

import com.cl.entity.PaibanxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.PaibanxinxiView;


/**
 * 排班信息
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface PaibanxinxiDao extends BaseMapper<PaibanxinxiEntity> {
	
	List<PaibanxinxiView> selectListView(@Param("ew") Wrapper<PaibanxinxiEntity> wrapper);

	List<PaibanxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<PaibanxinxiEntity> wrapper);
	
	PaibanxinxiView selectView(@Param("ew") Wrapper<PaibanxinxiEntity> wrapper);


}
