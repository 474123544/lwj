package com.cl.dao;

import com.cl.entity.ChatMessageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChatMessageView;


/**
 * 消息表
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
public interface ChatMessageDao extends BaseMapper<ChatMessageEntity> {
	
	List<ChatMessageView> selectListView(@Param("ew") Wrapper<ChatMessageEntity> wrapper);

	List<ChatMessageView> selectListView(Pagination page,@Param("ew") Wrapper<ChatMessageEntity> wrapper);
	
	ChatMessageView selectView(@Param("ew") Wrapper<ChatMessageEntity> wrapper);


}
