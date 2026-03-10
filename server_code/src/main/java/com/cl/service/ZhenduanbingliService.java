package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZhenduanbingliEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhenduanbingliView;


/**
 * 诊断病历
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface ZhenduanbingliService extends IService<ZhenduanbingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhenduanbingliView> selectListView(Wrapper<ZhenduanbingliEntity> wrapper);
   	
   	ZhenduanbingliView selectView(@Param("ew") Wrapper<ZhenduanbingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhenduanbingliEntity> wrapper);
   	
   
}

