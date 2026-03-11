package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.ChatFriendDao;
import com.cl.entity.ChatFriendEntity;
import com.cl.service.ChatFriendService;
import com.cl.entity.view.ChatFriendView;

@Service("chatFriendService")
public class ChatFriendServiceImpl extends ServiceImpl<ChatFriendDao, ChatFriendEntity> implements ChatFriendService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChatFriendEntity> page = this.selectPage(
                new Query<ChatFriendEntity>(params).getPage(),
                new EntityWrapper<ChatFriendEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChatFriendEntity> wrapper) {
		  Page<ChatFriendView> page =new Query<ChatFriendView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ChatFriendView> selectListView(Wrapper<ChatFriendEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChatFriendView selectView(Wrapper<ChatFriendEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
    @Override
    public PageUtils queryFriendPage(Map<String, Object> params) {
        Page<ChatFriendView> page =new Query<ChatFriendView>(params).getPage();
        page.setRecords(baseMapper.selectFriendListView(page, params));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }
	


}
