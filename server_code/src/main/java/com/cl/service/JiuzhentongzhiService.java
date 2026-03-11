package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiuzhentongzhiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiuzhentongzhiView;


/**
 * 就诊通知
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface JiuzhentongzhiService extends IService<JiuzhentongzhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiuzhentongzhiView> selectListView(Wrapper<JiuzhentongzhiEntity> wrapper);
   	
   	JiuzhentongzhiView selectView(@Param("ew") Wrapper<JiuzhentongzhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiuzhentongzhiEntity> wrapper);
   	
   
}

