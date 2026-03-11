package com.cl.entity.view;

import com.cl.entity.JiuzhenqiandaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 就诊签到
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-27 15:44:15
 */
@TableName("jiuzhenqiandao")
public class JiuzhenqiandaoView  extends JiuzhenqiandaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiuzhenqiandaoView(){
	}
 
 	public JiuzhenqiandaoView(JiuzhenqiandaoEntity jiuzhenqiandaoEntity){
 	try {
			BeanUtils.copyProperties(this, jiuzhenqiandaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
