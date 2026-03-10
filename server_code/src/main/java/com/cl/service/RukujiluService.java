package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.RukujiluEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.RukujiluView;


/**
 * 入库记录
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface RukujiluService extends IService<RukujiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RukujiluView> selectListView(Wrapper<RukujiluEntity> wrapper);
   	
   	RukujiluView selectView(@Param("ew") Wrapper<RukujiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RukujiluEntity> wrapper);
   	
   
}

