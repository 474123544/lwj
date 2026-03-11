package com.cl.entity.view;

import com.cl.entity.ChukujiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 出库记录
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("chukujilu")
public class ChukujiluView  extends ChukujiluEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChukujiluView(){
	}
 
 	public ChukujiluView(ChukujiluEntity chukujiluEntity){
 	try {
			BeanUtils.copyProperties(this, chukujiluEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
