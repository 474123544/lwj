package com.cl.entity.view;

import com.cl.entity.PaibanxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 排班信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("paibanxinxi")
public class PaibanxinxiView  extends PaibanxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PaibanxinxiView(){
	}
 
 	public PaibanxinxiView(PaibanxinxiEntity paibanxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, paibanxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
