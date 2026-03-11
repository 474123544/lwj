package com.cl.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

import com.cl.entity.JiuzhentongzhiEntity;
import com.cl.service.JiuzhentongzhiService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

@Component
public class NotificationTask {

    @Autowired
    private JiuzhentongzhiService jiuzhentongzhiService;

    // 每5分钟执行一次
    @Scheduled(cron = "0 0/5 * * * ?")
    public void processNotifications() {
        // 查询未发送且重试次数小于3的通知
        EntityWrapper<JiuzhentongzhiEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("jieshouzhuangtai", "未发送")
               .lt("chongshicishu", 3);
        
        List<JiuzhentongzhiEntity> notifications = jiuzhentongzhiService.selectList(wrapper);
        
        for (JiuzhentongzhiEntity notification : notifications) {
            // 检查通知时间是否已到
            if (notification.getTongzhishijian().before(new Date())) {
                try {
                    // 模拟发送通知
                    sendNotification(notification);
                    
                    // 更新通知状态为已发送
                    notification.setJieshouzhuangtai("已发送");
                    jiuzhentongzhiService.updateById(notification);
                } catch (Exception e) {
                    // 发送失败，更新重试次数和失败原因
                    notification.setChongshicishu(notification.getChongshicishu() + 1);
                    notification.setShibaiyuanyin(e.getMessage());
                    jiuzhentongzhiService.updateById(notification);
                }
            }
        }
    }

    private void sendNotification(JiuzhentongzhiEntity notification) throws Exception {
        // 这里实现实际的通知发送逻辑
        // 例如：发送短信、邮件等
        // 模拟发送过程
        System.out.println("发送通知：" + notification.getTongzhibianhao() + " 给 " + notification.getZhanghao());
        
        // 模拟发送失败的情况
        // if (Math.random() > 0.5) {
        //     throw new Exception("发送失败：网络异常");
        // }
    }
}
