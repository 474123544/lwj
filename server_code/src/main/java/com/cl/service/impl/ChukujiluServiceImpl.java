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


import com.cl.dao.ChukujiluDao;
import com.cl.entity.ChukujiluEntity;
import com.cl.service.ChukujiluService;
import com.cl.entity.view.ChukujiluView;

@Service("chukujiluService")
public class ChukujiluServiceImpl extends ServiceImpl<ChukujiluDao, ChukujiluEntity> implements ChukujiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChukujiluEntity> page = this.selectPage(
                new Query<ChukujiluEntity>(params).getPage(),
                new EntityWrapper<ChukujiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChukujiluEntity> wrapper) {
		  Page<ChukujiluView> page =new Query<ChukujiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ChukujiluView> selectListView(Wrapper<ChukujiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChukujiluView selectView(Wrapper<ChukujiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
