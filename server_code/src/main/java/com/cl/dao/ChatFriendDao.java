package com.cl.dao;

import com.cl.entity.ChatFriendEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ChatFriendView;


/**
 * 好友表
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:16
 */
public interface ChatFriendDao extends BaseMapper<ChatFriendEntity> {
	
	List<ChatFriendView> selectListView(@Param("ew") Wrapper<ChatFriendEntity> wrapper);

	List<ChatFriendView> selectListView(Pagination page,@Param("ew") Wrapper<ChatFriendEntity> wrapper);
	
	ChatFriendView selectView(@Param("ew") Wrapper<ChatFriendEntity> wrapper);

    List<ChatFriendView> selectFriendListView(Pagination page, Map<String, Object> params);

}
