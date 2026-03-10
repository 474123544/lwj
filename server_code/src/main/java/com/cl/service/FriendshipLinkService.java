package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.FriendshipLinkEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.FriendshipLinkView;


/**
 * 友情链接
 *
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
public interface FriendshipLinkService extends IService<FriendshipLinkEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FriendshipLinkView> selectListView(Wrapper<FriendshipLinkEntity> wrapper);
   	
   	FriendshipLinkView selectView(@Param("ew") Wrapper<FriendshipLinkEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FriendshipLinkEntity> wrapper);
   	
   
}

