package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChufangxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChufangxinxiView;


/**
 * 处方信息
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface ChufangxinxiService extends IService<ChufangxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChufangxinxiView> selectListView(Wrapper<ChufangxinxiEntity> wrapper);
   	
   	ChufangxinxiView selectView(@Param("ew") Wrapper<ChufangxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChufangxinxiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ChufangxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ChufangxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ChufangxinxiEntity> wrapper);



}

