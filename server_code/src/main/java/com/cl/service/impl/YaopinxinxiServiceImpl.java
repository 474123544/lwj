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


import com.cl.dao.YaopinxinxiDao;
import com.cl.entity.YaopinxinxiEntity;
import com.cl.service.YaopinxinxiService;
import com.cl.entity.view.YaopinxinxiView;

@Service("yaopinxinxiService")
public class YaopinxinxiServiceImpl extends ServiceImpl<YaopinxinxiDao, YaopinxinxiEntity> implements YaopinxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YaopinxinxiEntity> page = this.selectPage(
                new Query<YaopinxinxiEntity>(params).getPage(),
                new EntityWrapper<YaopinxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
		  Page<YaopinxinxiView> page =new Query<YaopinxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YaopinxinxiView> selectListView(Wrapper<YaopinxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YaopinxinxiView selectView(Wrapper<YaopinxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
