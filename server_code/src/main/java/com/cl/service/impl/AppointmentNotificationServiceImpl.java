package com.cl.service.impl;

import com.cl.entity.JiuzhentongzhiEntity;
import com.cl.entity.YishengyuyueEntity;
import com.cl.service.AppointmentNotificationService;
import com.cl.service.JiuzhentongzhiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 预约通知服务实现类
 * 处理预约成功后的就诊通知发送逻辑
 */
@Service
public class AppointmentNotificationServiceImpl implements AppointmentNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentNotificationServiceImpl.class);

    @Autowired
    private JiuzhentongzhiService jiuzhentongzhiService;

    // 通知类型常量
    private static final int NOTIFICATION_TYPE_APPOINTMENT_SUCCESS = 1;
    private static final int NOTIFICATION_TYPE_24H_REMINDER = 2;
    private static final int NOTIFICATION_TYPE_1H_REMINDER = 3;
    private static final int NOTIFICATION_TYPE_TODAY_REMINDER = 4;

    // 发送状态常量
    private static final int SEND_STATUS_PENDING = 0;
    private static final int SEND_STATUS_SUCCESS = 1;
    private static final int SEND_STATUS_FAILED = 2;

    // 最大重试次数
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAndSendNotifications(YishengyuyueEntity yuyue) {
        if (yuyue == null || yuyue.getYuyueshijian() == null) {
            logger.warn("预约信息不完整，无法创建通知");
            return;
        }

        Date yuyueTime = yuyue.getYuyueshijian();
        String yuyueBianhao = yuyue.getYuyuebianhao();
        String yishengZhanghao = yuyue.getYishengzhanghao();
        String zhanghao = yuyue.getZhanghao();
        String shouji = yuyue.getShouji();
        String dianhua = yuyue.getDianhua();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStr = sdf.format(yuyueTime);

        // 1. 创建预约成功通知（立即发送）
        String successContent = String.format("您好，您的预约已成功！预约编号：%s，就诊时间：%s，医生账号：%s。请准时就诊。",
                yuyueBianhao, timeStr, yishengZhanghao);
        createNotificationRecord(yuyueBianhao, yishengZhanghao, zhanghao, shouji, dianhua,
                NOTIFICATION_TYPE_APPOINTMENT_SUCCESS, successContent, new Date(), yuyueTime);

        // 2. 创建就诊前24小时提醒
        Calendar cal24h = Calendar.getInstance();
        cal24h.setTime(yuyueTime);
        cal24h.add(Calendar.HOUR_OF_DAY, -24);
        Date time24h = cal24h.getTime();
        String content24h = String.format("温馨提醒：您有预约就诊将在24小时后（%s）进行，预约编号：%s，请做好准备。",
                timeStr, yuyueBianhao);
        createNotificationRecord(yuyueBianhao, yishengZhanghao, zhanghao, shouji, dianhua,
                NOTIFICATION_TYPE_24H_REMINDER, content24h, time24h, yuyueTime);

        // 3. 创建就诊前1小时提醒
        Calendar cal1h = Calendar.getInstance();
        cal1h.setTime(yuyueTime);
        cal1h.add(Calendar.HOUR_OF_DAY, -1);
        Date time1h = cal1h.getTime();
        String content1h = String.format("温馨提醒：您有预约就诊将在1小时后（%s）进行，预约编号：%s，请尽快前往医院。",
                timeStr, yuyueBianhao);
        createNotificationRecord(yuyueBianhao, yishengZhanghao, zhanghao, shouji, dianhua,
                NOTIFICATION_TYPE_1H_REMINDER, content1h, time1h, yuyueTime);

        // 4. 创建就诊当天提醒
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(yuyueTime);
        calToday.set(Calendar.HOUR_OF_DAY, 8);
        calToday.set(Calendar.MINUTE, 0);
        calToday.set(Calendar.SECOND, 0);
        // 如果就诊时间是当天8点前，则使用当前时间+5分钟作为提醒时间
        if (calToday.getTime().after(yuyueTime) || calToday.getTime().equals(yuyueTime)) {
            calToday.setTime(new Date());
            calToday.add(Calendar.MINUTE, 5);
        }
        Date timeToday = calToday.getTime();
        String contentToday = String.format("今日就诊提醒：您今天有预约就诊（%s），预约编号：%s，请准时到达。",
                timeStr, yuyueBianhao);
        createNotificationRecord(yuyueBianhao, yishengZhanghao, zhanghao, shouji, dianhua,
                NOTIFICATION_TYPE_TODAY_REMINDER, contentToday, timeToday, yuyueTime);

        logger.info("已为预约 {} 创建所有通知记录", yuyueBianhao);
    }

    /**
     * 创建通知记录并立即发送
     */
    private void createNotificationRecord(String yuyueBianhao, String yishengZhanghao, String zhanghao,
                                          String shouji, String dianhua, int type, String content,
                                          Date planSendTime, Date jiuzhenTime) {
        // 创建通知记录
        JiuzhentongzhiEntity record = new JiuzhentongzhiEntity();
        record.setYuyuebianhao(yuyueBianhao);
        record.setYishengzhanghao(yishengZhanghao);
        record.setZhanghao(zhanghao);
        record.setShouji(shouji);
        record.setDianhua(dianhua);
        record.setTongzhileixing(type);
        record.setTongzhibeizhu(content);
        record.setFasongzhuangtai(SEND_STATUS_PENDING);
        record.setJihuafasongshijian(planSendTime);
        record.setJiuzhenshijian(jiuzhenTime);
        record.setChongshicishu(0);
        record.setAddtime(new Date());

        jiuzhentongzhiService.insert(record);

        // 立即发送通知
        sendNotification(record);
    }

    /**
     * 发送通知
     */
    private void sendNotification(JiuzhentongzhiEntity record) {
        try {
            boolean success = doSendNotification(record);

            if (success) {
                record.setFasongzhuangtai(SEND_STATUS_SUCCESS);
                record.setTongzhishijian(new Date());
                record.setShibaiyuanyin(null);
                logger.info("通知发送成功，记录ID：{}，类型：{}", record.getId(), record.getTongzhileixing());
            } else {
                record.setFasongzhuangtai(SEND_STATUS_FAILED);
                record.setShibaiyuanyin("发送失败");
                logger.warn("通知发送失败，记录ID：{}，类型：{}", record.getId(), record.getTongzhileixing());
            }
        } catch (Exception e) {
            record.setFasongzhuangtai(SEND_STATUS_FAILED);
            record.setShibaiyuanyin(e.getMessage());
            logger.error("通知发送异常，记录ID：{}，错误：{}", record.getId(), e.getMessage(), e);
        }

        jiuzhentongzhiService.updateById(record);
    }

    /**
     * 实际发送通知的方法
     * 这里模拟发送逻辑，实际项目中可以集成短信网关、推送服务等
     */
    private boolean doSendNotification(JiuzhentongzhiEntity record) {
        // 模拟发送逻辑
        // 实际项目中，这里应该调用短信网关或推送服务
        // 例如：发送短信、APP推送、微信通知等

        String content = record.getTongzhibeizhu();
        String phone = record.getShouji() != null ? record.getShouji() : record.getDianhua();

        // 模拟发送成功率95%
        boolean mockSuccess = Math.random() > 0.05;

        return mockSuccess;
    }

    @Override
    public boolean retrySendNotification(Long tongzhiId) {
        JiuzhentongzhiEntity record = jiuzhentongzhiService.selectById(tongzhiId);
        if (record == null) {
            logger.warn("通知记录不存在，ID：{}", tongzhiId);
            return false;
        }

        if (record.getFasongzhuangtai() == SEND_STATUS_SUCCESS) {
            logger.info("通知已发送成功，无需重试，ID：{}", tongzhiId);
            return true;
        }

        if (record.getChongshicishu() >= MAX_RETRY_COUNT) {
            logger.warn("通知重试次数已达上限，ID：{}，当前次数：{}",
                    tongzhiId, record.getChongshicishu());
            return false;
        }

        // 增加重试次数
        record.setChongshicishu(record.getChongshicishu() + 1);

        // 重新发送
        try {
            boolean success = doSendNotification(record);

            if (success) {
                record.setFasongzhuangtai(SEND_STATUS_SUCCESS);
                record.setTongzhishijian(new Date());
                record.setShibaiyuanyin(null);
                logger.info("通知重试发送成功，ID：{}", tongzhiId);
            } else {
                record.setFasongzhuangtai(SEND_STATUS_FAILED);
                record.setShibaiyuanyin("重试发送失败");
                logger.warn("通知重试发送失败，ID：{}，重试次数：{}",
                        tongzhiId, record.getChongshicishu());
            }

            jiuzhentongzhiService.updateById(record);
            return success;
        } catch (Exception e) {
            record.setFasongzhuangtai(SEND_STATUS_FAILED);
            record.setShibaiyuanyin(e.getMessage());
            jiuzhentongzhiService.updateById(record);
            logger.error("通知重试发送异常，ID：{}，错误：{}", tongzhiId, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public int batchRetrySendNotifications(Long[] ids) {
        int successCount = 0;
        if (ids == null || ids.length == 0) {
            return 0;
        }

        for (Long id : ids) {
            if (retrySendNotification(id)) {
                successCount++;
            }
        }

        logger.info("批量重试发送完成，总数：{}，成功：{}", ids.length, successCount);
        return successCount;
    }
}
