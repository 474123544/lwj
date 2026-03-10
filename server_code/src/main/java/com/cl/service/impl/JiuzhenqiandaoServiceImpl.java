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


import com.cl.dao.JiuzhenqiandaoDao;
import com.cl.entity.JiuzhenqiandaoEntity;
import com.cl.service.JiuzhenqiandaoService;
import com.cl.entity.view.JiuzhenqiandaoView;

@Service("jiuzhenqiandaoService")
public class JiuzhenqiandaoServiceImpl extends ServiceImpl<JiuzhenqiandaoDao, JiuzhenqiandaoEntity> implements JiuzhenqiandaoService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiuzhenqiandaoEntity> page = this.selectPage(
                new Query<JiuzhenqiandaoEntity>(params).getPage(),
                new EntityWrapper<JiuzhenqiandaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiuzhenqiandaoEntity> wrapper) {
		  Page<JiuzhenqiandaoView> page =new Query<JiuzhenqiandaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JiuzhenqiandaoView> selectListView(Wrapper<JiuzhenqiandaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiuzhenqiandaoView selectView(Wrapper<JiuzhenqiandaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
