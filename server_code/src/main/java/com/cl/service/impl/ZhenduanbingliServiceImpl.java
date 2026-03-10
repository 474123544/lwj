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


import com.cl.dao.ZhenduanbingliDao;
import com.cl.entity.ZhenduanbingliEntity;
import com.cl.service.ZhenduanbingliService;
import com.cl.entity.view.ZhenduanbingliView;

@Service("zhenduanbingliService")
public class ZhenduanbingliServiceImpl extends ServiceImpl<ZhenduanbingliDao, ZhenduanbingliEntity> implements ZhenduanbingliService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhenduanbingliEntity> page = this.selectPage(
                new Query<ZhenduanbingliEntity>(params).getPage(),
                new EntityWrapper<ZhenduanbingliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhenduanbingliEntity> wrapper) {
		  Page<ZhenduanbingliView> page =new Query<ZhenduanbingliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<ZhenduanbingliView> selectListView(Wrapper<ZhenduanbingliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhenduanbingliView selectView(Wrapper<ZhenduanbingliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
