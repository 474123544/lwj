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


import com.cl.dao.RukujiluDao;
import com.cl.entity.RukujiluEntity;
import com.cl.service.RukujiluService;
import com.cl.entity.view.RukujiluView;

@Service("rukujiluService")
public class RukujiluServiceImpl extends ServiceImpl<RukujiluDao, RukujiluEntity> implements RukujiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RukujiluEntity> page = this.selectPage(
                new Query<RukujiluEntity>(params).getPage(),
                new EntityWrapper<RukujiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RukujiluEntity> wrapper) {
		  Page<RukujiluView> page =new Query<RukujiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<RukujiluView> selectListView(Wrapper<RukujiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RukujiluView selectView(Wrapper<RukujiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
