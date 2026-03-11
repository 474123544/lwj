package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 就诊通知
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("jiuzhentongzhi")
public class JiuzhentongzhiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JiuzhentongzhiEntity() {
		
	}
	
	public JiuzhentongzhiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 通知编号
	 */
					
	private String tongzhibianhao;
	
	/**
	 * 医生账号
	 */
					
	private String yishengzhanghao;
	
	/**
	 * 电话
	 */
					
	private String dianhua;
	
	/**
	 * 就诊时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date jiuzhenshijian;
	
	/**
	 * 通知时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date tongzhishijian;
	
	/**
	 * 账号
	 */
					
	private String zhanghao;
	
	/**
	 * 手机
	 */
					
	private String shouji;
	
	/**
	 * 通知备注
	 */
					
	private String tongzhibeizhu;
	
	/**
	 * 预约编号
	 */
	private String yuyuebianhao;
	
	/**
	 * 通知类型（1-预约成功通知，2-就诊前24小时提醒，3-就诊前1小时提醒，4-就诊当天提醒）
	 */
	private Integer tongzhileixing;
	
	/**
	 * 发送状态（0-待发送，1-发送成功，2-发送失败）
	 */
	private Integer fasongzhuangtai;
	
	/**
	 * 失败原因
	 */
	private String shibaiyuanyin;
	
	/**
	 * 重试次数
	 */
	private Integer chongshicishu;
	
	/**
	 * 计划发送时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat		
	private Date jihuafasongshijian;
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：通知编号
	 */
	public void setTongzhibianhao(String tongzhibianhao) {
		this.tongzhibianhao = tongzhibianhao;
	}
	/**
	 * 获取：通知编号
	 */
	public String getTongzhibianhao() {
		return tongzhibianhao;
	}
	/**
	 * 设置：医生账号
	 */
	public void setYishengzhanghao(String yishengzhanghao) {
		this.yishengzhanghao = yishengzhanghao;
	}
	/**
	 * 获取：医生账号
	 */
	public String getYishengzhanghao() {
		return yishengzhanghao;
	}
	/**
	 * 设置：电话
	 */
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	/**
	 * 获取：电话
	 */
	public String getDianhua() {
		return dianhua;
	}
	/**
	 * 设置：就诊时间
	 */
	public void setJiuzhenshijian(Date jiuzhenshijian) {
		this.jiuzhenshijian = jiuzhenshijian;
	}
	/**
	 * 获取：就诊时间
	 */
	public Date getJiuzhenshijian() {
		return jiuzhenshijian;
	}
	/**
	 * 设置：通知时间
	 */
	public void setTongzhishijian(Date tongzhishijian) {
		this.tongzhishijian = tongzhishijian;
	}
	/**
	 * 获取：通知时间
	 */
	public Date getTongzhishijian() {
		return tongzhishijian;
	}
	/**
	 * 设置：账号
	 */
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
	/**
	 * 设置：手机
	 */
	public void setShouji(String shouji) {
		this.shouji = shouji;
	}
	/**
	 * 获取：手机
	 */
	public String getShouji() {
		return shouji;
	}
	/**
	 * 设置：通知备注
	 */
	public void setTongzhibeizhu(String tongzhibeizhu) {
		this.tongzhibeizhu = tongzhibeizhu;
	}
	/**
	 * 获取：通知备注
	 */
	public String getTongzhibeizhu() {
		return tongzhibeizhu;
	}
	
	public String getYuyuebianhao() {
		return yuyuebianhao;
	}

	public void setYuyuebianhao(String yuyuebianhao) {
		this.yuyuebianhao = yuyuebianhao;
	}
	
	public Integer getTongzhileixing() {
		return tongzhileixing;
	}

	public void setTongzhileixing(Integer tongzhileixing) {
		this.tongzhileixing = tongzhileixing;
	}
	
	public Integer getFasongzhuangtai() {
		return fasongzhuangtai;
	}

	public void setFasongzhuangtai(Integer fasongzhuangtai) {
		this.fasongzhuangtai = fasongzhuangtai;
	}
	
	public String getShibaiyuanyin() {
		return shibaiyuanyin;
	}

	public void setShibaiyuanyin(String shibaiyuanyin) {
		this.shibaiyuanyin = shibaiyuanyin;
	}
	
	public Integer getChongshicishu() {
		return chongshicishu;
	}

	public void setChongshicishu(Integer chongshicishu) {
		this.chongshicishu = chongshicishu;
	}
	
	public Date getJihuafasongshijian() {
		return jihuafasongshijian;
	}

	public void setJihuafasongshijian(Date jihuafasongshijian) {
		this.jihuafasongshijian = jihuafasongshijian;
	}

}
