package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChukujiluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChukujiluView;


/**
 * 出库记录
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface ChukujiluService extends IService<ChukujiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChukujiluView> selectListView(Wrapper<ChukujiluEntity> wrapper);
   	
   	ChukujiluView selectView(@Param("ew") Wrapper<ChukujiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChukujiluEntity> wrapper);
   	
   
}

