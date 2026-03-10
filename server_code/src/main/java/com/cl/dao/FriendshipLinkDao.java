package com.cl.dao;

import com.cl.entity.FriendshipLinkEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FriendshipLinkView;


/**
 * 友情链接
 * 
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface FriendshipLinkDao extends BaseMapper<FriendshipLinkEntity> {
	
	List<FriendshipLinkView> selectListView(@Param("ew") Wrapper<FriendshipLinkEntity> wrapper);

	List<FriendshipLinkView> selectListView(Pagination page,@Param("ew") Wrapper<FriendshipLinkEntity> wrapper);
	
	FriendshipLinkView selectView(@Param("ew") Wrapper<FriendshipLinkEntity> wrapper);


}
