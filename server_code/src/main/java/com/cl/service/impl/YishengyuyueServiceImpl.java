package com.cl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.YishengyuyueDao;
import com.cl.entity.YishengyuyueEntity;
import com.cl.service.YishengyuyueService;
import com.cl.entity.view.YishengyuyueView;
import com.cl.entity.JiuzhentongzhiEntity;
import com.cl.service.JiuzhentongzhiService;

@Service("yishengyuyueService")
public class YishengyuyueServiceImpl extends ServiceImpl<YishengyuyueDao, YishengyuyueEntity> implements YishengyuyueService {

    @Autowired
    private JiuzhentongzhiService jiuzhentongzhiService;
     
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YishengyuyueEntity> page = this.selectPage(
                new Query<YishengyuyueEntity>(params).getPage(),
                new EntityWrapper<YishengyuyueEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YishengyuyueEntity> wrapper) {
	  Page<YishengyuyueView> page =new Query<YishengyuyueView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,wrapper));
     	PageUtils pageUtil = new PageUtils(page);
     	return pageUtil;
  	}
    
	@Override
	public List<YishengyuyueView> selectListView(Wrapper<YishengyuyueEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YishengyuyueView selectView(Wrapper<YishengyuyueEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	@Override
    public boolean insert(YishengyuyueEntity entity) {
        boolean result = super.insert(entity);
        if (result) {
            // 预约成功后立即发送所有后续提醒
            sendAllNotifications(entity);
        }
        return result;
    }
    
    private void sendAllNotifications(YishengyuyueEntity entity) {
        // 创建通知实体
        JiuzhentongzhiEntity notification = new JiuzhentongzhiEntity();
        notification.setTongzhibianhao("NOTIFY_" + UUID.randomUUID().toString().substring(0, 8));
        notification.setYishengzhanghao(entity.getYishengzhanghao());
        notification.setZhanghao(entity.getZhanghao());
        notification.setDianhua(entity.getDianhua());
        notification.setShouji(entity.getShouji());
        notification.setJiuzhenshijian(entity.getYuyueshijian());
        notification.setTongzhishijian(new Date());
        notification.setTongzhibeizhu("预约成功，请注意就诊时间");
        notification.setJieshouzhuangtai("未发送");
        notification.setChongshicishu(0);
        notification.setAddtime(new Date());
        
        // 保存通知
        jiuzhentongzhiService.insert(notification);
        
        // 这里可以添加其他类型的通知，例如提前提醒等
        // 可以根据就诊时间计算提前提醒的时间点
        
        // 例如：提前一天提醒
        Date oneDayBefore = new Date(entity.getYuyueshijian().getTime() - 24 * 60 * 60 * 1000);
        if (oneDayBefore.after(new Date())) {
            JiuzhentongzhiEntity reminder1 = new JiuzhentongzhiEntity();
            reminder1.setTongzhibianhao("NOTIFY_" + UUID.randomUUID().toString().substring(0, 8));
            reminder1.setYishengzhanghao(entity.getYishengzhanghao());
            reminder1.setZhanghao(entity.getZhanghao());
            reminder1.setDianhua(entity.getDianhua());
            reminder1.setShouji(entity.getShouji());
            reminder1.setJiuzhenshijian(entity.getYuyueshijian());
            reminder1.setTongzhishijian(oneDayBefore);
            reminder1.setTongzhibeizhu("明天就诊，请做好准备");
            reminder1.setJieshouzhuangtai("未发送");
            reminder1.setChongshicishu(0);
            reminder1.setAddtime(new Date());
            jiuzhentongzhiService.insert(reminder1);
        }
        
        // 例如：提前2小时提醒
        Date twoHoursBefore = new Date(entity.getYuyueshijian().getTime() - 2 * 60 * 60 * 1000);
        if (twoHoursBefore.after(new Date())) {
            JiuzhentongzhiEntity reminder2 = new JiuzhentongzhiEntity();
            reminder2.setTongzhibianhao("NOTIFY_" + UUID.randomUUID().toString().substring(0, 8));
            reminder2.setYishengzhanghao(entity.getYishengzhanghao());
            reminder2.setZhanghao(entity.getZhanghao());
            reminder2.setDianhua(entity.getDianhua());
            reminder2.setShouji(entity.getShouji());
            reminder2.setJiuzhenshijian(entity.getYuyueshijian());
            reminder2.setTongzhishijian(twoHoursBefore);
            reminder2.setTongzhibeizhu("2小时后就诊，请提前到达");
            reminder2.setJieshouzhuangtai("未发送");
            reminder2.setChongshicishu(0);
            reminder2.setAddtime(new Date());
            jiuzhentongzhiService.insert(reminder2);
        }
    }

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YishengyuyueEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YishengyuyueEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YishengyuyueEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}