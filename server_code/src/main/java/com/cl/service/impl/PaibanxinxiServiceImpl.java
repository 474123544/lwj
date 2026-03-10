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


import com.cl.dao.PaibanxinxiDao;
import com.cl.entity.PaibanxinxiEntity;
import com.cl.service.PaibanxinxiService;
import com.cl.entity.view.PaibanxinxiView;

@Service("paibanxinxiService")
public class PaibanxinxiServiceImpl extends ServiceImpl<PaibanxinxiDao, PaibanxinxiEntity> implements PaibanxinxiService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PaibanxinxiEntity> page = this.selectPage(
                new Query<PaibanxinxiEntity>(params).getPage(),
                new EntityWrapper<PaibanxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PaibanxinxiEntity> wrapper) {
		  Page<PaibanxinxiView> page =new Query<PaibanxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<PaibanxinxiView> selectListView(Wrapper<PaibanxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PaibanxinxiView selectView(Wrapper<PaibanxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
