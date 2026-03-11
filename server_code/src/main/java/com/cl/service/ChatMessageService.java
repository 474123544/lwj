package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ChatMessageEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChatMessageView;


/**
 * 消息表
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
public interface ChatMessageService extends IService<ChatMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChatMessageView> selectListView(Wrapper<ChatMessageEntity> wrapper);
   	
   	ChatMessageView selectView(@Param("ew") Wrapper<ChatMessageEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChatMessageEntity> wrapper);
   	
   
}

