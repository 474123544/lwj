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
 * 处方信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("chufangxinxi")
public class ChufangxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChufangxinxiEntity() {
		
	}
	
	public ChufangxinxiEntity(T t) {
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
	 * 药品名称
	 */
					
	private String yaopinmingcheng;
	
	/**
	 * 单价
	 */
					
	private Double danjia;
	
	/**
	 * 开方数量
	 */
					
	private Integer kaifangshuliang;
	
	/**
	 * 服用方法
	 */
					
	private String fuyongfangfa;
	
	/**
	 * 其他费用
	 */
					
	private Double qitafeiyong;
	
	/**
	 * 需缴金额
	 */
					
	private Double xujiaojine;
	
	/**
	 * 账号
	 */
					
	private String zhanghao;
	
	/**
	 * 手机
	 */
					
	private String shouji;
	
	/**
	 * 开方时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date kaifangshijian;
	
	/**
	 * 医生账号
	 */
					
	private String yishengzhanghao;
	
	/**
	 * 电话
	 */
					
	private String dianhua;
	
	/**
	 * 其他
	 */
					
	private String qita;
	
	/**
	 * 是否支付
	 */
					
	private String ispay;
	

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
	 * 设置：药品名称
	 */
	public void setYaopinmingcheng(String yaopinmingcheng) {
		this.yaopinmingcheng = yaopinmingcheng;
	}
	/**
	 * 获取：药品名称
	 */
	public String getYaopinmingcheng() {
		return yaopinmingcheng;
	}
	/**
	 * 设置：单价
	 */
	public void setDanjia(Double danjia) {
		this.danjia = danjia;
	}
	/**
	 * 获取：单价
	 */
	public Double getDanjia() {
		return danjia;
	}
	/**
	 * 设置：开方数量
	 */
	public void setKaifangshuliang(Integer kaifangshuliang) {
		this.kaifangshuliang = kaifangshuliang;
	}
	/**
	 * 获取：开方数量
	 */
	public Integer getKaifangshuliang() {
		return kaifangshuliang;
	}
	/**
	 * 设置：服用方法
	 */
	public void setFuyongfangfa(String fuyongfangfa) {
		this.fuyongfangfa = fuyongfangfa;
	}
	/**
	 * 获取：服用方法
	 */
	public String getFuyongfangfa() {
		return fuyongfangfa;
	}
	/**
	 * 设置：其他费用
	 */
	public void setQitafeiyong(Double qitafeiyong) {
		this.qitafeiyong = qitafeiyong;
	}
	/**
	 * 获取：其他费用
	 */
	public Double getQitafeiyong() {
		return qitafeiyong;
	}
	/**
	 * 设置：需缴金额
	 */
	public void setXujiaojine(Double xujiaojine) {
		this.xujiaojine = xujiaojine;
	}
	/**
	 * 获取：需缴金额
	 */
	public Double getXujiaojine() {
		return xujiaojine;
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
	 * 设置：开方时间
	 */
	public void setKaifangshijian(Date kaifangshijian) {
		this.kaifangshijian = kaifangshijian;
	}
	/**
	 * 获取：开方时间
	 */
	public Date getKaifangshijian() {
		return kaifangshijian;
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
	 * 设置：其他
	 */
	public void setQita(String qita) {
		this.qita = qita;
	}
	/**
	 * 获取：其他
	 */
	public String getQita() {
		return qita;
	}
	/**
	 * 设置：是否支付
	 */
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}

}
