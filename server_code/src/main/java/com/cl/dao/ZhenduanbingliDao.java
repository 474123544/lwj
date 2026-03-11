package com.cl.dao;

import com.cl.entity.ZhenduanbingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhenduanbingliView;


/**
 * 诊断病历
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface ZhenduanbingliDao extends BaseMapper<ZhenduanbingliEntity> {
	
	List<ZhenduanbingliView> selectListView(@Param("ew") Wrapper<ZhenduanbingliEntity> wrapper);

	List<ZhenduanbingliView> selectListView(Pagination page,@Param("ew") Wrapper<ZhenduanbingliEntity> wrapper);
	
	ZhenduanbingliView selectView(@Param("ew") Wrapper<ZhenduanbingliEntity> wrapper);


}
