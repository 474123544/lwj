package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ConfigEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ConfigView;


/**
 * 轮播图
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:14
 */
public interface ConfigService extends IService<ConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ConfigView> selectListView(Wrapper<ConfigEntity> wrapper);
   	
   	ConfigView selectView(@Param("ew") Wrapper<ConfigEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ConfigEntity> wrapper);
   	
   
}

