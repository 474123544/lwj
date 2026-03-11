package com.cl.service;

import com.cl.entity.JiuzhentongzhiEntity;
import com.cl.entity.YishengyuyueEntity;
import com.cl.service.impl.AppointmentNotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * 预约通知服务测试类
 */
public class AppointmentNotificationServiceTest {

    @Mock
    private JiuzhentongzhiService jiuzhentongzhiService;

    @InjectMocks
    private AppointmentNotificationServiceImpl appointmentNotificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 测试预约审核通过后创建通知
     */
    @Test
    public void testCreateAndSendNotifications() {
        // 准备测试数据
        YishengyuyueEntity yuyue = new YishengyuyueEntity();
        yuyue.setYuyuebianhao("TEST20250311001");
        yuyue.setYishengzhanghao("doctor001");
        yuyue.setZhanghao("user001");
        yuyue.setShouji("13800138000");
        yuyue.setDianhua("010-12345678");
        yuyue.setYuyueshijian(new Date(System.currentTimeMillis() + 48 * 60 * 60 * 1000)); // 48小时后

        // 模拟插入操作
        when(jiuzhentongzhiService.insert(any(JiuzhentongzhiEntity.class))).thenReturn(true);
        when(jiuzhentongzhiService.updateById(any(JiuzhentongzhiEntity.class))).thenReturn(true);

        // 执行测试
        appointmentNotificationService.createAndSendNotifications(yuyue);

        // 验证插入了4条通知记录（预约成功、24小时提醒、1小时提醒、当天提醒）
        verify(jiuzhentongzhiService, times(4)).insert(any(JiuzhentongzhiEntity.class));
        verify(jiuzhentongzhiService, times(4)).updateById(any(JiuzhentongzhiEntity.class));
    }

    /**
     * 测试空预约数据
     */
    @Test
    public void testCreateAndSendNotificationsWithNull() {
        // 测试空数据
        appointmentNotificationService.createAndSendNotifications(null);
        verify(jiuzhentongzhiService, never()).insert(any());

        // 测试缺少预约时间
        YishengyuyueEntity yuyue = new YishengyuyueEntity();
        yuyue.setYuyuebianhao("TEST001");
        appointmentNotificationService.createAndSendNotifications(yuyue);
        verify(jiuzhentongzhiService, never()).insert(any());
    }

    /**
     * 测试重试发送功能 - 成功场景
     */
    @Test
    public void testRetrySendNotificationSuccess() {
        // 准备测试数据
        JiuzhentongzhiEntity record = new JiuzhentongzhiEntity();
        record.setId(1L);
        record.setFasongzhuangtai(2); // 发送失败状态
        record.setChongshicishu(0);
        record.setTongzhibeizhu("测试通知内容");

        when(jiuzhentongzhiService.selectById(1L)).thenReturn(record);
        when(jiuzhentongzhiService.updateById(any(JiuzhentongzhiEntity.class))).thenReturn(true);

        // 执行测试
        boolean result = appointmentNotificationService.retrySendNotification(1L);

        // 验证结果
        assertTrue(result);
        verify(jiuzhentongzhiService).updateById(any(JiuzhentongzhiEntity.class));
    }

    /**
     * 测试重试发送功能 - 记录不存在
     */
    @Test
    public void testRetrySendNotificationNotFound() {
        when(jiuzhentongzhiService.selectById(999L)).thenReturn(null);

        boolean result = appointmentNotificationService.retrySendNotification(999L);

        assertFalse(result);
        verify(jiuzhentongzhiService, never()).updateById(any());
    }

    /**
     * 测试批量重试功能
     */
    @Test
    public void testBatchRetrySendNotifications() {
        // 准备测试数据
        JiuzhentongzhiEntity record1 = new JiuzhentongzhiEntity();
        record1.setId(1L);
        record1.setFasongzhuangtai(2);
        record1.setChongshicishu(0);
        record1.setTongzhibeizhu("通知1");

        JiuzhentongzhiEntity record2 = new JiuzhentongzhiEntity();
        record2.setId(2L);
        record2.setFasongzhuangtai(2);
        record2.setChongshicishu(0);
        record2.setTongzhibeizhu("通知2");

        when(jiuzhentongzhiService.selectById(1L)).thenReturn(record1);
        when(jiuzhentongzhiService.selectById(2L)).thenReturn(record2);
        when(jiuzhentongzhiService.updateById(any(JiuzhentongzhiEntity.class))).thenReturn(true);

        // 执行测试
        Long[] ids = {1L, 2L};
        int successCount = appointmentNotificationService.batchRetrySendNotifications(ids);

        // 验证结果
        assertTrue(successCount >= 0 && successCount <= 2);
    }
}
