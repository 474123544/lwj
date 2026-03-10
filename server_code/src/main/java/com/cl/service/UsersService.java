package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.UsersEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.UsersView;


/**
 * 管理员
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:14
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<UsersView> selectListView(Wrapper<UsersEntity> wrapper);
   	
   	UsersView selectView(@Param("ew") Wrapper<UsersEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<UsersEntity> wrapper);
   	
   
}

