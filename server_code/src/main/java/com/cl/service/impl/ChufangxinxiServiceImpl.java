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


import com.cl.dao.ChufangxinxiDao;
import com.cl.entity.ChufangxinxiEntity;
import com.cl.service.ChufangxinxiService;
import com.cl.entity.view.ChufangxinxiView;

@Service("chufangxinxiService")
public class ChufangxinxiServiceImpl extends ServiceImpl<ChufangxinxiDao, ChufangxinxiEntity> implements ChufangxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChufangxinxiEntity> page = this.selectPage(
                new Query<ChufangxinxiEntity>(params).getPage(),
                new EntityWrapper<ChufangxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChufangxinxiEntity> wrapper) {
		  Page<ChufangxinxiView> page =new Query<ChufangxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ChufangxinxiView> selectListView(Wrapper<ChufangxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChufangxinxiView selectView(Wrapper<ChufangxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ChufangxinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ChufangxinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ChufangxinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
