package com.cl.service;

import com.cl.entity.YishengyuyueEntity;

/**
 * 预约通知服务接口
 * 处理预约成功后的就诊通知发送逻辑
 */
public interface AppointmentNotificationService {

    /**
     * 预约审核通过后，立即创建所有后续通知记录并发送
     * @param yuyue 预约实体
     */
    void createAndSendNotifications(YishengyuyueEntity yuyue);

    /**
     * 重试发送失败的通知
     * @param tongzhijiluId 通知记录ID
     * @return 是否发送成功
     */
    boolean retrySendNotification(Long tongzhijiluId);

    /**
     * 批量重试发送失败的通知
     * @param ids 通知记录ID数组
     * @return 成功重试的数量
     */
    int batchRetrySendNotifications(Long[] ids);
}
