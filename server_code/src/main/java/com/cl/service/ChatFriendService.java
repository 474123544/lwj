package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChatFriendEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChatFriendView;


/**
 * 好友表
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
public interface ChatFriendService extends IService<ChatFriendEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChatFriendView> selectListView(Wrapper<ChatFriendEntity> wrapper);
   	
   	ChatFriendView selectView(@Param("ew") Wrapper<ChatFriendEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChatFriendEntity> wrapper);
   	
       PageUtils queryFriendPage(Map<String, Object> params);

}

