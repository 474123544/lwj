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


import com.cl.dao.ConfigDao;
import com.cl.entity.ConfigEntity;
import com.cl.service.ConfigService;
import com.cl.entity.view.ConfigView;

@Service("configService")
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, ConfigEntity> implements ConfigService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ConfigEntity> page = this.selectPage(
                new Query<ConfigEntity>(params).getPage(),
                new EntityWrapper<ConfigEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ConfigEntity> wrapper) {
		  Page<ConfigView> page =new Query<ConfigView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ConfigView> selectListView(Wrapper<ConfigEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ConfigView selectView(Wrapper<ConfigEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
